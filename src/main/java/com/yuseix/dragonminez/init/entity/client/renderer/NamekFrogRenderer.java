package com.yuseix.dragonminez.init.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.NamekFrogModel;
import com.yuseix.dragonminez.init.entity.custom.NamekFrogEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NamekFrogRenderer extends GeoEntityRenderer<NamekFrogEntity> {
    public NamekFrogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NamekFrogModel());
    }

    @Override
    public ResourceLocation getTextureLocation(NamekFrogEntity animatable) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/ranas/ranadefault.png");
    }

    @Override
    public void render(NamekFrogEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        poseStack.pushPose();

        poseStack.scale(1.5f, 1.5f, 1.5f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

        poseStack.popPose();
    }
}
