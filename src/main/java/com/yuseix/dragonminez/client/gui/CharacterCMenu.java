package com.yuseix.dragonminez.client.gui;

import com.yuseix.dragonminez.client.gui.buttons.CustomButtons;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class CharacterCMenu extends Screen {


    private final List<AbstractWidget> botones = new ArrayList<>();


    public CharacterCMenu(Component pTitle) {
        super(pTitle);
    }

    @Override
    protected void init() {
        super.init();

        int posX = (this.width - 152) / 2;
        int posY = (this.height - 256) / 2;

        botones.add(new CustomButtons(posX - 125, posY + 45, Component.empty(), button -> {

        }));
        botones.forEach(this::addRenderableWidget);

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);


        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }


}
