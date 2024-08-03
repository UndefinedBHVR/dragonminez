package com.yuseix.dragonminez.character.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.HumanSaiyanModel;
import com.yuseix.dragonminez.character.models.hair.*;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class HairsLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private static final ResourceLocation SUIT_TEX = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/hairtexture.png");
    private static final ResourceLocation EARS = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/namek/body/ears.png");

    private float colorR, colorG, colorB;

    private GokuHairModel gokuhair;
    private FemHairModel femhair;
    private VegetaHairModel vegetahair;
    private GohanDBSHairModel gohandbshair;

    private EarsNamek earsNamek;
    private TailModel cola;

    public HairsLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);
        this.gokuhair = new GokuHairModel(GokuHairModel.createBodyLayer().bakeRoot());
        this.earsNamek = new EarsNamek(EarsNamek.createBodyLayer().bakeRoot());
        this.femhair = new FemHairModel(FemHairModel.createBodyLayer().bakeRoot());
        this.cola = new TailModel(TailModel.createBodyLayer().bakeRoot());
        this.vegetahair = new VegetaHairModel(VegetaHairModel.createBodyLayer().bakeRoot());
        this.gohandbshair = new GohanDBSHairModel(GohanDBSHairModel.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, AbstractClientPlayer abstractClientPlayer, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        poseStack.pushPose();
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucent(SUIT_TEX));

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE,abstractClientPlayer).ifPresent(cap -> {
            var raza = cap.getRace();
            var hairColor = cap.getHairColor();
            var hairId = cap.getHairID();
            var bodyColor = cap.getBodyColor();
            var genero = cap.getGender();

            colorR = (hairColor >> 16) / 255.0F;
            colorG = ((hairColor >> 8) & 0xff) / 255.0f;
            colorB = (hairColor & 0xff) / 255.0f;

            if(raza == 0 || raza == 1){

                if(raza == 1){
                    this.getParentModel().copyPropertiesTo(this.cola);
                    this.cola.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount,  ageInTicks, netHeadYaw,  headPitch);
                    this.cola.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY,0.410f,0.119f,0.00410f,1.0f);
                }

                if(hairId == 0){
                    this.getParentModel().getHead().translateAndRotate(poseStack);
                    this.gokuhair.renderToBuffer(poseStack,vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                } else if(hairId == 1){
                    this.getParentModel().getHead().translateAndRotate(poseStack);
                    this.femhair.renderToBuffer(poseStack,vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                } else if(hairId == 2){
                    this.getParentModel().getHead().translateAndRotate(poseStack);
                    this.vegetahair.renderToBuffer(poseStack,vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                } else if(hairId == 3){
                    this.gohandbshair.setupAnim(abstractClientPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    this.getParentModel().getHead().translateAndRotate(poseStack);
                    this.gohandbshair.renderToBuffer(poseStack,vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                }

            } else if(raza == 2){
                if(hairId == 0){
                    colorR = (bodyColor >> 16) / 255.0F;
                    colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                    colorB = (bodyColor & 0xff) / 255.0f;
                    this.getParentModel().getHead().translateAndRotate(poseStack);
                    this.earsNamek.renderEars1(poseStack, multiBufferSource.getBuffer(RenderType.entityCutout(EARS)), packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                } else if (hairId == 1){
                    colorR = (bodyColor >> 16) / 255.0F;
                    colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                    colorB = (bodyColor & 0xff) / 255.0f;
                    this.getParentModel().getHead().translateAndRotate(poseStack);
                    this.earsNamek.renderEars2(poseStack, multiBufferSource.getBuffer(RenderType.entityTranslucent(EARS)), packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                }
            } else if(raza == 5){
                if(genero.equals("Male")){

                }else{
                    if(hairId == 0){
                        colorR = (bodyColor >> 16) / 255.0F;
                        colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                        colorB = (bodyColor & 0xff) / 255.0f;
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.femhair.renderToBuffer(poseStack,vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } if(hairId == 1){
                        colorR = (bodyColor >> 16) / 255.0F;
                        colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                        colorB = (bodyColor & 0xff) / 255.0f;
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.gokuhair.renderToBuffer(poseStack,vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    } if(hairId == 2){
                        colorR = (bodyColor >> 16) / 255.0F;
                        colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                        colorB = (bodyColor & 0xff) / 255.0f;
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.vegetahair.renderToBuffer(poseStack,vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    }  if(hairId == 3){
                        colorR = (bodyColor >> 16) / 255.0F;
                        colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                        colorB = (bodyColor & 0xff) / 255.0f;
                        this.getParentModel().getHead().translateAndRotate(poseStack);
                        this.gohandbshair.renderToBuffer(poseStack,vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
                    }
                }

            }
        });

        poseStack.popPose();
    }
}
