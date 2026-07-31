package team.terrafirmagreg.jellies.common.entity.special.redeix;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import team.terrafirmagreg.jellies.common.entity.jellie.rock.RockJellie;

public class RedeixJellieRenderer extends MobRenderer<RedeixJellie, RedeixJellieModel<RedeixJellie>> {
    private final float scale = 1.0f;

    public RedeixJellieRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new RedeixJellieModel<>(ctx.bakeLayer(RedeixJellieModel.LAYER_LOCATION)), 0.4F);
        this.addLayer(new RedeixJellieOuterLayer(this, ctx.getModelSet()));
        this.addLayer(new RedeixJellieFaceLayer(this, ctx.getModelSet()));
    }

    protected void setupRotations(RedeixJellie entityLiving, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, poseStack, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void scale(RedeixJellie entity, PoseStack poseStack, float scale) {
        float amount = entity.isBaby() ? this.scale * 0.6f : this.scale;
        poseStack.scale(amount, amount, amount);
        super.scale(entity, poseStack, scale);
    }

    public ResourceLocation getTextureLocation(RedeixJellie entity) {
        return entity.getTextureLocation();
    }
}
