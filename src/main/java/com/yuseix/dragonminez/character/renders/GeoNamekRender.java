package com.yuseix.dragonminez.character.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
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

public class GeoNamekRender<T extends AbstractClientPlayer & GeoAnimatable> extends GeoEntityRenderer<T> {

    private float colorR, colorG, colorB;

    public GeoNamekRender(EntityRendererProvider.Context renderManager, GeoModel<T> model) {
        super(renderManager, model);
    }

    @Override
    public void render(T entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        poseStack.scale(0.9375F, 0.9375F, 0.9375F);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

        poseStack.popPose();
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
        float avgVelocity = (float) (Math.abs(velocity.x) + Math.abs(velocity.z)) / 2f;

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
            } else {
                renderType = null;
            }
        }

        if (renderType != null) {

            RenderType finalRenderType = renderType;
            VertexConsumer finalBuffer = buffer;

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, livingEntity).ifPresent(cap -> {
                var bodytype = cap.getBodytype();

                NamekBase(poseStack, animatable, model, finalRenderType, bufferSource, finalBuffer, isReRender, partialTick, packedLight,
                        packedOverlay, red, green, blue, alpha);


            });


        }

        poseStack.popPose();
    }

    private void NamekBase(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        LivingEntity livingEntity = animatable;

        var head = model.getBone("head").get();
        var body = model.getBone("body").get();
        var brazoderecho = model.getBone("right_arm").get();
        var brazoizquierdo = model.getBone("left_arm").get();
        var piernaderecha = model.getBone("right_leg").get();
        var piernaizquierda = model.getBone("left_leg").get();

        var orejas_type1 = model.getBone("orejas").get();
        var orejas_type2 = model.getBone("orejas2").get();

        var skin_type1_part1 = RenderType.entityTranslucent(TextureManager.N_BASE_BODY1_PART1);
        var skin_type1_part2 = RenderType.entityTranslucent(TextureManager.N_BASE_BODY1_PART2);
        var skin_type1_part3 = RenderType.entityTranslucent(TextureManager.N_BASE_BODY1_PART3);

        var ojos = RenderType.entityCutoutNoCull(TextureManager.N_EYES1);
        var iris = RenderType.entityCutoutNoCull(TextureManager.N_EYES1_IRIS1);
        var iris2 = RenderType.entityCutoutNoCull(TextureManager.N_EYES1_IRIS2);
        var cejas = RenderType.entityCutoutNoCull(TextureManager.N_EYES1_CEJAS);


        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, livingEntity).ifPresent(cap -> {

            int bodyColor = cap.getBodyColor();
            poseStack.translate(0.0f, 0.0f, 0.0f);

            switch (cap.getHairID()) {
                case 0:
                    orejas_type1.setHidden(false);
                    orejas_type2.setHidden(true);
                    colorR = (bodyColor >> 16) / 255.0F;
                    colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                    colorB = (bodyColor & 0xff) / 255.0f;
                    renderRecursively(poseStack, animatable, head, skin_type1_part1, bufferSource, bufferSource.getBuffer(skin_type1_part1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    break;
                case 1:
                    orejas_type1.setHidden(true);
                    orejas_type2.setHidden(false);
                    colorR = (bodyColor >> 16) / 255.0F;
                    colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                    colorB = (bodyColor & 0xff) / 255.0f;
                    renderRecursively(poseStack, animatable, head, skin_type1_part1, bufferSource, bufferSource.getBuffer(skin_type1_part1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    break;
                case 2:
                    orejas_type1.setHidden(false);
                    orejas_type2.setHidden(false);
                    colorR = (bodyColor >> 16) / 255.0F;
                    colorG = ((bodyColor >> 8) & 0xff) / 255.0f;
                    colorB = (bodyColor & 0xff) / 255.0f;
                    renderRecursively(poseStack, animatable, head, skin_type1_part1, bufferSource, bufferSource.getBuffer(skin_type1_part1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    break;
                default:
                    break;
            }


            switch (cap.getBodytype()) {
                case 0:
                    int bodyColor1 = cap.getBodyColor();
                    colorR = (bodyColor1 >> 16) / 255.0F;
                    colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
                    colorB = (bodyColor1 & 0xff) / 255.0f;

                    //Cuerpo1
                    renderRecursively(poseStack, animatable, head, skin_type1_part1, bufferSource, bufferSource.getBuffer(skin_type1_part1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, body, skin_type1_part1, bufferSource, bufferSource.getBuffer(skin_type1_part1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, brazoderecho, skin_type1_part1, bufferSource, bufferSource.getBuffer(skin_type1_part1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, brazoizquierdo, skin_type1_part1, bufferSource, bufferSource.getBuffer(skin_type1_part1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, piernaderecha, skin_type1_part1, bufferSource, bufferSource.getBuffer(skin_type1_part1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, piernaizquierda, skin_type1_part1, bufferSource, bufferSource.getBuffer(skin_type1_part1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

                    //Cuerpo 2
                    int bodyColor2 = cap.getBodyColor2();
                    colorR = (bodyColor2 >> 16) / 255.0F;
                    colorG = ((bodyColor2 >> 8) & 0xff) / 255.0f;
                    colorB = (bodyColor2 & 0xff) / 255.0f;

                    renderRecursively(poseStack, animatable, head, skin_type1_part2, bufferSource, bufferSource.getBuffer(skin_type1_part2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, body, skin_type1_part2, bufferSource, bufferSource.getBuffer(skin_type1_part2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, brazoderecho, skin_type1_part2, bufferSource, bufferSource.getBuffer(skin_type1_part2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, brazoizquierdo, skin_type1_part2, bufferSource, bufferSource.getBuffer(skin_type1_part2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, piernaderecha, skin_type1_part2, bufferSource, bufferSource.getBuffer(skin_type1_part2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, piernaizquierda, skin_type1_part2, bufferSource, bufferSource.getBuffer(skin_type1_part2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

                    //Cuerpo 3
                    int bodyColor3 = cap.getBodyColor3();
                    colorR = (bodyColor3 >> 16) / 255.0F;
                    colorG = ((bodyColor3 >> 8) & 0xff) / 255.0f;
                    colorB = (bodyColor3 & 0xff) / 255.0f;

                    renderRecursively(poseStack, animatable, head, skin_type1_part3, bufferSource, bufferSource.getBuffer(skin_type1_part3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, body, skin_type1_part3, bufferSource, bufferSource.getBuffer(skin_type1_part3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, brazoderecho, skin_type1_part3, bufferSource, bufferSource.getBuffer(skin_type1_part3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, brazoizquierdo, skin_type1_part3, bufferSource, bufferSource.getBuffer(skin_type1_part3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, piernaderecha, skin_type1_part3, bufferSource, bufferSource.getBuffer(skin_type1_part3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
                    renderRecursively(poseStack, animatable, piernaizquierda, skin_type1_part3, bufferSource, bufferSource.getBuffer(skin_type1_part3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

                    break;
            }

            if (cap.getEyesType() == 0) {
                int irisColor1 = cap.getEye1Color();
                colorR = (irisColor1 >> 16) / 255.0F;
                colorG = ((irisColor1 >> 8) & 0xff) / 255.0f;
                colorB = (irisColor1 & 0xff) / 255.0f;
                //OJOS
                poseStack.translate(0.0f, 0.0f, -0.0001f);
                renderRecursively(poseStack, animatable, head, ojos, bufferSource, bufferSource.getBuffer(ojos), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1.0f);
                //IRIS
                poseStack.translate(0.0f, 0.0f, -0.0002f);
                renderRecursively(poseStack, animatable, head, iris, bufferSource, bufferSource.getBuffer(iris), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

                int irisColor2 = cap.getEye2Color();
                colorR = (irisColor2 >> 16) / 255.0F;
                colorG = ((irisColor2 >> 8) & 0xff) / 255.0f;
                colorB = (irisColor2 & 0xff) / 255.0f;
                //IRIS
                poseStack.translate(0.0f, 0.0f, -0.0002f);
                renderRecursively(poseStack, animatable, head, iris2, bufferSource, bufferSource.getBuffer(iris2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

                int cejasColor = cap.getBodyColor();
                colorR = (cejasColor >> 16) / 255.0F;
                colorG = ((cejasColor >> 8) & 0xff) / 255.0f;
                colorB = (cejasColor & 0xff) / 255.0f;
                //OJOS
                poseStack.translate(0.0f, 0.0f, -0.0001f);
                renderRecursively(poseStack, animatable, head, cejas, bufferSource, bufferSource.getBuffer(cejas), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            }

        });

    }
}
