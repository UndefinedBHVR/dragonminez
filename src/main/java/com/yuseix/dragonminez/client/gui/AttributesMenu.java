package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
import com.yuseix.dragonminez.client.gui.buttons.CustomButtons;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class AttributesMenu extends Screen implements RenderEntityInv {

    private int alturaTexto;
    private int anchoTexto;
    private int multiplicadorTP = 1;

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

    private CustomButtons strBoton,defBoton,conBoton,pwrBoton,eneBoton, multiBoton;

    public AttributesMenu(Component pGuiScreen) {
        super(pGuiScreen);
    }


    @Override
    public void init() {

        super.init();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {


        });
    }

    @Override
    public void tick() {
        super.tick();

        botonesStats();
        //MenuInicio



    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(graphics);

        //Paneles del menu
        menuPaneles(graphics);

        menu0info(graphics);

        menu1info(graphics);

        menu2info(graphics);
        super.render(graphics, pMouseX, pMouseY, pPartialTick);




    }

    public void botonesStats(){
        this.removeWidget(strBoton);
        this.removeWidget(defBoton);
        this.removeWidget(conBoton);
        this.removeWidget(pwrBoton);
        this.removeWidget(eneBoton);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {
            var tps = playerstats.getZpoints();
            var str = playerstats.getStrength();
            var def = playerstats.getDefense();
            var con = playerstats.getConstitution();
            var kipower = playerstats.getKiPower();
            var energy = playerstats.getEnergy();

            anchoTexto = 17;
            alturaTexto = (this.height / 2) + 1;

            int maxStats = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
            var baseCost =  (int) Math.round(((((str + def + con + kipower + energy) / 2) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get())) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get());
            int adjustedCostSTR, adjustedCostDEF, adjustedCostCON, adjustedCostPWR, adjustedCostENE;
            var multCost = (int) Math.round((str + def + con + kipower + energy) /  2) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get();

            this.multiBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto-3, alturaTexto + 63,Component.empty(), wa -> {
                if(multiplicadorTP == 1){
                    multiplicadorTP *= 10;
                } else if(multiplicadorTP == 10){
                    multiplicadorTP *= 10;
                } else if(multiplicadorTP == 100){
                    multiplicadorTP = 1;
                }
            }));

            if (str >= (maxStats - multiplicadorTP)) {
                // Calcula los puntos restantes para alcanzar el límite de estadísticas y ajusta el costo en base a eso xd
                int puntosNecesarios = maxStats - str;
                adjustedCostSTR = (int) Math.round(baseCost * puntosNecesarios);
            } else {
                adjustedCostSTR = (int) Math.round((baseCost * multiplicadorTP) + multCost * multiplicadorTP);
            }

            if (def >= (maxStats - multiplicadorTP)) {
                int puntosNecesarios = maxStats - def;
                adjustedCostDEF = (int) Math.round(baseCost * puntosNecesarios);
            } else {
                adjustedCostDEF = (int) Math.round((baseCost * multiplicadorTP) + multCost * multiplicadorTP);
            }

            if (con >= (maxStats - multiplicadorTP)) {
                int puntosNecesarios = maxStats - con;
                adjustedCostCON = (int) Math.round(baseCost * puntosNecesarios);
            } else {
                adjustedCostCON = (int) Math.round((baseCost * multiplicadorTP) + multCost * multiplicadorTP);
            }

            if (kipower >= (maxStats - multiplicadorTP)) {
                int puntosNecesarios = maxStats - kipower;
                adjustedCostPWR = (int) Math.round(baseCost * puntosNecesarios);
            } else {
                adjustedCostPWR = (int) Math.round((baseCost * multiplicadorTP) + multCost * multiplicadorTP);
            }

            if (energy >= (maxStats - multiplicadorTP)) {
                int puntosNecesarios = maxStats - energy;
                adjustedCostENE = (int) Math.round(baseCost * puntosNecesarios);
            } else {
                adjustedCostENE = (int) Math.round((baseCost * multiplicadorTP) + multCost * multiplicadorTP);
            }

            if(tps >= baseCost){
                if (str < maxStats) {
                    this.strBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, adjustedCostSTR));
                        ModMessages.sendToServer(new StatsC2S(0,1 * multiplicadorTP));
                    }));}
                if (def < maxStats) {
                    this.defBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto + 12,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, adjustedCostDEF));
                        ModMessages.sendToServer(new StatsC2S(1,1 * multiplicadorTP));
                    }));}
                if (con < maxStats) {
                    this.conBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto + 24,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, adjustedCostCON));
                        ModMessages.sendToServer(new StatsC2S(2,1 * multiplicadorTP));
                    }));}
                if (kipower < maxStats) {
                    this.pwrBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto + 36,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, adjustedCostPWR));
                        ModMessages.sendToServer(new StatsC2S(3,1 * multiplicadorTP));
                    }));}
                if (energy < maxStats) {
                    this.eneBoton = (CustomButtons) this.addRenderableWidget(new CustomButtons("stat",anchoTexto, alturaTexto + 48,Component.empty(), wa -> {
                        ModMessages.sendToServer(new ZPointsC2S(1, adjustedCostENE));
                        ModMessages.sendToServer(new StatsC2S(4,1 * multiplicadorTP));
                    }));}
        }});
    }

    public void menu0info(GuiGraphics guiGraphics){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            alturaTexto = 19;
            anchoTexto = this.width/2;

            var playername = Minecraft.getInstance().player.getName().getString();
            var alignment = playerstats.getDmzAlignment();
            var raza = playerstats.getRace();

            if(alignment > 60){
                drawStringWithBorder(guiGraphics, font, Component.literal(playername).withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0x63FFFF);
            }else if(alignment > 40){
                drawStringWithBorder(guiGraphics, font, Component.literal(playername).withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xeaa8fe);
            }else {
                drawStringWithBorder(guiGraphics, font, Component.literal(playername).withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xFA5252);
            }
            alturaTexto = 46;
            if(raza == 0){

                anchoTexto = this.width/2;

                drawStringWithBorder(guiGraphics, font, TranslateManager.Human, anchoTexto, alturaTexto, 0x177CFC);
            } else if(raza == 1){

                anchoTexto = this.width/2;

                drawStringWithBorder(guiGraphics, font, TranslateManager.Saiyan, anchoTexto, alturaTexto, 0xFCB317);
            } else if(raza == 2){

                anchoTexto = this.width/2;

                drawStringWithBorder(guiGraphics, font, TranslateManager.Namek, anchoTexto, alturaTexto, 0x186814);

            } else if(raza == 3){

                anchoTexto = this.width/2;

                drawStringWithBorder(guiGraphics, font, TranslateManager.BioAndroid, anchoTexto, alturaTexto, 0x7DFF76);

            } else if(raza == 4){

                anchoTexto = this.width/2;

                drawStringWithBorder(guiGraphics, font, TranslateManager.ColdDemon, anchoTexto, alturaTexto, 0x6A31EE);

            } else {

                anchoTexto = this.width/2;

                drawStringWithBorder(guiGraphics, font, TranslateManager.Majin, anchoTexto, alturaTexto, 0xFF86FD);

            }
        });
    }

    public void menu1info(GuiGraphics graphics){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            var TPS = playerstats.getZpoints();

            var nivel = (playerstats.getStrength() + playerstats.getDefense() + playerstats.getConstitution()
                    + playerstats.getKiPower() + playerstats.getEnergy()) / 5;

            var clase = playerstats.getDmzClass();

            alturaTexto = (this.height / 2) - 83;

            //Information title
            drawStringWithBorder(graphics, font, Component.literal("INFORMATION"), 75, alturaTexto, 0xFBC51C);

            //Titulos
            anchoTexto = 25;
            alturaTexto = (this.height / 2) - 67;

            graphics.drawString(font, Component.literal("Lvl:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto, 0xD7FEF5);
            graphics.drawString(font, Component.literal("TPs:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 11, 0xD7FEF5);
            graphics.drawString(font, Component.literal("Form:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 22, 0xD7FEF5);
            graphics.drawString(font, Component.literal("Class:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 33, 0xD7FEF5);

            //VARIABLES:
            //NIVEL
            anchoTexto = 70;
            drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(nivel)), anchoTexto, alturaTexto, 0xFFFFFF);
            //TPS

            drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(TPS)), anchoTexto, alturaTexto + 11, 0xFFE593);

            //FORMA
            drawStringWithBorder2(graphics, font, Component.literal("Base"), anchoTexto, alturaTexto + 22, 0xC7EAFC);
            //Clase
            if(clase.equals("Warrior")){
                drawStringWithBorder(graphics, font,Component.literal("Warrior"), 90, alturaTexto + 33, 0xFC4E2B);
            }else {
                drawStringWithBorder(graphics, font,Component.literal("Spiritualist"), 90, alturaTexto + 33, 0x2BFCFC);
            }

            //STATS
            alturaTexto = (this.height / 2) - 20;
            anchoTexto = 37;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            graphics.blit(cuadronegro, anchoTexto, alturaTexto, 0, 0, 73, 15);
            RenderSystem.disableBlend();

            //STATS TITLE
            alturaTexto = (this.height / 2) - 16;
            drawStringWithBorder(graphics, font, Component.literal("STATS"), 72, alturaTexto, 0x68CCFF);

            //Variables stats
            alturaTexto = (this.height / 2) + 2;
            anchoTexto = 32;
            graphics.drawString(font, Component.literal("STR:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto, 0xD71432);
            graphics.drawString(font, Component.literal("DEF:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 12, 0xD71432);
            graphics.drawString(font, Component.literal("CON:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 24, 0xD71432);
            graphics.drawString(font, Component.literal("PWR:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 36, 0xD71432);
            graphics.drawString(font, Component.literal("ENE:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 48, 0xD71432);
            graphics.drawString(font, Component.literal("TPC:").withStyle(ChatFormatting.BOLD),anchoTexto - 4, alturaTexto + 64, 0x2BFFE2);

            var strdefault = playerstats.getStrength();
            var defdefault = playerstats.getDefense();
            var condefault = playerstats.getConstitution();
            var kipowerdefault = playerstats.getKiPower();
            var energydefault = playerstats.getEnergy();

            var baseCost =  (int) Math.round((((strdefault + defdefault + condefault + kipowerdefault + energydefault) / 2) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get()) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get());
            var multCost = (int) Math.round((strdefault + defdefault + condefault + kipowerdefault + energydefault) /  2) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_COST.get();
            int finalCost = (int) Math.round((baseCost * multiplicadorTP) + multCost * multiplicadorTP);

            //STATS CAPABILITY
            alturaTexto = (this.height / 2) + 2;
            anchoTexto = 70;
            drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(strdefault)), anchoTexto, alturaTexto, 0xFFFFFF);
            drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(defdefault)), anchoTexto, alturaTexto + 12, 0xFFFFFF);
            drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(condefault)), anchoTexto, alturaTexto + 24, 0xFFFFFF);
            drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(kipowerdefault)), anchoTexto, alturaTexto + 36, 0xFFFFFF);
            drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(energydefault)), anchoTexto, alturaTexto + 48, 0xFFFFFF);

            //Multiplicadores per Stat
            anchoTexto = 100;
            var color = 0xD71432;
            /*

            Aca cuando tengamos el majin, arbol este activado o kaioken deberiamos poner un
            if(majin || arbol || kaioken || transformado)  <- como esto
             para que cargue el multiplicador al lado de la stat, por el momento lo dejaremos asi nada mas
            o si quieres lo puedes borrar, como se vea mas ordenado.

            */
            graphics.drawString(font, Component.literal("x" + DMZDatos.calcularMultiStat(playerstats.getRace(), playerstats.getDmzState(), "STR")),anchoTexto, alturaTexto, color);
            graphics.drawString(font, Component.literal("x" + DMZDatos.calcularMultiStat(playerstats.getRace(), playerstats.getDmzState(), "DEF")),anchoTexto, alturaTexto + 12, color);
            graphics.drawString(font, Component.literal("x" + DMZDatos.calcularMultiStat(playerstats.getRace(), playerstats.getDmzState(), "KIPOWER")),anchoTexto, alturaTexto + 36, color);



            Component Multiplier = Component.empty()
                    .append(Component.literal(String.valueOf(finalCost)))
                    .append(Component.literal(" (x")
                    .append(Component.literal(String.valueOf(multiplicadorTP)))
                    .append(Component.literal(".0)"))
                    );
            anchoTexto = 65;
            drawStringWithBorder2(graphics, font, Component.literal(String.valueOf(finalCost)), anchoTexto, alturaTexto + 64, 0xFFCE41);
            drawStringWithBorder2(graphics, font, Component.literal("x" + multiplicadorTP), anchoTexto, alturaTexto + 76, 0x2BFFE2);

        });

    }

    public void menu2info(GuiGraphics graphics){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            anchoTexto = (this.width - 75);
            alturaTexto = (this.height / 2) - 83;

            //Information title
            drawStringWithBorder(graphics, font, Component.literal("STATISTICS"), anchoTexto, alturaTexto, 0xF91E64);

            //Titulos
            anchoTexto = (this.width - 127);
            alturaTexto = (this.height / 2) - 64;

            var color = 0xFBA16A;

            graphics.drawString(font, Component.literal("Damage:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto, color);
            graphics.drawString(font, Component.literal("Defense:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 12, color);
            graphics.drawString(font, Component.literal("Health:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 24, color);
            graphics.drawString(font, Component.literal("Stamina:").withStyle(ChatFormatting.BOLD),anchoTexto+4, alturaTexto + 36, 0xFFBB91);
            graphics.drawString(font, Component.literal("Ki Damage:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 48, color);
            graphics.drawString(font, Component.literal("Max Ki:").withStyle(ChatFormatting.BOLD),anchoTexto, alturaTexto + 60, color);

            graphics.drawString(font, Component.literal("Multiplier:").withStyle(ChatFormatting.BOLD),anchoTexto - 3, alturaTexto + 80, 0xC51D1D);


            anchoTexto = (this.width - 55);

            //Datos
            var strMax = DMZDatos.calcularSTR(playerstats.getRace(), playerstats.getStrength(), 1, playerstats.getDmzState(),playerstats.getDmzRelease(),playerstats.getDmzClass());
            var defMax = DMZDatos.calcularDEF(playerstats.getRace(),playerstats.getDefense(), playerstats.getDmzState(),playerstats.getDmzRelease(), playerstats.getDmzClass());
            var conMax = DMZDatos.calcularCON(playerstats.getRace(), playerstats.getConstitution(), 20, playerstats.getDmzClass());
            var stmMax = DMZDatos.calcularSTM(playerstats.getRace(), conMax);
            var KPWMax = DMZDatos.calcularKiPower(playerstats.getRace(), playerstats.getKiPower(), playerstats.getDmzState(), playerstats.getDmzRelease(), playerstats.getDmzClass());
            var enrMax = DMZDatos.calcularENE(playerstats.getRace(), playerstats.getEnergy(), playerstats.getDmzClass());

            drawStringWithBorder(graphics, font, Component.literal(String.valueOf(strMax)), anchoTexto, alturaTexto, 0xFFD7AB);
            drawStringWithBorder(graphics, font, Component.literal(String.valueOf(defMax)), anchoTexto, alturaTexto + 12, 0xFFD7AB);
            drawStringWithBorder(graphics, font, Component.literal(String.valueOf(conMax)), anchoTexto, alturaTexto + 24, 0xFFD7AB);
            drawStringWithBorder(graphics, font, Component.literal(String.valueOf(stmMax)), anchoTexto, alturaTexto + 36, 0xFFD7AB);
            drawStringWithBorder(graphics, font, Component.literal(String.valueOf(KPWMax)), anchoTexto, alturaTexto + 48, 0xFFD7AB);
            drawStringWithBorder(graphics, font, Component.literal(String.valueOf(enrMax)), anchoTexto, alturaTexto + 60, 0xFFD7AB);

            drawStringWithBorder2(graphics, font, Component.literal("x"+DMZDatos.calcularMultiTotal(playerstats.getRace(), playerstats.getDmzState())), anchoTexto-3, alturaTexto + 80, 0xFCFCFC);
        });
    }

    public void menuPaneles(GuiGraphics guiGraphics){
        //INFORMACION (Nivel, tps, forma)
        alturaTexto = (this.height / 2) - 105;
        anchoTexto = 2;
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        guiGraphics.blit(menugrande, anchoTexto, alturaTexto, 0, 0, 146, 219);
        RenderSystem.disableBlend();

        //NOMBRE JUGADOR
        alturaTexto = 5;
        anchoTexto = (this.width/2) - 71;
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        guiGraphics.blit(menuraza, anchoTexto, alturaTexto, 0, 93, 145, 60);
        RenderSystem.disableBlend();

        //INFO GENERAL (Fuerza maxima, energia maxima, stamina, etc)
        alturaTexto = (this.height / 2) - 105;
        anchoTexto = (this.width - 148);
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        guiGraphics.blit(menugrande, anchoTexto, alturaTexto, 0, 0, 146, 219);
        RenderSystem.disableBlend();

    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {
        // Calcular la posición centrada
        int textWidth = font.width(texto);
        int centeredX = x - (textWidth / 2);

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

}
