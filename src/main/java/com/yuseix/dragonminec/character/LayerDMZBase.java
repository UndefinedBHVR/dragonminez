package com.yuseix.dragonminec.character;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminec.events.ModEvents;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerDMZBase extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public LayerDMZBase(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer abstractClientPlayer, float v, float v1, float v2, float v3, float v4, float v5) {

        VertexConsumer vertexBuilder = multiBufferSource.getBuffer(RenderType.entityTranslucent());

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,abstractClientPlayer).ifPresent(cap -> {

            int raza = cap.getRace();

            this.getParentModel().head.render(poseStack, vertexBuilder, i, );


        });

    }
}
