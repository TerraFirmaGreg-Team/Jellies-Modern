package team.terrafirmagreg.jellies.common.entity.special.pyritie;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PyritieJellieRenderer extends MobRenderer<PyritieJellie, PyritieJellieModel<PyritieJellie>> {

    public PyritieJellieRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new PyritieJellieModel<>(ctx.bakeLayer(PyritieJellieModel.LAYER_LOCATION)), 0.4F);
        this.addLayer(new PyritieJellieOuterLayer(this, ctx.getModelSet()));
        this.addLayer(new PyritieJellieFaceLayer(this, ctx.getModelSet()));
    }

    protected void setupRotations(PyritieJellie entityLiving, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, poseStack, ageInTicks, rotationYaw, partialTicks);
    }

    public ResourceLocation getTextureLocation(PyritieJellie entity) {
        return entity.getTextureLocation();
    }
}
