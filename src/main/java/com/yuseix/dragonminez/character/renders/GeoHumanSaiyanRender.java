package com.yuseix.dragonminez.character.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.stats.DMZCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.TextureManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GeoHumanSaiyanRender<T extends AbstractClientPlayer & GeoAnimatable> extends GeoEntityRenderer<T> {

    private float colorR,colorG,colorB;

    public GeoHumanSaiyanRender(EntityRendererProvider.Context renderManager, GeoModel<T> model) {
        super(renderManager, model);
    }

    @Override
    public void actuallyRender(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();

        LivingEntity livingEntity = this.animatable;
        boolean shouldSit = this.animatable.isPassenger() && (this.animatable.getVehicle() != null && this.animatable.getVehicle().shouldRiderSit());
        float lerpBodyRot = Mth.rotLerp(partialTick, livingEntity.yBodyRotO, livingEntity.yBodyRot);
        float lerpHeadRot = Mth.rotLerp(partialTick, livingEntity.yHeadRotO, livingEntity.yHeadRot);
        float netHeadYaw = lerpHeadRot - lerpBodyRot;

        if (shouldSit && this.animatable.getVehicle() instanceof LivingEntity livingentity) {
            lerpBodyRot = Mth.rotLerp(partialTick, livingentity.yBodyRotO, livingentity.yBodyRot);
            netHeadYaw = lerpHeadRot - lerpBodyRot;
            float clampedHeadYaw = Mth.clamp(Mth.wrapDegrees(netHeadYaw), -85, 85);
            lerpBodyRot = lerpHeadRot - clampedHeadYaw;

            if (clampedHeadYaw * clampedHeadYaw > 2500f)
                lerpBodyRot += clampedHeadYaw * 0.2f;

            netHeadYaw = lerpHeadRot - lerpBodyRot;
        }

        if (this.animatable.getPose() == Pose.SLEEPING) {
            Direction bedDirection = livingEntity.getBedOrientation();

            if (bedDirection != null) {
                float eyePosOffset = livingEntity.getEyeHeight(Pose.STANDING) - 0.1F;

                poseStack.translate(-bedDirection.getStepX() * eyePosOffset, 0, -bedDirection.getStepZ() * eyePosOffset);
            }
        }

        float ageInTicks = this.animatable.tickCount + partialTick;
        float limbSwingAmount = 0;
        float limbSwing = 0;

        applyRotations(animatable, poseStack, ageInTicks, lerpBodyRot, partialTick);

        if (!shouldSit && this.animatable.isAlive()) {
            limbSwingAmount = livingEntity.walkAnimation.speed(partialTick);
            limbSwing = livingEntity.walkAnimation.position(partialTick);

            if (livingEntity.isBaby())
                limbSwing *= 3f;

            if (limbSwingAmount > 1f)
                limbSwingAmount = 1f;
        }

        float headPitch = Mth.lerp(partialTick, this.animatable.xRotO, this.animatable.getXRot());
        float motionThreshold = getMotionAnimThreshold(animatable);
        boolean isMoving;


        Vec3 velocity = livingEntity.getDeltaMovement();
        float avgVelocity = (float)(Math.abs(velocity.x) + Math.abs(velocity.z)) / 2f;

        isMoving = avgVelocity >= motionThreshold && limbSwingAmount != 0;



        if (!isReRender) {
            AnimationState animationState = new AnimationState<>(animatable, limbSwing, limbSwingAmount, partialTick, isMoving);
            long instanceId = getInstanceId(animatable);

            animationState.setData(DataTickets.TICK, animatable.getTick(this.animatable));
            animationState.setData(DataTickets.ENTITY, this.animatable);
            animationState.setData(DataTickets.ENTITY_MODEL_DATA, new EntityModelData(shouldSit, livingEntity.isBaby(), -netHeadYaw, -headPitch));
            this.model.addAdditionalStateData(animatable, instanceId, animationState::setData);
            this.model.handleAnimations(animatable, instanceId, animationState);
        }

        poseStack.translate(0, 0.01f, 0);

        this.modelRenderTranslations = new Matrix4f(poseStack.last().pose());

        if (this.animatable.isInvisibleTo(Minecraft.getInstance().player)) {
            if (Minecraft.getInstance().shouldEntityAppearGlowing(this.animatable)) {
                buffer = bufferSource.getBuffer(renderType = RenderType.outline(getTextureLocation(animatable)));
            }
            else {
                renderType = null;
            }
        }

        if (renderType != null) {

            RenderType finalRenderType = renderType;
            VertexConsumer finalBuffer = buffer;

            DMZStatsProvider.getCap(DMZCapabilities.INSTANCE, livingEntity).ifPresent(cap -> {

                var raza = cap.getRace();
                var bodytype = cap.getBodytype();

                switch (raza){
                    //Humano
                    case 0:
                        if(bodytype == 0){
                            HumanRenderBody1(poseStack, animatable, model, finalRenderType, bufferSource, finalBuffer, isReRender, partialTick, packedLight,
                                    packedOverlay, red, green, blue, alpha);
                        }else if(bodytype == 1){
                            HumanRenderBody2(poseStack, animatable, model, finalRenderType, bufferSource, finalBuffer, isReRender, partialTick, packedLight,
                                    packedOverlay, red, green, blue, alpha);
                        }
                        break;
                    //SAIYAJIN
                    case 1:
                        if(bodytype == 0){
                            SaiyanRenderBody1(poseStack, animatable, model, finalRenderType, bufferSource, finalBuffer, isReRender, partialTick, packedLight,
                                    packedOverlay, red, green, blue, alpha);
                        }else if(bodytype == 1){
                            SaiyanRenderBody2(poseStack, animatable, model, finalRenderType, bufferSource, finalBuffer, isReRender, partialTick, packedLight,
                                    packedOverlay, red, green, blue, alpha);
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

        LivingEntity livingEntity = animatable;

        var head = model.getBone("head").get();
        var body = model.getBone("body").get();
        var brazoderecho = model.getBone("right_arm").get();
        var brazoizquierdo = model.getBone("left_arm").get();
        var piernaderecha = model.getBone("right_leg").get();
        var piernaizquierda = model.getBone("left_leg").get();

        var skin_type1 = RenderType.entityTranslucent(animatable.getSkinTextureLocation());

        DMZStatsProvider.getCap(DMZCapabilities.INSTANCE,livingEntity).ifPresent(cap -> {
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

        LivingEntity livingEntity = animatable;

        var head = model.getBone("head").get();
        var body = model.getBone("body").get();
        var brazoderecho = model.getBone("right_arm").get();
        var brazoizquierdo = model.getBone("left_arm").get();
        var piernaderecha = model.getBone("right_leg").get();
        var piernaizquierda = model.getBone("left_leg").get();

        var skin_type1 = RenderType.entityTranslucent(TextureManager.SH_BODY1);
        var ojos = RenderType.entityCutoutNoCull(TextureManager.SH_EYES1);
        var iris = RenderType.entityCutoutNoCull(TextureManager.SH_IRIS1);
        var iris2 = RenderType.entityCutoutNoCull(TextureManager.SH_IRIS2);

        DMZStatsProvider.getCap(DMZCapabilities.INSTANCE,livingEntity).ifPresent(cap -> {

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
                poseStack.translate(0.0f,0.0f,-0.0001f);
                renderRecursively(poseStack, animatable, head, ojos, bufferSource, bufferSource.getBuffer(ojos), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1f, 1f, 1f, 1.0f);
                //IRIS
                poseStack.translate(0.0f,0.0f,-0.0002f);
                renderRecursively(poseStack, animatable, head, iris, bufferSource, bufferSource.getBuffer(iris), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);

                int irisColor2 = cap.getEye2Color();
                colorR = (irisColor2 >> 16) / 255.0F;
                colorG = ((irisColor2 >> 8) & 0xff) / 255.0f;
                colorB = (irisColor2 & 0xff) / 255.0f;
                //IRIS
                poseStack.translate(0.0f,0.0f,-0.0002f);
                renderRecursively(poseStack, animatable, head, iris2, bufferSource, bufferSource.getBuffer(iris2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);

            }

        });

    }

    private void SaiyanRenderBody1(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        LivingEntity livingEntity = animatable;

        var head = model.getBone("head").get();
        var body = model.getBone("body").get();
        var brazoderecho = model.getBone("right_arm").get();
        var brazoizquierdo = model.getBone("left_arm").get();
        var piernaderecha = model.getBone("right_leg").get();
        var piernaizquierda = model.getBone("left_leg").get();

        var skin_type1 = RenderType.entityTranslucent(animatable.getSkinTextureLocation());

        DMZStatsProvider.getCap(DMZCapabilities.INSTANCE,livingEntity).ifPresent(cap -> {
            //Cuerpo1
            renderRecursively(poseStack, animatable, head, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, body, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, brazoderecho, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, brazoizquierdo, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, piernaderecha, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);
            renderRecursively(poseStack, animatable, piernaizquierda, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1.0f, 1.0f, 1.0f, 1.0f);

        });

    }

    private void SaiyanRenderBody2(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        LivingEntity livingEntity = animatable;

        var head = model.getBone("head").get();
        var body = model.getBone("body").get();
        var brazoderecho = model.getBone("right_arm").get();
        var brazoizquierdo = model.getBone("left_arm").get();
        var piernaderecha = model.getBone("right_leg").get();
        var piernaizquierda = model.getBone("left_leg").get();

        var skin_type1 = RenderType.entityTranslucent(TextureManager.SH_BODY1);
        var ojos = RenderType.entityCutoutNoCull(TextureManager.SH_EYES1);
        var iris = RenderType.entityCutoutNoCull(TextureManager.SH_IRIS1);
        var iris2 = RenderType.entityCutoutNoCull(TextureManager.SH_IRIS2);

        DMZStatsProvider.getCap(DMZCapabilities.INSTANCE,livingEntity).ifPresent(cap -> {

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

                int irisColor2 = cap.getEye2Color();
                colorR = (irisColor2 >> 16) / 255.0F;
                colorG = ((irisColor2 >> 8) & 0xff) / 255.0f;
                colorB = (irisColor2 & 0xff) / 255.0f;
                //IRIS
                poseStack.translate(0.0f,0.0f,-0.002f);
                renderRecursively(poseStack, animatable, head, iris2, bufferSource, bufferSource.getBuffer(iris2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);

            }

        });

    }

}
