package com.yuseix.dragonminez.character;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;
import software.bernie.geckolib.util.RenderUtils;

import java.util.Optional;

public class GeoItemInHandLayer<T extends AbstractClientPlayer & GeoAnimatable> extends GeoRenderLayer<T> {
    private final ItemInHandRenderer itemInHandRenderer;

    public GeoItemInHandLayer(GeoRenderer<T> entityRendererIn, ItemInHandRenderer itemInHandRenderer) {
        super(entityRendererIn);
        this.itemInHandRenderer = itemInHandRenderer;
    }

    @Override
    public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        boolean isRightHanded = animatable.getMainArm() == HumanoidArm.RIGHT;
        ItemStack offhandItem = isRightHanded ? animatable.getOffhandItem() : animatable.getMainHandItem();
        ItemStack mainhandItem = isRightHanded ? animatable.getMainHandItem() : animatable.getOffhandItem();

        if (!offhandItem.isEmpty() || !mainhandItem.isEmpty()) {
            poseStack.pushPose();

            this.renderArmWithItem(animatable, mainhandItem, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, poseStack, bufferSource, packedLight);
            this.renderArmWithItem(animatable, offhandItem, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, poseStack, bufferSource, packedLight);
            poseStack.popPose();
        }
    }

    protected void renderArmWithItem(LivingEntity entity, ItemStack stack, ItemDisplayContext context, HumanoidArm arm, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (!stack.isEmpty()) {
            poseStack.pushPose();
            GeoRenderer<T> renderer = this.getRenderer();

            Optional<GeoBone> armBoneOptional = renderer.getGeoModel().getBone(arm == HumanoidArm.LEFT ? "leftArm" : "rightArm");
            if (armBoneOptional.isPresent()) {
                GeoBone armBone = armBoneOptional.get();
                RenderUtils.prepMatrixForBone(poseStack, armBone);
            }

            boolean isLeftHand = arm == HumanoidArm.LEFT;
            poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
            poseStack.translate((isLeftHand ? -0.37F : 0.37F), 0.125F, 0.78F);

            this.itemInHandRenderer.renderItem(entity, stack, context, isLeftHand, poseStack, bufferSource, packedLight);
            poseStack.popPose();
        }
    }
}