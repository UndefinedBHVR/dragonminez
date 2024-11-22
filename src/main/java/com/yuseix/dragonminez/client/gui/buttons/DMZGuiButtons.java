package com.yuseix.dragonminez.client.gui.buttons;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DMZGuiButtons extends Button {

    private String tipo;

    private static final ResourceLocation botones = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/buttons/menubuttons.png");


    public DMZGuiButtons(int pX, int pY, String tipo, Component pMessage, OnPress pOnPress) {
        super(pX, pY, 20, 20, pMessage, pOnPress, DEFAULT_NARRATION);
        this.tipo = tipo;
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

        int buttonY = 0;
        if(this.isHovered){
            buttonY += 20;
        }

        int buttonX = 0;

        switch (tipo){
            case "libro":
                buttonX = 0;
                break;
            default:
                buttonX = 0;
                break;
        }

        // Dibujar la textura del botón
        pGuiGraphics.blit(botones, this.getX(), this.getY(), buttonX, buttonY, 20, 20);


}
}
