package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
import com.yuseix.dragonminez.client.gui.buttons.CustomButtons;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.C2S.StatsC2S;
import com.yuseix.dragonminez.network.C2S.ZPointsC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class AttributesMenu extends Screen implements RenderEntityInv {

    int alturaTexto;
    int anchoTexto;

    private static final ResourceLocation menu1 = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menugrande.png");

    private static final ResourceLocation cuadronegro = new ResourceLocation(DragonMineZ.MOD_ID,
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
    MutableComponent BodyType = (Component.translatable("dmz.ccreation.bodytype"));
    MutableComponent EyeType = (Component.translatable("dmz.ccreation.eyetype"));

    //BODYTYPES
    MutableComponent HBody0 = (Component.translatable("dmz.races.body0.hum"));
    MutableComponent HBody1 = (Component.translatable("dmz.races.body1.hum"));

    MutableComponent SBody0 = (Component.translatable("dmz.races.body0.sai"));
    MutableComponent SBody1 = (Component.translatable("dmz.races.body1.sai"));

    //EYETYPES
    MutableComponent SHEye0 = (Component.translatable("dmz.races.eye0.saihum"));
    MutableComponent SHEye1 = (Component.translatable("dmz.races.eye1.saihum"));

    private final List<AbstractWidget> botones = new ArrayList<>();
    private final List<AbstractWidget> botonesRazas = new ArrayList<>();
    private final List<AbstractWidget> botonesBodyType = new ArrayList<>();

    private DMZRightButton botonRazRigth;
    private DMZRightButton botonRazaLeft;

    private static final ResourceLocation menu = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menugrande.png");
    private static final ResourceLocation menu2 = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menumedio.png");
    private static final ResourceLocation menu3 = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menupequeno.png");

    public AttributesMenu(Component pGuiScreen) {
        super(pGuiScreen);
    }


    @Override
    public void init() {
        super.init();

        botonesRazas.clear();
        botonesRazas.forEach(this::removeWidget);

        //MenuInicio
        int posX = (this.width - 148) / 2;
        int posY = (this.height - 222) / 2;

        assert Minecraft.getInstance().player != null;
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            int race = playerstats.getRace();

            if (playerstats.isAcceptCharacter() == false) {

                //Botones de Razas
                botonesRazas.add(new DMZRightButton("right", posX - 23, posY + 35, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 1));
                }));
                botonesRazas.add(new DMZRightButton("left", posX - 109, posY + 35, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 0));
                }));

                botonesRazas.forEach(this::addRenderableWidget);

                switch (race) {
                    case 0: //HUMANO

                        break;
                    case 1: //Saiyan

                        break;
                    case 2: //Namek

                        break;
                    case 3: //BioAndroide

                        break;
                    case 4: //ColdDemon

                        break;
                    case 5: //Majin

                        break;
                    default:

                        break;
                }


            } else {


                int zpoints = playerstats.getZpoints();

                int zCost = (playerstats.getStrength() +
                        playerstats.getDefense() +
                        playerstats.getConstitution() +
                        playerstats.getKiPower() +
                        playerstats.getEnergy() + 6) * DMCAttrConfig.MULTIPLIER_ZPOINTS_COST.get();

                if (zpoints >= zCost) {
                    //Fuerza
                    botones.add(new CustomButtons(posX - 125, posY + 45, Component.empty(), button -> {
                        ModMessages.sendToServer(new StatsC2S(0, 1));
                        ModMessages.sendToServer(new ZPointsC2S(1, zCost));
                    }));
                    //Defensa
                    botones.add(new CustomButtons(posX - 125, posY + 60, Component.empty(), button -> {
                        ModMessages.sendToServer(new StatsC2S(1, 1));
                        ModMessages.sendToServer(new ZPointsC2S(1, zCost));
                    }));
                    //Vida
                    botones.add(new CustomButtons(posX - 125, posY + 75, Component.empty(), button -> {
                        ModMessages.sendToServer(new StatsC2S(2, 1));
                        ModMessages.sendToServer(new ZPointsC2S(1, zCost));
                    }));
                    //Kipower
                    botones.add(new CustomButtons(posX - 125, posY + 90, Component.empty(), button -> {
                        ModMessages.sendToServer(new StatsC2S(3, 1));
                        ModMessages.sendToServer(new ZPointsC2S(1, zCost));
                    }));
                    //Energy
                    botones.add(new CustomButtons(posX - 125, posY + 105, Component.empty(), button -> {
                        ModMessages.sendToServer(new StatsC2S(4, 1));
                        ModMessages.sendToServer(new ZPointsC2S(1, zCost));
                    }));
                }

            }


        });
    }

    @Override
    public void tick() {
        super.tick();

        //MenuInicio
        int posX = (this.width - 148) / 2;
        int posY = (this.height - 222) / 2;

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            if (playerstats.isAcceptCharacter() == false) {

                switch (playerstats.getRace()) {
                    case 0: //HUMANO

                        //TIPO DE CUERPO
                        if (playerstats.getBodytype() == 0) {
                            this.botonRazRigth = this.addRenderableWidget(new DMZRightButton("right", posX - 23, posY + 75, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            }));
                            this.botonRazaLeft = this.addRenderableWidget(new DMZRightButton("left", posX - 109, posY + 75, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                            }));

                        }
                        if (playerstats.getBodytype() == 1) {
                            this.botonRazRigth = this.addRenderableWidget(new DMZRightButton("right", posX - 23, posY + 75, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            }));
                            this.botonRazaLeft = this.addRenderableWidget(new DMZRightButton("left", posX - 109, posY + 75, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                            }));
                        }

                        //TIPO DE OJOS
                        if (playerstats.getEyesType() == 0) {
                            this.botonRazRigth = this.addRenderableWidget(new DMZRightButton("right", posX - 23, posY + 115, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 1));
                            }));
                            this.botonRazaLeft = this.addRenderableWidget(new DMZRightButton("left", posX - 109, posY + 115, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 0));
                            }));

                        }
                        if (playerstats.getEyesType() == 1) {
                            this.botonRazRigth = this.addRenderableWidget(new DMZRightButton("right", posX - 23, posY + 115, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 1));
                            }));
                            this.botonRazaLeft = this.addRenderableWidget(new DMZRightButton("left", posX - 109, posY + 115, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 0));
                            }));
                        }

                        break;
                    case 1: //Saiyan

                        //TIPO DE CUERPO
                        if (playerstats.getBodytype() == 0) {
                            this.botonRazRigth = this.addRenderableWidget(new DMZRightButton("right", posX - 23, posY + 75, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            }));
                            this.botonRazaLeft = this.addRenderableWidget(new DMZRightButton("left", posX - 109, posY + 75, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                            }));

                        }
                        if (playerstats.getBodytype() == 1) {
                            this.botonRazRigth = this.addRenderableWidget(new DMZRightButton("right", posX - 23, posY + 75, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            }));
                            this.botonRazaLeft = this.addRenderableWidget(new DMZRightButton("left", posX - 109, posY + 75, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                            }));
                        }

                        //TIPO DE OJOS
                        if (playerstats.getEyesType() == 0) {
                            this.botonRazRigth = this.addRenderableWidget(new DMZRightButton("right", posX - 23, posY + 115, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 1));
                            }));
                            this.botonRazaLeft = this.addRenderableWidget(new DMZRightButton("left", posX - 109, posY + 115, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 0));
                            }));

                        }
                        if (playerstats.getEyesType() == 1) {
                            this.botonRazRigth = this.addRenderableWidget(new DMZRightButton("right", posX - 23, posY + 115, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 1));
                            }));
                            this.botonRazaLeft = this.addRenderableWidget(new DMZRightButton("left", posX - 109, posY + 115, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 0));
                            }));
                        }

                        break;
                    case 2: //Namek

                        break;
                    case 3: //BioAndroide

                        break;
                    case 4: //ColdDemon

                        break;
                    case 5: //Majin

                        break;
                    default:

                        break;
                }


            } else {
                int zpoints = playerstats.getZpoints();

                int zCost = (playerstats.getStrength() +
                        playerstats.getDefense() +
                        playerstats.getConstitution() +
                        playerstats.getKiPower() +
                        playerstats.getEnergy() + 6) * DMCAttrConfig.MULTIPLIER_ZPOINTS_COST.get();

                if (zpoints >= zCost) {
                    botones.forEach(this::addRenderableWidget);
                } else {
                    this.clearWidgets();
                }
            }


        });
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(graphics);

        int posX = (this.width - 152) / 2; //Normalmente da 100... (Ya no se ve)
        int posY = (this.height - 256) / 2;

        var Altura = graphics.guiHeight();
        var Ancho = graphics.guiWidth();


        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            if (playerstats.isAcceptCharacter() == false) {

                RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

                RenderSystem.setShaderTexture(0, menu1);

                graphics.blit(menu1, (this.width / 2 - 148) - 62, (this.height / 2 - 222) + 112, 0, 0, 148, 222);

                //TITULO
                alturaTexto = (Altura / 2) - 40;
                anchoTexto = ((Ancho - this.font.width(this.CCreation)) / 2);


                graphics.drawString(font, CCreation, anchoTexto + 1, alturaTexto, 0, false);
                graphics.drawString(font, CCreation, anchoTexto - 1, alturaTexto, 0, false);
                graphics.drawString(font, CCreation, anchoTexto, alturaTexto + 1, 0, false);
                graphics.drawString(font, CCreation, anchoTexto, alturaTexto - 1, 0, false);
                graphics.drawString(font, CCreation, anchoTexto, alturaTexto, 0xFDFDFD, false);


                //RAZA TITULO

                alturaTexto = (Altura / 2) - 89;
                anchoTexto = ((Ancho - this.font.width(this.Race)) / 2) - 137;

                graphics.drawString(font, Race.withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xFDFDFD, true);


                //TITULO PARA TIPO DE CUERPO
                RenderSystem.setShaderTexture(0, cuadronegro);

                graphics.blit(cuadronegro, (this.width / 2 - 148) - 25, (this.height / 2 - 222) + 168, 0, 0, 73, 15);

                alturaTexto = (Altura / 2) - 50;
                anchoTexto = ((Ancho - this.font.width(this.BodyType)) / 2) - 137;

                graphics.drawString(font, BodyType.withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xFFE7C0, true);

                //TITULO PARA TIPO DE OJOS
                RenderSystem.setShaderTexture(0, cuadronegro);

                graphics.blit(cuadronegro, (this.width / 2 - 148) - 25, (this.height / 2 - 222) + 208, 0, 0, 73, 15);

                alturaTexto = (Altura / 2) - 10;
                anchoTexto = ((Ancho - this.font.width(this.EyeType)) / 2) - 137;

                graphics.drawString(font, EyeType.withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xC0EDFF, true);

                var raza = playerstats.getRace();


                switch (raza) {
                    case 0: //Humano
                        alturaTexto = (Altura / 2) - 70;
                        anchoTexto = ((Ancho - this.font.width(this.Human)) / 2) - 137;

                        graphics.drawString(font, Human, anchoTexto, alturaTexto, 0x31EAFF, true);

                        switch (playerstats.getBodytype()) {
                            case 0:
                                alturaTexto = (Altura / 2) - 30;
                                anchoTexto = ((Ancho - this.font.width(this.HBody0)) / 2) - 137;

                                graphics.drawString(font, HBody0, anchoTexto, alturaTexto, 0xFFFFFF, true);
                                break;
                            case 1:
                                alturaTexto = (Altura / 2) - 30;
                                anchoTexto = ((Ancho - this.font.width(this.HBody1)) / 2) - 137;

                                graphics.drawString(font, HBody1, anchoTexto, alturaTexto, 0xFEC9C9, true);
                                break;

                            default:
                                break;
                        }

                        switch (playerstats.getEyesType()) {
                            case 0:
                                alturaTexto = (Altura / 2) + 10;
                                anchoTexto = ((Ancho - this.font.width(this.SHEye0)) / 2) - 137;

                                graphics.drawString(font, SHEye0, anchoTexto, alturaTexto, 0xFFFFFF, true);
                                break;
                            case 1:
                                alturaTexto = (Altura / 2) + 10;
                                anchoTexto = ((Ancho - this.font.width(this.SHEye1)) / 2) - 137;

                                graphics.drawString(font, SHEye1, anchoTexto, alturaTexto, 0xFEC9C9, true);
                                break;

                            default:
                                break;
                        }


                        break;
                    case 1: // Saiyan
                        alturaTexto = (Altura / 2) - 70;
                        anchoTexto = ((Ancho - this.font.width(this.Saiyan)) / 2) - 137;

                        graphics.drawString(font, Saiyan, anchoTexto, alturaTexto, 0xFFBA35, true);

                        switch (playerstats.getBodytype()) {
                            case 0:
                                alturaTexto = (Altura / 2) - 30;
                                anchoTexto = ((Ancho - this.font.width(this.SBody0)) / 2) - 137;

                                graphics.drawString(font, SBody0, anchoTexto, alturaTexto, 0xFFFFFF, true);
                                break;
                            case 1:
                                alturaTexto = (Altura / 2) - 30;
                                anchoTexto = ((Ancho - this.font.width(this.SBody1)) / 2) - 137;

                                graphics.drawString(font, SBody1, anchoTexto, alturaTexto, 0xFEC9C9, true);
                                break;

                            default:
                                break;
                        }

                        switch (playerstats.getEyesType()) {
                            case 0:
                                alturaTexto = (Altura / 2) + 10;
                                anchoTexto = ((Ancho - this.font.width(this.SHEye0)) / 2) - 137;

                                graphics.drawString(font, SHEye0, anchoTexto, alturaTexto, 0xFFFFFF, true);
                                break;
                            case 1:
                                alturaTexto = (Altura / 2) + 10;
                                anchoTexto = ((Ancho - this.font.width(this.SHEye1)) / 2) - 137;

                                graphics.drawString(font, SHEye1, anchoTexto, alturaTexto, 0xFEC9C9, true);
                                break;

                            default:
                                break;
                        }

                        break;
                    case 2: // Namek
                        alturaTexto = (Altura / 2) - 70;
                        anchoTexto = ((Ancho - this.font.width(this.Namek)) / 2) - 137;

                        graphics.drawString(font, Namek, anchoTexto, alturaTexto, 0x378942, true);

                        break;
                    case 3: // BioAndroid
                        alturaTexto = (Altura / 2) - 70;
                        anchoTexto = ((Ancho - this.font.width(this.BioAndroid)) / 2) - 137;

                        graphics.drawString(font, BioAndroid, anchoTexto, alturaTexto, 0x72DA58, true);

                        break;
                    case 4: // ColdDemon
                        alturaTexto = (Altura / 2) - 70;
                        anchoTexto = ((Ancho - this.font.width(this.ColdDemon)) / 2) - 137;

                        graphics.drawString(font, ColdDemon, anchoTexto, alturaTexto, 0xAC1BEC, true);

                        break;
                    case 5: // Majin
                        alturaTexto = (Altura / 2) - 70;
                        anchoTexto = ((Ancho - this.font.width(this.Majin)) / 2) - 137;

                        graphics.drawString(font, Majin, anchoTexto, alturaTexto, 0xFE7FF4, true);

                        break;
                    default:

                        break;
                }

            } else {

                RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                RenderSystem.setShaderTexture(0, menu);


                //PuntosZ
                int zpoints = playerstats.getZpoints();

                int level = (playerstats.getStrength() +
                        playerstats.getDefense() +
                        playerstats.getConstitution() +
                        playerstats.getKiPower() +
                        playerstats.getEnergy()) / 5;

                int zCost = (playerstats.getStrength() +
                        playerstats.getDefense() +
                        playerstats.getConstitution() +
                        playerstats.getKiPower() +
                        playerstats.getEnergy() + 6) * DMCAttrConfig.MULTIPLIER_ZPOINTS_COST.get();

                //Attributos
                int str = playerstats.getStrength();
                int def = playerstats.getDefense();
                int con = playerstats.getConstitution();
                int kipower = playerstats.getKiPower();
                int energy = playerstats.getEnergy();
                int stamina = playerstats.getStamina();

                //AtributosMaximos
                int MaxStr = 1;
                int MaxDef = 1;
                int MaxCon = 1;
                int MaxKiPower = 1;
                int MaxEnergy = 1;
                int MaxStamina = stamina + 3;

                int LTITULO = posX + 195;
                int RTITULO = posX - 80;
                int LSUBTITULO = posX + 158;
                int RSUBTITULO = posX - 158;
                int LDESC = posX + 245;
                int RDESC = posX - 30;

                //SUBIR STATS
                //Pos cambiada a -140 de -250 por motivos de cambio de resoluciones para adaptar
                graphics.blit(menu, posX - 140, posY, 0, 0, 148, 221);
                graphics.drawString(this.font.self(), ChatFormatting.BOLD + "STATS", LTITULO, posY + 22, 0xFCC3C3, true);

                graphics.drawString(Minecraft.getInstance().fontFilterFishy, ChatFormatting.BOLD + "ZPoints: ", LSUBTITULO, posY + 38, 0xFFFFFF, true);
                graphics.drawString(font, String.valueOf(zpoints), posX - 45, posY + 38, 0xFFE800, false);

                graphics.drawString(font, ChatFormatting.BOLD + "STR: ", LSUBTITULO, posY + 50, 0x320C0C, true);
                graphics.drawString(font, String.valueOf(str), LDESC, posY + 50, 0xBB1C2A, false);

                graphics.drawString(font, ChatFormatting.BOLD + "DEF: ", LSUBTITULO, posY + 65, 0x320C0C, true);
                graphics.drawString(font, String.valueOf(def), LDESC, posY + 65, 0xBB1C2A, false);

                graphics.drawString(font, ChatFormatting.BOLD + "CON: ", LSUBTITULO, posY + 80, 0x320C0C, true);
                graphics.drawString(font, String.valueOf(con), LDESC, posY + 80, 0xBB1C2A, false);

                graphics.drawString(font, ChatFormatting.BOLD + "POW: ", LSUBTITULO, posY + 95, 0x320C0C, true);
                graphics.drawString(font, String.valueOf(kipower), LDESC, posY + 95, 0xBB1C2A, false);

                graphics.drawString(font, ChatFormatting.BOLD + "ENE: ", LSUBTITULO, posY + 110, 0x320C0C, true);
                graphics.drawString(font, String.valueOf(energy), LDESC, posY + 110, 0xBB1C2A, false);

                graphics.drawString(font, ChatFormatting.BOLD + "ZPCost: ", LSUBTITULO, posY + 125, 0xF0B61E, true);
                graphics.drawString(font, String.valueOf(zCost), posX - 45, posY + 125, 0xFFE800, false);

                //STATS
                graphics.blit(menu2, posX + 140, posY, 0, 0, 147, 163);
                graphics.drawString(font, ChatFormatting.BOLD + "INFORMATION", RTITULO, posY + 15, 0xF0B61E, true);

                graphics.drawString(font, ChatFormatting.BOLD + "Damage: ", RSUBTITULO, posY + 30, 0x830318, true);
                graphics.drawString(font, String.valueOf(MaxStr), RDESC, posY + 30, 0x9B1D32, false);

                graphics.drawString(font, ChatFormatting.BOLD + "Defense: ", RSUBTITULO, posY + 45, 0x830318, true);
                graphics.drawString(font, String.valueOf(MaxDef), RDESC, posY + 45, 0x9B1D32, false);

                graphics.drawString(font, ChatFormatting.BOLD + "Body: ", RSUBTITULO, posY + 60, 0x830318, true);
                graphics.drawString(font, String.valueOf(MaxCon), RDESC, posY + 60, 0x9B1D32, false);

                graphics.drawString(font, ChatFormatting.BOLD + "Stamina: ", RSUBTITULO, posY + 75, 0x830318, true);
                graphics.drawString(font, String.valueOf(MaxStamina), RDESC, posY + 75, 0x9B1D32, false);

                graphics.drawString(font, ChatFormatting.BOLD + "KiPower: ", RSUBTITULO, posY + 90, 0x830318, true);
                graphics.drawString(font, String.valueOf(MaxKiPower), RDESC, posY + 90, 0x9B1D32, false);

                graphics.drawString(font, ChatFormatting.BOLD + "Max Ki: ", RSUBTITULO, posY + 105, 0x830318, true);
                graphics.drawString(font, String.valueOf(MaxEnergy), RDESC, posY + 105, 0x9B1D32, false);

                graphics.drawString(font, ChatFormatting.BOLD + this.minecraft.player.getName().getString(), posX + 63, posY + 10, 0xFFFFFF, true);
                graphics.drawString(font, ChatFormatting.BOLD + "Lvl: ", posX + 50, posY + 25, 0xFFE800, true);
                graphics.drawString(font, ChatFormatting.BOLD + String.valueOf(level), posX + 80, posY + 25, 0x67EDFC, true);

                //RenderEntityInv.renderEntityInInventoryFollowsAngle(graphics, posX + 73, posY + 200, 80, 0, 0, this.minecraft.player);

                graphics.drawString(font, ChatFormatting.BOLD + "Humano :v", posX + 45, posY + 220, 0x45E9FC, true);


            }


        });

        super.render(graphics, pMouseX, pMouseY, pPartialTick);

    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }


}
