package com.yuseix.dragonminez.character;


import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.character.renders.GeoBioAndroidRender;
import net.minecraft.client.model.PlayerModel;
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
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.util.RenderUtils;

import java.util.Optional;

public class GeoPlayerItemInHandLayer<T extends AbstractClientPlayer & GeoAnimatable> extends GeoItemInHandLayer<T>{
    private final ItemInHandRenderer itemInHandRenderer;
    private static final float X_ROT_MIN = (-(float)Math.PI / 6F);
    private static final float X_ROT_MAX = ((float)Math.PI / 2F);

    public GeoPlayerItemInHandLayer(GeoRenderer<T> entityRendererIn, ItemInHandRenderer p_234847_) {
        super(entityRendererIn, p_234847_);
        this.itemInHandRenderer = p_234847_;
    }

    protected void renderArmWithItem(LivingEntity p_270884_, ItemStack p_270379_, ItemDisplayContext p_270607_, HumanoidArm p_270324_, PoseStack p_270124_, MultiBufferSource p_270414_, int p_270295_) {
        if (p_270379_.is(Items.SPYGLASS) && p_270884_.getUseItem() == p_270379_ && p_270884_.swingTime == 0) {
            this.renderArmWithSpyglass(p_270884_, p_270379_, p_270324_, p_270124_, p_270414_, p_270295_);
        } else {
            super.renderArmWithItem(p_270884_, p_270379_, p_270607_, p_270324_, p_270124_, p_270414_, p_270295_);
        }

    }

    private void renderArmWithSpyglass(LivingEntity p_174518_, ItemStack p_174519_, HumanoidArm p_174520_, PoseStack p_174521_, MultiBufferSource p_174522_, int p_174523_) {
        p_174521_.pushPose();
        var m = ((PlayerModel<T>) this.renderer);
        var wa = this.getRenderer();

        float f = m.head.xRot;
        m.head.xRot = (Mth.clamp(m.head.xRot, (-(float)Math.PI / 6F), ((float)Math.PI / 2F)));

        RenderUtils.prepMatrixForBone(p_174521_, wa.getGeoModel().getBone("head").get());

        m.head.xRot = f;
        CustomHeadLayer.translateToHead(p_174521_, false);
        boolean flag = p_174520_ == HumanoidArm.LEFT;
        p_174521_.translate((flag ? 2F : -2F) / 16.0F, -3.0625F, 0.0F);
        this.itemInHandRenderer.renderItem(p_174518_, p_174519_, ItemDisplayContext.HEAD, false, p_174521_, p_174522_, p_174523_);
        p_174521_.popPose();
    }
}