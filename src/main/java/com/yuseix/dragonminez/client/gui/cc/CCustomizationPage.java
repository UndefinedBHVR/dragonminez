package com.yuseix.dragonminez.client.gui.cc;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.ColorButton;
import com.yuseix.dragonminez.client.gui.buttons.ColorButton2;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.TextButton;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.TranslateManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
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

public class CCustomizationPage extends Screen {

    private int alturaTexto;
    private int anchoTexto;

    //Textura de los panoramas
    private static final ResourceLocation PANORAMA_PATH = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/panorama");
    private static final ResourceLocation PANORAMA_BUU = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/buu_panorama");
    private static final ResourceLocation PANORAMA_BIO = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/bio_panorama");
    private static final ResourceLocation PANORAMA_SAIYAN = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/s_panorama");
    private static final ResourceLocation PANORAMA_NAMEK = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/n_panorama");
    private static final ResourceLocation PANORAMA_COLD = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/c_panorama");

    //Panoramas
    private final PanoramaRenderer customPanorama = new PanoramaRenderer(new CubeMap(PANORAMA_PATH));
    private final PanoramaRenderer panoramaBuu = new PanoramaRenderer(new CubeMap(PANORAMA_BUU));
    private final PanoramaRenderer panoramaBio = new PanoramaRenderer(new CubeMap(PANORAMA_BIO));
    private final PanoramaRenderer panoramaSai = new PanoramaRenderer(new CubeMap(PANORAMA_SAIYAN));
    private final PanoramaRenderer panoramaNam = new PanoramaRenderer(new CubeMap(PANORAMA_NAMEK));
    private final PanoramaRenderer panoramaCold = new PanoramaRenderer(new CubeMap(PANORAMA_COLD));


    private static final ResourceLocation menu1 = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menugrande.png");

    private static final ResourceLocation texto = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menutexto.png");
    private static final ResourceLocation colorCuadrado = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/buttons/characterbuttons.png");

    private final List<ColorButton2> botonColorDefecto = new ArrayList<>();

    private DMZRightButton eyesTypeRight, eyesTypeLeft, bodyTypeRightButton, bodyTypeLeftButton, gendersRigthButton, gendersLeftButton, hairRigthButton, hairLeftButton, claseRigthButton,claseLeftButton;
    private DMZRightButton botonAlignmentRight, botonAlignmentLeft;
    private TextButton nextButton, backButton;
    private ColorButton eyesButtonColor, eyesButtonColor2, bodyButtonColor1, bodyButtonColor2, bodyButtonColor3, hairButtonColor, auraButtonColor;
    private int currentPage = 0;
    private static String partePagina = "";


    public CCustomizationPage(Component pTitle) {
        super(pTitle);

    }

    @Override
    protected void init() {

        //MenuInicio
        int posX = (this.width);
        int posY = (this.minecraft.getWindow().getGuiScaledHeight()) / 2;

        if (currentPage == 0) {

            botonesRazaColores(72, posY);
        } else if (currentPage == 1) {

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

            botonesBodyType(113, alto - 44);
            botonesGeneros(113, alto - 76);
            botonesOjos(113, alto + 3);
            botonesCabellos(113, alto + 3);

        } else if (currentPage == 1) {
            botonesClases(113, alto - 76);
            botonesAlignment(113,alto - 39);

        } else if (currentPage == 2) {

        } else {

        }

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        //renderBackground(pGuiGraphics);

        var Altura = pGuiGraphics.guiHeight();
        var Ancho = pGuiGraphics.guiWidth();

        panoramas(pGuiGraphics, pPartialTick);

        if (currentPage == 0) {
            for (ColorButton2 button : botonColorDefecto) {
                button.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
            }

            pagina1(pGuiGraphics);


        } else if (currentPage == 1) {

            pagina2(pGuiGraphics);

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
            //BOTON VOLVER
            this.backButton = (TextButton) this.addRenderableWidget(new TextButton(20, posY, TranslateManager.BACK.withStyle(ChatFormatting.BOLD), button -> {
                /*
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

                 */
                ModMessages.sendToServer(new CharacterC2S("hairID", 0));
                ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                this.minecraft.setScreen(new CFirstPage());

            }));

            //BOTON SIGUIENTE
            this.nextButton = (TextButton) this.addRenderableWidget(new TextButton(this.width - 85, posY, TranslateManager.NEXT.withStyle(ChatFormatting.BOLD), button -> {
                this.removeWidget(eyesButtonColor);
                this.removeWidget(eyesButtonColor2);
                this.removeWidget(bodyButtonColor1);
                this.removeWidget(bodyButtonColor2);
                this.removeWidget(bodyButtonColor3);
                this.removeWidget(hairButtonColor);
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
                this.removeWidget(botonAlignmentRight);
                this.removeWidget(botonAlignmentLeft);
                clearAllButtons();
                currentPage = 1;

                botonAuraColor(72, this.height / 2);
            }));
        } else if (currentPage == 1) {
            //BOTON VOLVER
            this.backButton = (TextButton) this.addRenderableWidget(new TextButton(20, posY, TranslateManager.BACK.withStyle(ChatFormatting.BOLD), button -> {
                currentPage = 0;
                this.removeWidget(eyesButtonColor);
                this.removeWidget(eyesButtonColor2);
                this.removeWidget(bodyButtonColor1);
                this.removeWidget(bodyButtonColor2);
                this.removeWidget(bodyButtonColor3);
                this.removeWidget(hairButtonColor);
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

                botonesRazaColores(72, this.height / 2);

            }));

            //BOTON CONFIRMAR
            this.nextButton = (TextButton) this.addRenderableWidget(new TextButton(this.width - 85, posY, Component.literal("Confirm").withStyle(ChatFormatting.BOLD), button -> {
                this.removeWidget(auraButtonColor);
                this.removeWidget(nextButton);
                clearAllButtons();

                ModMessages.sendToServer(new CharacterC2S("isConfirm", 1));
                this.minecraft.setScreen(null);
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
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("eye1Color"));
                    }));
                    //BOTON COLOR OJO 2
                    this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY + 18, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("eyeColor2"));
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX, posY - 29, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor1"));
                    }));


                    this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX, posY + 64, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("hairColor"));
                    }));
                    break;
                case 1:
                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX - 15, posY + 18, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("eye1Color"));
                    }));
                    //BOTON COLOR OJO 2
                    this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY + 18, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("eyeColor2"));
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX, posY - 29, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor1"));
                    }));


                    this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX, posY + 64, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("hairColor"));
                    }));
                    break;
                case 2:
                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX - 15, posY - 63, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("eye1Color"));
                    }));
                    //BOTON COLOR OJO 2
                    this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY - 63, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("eyeColor2"));
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX - 33, posY - 18, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor1"));
                    }));

                    this.bodyButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor2", posX - 11, posY - 18, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor2"));
                    }));

                    this.bodyButtonColor3 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor3", posX + 11, posY - 18, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor3"));
                    }));

                    this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX + 33, posY - 18, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("hairColor"));
                    }));
                    break;
                case 3:

                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX, posY - 63, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("eye1Color"));
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX - 25, posY - 17, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor1"));
                    }));

                    this.bodyButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor2", posX, posY - 17, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor2"));
                    }));

                    this.bodyButtonColor3 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor3", posX + 25, posY - 17, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor3"));
                    }));


                    break;
                case 4:
                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX - 15, posY - 63, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("eye1Color"));
                    }));
                    //BOTON COLOR OJO 2
                    this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2", posX + 15, posY - 63, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("eyeColor2"));
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX - 33, posY - 14, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor1"));
                    }));

                    this.bodyButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor2", posX - 11, posY - 14, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor2"));
                    }));

                    this.bodyButtonColor3 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor3", posX + 11, posY - 14, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor3"));
                    }));

                    this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor", posX + 33, posY - 14, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("hairColor"));
                    }));
                    break;
                case 5:
                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1", posX, posY + 57, Component.empty(), button -> {

                        Minecraft.getInstance().setScreen(new ColorPickerScreen("eye1Color"));
                    }));

                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1", posX, posY - 30, Component.empty(), button -> {
                        Minecraft.getInstance().setScreen(new ColorPickerScreen("BodyColor1"));
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
                    if(cap.getGender().equals("Female")){

                        if (cap.getHairID() == 0) {
                            this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 3, Component.empty(), button -> {
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
                            this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 3, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                        } else if (cap.getHairID() == 2) {
                            this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+3, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 1));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                            this.hairRigthButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY + 3, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 3));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                        } else if (cap.getHairID() == 3) {
                            this.hairLeftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY+3, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("hairID", 2));
                                this.removeWidget(hairRigthButton);
                                this.removeWidget(hairLeftButton);
                            }));
                        }

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
                    anchoTexto = 65;
                    pGuiGraphics.drawString(font, Component.literal("Gender").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 89, 0xC07FFD);

                    switch (cap.getGender()) {
                        case "Male", "male":
                            anchoTexto = 74;
                            drawStringWithBorder(pGuiGraphics, font, Component.literal("Male"), anchoTexto, alturaTexto - 72, 0xFFFFFF, 0x2133A6);
                            break;
                        case "Female", "female":
                            anchoTexto = 68;
                            drawStringWithBorder(pGuiGraphics, font, Component.literal("Female"), anchoTexto, alturaTexto - 72, 0xFFFFFF, 0xFC63D9);
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
                    anchoTexto = 53;
                    pGuiGraphics.drawString(font, Component.literal("Body Style").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 57, 0xFFCA9B);

                    //TIPO DE CUERPO
                    if (cap.getBodytype() == 0) {
                        anchoTexto = 66;
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 0"), anchoTexto, alturaTexto - 40, 0xFFFFFF);
                    } else if (cap.getBodytype() == 1) {
                        anchoTexto = 66;
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 1"), anchoTexto, alturaTexto - 40, 0xFFFFFF);

                    }

                    //OJOS TIPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 13, 0, 0, 73, 15);
                    RenderSystem.disableBlend();
                    //OJOS TITULO
                    anchoTexto = 53;
                    pGuiGraphics.drawString(font, Component.literal("Eyes Style").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 10, 0xFF9B9B);

                    anchoTexto = 63;
                    if (cap.getEyesType() == 0) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto + 7, 0xFFFFFF);
                    } else if (cap.getEyesType() == 1) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto + 7, 0xFFFFFF);
                    } else if (cap.getEyesType() == 2){
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 03"), anchoTexto, alturaTexto + 7, 0xFFFFFF);
                    }

                    //HAIR COLOR
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto + 34, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    //HAIR TITULO
                    anchoTexto = 72;
                    pGuiGraphics.drawString(font, Component.literal("Hair").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto + 38, 0x65FC63);

                    anchoTexto = 63;
                    if (cap.getHairID() == 0) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getHairID() == 1) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getHairID() == 2) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 03"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getHairID() == 3) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 04"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    }

                    break;
                case 1:
                    //GENERO TITULO
                    alturaTexto = (pGuiGraphics.guiHeight() / 2);
                    anchoTexto = 65;
                    pGuiGraphics.drawString(font, Component.literal("Gender").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 89, 0xC07FFD);

                    switch (cap.getGender()) {
                        case "Male", "male":
                            anchoTexto = 74;
                            drawStringWithBorder(pGuiGraphics, font, Component.literal("Male"), anchoTexto, alturaTexto - 72, 0xFFFFFF, 0x2133A6);
                            break;
                        case "Female", "female":
                            anchoTexto = 68;
                            drawStringWithBorder(pGuiGraphics, font, Component.literal("Female"), anchoTexto, alturaTexto - 72, 0xFFFFFF, 0xFC63D9);
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
                    anchoTexto = 53;
                    pGuiGraphics.drawString(font, Component.literal("Body Style").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 57, 0xFFCA9B);

                    //TIPO DE CUERPO
                    if (cap.getBodytype() == 0) {
                        anchoTexto = 66;
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 0"), anchoTexto, alturaTexto - 40, 0xFFFFFF);
                    } else if (cap.getBodytype() == 1) {
                        anchoTexto = 66;
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 1"), anchoTexto, alturaTexto - 40, 0xFFFFFF);

                    }

                    //OJOS TIPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 13, 0, 0, 73, 15);
                    RenderSystem.disableBlend();
                    //OJOS TITULO
                    anchoTexto = 53;
                    pGuiGraphics.drawString(font, Component.literal("Eyes Style").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 10, 0xFF9B9B);

                    anchoTexto = 63;
                    if (cap.getEyesType() == 0) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto + 7, 0xFFFFFF);
                    } else if (cap.getEyesType() == 1) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto + 7, 0xFFFFFF);
                    } else if (cap.getEyesType() == 2){
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 03"), anchoTexto, alturaTexto + 7, 0xFFFFFF);
                    }

                    //HAIR COLOR
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto + 34, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    //HAIR TITULO
                    anchoTexto = 72;
                    pGuiGraphics.drawString(font, Component.literal("Hair").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto + 38, 0x65FC63);

                    anchoTexto = 63;
                    if (cap.getHairID() == 0) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getHairID() == 1) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getHairID() == 2) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 03"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    } else if (cap.getHairID() == 3) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 04"), anchoTexto, alturaTexto + 54, 0xFFFFFF);
                    }

                    break;
                case 2:
                    //TIPO DE OJOS
                    alturaTexto = (pGuiGraphics.guiHeight() / 2);
                    anchoTexto = 53;
                    pGuiGraphics.drawString(font, Component.literal("Eyes Style").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 89, 0xFF9B9B);

                    anchoTexto = 63;
                    if (cap.getEyesType() == 0) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto - 73, 0xFFFFFF);
                    } else if (cap.getEyesType() == 1) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto - 73, 0xFFFFFF);
                    } else if (cap.getEyesType() == 2){
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 03"), anchoTexto,alturaTexto - 73, 0xFFFFFF);
                    }

                    //TIPO DE CUERPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 48, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    //CUERPO TITULO
                    anchoTexto = 52;
                    pGuiGraphics.drawString(font, Component.literal("Body Style").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 44, 0xFFCA9B);

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
                    anchoTexto = 53;
                    pGuiGraphics.drawString(font, Component.literal("Eyes Style").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 89, 0xFF9B9B);

                    anchoTexto = 63;
                    drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto - 74, 0xFFFFFF);

                    //TIPO DE CUERPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 46, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    anchoTexto = 53;
                    pGuiGraphics.drawString(font, Component.literal("Body Style").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 42, 0xFFCA9B);

                    anchoTexto = 50;
                    drawStringWithBorder(pGuiGraphics, font, Component.literal("Dr.Gero Style"), anchoTexto, alturaTexto - 28, 0xFFFFFF);

                    break;
                case 4:
                    //TIPO DE OJOS
                    alturaTexto = (pGuiGraphics.guiHeight() / 2);
                    anchoTexto = 53;
                    pGuiGraphics.drawString(font, Component.literal("Eyes Style").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 89, 0xFF9B9B);

                    anchoTexto = 64;
                    drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto - 74, 0xFFFFFF);

                    //TIPO DE CUERPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 46, 0, 0, 73, 15);
                    RenderSystem.disableBlend();

                    anchoTexto = 53;
                    pGuiGraphics.drawString(font, Component.literal("Body Style").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 42, 0xFFCA9B);

                    anchoTexto = 64;
                    if(cap.getBodytype() == 0){
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto - 26, 0xFFFFFF);
                    } else if(cap.getBodytype() == 1){
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto - 26, 0xFFFFFF);
                    } else if(cap.getBodytype() == 2){
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 03"), anchoTexto, alturaTexto - 26, 0xFFFFFF);
                    }

                    break;
                case 5:
                    //GENERO TITULO
                    alturaTexto = (pGuiGraphics.guiHeight() / 2);
                    anchoTexto = 65;
                    pGuiGraphics.drawString(font, Component.literal("Gender").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 89, 0xC07FFD);

                    switch (cap.getGender()) {
                        case "Male", "male":
                            anchoTexto = 74;
                            drawStringWithBorder(pGuiGraphics, font, Component.literal("Male"), anchoTexto, alturaTexto - 72, 0xFFFFFF, 0x2133A6);
                            break;
                        case "Female", "female":
                            anchoTexto = 68;
                            drawStringWithBorder(pGuiGraphics, font, Component.literal("Female"), anchoTexto, alturaTexto - 72, 0xFFFFFF, 0xFC63D9);
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
                    anchoTexto = 53;
                    pGuiGraphics.drawString(font, Component.literal("Body Style").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 57, 0xFFCA9B);

                    //TIPO DE CUERPO
                    if (cap.getBodytype() == 0) {
                        anchoTexto = 65;
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Default"), anchoTexto, alturaTexto - 40, 0xFFFFFF);
                    } else if (cap.getBodytype() == 1) {
                        anchoTexto = 65;
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 1"), anchoTexto, alturaTexto - 40, 0xFFFFFF);

                    }

                    //TIPO DE CABELLO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 13, 0, 0, 73, 15);
                    RenderSystem.disableBlend();
                    //HAIR TITULO
                    anchoTexto = 73;
                    pGuiGraphics.drawString(font, Component.literal("Hair").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 9, 0x65FC63);

                    anchoTexto = 64;
                    if (cap.getHairID() == 0) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto + 9, 0xFFFFFF);
                    } else if (cap.getHairID() == 1) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto + 9, 0xFFFFFF);
                    } else if (cap.getHairID() == 2) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 03"), anchoTexto, alturaTexto + 9, 0xFFFFFF);
                    } else if (cap.getHairID() == 3) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 04"), anchoTexto, alturaTexto + 9, 0xFFFFFF);
                    }

                    //OJOS TIPO
                    anchoTexto = 47;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.setShaderTexture(0, texto);
                    pGuiGraphics.blit(texto, anchoTexto, alturaTexto + 23, 0, 0, 73, 15);
                    RenderSystem.disableBlend();
                    //OJOS TITULO
                    anchoTexto = 53;
                    pGuiGraphics.drawString(font, Component.literal("Eyes Style").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto + 27, 0xFF9B9B);

                    anchoTexto = 63;
                    if (cap.getEyesType() == 0) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 01"), anchoTexto, alturaTexto + 44, 0xFFFFFF);
                    } else if (cap.getEyesType() == 1) {
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 02"), anchoTexto, alturaTexto + 44, 0xFFFFFF);
                    } else if (cap.getEyesType() == 2){
                        drawStringWithBorder(pGuiGraphics, font, Component.literal("Type 03"), anchoTexto,alturaTexto + 44, 0xFFFFFF);
                    }
                    break;
                default:
                    break;
            }
        });


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
            anchoTexto = 51;
            pGuiGraphics.drawString(font, Component.literal("Class Type").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 89, 0xFF9B9B);

            if(cap.getDmzClass().equals("Warrior")){
                anchoTexto = 65;
                drawStringWithBorder(pGuiGraphics, font, Component.literal("Warrior"), anchoTexto, alturaTexto - 72, 0xFC4E2B);

            } else {
                anchoTexto = 60;
                drawStringWithBorder(pGuiGraphics, font, Component.literal("Spiritualist"), anchoTexto, alturaTexto - 72, 0x2BFCFC);

            }

            //ALINEAMIENTO
            anchoTexto = 47;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, texto);
            pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 56, 0, 0, 73, 15);
            RenderSystem.disableBlend();

            anchoTexto = 56;
            pGuiGraphics.drawString(font, Component.literal("Alignment").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 52, 0xFFDEDE);

            if(cap.getDmzAlignment().equals("Good")){
                anchoTexto = 70;
                drawStringWithBorder(pGuiGraphics, font, Component.literal("Good"), anchoTexto, alturaTexto - 35, 0x1EFFD9, 0x1E6CFF);

            } else {
                anchoTexto = 74;
                drawStringWithBorder(pGuiGraphics, font, Component.literal("Evil"), anchoTexto, alturaTexto - 35, 0xFF3D72,0xF61414);

            }


            //COLOR DE KI
            anchoTexto = 47;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, texto);
            pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 16, 0, 0, 73, 15);
            RenderSystem.disableBlend();

            anchoTexto = 52;
            pGuiGraphics.drawString(font, Component.literal("Aura Color").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto - 12, 0xFFCA9B);

        });


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

    public void panoramas(GuiGraphics graphics, float partialtick){

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {
            var race = cap.getRace();

            if(race == 0){
                this.customPanorama.render(partialtick, 1.0f);

            }else if(race == 1){
                this.panoramaSai.render(partialtick, 1.0f);

            }else if(race == 2){
                this.panoramaNam.render(partialtick, 1.0f);

            }else if(race == 3){
                this.panoramaBio.render(partialtick, 1.0f);

            }else if(race == 4){
                this.panoramaCold.render(partialtick, 1.0f);

            }else {
                this.panoramaBuu.render(partialtick, 1.0f);

            }
        });

    }

}
