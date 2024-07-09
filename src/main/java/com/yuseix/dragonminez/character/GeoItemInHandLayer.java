package com.yuseix.dragonminez.character;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.character.renders.GeoBioAndroidRender;
import net.minecraft.client.model.PlayerModel;
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

public class GeoItemInHandLayer<T extends AbstractClientPlayer&GeoAnimatable> extends GeoRenderLayer<T> {
    private final ItemInHandRenderer itemInHandRenderer;

    public GeoItemInHandLayer(GeoRenderer<T> entityRendererIn, ItemInHandRenderer p_234847_) {
        super(entityRendererIn);
        this.itemInHandRenderer = p_234847_;
    }

    @Override
    public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        boolean flag = animatable.getMainArm() == HumanoidArm.RIGHT;
        ItemStack itemstack = flag ? animatable.getOffhandItem() : animatable.getMainHandItem();
        ItemStack itemstack1 = flag ? animatable.getMainHandItem() : animatable.getOffhandItem();
        if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
            poseStack.pushPose();

            this.renderArmWithItem(animatable, itemstack1, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, poseStack, bufferSource, packedLight);
            this.renderArmWithItem(animatable, itemstack, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, poseStack, bufferSource, packedLight);
            poseStack.popPose();
        }  }

    protected void renderArmWithItem(LivingEntity p_117185_, ItemStack p_117186_, ItemDisplayContext p_270970_, HumanoidArm p_117188_, PoseStack p_117189_, MultiBufferSource p_117190_, int p_117191_) {
        if (!p_117186_.isEmpty()) {
            p_117189_.pushPose();
            //var a = ((PlayerModel<T>)renderer);
            //var wa = ((GeoBioAndroidRender<T>)renderer);
            var wa = this.getRenderer();

            RenderUtils.prepMatrixForBone(p_117189_, p_117188_.getId() == 0 ? wa.getGeoModel().getBone("left_arm").get() : wa.getGeoModel().getBone("right_arm").get());


            var flag = p_117188_ == HumanoidArm.LEFT;
            p_117189_.mulPose(Axis.XP.rotationDegrees(-90.0F));
            p_117189_.translate((float)(flag ? -0.37F : 0.37f), 0.125F, 0.78F);

            this.itemInHandRenderer.renderItem(p_117185_, p_117186_, p_270970_, flag, p_117189_, p_117190_, p_117191_);
            p_117189_.popPose();
        }
    }
}