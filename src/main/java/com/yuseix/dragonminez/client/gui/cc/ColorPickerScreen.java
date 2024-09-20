package com.yuseix.dragonminez.client.gui.cc;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.TextButton;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.ModMessages;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class ColorPickerScreen extends Screen {

    private static final ResourceLocation menu = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menulargo.png");
    private static final ResourceLocation texto = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menutexto.png");
    private int selectedColor = 0xFFFFFFFF; // Color blanco por defecto
    private float brightness = 1.0f; // Nivel de brillo, 1.0 por defecto (máximo brillo)
    private String tipoColor = "";

    // Nuevas variables para almacenar los componentes de color
    private int ColorR;
    private int ColorG;
    private int ColorB;
    private int decimalColor = 16777215;

    private TextButton backButton, setColor;

    public ColorPickerScreen(String tipoColor) {
        super(Component.literal("Color Picker"));
        this.tipoColor = tipoColor;
    }

    @Override
    protected void init() {

        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width/2+20, (this.height/2) + 23, Component.literal("SET"), wa -> {
            ModMessages.sendToServer(new CharacterC2S("BodyColor1", decimalColor));

        }));

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        //MenuLargo
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        guiGraphics.blit(menu, (this.width/2) - 125, (this.height/2) - 75, 0, 0, 250, 150);
        RenderSystem.disableBlend();
        //Cuadro para Texto
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, texto);
        guiGraphics.blit(texto, this.width/2+20, this.height/2-55, 0, 0, 73, 15);
        RenderSystem.disableBlend();
        //Texto wa
        guiGraphics.drawString(font, Component.literal("COLOR").withStyle(ChatFormatting.BOLD), this.width/2+40, this.height/2-51, decimalColor);

        int ColorPickerX = (this.width / 2) - 110;
        int ColorPickerY = (this.height / 2) - 55;

        // Dibuja el cuadro de colores con el brillo ajustado
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                int color = getColorFromPosition(x, y);
                guiGraphics.fill(ColorPickerX + x, ColorPickerY + y, ColorPickerX + 1 + x, ColorPickerY + 1 + y, color);
            }
        }
        int SaturationX = (this.width / 2) - 5;
        int SaturationY = (this.height / 2) - 55;
        // Dibuja la barra de saturación
        for (int i = 0; i < 100; i++) {
            int brightnessColor = getBrightnessFromPosition(i);
            guiGraphics.fill(SaturationX, SaturationY + i, SaturationX + 10, SaturationY + 1 + i, brightnessColor);
        }

        // Dibuja la vista previa del color seleccionado
        drawColorPreview(guiGraphics);

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    // Método para obtener el color basado en la posición dentro del cuadro de colores
    private int getColorFromPosition(int x, int y) {
        float hue = (float) x / 100f;
        float saturation = (float) y / 100f;
        // Aplica el brillo actual en el cálculo del color
        return Color.HSBtoRGB(hue, saturation, brightness);
    }

    private int getBrightnessFromPosition(int i) {
        float brightnessValue = (float) i / 100f;
        return Color.HSBtoRGB(0, 0, brightnessValue); // Color en escala de grises
    }

    private void drawColorPreview(GuiGraphics guiGraphics) {
        int PreviewX = (this.width / 2) + 32;
        int PreviewY = (this.height / 2) - 35;
        // Dibuja un rectángulo de 50x50 con el color seleccionado
        guiGraphics.fill(PreviewX, PreviewY, PreviewX + 50, PreviewY + 50, selectedColor);

        // Puedes agregar un borde alrededor de la vista previa si quieres
        drawRectBorder(guiGraphics, PreviewX, PreviewY, PreviewX + 50, PreviewY + 50, 0xFF000000); // Color negro para el borde
    }

    private void drawRectBorder(GuiGraphics guiGraphics, int x1, int y1, int x2, int y2, int borderColor) {
        guiGraphics.fill(x1 - 1, y1 - 1, x2 + 1, y1, borderColor); // Línea superior
        guiGraphics.fill(x1 - 1, y2, x2 + 1, y2 + 1, borderColor);  // Línea inferior
        guiGraphics.fill(x1 - 1, y1, x1, y2, borderColor);          // Línea izquierda
        guiGraphics.fill(x2, y1, x2 + 1, y2, borderColor);          // Línea derecha
    }

    public void updateColorComponents() {
        ColorR = (selectedColor >> 16) & 0xFF; // Rojo
        ColorG = (selectedColor >> 8) & 0xFF;  // Verde
        ColorB = selectedColor & 0xFF;         // Azul

        // También puedes convertir a decimal aquí si lo necesitas
        decimalColor = calcularColor((int) ColorR, (int) ColorG, (int) ColorB);
    }
    public static int calcularColor(int r, int g, int b) {
        // Convertir el número a decimal
        return (r << 16) + (g << 8) + b;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int ColorPickerX = (this.width / 2) - 110;
        int ColorPickerY = (this.height / 2) - 55;

        if (mouseX >= ColorPickerX && mouseX <= ColorPickerX + 100 && mouseY >= ColorPickerY && mouseY <= ColorPickerY + 100) {
            int x = (int) (mouseX - ColorPickerX);
            int y = (int) (mouseY - ColorPickerY);
            selectedColor = getColorFromPosition(x, y);
            updateColorComponents();
        }

        int SaturationX = (this.width / 2) - 5;
        int SaturationY = (this.height / 2) - 55;

        if (mouseX >= SaturationX && mouseX <= SaturationX + 10 && mouseY >= SaturationY && mouseY <= SaturationY + 100) {
            int i = (int) (mouseY - SaturationY);
            brightness = (float) i / 100f;
            selectedColor = getColorFromPosition((int)(mouseX - ColorPickerX), (int)(mouseY - ColorPickerY));
            updateColorComponents();
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
