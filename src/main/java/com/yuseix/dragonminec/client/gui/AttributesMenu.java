package com.yuseix.dragonminec.client.gui;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.client.ClientPlayerStats;
import com.yuseix.dragonminec.client.gui.buttons.CustomButtons;
import com.yuseix.dragonminec.network.C2S.StatsC2S;
import com.yuseix.dragonminec.network.C2S.ZPointsC2S;
import com.yuseix.dragonminec.network.ModMessages;
import com.yuseix.dragonminec.network.S2C.ZPointsS2C;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AttributesMenu extends Screen {


    private int zpoints = Minecraft.getInstance().player.getPersistentData().getInt("zpoints");

    private String wa = String.valueOf(zpoints);

    private final List<AbstractWidget> botones = new ArrayList<>();

    private static final ResourceLocation menu = new ResourceLocation(DragonMineC.MODID,
            "textures/gui/menu.png");



    public AttributesMenu(Component p_96550_) {
        super(p_96550_);
    }


    @Override
    public void init() {
        super.init();
        //MenuInicio
        int posX = (this.width- 152)/2;
        int posY = (this.height- 256)/2;

        if(ClientPlayerStats.getZpoints() >= ClientPlayerStats.getZcost()){
            //Fuerza
            botones.add(new CustomButtons(posX-125,posY+45,Component.empty(),button -> {
                ModMessages.sendToServer(new StatsC2S(0,1));
                ModMessages.sendToServer(new ZPointsC2S(1,ClientPlayerStats.getZcost()));
            }));
            //Defensa
            botones.add(new CustomButtons(posX-125,posY+60,Component.empty(),button -> {
                ModMessages.sendToServer(new StatsC2S(1,1));
                ModMessages.sendToServer(new ZPointsC2S(1,ClientPlayerStats.getZcost()));
            }));
            //Vida
            botones.add(new CustomButtons(posX-125,posY+75,Component.empty(),button -> {
                ModMessages.sendToServer(new StatsC2S(2,1));
                ModMessages.sendToServer(new ZPointsC2S(1,ClientPlayerStats.getZcost()));
            }));
            //Kipower
            botones.add(new CustomButtons(posX-125,posY+90,Component.empty(),button -> {
                ModMessages.sendToServer(new StatsC2S(3,1));
                ModMessages.sendToServer(new ZPointsC2S(1,ClientPlayerStats.getZcost()));
            }));
            //Energy
            botones.add(new CustomButtons(posX-125,posY+105,Component.empty(),button -> {
                ModMessages.sendToServer(new StatsC2S(4,1));
                ModMessages.sendToServer(new ZPointsC2S(1,ClientPlayerStats.getZcost()));
            }));

        }


    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if(ClientPlayerStats.getZpoints() >= ClientPlayerStats.getZcost()){
            botones.forEach(this::addRenderableWidget);
        } else if (ClientPlayerStats.getZpoints() < ClientPlayerStats.getZcost()){
            this.clearWidgets();
        }
    }

    @Override
    public void render(GuiGraphics graphics, int p_281550_, int p_282878_, float p_282465_) {
        this.renderBackground(graphics);

        String strAttribute = String.valueOf(ClientPlayerStats.getStrenght());
        String defAttribute = String.valueOf(ClientPlayerStats.getDefense());
        String conAttribute = String.valueOf(ClientPlayerStats.getConstitution());
        String kipwrAttribute = String.valueOf(ClientPlayerStats.getKipower());
        String energyAttribute = String.valueOf(ClientPlayerStats.getEnergy());

        String zpoints = String.valueOf(ClientPlayerStats.getZpoints());

        String strMaxAttr = String.valueOf(ClientPlayerStats.getMaxSTR());
        String defMaxAttr = String.valueOf(ClientPlayerStats.getMaxDEF());
        String conMaxAttr = String.valueOf(ClientPlayerStats.getMaxCON());
        String kipwrMaxAttr = String.valueOf(ClientPlayerStats.getMaxKIPWR());
        String energyMaxAttr = String.valueOf(ClientPlayerStats.getMaxENERGY());


        int posX = (this.width- 152)/2;
        int posY = (this.height- 256)/2;

        RenderSystem.setShaderColor(1.0f, 1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0,menu);


        //SUBIR STATS
        graphics.blit(menu,posX-250,posY,0,0,152,256);
        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"STATS",posX-190,posY+15,0xFCC3C3, true);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"ZPoints: ",posX-230,posY+30,0xFFFFFF, true);
        graphics.drawString(Minecraft.getInstance().font, zpoints,posX-155,posY + 30,0xFFE800, false);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"STR: ",posX-230,posY+45,0x320C0C, true);
        graphics.drawString(Minecraft.getInstance().font, strAttribute ,posX-170,posY + 45,0xBB1C2A, false);


        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"DEF: ",posX-230,posY+60,0x320C0C, true);
        graphics.drawString(Minecraft.getInstance().font, defAttribute,posX-170,posY + 60,0xBB1C2A, false);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"CON: ",posX-230,posY+75,0x320C0C, true);
        graphics.drawString(Minecraft.getInstance().font, conAttribute ,posX-170,posY + 75,0xBB1C2A, false);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"POW: ",posX-230,posY+90,0x320C0C, true);
        graphics.drawString(Minecraft.getInstance().font, kipwrAttribute ,posX-170,posY + 90,0xBB1C2A, false);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"ENE: ",posX-230,posY+105,0x320C0C, true);
        graphics.drawString(Minecraft.getInstance().font, energyAttribute ,posX-170,posY + 105,0xBB1C2A, false);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"ZPCost: ",posX-230,posY+120,0xF0B61E, true);
        graphics.drawString(Minecraft.getInstance().font, String.valueOf(ClientPlayerStats.getZcost()),posX-155,posY+120,0xFFE800, false);

        //STATS
        graphics.blit(menu,posX+250,posY,0,0,152,256);
        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"INFORMATION",posX+290,posY+15,0xF0B61E, true);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"Damage: ",posX+270,posY+30,0x830318, true);
        graphics.drawString(Minecraft.getInstance().font, String.valueOf(ClientPlayerStats.getMaxSTR()),posX+355,posY+30,0x9B1D32, false);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"Defense: ",posX+270,posY+45,0x830318, true);
        graphics.drawString(Minecraft.getInstance().font, String.valueOf(ClientPlayerStats.getMaxDEF()),posX+355,posY+45,0x9B1D32, false);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"Body: ",posX+270,posY+60,0x830318, true);
        graphics.drawString(Minecraft.getInstance().font, String.valueOf(ClientPlayerStats.getMaxCON()),posX+355,posY+60,0x9B1D32, false);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"Stamina: ",posX+275,posY+75,0x830318, true);
        graphics.drawString(Minecraft.getInstance().font, String.valueOf(ClientPlayerStats.getMaxSTAMINA()),posX+360,posY+75,0x9B1D32, false);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"KiPower: ",posX+270,posY+90,0x830318, true);
        graphics.drawString(Minecraft.getInstance().font, String.valueOf(ClientPlayerStats.getMaxKIPWR()),posX+355,posY+90,0x9B1D32, false);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD +"Max Ki: ",posX+270,posY+105,0x830318, true);
        graphics.drawString(Minecraft.getInstance().font, String.valueOf(ClientPlayerStats.getMaxENERGY()),posX+355,posY+105,0x9B1D32, false);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD + ""+ this.minecraft.player.getName().getString() ,posX+63,posY+10,0xFFFFFF, true);
        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD + "Lvl: ",posX+50,posY+25,0xFFE800, true);
        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD + "" + String.valueOf(ClientPlayerStats.getLevel()),posX+80,posY+25,0x67EDFC, true);

        renderEntityInInventoryFollowsAngle(graphics, posX+73, posY+200, 80, 0, 0, this.minecraft.player);

        graphics.drawString(Minecraft.getInstance().font, ChatFormatting.BOLD + "Humano :v" ,posX+45,posY+220,0x45E9FC, true);



        super.render(graphics, p_281550_, p_282878_, p_282465_);

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
        pGuiGraphics.pose().translate((double)pX, (double)pY, 50.0);
        pGuiGraphics.pose().mulPoseMatrix((new Matrix4f()).scaling((float)pScale, (float)pScale, (float)(-pScale)));
        pGuiGraphics.pose().mulPose(pPose);
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        if (pCameraOrientation != null) {
            pCameraOrientation.conjugate();
            entityrenderdispatcher.overrideCameraOrientation(pCameraOrientation);
        }

        entityrenderdispatcher.setRenderShadow(false);
        RenderSystem.runAsFancy(() -> {
            entityrenderdispatcher.render(pEntity, 0.0, 0.0, 0.0, 0.0F, 1.0F, pGuiGraphics.pose(), pGuiGraphics.bufferSource(), 15728880);
        });
        pGuiGraphics.flush();
        entityrenderdispatcher.setRenderShadow(true);
        pGuiGraphics.pose().popPose();
        Lighting.setupFor3DItems();
    }

}
