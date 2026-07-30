package team.terrafirmagreg.jellies.mixin.common.tfc.entities;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.dries007.tfc.common.entities.BrainBreeder;
import net.dries007.tfc.common.entities.ai.livestock.BreedBehavior;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.Animal;

import team.terrafirmagreg.jellies.common.entity.JellieBase;

@Mixin(value = BreedBehavior.class, remap = false)
public class BreedBehaviorMixin {
    // spotless:off
    @Redirect(
            method = "checkExtraStartConditions(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/animal/Animal;)Z",
            at = @At(value = "INVOKE", target = "Lnet/dries007/tfc/common/entities/BrainBreeder;isMale()Z"))
    // spotless:on
    private boolean jellies$genderlessJellieCanStartBreeding(BrainBreeder breeder) {
        if (breeder instanceof JellieBase) {
            return true;
        }

        return breeder.isMale();
    }

    // spotless:off
    @Inject(
            method = "hasValidBreedPartner",
            at = @At("HEAD"), cancellable = true
    )
    // spotless:on
    private void jellies$allowHavingBreedPartner(Animal animal, CallbackInfoReturnable<Boolean> cir) {
        if (!(animal instanceof JellieBase)) {
            return;
        }

        Brain<?> brain = animal.getBrain();
        if (!brain.hasMemoryValue(MemoryModuleType.BREED_TARGET)) {
            return;
        }

        AgeableMob target = brain.getMemory(MemoryModuleType.BREED_TARGET).get();
        cir.setReturnValue(target instanceof JellieBase && target instanceof BrainBreeder);
    }

    // spotless:off
    @Inject(
            method = "findValidBreedPartner",
            at = @At("HEAD"), cancellable = true
    )
    // spotless:on
    private void jellies$allowFindingBreedPartner(Animal animal, CallbackInfoReturnable<Optional<AgeableMob>> cir) {
        if (!(animal instanceof JellieBase)) {
            return;
        }

        Optional<AgeableMob> returnTest = animal.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES).get()
                .findClosest(target -> target instanceof JellieBase && target instanceof Animal targetAnimal && animal.canMate(targetAnimal))
                .map(t -> (AgeableMob) t);

        cir.setReturnValue(returnTest);
    }
}
