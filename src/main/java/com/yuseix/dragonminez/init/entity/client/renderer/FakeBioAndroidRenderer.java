package com.yuseix.dragonminez.init.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.FakeBioAndroidModel;
import com.yuseix.dragonminez.init.entity.custom.FakeBioAndroidEntity;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FakeBioAndroidRenderer extends GeoEntityRenderer<FakeBioAndroidEntity> {

    private static final ResourceLocation B_BODY1 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodybase1.png");
    private static final ResourceLocation B_BODY2 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodybase2.png");
    private static final ResourceLocation B_BODY3 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodybase3.png");
    private static final ResourceLocation B_BODYCOLA = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodycola.png");
    private static final ResourceLocation B_EYES = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/eyes/eyes_0.png");

    public FakeBioAndroidRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FakeBioAndroidModel());

    }


    @Override
    public void actuallyRender(PoseStack poseStack, FakeBioAndroidEntity animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();

        if (renderType != null) {
            render(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight,
                    packedOverlay, red, green, blue, alpha);
        }

        poseStack.popPose();
    }


    private void render(PoseStack poseStack, FakeBioAndroidEntity animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        var player = Minecraft.getInstance().player;

        var head = model.getBone("bipedHead").get();
        var body = model.getBone("bipedBody").get();
        var brazoderecho = model.getBone("bipedRightArm").get();
        var brazoizquierdo = model.getBone("bipedLeftArm").get();
        var piernaderecha = model.getBone("bipedRightLeg").get();
        var piernaizquierda = model.getBone("bipedLeftLeg").get();


        var skin_type1 = RenderType.entityTranslucent(B_BODY1);
        var skin_type2 = RenderType.entityTranslucent(B_BODY2);
        var skin_type3 = RenderType.entityTranslucent(B_BODY3);
        var bcola = RenderType.entityTranslucent(B_BODYCOLA);
        var ojos = RenderType.entityTranslucent(B_EYES);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
            //Cuerpo1
            int bodyColor1 = cap.getBodyColor();
            float colorR = (bodyColor1 >> 16) / 255.0F;
            float colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
            float colorB = (bodyColor1 & 0xff) / 255.0f;
            renderRecursively(poseStack, animatable, head, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, body, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, brazoderecho, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, brazoizquierdo, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaderecha, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaizquierda, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            //Cuerpo2
            int bodyColor2 = cap.getBodyColor2();
            colorR = (bodyColor2 >> 16) / 255.0F;
            colorG = ((bodyColor2 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor2 & 0xff) / 255.0f;
            renderRecursively(poseStack, animatable, head, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, body, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, body, bcola, bufferSource, bufferSource.getBuffer(bcola), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1.0f);
            renderRecursively(poseStack, animatable, brazoderecho, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, brazoizquierdo, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaderecha, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaizquierda, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            int bodyColor3 = cap.getBodyColor3();
            colorR = (bodyColor3 >> 16) / 255.0F;
            colorG = ((bodyColor3 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor3 & 0xff) / 255.0f;
            renderRecursively(poseStack, animatable, head, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

            renderRecursively(poseStack, animatable, body, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

            renderRecursively(poseStack, animatable, brazoderecho, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

            renderRecursively(poseStack, animatable, brazoizquierdo, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

            renderRecursively(poseStack, animatable, piernaderecha, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

            renderRecursively(poseStack, animatable, piernaizquierda, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);


        });

        poseStack.translate(0.0f, 0.0f, -0.001f);
        renderRecursively(poseStack, animatable, head, ojos, bufferSource, bufferSource.getBuffer(ojos), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1.0f);

    }

}

