package com.yuseix.dragonminez.character;


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.util.RenderUtils;

import java.util.Optional;

public class GeoPlayerItemInHandLayer<T extends AbstractClientPlayer & GeoAnimatable> extends GeoItemInHandLayer<T> {
    private final ItemInHandRenderer itemInHandRenderer;
    private static final float X_ROT_MIN = (-(float) Math.PI / 6F);
    private static final float X_ROT_MAX = ((float) Math.PI / 2F);

    public GeoPlayerItemInHandLayer(GeoRenderer<T> entityRendererIn, ItemInHandRenderer itemInHandRenderer) {
        super(entityRendererIn, itemInHandRenderer);
        this.itemInHandRenderer = itemInHandRenderer;
    }

    @Override
    protected void renderArmWithItem(LivingEntity entity, ItemStack stack, ItemDisplayContext context, HumanoidArm arm, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (stack.is(Items.SPYGLASS) && entity.getUseItem() == stack && entity.swingTime == 0) {
            this.renderArmWithSpyglass(entity, stack, arm, poseStack, bufferSource, packedLight);
        } else {
            super.renderArmWithItem(entity, stack, context, arm, poseStack, bufferSource, packedLight);
        }
    }

    private void renderArmWithSpyglass(LivingEntity entity, ItemStack stack, HumanoidArm arm, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        GeoRenderer<T> renderer = this.getRenderer();

        Optional<GeoBone> headBoneOptional = renderer.getGeoModel().getBone("head");
        if (headBoneOptional.isPresent()) {
            GeoBone headBone = headBoneOptional.get();
            float originalRotX = headBone.getRotX();
            headBone.setRotX(Mth.clamp(originalRotX, X_ROT_MIN, X_ROT_MAX));
            RenderUtils.prepMatrixForBone(poseStack, headBone);
            headBone.setRotX(originalRotX);
        }

        CustomHeadLayer.translateToHead(poseStack, false);
        boolean isLeftArm = arm == HumanoidArm.LEFT;
        poseStack.translate((isLeftArm ? 2F : -2F) / 16.0F, -3.0625F, 0.0F);
        this.itemInHandRenderer.renderItem(entity, stack, ItemDisplayContext.HEAD, false, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }
}