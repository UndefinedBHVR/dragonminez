package com.yuseix.dragonminez.character.layer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.AuraModel;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.TextureManager;
import com.yuseix.dragonminez.utils.shaders.CustomRenderTypes;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class AuraLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private AuraModel model;
    private float colorR, colorG, colorB;

    public AuraLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);

        this.model = new AuraModel(AuraModel.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, AbstractClientPlayer abstractClientPlayer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, abstractClientPlayer).ifPresent(cap -> {

            int auraColor = cap.getAuraColor();
            float transparency = 0.15f;  // Ajusta el nivel de transparencia de la aura

            if (cap.isAuraOn()) {
                renderAuraBase(abstractClientPlayer, poseStack, multiBufferSource, light, partialTicks, transparency, auraColor);

            }

        });

    }

    private void renderAuraBase(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, float pPartialTicks, float transparencia, int colorAura){

        colorR = (colorAura >> 16) / 255.0F;
        colorG = ((colorAura >> 8) & 0xff) / 255.0f;
        colorB = (colorAura & 0xff) / 255.0f;

        float rotationAngle = 0.0F;
        rotationAngle = (pEntity.tickCount + pPartialTicks) * 5.0F; // Ajusta la velocidad aquí


        float rotationAngle2 = 0.0F;
        rotationAngle2 = (pEntity.tickCount + pPartialTicks) * -5.0F; // Ajusta la velocidad aquí

        VertexConsumer vertexConsumer = pBuffer.getBuffer(CustomRenderTypes.energy3(TextureManager.AURA_BASE3));



    }

}
