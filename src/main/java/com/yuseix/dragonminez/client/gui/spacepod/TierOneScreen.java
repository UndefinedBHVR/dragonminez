package com.yuseix.dragonminez.client.gui.spacepod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.DMZCustomButton;
import com.yuseix.dragonminez.client.gui.buttons.TextButton;
import com.yuseix.dragonminez.client.gui.cc.CCustomizationPage;
import com.yuseix.dragonminez.network.C2S.SpacePodC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.utils.TranslateManager;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.level.Level;

import java.util.List;

public class TierOneScreen extends Screen {
    private int alturaTexto, anchoTexto;

    //Texturas de los panoramas
    private static final ResourceLocation PANORAMA_OVERWORLD = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/panorama");
    private static final ResourceLocation PANORAMA_NAMEK = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/n_panorama");

    //Panoramas
    private final PanoramaRenderer panoramaOverworld = new PanoramaRenderer(new CubeMap(PANORAMA_OVERWORLD));
    private final PanoramaRenderer panoramaNamek = new PanoramaRenderer(new CubeMap(PANORAMA_NAMEK));

    private static final ResourceLocation texto = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menupequeno.png");

    private int currentPage = 0;
    private TextButton travelButton;

    public TierOneScreen() {
        super(Component.empty());
    }

    @Override
    protected void init() {
        int posY = this.minecraft.getWindow().getGuiScaledHeight() / 2;

        // Siguiente página
        this.addRenderableWidget(new DMZCustomButton(this.width /2 + 56, this.height-25, 20, 20, Component.literal(">"), (button) -> {
            if (currentPage == 0) {
                currentPage = 1;
            } else {
                currentPage = 0;
            }
        }));

        // Anterior página
        this.addRenderableWidget(new DMZCustomButton(this.width /2 - 83, this.height-25, 20, 20, Component.literal("<"), (button) -> {
            if (currentPage == 0) {
                currentPage = 1;
            } else {
                currentPage = 0;
            }
        }));

        this.travelButton = (TextButton) this.addRenderableWidget(new TextButton(this.width / 2 - 40, this.height-25, TranslateManager.TRAVEL.withStyle(ChatFormatting.BOLD), button -> {
            this.removeWidget(travelButton);
            // Lógica para teletransportar al jugador
            if (currentPage == 0) {
                // Teletransportar al jugador al mundo Overworld y cerrar el Menú
                ModMessages.sendToServer(new SpacePodC2S(Level.OVERWORLD));
                this.onClose();
            } else {
                // Teletransportar al jugador al mundo Namek y cerrar el Menú
                ModMessages.sendToServer(new SpacePodC2S(ModDimensions.NAMEK_DIM_LEVEL_KEY));
                this.onClose();
            }
        }));

        super.init();
    }

    @Override
    public boolean isPauseScreen() { return false; }

    @Override
    public void tick() { super.tick(); }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        panoramas(pGuiGraphics, pPartialTick);
        renderBlurredBorder(pGuiGraphics);
        titulo(pGuiGraphics, this.width, this.height);
        infoPlaneta1(pGuiGraphics);

        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    public void titulo(GuiGraphics pGuiGraphics, int posX, int posY) {
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.depthMask(false);
        pGuiGraphics.blit(texto, (this.width / 2)- 71, 5, 0, 171, 145, 36);
        RenderSystem.disableBlend();

        alturaTexto = 19;
        anchoTexto = this.width/2;

        if (currentPage == 0) {
            pGuiGraphics.drawCenteredString(font, Component.translatable("ui.dmz.spacepod.overworld").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0x31EAFF);
        } else {
            pGuiGraphics.drawCenteredString(font, Component.translatable("ui.dmz.spacepod.namek").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0x378942);
        }
    }

    // Panel Izquierdo
    public void infoPlaneta1(GuiGraphics graphics) {
        if (currentPage == 0) {
            CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("ui.dmz.spacepod.overworld").withStyle(ChatFormatting.BOLD),10,(this.height/2)-75,0x61C3FE);

            List<FormattedCharSequence> lines = font.split(Component.translatable("ui.dmz.spacepod.overworld.description"), 130);
            for (int i = 0; i < lines.size(); i++) {
                graphics.drawString(font, lines.get(i), 3, ((this.height/2 ) - 60)  + i * font.lineHeight, 0xFFFFFF);
            }
        } else {
            CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("ui.dmz.spacepod.namek").withStyle(ChatFormatting.BOLD),10,(this.height/2)-75,0x61C3FE);

            List<FormattedCharSequence> lines = font.split(Component.translatable("ui.dmz.spacepod.namek.description"), 130);
            for (int i = 0; i < lines.size(); i++) {
                graphics.drawString(font, lines.get(i), 3, ((this.height/2 ) - 60)  + i * font.lineHeight, 0xFFFFFF);
            }
        }
    }

    private void renderBlurredBorder(GuiGraphics pGuiGraphics) {
        int borderSize = 20;  // Tamaño del borde difuso
        int width = this.width;
        int height = this.height;

        // Colores del borde (con transparencia)
        int borderColorStart = 0x80000000;  // Color negro con opacidad inicial
        int borderColorEnd = 0x00000000;    // Color completamente transparente

        // Borde superior (difuminado de arriba hacia abajo)
        pGuiGraphics.fillGradient(0, 0, width, borderSize, borderColorStart, borderColorEnd);
        // Borde inferior (difuminado de abajo hacia arriba)
        pGuiGraphics.fillGradient(0, height - borderSize, width, height, borderColorEnd, borderColorStart);
        // Borde izquierdo (difuminado de dentro hacia afuera)
        pGuiGraphics.fillGradient(0, 0, borderSize, height, borderColorStart, borderColorEnd);
        // Borde derecho (difuminado de dentro hacia afuera)
        pGuiGraphics.fillGradient(width - borderSize, 0, width, height, borderColorEnd, borderColorStart);
    }


    public void panoramas(GuiGraphics pGuiGraphics, float pPartialTick) {
        if (currentPage == 0) {
            panoramaOverworld.render(pPartialTick, 1.0F);
        } else {
            panoramaNamek.render(pPartialTick, 1.0F);
        }
    }
}
