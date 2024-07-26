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

public class GokuHairModel extends HumanoidModel<AbstractClientPlayer> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DragonMineZ.MOD_ID, "hairs"), "gokuhair");
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
	private final ModelPart pelo35;
	private final ModelPart pelo36;
	private final ModelPart pelo37;
	private final ModelPart pelo38;
	private final ModelPart pelo39;
	private final ModelPart pelo40;
	private final ModelPart pelo41;
	private final ModelPart pelo42;
	private final ModelPart pelo43;
	private final ModelPart pelo44;
	private final ModelPart pelo45;
	private final ModelPart pelo46;
	private final ModelPart pelo47;
	private final ModelPart pelo48;
	private final ModelPart pelo49;
	private final ModelPart pelo50;
	private final ModelPart pelo51;
	private final ModelPart pelo52;
	private final ModelPart pelo53;
	private final ModelPart pelo54;
	private final ModelPart pelo55;
	private final ModelPart pelo56;
	private final ModelPart pelo57;
	private final ModelPart pelo58;
	private final ModelPart pelo59;
	private final ModelPart pelo60;
	private final ModelPart pelo61;
	private final ModelPart pelo62;

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
		this.pelo35 = gokucabello.getChild("pelo35");
		this.pelo36 = gokucabello.getChild("pelo36");
		this.pelo37 = gokucabello.getChild("pelo37");
		this.pelo38 = gokucabello.getChild("pelo38");
		this.pelo39 = gokucabello.getChild("pelo39");
		this.pelo40 = gokucabello.getChild("pelo40");
		this.pelo41 = gokucabello.getChild("pelo41");
		this.pelo42 = gokucabello.getChild("pelo42");
		this.pelo43 = gokucabello.getChild("pelo43");
		this.pelo44 = gokucabello.getChild("pelo44");
		this.pelo45 = gokucabello.getChild("pelo45");
		this.pelo46 = gokucabello.getChild("pelo46");
		this.pelo47 = gokucabello.getChild("pelo47");
		this.pelo48 = gokucabello.getChild("pelo48");
		this.pelo49 = gokucabello.getChild("pelo49");
		this.pelo50 = gokucabello.getChild("pelo50");
		this.pelo51 = gokucabello.getChild("pelo51");
		this.pelo52 = gokucabello.getChild("pelo52");
		this.pelo53 = gokucabello.getChild("pelo53");
		this.pelo54 = gokucabello.getChild("pelo54");
		this.pelo55 = gokucabello.getChild("pelo55");
		this.pelo56 = gokucabello.getChild("pelo56");
		this.pelo57 = gokucabello.getChild("pelo57");
		this.pelo58 = gokucabello.getChild("pelo58");
		this.pelo59 = gokucabello.getChild("pelo59");
		this.pelo60 = gokucabello.getChild("pelo60");
		this.pelo61 = gokucabello.getChild("pelo61");
		this.pelo62 = gokucabello.getChild("pelo62");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE,0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition gokucabello = partdefinition.addOrReplaceChild("gokucabello", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition pelo1 = gokucabello.addOrReplaceChild("pelo1", CubeListBuilder.create().texOffs(-1, 33).addBox(-3.8F, -10.0F, -4.6F, 2.0F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, -21.8F, -3.6F, -0.2104F, 0.3108F, 0.3259F));

		PartDefinition pelo2 = gokucabello.addOrReplaceChild("pelo2", CubeListBuilder.create().texOffs(0, 33).addBox(-3.3F, -10.0F, -4.6F, 1.5F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -20.0F, -4.3F, -0.2834F, 0.2464F, 0.0619F));

		PartDefinition pelo3 = gokucabello.addOrReplaceChild("pelo3", CubeListBuilder.create().texOffs(0, 33).addBox(-3.028F, -10.0612F, -4.5672F, 1.3F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, -22.6F, 0.0F, 0.0864F, 0.3633F, 0.6919F));

		PartDefinition pelo4 = gokucabello.addOrReplaceChild("pelo4", CubeListBuilder.create().texOffs(0, 33).addBox(-3.6529F, -10.0994F, -4.1344F, 1.3F, 2.1F, 1.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2F, -20.2F, -3.1F, -0.2104F, 0.3108F, 0.3259F));

		PartDefinition pelo5 = gokucabello.addOrReplaceChild("pelo5", CubeListBuilder.create().texOffs(0, 33).addBox(-1.3F, -1.25F, -1.0F, 2.6F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.45F, -31.494F, -3.9193F, -0.37F, -0.051F, -0.7659F));

		PartDefinition pelo6 = gokucabello.addOrReplaceChild("pelo6", CubeListBuilder.create().texOffs(0, 33).addBox(-3.3F, -10.0F, -4.6F, 1.9F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5F, -22.2F, -4.4F, -0.3625F, 0.0913F, -0.3995F));

		PartDefinition pelo7 = gokucabello.addOrReplaceChild("pelo7", CubeListBuilder.create().texOffs(0, 33).addBox(-3.5F, -10.0F, -4.6F, 2.3F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.4F, -26.6F, -3.6F, -0.37F, -0.051F, -0.7659F));

		PartDefinition pelo8 = gokucabello.addOrReplaceChild("pelo8", CubeListBuilder.create().texOffs(0, 33).addBox(-3.37F, -9.8201F, -5.0656F, 1.9F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, -22.8F, -3.6F, -0.3687F, 0.06F, -0.4812F));

		PartDefinition pelo9 = gokucabello.addOrReplaceChild("pelo9", CubeListBuilder.create().texOffs(0, 33).addBox(-3.4943F, -10.2273F, -4.9862F, 1.8F, 1.9F, 2.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.4F, -24.4F, -3.0F, -0.3723F, 0.0284F, -0.5627F));

		PartDefinition pelo10 = gokucabello.addOrReplaceChild("pelo10", CubeListBuilder.create().texOffs(0, 33).addBox(-3.4681F, -10.1953F, -5.0793F, 1.8F, 1.5F, 1.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.3F, -21.4F, -3.0F, -0.3585F, 0.1066F, -0.3585F));

		PartDefinition pelo11 = gokucabello.addOrReplaceChild("pelo11", CubeListBuilder.create().texOffs(0, 33).addBox(-3.4943F, -10.2273F, -4.9862F, 1.8F, 1.9F, 2.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.4F, -24.4F, -3.0F, -0.3723F, 0.0284F, -0.5627F));

		PartDefinition pelo12 = gokucabello.addOrReplaceChild("pelo12", CubeListBuilder.create().texOffs(0, 33).addBox(-3.4681F, -10.1953F, -5.0793F, 1.8F, 1.5F, 1.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.3F, -21.4F, -3.0F, -0.3585F, 0.1066F, -0.3585F));

		PartDefinition pelo13 = gokucabello.addOrReplaceChild("pelo13", CubeListBuilder.create().texOffs(0, 33).addBox(-3.028F, -10.0612F, -4.5672F, 1.3F, 2.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, -22.6F, 0.0F, 0.0864F, 0.3633F, 0.6919F));

		PartDefinition pelo14 = gokucabello.addOrReplaceChild("pelo14", CubeListBuilder.create().texOffs(0, 33).addBox(-3.6529F, -10.0994F, -4.1344F, 1.3F, 2.1F, 1.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2F, -20.2F, -3.1F, -0.2104F, 0.3108F, 0.3259F));

		PartDefinition pelo19 = gokucabello.addOrReplaceChild("pelo19", CubeListBuilder.create().texOffs(0, 33).addBox(-3.028F, -10.0612F, -4.6672F, 1.8F, 2.5F, 2.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9F, -17.7F, 2.1F, 0.0F, 0.0F, 0.4363F));

		PartDefinition pelo20 = gokucabello.addOrReplaceChild("pelo20", CubeListBuilder.create().texOffs(0, 33).addBox(-3.028F, -10.0612F, -4.8672F, 3.4F, 2.5F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, -25.8F, 2.1F, 0.0F, 0.0F, -1.1345F));

		PartDefinition pelo21 = gokucabello.addOrReplaceChild("pelo21", CubeListBuilder.create(), PartPose.offsetAndRotation(2.3F, -26.6F, 2.1F, 0.0F, 0.0F, -1.0908F));

		PartDefinition cube_r1 = pelo21.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 33).addBox(-0.2631F, -1.5683F, -0.8672F, 2.0F, 2.2F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0F, 0.0F, -0.1309F));

		PartDefinition pelo22 = gokucabello.addOrReplaceChild("pelo22", CubeListBuilder.create().texOffs(0, 33).addBox(-3.028F, -10.0612F, -4.6672F, 1.8F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, -19.6F, 4.2F, 0.0F, 0.0F, -0.48F));

		PartDefinition pelo23 = gokucabello.addOrReplaceChild("pelo23", CubeListBuilder.create().texOffs(0, 33).addBox(-3.128F, -10.0612F, -4.4672F, 1.9F, 2.7F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, -19.9F, 6.4F, 0.0F, 0.0F, -0.5672F));

		PartDefinition pelo24 = gokucabello.addOrReplaceChild("pelo24", CubeListBuilder.create().texOffs(0, 33).addBox(-3.028F, -10.0612F, -4.6672F, 1.8F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, -19.9F, 6.4F, 0.0F, 0.0F, -0.5672F));

		PartDefinition pelo25 = gokucabello.addOrReplaceChild("pelo25", CubeListBuilder.create().texOffs(0, 33).addBox(-3.028F, -10.0612F, -4.8672F, 3.4F, 2.5F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, -25.2F, 4.1F, 0.0F, 0.0F, -1.1345F));

		PartDefinition pelo26 = gokucabello.addOrReplaceChild("pelo26", CubeListBuilder.create().texOffs(0, 33).addBox(-2.728F, -9.7612F, -4.6672F, 3.1F, 2.2F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, -25.2F, 5.7F, 0.0F, 0.0F, -1.1345F));

		PartDefinition pelo27 = gokucabello.addOrReplaceChild("pelo27", CubeListBuilder.create(), PartPose.offsetAndRotation(2.3F, -26.6F, 4.1F, 0.0F, 0.0F, -1.0908F));

		PartDefinition cube_r2 = pelo27.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 33).addBox(-0.2631F, -1.4683F, 0.0328F, 2.0F, 2.1F, 1.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0F, 0.0F, 0.2618F));

		PartDefinition pelo28 = gokucabello.addOrReplaceChild("pelo28", CubeListBuilder.create(), PartPose.offsetAndRotation(2.3F, -26.6F, 5.6F, 0.0F, 0.0F, -1.0908F));

		PartDefinition cube_r3 = pelo28.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 33).addBox(-0.2631F, -1.4683F, 0.4328F, 2.0F, 2.1F, 1.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0F, 0.0F, 0.2618F));

		PartDefinition pelo29 = gokucabello.addOrReplaceChild("pelo29", CubeListBuilder.create().texOffs(0, 33).addBox(-3.928F, -9.6612F, -4.8672F, 3.6F, 3.8F, 4.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6F, -30.8F, 2.1F, 0.0F, 0.0F, -1.309F));

		PartDefinition pelo30 = gokucabello.addOrReplaceChild("pelo30", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, -31.2F, 2.1F, 0.0F, 0.0F, -1.309F));

		PartDefinition cube_r4 = pelo30.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 33).addBox(-0.828F, -2.0612F, -0.8672F, 3.0F, 3.0F, 3.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0F, 0.0F, -0.1745F));

		PartDefinition pelo31 = gokucabello.addOrReplaceChild("pelo31", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, -32.3F, 2.1F, 0.0F, 0.0F, -1.4399F));

		PartDefinition cube_r5 = pelo31.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 33).addBox(-0.328F, -1.9612F, -0.6672F, 2.3F, 4.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0F, 0.0F, -0.3054F));

		PartDefinition pelo32 = gokucabello.addOrReplaceChild("pelo32", CubeListBuilder.create().texOffs(0, 33).addBox(-4.028F, -9.6612F, -5.1672F, 5.3F, 4.5F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6F, -27.6F, 2.1F, 0.0F, 0.0F, -0.6545F));

		PartDefinition pelo33 = gokucabello.addOrReplaceChild("pelo33", CubeListBuilder.create(), PartPose.offsetAndRotation(3.3401F, -31.6646F, 2.1F, 0.0F, 0.0F, -0.9163F));

		PartDefinition cube_r6 = pelo33.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 33).addBox(-1.928F, -2.2612F, -1.0672F, 5.2F, 4.2F, 5.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0F, 0.0F, -0.1309F));

		PartDefinition pelo34 = gokucabello.addOrReplaceChild("pelo34", CubeListBuilder.create(), PartPose.offsetAndRotation(2.4649F, -34.5166F, 2.1F, 0.0F, 0.0F, -1.1781F));

		PartDefinition cube_r7 = pelo34.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 33).addBox(-1.028F, -2.3612F, -0.7672F, 3.5F, 3.2F, 4.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0F, 0.0F, -0.2182F));

		PartDefinition pelo35 = gokucabello.addOrReplaceChild("pelo35", CubeListBuilder.create().texOffs(0, 33).addBox(-4.128F, -9.6612F, -4.9672F, 4.1F, 4.5F, 5.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5691F, -26.6169F, 2.1F, 0.0F, 0.0F, 1.0908F));

		PartDefinition pelo36 = gokucabello.addOrReplaceChild("pelo36", CubeListBuilder.create().texOffs(0, 33).addBox(-3.128F, -7.4612F, -4.5672F, 3.0F, 2.3F, 4.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7505F, -30.2092F, 2.1F, 0.0F, 0.0F, 1.4399F));

		PartDefinition pelo37 = gokucabello.addOrReplaceChild("pelo37", CubeListBuilder.create().texOffs(0, 33).addBox(-2.828F, -6.5612F, -4.0672F, 2.2F, 1.4F, 3.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2709F, -31.6837F, 2.1F, 0.0F, 0.0F, 1.6581F));

		PartDefinition pelo38 = gokucabello.addOrReplaceChild("pelo38", CubeListBuilder.create().texOffs(0, 33).addBox(-4.128F, -8.7612F, -4.8672F, 2.7F, 3.6F, 4.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3315F, -27.8792F, 2.1F, 0.0F, 0.0F, 1.6144F));

		PartDefinition pelo39 = gokucabello.addOrReplaceChild("pelo39", CubeListBuilder.create().texOffs(0, 33).addBox(-3.928F, -7.4612F, -4.3672F, 2.2F, 2.3F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6427F, -28.9255F, 2.1F, 0.0F, 0.0F, 1.789F));

		PartDefinition pelo40 = gokucabello.addOrReplaceChild("pelo40", CubeListBuilder.create().texOffs(0, 33).addBox(-3.728F, -6.1612F, -4.0672F, 1.6F, 1.0F, 2.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3099F, -28.4926F, 2.1F, 0.0F, 0.0F, 1.789F));

		PartDefinition pelo41 = gokucabello.addOrReplaceChild("pelo41", CubeListBuilder.create().texOffs(0, 33).addBox(-3.028F, -10.3612F, -4.8672F, 3.0F, 2.5F, 3.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7478F, -22.7093F, 2.2F, 0.0F, 0.0F, 1.1345F));

		PartDefinition pelo42 = gokucabello.addOrReplaceChild("pelo42", CubeListBuilder.create().texOffs(0, 33).addBox(-2.128F, -9.0612F, -4.3672F, 1.8F, 1.2F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5873F, -24.8292F, 2.2F, 0.0F, 0.0F, 1.309F));

		PartDefinition pelo43 = gokucabello.addOrReplaceChild("pelo43", CubeListBuilder.create().texOffs(0, 33).addBox(-3.328F, -10.0612F, -4.9672F, 1.8F, 2.5F, 2.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1108F, -18.3699F, 2.6F, 0.0F, 0.0F, 0.829F));

		PartDefinition pelo44 = gokucabello.addOrReplaceChild("pelo44", CubeListBuilder.create().texOffs(0, 33).addBox(-3.328F, -10.0612F, -4.9672F, 1.8F, 2.5F, 2.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0778F, -17.1692F, 4.5F, 0.0F, 0.0F, 0.6545F));

		PartDefinition pelo45 = gokucabello.addOrReplaceChild("pelo45", CubeListBuilder.create().texOffs(0, 33).addBox(-3.028F, -10.3612F, -4.6672F, 3.0F, 2.5F, 3.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0075F, -21.6339F, 5.1F, 0.0F, 0.0F, 1.021F));

		PartDefinition pelo46 = gokucabello.addOrReplaceChild("pelo46", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5536F, -22.6158F, 5.1F, 0.0F, 0.0F, 1.021F));

		PartDefinition cube_r8 = pelo46.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 33).addBox(0.972F, -3.0612F, -0.9672F, 2.2F, 2.5F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0F, 0.0F, -0.2182F));

		PartDefinition pelo47 = gokucabello.addOrReplaceChild("pelo47", CubeListBuilder.create().texOffs(0, 33).addBox(-4.528F, -8.4612F, -2.9672F, 3.8F, 3.3F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1057F, -29.1594F, 4.6F, 0.0F, 0.0F, 1.7453F));

		PartDefinition pelo48 = gokucabello.addOrReplaceChild("pelo48", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.6133F, -29.0726F, 4.6F, 0.0F, 0.0F, 1.7453F));

		PartDefinition cube_r9 = pelo48.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 33).addBox(-1.828F, -1.9612F, 0.7328F, 2.3F, 1.9F, 2.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0F, 0.0F, 0.2182F));

		PartDefinition pelo49 = gokucabello.addOrReplaceChild("pelo49", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.8282F, -27.5852F, 4.6F, 0.0F, 0.0F, 1.3526F));

		PartDefinition cube_r10 = pelo49.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 33).addBox(-2.028F, -1.4612F, 0.4328F, 3.4F, 3.3F, 2.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0F, 0.0F, -0.4363F));

		PartDefinition pelo50 = gokucabello.addOrReplaceChild("pelo50", CubeListBuilder.create().texOffs(0, 33).addBox(-4.228F, -6.9612F, -2.6672F, 2.0F, 1.8F, 1.9F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.485F, -28.1047F, 4.6F, 0.0F, 0.0F, 1.3526F));

		PartDefinition pelo51 = gokucabello.addOrReplaceChild("pelo51", CubeListBuilder.create().texOffs(0, 33).addBox(-3.328F, -10.0612F, -4.7672F, 1.8F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0809F, -17.291F, 6.5F, 0.0F, 0.0F, 0.6545F));

		PartDefinition pelo52 = gokucabello.addOrReplaceChild("pelo52", CubeListBuilder.create().texOffs(0, 33).addBox(-3.928F, -9.6612F, -4.6672F, 3.6F, 3.8F, 4.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6F, -30.8F, 4.8F, 0.0F, 0.0F, -1.309F));

		PartDefinition pelo53 = gokucabello.addOrReplaceChild("pelo53", CubeListBuilder.create().texOffs(0, 33).addBox(-3.528F, -8.0612F, -3.5672F, 2.6F, 2.2F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9936F, -32.4544F, 4.8F, 0.0F, 0.0F, -1.4399F));

		PartDefinition pelo54 = gokucabello.addOrReplaceChild("pelo54", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.4809F, -16.1299F, 3.8426F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r11 = pelo54.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 33).addBox(-0.828F, -3.2165F, -0.7877F, 1.8F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0873F, 0.0F, 0.0F));

		PartDefinition pelo55 = gokucabello.addOrReplaceChild("pelo55", CubeListBuilder.create(), PartPose.offsetAndRotation(4.9191F, -16.1299F, 3.8426F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r12 = pelo55.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 33).addBox(-0.828F, -3.2266F, -0.7905F, 2.0F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, -3.6F, 0.0698F, 0.0F, 0.0F));

		PartDefinition pelo56 = gokucabello.addOrReplaceChild("pelo56", CubeListBuilder.create(), PartPose.offsetAndRotation(1.2191F, -16.1299F, 3.8426F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r13 = pelo56.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 33).addBox(-0.7649F, -3.8083F, -0.789F, 2.5F, 3.1F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, -7.0F, -3.6F, 0.0896F, -0.0181F, -0.2612F));

		PartDefinition pelo57 = gokucabello.addOrReplaceChild("pelo57", CubeListBuilder.create(), PartPose.offsetAndRotation(3.5191F, -16.1299F, 3.8426F, -0.3665F, 0.0F, 0.0F));

		PartDefinition cube_r14 = pelo57.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 33).addBox(-1.6746F, -3.7962F, -0.7845F, 2.6F, 3.1F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, -6.9F, -3.6F, 0.1309F, 0.0F, 0.2182F));

		PartDefinition pelo58 = gokucabello.addOrReplaceChild("pelo58", CubeListBuilder.create(), PartPose.offsetAndRotation(2.5191F, -16.1299F, 3.8426F, -0.3491F, 0.0F, 0.0F));

		PartDefinition pelo59 = gokucabello.addOrReplaceChild("pelo59", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.4809F, -18.3358F, 5.5859F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r15 = pelo59.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 33).addBox(-0.8111F, -4.4377F, -0.7724F, 2.8F, 3.8F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, -7.1F, -3.6F, 0.0F, 0.0F, -0.2182F));

		PartDefinition pelo60 = gokucabello.addOrReplaceChild("pelo60", CubeListBuilder.create(), PartPose.offsetAndRotation(4.7191F, -18.3358F, 5.5859F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r16 = pelo60.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 33).addBox(-2.1416F, -4.5383F, -0.7724F, 3.1F, 3.9F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9F, -7.0F, -3.6F, 0.0F, 0.0F, 0.1745F));

		PartDefinition pelo61 = gokucabello.addOrReplaceChild("pelo61", CubeListBuilder.create().texOffs(0, 33).addBox(-4.328F, -11.6499F, -4.5738F, 2.5F, 4.0F, 2.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7191F, -18.3358F, 5.5859F, -0.1484F, 0.0F, 0.0F));

		PartDefinition pelo62 = gokucabello.addOrReplaceChild("pelo62", CubeListBuilder.create(), PartPose.offsetAndRotation(4.7191F, -20.3771F, 4.3183F, -0.2356F, 0.0F, 0.0F));

		PartDefinition cube_r17 = pelo62.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 33).addBox(-4.928F, -4.5439F, -1.0789F, 5.9F, 3.9F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, -6.8F, -3.4F, -0.0436F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		gokucabello.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}