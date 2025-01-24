package com.yuseix.dragonminez.client.character.models.kiweapons;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;

public class KiScytheModel extends HumanoidModel<AbstractClientPlayer> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DragonMineZ.MOD_ID, "kiweapons"), "scythe");
	public final ModelPart scythe;

	public KiScytheModel(ModelPart root) {
        super(root);
        this.scythe = root.getChild("scythe");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition =  HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition scythe = partdefinition.addOrReplaceChild("scythe", CubeListBuilder.create().texOffs(0, 31).addBox(-2.0F, 7.0F, -18.0F, 1.5F, 1.5F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(1, 43).addBox(-1.75F, 7.25F, 13.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(44, 44).addBox(-2.25F, 6.75F, -17.5F, 2.0F, 2.25F, 7.5F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r1 = scythe.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(58, 28).addBox(-0.65F, -3.75F, 4.0F, 0.25F, 5.5F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 29.4F, -0.85F, 1.6144F, 0.0F, 0.0F));

		PartDefinition cube_r2 = scythe.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(55, 16).addBox(-0.65F, -12.0F, 1.0F, 0.25F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 29.4F, -0.85F, 1.0908F, 0.0F, 0.0F));

		PartDefinition cube_r3 = scythe.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(45, 1).addBox(-0.9F, -12.0F, 0.0F, 0.75F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 26.5F, -10.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r4 = scythe.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(1, 0).addBox(-1.0F, -12.0F, -1.0F, 1.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 18.25F, -15.25F, 0.0873F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
		this.scythe.copyFrom(this.rightArm);
		this.scythe.x = 0.5f;
		this.scythe.y = 0.2f;

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		scythe.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}