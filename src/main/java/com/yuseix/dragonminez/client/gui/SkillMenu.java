package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.CustomButtons;
import com.yuseix.dragonminez.client.gui.buttons.DMZGuiButtons;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.stats.skills.DMZSkill;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class SkillMenu extends Screen {

    private static final ResourceLocation menucentro = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menulargo2.png");
    private static final ResourceLocation menuinfo = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menulargomitad.png");

    private static boolean infoMenu = false;
    private static String skillsId = "";
    private int alturaTexto, anchoTexto;

    private final List<AbstractWidget> skillButtons = new ArrayList<>(); // Lista para rastrear widgets de habilidades


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
        botonesMenus();
        botonesSkills();
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        menuPaneles(pGuiGraphics);
        menuSkills(pGuiGraphics);


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

            int startX = ((this.width - 250) / 2 + 30) - 72;
            int startY = (this.height - 168) / 2 + 18;
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.level"), startX, startY, 0xe6fffd);
            startX = ((this.width - 250) / 2 + 100) - 72;
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.skill"), startX, startY, 0xffc134);
            startX = ((this.width - 250) / 2 + 170) - 72;
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.active"), startX, startY, 0x20e0ff);

            //Cargar el menu 2 con la informacion completa de las habilidades
            menuSkillsDesc(guiGraphics);

        } else {
            alturaTexto = (this.height - 168)/2;
            anchoTexto = (this.width - 250)/2;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            guiGraphics.blit(menucentro, anchoTexto, alturaTexto, 0, 0, 250, 168);

            int startX = (this.width - 250) / 2 + 30;
            int startY = (this.height - 168) / 2 + 18;
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.level"), startX, startY, 0xe6fffd);
            startX = (this.width - 250) / 2 + 100;
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.skill"), startX, startY, 0xffc134);
            startX = (this.width - 250) / 2 + 170;
            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.active"), startX, startY, 0x20e0ff);

        }

        RenderSystem.disableBlend();
    }

    public void botonesMenus(){
        this.removeWidget(infoButton);
        this.removeWidget(statsMenuButton);
        this.removeWidget(skillMenuButton);

        alturaTexto = (this.height + 168)/2;
        anchoTexto = this.infoMenu ? ((this.width - 250)/2) - 72: (this.width - 250)/2;

        if (this.minecraft.level.isClientSide) { //Volver al MENU DE STATS
            this.statsMenuButton = (DMZGuiButtons) this.addRenderableWidget(new DMZGuiButtons(anchoTexto + 2, alturaTexto, "libro", Component.empty(), wa -> {
                // Cambiar la pantalla solo en el cliente
                this.minecraft.setScreen(new AttributesMenu2());
            }));
        }



    }
    private void botonesSkills(){
        Player player = this.minecraft.player;

        // Elimina solo los botones relacionados con habilidades
        skillButtons.forEach(this::removeWidget);
        skillButtons.clear();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
            Map<String, DMZSkill> skills = cap.getDMZSkills();

            int startX = (this.width - 250) / 2 + 13;
            int startY = (this.height - 168) / 2 + 30;
            int offsetY = 13; // Espacio vertical entre cada habilidad

            // Renderizar cada habilidad
            for (Map.Entry<String, DMZSkill> entry : skills.entrySet()) {
                String skillId = entry.getKey();
                DMZSkill skill = entry.getValue();

                switch (skillId) { //Aca pondremos que habilidades tendran el boton de activo y eso
                    case "potential_unlock":
                        break;
                    case "jump":
                        //boton switch aca

                        break;
                    case "fly":

                        break;
                    default:
                        // Si no necesita botones extra, no se hace nada
                        break;
                }

                // Crear un botón base para todos
                CustomButtons button = new CustomButtons("info", this.infoMenu ? startX + 200 - 72 : startX + 200, startY - 2, Component.empty(), btn -> {
                    this.infoMenu = !infoMenu; // Alternar infoMenu
                    this.skillsId = skillId;
                });
                this.addRenderableWidget(button);
                skillButtons.add(button);

                // Mover hacia abajo para la próxima habilidad
                startY += offsetY;
            }
        });
    }

    private void menuSkills(GuiGraphics guiGraphics) {
        // Obtener las habilidades desde la capability del jugador
        Player player = this.minecraft.player;

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
            Map<String, DMZSkill> skills = cap.getDMZSkills();

            int startX = (this.width - 250) / 2 + 15;
            int startY = (this.height - 168) / 2 + 30;
            int offsetY = 13; // Espacio vertical entre cada habilidad

            // Renderizar cada habilidad
            for (Map.Entry<String, DMZSkill> entry : skills.entrySet()) {
                String skillId = entry.getKey();
                DMZSkill skill = entry.getValue();

                switch (skillId) {
                    case "potential_unlock":
                        // Mostrar el texto de la habilidad
                        drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(skill.getLevel())), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xFFFFFF);
                        //Nombre de la habilidad
                        //guiGraphics.drawString(this.font, Component.translatable(skill.getName().getString()).withStyle(ChatFormatting.BOLD), startX + 40, startY, 0xFFFFFF);
                        drawStringWithBorder(guiGraphics, this.font, Component.translatable(skill.getName().getString()), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xFFFFFF);
                        //Activo o inactivo
                        if(skill.isActive()){
                            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.on"), this.infoMenu ? startX + 155 - 72: startX + 155, startY, 0x60fb58);
                        } else {
                            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.off"), this.infoMenu ? startX + 155 - 72: startX + 155, startY, 0xfb5858);
                        }

                        break;
                    case "jump":
                        drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(skill.getLevel())), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xFFFFFF);

                        drawStringWithBorder(guiGraphics, this.font, Component.translatable(skill.getName().getString()), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xFFFFFF);
                        break;
                    case "fly":
                        drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(skill.getLevel())), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xFFFFFF);
                        drawStringWithBorder(guiGraphics, this.font, Component.translatable(skill.getName().getString()), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xFFFFFF);
                        //Activo o inactivo
                        if(skill.isActive()){
                            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.on"), this.infoMenu ? startX + 155 - 72: startX + 155, startY, 0x60fb58);
                        } else {
                            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.off"), this.infoMenu ? startX + 155 - 72: startX + 155, startY, 0xfb5858);
                        }
                        break;
                    default:
                        drawStringWithBorder(guiGraphics, this.font, Component.literal(String.valueOf(skill.getLevel())), this.infoMenu ? startX + 16 - 72 : startX + 16, startY, 0xFFFFFF);
                        drawStringWithBorder(guiGraphics, this.font, Component.translatable(skill.getName().getString()), this.infoMenu ? startX + 85 - 72: startX + 85, startY, 0xFFFFFF);
                        //Activo o inactivo
                        if(skill.isActive()){
                            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.on"), this.infoMenu ? startX + 155 - 72: startX + 155, startY, 0x60fb58);
                        } else {
                            drawStringWithBorder(guiGraphics, this.font, Component.translatable("dmz.skills.off"), this.infoMenu ? startX + 155 - 72: startX + 155, startY, 0xfb5858);
                        }
                        // Si no necesita botones extra, no se hace nada
                        break;
                }

                // Mover hacia abajo para la próxima habilidad
                startY += offsetY;
            }
        });
    }

    private void menuSkillsDesc(GuiGraphics guiGraphics) {
        // Obtener las habilidades desde la capability del jugador
        Player player = this.minecraft.player;

        if(infoMenu){
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {

                int startY = (this.height - 168) / 2 + 18;
                int startX = (this.width - 250) / 2 + 160;

                Map<String, DMZSkill> skills = cap.getDMZSkills();

                // Renderizar cada habilidad
                for (Map.Entry<String, DMZSkill> entry : skills.entrySet()) {
                    String skillId = entry.getKey();
                    DMZSkill skill = entry.getValue();

                    if(skillId.equals(this.skillsId)){
                        //Nombre de la habilidad
                        drawStringWithBorder(guiGraphics, this.font, Component.translatable(skill.getName().getString()), startX + 93, startY, 0xFFFFFF);
                        //Tipo y aca pongo lo de skill
                        drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.type"), startX + 37, startY+ 13, 0xFFFFFF);
                        drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.skill2"), startX + 74, startY+ 13, 0xffc134);
                        //Aca pongo lo de nivel
                        drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.level"), startX + 37, startY+24, 0xFFFFFF);
                        drawStringWithBorder2(guiGraphics, this.font, Component.literal(String.valueOf(skill.getLevel())), startX + 74, startY+24, 0xFFFFFF);
                        //Activo o no
                        drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.active"), startX + 37, startY+36, 0xFFFFFF);
                        //Activo o inactivo
                        if(skill.isActive()){
                            drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.on"), startX + 74, startY+36, 0x60fb58);
                        } else {
                            drawStringWithBorder2(guiGraphics, this.font, Component.translatable("dmz.skills.off"), startX + 74, startY+36, 0xfb5858);
                        }
                        //descripcion
                        List<FormattedCharSequence> lines = font.split(Component.translatable(skill.getDesc().getString()), 130);
                        for (int i = 0; i < lines.size(); i++) {
                            guiGraphics.drawString(font, lines.get(i), startX + 37, (startY+48) + i * font.lineHeight, 0xFFFFFF);
                        }

                    }

                }
            });
        }


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
