package team.terrafirmagreg.jellies.common.entity.jellie.rock;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RockJellieRenderer extends MobRenderer<RockJellie, RockJellieModel<RockJellie>> {

    public RockJellieRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new RockJellieModel<>(ctx.bakeLayer(RockJellieModel.LAYER_LOCATION)), 0.4F);
        this.addLayer(new RockJellieOuterLayer(this, ctx.getModelSet()));
        this.addLayer(new RockJellieFaceLayer(this, ctx.getModelSet()));
    }

    protected void setupRotations(RockJellie entityLiving, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, poseStack, ageInTicks, rotationYaw, partialTicks);
    }

    public ResourceLocation getTextureLocation(RockJellie entity) {
        return entity.getTextureLocation();
    }
}
