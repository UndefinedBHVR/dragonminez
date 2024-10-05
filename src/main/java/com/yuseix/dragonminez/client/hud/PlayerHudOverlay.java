package com.yuseix.dragonminez.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.characters.*;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class PlayerHudOverlay implements RenderEntityInv {

    private static final ResourceLocation hud = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/hud/hud.png");

    private static int displayedRelease = 0; // Valor que se mostrará en pantalla, inicializado en 0
    private static int animationDurationTicks = 2 * (20); // Duración de la animación en ticks (2 segundos)
    private static int elapsedTicks = 0; // Ticks transcurridos desde que comenzó la animación
    public static final IGuiOverlay HUD_PLAYER = (forgeGui, guiGraphics, v, i, i1) -> {
        assert Minecraft.getInstance().player != null;
        int VidaMaxima = (int) Minecraft.getInstance().player.getMaxHealth();
        int vidarestante = (int) Minecraft.getInstance().player.getHealth();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(playerstats -> {
            var vidaMC = 20;
            var con = playerstats.getConstitution();
            var maxVIDA = 0.0;

            int vidawa = ((163 * vidarestante) / VidaMaxima);
            int vida = Math.min(vidawa, 163);

            int StaminaMax = 0;

            maxVIDA = DMZDatos.calcularCON(playerstats.getRace(), con, vidaMC, playerstats.getDmzClass());
            StaminaMax = DMZDatos.calcularSTM(playerstats.getRace(), (int) maxVIDA);

            int curStamina = playerstats.getCurStam();

            int energiaMax = 0;

            energiaMax = DMZDatos.calcularENE(playerstats.getRace(), playerstats.getEnergy(), playerstats.getDmzClass());
            
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


            guiGraphics.drawString(Minecraft.getInstance().font, Component.literal(String.valueOf( (int) Math.round(Minecraft.getInstance().player.getHealth())) + "/" + (int) Math.round(maxVIDA)).withStyle(ChatFormatting.BOLD), 150, 16, 0xfddb1e);


            Component porcentaje = Component.empty()
                    .append(Component.translatable("dmz.hud.powerrelease"))
                    .append(Component.literal(": "));

            var posXPowerRelease = 0;

            drawStringWithBorder(guiGraphics, Minecraft.getInstance().font, porcentaje, posXPowerRelease + 35, 44,0x38fff0);
            //drawStringWithBorder(guiGraphics, Minecraft.getInstance().font, Component.empty().append(Component.literal(String.valueOf(playerstats.getDmzRelease()))).append(Component.literal("%")), posXPowerRelease + 115, 44,0xfdbf26);
            renderPowerReleaseAnimation(guiGraphics, playerstats.getDmzRelease(), posXPowerRelease + 115);

        double scaleFactor = Minecraft.getInstance().getWindow().getGuiScale();


        RenderSystem.enableScissor((int) ((5) * scaleFactor),
                (int) (Minecraft.getInstance().getWindow().getHeight() - (20 * 2) * scaleFactor),
                (int) ((25 * 2) * scaleFactor),
                (int) ((23 * 2) * scaleFactor));


        personajesMenu(guiGraphics);

        RenderSystem.disableScissor();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, hud);

        });

    };

    public static void renderPowerReleaseAnimation(GuiGraphics guiGraphics, int porcentaje, int posXPowerRelease) {
        int targetRelease = porcentaje; // El valor objetivo que queremos mostrar

        // Incrementar o disminuir el valor mostrado basado en la duración de la animación
        if (elapsedTicks < animationDurationTicks) {
            // Proporción de la animación
            float deltaTime = (float) elapsedTicks / animationDurationTicks;

            // Calcula el incremento basado en el tiempo transcurrido
            int increment = (int) ((targetRelease - displayedRelease) * deltaTime);

            // Actualizar el valor mostrado
            displayedRelease += increment;

            // Asegurarse de no sobrepasar el objetivo
            if (displayedRelease > targetRelease) {
                displayedRelease = targetRelease;
            } else if (displayedRelease < targetRelease) {
                displayedRelease = targetRelease; // Para evitar ir en dirección contraria
            }

            // Incrementar los ticks transcurridos
            elapsedTicks++;
        } else {
            // Reiniciar el contador cuando la animación completa
            elapsedTicks = 0;
        }

        // Renderizar el valor con la animación
        drawStringWithBorder(guiGraphics, Minecraft.getInstance().font,
                Component.empty().append(Component.literal(String.valueOf(displayedRelease))).append(Component.literal("%")),
                posXPowerRelease, 44, 0xfdbf26);
    }


    public static void personajesMenu(GuiGraphics pGuiGraphics){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            if(cap.getRace() == 0){//HUMANO
                if (cap.getGender().equals("Male")){
                    LivingEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), Minecraft.getInstance().level);

                    RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);

                }else {
                    LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), Minecraft.getInstance().level);

                    RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);
                }

            }else if(cap.getRace() == 1){ //SAIYAN
                if(cap.getBodytype() == 0){
                    if(Minecraft.getInstance().player.getModelName().equals("default")){
                        LivingEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), Minecraft.getInstance().level);

                        RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);

                    }else {
                        LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), Minecraft.getInstance().level);

                        RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);
                    }

                } else {
                    if (cap.getGender().equals("Male")){
                        LivingEntity avatar = new FPHumanSaiyanEntity(MainEntity.FP_HUMANSAIYAN.get(), Minecraft.getInstance().level);

                        RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);

                    }else {
                        LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), Minecraft.getInstance().level);

                        RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);
                    }
                }

            }else if(cap.getRace() == 2){ //NAMEK
                LivingEntity avatar = new FPNamekianEntity(MainEntity.FP_NAMEK.get(), Minecraft.getInstance().level);

                RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);

            }else if(cap.getRace() == 3){ //BIOANDROIDE
                LivingEntity bioAndroidEntity = new FPBioAndroidEntity(MainEntity.FP_BIOANDROIDE.get(), Minecraft.getInstance().level);

                RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, bioAndroidEntity);

            }else if(cap.getRace() == 4){ //NARCO OSEA ARCO JEJE
                LivingEntity avatar = new FPDemonColdEntity(MainEntity.FP_DEMONCOLD.get(), Minecraft.getInstance().level);

                RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);

            }else { // MAJIN
                if (cap.getGender().equals("Male")){
                    LivingEntity avatar = new FPMajinGordEntity(MainEntity.FP_MAJINGORDO.get(), Minecraft.getInstance().level);

                    RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);

                }else {
                    LivingEntity avatar = new FPSlimEntity(MainEntity.FP_SLIMSAIYANHUM.get(), Minecraft.getInstance().level);

                    RenderEntityInv.renderEntityInInventoryFollowsAngle(pGuiGraphics, 30, 125, 65, 35.5f, 0, avatar);
                }
            }


        });

    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde) {

        guiGraphics.drawString(font, texto, x + 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x - 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y + 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y - 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y, ColorTexto, false);
    }

    public static void drawStringWithBorder(GuiGraphics guiGraphics, Font font, Component texto, int x, int y, int ColorTexto) {
        drawStringWithBorder(guiGraphics, font, texto, x, y, ColorTexto, 0);
    }

}
