package com.yuseix.dragonminez.character.models.bioandroid;// Made with Blockbench 4.9.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;

public class BioAndroidModel<T extends LivingEntity> extends PlayerModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DragonMineZ.MOD_ID, "bioandroidmodel"), "main");
	public final ModelPart headwa;
	public final ModelPart bodywa;
	public final ModelPart rightarm;
	public final ModelPart leftarm;
	public final ModelPart rightleg;
	public final ModelPart leftleg;

	public BioAndroidModel(ModelPart root) {
		super(root, false);
		this.headwa = root.getChild("head");
		this.bodywa = root.getChild("body");
		this.rightarm = root.getChild("right_arm");
		this.leftarm = root.getChild("left_arm");
		this.rightleg = root.getChild("right_leg");
		this.leftleg = root.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.001F))
		.texOffs(0, 0).addBox(-1.0F, -3.25F, -4.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0, 13, 0));
		partdefinition.addOrReplaceChild("ear", CubeListBuilder.create(), PartPose.offset(0, 13, 0));
		partdefinition.addOrReplaceChild("cloak", CubeListBuilder.create(), PartPose.offset(0, 13, 0));
		partdefinition.addOrReplaceChild("left_sleeve", CubeListBuilder.create(), PartPose.offset(0, 13, 0));
		partdefinition.addOrReplaceChild("right_sleeve", CubeListBuilder.create(), PartPose.offset(0, 13, 0));
		partdefinition.addOrReplaceChild("left_pants", CubeListBuilder.create(), PartPose.offset(0, 13, 0));
		partdefinition.addOrReplaceChild("right_pants", CubeListBuilder.create(), PartPose.offset(0, 13, 0));
		partdefinition.addOrReplaceChild("jacket", CubeListBuilder.create(), PartPose.offset(0, 13, 0));


		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(50, 51).addBox(2.25F, -5.75F, -3.25F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.75F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(50, 51).addBox(-4.25F, -5.75F, -3.25F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.75F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cabeza2 = head.addOrReplaceChild("cabeza2", CubeListBuilder.create().texOffs(24, 0).addBox(-0.5F, -5.9F, -4.502F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(38, 16).addBox(-4.0F, -8.9F, -4.499F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(38, 22).addBox(-2.0F, -9.65F, -4.4998F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 0.25F));

		PartDefinition cube_r3 = cabeza2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(48, 6).addBox(-5.05F, -11.6F, -4.501F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r4 = cabeza2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(48, 6).addBox(3.05F, -12.1F, -4.501F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition cube_r5 = cabeza2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(12, 52).addBox(-0.15F, -9.85F, -4.4997F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r6 = cabeza2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(12, 52).addBox(-0.75F, -9.85F, -4.4997F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r7 = cabeza2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 48).addBox(1.55F, -10.85F, -4.5F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r8 = cabeza2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(28, 48).addBox(1.591F, -13.9F, -4.503F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition cube_r9 = cabeza2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(28, 48).addBox(-2.55F, -13.8F, -4.503F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r10 = cabeza2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 48).addBox(-3.55F, -10.8F, -4.5F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r11 = cabeza2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(50, 35).addBox(2.0F, -7.0F, -4.501F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r12 = cabeza2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(38, 51).addBox(-6.0F, -7.0F, -4.503F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(46, 44).addBox(-1.5F, 8.0F, 2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(46, 44).addBox(-1.5F, 8.0F, 2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(46, 44).addBox(-1.5F, 8.0F, 2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(46, 44).addBox(-1.5F, 8.0F, 2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition tail5 = tail4.addOrReplaceChild("tail5", CubeListBuilder.create().texOffs(46, 44).addBox(-1.5F, 8.0F, 2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition tail6 = tail5.addOrReplaceChild("tail6", CubeListBuilder.create().texOffs(46, 28).addBox(-1.5F, 8.0F, 6.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, 8.5F, 10.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition alas = body.addOrReplaceChild("alas", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r13 = alas.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(24, 16).addBox(0.0F, 0.5F, 2.0F, 6.0F, 19.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0872F, 0.0F, -0.1747F));

		PartDefinition cube_r14 = alas.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(24, 16).addBox(-6.0F, 0.5F, 2.0F, 6.0F, 19.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0872F, 0.0F, 0.1747F));

		PartDefinition rightarm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 36).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition leftarm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(34, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition rightleg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition leftleg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.headwa.copyFrom(this.head);
		this.bodywa.copyFrom(this.body);
		this.rightarm.copyFrom(this.rightArm);
		this.leftarm.copyFrom(this.leftArm);
		this.rightleg.copyFrom(this.rightLeg);
		this.leftleg.copyFrom(this.leftLeg);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		headwa.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bodywa.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}