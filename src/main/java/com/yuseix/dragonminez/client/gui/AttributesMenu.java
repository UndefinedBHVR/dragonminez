package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
import com.yuseix.dragonminez.client.gui.buttons.CustomButtons;
import com.yuseix.dragonminez.client.gui.buttons.DMZGuiButtons;
import com.yuseix.dragonminez.client.gui.buttons.SwitchButton;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.C2S.SkillActivateC2S;
import com.yuseix.dragonminez.network.C2S.StatsC2S;
import com.yuseix.dragonminez.network.C2S.ZPointsC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import com.yuseix.dragonminez.utils.TranslateManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@OnlyIn(Dist.CLIENT)
public class AttributesMenu extends Screen implements RenderEntityInv {

    private int alturaTexto; private int anchoTexto; private int multiplicadorTP = 1;

    private static final ResourceLocation menu1 = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menugrande.png");

    private static final ResourceLocation cuadronegro = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menutexto.png");
    private static final ResourceLocation menugrande = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menugrande.png");
    private static final ResourceLocation menumedio = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menumedio.png");
    private static final ResourceLocation menuraza = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menupequeno.png");

    private CustomButtons multiBoton; private DMZGuiButtons newMenuBoton; private DMZDatos dmzdatos = new DMZDatos();

    // Formateador de números con separadores (por ejemplo, "10.000.000")
    NumberFormat numberFormatter = NumberFormat.getInstance(Locale.US);

    public AttributesMenu(Component pGuiScreen) {
        super(pGuiScreen);
    }


    @Override
    public void init() {
        super.init();

        anchoTexto = (this.width/2);
        alturaTexto = (this.height - 25);

        if (this.minecraft.level.isClientSide) {
            Player player = this.minecraft.player;
            this.newMenuBoton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 85, alturaTexto, "stats", Component.empty(), wa -> {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
                    if (playerstats.isCompactMenu()) {
                        this.minecraft.setScreen(new AttributesMenu2());
                    }
                });
            }));
            this.newMenuBoton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 55, alturaTexto, "skills", Component.empty(), wa -> {
                this.minecraft.setScreen(new SkillMenu());
            }));
            this.newMenuBoton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto - 25, alturaTexto, "transf", Component.empty(), wa -> {
                // Agregar acá el menú de Transf
                // this.minecraft.setScreen(new TransfMenu());
            }));
            this.newMenuBoton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 5, alturaTexto, "storyline", Component.empty(), wa -> {
                // Agregar el menú de Story
                // this.minecraft.setScreen(new StoryMenu());
            }));
            this.newMenuBoton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 35, alturaTexto, "kitech", Component.empty(), wa -> {
                // Agregar el menú de KiTech
                // this.minecraft.setScreen(new KiTechMenu());
            }));
            this.newMenuBoton = this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 65, alturaTexto, "settings", Component.empty(), wa -> {
                this.minecraft.setScreen(new ConfigMenu());
            }));
        }
    }


    @Override
    public void tick() {
        super.tick();
        botonesStats();
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(graphics);
        //Paneles del menu
        menuPaneles(graphics);
        menu1info(graphics, pMouseX, pMouseY);
        menu2info(graphics, pMouseX, pMouseY);
        menu0info(graphics, pMouseX, pMouseY);
        super.render(graphics, pMouseX, pMouseY, pPartialTick);

    }

    public void botonesStats() {

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {
            var tps = playerstats.getZpoints(); var str = playerstats.getStrength(); var def = playerstats.getDefense();
            var con = playerstats.getConstitution(); var kipower = playerstats.getKiPower(); var energy = playerstats.getEnergy();

            anchoTexto = 17; alturaTexto = (this.height / 2) + 1;

            int maxStats = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
            int baseCost = (int) Math.round(((((str + def + con + kipower + energy) / 2)
                    * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get()))
                    * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get() * 1.5);

            this.multiBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat", anchoTexto - 3, alturaTexto + 63, Component.empty(), wa -> {
                switch (multiplicadorTP) {
                    case 1:
                        multiplicadorTP = 10;
                        break;
                    case 10:
                        multiplicadorTP = 100;
                        break;
                    case 100:
                        multiplicadorTP = 1;
                        break;
                }
            }));

            // Crear botones dinámicos para las estadísticas, solo si las estadísticas no están en su máximo y hay suficientes ZPoints
            int[] stats = {str, def, con, kipower, energy};

            for (int i = 0; i < stats.length; i++) {
                if (stats[i] < maxStats && tps >= baseCost) {
                    int offsetY = i * 12;
                    int finalI = i;

                    this.addRenderableWidget(new CustomButtons("stat", anchoTexto, alturaTexto + offsetY, Component.empty(), wa -> {
                        // Recalcular valores dinámicamente al presionar el botón
                        // Si el costo ajustado es mayor que los puntos totales, entonces se pueden aumentar las Stats al máximo posible con esos tps
                        int statActual = stats[finalI];
                        int adjustedCost = calcularCostoRecursivo(statActual, multiplicadorTP, baseCost, maxStats);
                        int upgradeStat;
                        int finalCost;

                        if (adjustedCost > tps) {
                            upgradeStat = calcularNivelesAumentar(statActual, tps, baseCost, maxStats);
                            finalCost = calcularCostoRecursivo(statActual, upgradeStat, baseCost, maxStats);
                        } else {
                            upgradeStat = Math.min(multiplicadorTP, maxStats - statActual);
                            finalCost = adjustedCost;
                        }

                        // Enviar los valores actualizados
                        ModMessages.sendToServer(new ZPointsC2S(1, finalCost));
                        ModMessages.sendToServer(new StatsC2S(finalI, upgradeStat));
                    }));
                }
            }
        });
    }

    private int calcularCostoRecursivo(int statActual, int nivelesAumentar, int baseCost, int maxStats) {
        int costoTotal = 0;
        for (int i = 0; i < nivelesAumentar; i++) {
            if (statActual + i >= maxStats) break; // No exceder el límite máximo de estadísticas
            costoTotal += baseCost + (int) Math.round(DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get() * (statActual + i));
        }
        return costoTotal;
    }

    private int calcularNivelesAumentar(int statActual, int tps, int baseCost, int maxStats) {
        int nivelesAumentar = 0;
        int costoAcumulado = 0;

        while (statActual + nivelesAumentar < maxStats) {
            int costoNivel = baseCost + (int) Math.round(DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get() * (statActual + nivelesAumentar));
            if (costoAcumulado + costoNivel > tps) break; // Si no hay suficientes puntos, detener
            costoAcumulado += costoNivel;
            nivelesAumentar++;
        }
        return nivelesAumentar;
    }

    public void menu0info(GuiGraphics guiGraphics, int mouseX, int mouseY){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            alturaTexto = 19; anchoTexto = (this.width/2)+2;

            var playername = Minecraft.getInstance().player.getName().getString(); var alignment = playerstats.getDmzAlignment();
            var raza = playerstats.getRace(); int namecolor;
            if (alignment > 60) {
                namecolor = 0x63FFFF;
            } else if (alignment > 40) {
                namecolor = 0xeaa8fe;
            } else {
                namecolor = 0xFA5252;
            }
             drawStringWithBorder(guiGraphics, font, Component.literal(playername), anchoTexto, alturaTexto, namecolor);

            if (mouseX >= anchoTexto - 10 && mouseX <= anchoTexto + 10 && mouseY >= alturaTexto && mouseY <= alturaTexto + font.lineHeight) {
                List<FormattedCharSequence> descriptionLines = new ArrayList<>();
                if (alignment > 60) {
                    descriptionLines.add(Component.translatable("stats.dmz.alignment_good", alignment).withStyle(ChatFormatting.YELLOW).getVisualOrderText());
                } else if (alignment > 40) {
                    descriptionLines.add(Component.translatable("stats.dmz.alignment_neutral", alignment).withStyle(ChatFormatting.YELLOW).getVisualOrderText());
                } else {
                    descriptionLines.add(Component.translatable("stats.dmz.alignment_evil", alignment).withStyle(ChatFormatting.YELLOW).getVisualOrderText());
                }
                guiGraphics.renderTooltip(font, descriptionLines, mouseX, mouseY);
            }

            alturaTexto = 46; anchoTexto = this.width/2;
            String[] razasString = {"human", "saiyan", "namek", "bioandroid", "colddemon", "majin"};
            int[] razasInt = {0, 1, 2, 3, 4, 5};
            int[] colors = {0x177CFC, 0xFCB317, 0x186814, 0x7DFF76, 0x6A31EE, 0xFF86FD};
            for (int i = 0; i < razasString.length; i++) {
                String razaActual = razasString[i];
                int razas = razasInt[i];
                int color = colors[i];
                if (raza == razas) {
                    drawStringWithBorder(guiGraphics, font, Component.translatable("dmz.races.name." + razaActual), anchoTexto, alturaTexto, color);
                }
            }
        });
    }

    public void menu1info(GuiGraphics graphics, int mouseX, int mouseY){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {
            var TPS = playerstats.getZpoints();
            var nivel = (playerstats.getStrength() + playerstats.getDefense() + playerstats.getConstitution()
                    + playerstats.getKiPower() + playerstats.getEnergy()) / 5;
            var clase = playerstats.getDmzClass();

            alturaTexto = (this.height / 2) - 83;

            //Information title
            drawStringWithBorder(graphics, font, Component.literal("INFORMATION"), 75, alturaTexto, 0xFBC51C);

            //VARIABLES:
            //NIVEL TPS
            anchoTexto = 70; alturaTexto = (this.height / 2) - 67;
            drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(nivel)), anchoTexto, alturaTexto, 0xFFFFFF);
            drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(TPS)), anchoTexto, alturaTexto + 11, 0xFFE593);

            //FORMA
            drawStringWithBorder2(graphics, font, Component.literal("Base"), anchoTexto, alturaTexto + 22, 0xC7EAFC);
            //Clase
            if(clase.equals("Warrior")){
                drawStringWithBorder(graphics, font,Component.literal("Warrior"), 90, alturaTexto + 33, 0xFC4E2B);
            }else {
                drawStringWithBorder(graphics, font,Component.literal("Spiritualist"), 90, alturaTexto + 33, 0x2BFCFC);
            }

            //STATS
            alturaTexto = (this.height / 2) - 20; anchoTexto = 37;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            graphics.blit(cuadronegro, anchoTexto, alturaTexto, 0, 0, 73, 15);
            RenderSystem.disableBlend();

            //STATS TITLE
            alturaTexto = (this.height / 2) - 16;
            drawStringWithBorder(graphics, font, Component.literal("STATS"), 72, alturaTexto, 0x68CCFF);

            var strdefault = playerstats.getStrength(); var defdefault = playerstats.getDefense(); var condefault = playerstats.getConstitution();
            var kipowerdefault = playerstats.getKiPower(); var energydefault = playerstats.getEnergy(); var raza = playerstats.getRace(); var transf = playerstats.getDmzState();

            int[] cantStats = {strdefault, defdefault, condefault, kipowerdefault, energydefault};
            Arrays.sort(cantStats);
            int minStat = cantStats[0];

            //Efectos
            var majinOn = playerstats.hasDMZPermaEffect("majin");
            var frutaOn = playerstats.hasDMZTemporalEffect("mightfruit");

            var baseCost =  (int) Math.round((((((strdefault + defdefault + condefault + kipowerdefault + energydefault) / 2) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get())) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get()) * 1.5);
            int costoRecursivo = calcularCostoRecursivo(minStat, multiplicadorTP, baseCost, DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get());

            var strcompleta = dmzdatos.calcularSTRCompleta(raza, transf, strdefault, majinOn, frutaOn);
            var defcompleta = dmzdatos.calcularDEFCompleta(raza, transf, defdefault, majinOn, frutaOn);
            var pwrcompleta = dmzdatos.calcularPWRCompleta(raza, transf, kipowerdefault, majinOn, frutaOn);

            var STRMulti = Math.round((dmzdatos.calcularMultiStat(raza, transf, "STR", majinOn, frutaOn)) * 100) / 100.0;
            var DEFMulti = Math.round((dmzdatos.calcularMultiStat(raza, transf, "DEF", majinOn, frutaOn)) * 100) / 100.0;
            var KIPOWERMulti = Math.round((dmzdatos.calcularMultiStat(raza, transf, "KIPOWER", majinOn, frutaOn)) * 100) / 100.0;
            var multiTotal = dmzdatos.calcularMultiTotal(raza, transf, majinOn, frutaOn);

            var isMultiOn = majinOn || frutaOn || transf > 0; var colorEnForma = isMultiOn ? 0xfebc0d : 0xFFD7AB;


            //WA
            Component STRReal = Component.empty()
                    .append(Component.literal(numberFormatter.format(strcompleta)))
                    .append(Component.literal(" x")
                            .append(Component.literal(numberFormatter.format(STRMulti)))
                    );
            Component DEFReal = Component.empty()
                    .append(Component.literal(numberFormatter.format(defcompleta)))
                    .append(Component.literal(" x")
                            .append(Component.literal(numberFormatter.format(DEFMulti)))
                    );
            Component PWRReal = Component.empty()
                    .append(Component.literal(numberFormatter.format(pwrcompleta)))
                    .append(Component.literal(" x")
                            .append(Component.literal(numberFormatter.format(KIPOWERMulti)))
                    );

            //Form, Class, Level, TPs, Stats.
            anchoTexto = 25; alturaTexto = (this.height / 2) - 67;

            graphics.drawString(font, Component.literal("Form:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 22, 0xD7FEF5);
            graphics.drawString(font, Component.literal("Class:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 33, 0xD7FEF5);

            String[] stats = { "Level", "TPs", "STR", "DEF", "CON", "PWR", "ENE", "TPC"};
            int[] colors = { 0xD7FEF5, 0xD7FEF5, 0xD71432, 0xD71432, 0xD71432, 0xD71432, 0xD71432, 0x2BFFE2};
            for (int i = 0; i < stats.length; i++) {
                String statKey = stats[i];
                int colores = colors[i];
                int yOffset;
                if (statKey.equals("Level") || statKey.equals("TPs")) {
                    yOffset = alturaTexto + (i * 11); // Valor fijo para "Level" y "TPs"
                } else if (statKey.equals("TPC")) {
                    yOffset = (alturaTexto + 69) + ((i-2) * 12) + 4; // Valor fijo para "TPC"
                } else {
                    yOffset = (alturaTexto + 69) + ((i-2) * 12); // Valor general para otras stats
                }
                if (statKey.equals("Level") || statKey.equals("TPs")) {
                    anchoTexto = 25;
                } else if (statKey.equals("TPC")) {
                    anchoTexto = 28;
                } else {
                    anchoTexto = 32;
                }

                Component statComponent = Component.literal(statKey + ":")
                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(colores)).withBold(true));
                graphics.drawString(font, statComponent, anchoTexto, yOffset, colores);

                if (mouseX >= anchoTexto - 10 && mouseX <= anchoTexto + 25 && mouseY >= yOffset && mouseY <= yOffset + font.lineHeight) {
                    List<FormattedCharSequence> descriptionLines = new ArrayList<>();
                    FormattedText descriptionText = Component.translatable("stats.dmz." + statKey.toLowerCase());
                    List<FormattedCharSequence> lines = font.split(descriptionText, 250);
                    descriptionLines.addAll(lines);

                    FormattedText descText = Component.translatable("stats.dmz." + statKey.toLowerCase() + ".desc");
                    List<FormattedCharSequence> descLines = font.split(descText, 250);
                    descriptionLines.addAll(descLines);

                    if (statKey.equals("STR") && multiTotal > 1) {
                        descriptionLines.add(Component.translatable("stats.dmz.original", numberFormatter.format(strdefault)).withStyle(ChatFormatting.RED).getVisualOrderText());
                        descriptionLines.add(Component.translatable("stats.dmz.modified", numberFormatter.format(strcompleta)).withStyle(ChatFormatting.GOLD).getVisualOrderText());
                    } else if (statKey.equals("DEF") && multiTotal > 1) {
                        descriptionLines.add(Component.translatable("stats.dmz.original", numberFormatter.format(defdefault)).withStyle(ChatFormatting.RED).getVisualOrderText());
                        descriptionLines.add(Component.translatable("stats.dmz.modified", numberFormatter.format(defcompleta)).withStyle(ChatFormatting.GOLD).getVisualOrderText());
                    } else if (statKey.equals("PWR") && multiTotal > 1) {
                        descriptionLines.add(Component.translatable("stats.dmz.original", numberFormatter.format(kipowerdefault)).withStyle(ChatFormatting.RED).getVisualOrderText());
                        descriptionLines.add(Component.translatable("stats.dmz.modified", numberFormatter.format(pwrcompleta)).withStyle(ChatFormatting.GOLD).getVisualOrderText());
                    }

                    graphics.renderTooltip(font, descriptionLines, mouseX, mouseY);
                }
            }
            //STATS CAPABILITY
            alturaTexto = (this.height / 2) + 2; anchoTexto = 70;

            if(isMultiOn){ //Si alguna forma, estado esta activo.
                drawStringWithBorder2(graphics, font, STRReal, anchoTexto, alturaTexto, colorEnForma);
                drawStringWithBorder2(graphics, font, DEFReal, anchoTexto, alturaTexto + 12, colorEnForma);
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(condefault)), anchoTexto, alturaTexto + 24, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, PWRReal, anchoTexto, alturaTexto + 36, colorEnForma);
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(energydefault)), anchoTexto, alturaTexto + 48, 0xFFD7AB);
            } else {
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(strdefault)), anchoTexto, alturaTexto, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(defdefault)), anchoTexto, alturaTexto + 12, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(condefault)), anchoTexto, alturaTexto + 24, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(kipowerdefault)), anchoTexto, alturaTexto + 36, 0xFFD7AB);
                drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(energydefault)), anchoTexto, alturaTexto + 48, 0xFFD7AB);
            }

            anchoTexto = 65;
            drawStringWithBorder2(graphics, font, Component.literal(numberFormatter.format(costoRecursivo)), anchoTexto, alturaTexto + 64, 0xFFCE41);
            drawStringWithBorder2(graphics, font, Component.literal("x" + multiplicadorTP), anchoTexto, alturaTexto + 76, 0x2BFFE2);

        });
    }

    public void menu2info(GuiGraphics graphics, int mouseX, int mouseY){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            anchoTexto = (this.width - 75); alturaTexto = (this.height / 2) - 83;

            //Information title
            drawStringWithBorder(graphics, font, Component.literal("STATISTICS"), anchoTexto, alturaTexto, 0xF91E64);

            //Titulos
            anchoTexto = (this.width - 127); alturaTexto = (this.height / 2) - 64;

            String[] stats = {"Damage", "Defense", "Health", "Stamina", "Ki Damage", "Max Ki"};
            int[] colors = {0xFBA16A, 0xFBA16A, 0xFBA16A, 0xFFBB91, 0xFBA16A, 0xFBA16A};
            for (int i = 0; i < stats.length; i++) {
                String statKey = stats[i];
                int colores = colors[i];
                int yOffset = alturaTexto + (i * 12);
                // Dibujar textos Damage, Health, etc
                Component statComponent = Component.literal(statKey + ":")
                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(colores)).withBold(true));
                if (statKey.equals("Stamina")) {
                    graphics.drawString(font, statComponent, anchoTexto + 4, yOffset, colores);
                } else {
                    graphics.drawString(font, statComponent, anchoTexto, yOffset, colores);
                }
                // Dibujar Hovers
                if (mouseX >= anchoTexto - 10 && mouseX <= anchoTexto + 60 && mouseY >= yOffset && mouseY <= yOffset + font.lineHeight) {
                    List<FormattedCharSequence> descriptionLines;
                    if (statKey.equals("Ki Damage")) {
                        descriptionLines = getStatDescription("ki_damage", font);
                    } else if (statKey.equals("Max Ki")) {
                        descriptionLines = getStatDescription("max_ki", font);
                    } else {
                        descriptionLines = getStatDescription(statKey.toLowerCase(), font);
                    }
                    graphics.renderTooltip(font, descriptionLines, mouseX, mouseY);
                }
            }
            anchoTexto = (this.width - 55);
            //Efectos
            var majinOn = playerstats.hasDMZPermaEffect("majin"); var frutaOn = playerstats.hasDMZTemporalEffect("mightfruit");
            //Datos
            var raza = playerstats.getRace(); var str = playerstats.getStrength(); var def = playerstats.getDefense(); var con = playerstats.getConstitution();
            var kpw = playerstats.getKiPower(); var enr = playerstats.getEnergy();
            var clase = playerstats.getDmzClass(); var transf = playerstats.getDmzState(); var release = playerstats.getDmzRelease();

            var strMax = dmzdatos.calcularSTR(raza, str, 1, transf,release,clase, majinOn, frutaOn);
            var defMax = dmzdatos.calcularDEF(Minecraft.getInstance().player,raza,def, transf,release, clase, majinOn, frutaOn);
            var conMax = dmzdatos.calcularCON(raza, con, 20, clase);
            var stmMax = dmzdatos.calcularSTM(raza, conMax);
            var KPWMax = dmzdatos.calcularKiPower(raza, kpw, transf, release, clase, majinOn, frutaOn);
            var enrMax = dmzdatos.calcularENE(raza, enr, clase);

            var colorEnForma = majinOn || frutaOn || transf > 0 ? 0xfebc0d : 0xFFD7AB;

            drawStringWithBorder(graphics, font, Component.literal(numberFormatter.format(strMax)), anchoTexto, alturaTexto, colorEnForma);
            drawStringWithBorder(graphics, font, Component.literal(numberFormatter.format(defMax)), anchoTexto, alturaTexto + 12, colorEnForma);
            drawStringWithBorder(graphics, font, Component.literal(numberFormatter.format(conMax)), anchoTexto, alturaTexto + 24, 0xFFD7AB);
            drawStringWithBorder(graphics, font, Component.literal(numberFormatter.format(stmMax)), anchoTexto, alturaTexto + 36, 0xFFD7AB);
            drawStringWithBorder(graphics, font, Component.literal(numberFormatter.format(KPWMax)), anchoTexto, alturaTexto + 48, colorEnForma);
            drawStringWithBorder(graphics, font, Component.literal(numberFormatter.format(enrMax)), anchoTexto, alturaTexto + 60, 0xFFD7AB);

            var MultiTotal = Math.round((dmzdatos.calcularMultiTotal(raza, transf, majinOn, frutaOn)) * 100) / 100.0;

            var multiMajin = DMZGeneralConfig.MULTIPLIER_MAJIN.get();
            var multiFruta = DMZGeneralConfig.MULTIPLIER_TREE_MIGHT.get();
            var multiTransf = dmzdatos.calcularMultiTransf(raza, transf);
            var anchoMulti = (this.width - 127); var altoMulti = (this.height / 2) + 16;

            Component statComponent = Component.literal("Multiplier:")
                    .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xC51D1D)).withBold(true));

            graphics.drawString(font, statComponent, anchoMulti, altoMulti, 0xC51D1D);

            if (mouseX >= anchoMulti -30 && mouseX <= anchoMulti + 50 && mouseY >= altoMulti && mouseY <= altoMulti + font.lineHeight) {
                List<FormattedCharSequence> descriptionLines = new ArrayList<>();
                descriptionLines.add(Component.translatable("stats.dmz.multiplier", multiTransf).withStyle(ChatFormatting.BLUE).getVisualOrderText());
                descriptionLines.add(Component.translatable("stats.dmz.multiplier.desc", multiTransf).getVisualOrderText());
                descriptionLines.add(Component.translatable("stats.dmz.multi.transf", multiTransf).withStyle(ChatFormatting.DARK_AQUA).getVisualOrderText());
                if (majinOn) {
                    descriptionLines.add(Component.translatable("stats.dmz.multi.majin", multiMajin).withStyle(ChatFormatting.LIGHT_PURPLE).getVisualOrderText());
                }
                if (frutaOn) {
                    descriptionLines.add(Component.translatable("stats.dmz.multi.fruta", multiFruta).withStyle(ChatFormatting.RED).getVisualOrderText());
                }
                // Agregar más if luego para ver si está el Kaioken, etc, etc, etc.
                graphics.renderTooltip(font, descriptionLines, mouseX, mouseY);
            }
            drawStringWithBorder2(graphics, font, Component.literal("x"+MultiTotal), anchoTexto-3, alturaTexto + 80, colorEnForma);
        });
    }

    public void menuPaneles(GuiGraphics guiGraphics){
        //INFORMACION (Nivel, tps, forma)
        alturaTexto = (this.height / 2) - 105; anchoTexto = 2;
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        guiGraphics.blit(menugrande, anchoTexto, alturaTexto, 0, 0, 146, 219);
        RenderSystem.disableBlend();

        //NOMBRE JUGADOR
        alturaTexto = 5; anchoTexto = (this.width/2) - 70;
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        guiGraphics.blit(menuraza, anchoTexto, alturaTexto, 0, 93, 145, 60);
        RenderSystem.disableBlend();

        //INFO GENERAL (Fuerza maxima, energia maxima, stamina, etc)
        alturaTexto = (this.height / 2) - 105; anchoTexto = (this.width - 148);
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        guiGraphics.blit(menugrande, anchoTexto, alturaTexto, 0, 0, 146, 219);
        RenderSystem.disableBlend();

    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {
        // Calcular la posición centrada
        int textWidth = font.width(texto); int centeredX = x - (textWidth / 2);

        // Dibujar el texto con el borde
        guiGraphics.drawString(font, texto, centeredX + 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, centeredX - 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, centeredX, y + 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, centeredX, y - 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, centeredX, y, ColorTexto);
    }

    public static void drawStringWithBorder2(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {
        guiGraphics.drawString(font, texto, x + 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x - 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y + 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y - 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y, ColorTexto, false);
    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto) {
        drawStringWithBorder(guiGraphics, font, texto, x, y, ColorTexto, 0);
    }
    public static void drawStringWithBorder2(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto) {
        drawStringWithBorder2(guiGraphics, font, texto, x, y, ColorTexto, 0);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private List<FormattedCharSequence> getStatDescription(String statKey, Font font) {
        Component descripcion = Component.translatable("stats.dmz." + statKey);
        int maxWidth = 200;
        return font.split(descripcion, maxWidth);
    }
}
