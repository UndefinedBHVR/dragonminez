package com.yuseix.dragonminez.character.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.LayerDMZBase;
import com.yuseix.dragonminez.character.models.ModeloBrazos;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class RenderBrazos extends LivingEntityRenderer<AbstractClientPlayer, ModeloBrazos<AbstractClientPlayer>> {

    //BIOANDROIDE
    private static final ResourceLocation B_BODY1 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodybase1.png");
    private static final ResourceLocation B_BODY2 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodybase2.png");
    private static final ResourceLocation B_BODY3 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodybase3.png");



    public RenderBrazos(EntityRendererProvider.Context pContext) {

        super(pContext, new ModeloBrazos<>(pContext.bakeLayer(ModeloBrazos.LAYER_LOCATION)), 0.5f);

        this.addLayer(new PlayerItemInHandLayer(this, pContext.getItemInHandRenderer()));
        this.addLayer(new LayerDMZBase(this));
    }




    @Override
    public void render(@NotNull AbstractClientPlayer pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        this.setModelProperties(pEntity);
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

    }

    private static HumanoidModel.ArmPose getArmPose(@NotNull AbstractClientPlayer pPlayer, InteractionHand pHand) {
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
    public @NotNull ResourceLocation getTextureLocation(@NotNull AbstractClientPlayer abstractClientPlayer) {
        return abstractClientPlayer.getSkinTextureLocation();
    }



    public void renderRightHand(PoseStack pPoseStack, MultiBufferSource pBuffer, int pCombinedLight, AbstractClientPlayer pPlayer) {
            this.renderHand(pPoseStack, pBuffer, pCombinedLight, pPlayer, ((PlayerModel<?>)this.model).rightArm, ((PlayerModel)this.model).rightSleeve);

    }

    public void renderLeftHand(PoseStack pPoseStack, MultiBufferSource pBuffer, int pCombinedLight, AbstractClientPlayer pPlayer) {
            this.renderHand(pPoseStack, pBuffer, pCombinedLight, pPlayer, ((PlayerModel<?>)this.model).leftArm, ((PlayerModel)this.model).leftSleeve);


    }

    private void renderHand(PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pCombinedLight, AbstractClientPlayer pPlayer, @NotNull ModelPart pRendererArm, ModelPart pRendererArmwear) {

        PlayerModel<AbstractClientPlayer> playermodel = (PlayerModel)this.getModel();
        this.setModelProperties(pPlayer);
        playermodel.attackTime = 0.0F;
        playermodel.crouching = false;
        playermodel.swimAmount = 0.0F;
        playermodel.setupAnim(pPlayer, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);

        pRendererArm.xRot = 0.0F;
        VertexConsumer skin_type1 = pBuffer.getBuffer(RenderType.entityCutoutNoCull(B_BODY1));
        pRendererArm.render(pPoseStack, skin_type1, pCombinedLight, OverlayTexture.NO_OVERLAY, 0.250f, 0.232f, 0.235f, 1.0f);
        VertexConsumer skin_type2 = pBuffer.getBuffer(RenderType.entityCutoutNoCull(B_BODY2));
        pRendererArm.render(pPoseStack, skin_type2, pCombinedLight, OverlayTexture.NO_OVERLAY, 0.920f, 0.920f, 0.920f, 1.0f);
        VertexConsumer skin_type3 = pBuffer.getBuffer(RenderType.entityCutoutNoCull(B_BODY3));
        pRendererArm.render(pPoseStack, skin_type3, pCombinedLight, OverlayTexture.NO_OVERLAY, 0.760f, 0.760f, 0.760f, 1.0f);

    }

    private void setModelProperties(@NotNull AbstractClientPlayer pClientPlayer) {
        PlayerModel<AbstractClientPlayer> playermodel = (PlayerModel)this.getModel();
        if (pClientPlayer.isSpectator()) {
            playermodel.setAllVisible(false);
            playermodel.head.visible = true;
            playermodel.hat.visible = true;
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

}
