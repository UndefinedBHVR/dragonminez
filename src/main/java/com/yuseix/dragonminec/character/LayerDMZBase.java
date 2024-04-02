package com.yuseix.dragonminec.character;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.events.ModEvents;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.client.model.CatModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerDMZBase extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private static final ResourceLocation OJOS = new ResourceLocation(DragonMineC.MODID, "textures/races/ojos.png");


    public LayerDMZBase(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer abstractClientPlayer, float v, float v1, float v2, float v3, float v4, float v5) {

        VertexConsumer vertexBuilder = multiBufferSource.getBuffer(RenderType.entityTranslucent(OJOS));

            this.getParentModel().head.render(poseStack, vertexBuilder, i, OverlayTexture.NO_OVERLAY);
            this.getParentModel().body.render(poseStack, vertexBuilder, i, OverlayTexture.NO_OVERLAY, 1.0f, 5.0f, 8.0f, 0.5f);
            this.getParentModel().leftArm.render(poseStack, vertexBuilder, i, OverlayTexture.NO_OVERLAY);
            this.getParentModel().leftLeg.render(poseStack, vertexBuilder, i, OverlayTexture.NO_OVERLAY);
            this.getParentModel().rightArm.render(poseStack, vertexBuilder, i, OverlayTexture.NO_OVERLAY);
            this.getParentModel().rightLeg.render(poseStack, vertexBuilder, i, OverlayTexture.NO_OVERLAY);

    }
}
