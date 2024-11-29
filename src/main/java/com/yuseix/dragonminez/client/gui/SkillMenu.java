package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.CustomButtons;
import com.yuseix.dragonminez.client.gui.buttons.DMZGuiButtons;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.network.C2S.StatsC2S;
import com.yuseix.dragonminez.network.C2S.ZPointsC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.stats.skills.DMZSkill;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class SkillMenu extends Screen {

    private static final ResourceLocation menucentro = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menulargo2.png");
    private static final ResourceLocation menuinfo = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menulargomitad.png");

    private static boolean infoMenu = false;
    private static String skillId = "";
    private int alturaTexto, anchoTexto;

    private CustomButtons infoButton;
    private DMZGuiButtons statsMenuButton, skillMenuButton;

    public SkillMenu() {
        super(Component.empty());
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void tick() {
        super.tick();
        botonesStats();
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        menuPaneles(pGuiGraphics);
        renderSkills(pGuiGraphics);


        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

    }

    public void menuPaneles(GuiGraphics guiGraphics){

        if(infoMenu){
            alturaTexto = (this.height - 168)/2;
            anchoTexto = ((this.width - 250)/2) - 72;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            guiGraphics.blit(menucentro, anchoTexto, alturaTexto, 0, 0, 250, 168);

            anchoTexto = ((this.width - 250)/2) + 180;
            guiGraphics.blit(menuinfo, anchoTexto, alturaTexto, 0, 0, 145, 168);

        } else {
            alturaTexto = (this.height - 168)/2;
            anchoTexto = (this.width - 250)/2;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            guiGraphics.blit(menucentro, anchoTexto, alturaTexto, 0, 0, 250, 168);

        }

        RenderSystem.disableBlend();
    }

    public void botonesStats(){
        this.removeWidget(infoButton);
        this.removeWidget(statsMenuButton);
        this.removeWidget(skillMenuButton);

        alturaTexto = (this.height - 160)/2;
        anchoTexto = (this.width + 240)/2;

//        this.infoButton = (CustomButtons) this.addRenderableWidget(new CustomButtons("info", anchoTexto, alturaTexto, Component.empty(), wa -> {
//            this.infoMenu = infoMenu ? false : true;
//        }));

        alturaTexto = (this.height + 168)/2;
        anchoTexto = this.infoMenu ? ((this.width - 250)/2) - 72: (this.width - 250)/2;

        if (this.minecraft.level.isClientSide) { //Volver al MENU DE STATS
            this.statsMenuButton = (DMZGuiButtons) this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 2, alturaTexto, "libro", Component.empty(), wa -> {
                // Cambiar la pantalla solo en el cliente
                this.minecraft.setScreen(new AttributesMenu2());
            }));
        }



    }
    private void renderSkills(GuiGraphics guiGraphics) {
        // Obtener las habilidades desde la capability del jugador
        Player player = this.minecraft.player;

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
            Map<String, DMZSkill> skills = cap.getDMZSkills();

            int startX = (this.width - 250) / 2 + 13;
            int startY = (this.height - 168) / 2 + 20;
            int offsetY = 20; // Espacio vertical entre cada habilidad

            // Renderizar cada habilidad
            for (Map.Entry<String, DMZSkill> entry : skills.entrySet()) {
                String skillId = entry.getKey();
                DMZSkill skill = entry.getValue();

                switch (skillId) {
                    case "potential_unlock":
                        // Mostrar el texto de la habilidad
                        guiGraphics.drawString(this.font, "Lvl: " + skill.getLevel(), startX, startY, 0xFFFFFF);
                        guiGraphics.drawString(this.font, skill.getName(), startX + 50, startY, 0xFFFFFF);
                        guiGraphics.drawString(this.font, skill.isActive() ? "Active" : "Inactive", startX + 150, startY, 0xFFFFFF);

                        break;
                    case "jump":
                        //boton switch aca
                        guiGraphics.drawString(this.font, "Lvl: " + skill.getLevel(), startX, startY, 0xFFFFFF);
                        guiGraphics.drawString(this.font, skill.getName(), startX + 50, startY, 0xFFFFFF);
                        break;
                    case "fly":
                        guiGraphics.drawString(this.font, "Lvl: " + skill.getLevel(), startX, startY, 0xFFFFFF);
                        guiGraphics.drawString(this.font, skill.getName(), startX + 50, startY, 0xFFFFFF);
                        guiGraphics.drawString(this.font, skill.isActive() ? "Active" : "Inactive", startX + 150, startY, 0xFFFFFF);

                        break;
                    default:
                        // Si no necesita botones extra, no se hace nada
                        break;
                }

                // Crear un botón base para todos
                this.addRenderableWidget(new CustomButtons("info" + skill.getName(), startX + 200, startY - 5, Component.empty(), button -> {
                }));

                // Mover hacia abajo para la próxima habilidad
                startY += offsetY;
            }
        });
    }
    @Override
    public boolean isPauseScreen() {
        return false;
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

}
