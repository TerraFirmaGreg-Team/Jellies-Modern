package team.terrafirmagreg.jellies.mixin.common.tfc.entities;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.dries007.tfc.common.entities.BrainBreeder;
import net.dries007.tfc.common.entities.ai.livestock.BreedBehavior;

import team.terrafirmagreg.jellies.common.entity.jellie.base.JellieBase;

@Mixin(value = BreedBehavior.class, remap = false)
public class BreedBehaviorMixin {
    // spotless:off
    @Redirect(
            method = "checkExtraStartConditions(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/animal/Animal;)Z",
            at = @At(value = "INVOKE", target = "Lnet/dries007/tfc/common/entities/BrainBreeder;isMale()Z"))
    // spotless:on
    private boolean tfg$slimeCanStartBreeding(BrainBreeder breeder) {
        if (breeder instanceof JellieBase) {
            return true;
        }

        return breeder.isMale();
    }

    // spotless:off
    @Redirect(
            method = "hasValidBreedPartner(Lnet/minecraft/world/entity/animal/Animal;)Z",
            at = @At(value = "INVOKE", target = "Lnet/dries007/tfc/common/entities/BrainBreeder;isMale()Z"))
    // spotless:on
    private boolean tfg$slimeIsValidPartner(BrainBreeder breeder) {
        if (breeder instanceof JellieBase) {
            return false;
        }

        return breeder.isMale();
    }
}
