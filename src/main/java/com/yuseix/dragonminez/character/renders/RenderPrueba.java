package com.yuseix.dragonminez.character.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.character.models.ModeloPrueba;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

@OnlyIn(Dist.CLIENT)
public class RenderPrueba extends LivingEntityRenderer<AbstractClientPlayer, ModeloPrueba<AbstractClientPlayer>> {

    public static RenderPrueba INSTANCE = null;

    public RenderPrueba(EntityRendererProvider.Context pContext) {
        super(pContext, new ModeloPrueba<>(pContext.bakeLayer(ModeloPrueba.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public void render(AbstractClientPlayer pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
        this.setModelProperties(pEntity);
    }

    private void setModelProperties(AbstractClientPlayer pClientPlayer) {
        ModeloPrueba<AbstractClientPlayer> playermodel = (ModeloPrueba) this.getModel();
        if (pClientPlayer.isSpectator()) {
            playermodel.setAllVisible(false);
            playermodel.head.visible = true;
        } else {
            playermodel.setAllVisible(true);
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
    public ResourceLocation getTextureLocation(AbstractClientPlayer abstractClientPlayer) {
        return abstractClientPlayer.getSkinTextureLocation();
    }
}
