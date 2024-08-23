package com.yuseix.dragonminez.character.models.hair;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

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

public class GokuHairModel extends HumanoidModel<AbstractClientPlayer> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DragonMineZ.MOD_ID, "hairs"), "goku");
	private final ModelPart gokucabello;
	private final ModelPart pelo1;
	private final ModelPart pelo2;
	private final ModelPart pelo3;
	private final ModelPart pelo4;
	private final ModelPart pelo5;
	private final ModelPart pelo6;
	private final ModelPart pelo7;
	private final ModelPart pelo8;
	private final ModelPart pelo9;
	private final ModelPart pelo10;
	private final ModelPart pelo11;
	private final ModelPart pelo12;
	private final ModelPart pelo13;
	private final ModelPart pelo14;
	private final ModelPart pelo15;
	private final ModelPart pelo16;
	private final ModelPart pelo17;
	private final ModelPart pelo18;
	private final ModelPart pelo19;
	private final ModelPart pelo20;
	private final ModelPart pelo21;
	private final ModelPart pelo22;
	private final ModelPart pelo23;
	private final ModelPart pelo24;
	private final ModelPart pelo25;
	private final ModelPart pelo26;
	private final ModelPart pelo27;
	private final ModelPart pelo28;
	private final ModelPart pelo29;
	private final ModelPart pelo30;
	private final ModelPart pelo31;
	private final ModelPart pelo32;
	private final ModelPart pelo33;
	private final ModelPart pelo34;

	public GokuHairModel(ModelPart root) {
        super(root);
        this.gokucabello = root.getChild("gokucabello");
		this.pelo1 = gokucabello.getChild("pelo1");
		this.pelo2 = gokucabello.getChild("pelo2");
		this.pelo3 = gokucabello.getChild("pelo3");
		this.pelo4 = gokucabello.getChild("pelo4");
		this.pelo5 = gokucabello.getChild("pelo5");
		this.pelo6 = gokucabello.getChild("pelo6");
		this.pelo7 = gokucabello.getChild("pelo7");
		this.pelo8 = gokucabello.getChild("pelo8");
		this.pelo9 = gokucabello.getChild("pelo9");
		this.pelo10 = gokucabello.getChild("pelo10");
		this.pelo11 = gokucabello.getChild("pelo11");
		this.pelo12 = gokucabello.getChild("pelo12");
		this.pelo13 = gokucabello.getChild("pelo13");
		this.pelo14 = gokucabello.getChild("pelo14");
		this.pelo15 = gokucabello.getChild("pelo15");
		this.pelo16 = gokucabello.getChild("pelo16");
		this.pelo17 = gokucabello.getChild("pelo17");
		this.pelo18 = gokucabello.getChild("pelo18");
		this.pelo19 = gokucabello.getChild("pelo19");
		this.pelo20 = gokucabello.getChild("pelo20");
		this.pelo21 = gokucabello.getChild("pelo21");
		this.pelo22 = gokucabello.getChild("pelo22");
		this.pelo23 = gokucabello.getChild("pelo23");
		this.pelo24 = gokucabello.getChild("pelo24");
		this.pelo25 = gokucabello.getChild("pelo25");
		this.pelo26 = gokucabello.getChild("pelo26");
		this.pelo27 = gokucabello.getChild("pelo27");
		this.pelo28 = gokucabello.getChild("pelo28");
		this.pelo29 = gokucabello.getChild("pelo29");
		this.pelo30 = gokucabello.getChild("pelo30");
		this.pelo31 = gokucabello.getChild("pelo31");
		this.pelo32 = gokucabello.getChild("pelo32");
		this.pelo33 = gokucabello.getChild("pelo33");
		this.pelo34 = gokucabello.getChild("pelo34");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE,0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition gokucabello = partdefinition.addOrReplaceChild("gokucabello", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition pelo1 = gokucabello.addOrReplaceChild("pelo1", CubeListBuilder.create(), PartPose.offset(-2.7356F, -31.6382F, -4.3561F));

		PartDefinition cube_r1 = pelo1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 33).addBox(-0.8F, -3.0F, -1.0F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6356F, 3.6882F, 0.4061F, -0.0873F, 0.0F, -0.2618F));

		PartDefinition cube_r2 = pelo1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-1, 33).addBox(-1.3F, -3.0F, -1.0F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.15F, 2.3F, 0.0F, -0.3491F, 0.0F, 0.1745F));

		PartDefinition pelo2 = gokucabello.addOrReplaceChild("pelo2", CubeListBuilder.create(), PartPose.offset(-4.2789F, -30.9079F, -3.5479F));

		PartDefinition cube_r3 = pelo2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 33).addBox(-1.1529F, -3.0994F, -0.5344F, 1.3F, 2.1F, 1.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5789F, 3.1079F, -1.0521F, -0.2618F, 0.0F, -0.0436F));

		PartDefinition cube_r4 = pelo2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 33).addBox(-0.528F, -3.0612F, -0.9672F, 1.3F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7211F, 1.4079F, -0.0521F, -0.1309F, 0.0F, 0.3491F));

		PartDefinition pelo3 = gokucabello.addOrReplaceChild("pelo3", CubeListBuilder.create(), PartPose.offset(0.8609F, -33.5884F, -5.7549F));

		PartDefinition cube_r5 = pelo3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 33).addBox(-0.8F, -3.0F, -1.0F, 1.9F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.05F, 4.947F, 1.8596F, -0.0843F, -0.0226F, -0.3917F));

		PartDefinition cube_r6 = pelo3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 33).addBox(-1.3F, -3.0F, -1.0F, 2.6F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, 3.103F, 2.0404F, 0.0F, 0.1745F, 0.829F));

		PartDefinition pelo4 = gokucabello.addOrReplaceChild("pelo4", CubeListBuilder.create(), PartPose.offset(7.815F, -33.4851F, -7.4328F));

		PartDefinition cube_r7 = pelo4.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 33).addBox(-0.87F, -2.8201F, -1.4656F, 1.9F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.315F, 4.4851F, 3.7328F, -0.3532F, -0.1395F, -0.508F));

		PartDefinition cube_r8 = pelo4.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 33).addBox(-1.0F, -3.0F, -1.0F, 2.3F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.615F, 3.3851F, 3.4328F, -0.2618F, 0.0F, -0.7418F));

		PartDefinition pelo5 = gokucabello.addOrReplaceChild("pelo5", CubeListBuilder.create(), PartPose.offset(4.0367F, -31.2182F, -3.1713F));

		PartDefinition cube_r9 = pelo5.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 33).addBox(-0.9681F, -3.1953F, -1.4793F, 1.8F, 1.5F, 1.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0367F, 3.8182F, -0.0287F, -0.1298F, -0.017F, -0.1298F));

		PartDefinition cube_r10 = pelo5.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 33).addBox(-0.9943F, -3.2273F, -1.3862F, 1.8F, 1.9F, 2.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2943F, 2.0773F, -0.1138F, -0.1745F, 0.0F, -0.3491F));

		PartDefinition pelo6 = gokucabello.addOrReplaceChild("pelo6", CubeListBuilder.create(), PartPose.offset(-3.9777F, -25.9869F, -1.5172F));

		PartDefinition cube_r11 = pelo6.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 33).addBox(-0.528F, -3.0612F, -1.0672F, 1.8F, 2.5F, 2.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3277F, 1.2869F, 0.0172F, 0.0F, 0.0F, -1.0036F));

		PartDefinition pelo7 = gokucabello.addOrReplaceChild("pelo7", CubeListBuilder.create(), PartPose.offset(1.7207F, -34.3858F, -1.1922F));

		PartDefinition cube_r12 = pelo7.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 33).addBox(-0.2631F, -1.5683F, -0.8672F, 2.0F, 2.2F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.6207F, 6.3858F, -0.4078F, 0.0F, 0.0F, 0.0436F));

		PartDefinition cube_r13 = pelo7.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 33).addBox(-0.528F, -3.0612F, -1.2672F, 3.4F, 2.5F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.1707F, 7.8358F, -0.4078F, 0.0F, 0.0F, 0.4363F));

		PartDefinition pelo8 = gokucabello.addOrReplaceChild("pelo8", CubeListBuilder.create(), PartPose.offset(2.2F, -19.6F, 4.2F));

		PartDefinition cube_r14 = pelo8.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 33).addBox(-0.528F, -3.0612F, -1.0672F, 1.8F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.1F, -4.95F, -3.6F, 0.0F, 0.0F, -0.9163F));

		PartDefinition pelo9 = gokucabello.addOrReplaceChild("pelo9", CubeListBuilder.create().texOffs(0, 33).addBox(-3.128F, -10.0612F, -4.4672F, 1.9F, 2.7F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, -19.9F, 6.4F, 0.0F, 0.0F, -0.5672F));

		PartDefinition pelo10 = gokucabello.addOrReplaceChild("pelo10", CubeListBuilder.create(), PartPose.offset(4.3F, -25.2F, 4.1F));

		PartDefinition cube_r15 = pelo10.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 33).addBox(-0.528F, -3.0612F, -1.2672F, 3.4F, 2.5F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, -1.0F, -3.6F, 0.0F, 0.0F, 0.5236F));

		PartDefinition pelo11 = gokucabello.addOrReplaceChild("pelo11", CubeListBuilder.create().texOffs(0, 33).addBox(-2.728F, -9.7612F, -4.6672F, 3.1F, 2.2F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, -25.2F, 5.7F, 0.0F, 0.0F, -1.1345F));

		PartDefinition pelo12 = gokucabello.addOrReplaceChild("pelo12", CubeListBuilder.create(), PartPose.offsetAndRotation(2.3F, -26.6F, 4.1F, 0.0F, 0.0F, -1.0908F));

		PartDefinition cube_r16 = pelo12.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 33).addBox(-0.2631F, -1.4683F, 0.0328F, 2.0F, 2.1F, 1.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0F, 0.0F, 0.2618F));

		PartDefinition pelo13 = gokucabello.addOrReplaceChild("pelo13", CubeListBuilder.create(), PartPose.offsetAndRotation(2.3F, -26.6F, 5.6F, 0.0F, 0.0F, -1.0908F));

		PartDefinition cube_r17 = pelo13.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 33).addBox(-0.2631F, -1.4683F, 0.4328F, 2.0F, 2.1F, 1.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0F, 0.0F, 0.2618F));

		PartDefinition pelo14 = gokucabello.addOrReplaceChild("pelo14", CubeListBuilder.create(), PartPose.offset(4.6F, -30.8F, 2.1F));

		PartDefinition cube_r18 = pelo14.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 33).addBox(-0.328F, -1.9612F, -0.6672F, 2.3F, 1.25F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.3F, -0.45F, -3.6F, 0.0F, 0.0F, -1.6581F));

		PartDefinition cube_r19 = pelo14.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 33).addBox(-0.828F, -2.0612F, -0.8672F, 3.0F, 3.0F, 3.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, -0.35F, -3.6F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r20 = pelo14.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 33).addBox(-1.428F, -2.6612F, -1.2672F, 3.6F, 3.8F, 4.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, 1.25F, -3.6F, 0.0F, 0.0F, -0.9599F));

		PartDefinition pelo15 = gokucabello.addOrReplaceChild("pelo15", CubeListBuilder.create(), PartPose.offset(4.6F, -27.6F, 2.1F));

		PartDefinition cube_r21 = pelo15.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 33).addBox(-1.028F, -2.3612F, -0.7672F, 3.5F, 3.2F, 4.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.3351F, -7.2166F, -3.6F, 0.0F, 0.0F, -1.4399F));

		PartDefinition cube_r22 = pelo15.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 33).addBox(-1.928F, -2.2612F, -1.0672F, 5.2F, 4.2F, 5.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.6599F, -6.2646F, -3.6F, 0.0F, 0.0F, -1.0036F));

		PartDefinition cube_r23 = pelo15.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 33).addBox(-1.528F, -2.6612F, -1.5672F, 5.3F, 4.5F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.8F, -4.45F, -3.6F, 0.0F, 0.0F, -0.7418F));

		PartDefinition pelo16 = gokucabello.addOrReplaceChild("pelo16", CubeListBuilder.create(), PartPose.offset(-3.5691F, -26.6169F, 2.1F));

		PartDefinition cube_r24 = pelo16.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 33).addBox(-0.328F, 0.4388F, -0.4672F, 1.2F, 2.2F, 3.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0982F, -8.3668F, -3.6F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r25 = pelo16.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(1, 33).addBox(0.122F, -0.4612F, -0.9672F, 2.25F, 3.05F, 4.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.8186F, -7.3924F, -3.6F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r26 = pelo16.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 33).addBox(-1.628F, -2.6612F, -1.3672F, 4.1F, 4.5F, 5.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.75F, -5.0F, -3.6F, 0.0F, 0.0F, -0.2618F));

		PartDefinition pelo17 = gokucabello.addOrReplaceChild("pelo17", CubeListBuilder.create(), PartPose.offset(-3.3315F, -27.8792F, 2.1F));

		PartDefinition cube_r27 = pelo17.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(1, 33).addBox(-0.228F, 0.8388F, -0.4672F, 0.6F, 1.75F, 2.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.5413F, -4.0634F, -3.6F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r28 = pelo17.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 33).addBox(-1.428F, -0.4612F, -0.7672F, 2.2F, 2.3F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.3387F, -3.1463F, -3.6F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r29 = pelo17.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 33).addBox(-1.628F, -1.8612F, -1.2672F, 2.7F, 2.7F, 4.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.75F, -2.0F, -3.6F, 0.0F, 0.0F, 0.1309F));

		PartDefinition pelo18 = gokucabello.addOrReplaceChild("pelo18", CubeListBuilder.create(), PartPose.offset(-3.7478F, -22.7093F, 2.2F));

		PartDefinition cube_r30 = pelo18.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 33).addBox(0.372F, -2.0612F, -0.7672F, 1.8F, 1.2F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1605F, -6.6699F, -3.6F, 0.0F, 0.0F, 1.4399F));

		PartDefinition cube_r31 = pelo18.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(0, 33).addBox(-0.528F, -3.3612F, -1.2672F, 3.0F, 2.5F, 3.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -2.75F, -3.6F, 0.0F, 0.0F, -0.3491F));

		PartDefinition pelo19 = gokucabello.addOrReplaceChild("pelo19", CubeListBuilder.create().texOffs(0, 33).addBox(-3.328F, -10.0612F, -4.9672F, 1.8F, 2.5F, 2.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1108F, -18.3699F, 2.6F, 0.0F, 0.0F, 0.829F));

		PartDefinition pelo20 = gokucabello.addOrReplaceChild("pelo20", CubeListBuilder.create().texOffs(0, 33).addBox(-3.328F, -10.0612F, -4.9672F, 1.8F, 2.5F, 2.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0778F, -17.1692F, 4.5F, 0.0F, 0.0F, 0.6545F));

		PartDefinition pelo21 = gokucabello.addOrReplaceChild("pelo21", CubeListBuilder.create(), PartPose.offset(-3.0075F, -21.6339F, 5.1F));

		PartDefinition cube_r32 = pelo21.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(0, 33).addBox(0.972F, -3.0612F, -0.9672F, 2.2F, 2.5F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.9539F, -4.7318F, -3.6F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r33 = pelo21.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 33).addBox(-0.528F, -3.3612F, -1.0672F, 3.0F, 2.5F, 3.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -6.0F, -3.6F, 0.0F, 0.0F, 1.0036F));

		PartDefinition pelo22 = gokucabello.addOrReplaceChild("pelo22", CubeListBuilder.create(), PartPose.offset(4.3986F, -30.7784F, 3.0328F));

		PartDefinition cube_r34 = pelo22.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(0, 33).addBox(-1.828F, -1.9612F, 0.7328F, 2.3F, 1.9F, 2.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2619F, 0.0058F, -1.7828F, 0.0F, 0.0F, 1.7017F));

		PartDefinition cube_r35 = pelo22.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(0, 33).addBox(-2.028F, -1.4612F, 0.6328F, 3.8F, 3.3F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7543F, -0.081F, -2.0328F, 0.0F, 0.0F, -0.2618F));

		PartDefinition pelo23 = gokucabello.addOrReplaceChild("pelo23", CubeListBuilder.create(), PartPose.offset(4.1358F, -32.5665F, 2.8078F));

		PartDefinition cube_r36 = pelo23.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(0, 33).addBox(-1.728F, 0.0388F, 0.9328F, 2.0F, 1.8F, 1.9F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4792F, -0.0382F, -1.8078F, 0.0F, 0.0F, 1.3963F));

		PartDefinition cube_r37 = pelo23.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(0, 33).addBox(-2.028F, -1.4612F, 0.4328F, 3.4F, 3.3F, 2.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6139F, 0.2313F, -1.8078F, 0.0F, 0.0F, -0.7418F));

		PartDefinition pelo24 = gokucabello.addOrReplaceChild("pelo24", CubeListBuilder.create().texOffs(0, 33).addBox(-3.328F, -10.0612F, -4.8672F, 1.8F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0809F, -17.291F, 6.5F, 0.0F, 0.0F, 0.6545F));

		PartDefinition pelo25 = gokucabello.addOrReplaceChild("pelo25", CubeListBuilder.create().texOffs(0, 33).addBox(-3.928F, -9.6612F, -4.6672F, 3.6F, 3.8F, 4.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6F, -30.8F, 4.8F, 0.0F, 0.0F, -1.309F));

		PartDefinition pelo26 = gokucabello.addOrReplaceChild("pelo26", CubeListBuilder.create().texOffs(0, 33).addBox(-3.528F, -8.0612F, -3.5672F, 2.6F, 2.2F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9936F, -32.4544F, 4.8F, 0.0F, 0.0F, -1.4399F));

		PartDefinition pelo27 = gokucabello.addOrReplaceChild("pelo27", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.4809F, -16.1299F, 3.8426F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r38 = pelo27.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(0, 33).addBox(-0.828F, -3.2165F, -0.7877F, 1.8F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0873F, 0.0F, 0.0F));

		PartDefinition pelo28 = gokucabello.addOrReplaceChild("pelo28", CubeListBuilder.create(), PartPose.offsetAndRotation(4.9191F, -16.1299F, 3.8426F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r39 = pelo28.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(0, 33).addBox(-0.828F, -3.2266F, -0.7905F, 2.0F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0698F, 0.0F, 0.0F));

		PartDefinition pelo29 = gokucabello.addOrReplaceChild("pelo29", CubeListBuilder.create(), PartPose.offsetAndRotation(1.2191F, -16.1299F, 3.8426F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r40 = pelo29.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(0, 33).addBox(-0.7649F, -3.8083F, -0.789F, 2.5F, 3.1F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, -7.0F, -3.6F, 0.0896F, -0.0181F, -0.2612F));

		PartDefinition pelo30 = gokucabello.addOrReplaceChild("pelo30", CubeListBuilder.create(), PartPose.offsetAndRotation(3.5191F, -16.1299F, 3.8426F, -0.3665F, 0.0F, 0.0F));

		PartDefinition cube_r41 = pelo30.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(0, 33).addBox(-1.6746F, -3.7962F, -0.7845F, 2.6F, 3.1F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, -6.9F, -3.6F, 0.1309F, 0.0F, 0.2182F));

		PartDefinition pelo31 = gokucabello.addOrReplaceChild("pelo31", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.4809F, -18.3358F, 5.5859F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r42 = pelo31.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(0, 33).addBox(-0.8111F, -4.4377F, -0.7724F, 2.8F, 3.8F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, -7.1F, -3.6F, 0.0F, 0.0F, -0.2182F));

		PartDefinition pelo32 = gokucabello.addOrReplaceChild("pelo32", CubeListBuilder.create(), PartPose.offsetAndRotation(4.7191F, -18.3358F, 5.5859F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r43 = pelo32.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(0, 33).addBox(-2.1416F, -4.5383F, -0.7724F, 3.1F, 3.9F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9F, -7.0F, -3.6F, 0.0F, 0.0F, 0.1745F));

		PartDefinition pelo33 = gokucabello.addOrReplaceChild("pelo33", CubeListBuilder.create().texOffs(0, 33).addBox(-4.328F, -11.6499F, -4.5738F, 2.5F, 4.0F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7191F, -18.3358F, 5.5859F, -0.1484F, 0.0F, 0.0F));

		PartDefinition pelo34 = gokucabello.addOrReplaceChild("pelo34", CubeListBuilder.create(), PartPose.offsetAndRotation(4.7191F, -20.3771F, 4.3183F, -0.2356F, 0.0F, 0.0F));

		PartDefinition cube_r44 = pelo34.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(0, 33).addBox(-4.928F, -4.5439F, -1.0789F, 5.9F, 3.9F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, -6.8F, -3.4F, -0.0436F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		//super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
		float random = (float) Math.random();

		this.pelo1.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.04f)*0.02F);
		this.pelo2.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.04f)*0.02F);
		this.pelo3.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.04f)*-0.02F);
		//this.pelo4.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.04f)*-0.02F);
		//this.pelo5.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.04f)*-0.02F);

		this.pelo15.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.03f)*0.01F);
		this.pelo14.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.03f)*0.01F);
		this.pelo7.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.03f)*0.01F);
		this.pelo6.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.03f)*0.01F);
		this.pelo10.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.03f)*0.01F);

		this.pelo16.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.03f)*-0.01F);
		this.pelo17.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.03f)*-0.01F);
		this.pelo18.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.03f)*-0.01F);
		this.pelo21.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.03f)*-0.01F);
		this.pelo22.zRot = (float) (Math.cos((pEntity.tickCount+random)*0.03f)*-0.01F);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		gokucabello.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}