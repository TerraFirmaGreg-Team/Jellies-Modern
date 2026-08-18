package team.terrafirmagreg.jellies.mixin.common.tfc.items;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.dries007.tfc.common.blocks.devices.FirepitBlock;
import net.dries007.tfc.common.items.FirestarterItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import team.terrafirmagreg.jellies.common.data.JelliesEntities;
import team.terrafirmagreg.jellies.common.entity.unique.teto.TetoJellie;

@Mixin(value = FirestarterItem.class)
public class FirestarterItemMixin {
    @WrapOperation(method = "onUseTick", at = @At(value = "INVOKE", target = "Lnet/dries007/tfc/util/events/StartFireEvent;startFire(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)Z"), remap = false)
    private boolean wrapStartFire(
            Level level,
            BlockPos pos,
            BlockState state,
            Direction direction,
            Player player,
            ItemStack stack,
            Operation<Boolean> original) {
        BlockPos abovePos = pos.above();
        if (FirepitBlock.canSurvive(level, abovePos)) {
            List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, new AABB((double) abovePos.getX() - (double) 0.5F, (double) abovePos.getY(), (double) abovePos.getZ() - (double) 0.5F,
                    (double) abovePos.getX() + (double) 1.5F, (double) (abovePos.getY() + 1), (double) abovePos.getZ() + (double) 1.5F));
            List<ItemEntity> usableItems = new ArrayList();
            int red_offering = 0;
            int bread_offering = 0;

            for (ItemEntity entity : items) {
                ItemStack foundStack = entity.getItem();
                Item foundItem = foundStack.getItem();
                int itemCount = foundStack.getCount();
                if (foundItem == ChemicalHelper.get(TagPrefix.toolHeadDrill, GTMaterials.RedSteel).getItem()) {
                    red_offering += itemCount;
                    usableItems.add(entity);
                }
                if (TetoJellie.isBread(foundStack)) {
                    bread_offering += itemCount;
                    usableItems.add(entity);
                }
            }

            if (red_offering >= 2 && bread_offering >= 1) {
                if ((double) level.random.nextFloat() < 1.0f) {
                    usableItems.forEach(Entity::kill);
                    TetoJellie tetoJellie = JelliesEntities.TETO_JELLIE.create(level);

                    if (tetoJellie != null) {
                        tetoJellie.moveTo(abovePos.getX() + 0.5, abovePos.getY(), abovePos.getZ() + 0.5, 0.0F, 0.0F);

                        tetoJellie.invulnerableTime = 200;
                        tetoJellie.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0, false, false, false));
                        level.addFreshEntity(tetoJellie);

                        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
                        if (lightning != null) {
                            lightning.moveTo(abovePos.getX() + 0.5, abovePos.getY(), abovePos.getZ() + 0.5);
                            level.addFreshEntity(lightning);
                        }

                        tetoJellie.clearFire();
                    }
                }

                return false;
            }
        }

        return original.call(level, pos, state, direction, player, stack);
    }
}
