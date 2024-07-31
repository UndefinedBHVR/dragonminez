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
import com.yuseix.dragonminez.utils.TranslateManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
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

    private int alturaTexto;
    private int anchoTexto;

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

    private DMZRightButton botonRazRigth;
    private DMZRightButton botonRazaLeft;

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

        //MenuInicio


        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

        });
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(graphics);

        //Paneles del menu
        menuPaneles(graphics);

        menu0info(graphics);

        super.render(graphics, pMouseX, pMouseY, pPartialTick);

    }

    public void menu0info(GuiGraphics guiGraphics){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            alturaTexto = 19;
            anchoTexto = ((this.width / 2) - this.font.width(Minecraft.getInstance().player.getName().getString())) + 9;

            var playername = Minecraft.getInstance().player.getName().getString();
            var alignment = playerstats.getDmzAlignment();
            var raza = playerstats.getRace();

            if(alignment.equals("Good")){
                drawStringWithBorder(guiGraphics, font, Component.literal(playername).withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0x63FFFF);
            }else {
                drawStringWithBorder(guiGraphics, font, Component.literal(playername).withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xFA5252);
            }
            alturaTexto = 46;
            if(raza == 0){

                anchoTexto = ((this.width / 2) - this.font.width(TranslateManager.Human)) + 16;

                drawStringWithBorderShadow(guiGraphics, font, TranslateManager.Human, anchoTexto, alturaTexto, 0x177CFC);
            } else if(raza == 1){

                anchoTexto = ((this.width / 2) - this.font.width(TranslateManager.Saiyan)) + 18;

                drawStringWithBorderShadow(guiGraphics, font, TranslateManager.Saiyan, anchoTexto, alturaTexto, 0xFCB317);
            } else if(raza == 2){

                anchoTexto = ((this.width / 2) - this.font.width(TranslateManager.Namek)) + 23;

                drawStringWithBorderShadow(guiGraphics, font, TranslateManager.Namek, anchoTexto, alturaTexto, 0x186814);

            } else if(raza == 3){

                anchoTexto = ((this.width / 2) - this.font.width(TranslateManager.BioAndroid)) + 30;

                drawStringWithBorderShadow(guiGraphics, font, TranslateManager.BioAndroid, anchoTexto, alturaTexto, 0x7DFF76);

            } else if(raza == 4){

                anchoTexto = ((this.width / 2) - this.font.width(TranslateManager.ColdDemon)) + 30;

                drawStringWithBorderShadow(guiGraphics, font, TranslateManager.ColdDemon, anchoTexto, alturaTexto, 0x6A31EE);

            } else {

                anchoTexto = ((this.width / 2) - this.font.width(TranslateManager.Majin)) + 14;

                drawStringWithBorderShadow(guiGraphics, font, TranslateManager.Majin, anchoTexto, alturaTexto, 0xFF86FD);

            }
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

        guiGraphics.drawString(font, texto, x + 1, y, ColorBorde);
        guiGraphics.drawString(font, texto, x - 1, y, ColorBorde);
        guiGraphics.drawString(font, texto, x, y + 1, ColorBorde);
        guiGraphics.drawString(font, texto, x, y - 1, ColorBorde);
        guiGraphics.drawString(font, texto, x, y, ColorTexto);
    }

    public static void drawStringWithBorderShadow(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {

        guiGraphics.drawString(font, texto, x + 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x - 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y + 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y - 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y, ColorTexto, false);
    }
    public static void drawStringWithBorderShadow(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto) {
        drawStringWithBorderShadow(guiGraphics, font, texto, x, y, ColorTexto, 0);
    }
        public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto) {
        drawStringWithBorder(guiGraphics, font, texto, x, y, ColorTexto, 0);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }


}
