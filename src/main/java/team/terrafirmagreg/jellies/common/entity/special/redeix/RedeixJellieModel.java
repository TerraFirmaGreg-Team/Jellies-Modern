package team.terrafirmagreg.jellies.common.entity.special.redeix;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import team.terrafirmagreg.jellies.Jellies;

public class RedeixJellieModel<T extends RedeixJellie> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            Jellies.id("redeix_jellie"), "main");

    private final ModelPart root;

    public RedeixJellieModel(ModelPart root) {
        this.root = root;
    }

    public static LayerDefinition createInnerBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition inner = partdefinition.addOrReplaceChild("inner", CubeListBuilder.create().texOffs(44, 24).addBox(-5.5F, 15.0F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(44, 46).addBox(-4.5F, 14.0F, -4.5F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(64, 0).addBox(5.4F, 22.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        inner.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, -5.0F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 56).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 49).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -5.0F, 0.4363F, 0.0F, 0.0F));

        inner.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(52, 6).addBox(-0.2F, -3.0F, -2.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(12.2716F, 26.0F, 0.8519F, 0.0F, 0.8727F, 0.0F));

        inner.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(52, 0).addBox(-0.2F, -3.5F, -3.3F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(9.5F, 26.0F, 2.0F, 0.0F, 0.3927F, 0.0F));

        inner.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 53).addBox(-1.0F, -5.0F, -3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 49).addBox(-1.0F, -4.0F, -3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(14, 49).addBox(-1.0F, -3.0F, -3.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, 5.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        bb_main.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(49, 11).addBox(-4.0F, 0.0F, -4.5F, 7.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -3.0F, -6.5F, 1.5708F, -1.1345F, -1.5708F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public static LayerDefinition createOuterBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition outer = partdefinition.addOrReplaceChild("outer", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, 12.0F, -6.5F, 13.0F, 11.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(-5.5F, 10.0F, -5.5F, 11.0F, 14.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(32, 58).addBox(5.5F, 18.5F, -2.5F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        outer.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 68).addBox(-1.5F, -5.5F, -3.6F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 63).addBox(-1.5F, -4.5F, -3.6F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 58).addBox(-1.5F, -3.5F, -3.6F, 3.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 5.0F, -0.4363F, 0.0F, 0.0F));

        outer.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(64, 62).addBox(-0.1F, -3.5F, -3.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(12.2716F, 23.0F, 0.8519F, 0.0F, 0.8727F, 0.0F));

        outer.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(50, 60).addBox(-0.1F, -4.0F, -3.8F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(9.5F, 23.0F, 2.0F, 0.0F, 0.3927F, 0.0F));

        outer.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 76).mirror().addBox(-1.5F, -4.5F, -0.4F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 72).mirror().addBox(-1.5F, -5.5F, 0.6F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(14, 68).addBox(-1.5F, -3.5F, -1.4F, 3.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, -5.0F, 0.4363F, 0.0F, 0.0F));

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
