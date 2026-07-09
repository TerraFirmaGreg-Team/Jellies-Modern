package team.terrafirmagreg.jellies.common.entity.special.pyritie;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import team.terrafirmagreg.jellies.Jellies;

public class PyritieJellieModel<T extends PyritieJellie> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            Jellies.id("pyritie_jellie"), "main");

    private final ModelPart root;

    public PyritieJellieModel(ModelPart root) {
        this.root = root;
    }

    public static LayerDefinition createInnerBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition inner = partdefinition.addOrReplaceChild("inner", CubeListBuilder.create().texOffs(41, 13).addBox(-6.0F, 15.0F, -6.0F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(39, 3).addBox(-5.0F, 14.0F, -5.0F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -3.0F, 0.5F));
        inner.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(88, 0).addBox(-5.0F, 0.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-6.0F, 15.0F, -0.5F, 0.0F, 0.0F, -0.6109F));
        inner.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(88, 0).mirror().addBox(0.0F, 0.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offsetAndRotation(5.0F, 15.0F, -0.5F, 0.0F, 0.0F, 0.6109F));
        inner.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(116, 18).addBox(-1.0F, -2.2F, 0.3F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, 21.4314F, 11.6447F, 1.3701F, 0.0F, 0.0F));
        inner.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(112, 10).addBox(-1.5F, -3.4F, -0.3F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, 23.4965F, 9.4686F, 0.7592F, 0.0F, 0.0F));
        inner.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(108, 0).addBox(-2.0F, -4.0F, 0.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, 25.0F, 4.7F, 0.3054F, 0.0F, 0.0F));

        PartDefinition flower = partdefinition.addOrReplaceChild("flower", CubeListBuilder.create(), PartPose.offset(0.0F, 9.8F, 0.0F));

        PartDefinition red_layer = flower.addOrReplaceChild("red_layer", CubeListBuilder.create().texOffs(38, 35).addBox(-6.0F, 0.0F, -6.0F, 12.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        red_layer.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(45, 47).addBox(-8.0F, -2.5357F, -8.4378F, 16.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -0.4363F));
        red_layer.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(45, 47).addBox(-8.0F, -2.5357F, -8.4378F, 16.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -2.7053F, 0.0F, -3.1416F));
        red_layer.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(45, 47).addBox(-8.0F, -2.5357F, -8.4378F, 16.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.4363F));
        red_layer.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(45, 47).addBox(-8.0F, -2.5357F, -8.4378F, 16.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition orange_layer = flower.addOrReplaceChild("orange_layer", CubeListBuilder.create(), PartPose.offset(0.0F, -0.9F, 0.0F));
        orange_layer.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 59).addBox(-7.0F, -2.1131F, -6.5315F, 14.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.7854F, 0.0F));
        orange_layer.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 59).addBox(-7.0F, -2.1131F, -6.5315F, 14.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, -0.7854F, 0.0F));
        orange_layer.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 59).addBox(-7.0F, -2.1131F, -6.5315F, 14.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -2.7053F, -0.7854F, 3.1416F));
        orange_layer.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 59).addBox(-7.0F, -2.1131F, -6.5315F, 14.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -2.7053F, 0.7854F, 3.1416F));
        orange_layer.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(-6, 49).addBox(-5.0F, 0.0F, -5.0F, 10.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition semi_orange_layer = flower.addOrReplaceChild("semi_orange_layer",
                CubeListBuilder.create().texOffs(-3, 61).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.4F, 0.0F));
        semi_orange_layer.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(3, 69).addBox(-5.0F, -1.6905F, -4.6252F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -2.7053F, 0.0F, 3.1416F));
        semi_orange_layer.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(3, 69).addBox(-5.0F, -1.6905F, -4.6252F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -0.4363F));
        semi_orange_layer.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(3, 69).addBox(-5.0F, -1.6905F, -4.6252F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));
        semi_orange_layer.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(3, 69).addBox(-5.0F, -1.6905F, -4.6252F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.4363F));

        PartDefinition yellow_layer = flower.addOrReplaceChild("yellow_layer", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -0.7854F, 0.0F));
        yellow_layer.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(23, 55).addBox(-4.0F, 1.2678F, -3.7189F, 8.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.4363F));
        yellow_layer.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(23, 55).addBox(-4.0F, 1.2678F, -3.7189F, 8.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, -0.4363F));
        yellow_layer.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(23, 55).addBox(-4.0F, 1.2678F, -3.7189F, 8.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 2.7053F, 0.0F, -3.1416F));
        yellow_layer.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(19, 49).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
        yellow_layer.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(23, 55).addBox(-4.0F, 1.2678F, -3.7189F, 8.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition pistil = flower.addOrReplaceChild("pistil", CubeListBuilder.create().texOffs(76, 4).addBox(0.0F, -5.0F, -3.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -1.5708F, 0.0F));
        pistil.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(76, -1).addBox(0.0F, -5.0F, -3.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -3.1416F, -1.0472F, -3.1416F));
        pistil.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(76, -6).addBox(0.0F, -5.0F, -3.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public static LayerDefinition createOuterBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition outer = partdefinition.addOrReplaceChild("outer", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, 12.0F, -6.0F, 13.0F, 11.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(-6.0F, 10.0F, -5.0F, 11.0F, 14.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, -0.5F));
        outer.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(111, 24).addBox(-1.5F, -3.2F, 0.9F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, 19.4314F, 12.9447F, 1.3701F, 0.0F, 0.0F));
        outer.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(106, 32).addBox(-2.5F, -4.8F, -0.8F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, 21.4965F, 10.7686F, 0.7592F, 0.0F, 0.0F));
        outer.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(102, 42).addBox(-3.0F, -6.1F, -0.3F, 7.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, 23.0F, 6.0F, 0.3054F, 0.0F, 0.0F));
        outer.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(35, 100).mirror().addBox(-1.0F, -1.0F, -3.5F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offsetAndRotation(5.0F, 12.0F, 0.5F, 0.0F, 0.0F, 0.6109F));
        outer.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(35, 100).addBox(-6.0F, -1.0F, -3.5F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-6.0F, 12.0F, 0.5F, 0.0F, 0.0F, -0.6109F));

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
