package com.yuseix.dragonminez.client.gui.buttons;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class TextButton extends Button {

    private static final ResourceLocation botones = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/buttons/characterbuttons.png");

    public TextButton(int pX, int pY, Component pMessage, OnPress pOnPress) {
        super(pX, pY, 74, 20, pMessage, pOnPress, DEFAULT_NARRATION);
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0f);

        int buttonY = 28;
        int offset = 0;

        if(this.isHovered){
            buttonY += 20;
        }

        this.setMessage(this.getMessage().copy());


        int posX = this.getX() + (74 - Minecraft.getInstance().font.width(this.getMessage())) / 2;
        pGuiGraphics.blit(botones, this.getX(), this.getY(), 0, buttonY, 74, 20);

        pGuiGraphics.drawString(Minecraft.getInstance().font, this.getMessage(),this.getX() + 23,this.getY() + 5,0xFFFFFF);

    }




}
