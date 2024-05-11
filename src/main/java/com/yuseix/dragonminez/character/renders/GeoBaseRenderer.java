package com.yuseix.dragonminez.character.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.GeoBaseModel;
import com.yuseix.dragonminez.character.models.GeoHumanModel;
import com.yuseix.dragonminez.character.models.bioandroid.GeoBioAndroidModel;
import com.yuseix.dragonminez.character.models.bioandroid.GeoBioAndroidPlayer;
import com.yuseix.dragonminez.character.models.entity.GeoBasePlayer;
import com.yuseix.dragonminez.events.ModEvents;
import com.yuseix.dragonminez.stats.PlayerStatsAttrProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;

public class GeoBaseRenderer extends GeoReplacedEntityRenderer<Player, GeoBasePlayer> {


    private final GeoModel HumanModel = new GeoHumanModel();
    private final GeoModel BioAndroidModel = new GeoBioAndroidModel();

    private static final ResourceLocation B_BODY1 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodybase1.png");
    private static final ResourceLocation B_BODY2 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodybase2.png");
    private static final ResourceLocation B_BODY3 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodybase3.png");
    private static final ResourceLocation B_BODYCOLA = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodycola.png");
    private static final ResourceLocation B_EYES = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/eyes/eyes_0.png");

    private float colorR,colorG,colorB;

    public GeoBaseRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GeoBaseModel(), new GeoBasePlayer());
    }

    @Override
    public void actuallyRender(PoseStack poseStack, GeoBasePlayer animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        poseStack.pushPose();

        LivingEntity livingEntity = this.currentEntity;
        boolean shouldSit = this.currentEntity.isPassenger() && (this.currentEntity.getVehicle() != null && this.currentEntity.getVehicle().shouldRiderSit());
        float lerpBodyRot = Mth.rotLerp(partialTick, livingEntity.yBodyRotO, livingEntity.yBodyRot);
        float lerpHeadRot = Mth.rotLerp(partialTick, livingEntity.yHeadRotO, livingEntity.yHeadRot);
        float netHeadYaw = lerpHeadRot - lerpBodyRot;

        if (shouldSit && this.currentEntity.getVehicle() instanceof LivingEntity livingentity) {
            lerpBodyRot = Mth.rotLerp(partialTick, livingentity.yBodyRotO, livingentity.yBodyRot);
            netHeadYaw = lerpHeadRot - lerpBodyRot;
            float clampedHeadYaw = Mth.clamp(Mth.wrapDegrees(netHeadYaw), -85, 85);
            lerpBodyRot = lerpHeadRot - clampedHeadYaw;

            if (clampedHeadYaw * clampedHeadYaw > 2500f)
                lerpBodyRot += clampedHeadYaw * 0.2f;

            netHeadYaw = lerpHeadRot - lerpBodyRot;
        }

        if (this.currentEntity.getPose() == Pose.SLEEPING) {
            Direction bedDirection = livingEntity.getBedOrientation();

            if (bedDirection != null) {
                float eyePosOffset = livingEntity.getEyeHeight(Pose.STANDING) - 0.1F;

                poseStack.translate(-bedDirection.getStepX() * eyePosOffset, 0, -bedDirection.getStepZ() * eyePosOffset);
            }
        }

        float ageInTicks = this.currentEntity.tickCount + partialTick;
        float limbSwingAmount = 0;
        float limbSwing = 0;

        applyRotations(animatable, poseStack, ageInTicks, lerpBodyRot, partialTick);

        if (!shouldSit && this.currentEntity.isAlive()) {
            limbSwingAmount = livingEntity.walkAnimation.speed(partialTick);
            limbSwing = livingEntity.walkAnimation.position(partialTick);

            if (livingEntity.isBaby())
                limbSwing *= 3f;

            if (limbSwingAmount > 1f)
                limbSwingAmount = 1f;
        }

        float headPitch = Mth.lerp(partialTick, this.currentEntity.xRotO, this.currentEntity.getXRot());
        float motionThreshold = getMotionAnimThreshold(animatable);
        boolean isMoving;


        Vec3 velocity = livingEntity.getDeltaMovement();
        float avgVelocity = (float)(Math.abs(velocity.x) + Math.abs(velocity.z)) / 2f;

        isMoving = avgVelocity >= motionThreshold && limbSwingAmount != 0;



        if (!isReRender) {
            AnimationState<GeoBasePlayer> animationState = new AnimationState<>(animatable, limbSwing, limbSwingAmount, partialTick, isMoving);
            long instanceId = getInstanceId(animatable);

            animationState.setData(DataTickets.TICK, animatable.getTick(this.currentEntity));
            animationState.setData(DataTickets.ENTITY, this.currentEntity);
            animationState.setData(DataTickets.ENTITY_MODEL_DATA, new EntityModelData(shouldSit, livingEntity.isBaby(), -netHeadYaw, -headPitch));
            this.model.addAdditionalStateData(animatable, instanceId, animationState::setData);
            this.model.handleAnimations(animatable, instanceId, animationState);
        }

        poseStack.translate(0, 0.01f, 0);

        this.modelRenderTranslations = new Matrix4f(poseStack.last().pose());

        if (this.currentEntity.isInvisibleTo(Minecraft.getInstance().player)) {
            if (Minecraft.getInstance().shouldEntityAppearGlowing(this.currentEntity)) {
                buffer = bufferSource.getBuffer(renderType = RenderType.outline(getTextureLocation(animatable)));
            }
            else {
                renderType = null;
            }
        }

        if (renderType != null) {
            RenderType finalRenderType = renderType;
            VertexConsumer finalBuffer = buffer;
            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,Minecraft.getInstance().player).ifPresent(cap -> {

                if(cap.getRace() == 3){
                    renderBio(poseStack, animatable, model , finalRenderType, bufferSource, finalBuffer,isReRender, partialTick, packedLight,
                            packedOverlay, red, green, blue, alpha);
                }

            });

        }

        poseStack.popPose();
    }

    private void renderBio(PoseStack poseStack, GeoBasePlayer animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        var player = currentEntity;

        GeoBone cabeza = (GeoBone) BioAndroidModel.getBone("bipedHead").get();
        var head = model.getBone("bipedHead").get();
        var body = model.getBone("bipedBody").get();
        var brazoderecho = model.getBone("bipedRightArm").get();
        var brazoizquierdo = model.getBone("bipedLeftArm").get();
        var piernaderecha = model.getBone("bipedRightLeg").get();
        var piernaizquierda = model.getBone("bipedLeftLeg").get();


        var skin_type1 = RenderType.entityTranslucent(B_BODY1);
        var skin_type2 = RenderType.entityCutoutNoCull(B_BODY2);
        var skin_type3 = RenderType.entityCutoutNoCull(B_BODY3);
        var bcola = RenderType.entityCutoutNoCull(B_BODYCOLA);
        var ojos = RenderType.entityCutoutNoCull(B_EYES);

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,player).ifPresent(cap -> {
            //Cuerpo1
            int bodyColor1 = cap.getBodyColor();
            colorR = (bodyColor1 >> 16) / 255.0F;
            colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor1 & 0xff) / 255.0f;
            renderRecursively(poseStack, animatable, cabeza, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, body, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, brazoderecho, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, brazoizquierdo, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaderecha, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaizquierda, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            //Cuerpo2
            int bodyColor2 = cap.getBodyColor2();
            colorR = (bodyColor2 >> 16) / 255.0F;
            colorG = ((bodyColor2 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor2 & 0xff) / 255.0f;

            renderRecursively(poseStack, animatable, head, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, body, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, brazoderecho, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, brazoizquierdo, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaderecha, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaizquierda, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            //Cuerpo3
            int bodyColor3 = cap.getBodyColor3();
            colorR = (bodyColor3 >> 16) / 255.0F;
            colorG = ((bodyColor3 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor3 & 0xff) / 255.0f;

            renderRecursively(poseStack, animatable, head, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, body, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, brazoderecho, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, brazoizquierdo, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaderecha, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaizquierda, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

        });


        renderRecursively(poseStack, animatable, body, bcola, bufferSource, bufferSource.getBuffer(bcola), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,1f, 1f, 1f, 1.0f);


        poseStack.translate(0.0f,0.0f,-0.001f);
        renderRecursively(poseStack, animatable, head, ojos, bufferSource, bufferSource.getBuffer(ojos), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1f, 1f, 1f, 1.0f);

    }

    private void renderwa(PoseStack poseStack, GeoBasePlayer animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        var player = currentEntity;

        var head = model.getBone("bipedHead").get();
        var body = model.getBone("bipedBody").get();
        var brazoderecho = model.getBone("bipedRightArm").get();
        var brazoizquierdo = model.getBone("bipedLeftArm").get();
        var piernaderecha = model.getBone("bipedRightLeg").get();
        var piernaizquierda = model.getBone("bipedLeftLeg").get();


        var skin_type1 = RenderType.entityTranslucent(B_BODY1);
        var skin_type2 = RenderType.entityCutoutNoCull(B_BODY2);
        var skin_type3 = RenderType.entityCutoutNoCull(B_BODY3);
        var bcola = RenderType.entityCutoutNoCull(B_BODYCOLA);
        var ojos = RenderType.entityCutoutNoCull(B_EYES);

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,player).ifPresent(cap -> {
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
            //Cuerpo2
            int bodyColor2 = cap.getBodyColor2();
            colorR = (bodyColor2 >> 16) / 255.0F;
            colorG = ((bodyColor2 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor2 & 0xff) / 255.0f;

            renderRecursively(poseStack, animatable, head, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, body, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, brazoderecho, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, brazoizquierdo, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaderecha, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaizquierda, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  colorR, colorG, colorB, 1.0f);
            //Cuerpo3
            int bodyColor3 = cap.getBodyColor3();
            colorR = (bodyColor3 >> 16) / 255.0F;
            colorG = ((bodyColor3 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor3 & 0xff) / 255.0f;

            renderRecursively(poseStack, animatable, head, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, body, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, brazoderecho, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, brazoizquierdo, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaderecha, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);
            renderRecursively(poseStack, animatable, piernaizquierda, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

        });


        renderRecursively(poseStack, animatable, body, bcola, bufferSource, bufferSource.getBuffer(bcola), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,1f, 1f, 1f, 1.0f);


        poseStack.translate(0.0f,0.0f,-0.001f);
        renderRecursively(poseStack, animatable, head, ojos, bufferSource, bufferSource.getBuffer(ojos), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1f, 1f, 1f, 1.0f);

    }
}
