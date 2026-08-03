package team.terrafirmagreg.jellies.common.entity.unique.teto;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TetoJellieRenderer extends MobRenderer<TetoJellie, TetoJellieModel<TetoJellie>> {
    private final float scale = 1.0f;

    public TetoJellieRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new TetoJellieModel<>(ctx.bakeLayer(TetoJellieModel.LAYER_LOCATION)), 0.4F);
        this.addLayer(new TetoJellieOuterLayer(this, ctx.getModelSet()));
        this.addLayer(new TetoJellieFaceLayer(this, ctx.getModelSet()));
    }

    protected void setupRotations(TetoJellie entityLiving, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, poseStack, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void scale(TetoJellie entity, PoseStack poseStack, float scale) {
        float amount = entity.isBaby() ? this.scale * 0.6f : this.scale;
        poseStack.scale(amount, amount, amount);
        super.scale(entity, poseStack, scale);
    }

    public ResourceLocation getTextureLocation(TetoJellie entity) {
        return entity.getTextureLocation();
    }
}
