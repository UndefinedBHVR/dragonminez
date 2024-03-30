package com.yuseix.dragonminec.client.gui.buttons;

import com.yuseix.dragonminec.DragonMineC;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CustomButtons extends Button {

    private static final ResourceLocation botones = new ResourceLocation(DragonMineC.MODID,
            "textures/gui/buttons/characterbuttons.png");

    public CustomButtons(int pX, int pY, Component pMessage, OnPress pOnPress) {
        super(pX, pY, 10, 10, pMessage, pOnPress, DEFAULT_NARRATION);

    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.renderWidget(pGuiGraphics, pMouseX, pMouseY, pPartialTick);


        int buttonX = 0;
        int buttonY = 0;

        if (this.isHovered()) {
            //ejemplo para añadir botones mas adelante buttonX += 10;
            buttonY += 10;
        }

        pGuiGraphics.blit(botones, this.getX(), this.getY(), 0, buttonY, 10, 10);
    }
}
