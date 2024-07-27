package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.ColorButton;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.TextButton;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.DinoEntity;
import com.yuseix.dragonminez.init.entity.custom.FakeBioAndroidEntity;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.TranslateManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.gui.widget.ForgeSlider;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CharacterCMenu extends Screen {

    private int alturaTexto;
    private int anchoTexto;

    private static final ResourceLocation menu1 = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menugrande.png");
    private static final ResourceLocation menu2 = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menumedio.png");
    private static final ResourceLocation texto = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menutexto.png");
    private static final ResourceLocation colorCuadrado = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/buttons/characterbuttons.png");

    private final List<AbstractWidget> botonOjos = new ArrayList<>();

    private DMZRightButton botonRazaRight, botonRazaLeft, eyesTypeRight, eyesTypeLeft, bodyTypeRightButton, bodyTypeLeftButton, gendersRigthButton, gendersLeftButton, hairRigthButton, hairLeftButton;

    private TextButton nextButton, backButton, setColor;
    private ColorButton eyesButtonColor, eyesButtonColor2, bodyButtonColor1, bodyButtonColor2, bodyButtonColor3, hairButtonColor;
    private ForgeSlider sliderR, sliderG, sliderB;
    private int colorR, colorG, colorB;
    private static int currentPage = 0;
    private static String partePagina = "";


    public CharacterCMenu(Component pTitle) {
        super(pTitle);
    }

    @Override
    protected void init() {

        //MenuInicio
        int posX = (this.width);
        int posY = (this.minecraft.getWindow().getGuiScaledHeight()) / 2;

        if (currentPage == 0) {

        } else if (currentPage == 1) {
            sliders(posX - 127, posY + 5);

            botonesRazaColores(72, posY);

        }

        super.init();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        //MenuInicio
        int ancho = (this.width / 2);
        int alto = (this.minecraft.getWindow().getGuiScaledHeight()) / 2;


        botonNextBack(ancho, this.height - 25);

        if (currentPage == 0) {

            botonesRazasElegir(ancho, alto + 87);

        } else if (currentPage == 1) {

            botonesBodyType(113, alto - 44);
            botonesGeneros(113, alto - 76);
            botonesOjos(113, alto + 3);
            botonesCabellos(113,alto+3);
        } else if (currentPage == 2) {

        } else {

        }

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);

        var Altura = pGuiGraphics.guiHeight();
        var Ancho = pGuiGraphics.guiWidth();

        if (currentPage == 0) {

            var AlturaGui = this.height;
            var AnchoGui = this.width;

            pagina0(pGuiGraphics, AnchoGui, AlturaGui);

        } else if (currentPage == 1) {

            pagina1(pGuiGraphics);

            pagina1Color(pGuiGraphics);


        } else if (currentPage == 2) {

        } else {

        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);


        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

    }


    public static int calcularColor(int r, int g, int b) {
        //Convertir el numero a decimal
        int colorDecimal = (r << 16) + (g << 8) + b;

        return colorDecimal;
    }

    public void botonNextBack(int posX, int posY) {

        this.removeWidget(backButton);
        this.removeWidget(nextButton);

        if (currentPage == 0) {
            this.nextButton = this.addRenderableWidget(new TextButton(this.width - 85, posY, TranslateManager.NEXT.withStyle(ChatFormatting.BOLD), button -> {
                currentPage = 1;
                this.removeWidget(nextButton);
                this.removeWidget(botonRazaLeft);
                this.removeWidget(botonRazaRight);
                sliders(this.width - 127, ((this.minecraft.getWindow().getGuiScaledHeight()) / 2) + 5);
                botonesRazaColores(72, this.height / 2);

                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

                    switch (cap.getRace()) {
                        case 0:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
                            break;
                        case 1:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
                            break;
                        case 2:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 2075172));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor2", 12263460));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16746150));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                            break;
                        case 3:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 1603072));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor2", 10478369));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16741888));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                            break;
                        case 4:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 15590399));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor2", 15255295));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor3", 15240865));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                            break;
                        case 5:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16753919));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 11796480));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 11796480));
                            ModMessages.sendToServer(new CharacterC2S("hairColor", 16753919));
                            break;
                        default:

                            break;
                    }


                });
                ModMessages.sendToServer(new CharacterC2S("Gender", 0));

            }));
        } else if (currentPage == 1) {
            //BOTON VOLVER
            this.backButton = (TextButton) this.addRenderableWidget(new TextButton(20, posY, TranslateManager.BACK.withStyle(ChatFormatting.BOLD), button -> {
                currentPage = 0;
                this.removeWidget(sliderR);
                this.removeWidget(sliderG);
                this.removeWidget(sliderB);
                this.removeWidget(eyesButtonColor);
                this.removeWidget(eyesButtonColor2);
                this.removeWidget(bodyButtonColor1);
                this.removeWidget(bodyButtonColor2);
                this.removeWidget(bodyButtonColor3);
                this.removeWidget(hairButtonColor);
                this.removeWidget(setColor);
                this.removeWidget(eyesTypeLeft);
                this.removeWidget(eyesTypeRight);
                this.removeWidget(bodyTypeRightButton);
                this.removeWidget(bodyTypeLeftButton);
                this.removeWidget(gendersRigthButton);
                this.removeWidget(gendersLeftButton);
                this.removeWidget(hairRigthButton);
                this.removeWidget(hairLeftButton);
            }));
            //BOTON SIGUIENTE
            this.nextButton = (TextButton) this.addRenderableWidget(new TextButton(this.width - 85, posY, TranslateManager.NEXT.withStyle(ChatFormatting.BOLD), button -> {
            }));
        } else {

        }


    }

    public void botonesRazaColores(int posX, int posY) {
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        this.removeWidget(eyesButtonColor);
        this.removeWidget(eyesButtonColor2);
        this.removeWidget(bodyButtonColor1);
        this.removeWidget(bodyButtonColor2);
        this.removeWidget(bodyButtonColor3);
        this.removeWidget(hairButtonColor);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            switch (cap.getRace()) {
                case 0:
                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX - 15, posY + 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int ojo1 = cap.getEye1Color();

                        float r = (ojo1 >> 16) / 255.0F;
                        float g = ((ojo1 >> 8) & 0xff) / 255.0f;
                        float b = (ojo1 & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo1";
                    }));
                    //BOTON COLOR OJO 2
                    this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY + 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int ojo2 = cap.getEye2Color();

                        float r = (ojo2 >> 16) / 255.0F;
                        float g = ((ojo2 >> 8) & 0xff) / 255.0f;
                        float b = (ojo2 & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo2";
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX, posY - 29, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getBodyColor();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo1";
                    }));


                    this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX, posY + 64, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getHairColor();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("hairColor", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cabelloPagina";
                    }));
                    break;
                case 1:
                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX - 15, posY + 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int ojo1 = cap.getEye1Color();

                        float r = (ojo1 >> 16) / 255.0F;
                        float g = ((ojo1 >> 8) & 0xff) / 255.0f;
                        float b = (ojo1 & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo1";
                    }));
                    //BOTON COLOR OJO 2
                    this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY + 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int ojo2 = cap.getEye2Color();

                        float r = (ojo2 >> 16) / 255.0F;
                        float g = ((ojo2 >> 8) & 0xff) / 255.0f;
                        float b = (ojo2 & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo2";
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX, posY - 29, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getBodyColor();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo1";
                    }));


                    this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX, posY + 64, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getHairColor();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("hairColor", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cabelloPagina";
                    }));
                    break;
                case 2:
                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX - 15, posY - 63, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int ojo1 = cap.getEye1Color();

                        float r = (ojo1 >> 16) / 255.0F;
                        float g = ((ojo1 >> 8) & 0xff) / 255.0f;
                        float b = (ojo1 & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo1";
                    }));
                    //BOTON COLOR OJO 2
                    this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY - 63, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int ojo2 = cap.getEye2Color();

                        float r = (ojo2 >> 16) / 255.0F;
                        float g = ((ojo2 >> 8) & 0xff) / 255.0f;
                        float b = (ojo2 & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo2";
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX - 33, posY - 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getBodyColor();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo1";
                    }));

                    this.bodyButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor2", posX-11, posY - 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getBodyColor2();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor2", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo2";
                    }));

                    this.bodyButtonColor3 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor3", posX + 11, posY - 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getBodyColor3();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor3", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo3";
                    }));

                    this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX+33, posY - 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getHairColor();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("hairColor", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cabelloPagina";
                    }));
                    break;
                case 3:

                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX, posY - 63, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int ojo1 = cap.getEye1Color();

                        float r = (ojo1 >> 16) / 255.0F;
                        float g = ((ojo1 >> 8) & 0xff) / 255.0f;
                        float b = (ojo1 & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo1";
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX - 25, posY - 17, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getBodyColor();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo1";
                    }));

                    this.bodyButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor2", posX, posY - 17, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getBodyColor2();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor2", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo2";
                    }));

                    this.bodyButtonColor3 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor3", posX + 25, posY - 17, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getBodyColor3();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor3", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo3";
                    }));


                    break;
                case 4:
                    break;
                case 5:
                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX, posY + 57, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int ojo1 = cap.getEye1Color();

                        float r = (ojo1 >> 16) / 255.0F;
                        float g = ((ojo1 >> 8) & 0xff) / 255.0f;
                        float b = (ojo1 & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo1";
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1",  posX, posY - 30, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getBodyColor();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r * 255);
                        colorG = (int) (g * 255);
                        colorB = (int) (b * 255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 43, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo1";
                    }));

                    break;
                default:
                    break;

            }

        });
        RenderSystem.disableBlend();
    }

    public void botonesCabellos(int posX, int posY) {

        this.removeWidget(hairRigthButton);
        this.removeWidget(hairLeftButton);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            switch (cap.getRace()) {
                case 0:
                    if (cap.getHairID() == 0) {
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));

                    } else if (cap.getHairID() == 1) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 0));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    }

                    break;
                case 1:
                    if (cap.getHairID() == 0) {
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));

                    } else if (cap.getHairID() == 1) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 0));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    }

                    break;
                case 2:
                    if (cap.getHairID() == 0) {
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY+11, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));

                    } else if (cap.getHairID() == 1) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+11, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 0));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY+11, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    }else if (cap.getHairID() == 2) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+11, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    }

                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    if (cap.getHairID() == 0) {
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY+3, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));

                    } else if (cap.getHairID() == 1) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+3, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 0));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY+3, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    }else if (cap.getHairID() == 2) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+3, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    }
                    break;
                default:
                    break;
            }

        });
    }

    public void botonesOjos(int posX, int posY) {

        this.removeWidget(eyesTypeRight);
        this.removeWidget(eyesTypeLeft);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            switch (cap.getRace()) {
                case 0:
                    if (cap.getBodytype() > 0) {
                        if (cap.getEyesType() == 0) {
                            this.eyesTypeRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 1));
                                this.removeWidget(eyesTypeRight);
                                this.removeWidget(eyesTypeLeft);
                            }));

                        } else if (cap.getEyesType() == 1) {
                            this.eyesTypeLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 0));
                                this.removeWidget(eyesTypeRight);
                                this.removeWidget(eyesTypeLeft);
                            }));
                        }
                    }

                    break;
                case 1:
                    if (cap.getBodytype() > 0) {
                        if (cap.getEyesType() == 0) {
                            this.eyesTypeRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 1));
                                this.removeWidget(eyesTypeRight);
                                this.removeWidget(eyesTypeLeft);
                            }));

                        } else if (cap.getEyesType() == 1) {
                            this.eyesTypeLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 0));
                                this.removeWidget(eyesTypeRight);
                                this.removeWidget(eyesTypeLeft);
                            }));
                        }
                    }

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    if (cap.getGender().equals("Female")) {
                        if (cap.getEyesType() == 0) {
                            this.eyesTypeRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 38, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 1));
                                this.removeWidget(eyesTypeRight);
                                this.removeWidget(eyesTypeLeft);
                            }));

                        } else if (cap.getEyesType() == 1) {
                            this.eyesTypeLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY + 38, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 0));
                                this.removeWidget(eyesTypeRight);
                                this.removeWidget(eyesTypeLeft);
                            }));
                        }
                    }
                    break;
                default:
                    break;
            }

        });
    }

    public void botonesBodyType(int posX, int posY) {

        this.removeWidget(bodyTypeRightButton);
        this.removeWidget(bodyTypeLeftButton);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            switch (cap.getRace()) {
                case 0:
                    if (cap.getBodytype() == 0) {
                        this.bodyTypeRightButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    } else if (cap.getBodytype() == 1) {
                        this.bodyTypeLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    }
                    break;
                case 1:
                    if (cap.getBodytype() == 0) {
                        this.bodyTypeRightButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    } else if (cap.getBodytype() == 1) {
                        this.bodyTypeLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    }
                    break;
                case 2:
                    if (cap.getBodytype() == 0) {
                        this.bodyTypeRightButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY+12, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    } else if (cap.getBodytype() == 1) {
                        this.bodyTypeLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+12, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }


        });
    }

    public void botonesGeneros(int posX, int posY) {

        this.removeWidget(gendersRigthButton);
        this.removeWidget(gendersLeftButton);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            switch (cap.getRace()) {
                case 0:
                    if (cap.getGender().equals("Female")) {

                        this.gendersLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("Gender", 0));
                            this.removeWidget(gendersRigthButton);
                            this.removeWidget(gendersLeftButton);
                        }));
                    } else if (cap.getGender().equals("Male")) {
                        this.gendersRigthButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("Gender", 1));
                            this.removeWidget(gendersRigthButton);
                            this.removeWidget(gendersLeftButton);
                        }));
                    }
                    break;
                case 1:
                    if (cap.getGender().equals("Female")) {

                        this.gendersLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("Gender", 0));
                            this.removeWidget(gendersRigthButton);
                            this.removeWidget(gendersLeftButton);
                        }));
                    } else if (cap.getGender().equals("Male")) {
                        this.gendersRigthButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("Gender", 1));
                            this.removeWidget(gendersRigthButton);
                            this.removeWidget(gendersLeftButton);
                        }));
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    if (cap.getGender().equals("Female")) {

                        this.gendersLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("Gender", 0));
                            this.removeWidget(gendersRigthButton);
                            this.removeWidget(gendersLeftButton);
                        }));
                    } else if (cap.getGender().equals("Male")) {
                        this.gendersRigthButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("Gender", 1));
                            this.removeWidget(gendersRigthButton);
                            this.removeWidget(gendersLeftButton);
                        }));
                    }
                    break;
                default:
                    break;
            }


        });
    }

    public void botonesRazasElegir(int posX, int posY) {

        this.removeWidget(botonRazaRight);
        this.removeWidget(botonRazaLeft);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            if (cap.getRace() == 0) {
                this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 1));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
                }));
            } else if (cap.getRace() == 1) {
                this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 2));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 2075172));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 12263460));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16746150));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                }));

                this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 0));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
                }));
            } else if (cap.getRace() == 2) {
                this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 3));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 1603072));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 10478369));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16741888));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                }));
                this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 1));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
                }));

            } else if (cap.getRace() == 3) {
                this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 4));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 15590399));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 15255295));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 15240865));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                }));
                this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 2));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 2075172));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 12263460));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16746150));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                }));
            } else if (cap.getRace() == 4) {

                this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 5));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16753919));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 11796480));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 11796480));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 16753919));
                }));
                this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 3));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 1603072));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 10478369));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16741888));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                }));
            } else {
                this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 4));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 15590399));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 15255295));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 15240865));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                }));
            }

        });
    }

    public void sliders(int sliderX, int posY) {

        this.removeWidget(sliderR);
        this.removeWidget(sliderG);
        this.removeWidget(sliderB);

        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        sliderR = this.addRenderableWidget(new ForgeSlider(sliderX, posY - 75, 100, 15, Component.literal("R:").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.RED), Component.empty(), 0.0, 255.0, colorR, true) {
            @Override
            protected void applyValue() {
                super.applyValue();
                colorR = this.getValueInt();
            }
        });
        sliderG = this.addRenderableWidget(new ForgeSlider(sliderX, posY - 55, 100, 15, Component.literal("G:").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GREEN), Component.empty(), 0.0, 255.0, colorB, true) {
            @Override
            protected void applyValue() {
                super.applyValue();
                colorG = this.getValueInt();
            }
        });
        sliderB = this.addRenderableWidget(new ForgeSlider(sliderX, posY - 35, 100, 15, Component.literal("B:").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.BLUE), Component.empty(), 0.0, 255.0, colorG, true) {
            @Override
            protected void applyValue() {
                super.applyValue();
                colorB = this.getValueInt();
            }
        });
        RenderSystem.disableBlend();
    }

    public void pagina0(GuiGraphics pGuiGraphics, int posX, int posY) {

        RenderSystem.enableBlend();

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.depthMask(false);

        pGuiGraphics.blit(texto, (pGuiGraphics.guiWidth() / 2) - 60, (pGuiGraphics.guiHeight() / 2) + 85, 0, 16, 130, 18);
        RenderSystem.disableBlend();


        //TITULO
        alturaTexto = (posY / 2) - 40;
        anchoTexto = ((posX - this.font.width(TranslateManager.CCreation)) / 2);

        drawStringWithBorder(pGuiGraphics, font, TranslateManager.CCreation, anchoTexto, alturaTexto, 0xFFFFFF);


        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            var raza = cap.getRace();

            switch (raza) {
                case 0: //Humano
                    alturaTexto = (posY / 2) + 90;
                    anchoTexto = ((posX - this.font.width(TranslateManager.Human)) / 2);

                    pGuiGraphics.drawString(font, TranslateManager.Human, anchoTexto, alturaTexto, 0x31EAFF, true);

                    break;
                case 1: // Saiyan
                    alturaTexto = (posY / 2) + 90;
                    anchoTexto = ((posX - this.font.width(TranslateManager.Saiyan)) / 2);

                    pGuiGraphics.drawString(font, TranslateManager.Saiyan, anchoTexto, alturaTexto, 0xFFBA35, true);

                    break;
                case 2: // Namek
                    alturaTexto = (posY / 2) + 90;
                    anchoTexto = ((posX - this.font.width(TranslateManager.Namek)) / 2);

                    pGuiGraphics.drawString(font, TranslateManager.Namek, anchoTexto, alturaTexto, 0x378942, true);

                    break;
                case 3: // BioAndroid
                    alturaTexto = (posY / 2) + 90;
                    anchoTexto = ((posX - this.font.width(TranslateManager.BioAndroid)) / 2);

                    pGuiGraphics.drawString(font, TranslateManager.BioAndroid, anchoTexto, alturaTexto, 0x72DA58, true);

                    break;
                case 4: // ColdDemon
                    alturaTexto = (posY / 2) + 90;
                    anchoTexto = ((posX - this.font.width(TranslateManager.ColdDemon)) / 2);

                    pGuiGraphics.drawString(font, TranslateManager.ColdDemon, anchoTexto, alturaTexto, 0xAC1BEC, true);

                    break;
                case 5: // Majin
                    alturaTexto = (posY / 2) + 90;
                    anchoTexto = ((posX - this.font.width(TranslateManager.Majin)) / 2);

                    pGuiGraphics.drawString(font, TranslateManager.Majin, anchoTexto, alturaTexto, 0xFE7FF4, true);

                    break;
                default:

                    break;
            }

            //LivingEntity bio = new FakeBioAndroidEntity(MainEntity.FAKEBIOANDROID1.get(), this.minecraft.level);
            //LivingEntity dino = new DinoEntity(MainEntity.DINO1.get(), this.minecraft.level);

            if (cap.getRace() == 0) {
                //renderEntityInInventoryFollowsAngle(pGuiGraphics, anchoTexto, alturaTexto, 15, 30, 0, bio);
            }

        });
    }

    public void pagina1(GuiGraphics pGuiGraphics) {

        //MENU CARACTERISTICAS
        alturaTexto = (pGuiGraphics.guiHeight() / 2);
        anchoTexto = 10;
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        pGuiGraphics.blit(menu1, anchoTexto, alturaTexto - 110, 0, 0, 148, 222);
        RenderSystem.disableBlend();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            switch (cap.getRace()) {
                case 0:
                    //GENERO TITULO
                    alturaTexto = (pGuiGraphics.guiHeight() / 2);
                    anchoTexto = (20 - this.font.width(TranslateManager.GENDERS));
                    pGuiGraphics.drawString(font, TranslateManager.GENDERS.withStyle(ChatFormatting.BOLD), anchoTexto + 87, alturaTexto - 89, 0xC07FFD);

                    switch (cap.getGender()) {
                        case "Male", "male":
                            anchoTexto = (20 - this.font.width(TranslateManager.GENDER_MALE));
                            drawStringWithBorder(pGuiGraphics, font, TranslateManager.GENDER_MALE, anchoTexto + 75, alturaTexto - 72, 0xFFFFFF, 0x2133A6);
                            break;
                        case "Female", "female":
                            anchoTexto = (20 - this.font.width(TranslateManager.GENDER_FEMALE));
                            drawStringWithBorder(pGuiGraphics, font, TranslateManager.GENDER_FEMALE, anchoTexto + 81, alturaTexto - 72, 0xFFFFFF, 0xFC63D9);
                            break;
                    }

                    //TIPO DE CUERPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 60, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    //CUERPO TITULO
                    anchoTexto = (20 - this.font.width(TranslateManager.BODYTYPES));
                    pGuiGraphics.drawString(font, TranslateManager.BODYTYPES.withStyle(ChatFormatting.BOLD), anchoTexto + 95, alturaTexto - 57, 0xFFCA9B);

                    //TIPO DE CUERPO
                    if (cap.getBodytype() == 0) {
                        anchoTexto = (20 - this.font.width(TranslateManager.H_BODY_TYPE));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.H_BODY_TYPE, anchoTexto + 83, alturaTexto - 40, 0xFFFFFF);
                    } else if (cap.getBodytype() == 1) {
                        anchoTexto = (20 - this.font.width(TranslateManager.H_BODY_TYPE2));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.H_BODY_TYPE2, anchoTexto + 83, alturaTexto - 40, 0xFFFFFF);

                    }

                    //OJOS TIPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 13, 0, 0, 73, 15);
                    RenderSystem.disableBlend();
                    //OJOS TITULO
                    anchoTexto = (20 - this.font.width(TranslateManager.EYES));
                    pGuiGraphics.drawString(font, TranslateManager.EYES.withStyle(ChatFormatting.BOLD), anchoTexto + 95, alturaTexto - 10, 0xFF9B9B);

                    if (cap.getEyesType() == 0) {
                        anchoTexto = (20 - this.font.width(TranslateManager.EYES_TYPE_1));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.EYES_TYPE_1, anchoTexto + 83, alturaTexto + 7, 0xFFFFFF);
                    } else if (cap.getEyesType() == 1) {
                        anchoTexto = (20 - this.font.width(TranslateManager.EYES_TYPE_2));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.EYES_TYPE_2, anchoTexto + 83, alturaTexto + 7, 0xFFFFFF);

                    }

                    //HAIR COLOR
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto + 34, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    //HAIR TITULO
                    anchoTexto = (20 - this.font.width(TranslateManager.HAIRTYPES));
                    pGuiGraphics.drawString(font, TranslateManager.HAIRTYPES.withStyle(ChatFormatting.BOLD), anchoTexto + 77, alturaTexto + 38, 0x65FC63);

                    if (cap.getHairID() == 0) {
                        anchoTexto = (20 - this.font.width(TranslateManager.HAIR_0));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.HAIR_0, anchoTexto + 83, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getHairID() == 1) {
                    }

                    break;
                case 1:
                    //GENERO TITULO
                    anchoTexto = (20 - this.font.width(TranslateManager.GENDERS));
                    pGuiGraphics.drawString(font, TranslateManager.GENDERS.withStyle(ChatFormatting.BOLD), anchoTexto + 87, alturaTexto - 89, 0xC07FFD);

                    switch (cap.getGender()) {
                        case "Male", "male":
                            anchoTexto = (20 - this.font.width(TranslateManager.GENDER_MALE));
                            drawStringWithBorder(pGuiGraphics, font, TranslateManager.GENDER_MALE, anchoTexto + 75, alturaTexto - 72, 0xFFFFFF, 0x2133A6);
                            break;
                        case "Female", "female":
                            anchoTexto = (20 - this.font.width(TranslateManager.GENDER_FEMALE));
                            drawStringWithBorder(pGuiGraphics, font, TranslateManager.GENDER_FEMALE, anchoTexto + 81, alturaTexto - 72, 0xFFFFFF, 0xFC63D9);
                            break;
                    }


                    //TIPO DE CUERPO
                    alturaTexto = (pGuiGraphics.guiHeight() / 2);
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 60, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    //CUERPO TITULO
                    anchoTexto = (20 - this.font.width(TranslateManager.BODYTYPES));
                    pGuiGraphics.drawString(font, TranslateManager.BODYTYPES.withStyle(ChatFormatting.BOLD), anchoTexto + 95, alturaTexto - 57, 0xFFCA9B);

                    //TIPO DE CUERPO
                    if (cap.getBodytype() == 0) {
                        anchoTexto = (20 - this.font.width(TranslateManager.S_BODY_TYPE));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.S_BODY_TYPE, anchoTexto + 83, alturaTexto - 40, 0xFFFFFF);
                    } else if (cap.getBodytype() == 1) {
                        anchoTexto = (20 - this.font.width(TranslateManager.S_BODY_TYPE2));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.S_BODY_TYPE2, anchoTexto + 83, alturaTexto - 40, 0xFFFFFF);

                    }
                    //OJOS
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 13, 0, 0, 73, 15);
                    RenderSystem.disableBlend();
                    //OJOS TITULO
                    anchoTexto = (20 - this.font.width(TranslateManager.EYES));
                    pGuiGraphics.drawString(font, TranslateManager.EYES.withStyle(ChatFormatting.BOLD), anchoTexto + 95, alturaTexto - 10, 0xFF9B9B);

                    if (cap.getEyesType() == 0) {
                        anchoTexto = (20 - this.font.width(TranslateManager.EYES_TYPE_1));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.EYES_TYPE_1, anchoTexto + 83, alturaTexto + 7, 0xFFFFFF);
                    } else if (cap.getEyesType() == 1) {
                        anchoTexto = (20 - this.font.width(TranslateManager.EYES_TYPE_2));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.EYES_TYPE_2, anchoTexto + 83, alturaTexto + 7, 0xFFFFFF);

                    }

                    //HAIR COLOR
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto + 34, 0, 0, 73, 15);
                    RenderSystem.disableBlend();
                    //HAIR TITULO
                    anchoTexto = (20 - this.font.width(TranslateManager.HAIRTYPES));
                    pGuiGraphics.drawString(font, TranslateManager.HAIRTYPES.withStyle(ChatFormatting.BOLD), anchoTexto + 77, alturaTexto + 38, 0x65FC63);

                    if (cap.getHairID() == 0) {
                        anchoTexto = (20 - this.font.width(TranslateManager.HAIR_0));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.HAIR_0, anchoTexto + 83, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getHairID() == 1) {

                    }

                    break;
                case 2:
                    //TIPO DE OJOS
                    alturaTexto = (pGuiGraphics.guiHeight() / 2);
                    anchoTexto = (20 - this.font.width(TranslateManager.EYES));
                    pGuiGraphics.drawString(font, TranslateManager.EYES.withStyle(ChatFormatting.BOLD), anchoTexto + 94, alturaTexto - 89, 0xFF9B9B);

                    if (cap.getEyesType() == 0) {
                        anchoTexto = (20 - this.font.width(TranslateManager.EYES_TYPE_1));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.EYES_TYPE_1, anchoTexto + 83, alturaTexto - 73, 0xFFFFFF);
                    } else if (cap.getEyesType() == 1) {
                        anchoTexto = (20 - this.font.width(TranslateManager.EYES_TYPE_2));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.EYES_TYPE_2, anchoTexto + 83, alturaTexto - 73, 0xFFFFFF);
                    }
                    //TIPO DE CUERPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 48, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    //CUERPO TITULO
                    anchoTexto = (20 - this.font.width(TranslateManager.BODYTYPES));
                    pGuiGraphics.drawString(font, TranslateManager.BODYTYPES.withStyle(ChatFormatting.BOLD), anchoTexto + 95, alturaTexto - 44, 0xFFCA9B);

                    //TIPO DE CUERPO
                    if (cap.getBodytype() == 0) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Default"), 67, alturaTexto - 29, 0xFFFFFF);
                    } else if (cap.getBodytype() == 1) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Custom"), 67, alturaTexto - 29, 0xFFFFFF);

                    }
                    //TIPO DE CABELLO EN ESTE CASO OREJAS PARA EL NAMEK
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 2, 0, 0, 73, 15);
                    RenderSystem.disableBlend();
                    //HAIR TITULO
                    pGuiGraphics.drawString(font, Component.literal("Ears").withStyle(ChatFormatting.BOLD), 70, alturaTexto + 1, 0x65FC63);

                    if (cap.getHairID() == 0) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Default"), 67, alturaTexto + 18, 0xFFFFFF);
                    } else if (cap.getHairID() == 1) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 02"), 66, alturaTexto + 18, 0xFFFFFF);
                    } else if (cap.getHairID() == 2) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("None"), 72, alturaTexto + 18, 0xFFFFFF);
                    }

                    break;
                case 3:
                    //TIPO DE OJOS
                    alturaTexto = (pGuiGraphics.guiHeight() / 2);
                    anchoTexto = (20 - this.font.width(TranslateManager.EYES));
                    pGuiGraphics.drawString(font, TranslateManager.EYES.withStyle(ChatFormatting.BOLD), anchoTexto + 94, alturaTexto - 89, 0xFF9B9B);

                    anchoTexto = (20 - this.font.width(TranslateManager.EYES_TYPE_1));
                    drawStringWithBorder(pGuiGraphics, font, TranslateManager.EYES_TYPE_1, anchoTexto + 83, alturaTexto - 74, 0xFFFFFF);

                    //TIPO DE CUERPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 46, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    anchoTexto = (20 - this.font.width(TranslateManager.BODYTYPES));
                    pGuiGraphics.drawString(font, TranslateManager.BODYTYPES.withStyle(ChatFormatting.BOLD), anchoTexto + 94, alturaTexto - 42, 0xFFCA9B);

                    anchoTexto = (20 - this.font.width(TranslateManager.B_BODY_TYPE));
                    drawStringWithBorder(pGuiGraphics, font, TranslateManager.B_BODY_TYPE, anchoTexto + 106, alturaTexto - 28, 0xFFFFFF);

                    break;
                case 4:
                    break;
                case 5:
                    //GENERO TITULO
                    alturaTexto = (pGuiGraphics.guiHeight() / 2);
                    anchoTexto = (20 - this.font.width(TranslateManager.GENDERS));
                    pGuiGraphics.drawString(font, TranslateManager.GENDERS.withStyle(ChatFormatting.BOLD), anchoTexto + 87, alturaTexto - 89, 0xC07FFD);

                    switch (cap.getGender()) {
                        case "Male", "male":
                            anchoTexto = (20 - this.font.width(TranslateManager.GENDER_MALE));
                            drawStringWithBorder(pGuiGraphics, font, TranslateManager.GENDER_MALE, anchoTexto + 75, alturaTexto - 72, 0xFFFFFF, 0x2133A6);
                            break;
                        case "Female", "female":
                            anchoTexto = (20 - this.font.width(TranslateManager.GENDER_FEMALE));
                            drawStringWithBorder(pGuiGraphics, font, TranslateManager.GENDER_FEMALE, anchoTexto + 81, alturaTexto - 72, 0xFFFFFF, 0xFC63D9);
                            break;
                    }

                    //TIPO DE CUERPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 60, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    //CUERPO TITULO
                    anchoTexto = (20 - this.font.width(TranslateManager.BODYTYPES));
                    pGuiGraphics.drawString(font, TranslateManager.BODYTYPES.withStyle(ChatFormatting.BOLD), anchoTexto + 95, alturaTexto - 57, 0xFFCA9B);

                    //TIPO DE CUERPO
                    if (cap.getBodytype() == 0) {
                        anchoTexto = (20 - this.font.width(TranslateManager.S_BODY_TYPE));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.S_BODY_TYPE, anchoTexto + 83, alturaTexto - 40, 0xFFFFFF);
                    } else if (cap.getBodytype() == 1) {
                        anchoTexto = (20 - this.font.width(TranslateManager.S_BODY_TYPE2));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.S_BODY_TYPE2, anchoTexto + 83, alturaTexto - 40, 0xFFFFFF);

                    }

                    //TIPO DE CABELLO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 13, 0, 0, 73, 15);
                    RenderSystem.disableBlend();
                    //HAIR TITULO
                    anchoTexto = (20 - this.font.width(TranslateManager.HAIRTYPES));
                    pGuiGraphics.drawString(font, TranslateManager.HAIRTYPES.withStyle(ChatFormatting.BOLD), anchoTexto + 77, alturaTexto - 9, 0x65FC63);

                    if (cap.getHairID() == 0) {
                        anchoTexto = (20 - this.font.width(TranslateManager.HAIR_0));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.HAIR_0, anchoTexto + 83, alturaTexto + 9, 0xFFFFFF);
                    } else if (cap.getHairID() == 1) {
                    }

                    //OJOS TIPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto + 23, 0, 0, 73, 15);
                    RenderSystem.disableBlend();
                    //OJOS TITULO
                    anchoTexto = (20 - this.font.width(TranslateManager.EYES));
                    pGuiGraphics.drawString(font, TranslateManager.EYES.withStyle(ChatFormatting.BOLD), anchoTexto + 95, alturaTexto + 27, 0xFF9B9B);

                    if (cap.getEyesType() == 0) {
                        anchoTexto = (20 - this.font.width(TranslateManager.EYES_TYPE_1));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.EYES_TYPE_1, anchoTexto + 83, alturaTexto + 44, 0xFFFFFF);
                    } else if (cap.getEyesType() == 1) {
                        anchoTexto = (20 - this.font.width(TranslateManager.EYES_TYPE_2));
                        drawStringWithBorder(pGuiGraphics, font, TranslateManager.EYES_TYPE_2, anchoTexto + 83, alturaTexto + 44, 0xFFFFFF);

                    }
                    break;
                default:
                    break;
            }
        });


    }

    public void pagina1Color(GuiGraphics pGuiGraphics) {

        //MENU COLOR
        alturaTexto = (pGuiGraphics.guiHeight() / 2);
        anchoTexto = this.width - 150;
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);
        pGuiGraphics.blit(menu1, anchoTexto, alturaTexto - 110, 0, 0, 148, 222);
        RenderSystem.disableBlend();
        //TITULO
        anchoTexto = (this.width - this.font.width(TranslateManager.COLOR_MENU));
        pGuiGraphics.drawString(font, TranslateManager.COLOR_MENU.withStyle(ChatFormatting.BOLD), anchoTexto - 60, alturaTexto - 89, 0xFF9B9B);

        //cuadrado colores.
        alturaTexto = (pGuiGraphics.guiHeight() / 2) - 5;
        anchoTexto = (this.width - 105);
        float rColor, gColor, bColor;

        switch (partePagina) {
            case "ojo1":
                rColor = sliderR.getValueInt() / 255.0F;
                gColor = sliderG.getValueInt() / 255.0f;
                bColor = sliderB.getValueInt() / 255.0f;

                RenderSystem.enableBlend();
                RenderSystem.setShaderColor(rColor, gColor, bColor, 1.0f);
                pGuiGraphics.blit(colorCuadrado, anchoTexto, alturaTexto, 41, 0, 61, 14);
                RenderSystem.disableBlend();
                break;
            case "ojo2":
                rColor = sliderR.getValueInt() / 255.0F;
                gColor = sliderG.getValueInt() / 255.0f;
                bColor = sliderB.getValueInt() / 255.0f;

                RenderSystem.enableBlend();
                RenderSystem.setShaderColor(rColor, gColor, bColor, 1.0f);
                pGuiGraphics.blit(colorCuadrado, anchoTexto, alturaTexto, 41, 0, 61, 14);
                RenderSystem.disableBlend();
                break;
            case "cuerpo1":
                rColor = sliderR.getValueInt() / 255.0F;
                gColor = sliderG.getValueInt() / 255.0f;
                bColor = sliderB.getValueInt() / 255.0f;

                RenderSystem.enableBlend();
                RenderSystem.setShaderColor(rColor, gColor, bColor, 1.0f);
                pGuiGraphics.blit(colorCuadrado, anchoTexto, alturaTexto, 41, 0, 61, 14);
                RenderSystem.disableBlend();
                break;
            case "cuerpo2":
                rColor = sliderR.getValueInt() / 255.0F;
                gColor = sliderG.getValueInt() / 255.0f;
                bColor = sliderB.getValueInt() / 255.0f;

                RenderSystem.enableBlend();
                RenderSystem.setShaderTexture(0, colorCuadrado);
                RenderSystem.setShaderColor(rColor, gColor, bColor, 1.0f);
                pGuiGraphics.blit(colorCuadrado, anchoTexto, alturaTexto, 41, 0, 61, 14);
                RenderSystem.disableBlend();
                break;
            case "cuerpo3":
                rColor = sliderR.getValueInt() / 255.0F;
                gColor = sliderG.getValueInt() / 255.0f;
                bColor = sliderB.getValueInt() / 255.0f;
                RenderSystem.enableBlend();
                RenderSystem.setShaderTexture(0, colorCuadrado);
                RenderSystem.setShaderColor(rColor, gColor, bColor, 1.0f);
                pGuiGraphics.blit(colorCuadrado, anchoTexto, alturaTexto, 41, 0, 61, 14);
                RenderSystem.disableBlend();

                break;
            case "cabelloPagina":
                rColor = sliderR.getValueInt() / 255.0F;
                gColor = sliderG.getValueInt() / 255.0f;
                bColor = sliderB.getValueInt() / 255.0f;
                RenderSystem.enableBlend();
                RenderSystem.setShaderTexture(0, colorCuadrado);
                RenderSystem.setShaderColor(rColor, gColor, bColor, 1.0f);
                pGuiGraphics.blit(colorCuadrado, anchoTexto, alturaTexto, 41, 0, 61, 14);
                RenderSystem.disableBlend();

                break;
        }
    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {

        guiGraphics.drawString(font, texto, x + 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x - 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y + 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y - 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y, ColorTexto, false);
    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto) {
        drawStringWithBorder(guiGraphics, font, texto, x, y, ColorTexto, 0);
    }

    public static void renderEntityInInventoryFollowsAngle(GuiGraphics guiGraphics, int x, int y, int scale, float angleXComponent, float angleYComponent, LivingEntity livingEntity) {
        Quaternionf quaternionf = (new Quaternionf()).rotateZ(3.1415927F);
        Quaternionf quaternionf1 = (new Quaternionf()).rotateX(angleYComponent * 20.0F * 0.017453292F);
        quaternionf.mul(quaternionf1);
        float f2 = livingEntity.yBodyRot;
        float f3 = livingEntity.getYRot();
        float f4 = livingEntity.getXRot();
        float f5 = livingEntity.yHeadRotO;
        float f6 = livingEntity.yHeadRot;
        livingEntity.yBodyRot = 180.0F + angleXComponent * 20.0F;
        renderEntityInInv(guiGraphics, x, y, scale, quaternionf, quaternionf1, livingEntity);
        livingEntity.yBodyRot = f2;
    }

    public static void renderEntityInInv(GuiGraphics pGuiGraphics, int pX, int pY, int pScale, Quaternionf pPose, @Nullable Quaternionf pCameraOrientation, LivingEntity pEntity) {
        RenderSystem.enableBlend();
        RenderSystem.depthMask(true);
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(pX, pY, 50.0);
        pGuiGraphics.pose().mulPoseMatrix((new Matrix4f()).scaling((float) pScale, (float) pScale, (float) (-pScale)));
        pGuiGraphics.pose().mulPose(pPose);
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        if (pCameraOrientation != null) {
            pCameraOrientation.conjugate();
            entityrenderdispatcher.overrideCameraOrientation(pCameraOrientation);
        }
        entityrenderdispatcher.setRenderShadow(false);
        entityrenderdispatcher.render(pEntity, 0.0, 0.0, 0.0, 0.0F, 1.0F, pGuiGraphics.pose(), pGuiGraphics.bufferSource(), 15728880);
        pGuiGraphics.flush();
        entityrenderdispatcher.setRenderShadow(true);
        pGuiGraphics.pose().popPose();
        Lighting.setupFor3DItems();
        RenderSystem.disableBlend();
    }
}
