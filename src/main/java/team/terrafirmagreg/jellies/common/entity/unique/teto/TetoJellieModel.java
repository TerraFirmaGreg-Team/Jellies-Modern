package team.terrafirmagreg.jellies.common.entity.unique.teto;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import team.terrafirmagreg.jellies.Jellies;

public class TetoJellieModel<T extends TetoJellie> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            Jellies.id("teto_jellie"), "main");

    private final ModelPart root;

    public TetoJellieModel(ModelPart root) {
        this.root = root;
    }

    public static LayerDefinition createInnerBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition inner = partdefinition.addOrReplaceChild("inner", CubeListBuilder.create().texOffs(44, 24).addBox(-6.0F, 15.0F, -6.0F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(44, 46).addBox(-5.0F, 14.0F, -5.0F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -3.0F, 0.5F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public static LayerDefinition createOuterBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition outer = partdefinition.addOrReplaceChild("outer", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, 12.0F, -6.0F, 13.0F, 11.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(-6.0F, 10.0F, -5.0F, 11.0F, 14.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, -0.5F));

        PartDefinition teto_things = partdefinition.addOrReplaceChild("teto_things", CubeListBuilder.create().texOffs(33, 24).addBox(-4.0F, -21.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 24.0F, 0.0F));

        teto_things.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(52, 0).addBox(-3.0F, -4.5F, -12.5F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(52, 9).mirror().addBox(-2.5F, -1.5F, -12.0F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(52, 17).mirror().addBox(-2.0F, 1.5F, -11.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -9.5F, 0.0F, 0.0F, -1.5708F, 0.0F));
        teto_things.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(52, 9).addBox(-2.5F, -0.5F, 5.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(52, 17).addBox(-2.0F, 2.5F, 6.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(52, 0).addBox(-3.0F, -3.5F, 5.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -10.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public static LayerDefinition createFaceLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition face = partdefinition.addOrReplaceChild("face", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, 12.0F, -6.0F, 13.0F, 11.0F, 13.0F, new CubeDeformation(0.1F))
                .texOffs(0, 24).addBox(-6.0F, 10.0F, -5.0F, 11.0F, 14.0F, 11.0F, new CubeDeformation(0.1F)), PartPose.offset(0.5F, 0.0F, -0.5F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public ModelPart root() {
        return this.root;
    }
}
