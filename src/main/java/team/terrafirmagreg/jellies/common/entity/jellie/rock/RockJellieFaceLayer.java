package team.terrafirmagreg.jellies.common.entity.jellie.rock;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

import team.terrafirmagreg.jellies.Jellies;

@SuppressWarnings({ "unchecked" })
public class RockJellieFaceLayer extends RenderLayer<RockJellie, RockJellieModel<RockJellie>> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            Jellies.id("rock_jellie_face"), "main");

    private final RockJellieModel model;

    public RockJellieFaceLayer(RenderLayerParent<RockJellie, RockJellieModel<RockJellie>> renderer, EntityModelSet ctx) {
        super(renderer);
        this.model = new RockJellieModel(ctx.bakeLayer(LAYER_LOCATION));
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, RockJellie livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
            float netHeadYaw, float headPitch) {
        if (!livingEntity.isInvisible()) {
            VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutout(Jellies.id("textures/entity/jellie/face/" + livingEntity.getVariantName() + "_smile.png")));

            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
