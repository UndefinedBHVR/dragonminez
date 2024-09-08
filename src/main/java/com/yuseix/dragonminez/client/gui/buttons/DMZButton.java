package com.yuseix.dragonminez.client.gui.buttons;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DMZButton extends Button {

    private String type = "";

    private static final ResourceLocation botones = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/buttons/guibuttons.png");


    public DMZButton(int pX, int pY, Component pMessage,String tipo, OnPress pOnPress) {
        super(pX, pY, 61, 18, pMessage, pOnPress, DEFAULT_NARRATION);

        type = tipo;
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0f);
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, botones);


        // Dibujar la textura del botón
        if(type.equals("Accept")){
            pGuiGraphics.blit(botones, this.getX(), this.getY(), 0, 0, 61, 18);
        } else {
            pGuiGraphics.blit(botones, this.getX(), this.getY(), 0, 18, 61, 18);
        }

        // Dibujar el texto del botón
        int textColor = this.isHovered ? 0xFFFFFF : 0x7CFDD6;
        pGuiGraphics.drawCenteredString(minecraft.font, this.getMessage(), this.getX() + this.width / 2, this.getY() + (this.height - 8) / 2, textColor);
    }
}
