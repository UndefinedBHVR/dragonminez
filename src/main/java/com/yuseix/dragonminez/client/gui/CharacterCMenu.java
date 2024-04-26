package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.TextButton;
import com.yuseix.dragonminez.events.ModEvents;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.PlayerStatsAttrProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import java.util.ArrayList;
import java.util.List;

public class CharacterCMenu extends Screen {

    private int alturaTexto;
    private int anchoTexto;

    private static final ResourceLocation menu1 = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menugrande.png");
    private static final ResourceLocation texto = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menutexto.png");

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

    private final List<AbstractWidget> botonesRazas = new ArrayList<>();

    private DMZRightButton botonRazaRight;
    private DMZRightButton botonRazaLeft;

    private static int currentPage = 0;

    public CharacterCMenu(Component pTitle) {
        super(pTitle);
    }

    @Override
    protected void init() {
        super.init();

        //MenuInicio
        int posX = (this.minecraft.getWindow().getGuiScaledWidth()) / 2;
        int posY = (this.minecraft.getWindow().getGuiScaledHeight()) / 2;

        if(currentPage == 0){

        } else if (currentPage == 1){

        }


    }
    @Override
    public boolean isPauseScreen() {
        return false;
    }
    @Override
    public void tick() {
        super.tick();

        //MenuInicio
        int posX = (this.minecraft.getWindow().getGuiScaledWidth()) / 2;
        int posY = (this.minecraft.getWindow().getGuiScaledHeight()) / 2;

        if(currentPage == 0){
            this.removeWidget(botonRazaRight);
            this.removeWidget(botonRazaLeft);

            DMZRightButton nextButton = this.addRenderableWidget(new DMZRightButton("right",posX + 23, posY + 90, Component.empty(), button -> {
                currentPage = 1;
            }));


            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

                if(cap.getRace() == 0){
                    this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 1));
                    }));
                    this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 0));
                    }));
                }else if (cap.getRace() == 1){
                    this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 2));
                    }));
                    this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 0));
                    }));
                }else if (cap.getRace() == 2){
                    this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 3));
                    }));
                    this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 1));
                    }));
                }else if (cap.getRace() == 3){
                    this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 4));
                    }));
                    this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 2));
                    }));
                }else if (cap.getRace() == 4){
                    this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 5));
                    }));
                    this.botonRazaLeft =  (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 3));
                    }));
                }else if (cap.getRace() == 5){
                    this.botonRazaRight =  (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 0));
                    }));
                    this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 4));
                    }));
                }

            });
        } else if(currentPage == 1){

            DMZRightButton backButton = this.addRenderableWidget(new DMZRightButton("left",posX - 23, posY + 90, Component.empty(), button -> {
                currentPage = 0;
            }));
        } else if (currentPage == 2) {

        } else {

        }

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);

        var Altura = pGuiGraphics.guiHeight();
        var Ancho = pGuiGraphics.guiWidth();


        if(currentPage == 0){
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
            RenderSystem.depthMask(false);
            alturaTexto = (Altura / 2);
            anchoTexto = (Ancho / 2);

            pGuiGraphics.blit(texto, anchoTexto - 60, alturaTexto + 85, 0, 16, 130, 18);
            RenderSystem.disableBlend();
            //TITULO
            alturaTexto = (Altura / 2) - 40;
            anchoTexto = ((Ancho - this.font.width(this.CCreation)) / 2);

            drawStringWithBorder(pGuiGraphics, font, CCreation,anchoTexto,alturaTexto, 0xFFFFFF);

            //RAZA TITULO

            alturaTexto = (Altura / 2) - 89;
            anchoTexto = ((Ancho - this.font.width(this.Race)) / 2) - 137;

            pGuiGraphics.drawString(font, Race.withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xFDFDFD, true);


            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

                var raza = cap.getRace();

                switch (raza){
                    case 0: //Humano
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.Human)) / 2);

                        pGuiGraphics.drawString(font, Human, anchoTexto, alturaTexto, 0x31EAFF, true);

                        break;
                    case 1: // Saiyan
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.Saiyan)) / 2);

                        pGuiGraphics.drawString(font, Saiyan, anchoTexto, alturaTexto, 0xFFBA35, true);

                        break;
                    case 2: // Namek
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.Namek)) / 2);

                        pGuiGraphics.drawString(font, Namek, anchoTexto, alturaTexto, 0x378942, true);

                        break;
                    case 3: // BioAndroid
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.BioAndroid)) / 2);

                        pGuiGraphics.drawString(font, BioAndroid, anchoTexto, alturaTexto, 0x72DA58, true);

                        break;
                    case 4: // ColdDemon
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.ColdDemon)) / 2);

                        pGuiGraphics.drawString(font, ColdDemon, anchoTexto, alturaTexto, 0xAC1BEC, true);

                        break;
                    case 5: // Majin
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.Majin)) / 2);

                        pGuiGraphics.drawString(font, Majin, anchoTexto, alturaTexto, 0xFE7FF4, true);

                        break;
                    default:

                        break;
                }

            });
        } else if(currentPage == 1){

        }else if(currentPage == 2){

        }else {

        }



        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

    }
    
    public static void drawStringWithBorder(GuiGraphics guiGraphics,Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde){

        guiGraphics.drawString(font, texto, x + 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x - 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y + 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y - 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y, ColorTexto, false);
    }
    public static void drawStringWithBorder(GuiGraphics guiGraphics,Font font, Component texto, int x, int y, int ColorTexto){
        drawStringWithBorder(guiGraphics,font, texto, x,y,ColorTexto,0);
    }
}
