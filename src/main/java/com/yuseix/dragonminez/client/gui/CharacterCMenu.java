package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.events.ModEvents;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.PlayerStatsAttrProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import java.util.ArrayList;
import java.util.List;

public class CharacterCMenu extends Screen {

    int alturaTexto;
    int anchoTexto;

    private static final ResourceLocation menu1 = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menugrande.png");


    //NOMBRES DE LAS RAZAS
    MutableComponent Human = (Component.translatable("dmz.races.name.human"));
    MutableComponent Saiyan = (Component.translatable("dmz.races.name.saiyan"));
    MutableComponent Namek = (Component.translatable("dmz.races.name.namek"));
    MutableComponent BioAndroid = (Component.translatable("dmz.races.name.bioandroid"));
    MutableComponent ColdDemon = (Component.translatable("dmz.races.name.colddemon"));
    MutableComponent Majin = (Component.translatable("dmz.races.name.majin"));

    //OTROS
    MutableComponent CCreation = (Component.translatable("dmz.ccreation.name"));
    MutableComponent Race = (Component.translatable("dmz.ccreation.race"));

    private final List<AbstractWidget> botones = new ArrayList<>();


    public CharacterCMenu(Component pTitle) {
        super(pTitle);
    }

    @Override
    protected void init() {

        super.init();

    }
    @Override
    public boolean isPauseScreen() {
        return false;
    }


    @Override
    public void tick() {


        super.tick();

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);

        var Altura = pGuiGraphics.guiHeight();
        var Ancho = pGuiGraphics.guiWidth();


        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        RenderSystem.setShaderTexture(0, menu1);

        pGuiGraphics.blit(menu1, (Ancho / 2) - 210, (Altura / 2)- 110, 0, 0, 148, 222);

        //TITULO
        alturaTexto = (Altura / 2) - 40;
        anchoTexto = ((Ancho - this.font.width(this.CCreation)) / 2);


        pGuiGraphics.drawString(font, CCreation, anchoTexto + 1, alturaTexto, 0, false);
        pGuiGraphics.drawString(font, CCreation, anchoTexto - 1, alturaTexto, 0, false);
        pGuiGraphics.drawString(font, CCreation, anchoTexto, alturaTexto + 1, 0, false);
        pGuiGraphics.drawString(font, CCreation, anchoTexto, alturaTexto - 1, 0, false);
        pGuiGraphics.drawString(font, CCreation, anchoTexto, alturaTexto, 0xFDFDFD, false);


        //RAZA TITULO

        alturaTexto = (Altura / 2) - 89;
        anchoTexto = ((Ancho - this.font.width(this.Race)) / 2) - 137;

        pGuiGraphics.drawString(font, Race.withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xFDFDFD, true);


        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            var raza = cap.getRace();


            switch (raza){
                case 0: //Humano
                    alturaTexto = (Altura / 2) - 70;
                    anchoTexto = ((Ancho - this.font.width(this.Human)) / 2) - 137;

                    pGuiGraphics.drawString(font, Human, anchoTexto, alturaTexto, 0x31EAFF, true);

                    break;
                case 1: // Saiyan
                    alturaTexto = (Altura / 2) - 70;
                    anchoTexto = ((Ancho - this.font.width(this.Saiyan)) / 2) - 137;

                    pGuiGraphics.drawString(font, Saiyan, anchoTexto, alturaTexto, 0xFFBA35, true);

                    break;
                case 2: // Namek
                    alturaTexto = (Altura / 2) - 70;
                    anchoTexto = ((Ancho - this.font.width(this.Namek)) / 2) - 137;

                    pGuiGraphics.drawString(font, Namek, anchoTexto, alturaTexto, 0x378942, true);

                    break;
                case 3: // BioAndroid
                    alturaTexto = (Altura / 2) - 70;
                    anchoTexto = ((Ancho - this.font.width(this.BioAndroid)) / 2) - 137;

                    pGuiGraphics.drawString(font, BioAndroid, anchoTexto, alturaTexto, 0x72DA58, true);

                    break;
                case 4: // ColdDemon
                    alturaTexto = (Altura / 2) - 70;
                    anchoTexto = ((Ancho - this.font.width(this.ColdDemon)) / 2) - 137;

                    pGuiGraphics.drawString(font, ColdDemon, anchoTexto, alturaTexto, 0xAC1BEC, true);

                    break;
                case 5: // Majin
                    alturaTexto = (Altura / 2) - 70;
                    anchoTexto = ((Ancho - this.font.width(this.Majin)) / 2) - 137;

                    pGuiGraphics.drawString(font, Majin, anchoTexto, alturaTexto, 0xFE7FF4, true);

                    break;
                default:

                    break;
            }

        });

        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

    }

}
