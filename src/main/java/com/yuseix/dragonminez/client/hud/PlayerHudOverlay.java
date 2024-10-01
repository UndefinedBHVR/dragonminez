package com.yuseix.dragonminez.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.characters.*;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class PlayerHudOverlay implements RenderEntityInv {

    private static final ResourceLocation hud = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/hud/hud.png");

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

            if(playerstats.getRace() == 0){
                maxVIDA = vidaMC + ((double) (con) * DMCAttrConfig.MULTIPLIER_CON.get());
                StaminaMax = ((int) Math.round(maxVIDA * 0.5));
            } else if(playerstats.getRace() == 1){
                maxVIDA = vidaMC + ((double) (con) * DMCAttrConfig.MULTIPLIER_CON_SAIYAN.get());
                StaminaMax = ((int) Math.round(maxVIDA * 0.5));
            } else if(playerstats.getRace() == 2){
                maxVIDA = vidaMC + ((double) (con) * DMCAttrConfig.MULTIPLIER_CON_NAMEK.get());
                StaminaMax = ((int) Math.round(maxVIDA * 0.5));
            } else if(playerstats.getRace() == 3){
                maxVIDA = vidaMC + ((double) (con) * DMCAttrConfig.MULTIPLIER_CON_BIO.get());
                StaminaMax = ((int) Math.round(maxVIDA * 0.5));
            } else if(playerstats.getRace() == 4){
                maxVIDA = vidaMC + ((double) (con) * DMCAttrConfig.MULTIPLIER_CON_COLD.get());
                StaminaMax = ((int) Math.round(maxVIDA * 0.5));
            } else if(playerstats.getRace() == 5){
                maxVIDA = vidaMC + ((double) (con) * DMCAttrConfig.MULTIPLIER_CON_MAJIN.get());
                StaminaMax = ((int) Math.round(maxVIDA * 0.5));
            }

            int curStamina = playerstats.getCurStam();

            int energiaMax = 0;

            if(playerstats.getRace() == 0){
                energiaMax = ( (int) Math.round(playerstats.getEnergy() * DMCAttrConfig.MULTIPLIER_ENERGY.get() + 40));
            } else if(playerstats.getRace() == 1){
                energiaMax = ( (int) Math.round(playerstats.getEnergy() * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get() + 40));
            } else if(playerstats.getRace() == 2){
                energiaMax = ( (int) Math.round(playerstats.getEnergy() * DMCAttrConfig.MULTIPLIER_ENERGY_NAMEK.get() + 40));
            } else if(playerstats.getRace() == 3){
                energiaMax = ( (int) Math.round(playerstats.getEnergy() * DMCAttrConfig.MULTIPLIER_ENERGY_BIO.get() + 40));
            } else if(playerstats.getRace() == 4){
                energiaMax = ( (int) Math.round(playerstats.getEnergy() * DMCAttrConfig.MULTIPLIER_ENERGY_COLD.get() + 40));
            } else if(playerstats.getRace() == 5){
                energiaMax = ( (int) Math.round(playerstats.getEnergy() * DMCAttrConfig.MULTIPLIER_ENERGY_MAJIN.get() + 40));
            }
            
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

            guiGraphics.drawString(Minecraft.getInstance().font, String.valueOf( (int) Math.round(Minecraft.getInstance().player.getHealth())), 150, 16, 0xBB1C2A);



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

}
