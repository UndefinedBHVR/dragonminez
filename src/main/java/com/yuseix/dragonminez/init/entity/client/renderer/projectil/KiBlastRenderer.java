package com.yuseix.dragonminez.init.entity.client.renderer.projectil;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.projectil.KiBlastModel;
import com.yuseix.dragonminez.init.entity.custom.projectil.KiBlastProyectil;
import com.yuseix.dragonminez.utils.shaders.CustomRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class KiBlastRenderer extends EntityRenderer<KiBlastProyectil> {

    private final KiBlastModel<KiBlastProyectil> model;

    public KiBlastRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new KiBlastModel<>(pContext.bakeLayer(KiBlastModel.LAYER_LOCATION));

    }

    @Override
    public void render(KiBlastProyectil pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        // Escala de la entidad
        pPoseStack.pushPose();

        pPoseStack.scale(5.0f,5.0f,5.0f);


        // Ajusta la rotación para que apunte en la dirección de movimiento
        //pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        //pPoseStack.mulPose(Axis.XP.rotationDegrees(180));

        VertexConsumer outlineConsumer = pBuffer.getBuffer(CustomRenderTypes.energy(this.getTextureLocation(pEntity)));

        pPoseStack.pushPose();
        pPoseStack.scale(1.5f, 1.5f, 1.5f); // Cambia el tamaño aquí si deseas escalar el modelo
        pPoseStack.translate(0.0, -0.35, 0.0); // Ajuste en el eje Z para acercar el borde al modelo

        float rotationAngle = (pEntity.tickCount + pPartialTick) * 150; // Ajusta el multiplicador para cambiar la velocidad
        pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle));

        this.model.renderToBuffer(pPoseStack, outlineConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 0.960f, 0.990f, 0.0990f, 0.7F); // Cambia el color del borde aquí
        pPoseStack.popPose();

        // Renderizar el modelo principal
        VertexConsumer mainConsumer = pBuffer.getBuffer(CustomRenderTypes.glow(this.getTextureLocation(pEntity)));
        pPoseStack.translate(-0.01, 0.02, 0.0); // Ajuste en el eje Z para acercar el borde al modelo

        float rotationAngle2 = (pEntity.tickCount + pPartialTick) * 150; // Ajusta el multiplicador para cambiar la velocidad
        pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2));

        this.model.renderToBuffer(pPoseStack, mainConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 0.0485f, 0.0500f, 0.00350f, 1.0f);

        pPoseStack.popPose();
        //super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(KiBlastProyectil kiBlastProyectil) {
        return new ResourceLocation(DragonMineZ.MOD_ID,"textures/entity/ki.png");
    }
}
