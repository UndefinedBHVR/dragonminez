package com.yuseix.dragonminez.character.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.bioandroid.BioAndroideModelo;
import com.yuseix.dragonminez.character.models.demoncold.DemonColdModel;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.TextureManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.eventbus.api.Event;

import java.util.Iterator;

public class DemonColdRender extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private float colorR, colorG, colorB;

    public DemonColdRender(EntityRendererProvider.Context pContext, PlayerModel<AbstractClientPlayer> pModel) {
        super(pContext, pModel, 0.5f);
        this.addLayer(new HumanoidArmorLayer(this, new HumanoidArmorModel(pContext.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidArmorModel(pContext.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), pContext.getModelManager()));
        this.addLayer(new PlayerItemInHandLayer(this, pContext.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer(this, pContext.getModelSet()));
        this.addLayer(new ParrotOnShoulderLayer(this, pContext.getModelSet()));
        this.addLayer(new SpinAttackEffectLayer(this, pContext.getModelSet()));
        this.addLayer(new BeeStingerLayer(this));
    }

    @Override
    public void render(AbstractClientPlayer pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.setModelProperties(pEntity);

        PlayerModel<AbstractClientPlayer> playermodel = (PlayerModel)this.getModel();

        RenderNameTagEvent renderNameTagEvent = new RenderNameTagEvent(pEntity, pEntity.getDisplayName(), this, pPoseStack, pBuffer, pPackedLight, pPartialTicks);

        pPoseStack.pushPose();
        pPoseStack.scale(0.9375F, 0.9375F, 0.9375F);
        playermodel.attackTime = this.getAttackAnim(pEntity, pPartialTicks);
        boolean shouldSit = pEntity.isPassenger() && pEntity.getVehicle() != null && pEntity.getVehicle().shouldRiderSit();
        playermodel.riding = shouldSit;
        playermodel.young = pEntity.isBaby();
        float f = Mth.rotLerp(pPartialTicks, pEntity.yBodyRotO, pEntity.yBodyRot);
        float f1 = Mth.rotLerp(pPartialTicks, pEntity.yHeadRotO, pEntity.yHeadRot);
        float f2 = f1 - f;
        float f7;
        if (shouldSit && pEntity.getVehicle() instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)pEntity.getVehicle();
            f = Mth.rotLerp(pPartialTicks, livingentity.yBodyRotO, livingentity.yBodyRot);
            f2 = f1 - f;
            f7 = Mth.wrapDegrees(f2);
            if (f7 < -85.0F) {
                f7 = -85.0F;
            }

            if (f7 >= 85.0F) {
                f7 = 85.0F;
            }

            f = f1 - f7;
            if (f7 * f7 > 2500.0F) {
                f += f7 * 0.2F;
            }

            f2 = f1 - f;
        }

        float f6 = Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot());
        if (isEntityUpsideDown(pEntity)) {
            f6 *= -1.0F;
            f2 *= -1.0F;
        }

        float f8;
        if (pEntity.hasPose(Pose.SLEEPING)) {
            Direction direction = pEntity.getBedOrientation();
            if (direction != null) {
                f8 = pEntity.getEyeHeight(Pose.STANDING) - 0.1F;
                pPoseStack.translate((float)(-direction.getStepX()) * f8, 0.0F, (float)(-direction.getStepZ()) * f8);
            }
        }

        f7 = this.getBob(pEntity, pPartialTicks);
        this.setupRotations(pEntity, pPoseStack, f7, f, pPartialTicks);
        pPoseStack.scale(-1.0F, -1.0F, 1.0F);
        this.scale(pEntity, pPoseStack, pPartialTicks);
        pPoseStack.translate(0.0F, -1.501F, 0.0F);
        f8 = 0.0F;
        float f5 = 0.0F;
        if (!shouldSit && pEntity.isAlive()) {
            f8 = pEntity.walkAnimation.speed(pPartialTicks);
            f5 = pEntity.walkAnimation.position(pPartialTicks);
            if (pEntity.isBaby()) {
                f5 *= 3.0F;
            }

            if (f8 > 1.0F) {
                f8 = 1.0F;
            }
        }

        playermodel.prepareMobModel(pEntity, f5, f8, pPartialTicks);
        playermodel.setupAnim(pEntity, f5, f8, f7, f2, f6);
        Minecraft minecraft = Minecraft.getInstance();
        boolean flag = this.isBodyVisible(pEntity);
        boolean flag1 = !flag && !pEntity.isInvisibleTo(minecraft.player);
        boolean flag2 = minecraft.shouldEntityAppearGlowing(pEntity);

        RenderType rendertype = getRenderType(pEntity,flag,flag1,flag2);

        if (rendertype != null) {
            int i = getOverlayCoords(pEntity, this.getWhiteOverlayProgress(pEntity, pPartialTicks));

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

                int bodyType = cap.getBodytype();

                if (bodyType == 0) {
                    renderBodyType0(pEntity, pPoseStack, pBuffer, pPackedLight);
                }
                renderEyes(pEntity, pPoseStack, pBuffer, pPackedLight);

            });

        }

        if (!pEntity.isSpectator()) {
            Iterator var24 = this.layers.iterator();

            while(var24.hasNext()) {
                RenderLayer<AbstractClientPlayer, EntityModel<AbstractClientPlayer>> renderlayer = (RenderLayer)var24.next();
                renderlayer.render(pPoseStack, pBuffer, pPackedLight, pEntity, f5, f8, pPartialTicks, f7, f2, f6);
            }
        }

        pPoseStack.popPose();

        if (renderNameTagEvent.getResult() != Event.Result.DENY && (renderNameTagEvent.getResult() == Event.Result.ALLOW || this.shouldShowName(pEntity))) {
            this.renderNameTag(pEntity, renderNameTagEvent.getContent(), pPoseStack, pBuffer, pPackedLight);
        }

    }
    private void renderBodyType0(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight){

        DemonColdModel<AbstractClientPlayer> playermodel = (DemonColdModel)this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

            int bodyColor1 = cap.getBodyColor();
            int bodyColor2 = cap.getBodyColor2();
            int bodyColor3 = cap.getBodyColor3();
            int bodyColor4 = cap.getHairColor();

            //RENDERIZAR EL CUERPO ENTERO PARTE 1
            colorR = (bodyColor1 >> 16) / 255.0F;
            colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor1 & 0xff) / 255.0f;
            playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DEMON_COLD_BASE_PART1)), pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

            //RENDERIZAR EL CUERPO ENTERO PARTE 2
            colorR = (bodyColor2 >> 16) / 255.0F;
            colorG = ((bodyColor2 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor2 & 0xff) / 255.0f;
            playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DEMON_COLD_BASE_PART2)), pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

            //RENDERIZAR EL CUERPO ENTERO PARTE 3
            colorR = (bodyColor3 >> 16) / 255.0F;
            colorG = ((bodyColor3 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor3 & 0xff) / 255.0f;
            playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DEMON_COLD_BASE_PART3)), pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

            //RENDERIZAR EL CUERPO ENTERO PARTE 4
            colorR = (bodyColor4 >> 16) / 255.0F;
            colorG = ((bodyColor4 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor4 & 0xff) / 255.0f;
            playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DEMON_COLD_BASE_PART4)), pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

        });

    }

    private void renderEyes(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight){

        DemonColdModel<AbstractClientPlayer> playermodel = (DemonColdModel)this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {


            int eye1color = cap.getEye1Color();
            int eye2color = cap.getEye2Color();

            if(cap.getEyesType() == 0){

                //OJOS BLANCOS
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DEMON_COLD_EYES)),pPackedLight, OverlayTexture.NO_OVERLAY, 1.0f,1.0f,1.0f,1.0f);

                //IRIS DE OJO1 Y COLOR DE IRIS
                colorR = (eye1color >> 16) / 255.0F;
                colorG = ((eye1color >> 8) & 0xff) / 255.0f;
                colorB = (eye1color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.002f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DEMON_COLD_EYES_IRIS1)),pPackedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);

                //IRIS DE OJO2 Y COLOR DE IRIS
                colorR = (eye2color >> 16) / 255.0F;
                colorG = ((eye2color >> 8) & 0xff) / 255.0f;
                colorB = (eye2color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.002f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.DEMON_COLD_EYES_IRIS2)),pPackedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);

            }

        });
    }


    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayer abstractClientPlayer) {
        return new ResourceLocation(DragonMineZ.MOD_ID,"textures/entity/prueba.png");
    }

    private void setModelProperties(AbstractClientPlayer pClientPlayer) {
        PlayerModel<AbstractClientPlayer> playermodel = this.getModel();
        if (pClientPlayer.isSpectator()) {
            playermodel.setAllVisible(false);
            playermodel.hat.visible = true;
            playermodel.head.visible = true;
        } else {
            playermodel.setAllVisible(true);
            playermodel.hat.visible = pClientPlayer.isModelPartShown(PlayerModelPart.HAT);
            playermodel.jacket.visible = pClientPlayer.isModelPartShown(PlayerModelPart.JACKET);
            playermodel.leftPants.visible = pClientPlayer.isModelPartShown(PlayerModelPart.LEFT_PANTS_LEG);
            playermodel.rightPants.visible = pClientPlayer.isModelPartShown(PlayerModelPart.RIGHT_PANTS_LEG);
            playermodel.leftSleeve.visible = pClientPlayer.isModelPartShown(PlayerModelPart.LEFT_SLEEVE);
            playermodel.rightSleeve.visible = pClientPlayer.isModelPartShown(PlayerModelPart.RIGHT_SLEEVE);
            playermodel.crouching = pClientPlayer.isCrouching();
            HumanoidModel.ArmPose humanoidmodel$armpose = getArmPose(pClientPlayer, InteractionHand.MAIN_HAND);
            HumanoidModel.ArmPose humanoidmodel$armpose1 = getArmPose(pClientPlayer, InteractionHand.OFF_HAND);
            if (humanoidmodel$armpose.isTwoHanded()) {
                humanoidmodel$armpose1 = pClientPlayer.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
            }

            if (pClientPlayer.getMainArm() == HumanoidArm.RIGHT) {
                playermodel.rightArmPose = humanoidmodel$armpose;
                playermodel.leftArmPose = humanoidmodel$armpose1;
            } else {
                playermodel.rightArmPose = humanoidmodel$armpose1;
                playermodel.leftArmPose = humanoidmodel$armpose;
            }
        }

    }

    private static HumanoidModel.ArmPose getArmPose(AbstractClientPlayer pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.isEmpty()) {
            return HumanoidModel.ArmPose.EMPTY;
        } else {
            if (pPlayer.getUsedItemHand() == pHand && pPlayer.getUseItemRemainingTicks() > 0) {
                UseAnim useanim = itemstack.getUseAnimation();
                if (useanim == UseAnim.BLOCK) {
                    return HumanoidModel.ArmPose.BLOCK;
                }

                if (useanim == UseAnim.BOW) {
                    return HumanoidModel.ArmPose.BOW_AND_ARROW;
                }

                if (useanim == UseAnim.SPEAR) {
                    return HumanoidModel.ArmPose.THROW_SPEAR;
                }

                if (useanim == UseAnim.CROSSBOW && pHand == pPlayer.getUsedItemHand()) {
                    return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
                }

                if (useanim == UseAnim.SPYGLASS) {
                    return HumanoidModel.ArmPose.SPYGLASS;
                }

                if (useanim == UseAnim.TOOT_HORN) {
                    return HumanoidModel.ArmPose.TOOT_HORN;
                }

                if (useanim == UseAnim.BRUSH) {
                    return HumanoidModel.ArmPose.BRUSH;
                }
            } else if (!pPlayer.swinging && itemstack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(itemstack)) {
                return HumanoidModel.ArmPose.CROSSBOW_HOLD;
            }

            HumanoidModel.ArmPose forgeArmPose = IClientItemExtensions.of(itemstack).getArmPose(pPlayer, pHand, itemstack);
            return forgeArmPose != null ? forgeArmPose : HumanoidModel.ArmPose.ITEM;
        }
    }

    @Override
    protected void setupRotations(AbstractClientPlayer pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        float f = pEntityLiving.getSwimAmount(pPartialTicks);
        float f3;
        float f2;
        if (pEntityLiving.isFallFlying()) {
            super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
            f3 = (float)pEntityLiving.getFallFlyingTicks() + pPartialTicks;
            f2 = Mth.clamp(f3 * f3 / 100.0F, 0.0F, 1.0F);
            if (!pEntityLiving.isAutoSpinAttack()) {
                pPoseStack.mulPose(Axis.XP.rotationDegrees(f2 * (-90.0F - pEntityLiving.getXRot())));
            }

            Vec3 vec3 = pEntityLiving.getViewVector(pPartialTicks);
            Vec3 vec31 = pEntityLiving.getDeltaMovementLerped(pPartialTicks);
            double d0 = vec31.horizontalDistanceSqr();
            double d1 = vec3.horizontalDistanceSqr();
            if (d0 > 0.0 && d1 > 0.0) {
                double d2 = (vec31.x * vec3.x + vec31.z * vec3.z) / Math.sqrt(d0 * d1);
                double d3 = vec31.x * vec3.z - vec31.z * vec3.x;
                pPoseStack.mulPose(Axis.YP.rotation((float)(Math.signum(d3) * Math.acos(d2))));
            }
        } else if (f > 0.0F) {
            super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
            f3 = !pEntityLiving.isInWater() && !pEntityLiving.isInFluidType((fluidType, height) -> {
                return pEntityLiving.canSwimInFluidType(fluidType);
            }) ? -90.0F : -90.0F - pEntityLiving.getXRot();
            f2 = Mth.lerp(f, 0.0F, f3);
            pPoseStack.mulPose(Axis.XP.rotationDegrees(f2));
            if (pEntityLiving.isVisuallySwimming()) {
                pPoseStack.translate(0.0F, -1.0F, 0.3F);
            }
        } else {
            super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        }
    }



}
