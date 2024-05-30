package com.yuseix.dragonminez.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.events.ForgeBusEvents;
import com.yuseix.dragonminez.stats.PlayerStatsAttrProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class PlayerHudOverlay implements RenderEntityInv {

    private static final ResourceLocation hud = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/hud/hud.png");

    public static final IGuiOverlay HUD_PLAYER = (forgeGui, guiGraphics, v, i, i1) -> {
        assert Minecraft.getInstance().player != null;
        int VidaMaxima = (int) Minecraft.getInstance().player.getMaxHealth();
        int vidarestante = (int) Minecraft.getInstance().player.getHealth();

        PlayerStatsAttrProvider.getCap(ForgeBusEvents.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {

            int vidawa = ((163 * vidarestante) / VidaMaxima);
            int vida = Math.min(vidawa, 163);

            int StaminaMax = playerstats.getStamina() + 3;
            int curStamina = playerstats.getCurStam();

            int energiaMax = (int) (playerstats.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY.get();
            int curEnergia = playerstats.getCurrentEnergy();

            int staminatotal = Math.min(((83 * curStamina) / StaminaMax), 83);
            int energiatotal = Math.min(((119 * curEnergia) / energiaMax), 119);

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, hud);

            guiGraphics.pose().pushPose();
            guiGraphics.pose().scale(1.2f, 1.2f, 1.0f);
            //VIDA VACIO
            guiGraphics.blit(hud, 35,
                    10,
                    0,
                    0,
                    191,
                    10);
            //Ki vacio
            guiGraphics.blit(hud, 45,
                    21,
                    6,
                    11,
                    133,
                    6);
            //Stamina vacio
            guiGraphics.blit(hud, 45,
                    28,
                    0,
                    18,
                    100,
                    7);

            //Vida llena
            guiGraphics.blit(hud,
                    52,
                    14,
                    0,
                    74,
                    vida,
                    4);
            //Ki Lleno
            guiGraphics.blit(hud,
                    57,
                    22,
                    0,
                    53,
                    energiatotal,
                    4);
            //Stamina llena
            guiGraphics.blit(hud,
                    60,
                    29,
                    0,
                    61,
                    staminatotal,
                    5);


            guiGraphics.pose().popPose();

            guiGraphics.drawString(Minecraft.getInstance().font, String.valueOf(Minecraft.getInstance().player.getHealth()), 150, 20, 0xBB1C2A);

        });


        double scaleFactor = Minecraft.getInstance().getWindow().getGuiScale();


        RenderSystem.enableScissor((int) ((5) * scaleFactor),
                (int) (Minecraft.getInstance().getWindow().getHeight() - (20 * 2) * scaleFactor),
                (int) ((25 * 2) * scaleFactor),
                (int) ((23 * 2) * scaleFactor));

        RenderEntityInv.renderEntityInInventoryFollowsAngle(guiGraphics, 30, 125, 65, 35.5f, 0, Minecraft.getInstance().player);

        RenderSystem.disableScissor();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, hud);

    };

}
