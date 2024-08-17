package com.yuseix.dragonminez.client.gui.entity;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.CustomButtons;
import com.yuseix.dragonminez.client.gui.buttons.GlowButton;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.KarinEntity;
import com.yuseix.dragonminez.network.C2S.StatsC2S;
import com.yuseix.dragonminez.network.C2S.ZPointsC2S;
import com.yuseix.dragonminez.network.ModMessages;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class KarinMenu extends Screen {

    private static final ResourceLocation textoCuadro = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/texto.png");

    private GlowButton kinton;

    public KarinMenu() {
        super(Component.literal("karinwa"));
    }

    @Override
    protected void init() {
        super.init();
        this.kinton = (GlowButton) this.addRenderableWidget(new GlowButton((this.width/2)-45, (this.height-23),Component.literal("NUBE VOLADORA"), wa -> {

        }));
    }

    @Override
    public void tick() {
        super.tick();
        /*
        this.removeWidget(kinton);
        this.kinton = (GlowButton) this.addRenderableWidget(new GlowButton((this.width/2), (this.height-78),Component.literal("NUBE VOLADORAAAA"), wa -> {

        }));

        */
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        // Calculate the position to center the texture
        int centerX = (this.width / 2);
        int centerY = (this.height);

        LivingEntity karinEntity = new KarinEntity(MainEntity.MASTER_KARIN.get(), this.minecraft.level);


        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, textoCuadro);


        BufferBuilder buffer = Tesselator.getInstance().getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

        buffer.vertex(centerX-140, centerY + 250, 0.0D).uv(0.0F, 1.0F).endVertex();
        buffer.vertex(centerX+140, centerY + 250, 0.0D).uv(1.0F, 1.0F).endVertex();
        buffer.vertex(centerX+140, centerY-90, 0.0D).uv(1.0F, 0.0F).endVertex();
        buffer.vertex(centerX-140, centerY-90, 0.0D).uv(0.0F, 0.0F).endVertex();
        Tesselator.getInstance().end();

        RenderSystem.disableBlend();

        //NOMBRE DE LA ENTIDAD
        pGuiGraphics.drawString(font, Component.literal(karinEntity.getName().getString()).withStyle(ChatFormatting.BOLD), centerX-120, centerY-88, 0xFFFFFF);
        //TEXTO QUE DIRA LA ENTIDAD
        List<FormattedCharSequence> lines = font.split(Component.translatable("lines.master_korin.menu"), 250);
        for (int i = 0; i < lines.size(); i++) {
            pGuiGraphics.drawString(font, lines.get(i), (centerX-120), (centerY-73)  + i * font.lineHeight, 0xFFFFFF);
        }

    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
