package team.terrafirmagreg.jellies.common.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class JellieBaseRenderer extends MobRenderer<JellieBase, JellieBaseModel<JellieBase>> {

    public JellieBaseRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new JellieBaseModel<>(ctx.bakeLayer(JellieBaseModel.LAYER_LOCATION)), 0.4F);
        this.addLayer(new JellieBaseOuterLayer(this, ctx.getModelSet()));
        this.addLayer(new JellieBaseFaceLayer(this, ctx.getModelSet()));
    }

    protected void setupRotations(JellieBase entityLiving, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, poseStack, ageInTicks, rotationYaw, partialTicks);
    }

    public ResourceLocation getTextureLocation(JellieBase entity) {
        return entity.getTextureLocation();
    }
}
