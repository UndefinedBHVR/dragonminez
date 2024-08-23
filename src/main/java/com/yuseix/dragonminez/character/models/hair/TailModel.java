package com.yuseix.dragonminez.character.models.hair;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


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

public class TailModel extends HumanoidModel<AbstractClientPlayer> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DragonMineZ.MOD_ID, "hairs"), "tail");
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart tail4;

	public TailModel(ModelPart root) {
        super(root);
        this.tail1 = root.getChild("tail1");
		this.tail2 = tail1.getChild("tail2");
		this.tail3 = tail2.getChild("tail3");
		this.tail4 = tail3.getChild("tail4");
		
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE,0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tail1 = partdefinition.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.75F, -0.75F, -2.0F, 1.5F, 1.5F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 9.75F, 4.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, -1.5F, 1.5F, 1.5F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, -0.25F, 3.5F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 0).addBox(-0.75F, -0.75F, -2.0F, 1.5F, 1.5F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 0.25F, 4.5F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(0, 0).addBox(-0.75F, -0.75F, -2.0F, 1.5F, 1.5F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);

		//this.tail1.copyFrom(this.body);
		float random = (float) Math.random();

		this.tail1.yRot = (float) (Math.cos((pEntity.tickCount+random)*0.1f)*0.09F);
		this.tail1.xRot = (float) (Math.cos((pEntity.tickCount+random)*0.1f)*0.09F);

		this.tail2.yRot = (float) (Math.cos((pEntity.tickCount+random)*0.1f)*0.09F);
		this.tail2.xRot = (float) (Math.cos((pEntity.tickCount+random)*0.1f)*0.09F);

		this.tail3.yRot = (float) (Math.cos((pEntity.tickCount+random)*0.1f)*0.09F);
		this.tail3.xRot = (float) (Math.cos((pEntity.tickCount+random)*0.1f)*0.09F);

		this.tail4.yRot = (float) (Math.cos((pEntity.tickCount+random)*0.1f)*0.09F);
		this.tail4.xRot = (float) (Math.cos((pEntity.tickCount+random)*0.1f)*0.09F);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		tail1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}