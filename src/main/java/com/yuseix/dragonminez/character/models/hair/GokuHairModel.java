package com.yuseix.dragonminez.character.models.hair;

import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
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
	private final ModelPart pelo7op1;
	private final ModelPart pelo8;
	private final ModelPart pelo8op1;
	private final ModelPart pelo8op2;
	private final ModelPart pelo9;
	private final ModelPart pelo9op1;
	private final ModelPart pelo9op2;
	private final ModelPart pelo10;
	private final ModelPart pelo10op1;
	private final ModelPart pelo10op2;
	private final ModelPart pelo11;
	private final ModelPart pelo11op1;
	private final ModelPart pelo11op2;
	private final ModelPart pelo12;
	private final ModelPart pelo12op1;
	private final ModelPart pelo13;
	private final ModelPart pelo14;
	private final ModelPart pelo15;
	private final ModelPart pelo16;
	private final ModelPart pelo17;
	private final ModelPart pelo18;
	private final ModelPart pelo19;
	private final ModelPart pelo20;
	private final ModelPart pelo21;
	private final ModelPart pelo21op3;
	private final ModelPart pelo21op4;
	private final ModelPart pelo22;
	private final ModelPart pelo22op2;
	private final ModelPart pelo22op5;
	private final ModelPart pelo23;
	private final ModelPart pelo23op1;
	private final ModelPart pelo23op2;
	private final ModelPart pelo24;
	private final ModelPart pelo24op1;
	private final ModelPart pelo24op2;
	private final ModelPart pelo25;
	private final ModelPart pelo25op1;
	private final ModelPart pelo25op2;
	private final ModelPart pelo26;
	private final ModelPart pelo26op1;
	private final ModelPart pelo26op2;
	private final ModelPart pelo27;
	private final ModelPart pelo27op1;
	private final ModelPart pelo27op2;
	private final ModelPart pelo28;
	private final ModelPart pelo28op1;
	private final ModelPart pelo29;
	private final ModelPart pelo30;
	private final ModelPart pelo30op1;

	public GokuHairModel(ModelPart root) {
        super(root);
        this.gokucabello = root.getChild("gokucabello");
		this.pelo1 = this.gokucabello.getChild("pelo1");
		this.pelo2 = this.gokucabello.getChild("pelo2");
		this.pelo3 = this.gokucabello.getChild("pelo3");
		this.pelo4 = this.gokucabello.getChild("pelo4");
		this.pelo5 = this.gokucabello.getChild("pelo5");
		this.pelo6 = this.gokucabello.getChild("pelo6");
		this.pelo7 = this.gokucabello.getChild("pelo7");
		this.pelo7op1 = this.pelo7.getChild("pelo7op1");
		this.pelo8 = this.gokucabello.getChild("pelo8");
		this.pelo8op1 = this.pelo8.getChild("pelo8op1");
		this.pelo8op2 = this.pelo8op1.getChild("pelo8op2");
		this.pelo9 = this.gokucabello.getChild("pelo9");
		this.pelo9op1 = this.pelo9.getChild("pelo9op1");
		this.pelo9op2 = this.pelo9op1.getChild("pelo9op2");
		this.pelo10 = this.gokucabello.getChild("pelo10");
		this.pelo10op1 = this.pelo10.getChild("pelo10op1");
		this.pelo10op2 = this.pelo10op1.getChild("pelo10op2");
		this.pelo11 = this.gokucabello.getChild("pelo11");
		this.pelo11op1 = this.pelo11.getChild("pelo11op1");
		this.pelo11op2 = this.pelo11op1.getChild("pelo11op2");
		this.pelo12 = this.gokucabello.getChild("pelo12");
		this.pelo12op1 = this.pelo12.getChild("pelo12op1");
		this.pelo13 = this.gokucabello.getChild("pelo13");
		this.pelo14 = this.gokucabello.getChild("pelo14");
		this.pelo15 = this.gokucabello.getChild("pelo15");
		this.pelo16 = this.gokucabello.getChild("pelo16");
		this.pelo17 = this.gokucabello.getChild("pelo17");
		this.pelo18 = this.gokucabello.getChild("pelo18");
		this.pelo19 = this.gokucabello.getChild("pelo19");
		this.pelo20 = this.gokucabello.getChild("pelo20");
		this.pelo21 = this.gokucabello.getChild("pelo21");
		this.pelo21op3 = this.pelo21.getChild("pelo21op3");
		this.pelo21op4 = this.pelo21op3.getChild("pelo21op4");
		this.pelo22 = this.gokucabello.getChild("pelo22");
		this.pelo22op2 = this.pelo22.getChild("pelo22op2");
		this.pelo22op5 = this.pelo22op2.getChild("pelo22op5");
		this.pelo23 = this.gokucabello.getChild("pelo23");
		this.pelo23op1 = this.pelo23.getChild("pelo23op1");
		this.pelo23op2 = this.pelo23op1.getChild("pelo23op2");
		this.pelo24 = this.gokucabello.getChild("pelo24");
		this.pelo24op1 = this.pelo24.getChild("pelo24op1");
		this.pelo24op2 = this.pelo24op1.getChild("pelo24op2");
		this.pelo25 = this.gokucabello.getChild("pelo25");
		this.pelo25op1 = this.pelo25.getChild("pelo25op1");
		this.pelo25op2 = this.pelo25op1.getChild("pelo25op2");
		this.pelo26 = this.gokucabello.getChild("pelo26");
		this.pelo26op1 = this.pelo26.getChild("pelo26op1");
		this.pelo26op2 = this.pelo26op1.getChild("pelo26op2");
		this.pelo27 = this.gokucabello.getChild("pelo27");
		this.pelo27op1 = this.pelo27.getChild("pelo27op1");
		this.pelo27op2 = this.pelo27op1.getChild("pelo27op2");
		this.pelo28 = this.gokucabello.getChild("pelo28");
		this.pelo28op1 = this.pelo28.getChild("pelo28op1");
		this.pelo29 = this.gokucabello.getChild("pelo29");
		this.pelo30 = this.gokucabello.getChild("pelo30");
		this.pelo30op1 = this.pelo30.getChild("pelo30op1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE,0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition gokucabello = partdefinition.addOrReplaceChild("gokucabello", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition pelo1 = gokucabello.addOrReplaceChild("pelo1", CubeListBuilder.create(), PartPose.offset(-2.7356F, -31.6382F, -4.8561F));

		PartDefinition cube_r1 = pelo1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 59).addBox(-1.05F, -3.0F, -1.25F, 1.75F, 2.0F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1356F, 3.7382F, 0.5561F, -0.0873F, 0.0F, -0.1309F));

		PartDefinition cube_r2 = pelo1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(42, 58).addBox(-1.3F, -3.7F, -1.0F, 2.1F, 3.2F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.35F, 2.25F, -0.1F, -0.3491F, 0.0F, 0.3054F));

		PartDefinition pelo2 = gokucabello.addOrReplaceChild("pelo2", CubeListBuilder.create(), PartPose.offset(-4.2789F, -30.9079F, -3.5479F));

		PartDefinition cube_r3 = pelo2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(60, 4).addBox(-1.1529F, -3.0994F, -0.5344F, 1.3F, 1.7F, 1.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2789F, 2.8579F, -1.0521F, -0.2618F, 0.0F, -0.0436F));

		PartDefinition cube_r4 = pelo2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(58, 44).addBox(-0.778F, -3.5612F, -1.4672F, 1.8F, 3.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9711F, 1.1579F, -0.0521F, -0.1289F, 0.0227F, 0.5221F));

		PartDefinition pelo3 = gokucabello.addOrReplaceChild("pelo3", CubeListBuilder.create(), PartPose.offset(0.8609F, -33.5884F, -5.7549F));

		PartDefinition cube_r5 = pelo3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(60, 0).addBox(-0.8F, -3.0F, -1.25F, 1.7F, 1.65F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.15F, 5.697F, 1.6596F, -0.0872F, 0.0038F, -0.0874F));

		PartDefinition cube_r6 = pelo3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(56, 28).addBox(-2.1F, -3.0F, -1.25F, 3.4F, 2.5F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.05F, 3.603F, 1.8404F, 0.0F, 0.1745F, 0.829F));

		PartDefinition pelo4 = gokucabello.addOrReplaceChild("pelo4", CubeListBuilder.create(), PartPose.offset(1.2506F, -32.9649F, -3.7711F));

		PartDefinition cube_r7 = pelo4.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(60, 0).addBox(-0.87F, -2.8201F, -1.7156F, 1.65F, 1.85F, 2.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0494F, 4.7649F, 0.0711F, -0.2262F, -0.0032F, 0.1343F));

		PartDefinition cube_r8 = pelo4.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(24, 58).addBox(-1.0F, -3.75F, -1.5F, 2.3F, 3.25F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0494F, 2.8649F, -0.2289F, -0.2618F, 0.0F, -0.7418F));

		PartDefinition pelo5 = gokucabello.addOrReplaceChild("pelo5", CubeListBuilder.create(), PartPose.offset(4.0367F, -31.2182F, -3.1713F));

		PartDefinition cube_r9 = pelo5.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(24, 53).addBox(-0.4681F, -3.1953F, -1.9793F, 1.4F, 1.5F, 2.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3867F, 3.5682F, -0.0287F, -0.1298F, 0.017F, 0.1298F));

		PartDefinition cube_r10 = pelo5.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(58, 39).addBox(-0.9943F, -3.7273F, -1.8862F, 2.05F, 2.4F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7443F, 2.0273F, -0.1138F, -0.1745F, 0.0F, -0.3491F));

		PartDefinition pelo6 = gokucabello.addOrReplaceChild("pelo6", CubeListBuilder.create(), PartPose.offset(-3.9777F, -25.9869F, -1.5172F));

		PartDefinition cube_r11 = pelo6.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 59).addBox(-0.528F, -3.0612F, -1.0672F, 1.8F, 2.5F, 2.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3277F, 1.2869F, 0.0172F, 0.0F, 0.0F, -1.0036F));

		PartDefinition pelo7 = gokucabello.addOrReplaceChild("pelo7", CubeListBuilder.create(), PartPose.offset(-3.3829F, -27.766F, -1.1922F));

		PartDefinition cube_r12 = pelo7.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(44, 28).addBox(-3.4515F, -2.64F, -1.6F, 3.4F, 2.5F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7605F, 2.0698F, -0.075F, 0.0F, 0.0F, 0.5672F));

		PartDefinition pelo7op1 = pelo7.addOrReplaceChild("pelo7op1", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition cube_r13 = pelo7op1.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(58, 39).addBox(-1.2621F, -1.5247F, -0.8672F, 2.0F, 2.2F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5171F, -0.234F, -0.4078F, 0.0F, 0.0F, 0.4363F));

		PartDefinition pelo8 = gokucabello.addOrReplaceChild("pelo8", CubeListBuilder.create(), PartPose.offset(-4.4F, -29.55F, 1.85F));

		PartDefinition cube_r14 = pelo8.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(44, 0).addBox(-1.428F, -2.6612F, -1.2672F, 3.6F, 3.8F, 4.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, -3.6F, 0.0F, 0.0F, -0.9599F));

		PartDefinition pelo8op1 = pelo8.addOrReplaceChild("pelo8op1", CubeListBuilder.create(), PartPose.offset(-1.5F, -1.5F, 0.0F));

		PartDefinition cube_r15 = pelo8op1.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(46, 15).addBox(-0.828F, -2.0612F, -0.8672F, 3.0F, 3.0F, 3.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -0.35F, -3.6F, 0.0F, 0.0F, 0.2182F));

		PartDefinition pelo8op2 = pelo8op1.addOrReplaceChild("pelo8op2", CubeListBuilder.create(), PartPose.offset(-1.5F, -1.25F, 0.0F));

		PartDefinition cube_r16 = pelo8op2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(50, 58).addBox(-0.328F, -1.9612F, -0.6672F, 2.3F, 1.25F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2F, 0.8F, -3.6F, 0.0F, 0.0F, -1.6581F));

		PartDefinition pelo9 = gokucabello.addOrReplaceChild("pelo9", CubeListBuilder.create(), PartPose.offset(1.6F, -31.6F, 2.1F));

		PartDefinition cube_r17 = pelo9.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 11).addBox(-1.528F, -2.6612F, -1.8672F, 5.3F, 4.5F, 6.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8F, -0.45F, -3.6F, 0.0F, 0.0F, -0.6109F));

		PartDefinition pelo9op1 = pelo9.addOrReplaceChild("pelo9op1", CubeListBuilder.create(), PartPose.offset(-2.0F, -1.5F, 0.0F));

		PartDefinition cube_r18 = pelo9op1.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(22, 23).addBox(-1.928F, -2.2612F, -1.3672F, 5.2F, 4.2F, 5.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6599F, -0.7646F, -3.6F, 0.0F, 0.0F, -0.9163F));

		PartDefinition pelo9op2 = pelo9op1.addOrReplaceChild("pelo9op2", CubeListBuilder.create(), PartPose.offset(-4.25F, -2.75F, 0.0F));

		PartDefinition cube_r19 = pelo9op2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(42, 33).addBox(-1.028F, -2.3612F, -1.0672F, 3.5F, 3.2F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9149F, 1.0334F, -3.6F, 0.0F, 0.0F, -1.2217F));

		PartDefinition pelo10 = gokucabello.addOrReplaceChild("pelo10", CubeListBuilder.create(), PartPose.offset(2.2576F, -32.1392F, -0.2172F));

		PartDefinition cube_r20 = pelo10.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(22, 33).addBox(-1.628F, -2.6612F, -1.6672F, 4.1F, 4.5F, 5.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0766F, 0.5223F, -1.2828F, 0.0F, 0.0F, -0.2618F));

		PartDefinition pelo10op1 = pelo10.addOrReplaceChild("pelo10op1", CubeListBuilder.create(), PartPose.offset(1.0F, -1.0F, 0.0F));

		PartDefinition cube_r21 = pelo10op1.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 43).addBox(0.122F, -0.2112F, -1.1672F, 3.0F, 3.8F, 4.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0081F, -0.87F, -1.2828F, 0.0F, 0.0F, -0.1309F));

		PartDefinition pelo10op2 = pelo10op1.addOrReplaceChild("pelo10op2", CubeListBuilder.create(), PartPose.offset(2.0F, 1.25F, 0.0F));

		PartDefinition cube_r22 = pelo10op2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(30, 50).addBox(-1.328F, -0.5612F, -0.4672F, 1.95F, 3.7F, 3.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2715F, -1.8445F, -1.2828F, 0.0F, 0.0F, 0.2182F));

		PartDefinition pelo11 = gokucabello.addOrReplaceChild("pelo11", CubeListBuilder.create(), PartPose.offset(3.2096F, -30.4223F, -0.5672F));

		PartDefinition cube_r23 = pelo11.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(16, 44).addBox(-1.628F, -2.8612F, -1.2672F, 2.7F, 4.7F, 4.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2089F, 0.5431F, -0.9328F, 0.0F, 0.0F, 0.1309F));

		PartDefinition pelo11op1 = pelo11.addOrReplaceChild("pelo11op1", CubeListBuilder.create(), PartPose.offset(2.3283F, -0.0017F, 0.05F));

		PartDefinition cube_r24 = pelo11op1.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(12, 53).addBox(-1.428F, -1.2112F, -0.7672F, 2.2F, 3.3F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4693F, -0.6015F, -0.9828F, 0.0F, 0.0F, 0.2182F));

		PartDefinition pelo11op2 = pelo11op1.addOrReplaceChild("pelo11op2", CubeListBuilder.create(), PartPose.offset(1.2252F, 0.1375F, -0.05F));

		PartDefinition cube_r25 = pelo11op2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(32, 58).addBox(-1.228F, 0.3388F, -0.5672F, 2.1F, 2.25F, 2.9F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4467F, -1.6561F, -0.9328F, 0.0F, 0.0F, 0.3054F));

		PartDefinition pelo12 = gokucabello.addOrReplaceChild("pelo12", CubeListBuilder.create(), PartPose.offset(3.2995F, -27.5123F, -0.7922F));

		PartDefinition cube_r26 = pelo12.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(44, 28).addBox(-0.528F, -3.3612F, -1.2672F, 3.2F, 2.7F, 3.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0473F, 2.053F, -0.6078F, 0.0F, 0.0F, -0.3491F));

		PartDefinition pelo12op1 = pelo12.addOrReplaceChild("pelo12op1", CubeListBuilder.create(), PartPose.offset(1.5F, -0.5F, 0.0F));

		PartDefinition cube_r27 = pelo12op1.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(60, 0).addBox(0.372F, -2.5612F, -0.7672F, 1.8F, 1.7F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3868F, -1.3669F, -0.6078F, 0.0F, 0.0F, 1.4399F));

		PartDefinition pelo13 = gokucabello.addOrReplaceChild("pelo13", CubeListBuilder.create(), PartPose.offset(3.8127F, -24.039F, -0.8672F));

		PartDefinition cube_r28 = pelo13.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(58, 15).addBox(-0.828F, -2.8112F, -1.4672F, 2.0F, 2.25F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.922F, -1.1888F, -0.1328F, 0.0F, 0.0F, 0.9163F));

		PartDefinition pelo14 = gokucabello.addOrReplaceChild("pelo14", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.9089F, -25.6864F, 3.9301F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r29 = pelo14.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 59).addBox(-0.828F, -3.2165F, -0.7877F, 1.8F, 2.5F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.072F, 2.0102F, -0.4137F, 0.0873F, 0.0F, 0.0F));

		PartDefinition pelo15 = gokucabello.addOrReplaceChild("pelo15", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.2133F, -26.0111F, 4.0233F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r30 = pelo15.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(24, 58).addBox(-0.7649F, -3.8083F, -0.789F, 2.5F, 3.1F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1324F, 2.3471F, -0.3902F, 0.0896F, -0.0181F, -0.2612F));

		PartDefinition pelo16 = gokucabello.addOrReplaceChild("pelo16", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5486F, -25.9622F, 3.9411F, -0.3665F, 0.0F, 0.0F));

		PartDefinition cube_r31 = pelo16.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(24, 58).addBox(-1.6746F, -3.7962F, -0.7845F, 2.6F, 3.1F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3705F, 2.3146F, -0.1684F, 0.1309F, 0.0F, 0.2182F));

		PartDefinition pelo17 = gokucabello.addOrReplaceChild("pelo17", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.1551F, -28.3326F, 4.0048F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r32 = pelo17.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(42, 51).addBox(-0.8111F, -4.4377F, -0.7724F, 2.8F, 3.8F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0257F, 2.605F, -0.7276F, 0.0F, 0.0F, -0.2182F));

		PartDefinition pelo18 = gokucabello.addOrReplaceChild("pelo18", CubeListBuilder.create(), PartPose.offsetAndRotation(1.686F, -28.2962F, 3.8739F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r33 = pelo18.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 52).addBox(-2.1416F, -4.5383F, -0.7724F, 3.1F, 3.9F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1332F, 2.6517F, -0.6026F, 0.0F, 0.0F, 0.1745F));

		PartDefinition pelo19 = gokucabello.addOrReplaceChild("pelo19", CubeListBuilder.create().texOffs(54, 51).addBox(-1.25F, -2.0F, -1.475F, 2.5F, 4.0F, 2.95F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3589F, -28.3377F, 3.9474F, -0.1484F, 0.0F, 0.0F));

		PartDefinition pelo20 = gokucabello.addOrReplaceChild("pelo20", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.0589F, -30.4831F, 3.682F, -0.2356F, 0.0F, 0.0F));

		PartDefinition cube_r34 = pelo20.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(0, 35).addBox(-4.928F, -5.1439F, -1.0789F, 6.5F, 4.5F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.678F, 2.8782F, -0.4221F, -0.0436F, 0.0F, 0.0F));

		PartDefinition pelo21 = gokucabello.addOrReplaceChild("pelo21", CubeListBuilder.create(), PartPose.offset(1.6F, -31.6F, 2.1F));

		PartDefinition cube_r35 = pelo21.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(22, 0).addBox(-1.528F, -2.6612F, -1.6172F, 5.3F, 4.5F, 6.35F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8F, -0.45F, -3.6F, 0.0F, 0.0F, -0.7418F));

		PartDefinition pelo21op3 = pelo21.addOrReplaceChild("pelo21op3", CubeListBuilder.create(), PartPose.offset(-2.0F, -1.5F, 0.0F));

		PartDefinition cube_r36 = pelo21op3.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(24, 11).addBox(-1.928F, -2.2612F, -1.1172F, 5.2F, 4.2F, 5.55F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6599F, -0.7646F, -3.6F, 0.0F, 0.0F, -1.0036F));

		PartDefinition pelo21op4 = pelo21op3.addOrReplaceChild("pelo21op4", CubeListBuilder.create(), PartPose.offset(-5.25F, -0.75F, -2.0F));

		PartDefinition cube_r37 = pelo21op4.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(46, 8).addBox(-1.028F, -2.3612F, -0.8172F, 3.0F, 2.45F, 4.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2896F, -0.9209F, -1.6F, 0.0F, 0.0F, -1.4399F));

		PartDefinition pelo22 = gokucabello.addOrReplaceChild("pelo22", CubeListBuilder.create(), PartPose.offset(-3.4F, -32.6F, 4.1F));

		PartDefinition cube_r38 = pelo22.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(0, 0).addBox(-1.528F, -2.6612F, -1.6172F, 5.3F, 5.5F, 6.35F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0192F, 1.0558F, -3.6F, 0.0F, 0.0F, -0.7418F));

		PartDefinition pelo22op2 = pelo22.addOrReplaceChild("pelo22op2", CubeListBuilder.create(), PartPose.offset(0.7808F, 0.0058F, 0.0F));

		PartDefinition cube_r39 = pelo22op2.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(24, 11).addBox(-1.928F, -2.2612F, -1.1172F, 5.2F, 4.2F, 5.55F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6599F, -0.7646F, -3.6F, 0.0F, 0.0F, -1.0036F));

		PartDefinition pelo22op5 = pelo22op2.addOrReplaceChild("pelo22op5", CubeListBuilder.create(), PartPose.offset(-4.25F, -1.75F, -2.0F));

		PartDefinition cube_r40 = pelo22op5.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(44, 21).addBox(-1.028F, -2.3612F, -0.8172F, 3.0F, 2.2F, 4.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5957F, 0.4598F, -1.6F, 0.0F, 0.0F, -1.4399F));

		PartDefinition pelo23 = gokucabello.addOrReplaceChild("pelo23", CubeListBuilder.create(), PartPose.offset(1.2576F, -31.1392F, 1.7828F));

		PartDefinition cube_r41 = pelo23.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(0, 23).addBox(-2.628F, -3.6612F, -1.6672F, 5.1F, 5.5F, 6.05F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.304F, 0.2162F, -1.2828F, 0.0F, 0.0F, -0.2618F));

		PartDefinition pelo23op1 = pelo23.addOrReplaceChild("pelo23op1", CubeListBuilder.create(), PartPose.offset(2.3807F, -1.3061F, 0.0F));

		PartDefinition cube_r42 = pelo23op1.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(42, 41).addBox(0.122F, -0.9612F, -1.1672F, 3.0F, 4.55F, 4.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0081F, -0.87F, -1.2828F, 0.0F, 0.0F, -0.1309F));

		PartDefinition pelo23op2 = pelo23op1.addOrReplaceChild("pelo23op2", CubeListBuilder.create(), PartPose.offset(2.0F, 1.25F, 0.0F));

		PartDefinition cube_r43 = pelo23op2.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(58, 33).addBox(-1.328F, -1.0612F, -0.4672F, 1.7F, 3.2F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2715F, -1.8445F, -1.2828F, 0.0F, 0.0F, 0.0873F));

		PartDefinition pelo24 = gokucabello.addOrReplaceChild("pelo24", CubeListBuilder.create(), PartPose.offset(-3.9F, -29.3F, 5.1F));

		PartDefinition cube_r44 = pelo24.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(44, 0).addBox(-1.428F, -2.6612F, -1.2672F, 3.6F, 3.8F, 4.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, -3.6F, 0.0F, 0.0F, -0.9599F));

		PartDefinition pelo24op1 = pelo24.addOrReplaceChild("pelo24op1", CubeListBuilder.create(), PartPose.offset(-1.5F, -1.5F, 0.0F));

		PartDefinition cube_r45 = pelo24op1.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(46, 15).addBox(-0.828F, -2.0612F, -0.8672F, 3.0F, 3.0F, 3.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -0.35F, -3.6F, 0.0F, 0.0F, 0.2182F));

		PartDefinition pelo24op2 = pelo24op1.addOrReplaceChild("pelo24op2", CubeListBuilder.create(), PartPose.offset(-1.5F, -1.25F, 0.0F));

		PartDefinition cube_r46 = pelo24op2.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(50, 58).addBox(-0.328F, -1.9612F, -0.6672F, 2.3F, 1.25F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2F, 0.8F, -3.6F, 0.0F, 0.0F, -1.6581F));

		PartDefinition pelo25 = gokucabello.addOrReplaceChild("pelo25", CubeListBuilder.create(), PartPose.offset(-3.5602F, -26.5413F, 1.3328F));

		PartDefinition cube_r47 = pelo25.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(44, 0).addBox(0.2103F, -3.8083F, -1.2672F, 3.6F, 3.8F, 4.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0898F, 2.7413F, -0.8328F, 0.0F, 0.0F, -0.7854F));

		PartDefinition pelo25op1 = pelo25.addOrReplaceChild("pelo25op1", CubeListBuilder.create(), PartPose.offset(-1.0898F, -0.5087F, 2.7672F));

		PartDefinition cube_r48 = pelo25op1.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(46, 15).addBox(-2.0208F, -2.8211F, -0.8672F, 3.0F, 3.0F, 3.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 0.65F, -3.6F, 0.0F, 0.0F, 0.48F));

		PartDefinition pelo25op2 = pelo25op1.addOrReplaceChild("pelo25op2", CubeListBuilder.create(), PartPose.offset(-1.5F, -1.25F, 0.0F));

		PartDefinition cube_r49 = pelo25op2.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(50, 58).addBox(-0.328F, -1.9612F, -0.6672F, 2.3F, 1.25F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.45F, 0.8F, -3.6F, 0.0F, 0.0F, -1.309F));

		PartDefinition pelo26 = gokucabello.addOrReplaceChild("pelo26", CubeListBuilder.create(), PartPose.offset(2.9596F, -31.4223F, 2.4328F));

		PartDefinition cube_r50 = pelo26.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(16, 44).addBox(-1.628F, -2.8612F, -1.2672F, 2.7F, 4.7F, 4.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2089F, 0.5431F, -0.9328F, 0.0F, 0.0F, 0.1309F));

		PartDefinition pelo26op1 = pelo26.addOrReplaceChild("pelo26op1", CubeListBuilder.create(), PartPose.offset(2.3283F, -0.0017F, 0.05F));

		PartDefinition cube_r51 = pelo26op1.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(12, 53).addBox(-1.428F, -1.2112F, -0.7672F, 2.2F, 3.3F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4693F, -0.6015F, -0.9828F, 0.0F, 0.0F, 0.2182F));

		PartDefinition pelo26op2 = pelo26op1.addOrReplaceChild("pelo26op2", CubeListBuilder.create(), PartPose.offset(1.2252F, 0.1375F, -0.05F));

		PartDefinition cube_r52 = pelo26op2.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(32, 58).addBox(-1.228F, 0.3388F, -0.4672F, 2.1F, 2.25F, 2.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4467F, -1.6561F, -0.9328F, 0.0F, 0.0F, 0.3054F));

		PartDefinition pelo27 = gokucabello.addOrReplaceChild("pelo27", CubeListBuilder.create(), PartPose.offset(2.9596F, -27.4223F, 2.4328F));

		PartDefinition cube_r53 = pelo27.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(16, 44).addBox(-1.628F, -2.8612F, -1.2672F, 2.7F, 4.7F, 4.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2089F, 0.5431F, -0.9328F, 0.0F, 0.0F, 0.1309F));

		PartDefinition pelo27op1 = pelo27.addOrReplaceChild("pelo27op1", CubeListBuilder.create(), PartPose.offset(2.3283F, -0.0017F, 0.05F));

		PartDefinition cube_r54 = pelo27op1.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(12, 53).addBox(-1.428F, -1.2112F, -0.7672F, 2.2F, 3.3F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4693F, -0.6015F, -0.9828F, 0.0F, 0.0F, 0.2182F));

		PartDefinition pelo27op2 = pelo27op1.addOrReplaceChild("pelo27op2", CubeListBuilder.create(), PartPose.offset(1.2252F, 0.1375F, -0.05F));

		PartDefinition cube_r55 = pelo27op2.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(32, 58).addBox(-1.228F, 0.3388F, -0.4672F, 2.1F, 2.25F, 2.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4467F, -1.6561F, -0.9328F, 0.0F, 0.0F, 0.3054F));

		PartDefinition pelo28 = gokucabello.addOrReplaceChild("pelo28", CubeListBuilder.create(), PartPose.offset(-3.6329F, -26.016F, 2.8078F));

		PartDefinition cube_r56 = pelo28.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(30, 44).addBox(-0.528F, -4.0612F, -1.2672F, 3.4F, 3.5F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5671F, 1.216F, -0.4078F, 0.0F, 0.0F, 0.4363F));

		PartDefinition pelo28op1 = pelo28.addOrReplaceChild("pelo28op1", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.0F, 0.0F));

		PartDefinition cube_r57 = pelo28op1.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(58, 39).addBox(-0.2631F, -1.5683F, -0.8672F, 2.0F, 2.2F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5171F, -0.734F, -0.4078F, 0.0F, 0.0F, 0.0436F));

		PartDefinition pelo29 = gokucabello.addOrReplaceChild("pelo29", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0911F, -25.6864F, 3.9301F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r58 = pelo29.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(0, 59).addBox(-0.828F, -3.2165F, -0.7877F, 1.8F, 2.5F, 2.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.072F, 2.0102F, -0.4137F, 0.0873F, 0.0F, 0.0F));

		PartDefinition pelo30 = gokucabello.addOrReplaceChild("pelo30", CubeListBuilder.create(), PartPose.offset(3.2995F, -25.5123F, 2.2078F));

		PartDefinition cube_r59 = pelo30.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(44, 28).addBox(-0.528F, -3.3612F, -1.2672F, 3.2F, 2.7F, 3.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0473F, 2.053F, -0.6078F, 0.0F, 0.0F, -0.3491F));

		PartDefinition pelo30op1 = pelo30.addOrReplaceChild("pelo30op1", CubeListBuilder.create(), PartPose.offset(1.5F, -0.5F, 0.0F));

		PartDefinition cube_r60 = pelo30op1.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(60, 0).addBox(0.372F, -2.5612F, -0.7672F, 1.8F, 1.7F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3868F, -1.3669F, -0.6078F, 0.0F, 0.0F, 1.4399F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		//super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
		float random = (float) Math.random();


		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

			var auraOn = cap.isAuraOn();

			if(auraOn){
				var cargaVelocidad = 0.4f;
				this.pelo7.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.03F);
				this.pelo8.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.03F);
				this.pelo9.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.03F);
				this.pelo10.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.03F);
				this.pelo25.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.03F);
				this.pelo28.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*0.03F);

				this.pelo22.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*-0.03F);
				this.pelo23.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*-0.03F);
				this.pelo27.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*-0.03F);
				this.pelo11.zRot = (float) (Math.cos((pEntity.tickCount+random)*cargaVelocidad)*-0.03F);

			} else {
				var velocidad = 0.04f;

				this.pelo7.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.03F);
				this.pelo8.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.03F);
				this.pelo9.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.03F);
				this.pelo10.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.03F);
				this.pelo25.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.03F);
				this.pelo28.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*0.03F);

				this.pelo22.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.03F);
				this.pelo23.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.03F);
				this.pelo27.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.03F);
				this.pelo11.zRot = (float) (Math.cos((pEntity.tickCount+random)*velocidad)*-0.03F);

			}

		});
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		gokucabello.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}