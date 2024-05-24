package com.yuseix.dragonminez.character.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.events.ModEvents;
import com.yuseix.dragonminez.stats.PlayerStatsAttrProvider;
import com.yuseix.dragonminez.utils.TextureManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GeoHumanSaiyanRender<T extends AbstractClientPlayer & GeoAnimatable> extends GeoEntityRenderer<T> {

    private float colorR,colorG,colorB;

    public GeoHumanSaiyanRender(EntityRendererProvider.Context renderManager, GeoModel<T> model) {
        super(renderManager, model);
    }

    @Override
    public void actuallyRender(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();

        if (renderType != null) {

            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

                var raza = cap.getRace();
                var bodytype = cap.getBodytype();

                switch (raza){
                    //Humano
                    case 0:
                        if(bodytype == 0){
                            HumanRenderBody1(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight,
                                    packedOverlay, red, green, blue, alpha);
                        }else if(bodytype == 1){
                            HumanRenderBody2(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight,
                                    packedOverlay, red, green, blue, alpha);
                        }
                        break;
                    //SAIYAJIN
                    case 1:
                        if(bodytype == 0){
                            SaiyanRenderBody1(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight,
                                    packedOverlay, red, green, blue, alpha);
                        }else if(bodytype == 1){

                        }

                        break;

                    default:
                        break;
                }


            });


        }

        poseStack.popPose();
    }
    private void HumanRenderBody1(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        var head = model.getBone("head").get();
        var body = model.getBone("body").get();
        var brazoderecho = model.getBone("right_arm").get();
        var brazoizquierdo = model.getBone("left_arm").get();
        var piernaderecha = model.getBone("right_leg").get();
        var piernaizquierda = model.getBone("left_leg").get();

        var skin_type1 = RenderType.entityTranslucent(Minecraft.getInstance().player.getSkinTextureLocation());

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,Minecraft.getInstance().player).ifPresent(cap -> {
            //Cuerpo1
            renderRecursively(poseStack, animatable, head, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, body, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, brazoderecho, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, brazoizquierdo, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, piernaderecha, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, piernaizquierda, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);

        });

    }

    private void HumanRenderBody2(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        var head = model.getBone("head").get();
        var body = model.getBone("body").get();
        var brazoderecho = model.getBone("right_arm").get();
        var brazoizquierdo = model.getBone("left_arm").get();
        var piernaderecha = model.getBone("right_leg").get();
        var piernaizquierda = model.getBone("left_leg").get();

        var skin_type1 = RenderType.entityTranslucent(TextureManager.SH_BODY1);
        var ojos = RenderType.entityCutoutNoCull(TextureManager.SH_EYES1);
        var iris = RenderType.entityCutoutNoCull(TextureManager.SH_IRIS1);

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,Minecraft.getInstance().player).ifPresent(cap -> {

            if(cap.getGender().equals("Male")){
                //Cuerpo1
                int bodyColor1 = cap.getBodyColor();
                colorR = (bodyColor1 >> 16) / 255.0F;
                colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
                colorB = (bodyColor1 & 0xff) / 255.0f;
                renderRecursively(poseStack, animatable, head, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
                renderRecursively(poseStack, animatable, body, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
                renderRecursively(poseStack, animatable, brazoderecho, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
                renderRecursively(poseStack, animatable, brazoizquierdo, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
                renderRecursively(poseStack, animatable, piernaderecha, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
                renderRecursively(poseStack, animatable, piernaizquierda, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);

            }else {
                int bodyColor1 = cap.getBodyColor();
                colorR = (bodyColor1 >> 16) / 255.0F;
                colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
                colorB = (bodyColor1 & 0xff) / 255.0f;
                renderRecursively(poseStack, animatable, head, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
                renderRecursively(poseStack, animatable, body, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
                renderRecursively(poseStack, animatable, brazoderecho, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
                renderRecursively(poseStack, animatable, brazoizquierdo, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
                renderRecursively(poseStack, animatable, piernaderecha, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
                renderRecursively(poseStack, animatable, piernaizquierda, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);

            }

            if(cap.getEyesType() == 0){
                int irisColor1 = cap.getEye1Color();
                colorR = (irisColor1 >> 16) / 255.0F;
                colorG = ((irisColor1 >> 8) & 0xff) / 255.0f;
                colorB = (irisColor1 & 0xff) / 255.0f;
                //OJOS
                poseStack.translate(0.0f,0.0f,-0.001f);
                renderRecursively(poseStack, animatable, head, ojos, bufferSource, bufferSource.getBuffer(ojos), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1f, 1f, 1f, 1.0f);
                //IRIS
                poseStack.translate(0.0f,0.0f,-0.002f);
                renderRecursively(poseStack, animatable, head, iris, bufferSource, bufferSource.getBuffer(iris), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            }

        });

    }

    private void SaiyanRenderBody1(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        var head = model.getBone("head").get();
        var body = model.getBone("body").get();
        var brazoderecho = model.getBone("right_arm").get();
        var brazoizquierdo = model.getBone("left_arm").get();
        var piernaderecha = model.getBone("right_leg").get();
        var piernaizquierda = model.getBone("left_leg").get();

        var skin_type1 = RenderType.entityTranslucent(Minecraft.getInstance().player.getSkinTextureLocation());

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,Minecraft.getInstance().player).ifPresent(cap -> {
            //Cuerpo1
            renderRecursively(poseStack, animatable, head, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, body, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, brazoderecho, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, brazoizquierdo, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, piernaderecha, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, piernaizquierda, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);

        });

    }

}
