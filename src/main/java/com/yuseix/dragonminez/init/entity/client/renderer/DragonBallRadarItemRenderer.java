package com.yuseix.dragonminez.init.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class DragonBallRadarItemRenderer extends ItemRenderer {

    public DragonBallRadarItemRenderer() {
        super(Minecraft.getInstance(), Minecraft.getInstance().getTextureManager(), Minecraft.getInstance().getModelManager(), Minecraft.getInstance().getItemColors(), Minecraft.getInstance().getItemRenderer().getBlockEntityRenderer());
    }

    @Override
    public void render(ItemStack pItemStack, ItemDisplayContext pDisplayContext, boolean pLeftHand, PoseStack pPoseStack, MultiBufferSource pBuffer, int pCombinedLight, int pCombinedOverlay, BakedModel pModel) {
        // Draw the base item (the radar)
        super.render(pItemStack, pDisplayContext, pLeftHand, pPoseStack, pBuffer, pCombinedLight, pCombinedOverlay, pModel);

        // Draw the player at the center of the radar
        drawSquare(pPoseStack, pBuffer, 0, 0, 1.0F, 0.0F, 0.0F); // Red square for the player

        // Get the dragon ball positions
        List<Vec3> dragonBallPositions = getDragonBallPositions();

        // Draw a square at each dragon ball position
        for (Vec3 pos : dragonBallPositions) {
            final Minecraft minecraft = Minecraft.getInstance();
            Vec3 screenPos = minecraft.gameRenderer.getMainCamera().getPosition();
            drawSquare(pPoseStack, pBuffer, screenPos.x, screenPos.y, 1.0F, 1.0F, 0.0F); // Yellow square for the dragon balls
        }
    }

    private List<Vec3> getDragonBallPositions() {
        // TODO: Implement this method to return the positions of the dragon balls
        return new ArrayList<>();
    }

    private void drawSquare(PoseStack poseStack, MultiBufferSource bufferSource, double x, double y, float r, float g, float b) {
        // TODO: Implement this method to draw a square at the given screen coordinates with the given color
        VertexConsumer builder = bufferSource.getBuffer(RenderType.lines());
        poseStack.pushPose();
        poseStack.translate(x, y, 0);
        builder.vertex(poseStack.last().pose(), 0, 0, 0).color(r, g, b, 1.0F).uv2(OverlayTexture.NO_OVERLAY).endVertex();
        builder.vertex(poseStack.last().pose(), 1, 0, 0).color(r, g, b, 1.0F).uv2(OverlayTexture.NO_OVERLAY).endVertex();
        builder.vertex(poseStack.last().pose(), 1, 1, 0).color(r, g, b, 1.0F).uv2(OverlayTexture.NO_OVERLAY).endVertex();
        builder.vertex(poseStack.last().pose(), 0, 1, 0).color(r, g, b, 1.0F).uv2(OverlayTexture.NO_OVERLAY).endVertex();
        poseStack.popPose();
    }
}