package team.terrafirmagreg.jellies.common.entity.special.redeix;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RedeixJellieRenderer extends MobRenderer<RedeixJellie, RedeixJellieModel<RedeixJellie>> {

    public RedeixJellieRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new RedeixJellieModel<>(ctx.bakeLayer(RedeixJellieModel.LAYER_LOCATION)), 0.4F);
        this.addLayer(new RedeixJellieOuterLayer(this, ctx.getModelSet()));
        this.addLayer(new RedeixJellieFaceLayer(this, ctx.getModelSet()));
    }

    protected void setupRotations(RedeixJellie entityLiving, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, poseStack, ageInTicks, rotationYaw, partialTicks);
    }

    public ResourceLocation getTextureLocation(RedeixJellie entity) {
        return entity.getTextureLocation();
    }
}
