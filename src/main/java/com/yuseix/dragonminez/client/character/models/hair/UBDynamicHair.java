package com.yuseix.dragonminez.character.models.hair;

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

import java.util.HashMap;
import java.util.Map;

/**
 * Explanation of implementation and approach.
 * <p>
 * This class is a model that represents a dynamically rendered hair model for the player.
 * We design this off a "stack and strand" approach. Each of the five visible face directions for the player has a grid
 * of 4x4 "stacks" of hair. Each stack has 4 "strands" of hair.
 * <p>
 * Each stack has a configurable "length", which says how many strands are visible in the stack.
 * If a strand is not visible, we skip rendering it.
 * Stacks can also be entirely hidden.
 * <p>
 * The hair model is generated entirely in the createBodyLayer method, which does *not* have access to the player's data.
 * This means we must all do all transformations after the point of creation.
 * <p>
 * This is partially done because the initial creation of the model is quite expensive, and we definitely don't want to
 * be doing that every frame.
 * <p>
 * In SetupAnim, hair strands will have their position and location data altered, as well as potentially having their scale adjusted.
 * We need do to some math to ensure each strand is positioned properly along its parent by snapping down the highest edge to its nearest corner.
 * Maybe worth checking the blockbench source code to see how their code for that works.
 * <p>
 * Each "hairstack" will have a curveX and a curveZ which defines how much to rotate the strand relative to the strand before it.
 * Ex: 0.15 means we rotate each strand by 0.15*N where N is the strand number.
 * <p>
 * I (UB) am currently working on a serialization format based on a byte array with palette compression which will back these transformations later.
 * It may also allow players to select a color (or a two color gradient we sample from automatically) for the hair to follow. I still need to look into how this would work
 */

public class UBDynamicHair extends HumanoidModel<AbstractClientPlayer> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DragonMineZ.MOD_ID, "dynamichair"), "main");
    private static final float scaleFactor = 0.99F;
    private final ModelPart TopHair;
    private final ModelPart LeftHair;
    private final ModelPart RightHair;
    private final ModelPart BackHair;
    private final Map<String, ModelPart> hairStrands = new HashMap<>();

    public UBDynamicHair(ModelPart root) {
        super(root);
        root = root.getChild("head");
        this.TopHair = root.getChild("TopHair");
        this.LeftHair = root.getChild("LeftHair");
        this.RightHair = root.getChild("RightHair");
        this.BackHair = root.getChild("BackHair");
        populateStrandMap(root);
    }

    private static String generateStrandId(String face, int rowId, int stackId, int strandId) {
        return String.format("%s_row%d_stack%d_strand%d", face, rowId, stackId, strandId);
    }

    private static CubeListBuilder createHairStrand(int yOffset, float scale) {
        CubeListBuilder builder = CubeListBuilder.create();
        builder.texOffs(6, 0).addBox(
                -0.5F * scale, yOffset, -1.5F * scale,  // position
                2.0F * scale, 2.0F, 2.0F * scale        // dimensions
        );
        return builder;
    }

    private static void addHairRow(PartDefinition parent, String face, int rowNum, float offsetZ, Map<String, ModelPart> strandMap) {
        PartDefinition row = parent.addOrReplaceChild("Row" + rowNum, CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, offsetZ));

        for (int stackId = 0; stackId < 4; stackId++) {
            PartDefinition stack = row.addOrReplaceChild(
                    "HairStack" + (((rowNum - 1) * 4) + stackId + 1),
                    CubeListBuilder.create(),
                    PartPose.offset(-3.5F + (2.0F * stackId), 0.0F, -2.5F)
            );

            // Create four individual strands for each stack
            float scale = 1.0F;
            for (int strandId = 0; strandId < 4; strandId++) {
                String strandKey = generateStrandId(face, rowNum, stackId + 1, strandId + 1);
                PartDefinition strand = stack.addOrReplaceChild(
                        "Strand" + (strandId + 1),
                        createHairStrand(-2 * (strandId + 1), scale),
                        PartPose.offset(0.0F, 0.0F, 0.0F)
                );
                scale *= scaleFactor;
            }
        }
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0f);
        PartDefinition partdefinition = meshdefinition.getRoot().getChild("head");

        // Top Hair
        PartDefinition TopHair = partdefinition.addOrReplaceChild("TopHair", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));
        for (int i = 0; i < 4; i++) {
            addHairRow(TopHair, "top", i + 1, i * 2.0F, null);
        }

        // Left Hair
        PartDefinition LeftHair = partdefinition.addOrReplaceChild("LeftHair", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, -4.0F, 0.0F, 0.0F, 0.0F, -1.5708F));
        for (int i = 0; i < 4; i++) {
            addHairRow(LeftHair, "left", i + 1, i * 2.0F, null);
        }

        // Right Hair
        PartDefinition RightHair = partdefinition.addOrReplaceChild("RightHair", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, -4.0F, 0.0F, 0.0F, 0.0F, 1.5708F));
        for (int i = 0; i < 4; i++) {
            addHairRow(RightHair, "right", i + 1, i * 2.0F, null);
        }

        // Back Hair
        PartDefinition BackHair = partdefinition.addOrReplaceChild("BackHair", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -4.0F, 4.0F, -1.5708F, 0.0F, 1.5708F));
        for (int i = 0; i < 4; i++) {
            addHairRow(BackHair, "back", i + 1, i * 2.0F, null);
        }

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    // Add this method to populate the strand map after construction
    private void populateStrandMap(ModelPart root) {
        String[] faces = {"Top", "Left", "Right", "Back"};

        for (String face : faces) {
            ModelPart facePart = root.getChild(face + "Hair");
            for (int row = 1; row <= 4; row++) {
                ModelPart rowPart = facePart.getChild("Row" + row);
                for (int stack = 1; stack <= 4; stack++) {
                    ModelPart stackPart = rowPart.getChild("HairStack" + ((row - 1) * 4 + stack));
                    for (int strand = 1; strand <= 4; strand++) {
                        String strandId = generateStrandId(face, row, stack, strand);
                        hairStrands.put(strandId, stackPart.getChild("Strand" + strand));
                    }
                }
            }
        }
    }

    @Override
    public void setupAnim(AbstractClientPlayer pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        TopHair.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftHair.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        RightHair.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        BackHair.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}