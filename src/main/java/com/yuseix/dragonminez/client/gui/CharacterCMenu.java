package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.ColorButton;
import com.yuseix.dragonminez.client.gui.buttons.ColorButton2;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.TextButton;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.DinoEntity;
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
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
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

    private static final ResourceLocation PANORAMA_PATH = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/panorama");

    private final List<ColorButton2> botonColorDefecto = new ArrayList<>();

    private DMZRightButton botonRazaRight, botonRazaLeft, eyesTypeRight, eyesTypeLeft, bodyTypeRightButton, bodyTypeLeftButton, gendersRigthButton, gendersLeftButton, hairRigthButton, hairLeftButton, claseRigthButton,claseLeftButton;
    private DMZRightButton botonAlignmentRight, botonAlignmentLeft;
    private TextButton nextButton, backButton, setColor;
    private ColorButton eyesButtonColor, eyesButtonColor2, bodyButtonColor1, bodyButtonColor2, bodyButtonColor3, hairButtonColor, auraButtonColor;
    private ForgeSlider sliderR, sliderG, sliderB;
    private int colorR, colorG, colorB;
    private int currentPage = 0;
    private static String partePagina = "";

    private final PanoramaRenderer customPanorama = new PanoramaRenderer(new CubeMap(PANORAMA_PATH));


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

        } else if(currentPage == 2){
            sliders(posX - 127, posY + 5);

            botonAuraColor(72, posY);
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
            botonesCabellos(113, alto + 3);
        } else if (currentPage == 2) {
            botonesClases(113, alto - 76);
            botonesAlignment(113,alto - 39);
        } else {

        }

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        //renderBackground(pGuiGraphics);

        var Altura = pGuiGraphics.guiHeight();
        var Ancho = pGuiGraphics.guiWidth();

        if (currentPage == 0) {

            var AlturaGui = this.height;
            var AnchoGui = this.width;
            this.customPanorama.render(pPartialTick, 1.0f);

            pagina0(pGuiGraphics, AnchoGui, AlturaGui);

        } else if (currentPage == 1) {
            for (ColorButton2 button : botonColorDefecto) {
                button.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
            }

            pagina1(pGuiGraphics);

            pagina1Color(pGuiGraphics);


        } else if (currentPage == 2) {

            pagina2(pGuiGraphics);
            pagina2Color(pGuiGraphics);

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
                clearAllButtons();
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
                            ModMessages.sendToServer(new CharacterC2S("hairColor", 6595598));
                            break;
                        case 3:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 1603072));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor2", 10478369));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16741888));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                            break;
                        case 4:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16777215));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor2", 15246079));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16726441));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 16711709));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 16711709));
                            ModMessages.sendToServer(new CharacterC2S("hairColor", 7471273));
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
                ModMessages.sendToServer(new CharacterC2S("auraColor", 8716287));

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
                this.removeWidget(auraButtonColor);
                clearAllButtons();
            }));
            //BOTON SIGUIENTE
            this.nextButton = (TextButton) this.addRenderableWidget(new TextButton(this.width - 85, posY, TranslateManager.NEXT.withStyle(ChatFormatting.BOLD), button -> {
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
                this.removeWidget(auraButtonColor);
                this.removeWidget(nextButton);
                this.removeWidget(botonRazaLeft);
                this.removeWidget(botonRazaRight);
                this.removeWidget(botonAlignmentRight);
                this.removeWidget(botonAlignmentLeft);
                clearAllButtons();
                currentPage = 2;

                sliders(this.width - 127, ((this.minecraft.getWindow().getGuiScaledHeight()) / 2) + 5);
                botonAuraColor(72, this.height / 2);
            }));
        } else {
            //BOTON VOLVER
            this.backButton = (TextButton) this.addRenderableWidget(new TextButton(20, posY, TranslateManager.BACK.withStyle(ChatFormatting.BOLD), button -> {
                currentPage = 1;
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
                this.removeWidget(auraButtonColor);
                this.removeWidget(claseRigthButton);
                this.removeWidget(claseLeftButton);
                this.removeWidget(botonAlignmentRight);
                this.removeWidget(botonAlignmentLeft);

                clearAllButtons();

                sliders(this.width - 127, ((this.minecraft.getWindow().getGuiScaledHeight()) / 2) + 5);
                botonesRazaColores(72, this.height / 2);

            }));

            //BOTON CONFIRMAR
            this.nextButton = (TextButton) this.addRenderableWidget(new TextButton(this.width - 85, posY, Component.literal("Confirm").withStyle(ChatFormatting.BOLD), button -> {
                this.removeWidget(sliderR);
                this.removeWidget(sliderG);
                this.removeWidget(sliderB);
                this.removeWidget(setColor);
                this.removeWidget(auraButtonColor);
                this.removeWidget(nextButton);
                clearAllButtons();

                ModMessages.sendToServer(new CharacterC2S("isConfirm", 1));
                this.minecraft.setScreen(null);
            }));
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
                        clearAllButtons();

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


                        botonPresetColor(this.width - 133, posY + 12, 16711680);
                        botonPresetColor(this.width - 113, posY + 12, 3093247);
                        botonPresetColor(this.width - 93, posY + 12, 16776978);
                        botonPresetColor(this.width - 73, posY + 12, 65535);
                        botonPresetColor(this.width - 53, posY + 12, 1051665);
                        botonPresetColor(this.width - 33, posY + 12, 5898388);
                        botonPresetColor(this.width - 128, posY + 28, 5963569);
                        botonPresetColor(this.width - 108, posY + 28, 16729088);
                        botonPresetColor(this.width - 88, posY + 28, 16731647);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", calcularColor(colorR, colorG, colorB)));
                        }));

                        this.partePagina = "ojo1";
                    }));
                    //BOTON COLOR OJO 2
                    this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY + 18, Component.empty(), button -> {
                        clearAllButtons();
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

                        botonPresetColor(this.width - 133, posY + 12, 16711680);
                        botonPresetColor(this.width - 113, posY + 12, 3093247);
                        botonPresetColor(this.width - 93, posY + 12, 16776978);
                        botonPresetColor(this.width - 73, posY + 12, 65535);
                        botonPresetColor(this.width - 53, posY + 12, 1051665);
                        botonPresetColor(this.width - 33, posY + 12, 5898388);

                        botonPresetColor(this.width - 128, posY + 28, 5963569);
                        botonPresetColor(this.width - 108, posY + 28, 16729088);
                        botonPresetColor(this.width - 88, posY + 28, 16731647);


                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", calcularColor(colorR, colorG, colorB)));
                        }));

                        this.partePagina = "ojo2";
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX, posY - 29, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16754861);
                        botonPresetColor(this.width - 113, posY + 12, 16756082);
                        botonPresetColor(this.width - 93, posY + 12, 16765368);
                        botonPresetColor(this.width - 73, posY + 12, 6501920);
                        botonPresetColor(this.width - 53, posY + 12, 6960687);
                        botonPresetColor(this.width - 33, posY + 12, 9660502);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo1";
                    }));


                    this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX, posY + 64, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 11603751);
                        botonPresetColor(this.width - 113, posY + 12, 2761084);
                        botonPresetColor(this.width - 93, posY + 12, 16777046);
                        botonPresetColor(this.width - 73, posY + 12, 2866128);
                        botonPresetColor(this.width - 53, posY + 12, 1051665);
                        botonPresetColor(this.width - 33, posY + 12, 5898388);

                        botonPresetColor(this.width - 128, posY + 28, 5963569);
                        botonPresetColor(this.width - 108, posY + 28, 16729088);
                        botonPresetColor(this.width - 88, posY + 28, 16731647);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("hairColor", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cabelloPagina";
                    }));
                    break;
                case 1:
                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX - 15, posY + 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16711680);
                        botonPresetColor(this.width - 113, posY + 12, 3093247);
                        botonPresetColor(this.width - 93, posY + 12, 16776978);
                        botonPresetColor(this.width - 73, posY + 12, 65535);
                        botonPresetColor(this.width - 53, posY + 12, 1051665);
                        botonPresetColor(this.width - 33, posY + 12, 5898388);

                        botonPresetColor(this.width - 128, posY + 28, 5963569);
                        botonPresetColor(this.width - 108, posY + 28, 16729088);
                        botonPresetColor(this.width - 88, posY + 28, 16731647);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo1";
                    }));
                    //BOTON COLOR OJO 2
                    this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY + 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16711680);
                        botonPresetColor(this.width - 113, posY + 12, 3093247);
                        botonPresetColor(this.width - 93, posY + 12, 16776978);
                        botonPresetColor(this.width - 73, posY + 12, 65535);
                        botonPresetColor(this.width - 53, posY + 12, 1051665);
                        botonPresetColor(this.width - 33, posY + 12, 5898388);

                        botonPresetColor(this.width - 128, posY + 28, 5963569);
                        botonPresetColor(this.width - 108, posY + 28, 16729088);
                        botonPresetColor(this.width - 88, posY + 28, 16731647);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo2";
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX, posY - 29, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16754861);
                        botonPresetColor(this.width - 113, posY + 12, 16756082);
                        botonPresetColor(this.width - 93, posY + 12, 16765368);
                        botonPresetColor(this.width - 73, posY + 12, 6501920);
                        botonPresetColor(this.width - 53, posY + 12, 6960687);
                        botonPresetColor(this.width - 33, posY + 12, 9660502);


                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo1";
                    }));


                    this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX, posY + 64, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 11603751);
                        botonPresetColor(this.width - 113, posY + 12, 2761084);
                        botonPresetColor(this.width - 93, posY + 12, 16777046);
                        botonPresetColor(this.width - 73, posY + 12, 2866128);
                        botonPresetColor(this.width - 53, posY + 12, 1051665);
                        botonPresetColor(this.width - 33, posY + 12, 5898388);

                        botonPresetColor(this.width - 128, posY + 28, 5963569);
                        botonPresetColor(this.width - 108, posY + 28, 16729088);
                        botonPresetColor(this.width - 88, posY + 28, 16731647);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("hairColor", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cabelloPagina";
                    }));
                    break;
                case 2:
                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX - 15, posY - 63, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16711680);
                        botonPresetColor(this.width - 113, posY + 12, 3093247);
                        botonPresetColor(this.width - 93, posY + 12, 16776978);
                        botonPresetColor(this.width - 73, posY + 12, 65535);
                        botonPresetColor(this.width - 53, posY + 12, 1051665);
                        botonPresetColor(this.width - 33, posY + 12, 5898388);

                        botonPresetColor(this.width - 128, posY + 28, 5963569);
                        botonPresetColor(this.width - 108, posY + 28, 16729088);
                        botonPresetColor(this.width - 88, posY + 28, 16731647);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo1";
                    }));
                    //BOTON COLOR OJO 2
                    this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY - 63, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16711680);
                        botonPresetColor(this.width - 113, posY + 12, 3093247);
                        botonPresetColor(this.width - 93, posY + 12, 16776978);
                        botonPresetColor(this.width - 73, posY + 12, 65535);
                        botonPresetColor(this.width - 53, posY + 12, 1051665);
                        botonPresetColor(this.width - 33, posY + 12, 5898388);

                        botonPresetColor(this.width - 128, posY + 28, 5963569);
                        botonPresetColor(this.width - 108, posY + 28, 16729088);
                        botonPresetColor(this.width - 88, posY + 28, 16731647);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo2";
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX - 33, posY - 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 1474571);
                        botonPresetColor(this.width - 113, posY + 12, 3060257);
                        botonPresetColor(this.width - 93, posY + 12, 8892948);
                        botonPresetColor(this.width - 73, posY + 12, 12385321);
                        botonPresetColor(this.width - 53, posY + 12, 23337);
                        botonPresetColor(this.width - 33, posY + 12, 15872);


                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo1";
                    }));

                    this.bodyButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor2", posX - 11, posY - 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 12263460);
                        botonPresetColor(this.width - 113, posY + 12, 16711716);
                        botonPresetColor(this.width - 93, posY + 12, 16722688);
                        botonPresetColor(this.width - 73, posY + 12, 13183853);
                        botonPresetColor(this.width - 53, posY + 12, 16742144);
                        botonPresetColor(this.width - 33, posY + 12, 8650752);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor2", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo2";
                    }));

                    this.bodyButtonColor3 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor3", posX + 11, posY - 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16761651);
                        botonPresetColor(this.width - 113, posY + 12, 16744043);
                        botonPresetColor(this.width - 93, posY + 12, 16733291);
                        botonPresetColor(this.width - 73, posY + 12, 16766262);
                        botonPresetColor(this.width - 53, posY + 12, 16735557);
                        botonPresetColor(this.width - 33, posY + 12, 12941637);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor3", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo3";
                    }));

                    this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX + 33, posY - 18, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 13611365);
                        botonPresetColor(this.width - 113, posY + 12, 13631306);
                        botonPresetColor(this.width - 93, posY + 12, 13612593);
                        botonPresetColor(this.width - 73, posY + 12, 7731455);
                        botonPresetColor(this.width - 53, posY + 12, 16752895);
                        botonPresetColor(this.width - 33, posY + 12, 16772351);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("hairColor", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cabelloPagina";
                    }));
                    break;
                case 3:

                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX, posY - 63, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16711680);
                        botonPresetColor(this.width - 113, posY + 12, 3093247);
                        botonPresetColor(this.width - 93, posY + 12, 16776978);
                        botonPresetColor(this.width - 73, posY + 12, 65535);
                        botonPresetColor(this.width - 53, posY + 12, 1051665);
                        botonPresetColor(this.width - 33, posY + 12, 5898388);

                        botonPresetColor(this.width - 128, posY + 28, 5963569);
                        botonPresetColor(this.width - 108, posY + 28, 16729088);
                        botonPresetColor(this.width - 88, posY + 28, 16731647);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo1";
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX - 25, posY - 17, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 1603072);
                        botonPresetColor(this.width - 113, posY + 12, 1603255);
                        botonPresetColor(this.width - 93, posY + 12, 13045263);
                        botonPresetColor(this.width - 73, posY + 12, 2237732);
                        botonPresetColor(this.width - 53, posY + 12, 8723455);
                        botonPresetColor(this.width - 33, posY + 12, 5752120);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo1";
                    }));

                    this.bodyButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor2", posX, posY - 17, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 5752120);
                        botonPresetColor(this.width - 113, posY + 12, 9961256);
                        botonPresetColor(this.width - 93, posY + 12, 13826075);
                        botonPresetColor(this.width - 73, posY + 12, 13826210);
                        botonPresetColor(this.width - 53, posY + 12, 13802495);
                        botonPresetColor(this.width - 33, posY + 12, 13802327);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor2", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo2";
                    }));

                    this.bodyButtonColor3 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor3", posX + 25, posY - 17, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16718336);
                        botonPresetColor(this.width - 113, posY + 12, 16738560);
                        botonPresetColor(this.width - 93, posY + 12, 16722177);
                        botonPresetColor(this.width - 73, posY + 12, 16722247);
                        botonPresetColor(this.width - 53, posY + 12, 6563248);
                        botonPresetColor(this.width - 33, posY + 12, 6567522);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor3", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo3";
                    }));


                    break;
                case 4:
                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX - 15, posY - 63, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16711680);
                        botonPresetColor(this.width - 113, posY + 12, 3093247);
                        botonPresetColor(this.width - 93, posY + 12, 16776978);
                        botonPresetColor(this.width - 73, posY + 12, 65535);
                        botonPresetColor(this.width - 53, posY + 12, 1051665);
                        botonPresetColor(this.width - 33, posY + 12, 5898388);

                        botonPresetColor(this.width - 128, posY + 28, 5963569);
                        botonPresetColor(this.width - 108, posY + 28, 16729088);
                        botonPresetColor(this.width - 88, posY + 28, 16731647);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo1";
                    }));
                    //BOTON COLOR OJO 2
                    this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY - 63, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16711680);
                        botonPresetColor(this.width - 113, posY + 12, 3093247);
                        botonPresetColor(this.width - 93, posY + 12, 16776978);
                        botonPresetColor(this.width - 73, posY + 12, 65535);
                        botonPresetColor(this.width - 53, posY + 12, 1051665);
                        botonPresetColor(this.width - 33, posY + 12, 5898388);

                        botonPresetColor(this.width - 128, posY + 28, 5963569);
                        botonPresetColor(this.width - 108, posY + 28, 16729088);
                        botonPresetColor(this.width - 88, posY + 28, 16731647);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo2";
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX - 33, posY - 14, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16185076);
                        botonPresetColor(this.width - 113, posY + 12, 9550335);
                        botonPresetColor(this.width - 93, posY + 12, 16759188);
                        botonPresetColor(this.width - 73, posY + 12, 16741082);
                        botonPresetColor(this.width - 53, posY + 12, 3222826);
                        botonPresetColor(this.width - 33, posY + 12, 7665147);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo1";
                    }));

                    this.bodyButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor2", posX - 11, posY - 14, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 15171579);
                        botonPresetColor(this.width - 113, posY + 12, 15182331);
                        botonPresetColor(this.width - 93, posY + 12, 11009023);
                        botonPresetColor(this.width - 73, posY + 12, 10562395);
                        botonPresetColor(this.width - 53, posY + 12, 6821467);
                        botonPresetColor(this.width - 33, posY + 12, 5991935);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor2", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo2";
                    }));

                    this.bodyButtonColor3 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor3", posX + 11, posY - 14, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 15676525);
                        botonPresetColor(this.width - 113, posY + 12, 15667217);
                        botonPresetColor(this.width - 93, posY + 12, 8082431);
                        botonPresetColor(this.width - 73, posY + 12, 4009215);
                        botonPresetColor(this.width - 53, posY + 12, 13804590);
                        botonPresetColor(this.width - 33, posY + 12, 16718964);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor3", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cuerpo3";
                    }));

                    this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX + 33, posY - 14, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16121880);
                        botonPresetColor(this.width - 113, posY + 12, 4391015);
                        botonPresetColor(this.width - 93, posY + 12, 8655866);
                        botonPresetColor(this.width - 73, posY + 12, 5114);
                        botonPresetColor(this.width - 53, posY + 12, 16729082);
                        botonPresetColor(this.width - 33, posY + 12, 7187240);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("hairColor", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "cabelloPagina";
                    }));
                    break;
                case 5:
                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX, posY + 57, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16711680);
                        botonPresetColor(this.width - 113, posY + 12, 3093247);
                        botonPresetColor(this.width - 93, posY + 12, 16776978);
                        botonPresetColor(this.width - 73, posY + 12, 65535);
                        botonPresetColor(this.width - 53, posY + 12, 1051665);
                        botonPresetColor(this.width - 33, posY + 12, 5898388);

                        botonPresetColor(this.width - 128, posY + 28, 5963569);
                        botonPresetColor(this.width - 108, posY + 28, 16729088);
                        botonPresetColor(this.width - 88, posY + 28, 16731647);


                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", calcularColor(colorR, colorG, colorB)));

                        }));
                        this.partePagina = "ojo1";
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX, posY - 30, Component.empty(), button -> {
                        this.removeWidget(setColor);
                        clearAllButtons();

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

                        botonPresetColor(this.width - 133, posY + 12, 16753919);
                        botonPresetColor(this.width - 113, posY + 12, 16753744);
                        botonPresetColor(this.width - 93, posY + 12, 8037631);
                        botonPresetColor(this.width - 73, posY + 12, 16745006);
                        botonPresetColor(this.width - 53, posY + 12, 4998730);
                        botonPresetColor(this.width - 33, posY + 12, 7156385);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
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

    public void botonAuraColor(int posX, int posY) {
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        this.removeWidget(eyesButtonColor);
        this.removeWidget(eyesButtonColor2);
        this.removeWidget(bodyButtonColor1);
        this.removeWidget(bodyButtonColor2);
        this.removeWidget(bodyButtonColor3);
        this.removeWidget(hairButtonColor);
        this.removeWidget(auraButtonColor);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            this.auraButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("auraColor", posX, posY + 3, Component.empty(), button -> {
                this.removeWidget(setColor);
                clearAllButtons();

                int auraColor = cap.getAuraColor();

                float r = (auraColor >> 16) / 255.0F;
                float g = ((auraColor >> 8) & 0xff) / 255.0f;
                float b = (auraColor & 0xff) / 255.0f;

                colorR = (int) (r * 255);
                colorG = (int) (g * 255);
                colorB = (int) (b * 255);

                sliderR.setValue(colorR);
                sliderG.setValue(colorG);
                sliderB.setValue(colorB);

                botonPresetColor(this.width - 133, posY + 12, 5636095);
                botonPresetColor(this.width - 113, posY + 12, 13793279);
                botonPresetColor(this.width - 93, posY + 12, 16647168);
                botonPresetColor(this.width - 73, posY + 12, 50432);
                botonPresetColor(this.width - 53, posY + 12, 16762112);
                botonPresetColor(this.width - 33, posY + 12, 16777215);

                this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY + 45, Component.literal("SET"), wa -> {
                    ModMessages.sendToServer(new CharacterC2S("auraColor", calcularColor(colorR, colorG, colorB)));

                }));
                this.partePagina = "AuraPagina";
            }));

            RenderSystem.disableBlend();
        });

    }

    public void botonesCabellos(int posX, int posY) {

        this.removeWidget(hairRigthButton);
        this.removeWidget(hairLeftButton);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            switch (cap.getRace()) {
                case 0:
                    if (cap.getHairID() == 0) {
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));

                    } else if (cap.getHairID() == 1) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 0));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    } else if (cap.getHairID() == 2) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 3));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    } else if (cap.getHairID() == 3) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    }

                    break;
                case 1:
                    if (cap.getHairID() == 0) {
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));

                    } else if (cap.getHairID() == 1) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 0));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    } else if (cap.getHairID() == 2) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 3));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    } else if (cap.getHairID() == 3) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 2));
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
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));

                    } else if (cap.getHairID() == 1) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 0));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    } else if (cap.getHairID() == 2) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                        this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 3));
                            this.removeWidget(hairRigthButton);
                            this.removeWidget(hairLeftButton);
                        }));
                    } else if (cap.getHairID() == 3) {
                        this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+47, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("hairID", 2));
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
    public void botonesClases(int posX, int posY) {

        this.removeWidget(claseLeftButton);
        this.removeWidget(claseRigthButton);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            if(cap.getDmzClass().equals("Warrior")){
                this.claseRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("dmzClass", 1));
                    this.removeWidget(claseRigthButton);
                    this.removeWidget(claseLeftButton);
                }));
            }else {
                this.claseLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("dmzClass", 0));
                    this.removeWidget(claseRigthButton);
                    this.removeWidget(claseLeftButton);
                }));
            }

        });
    }
    public void botonesAlignment(int posX, int posY) {

        this.removeWidget(botonAlignmentLeft);
        this.removeWidget(botonAlignmentRight);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            if(cap.getDmzAlignment().equals("Good")){
                this.botonAlignmentRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("dmzAlignment", 1));
                    this.removeWidget(botonAlignmentRight);
                    this.removeWidget(botonAlignmentLeft);
                }));
            }else {
                this.botonAlignmentLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("dmzAlignment", 0));
                    this.removeWidget(botonAlignmentRight);
                    this.removeWidget(botonAlignmentLeft);
                }));
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
                    if (cap.getBodytype() == 0) {
                        this.bodyTypeRightButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY+14, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    } else if (cap.getBodytype() == 1) {
                        this.bodyTypeLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+14, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                        this.bodyTypeRightButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY+14, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 2));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    } else if(cap.getBodytype() == 2){
                        this.bodyTypeLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+14, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    }
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
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16777215));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 15246079));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16726441));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 16711709));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 16711709));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 7471273));
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
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16777215));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 15246079));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16726441));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 16711709));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 16711709));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 7471273));
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
        pGuiGraphics.blit(texto, (this.width / 2) - 60, 10, 0, 16, 130, 18);

        RenderSystem.disableBlend();


        //TITULO
        alturaTexto = (posY / 2) - 40;
        anchoTexto = ((posX - this.font.width(TranslateManager.CCreation)) / 2);

        //drawStringWithBorder(pGuiGraphics, font, TranslateManager.CCreation, anchoTexto, 16, 0xFFFFFF);
        pGuiGraphics.drawString(font, TranslateManager.CCreation, anchoTexto + 7, 16, 0xFFFFFF, true);


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

            //LivingEntity dino = new DinoEntity(MainEntity.DINO1.get(), this.minecraft.level);
            renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, alturaTexto - 10, 70, 0, 0, minecraft.player);


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

                    if(cap.getBodytype() == 0){
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 01"), 62, alturaTexto - 26, 0xFFFFFF);
                    }

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

    public void pagina2(GuiGraphics pGuiGraphics) {

        //MENU CARACTERISTICAS
        alturaTexto = (pGuiGraphics.guiHeight() / 2);
        anchoTexto = 10;
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        pGuiGraphics.blit(menu1, anchoTexto, alturaTexto - 110, 0, 0, 148, 222);
        RenderSystem.disableBlend();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, minecraft.player).ifPresent(cap -> {

            //CLASE DEL JUGADOR
            alturaTexto = (pGuiGraphics.guiHeight() / 2);
            anchoTexto = (20 - this.font.width(TranslateManager.CLASS_TYPE));
            pGuiGraphics.drawString(font, TranslateManager.CLASS_TYPE.withStyle(ChatFormatting.BOLD), anchoTexto + 83, alturaTexto - 89, 0xFF9B9B);

            if(cap.getDmzClass().equals("Warrior")){
                anchoTexto = (20 - this.font.width(TranslateManager.CLASS_1));
                drawStringWithBorder(pGuiGraphics, font, TranslateManager.CLASS_1, anchoTexto + 85, alturaTexto - 72, 0xFC4E2B);

            } else {
                anchoTexto = (20 - this.font.width(TranslateManager.CLASS_2));
                drawStringWithBorder(pGuiGraphics, font, TranslateManager.CLASS_2, anchoTexto + 92, alturaTexto - 72, 0x2BFCFC);

            }

            //ALINEAMIENTO
            anchoTexto = 47;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, texto);
            pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 56, 0, 0, 73, 15);
            RenderSystem.disableBlend();

            anchoTexto = (20 - this.font.width(TranslateManager.ALIGNMENT));
            pGuiGraphics.drawString(font, TranslateManager.ALIGNMENT.withStyle(ChatFormatting.BOLD), anchoTexto + 92, alturaTexto - 52, 0xFFDEDE);

            if(cap.getDmzAlignment().equals("Good")){
                anchoTexto = (20 - this.font.width(TranslateManager.ALIGNMENT_GOOD));
                drawStringWithBorder(pGuiGraphics, font, TranslateManager.ALIGNMENT_GOOD, anchoTexto + 75, alturaTexto - 35, 0x1EFFD9, 0x1E6CFF);

            } else {
                anchoTexto = (20 - this.font.width(TranslateManager.ALIGNMENT_EVIL));
                drawStringWithBorder(pGuiGraphics, font, TranslateManager.ALIGNMENT_EVIL, anchoTexto + 72, alturaTexto - 35, 0xFF3D72,0xF61414);

            }


            //COLOR DE KI
            anchoTexto = 47;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, texto);
            pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 16, 0, 0, 73, 15);
            RenderSystem.disableBlend();

            anchoTexto = (20 - this.font.width(TranslateManager.COLOR_AURA));
            pGuiGraphics.drawString(font, TranslateManager.COLOR_AURA.withStyle(ChatFormatting.BOLD), anchoTexto + 96, alturaTexto - 12, 0xFFCA9B);

        });


    }
    public void pagina2Color(GuiGraphics pGuiGraphics) {

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
            case "AuraPagina":
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
    private void botonPresetColor(int posX, int posY, int color){
        ColorButton2 presetWa = (ColorButton2) this.addRenderableWidget(new ColorButton2(posX, posY,color,Component.empty(), wa -> {

            float r = (color >> 16) / 255.0F;
            float g = ((color >> 8) & 0xff) / 255.0f;
            float b = (color & 0xff) / 255.0f;

            colorR = (int) (r * 255);
            colorG = (int) (g * 255);
            colorB = (int) (b * 255);

            sliderR.setValue(colorR);
            sliderG.setValue(colorG);
            sliderB.setValue(colorB);
        }));

        botonColorDefecto.add(presetWa);
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

        // Guardar las rotaciones actuales de la entidad
        float f2 = livingEntity.yBodyRot;
        float f3 = livingEntity.getYRot();
        float f4 = livingEntity.getXRot();
        float f5 = livingEntity.yHeadRotO;
        float f6 = livingEntity.yHeadRot;

        // Ajustar la rotación del cuerpo y de la cabeza
        livingEntity.yBodyRot = 180.0F + angleXComponent * 20.0F;
        livingEntity.yHeadRot = livingEntity.yBodyRot;
        livingEntity.yHeadRotO = livingEntity.yBodyRot;

        // Renderizar la entidad
        renderEntityInInv(guiGraphics, x, y, scale, quaternionf, quaternionf1, livingEntity);

        // Restaurar las rotaciones originales de la entidad
        livingEntity.yBodyRot = f2;
        livingEntity.setYRot(f3);
        livingEntity.setXRot(f4);
        livingEntity.yHeadRotO = f5;
        livingEntity.yHeadRot = f6;
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

    private void clearAllButtons() {
        for (ColorButton2 button : botonColorDefecto) {
            this.removeWidget(button);
        }
        botonColorDefecto.clear();
    }
}
