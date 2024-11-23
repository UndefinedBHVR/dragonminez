package com.yuseix.dragonminez.init.entity.client.renderer.fpcharacters;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.AuraModel;
import com.yuseix.dragonminez.init.entity.custom.fpcharacters.AuraEntity;
import com.yuseix.dragonminez.init.entity.custom.fpcharacters.FPBase;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.TextureManager;
import com.yuseix.dragonminez.utils.shaders.CustomRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class AuraRenderer extends LivingEntityRenderer<AuraEntity, AuraModel<AuraEntity>> {

    private float colorR, colorG, colorB;
    private final AuraModel model;

    public AuraRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AuraModel<>(pContext.bakeLayer(AuraModel.LAYER_LOCATION)), 0.0f);

        this.model = new AuraModel<>(pContext.bakeLayer(AuraModel.LAYER_LOCATION)); // Cargamos el modelo

    }

    @Override
    public ResourceLocation getTextureLocation(AuraEntity auraEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID,"textures/entity/prueba.png");
    }

    @Override
    public void render(AuraEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        var playermodel = this.getModel();

        pPoseStack.pushPose();

        switch (pEntity.getRaza()){
            case 0: //Humano
                if(pEntity.getTransformation() == 0){
                    pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano defecto de jugador
                } else {
                    pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano defecto de jugador
                }
                break;
            case 1: //Saiyan
                if(pEntity.getTransformation() == 0){
                    pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano defecto de jugador
                } else {
                    pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano defecto de jugador
                }
                break;
            case 2: //Namekiano
                if(pEntity.getTransformation() == 0){
                    pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano defecto de jugador
                } else {
                    pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano defecto de jugador
                }
                break;
            case 3: //BioAndroide
                if(pEntity.getTransformation() == 0){
                    pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano defecto de jugador
                } else {
                    pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano defecto de jugador
                }
                break;
            case 4: //Demonio del frio
                if(pEntity.getTransformation() == 0){
                    pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano defecto de jugador
                } else {
                    pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano defecto de jugador
                }
                break;
            case 5: //Majin
                if(pEntity.getTransformation() == 0){
                    pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano defecto de jugador
                } else {
                    pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano defecto de jugador
                }
                break;
        }



        playermodel.attackTime = this.getAttackAnim(pEntity, pPartialTicks);
        boolean shouldSit = pEntity.isPassenger() && pEntity.getVehicle() != null && pEntity.getVehicle().shouldRiderSit();
        playermodel.riding = shouldSit;
        playermodel.young = pEntity.isBaby();
        float f = Mth.rotLerp(pPartialTicks, pEntity.yBodyRotO, pEntity.yBodyRot);
        float f1 = Mth.rotLerp(pPartialTicks, pEntity.yHeadRotO, pEntity.yHeadRot);
        float f2 = f1 - f;
        float f7;
        if (shouldSit && pEntity.getVehicle() instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)pEntity.getVehicle();
            f = Mth.rotLerp(pPartialTicks, livingentity.yBodyRotO, livingentity.yBodyRot);
            f2 = f1 - f;
            f7 = Mth.wrapDegrees(f2);
            if (f7 < -85.0F) {
                f7 = -85.0F;
            }

            if (f7 >= 85.0F) {
                f7 = 85.0F;
            }

            f = f1 - f7;
            if (f7 * f7 > 2500.0F) {
                f += f7 * 0.2F;
            }

            f2 = f1 - f;
        }

        float f6 = Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot());
        if (isEntityUpsideDown(pEntity)) {
            f6 *= -1.0F;
            f2 *= -1.0F;
        }

        float f8;
        if (pEntity.hasPose(Pose.SLEEPING)) {
            Direction direction = pEntity.getBedOrientation();
            if (direction != null) {
                f8 = pEntity.getEyeHeight(Pose.STANDING) - 0.1F;
                pPoseStack.translate((float)(-direction.getStepX()) * f8, 0.0F, (float)(-direction.getStepZ()) * f8);
            }
        }

        f7 = this.getBob(pEntity, pPartialTicks);
        this.setupRotations(pEntity, pPoseStack, f7, f, pPartialTicks);
        pPoseStack.scale(-1.0F, -1.0F, 1.0F);
        this.scale(pEntity, pPoseStack, pPartialTicks);
        pPoseStack.translate(0.0F, -1.501F, 0.0F);
        f8 = 0.0F;
        float f5 = 0.0F;
        if (!shouldSit && pEntity.isAlive()) {
            f8 = pEntity.walkAnimation.speed(pPartialTicks);
            f5 = pEntity.walkAnimation.position(pPartialTicks);
            if (pEntity.isBaby()) {
                f5 *= 3.0F;
            }

            if (f8 > 1.0F) {
                f8 = 1.0F;
            }
        }

        playermodel.prepareMobModel(pEntity, f5, f8, pPartialTicks);
        playermodel.setupAnim(pEntity, f5, f8, Minecraft.getInstance().player.tickCount + pPartialTicks, f2, f6);
        Minecraft minecraft = Minecraft.getInstance();
        boolean flag = this.isBodyVisible(pEntity);
        boolean flag1 = !flag && !pEntity.isInvisibleTo(minecraft.player);
        boolean flag2 = minecraft.shouldEntityAppearGlowing(pEntity);

        RenderType rendertype = getRenderType(pEntity,flag,flag1,flag2);

        if (rendertype != null) {
            int i = getOverlayCoords(pEntity, this.getWhiteOverlayProgress(pEntity, pPartialTicks));

            var color = pEntity.getColorAura();
            var transparency = pEntity.getTransparencia();
            var tipoAura = pEntity.getTipoAura();

            if(tipoAura == 0){ //Ponemos el tipo de Aura por defecto
                pPoseStack.pushPose();
                pPoseStack.translate(0.0f,-0.2f,0.0f);
                renderAuraBase(pEntity, pPoseStack, pBuffer, pPackedLight, pPartialTicks, transparency, color);
                pPoseStack.popPose();

            } else { //En Caso de existir otro lo pondriamos aca

            }

        }

        if (!pEntity.isSpectator()) {
            Iterator var24 = this.layers.iterator();

            while(var24.hasNext()) {
                RenderLayer<AbstractClientPlayer, EntityModel<AbstractClientPlayer>> renderlayer = (RenderLayer)var24.next();
                renderlayer.render(pPoseStack, pBuffer, pPackedLight, Minecraft.getInstance().player, f5, f8, pPartialTicks, f7, f2, f6);
            }
        }

        pPoseStack.popPose();
    }

    private void renderAuraBase(AuraEntity pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, float pPartialTicks, float transparencia, int colorAura){

        colorR = (colorAura >> 16) / 255.0F;
        colorG = ((colorAura >> 8) & 0xff) / 255.0f;
        colorB = (colorAura & 0xff) / 255.0f;

        float rotationAngle = 0.0F;
        rotationAngle = (pEntity.tickCount + pPartialTicks) * 7.0F; // Ajusta la velocidad aquí

        float rotationAngle2 = 0.0F;
        rotationAngle2 = (pEntity.tickCount + pPartialTicks) * -7.0F; // Ajusta la velocidad aquí

        VertexConsumer vertexConsumer = pBuffer.getBuffer(CustomRenderTypes.energy(TextureManager.AURA_BASE));

        // PARTE BAJA 1
        for (int i = 0; i < 8; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.9F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(40));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.5D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        for (int i = 0; i < 8; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.9F, 1.2F);

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
        for (int i = 0; i < 8; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.9F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(40));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.5D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
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
        for (int i = 0; i < 8; i++) {
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.4F, 1.9F, 1.4F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(40));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.5D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
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
        for (int i = 0; i < 10; i++) {
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.7F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(0));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -0.6D, -0.2D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
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
            pPoseStack.mulPose(Axis.XP.rotationDegrees(25f));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.1D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //parte medio 2 int
        for (int i = 0; i < 10; i++) {
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.7F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-15f));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.1D);

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
        for (int i = 0; i < 10; i++) {
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.9F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(15f));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.6D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
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
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.6F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(25F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -0.8D, -0.4D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
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
            pPoseStack.scale(1.2F, 1.8F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-15F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.4D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.8F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-15F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.4D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.8F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-15F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.0D, -0.4D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        //PARTE ARRIBA 4 interior
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.9F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(5F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.3D, -0.38D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.9F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(5F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.3D, -0.38D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }
        for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 1.9F, 1.2F);

            // Rotar cada plano un poco más en Y y X
            pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle+i*45F));  // Cambia 30F por el ángulo que desees
            pPoseStack.mulPose(Axis.XP.rotationDegrees(5F));

            // Posicionar el aura un poco más arriba o abajo
            pPoseStack.translate(0.0D, -1.3D, -0.38D);

            // Renderizar cada plano
            model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, transparencia);
            RenderSystem.disableBlend();

            pPoseStack.popPose();
        }



    }

    private void renderAuraBase2(AuraEntity pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, float pPartialTicks, float transparencia, int colorAura){

        colorR = (colorAura >> 16) / 255.0F;
        colorG = ((colorAura >> 8) & 0xff) / 255.0f;
        colorB = (colorAura & 0xff) / 255.0f;

        float rotationAngle = 0.0F;
        rotationAngle = (pEntity.tickCount + pPartialTicks) * 5.0F; // Ajusta la velocidad aquí

        boolean useAlternateTexture = ((pEntity.tickCount + pPartialTicks) / 20.0F) % 2 < 1;


        VertexConsumer vertexConsumer = pBuffer.getBuffer(CustomRenderTypes.energy2(
                useAlternateTexture ? TextureManager.AURA_BASE2 : TextureManager.AURA_BASE));

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
        for (int i = 0; i < 10; i++) {
            pPoseStack.pushPose();
            RenderSystem.enableBlend();
            pPoseStack.scale(1.2F, 3.0F, 1.2F);

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









    }

    @Override
    protected void renderNameTag(AuraEntity pEntity, Component pDisplayName, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
    }
}
