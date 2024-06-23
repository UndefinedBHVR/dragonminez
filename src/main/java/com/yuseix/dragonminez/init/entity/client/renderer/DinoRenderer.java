package com.yuseix.dragonminez.init.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.DinoModel;
import com.yuseix.dragonminez.init.entity.custom.DinoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DinoRenderer extends GeoEntityRenderer<DinoEntity> {
    public DinoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DinoModel());
    }

    @Override
    public ResourceLocation getTextureLocation(DinoEntity animatable) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/dinos/dino1.png");
    }

    @Override
    public void render(DinoEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        poseStack.pushPose();

        poseStack.scale(1.5f, 1.5f, 1.5f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

        poseStack.popPose();
    }
}
