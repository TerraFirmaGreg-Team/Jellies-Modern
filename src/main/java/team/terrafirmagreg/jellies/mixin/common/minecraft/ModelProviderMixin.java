package team.terrafirmagreg.jellies.mixin.common.minecraft;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelProvider;

import team.terrafirmagreg.jellies.Jellies;

@Mixin(value = ModelProvider.class, remap = false)
public class ModelProviderMixin {
    @Final
    @Shadow
    protected String folder;

    @Inject(method = "extendWithFolder", at = @At("HEAD"), remap = false, cancellable = true)
    private void jellies$extendWithFolder(ResourceLocation rl, CallbackInfoReturnable<ResourceLocation> cir) {
        if (rl.getNamespace().equals("tfm") && !rl.getPath().startsWith("item") && !rl.getPath().startsWith("block"))
            cir.setReturnValue(Jellies.id(folder + "/" + rl.getPath()));
    }
}
