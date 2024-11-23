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

        VertexConsumer vertexConsumer = pBuffer.getBuffer(CustomRenderTypes.energy(TextureManager.AURA_BASE3));

        // PARTE BAJA 1
        for (int i = 0; i < 8; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.7F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(40));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.7D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //Minecraft.getInstance().gameRenderer.shutdownEffect();



        float rotationAngle2 = 0.0F;
        rotationAngle2 = (pEntity.tickCount + pPartialTicks) * -5.0F; // Ajusta la velocidad aquí

        //PARTE BAJA 2
        for (int i = 0; i < 8; i++) {
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.4F, 1.9F, 1.4F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(40));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.5D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //PARTE MEDIO 1 interior
        for (int i = 0; i < 10; i++) {
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.7F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(0));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -0.6D, -0.2D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //parte medio 2 exterior
        for (int i = 0; i < 10; i++) {
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.7F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(15f));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.4D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //parte medio 3 exterior
        for (int i = 0; i < 10; i++) {
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.9F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(15f));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.6D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //PARTE ARRIBA 1 interior
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.6F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-35F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.1D, -0.38D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //Parte 2 arriba exterior
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.6F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(25F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -0.8D, -0.4D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //Parte 3 arriba exterior
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.6F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-15F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.2D, -0.4D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //PARTE ARRIBA 4 interior
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.6F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(5F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.5D, -0.38D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }


        //PARTE PARA QUE NO SE VEAN LOS RENDERS DE OTRAS ENTIDADES


        vertexConsumer = pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.AURA_BASE));
        var transparencia2 = 0.01f;

        //PARTE BAJA 2
        for (int i = 0; i < 8; i++) {
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.4F, 1.9F, 1.4F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(40));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.5D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia2);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //PARTE MEDIO 1 interior
        for (int i = 0; i < 10; i++) {
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.7F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(0));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -0.6D, -0.2D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia2);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //parte medio 2 exterior
        for (int i = 0; i < 10; i++) {
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.7F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(15f));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.4D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia2);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //parte medio 3 exterior
        for (int i = 0; i < 10; i++) {
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.9F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(15f));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.6D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia2);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //PARTE ARRIBA 1 interior
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.6F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-35F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.1D, -0.38D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia2);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //Parte 2 arriba exterior
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.6F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(25F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -0.8D, -0.4D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia2);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //Parte 3 arriba exterior
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.6F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-15F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.2D, -0.4D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia2);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //PARTE ARRIBA 4 interior
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.6F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(5F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.5D, -0.38D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia2);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }





    }

}
