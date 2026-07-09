package team.terrafirmagreg.jellies.common.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

import team.terrafirmagreg.jellies.Jellies;

@SuppressWarnings({ "unchecked" })
public class JellieBaseOuterLayer extends RenderLayer<JellieBase, JellieBaseModel<JellieBase>> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            Jellies.id("jellie_outer"), "main");

    private final JellieBaseModel model;

    public JellieBaseOuterLayer(RenderLayerParent<JellieBase, JellieBaseModel<JellieBase>> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new JellieBaseModel(modelSet.bakeLayer(LAYER_LOCATION));
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, JellieBase livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
            float netHeadYaw, float headPitch) {
        Minecraft minecraft = Minecraft.getInstance();
        boolean flag = minecraft.shouldEntityAppearGlowing(livingEntity) && livingEntity.isInvisible();
        if (!livingEntity.isInvisible() || flag) {
            VertexConsumer vertexconsumer;
            if (flag) {
                vertexconsumer = buffer.getBuffer(RenderType.outline(this.getTextureLocation(livingEntity)));
            } else {
                vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(livingEntity)));
            }

            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }

    }
}
