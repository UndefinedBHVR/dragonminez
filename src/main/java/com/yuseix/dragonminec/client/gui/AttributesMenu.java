package com.yuseix.dragonminec.client.gui;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.client.gui.buttons.CustomButtons;
import com.yuseix.dragonminec.config.DMCAttrConfig;
import com.yuseix.dragonminec.events.ModEvents;
import com.yuseix.dragonminec.network.C2S.StatsC2S;
import com.yuseix.dragonminec.network.C2S.ZPointsC2S;
import com.yuseix.dragonminec.network.ModMessages;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AttributesMenu extends Screen {


    private int zpoints;

    {
        assert Minecraft.getInstance().player != null;
        zpoints = Minecraft.getInstance().player.getPersistentData().getInt("zpoints");
    }

    private String wa = String.valueOf(zpoints);

    private final List<AbstractWidget> botones = new ArrayList<>();

    private static final ResourceLocation menu = new ResourceLocation(DragonMineC.MODID,
            "textures/gui/menu.png");



    public AttributesMenu(Component pGuiScreen) {
        super(pGuiScreen);
    }



    @Override
    public void init() {
        super.init();

        //MenuInicio
        int posX = (this.width- 152)/2;
        int posY = (this.height- 256)/2;

        assert Minecraft.getInstance().player != null;
        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,Minecraft.getInstance().player).ifPresent(playerstats -> {

            int zpoints = playerstats.getZpoints();

            int zCost = (playerstats.getStrength()+
                    playerstats.getDefense()+
                    playerstats.getConstitution()+
                    playerstats.getKiPower()+
                    playerstats.getEnergy() + 6 ) * DMCAttrConfig.MULTIPLIER_ZPOINTS_COST.get();


            if (zpoints >= zCost) {
                //Fuerza
                botones.add(new CustomButtons(posX - 125, posY + 45, Component.empty(), button -> {
                    ModMessages.sendToServer(new StatsC2S(0, 1));
                    ModMessages.sendToServer(new ZPointsC2S(1, zCost));
                }));
                //Defensa
                botones.add(new CustomButtons(posX - 125, posY + 60, Component.empty(), button -> {
                    ModMessages.sendToServer(new StatsC2S(1, 1));
                    ModMessages.sendToServer(new ZPointsC2S(1, zCost));
                }));
                //Vida
                botones.add(new CustomButtons(posX - 125, posY + 75, Component.empty(), button -> {
                    ModMessages.sendToServer(new StatsC2S(2, 1));
                    ModMessages.sendToServer(new ZPointsC2S(1, zCost));
                }));
                //Kipower
                botones.add(new CustomButtons(posX - 125, posY + 90, Component.empty(), button -> {
                    ModMessages.sendToServer(new StatsC2S(3, 1));
                    ModMessages.sendToServer(new ZPointsC2S(1, zCost));
                }));
                //Energy
                botones.add(new CustomButtons(posX - 125, posY + 105, Component.empty(), button -> {
                    ModMessages.sendToServer(new StatsC2S(4, 1));
                    ModMessages.sendToServer(new ZPointsC2S(1, zCost));
                }));
            }


        });
        }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        //No calcular jugadores que no estan en linea pero registrados
        assert Minecraft.getInstance().player != null;
        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,Minecraft.getInstance().player).ifPresent(playerstats -> {

            int zpoints = playerstats.getZpoints();

            int zCost = (playerstats.getStrength()+
                    playerstats.getDefense()+
                    playerstats.getConstitution()+
                    playerstats.getKiPower()+
                    playerstats.getEnergy() + 6 ) * DMCAttrConfig.MULTIPLIER_ZPOINTS_COST.get();

            if(zpoints >= zCost){
                botones.forEach(this::addRenderableWidget);
            } else {
                this.clearWidgets();
            }

        });


    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(graphics);

        int posX = (this.width- 152)/2; //Normalmente da 100... (Ya no se ve)
        int posY = (this.height- 256)/2;

        RenderSystem.setShaderColor(1.0f, 1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0,menu);

        assert Minecraft.getInstance().player != null;
        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,Minecraft.getInstance().player).ifPresent(playerstats -> {

            //PuntosZ
            int zpoints = playerstats.getZpoints();

            int level = (playerstats.getStrength()+
                    playerstats.getDefense()+
                    playerstats.getConstitution()+
                    playerstats.getKiPower()+
                    playerstats.getEnergy())/5;

            int zCost = (playerstats.getStrength()+
                    playerstats.getDefense()+
                    playerstats.getConstitution()+
                    playerstats.getKiPower()+
                    playerstats.getEnergy() + 6 ) * DMCAttrConfig.MULTIPLIER_ZPOINTS_COST.get();

            //Attributos
            int str = playerstats.getStrength();
            int def = playerstats.getDefense();
            int con = playerstats.getConstitution();
            int kipower = playerstats.getKiPower();
            int energy = playerstats.getEnergy();
            int stamina = playerstats.getStamina();

            //AtributosMaximos
            int MaxStr = (int) (str * 0.5)*DMCAttrConfig.MULTIPLIER_STR.get();
            int MaxDef = (int) (def * 0.5)*DMCAttrConfig.MULTIPLIER_DEF.get();
            int MaxCon = (int) (con * 0.5)*DMCAttrConfig.MULTIPLIER_CON.get();
            int MaxKiPower = (int) (kipower * 0.5)*DMCAttrConfig.MULTIPLIER_KIPOWER.get();
            int MaxEnergy = (int) (energy * 0.5)*DMCAttrConfig.MULTIPLIER_ENERGY.get();
            int MaxStamina = stamina + 3;

            int LTITULO = posX-80;
            int RTITULO = posX +180;
            int LSUBTITULO = posX-120;
            int RSUBTITULO = posX+160;
            int LDESC = posX-60;
            int RDESC = posX+245;

            //SUBIR STATS
            //Pos cambiada a -140 de -250 por motivos de cambio de resoluciones para adaptar
            graphics.blit(menu, posX-140, posY,0,0,152,256);
            graphics.drawString(font, ChatFormatting.BOLD +"STATS",LTITULO,posY+15,0xFCC3C3, true);

            graphics.drawString(font, ChatFormatting.BOLD +"ZPoints: ",LSUBTITULO,posY+30,0xFFFFFF, true);
            graphics.drawString(font, String.valueOf(zpoints) ,posX-45,posY + 30,0xFFE800, false);

            graphics.drawString(font, ChatFormatting.BOLD +"STR: ",LSUBTITULO,posY+45,0x320C0C, true);
            graphics.drawString(font, String.valueOf(str) ,LDESC,posY + 45,0xBB1C2A, false);

            graphics.drawString(font, ChatFormatting.BOLD +"DEF: ",LSUBTITULO,posY+60,0x320C0C, true);
            graphics.drawString(font, String.valueOf(def),LDESC,posY + 60,0xBB1C2A, false);

            graphics.drawString(font, ChatFormatting.BOLD +"CON: ",LSUBTITULO,posY+75,0x320C0C, true);
            graphics.drawString(font, String.valueOf(con) ,LDESC,posY + 75,0xBB1C2A, false);

            graphics.drawString(font, ChatFormatting.BOLD +"POW: ",LSUBTITULO,posY+90,0x320C0C, true);
            graphics.drawString(font, String.valueOf(kipower) ,LDESC,posY + 90,0xBB1C2A, false);

            graphics.drawString(font, ChatFormatting.BOLD +"ENE: ",LSUBTITULO,posY+105,0x320C0C, true);
            graphics.drawString(font, String.valueOf(energy) ,LDESC,posY + 105,0xBB1C2A, false);

            graphics.drawString(font, ChatFormatting.BOLD +"ZPCost: ",LSUBTITULO,posY+120,0xF0B61E, true);
            graphics.drawString(font, String.valueOf(zCost),posX-45,posY+120,0xFFE800, false);

            //STATS
            graphics.blit(menu,posX+140,posY,0,0,152,256);
            graphics.drawString(font, ChatFormatting.BOLD +"INFORMATION",RTITULO,posY+15,0xF0B61E, true);

            graphics.drawString(font, ChatFormatting.BOLD +"Damage: ",RSUBTITULO,posY+30,0x830318, true);
            graphics.drawString(font, String.valueOf(MaxStr),RDESC,posY+30,0x9B1D32, false);

            graphics.drawString(font, ChatFormatting.BOLD +"Defense: ",RSUBTITULO,posY+45,0x830318, true);
            graphics.drawString(font, String.valueOf(MaxDef),RDESC,posY+45,0x9B1D32, false);

            graphics.drawString(font, ChatFormatting.BOLD +"Body: ",RSUBTITULO,posY+60,0x830318, true);
            graphics.drawString(font, String.valueOf(MaxCon),RDESC,posY+60,0x9B1D32, false);

            graphics.drawString(font, ChatFormatting.BOLD +"Stamina: ",RSUBTITULO,posY+75,0x830318, true);
            graphics.drawString(font, String.valueOf(MaxStamina),RDESC,posY+75,0x9B1D32, false);

            graphics.drawString(font, ChatFormatting.BOLD +"KiPower: ",RSUBTITULO,posY+90,0x830318, true);
            graphics.drawString(font, String.valueOf(MaxKiPower),RDESC,posY+90,0x9B1D32, false);

            graphics.drawString(font, ChatFormatting.BOLD +"Max Ki: ",RSUBTITULO,posY+105,0x830318, true);
            graphics.drawString(font, String.valueOf(MaxEnergy),RDESC,posY+105,0x9B1D32, false);

            //Asegurandose de que el jugador se encuentra en linea (no sirve realmente lol)
            assert this.minecraft != null;
            assert this.minecraft.player!= null;

            graphics.drawString(font, ChatFormatting.BOLD + this.minecraft.player.getName().getString() ,posX+63,posY+10,0xFFFFFF, true);
            graphics.drawString(font, ChatFormatting.BOLD + "Lvl: ",posX+50,posY+25,0xFFE800, true);
            graphics.drawString(font, ChatFormatting.BOLD + String.valueOf(level),posX+80,posY+25,0x67EDFC, true);

            renderEntityInInventoryFollowsAngle(graphics, posX+73, posY+200, 80, 0, 0, this.minecraft.player);

            graphics.drawString(font, ChatFormatting.BOLD + "Humano :v" ,posX+45,posY+220,0x45E9FC, true);



        });

        super.render(graphics, pMouseX, pMouseY, pPartialTick);

    }





    public static void renderEntityInInventoryFollowsAngle(GuiGraphics p_282802_, int p_275688_, int p_275245_, int p_275535_, float angleXComponent, float angleYComponent, LivingEntity p_275689_) {
        Quaternionf quaternionf = (new Quaternionf()).rotateZ(3.1415927F);
        Quaternionf quaternionf1 = (new Quaternionf()).rotateX(angleYComponent * 20.0F * 0.017453292F);
        quaternionf.mul(quaternionf1);
        float f2 = p_275689_.yBodyRot;
        float f3 = p_275689_.getYRot();
        float f4 = p_275689_.getXRot();
        float f5 = p_275689_.yHeadRotO;
        float f6 = p_275689_.yHeadRot;
        p_275689_.yBodyRot = 180.0F + angleXComponent * 20.0F;
        p_275689_.setYRot(180.0F + angleXComponent * 40.0F);
        p_275689_.setXRot(-angleYComponent * 20.0F);
        p_275689_.yHeadRot = p_275689_.getYRot();
        p_275689_.yHeadRotO = p_275689_.getYRot();
        renderEntityInInventory(p_282802_, p_275688_, p_275245_, p_275535_, quaternionf, quaternionf1, p_275689_);
        p_275689_.yBodyRot = f2;
        p_275689_.setYRot(f3);
        p_275689_.setXRot(f4);
        p_275689_.yHeadRotO = f5;
        p_275689_.yHeadRot = f6;
    }
    public static void renderEntityInInventory(GuiGraphics pGuiGraphics, int pX, int pY, int pScale, Quaternionf pPose, @Nullable Quaternionf pCameraOrientation, LivingEntity pEntity) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(pX, pY, 50.0);
        pGuiGraphics.pose().mulPoseMatrix((new Matrix4f()).scaling((float)pScale, (float)pScale, (float)(-pScale)));
        pGuiGraphics.pose().mulPose(pPose);
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        if (pCameraOrientation != null) {
            pCameraOrientation.conjugate();
            entityrenderdispatcher.overrideCameraOrientation(pCameraOrientation);
        }

        entityrenderdispatcher.setRenderShadow(false);
        entityrenderdispatcher.render(pEntity, 0.0, 0.0, 0.0, 0.0F, 1.0F, pGuiGraphics.pose(), pGuiGraphics.bufferSource(), 15728880);
        pGuiGraphics.flush();
        entityrenderdispatcher.setRenderShadow(true);
        pGuiGraphics.pose().popPose();
        Lighting.setupFor3DItems();
    }

}
