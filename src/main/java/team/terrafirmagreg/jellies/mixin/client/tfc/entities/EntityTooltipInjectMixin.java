package team.terrafirmagreg.jellies.mixin.client.tfc.entities;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.dries007.tfc.compat.jade.common.EntityTooltip;
import net.dries007.tfc.compat.jade.common.EntityTooltips;
import net.dries007.tfc.compat.jade.common.RegisterCallback;
import net.minecraft.world.entity.Entity;

import team.terrafirmagreg.jellies.common.entity.JellieBase;

@Mixin(value = EntityTooltips.class, remap = false, priority = 500)
public class EntityTooltipInjectMixin {
    @Inject(method = "register", at = @At("TAIL"), remap = false)
    private static void jellies$registerTooltips(
            RegisterCallback<EntityTooltip, Entity> registry,
            CallbackInfo ci) {
        registry.register("jellie", JELLIE_BASE, JellieBase.class);
    }

    @Unique
    private static final EntityTooltip JELLIE_BASE = (level, entity, tooltip) -> {
        if (entity instanceof JellieBase jellie) {
        }
    };
}
