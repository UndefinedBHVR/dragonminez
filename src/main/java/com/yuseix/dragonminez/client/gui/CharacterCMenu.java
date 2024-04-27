package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.TextButton;
import com.yuseix.dragonminez.events.ModEvents;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.DinoEntity;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.PlayerStatsAttrProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CharacterCMenu extends Screen{

    private int alturaTexto;
    private int anchoTexto;

    private static final ResourceLocation menu1 = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menugrande.png");
    private static final ResourceLocation texto = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menutexto.png");

    //NOMBRES DE LAS RAZAS
    MutableComponent Human = (Component.translatable("dmz.races.name.human"));
    MutableComponent Saiyan = (Component.translatable("dmz.races.name.saiyan"));
    MutableComponent Namek = (Component.translatable("dmz.races.name.namek"));
    MutableComponent BioAndroid = (Component.translatable("dmz.races.name.bioandroid"));
    MutableComponent ColdDemon = (Component.translatable("dmz.races.name.colddemon"));
    MutableComponent Majin = (Component.translatable("dmz.races.name.majin"));

    //OTROS
    MutableComponent CCreation = (Component.translatable("dmz.ccreation.name"));
    MutableComponent Race = (Component.translatable("dmz.ccreation.race"));
    MutableComponent EYES = (Component.translatable("dmz.ccreation.eyestype"));

    private final List<AbstractWidget> botonesRazas = new ArrayList<>();

    private DMZRightButton botonRazaRight;
    private DMZRightButton botonRazaLeft;
    private DMZRightButton nextButton;
    private DMZRightButton backButton;
    private static int currentPage = 0;

    public CharacterCMenu(Component pTitle) {
        super(pTitle);
    }

    @Override
    protected void init() {
        super.init();

        //MenuInicio
        int posX = (this.minecraft.getWindow().getGuiScaledWidth()) / 2;
        int posY = (this.minecraft.getWindow().getGuiScaledHeight()) / 2;

        if(currentPage == 0){

        } else if (currentPage == 1){

        }


    }
    @Override
    public boolean isPauseScreen() {
        return false;
    }
    @Override
    public void tick() {
        super.tick();

        //MenuInicio
        int posX = (this.minecraft.getWindow().getGuiScaledWidth()) / 2;
        int posY = (this.minecraft.getWindow().getGuiScaledHeight()) / 2;

        if(currentPage == 0){
            this.removeWidget(botonRazaRight);
            this.removeWidget(botonRazaLeft);
            this.removeWidget(nextButton);
            this.removeWidget(backButton);

            nextButton = this.addRenderableWidget(new DMZRightButton("right",posX + 23, posY + 90, Component.empty(), button -> {
                currentPage = 1;
            }));


            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

                if(cap.getRace() == 0){
                    this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 1));
                    }));
                    this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 0));
                    }));
                }else if (cap.getRace() == 1){
                    this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 2));
                    }));
                    this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 0));
                    }));
                }else if (cap.getRace() == 2){
                    this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 3));
                    }));
                    this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 1));
                    }));
                }else if (cap.getRace() == 3){
                    this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 4));
                    }));
                    this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 2));
                    }));
                }else if (cap.getRace() == 4){
                    this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 5));
                    }));
                    this.botonRazaLeft =  (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 3));
                    }));
                }else if (cap.getRace() == 5){
                    this.botonRazaRight =  (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 0));
                    }));
                    this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 23, posY, Component.empty(), button -> {
                        ModMessages.sendToServer(new CharacterC2S("setRace", 4));
                    }));
                }

            });
        } else if(currentPage == 1){
            this.removeWidget(nextButton);
            this.removeWidget(backButton);

            backButton = this.addRenderableWidget(new DMZRightButton("left",posX - 23, posY + 90, Component.empty(), button -> {
                currentPage = 0;
            }));

        } else if (currentPage == 2) {

        } else {

        }

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);

        var Altura = pGuiGraphics.guiHeight();
        var Ancho = pGuiGraphics.guiWidth();


        if(currentPage == 0){
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
            RenderSystem.depthMask(false);
            alturaTexto = (Altura / 2);
            anchoTexto = (Ancho / 2);

            pGuiGraphics.blit(texto, anchoTexto - 60, alturaTexto + 85, 0, 16, 130, 18);
            RenderSystem.disableBlend();
            //TITULO
            alturaTexto = (Altura / 2) - 40;
            anchoTexto = ((Ancho - this.font.width(this.CCreation)) / 2);

            drawStringWithBorder(pGuiGraphics, font, CCreation,anchoTexto,alturaTexto, 0xFFFFFF);

            //RAZA TITULO

            alturaTexto = (Altura / 2) - 89;
            anchoTexto = ((Ancho - this.font.width(this.Race)) / 2) - 137;

            pGuiGraphics.drawString(font, Race.withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xFDFDFD, true);


            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

                var raza = cap.getRace();

                switch (raza){
                    case 0: //Humano
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.Human)) / 2);

                        pGuiGraphics.drawString(font, Human, anchoTexto, alturaTexto, 0x31EAFF, true);

                        break;
                    case 1: // Saiyan
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.Saiyan)) / 2);

                        pGuiGraphics.drawString(font, Saiyan, anchoTexto, alturaTexto, 0xFFBA35, true);

                        break;
                    case 2: // Namek
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.Namek)) / 2);

                        pGuiGraphics.drawString(font, Namek, anchoTexto, alturaTexto, 0x378942, true);

                        break;
                    case 3: // BioAndroid
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.BioAndroid)) / 2);

                        pGuiGraphics.drawString(font, BioAndroid, anchoTexto, alturaTexto, 0x72DA58, true);

                        break;
                    case 4: // ColdDemon
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.ColdDemon)) / 2);

                        pGuiGraphics.drawString(font, ColdDemon, anchoTexto, alturaTexto, 0xAC1BEC, true);

                        break;
                    case 5: // Majin
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.Majin)) / 2);

                        pGuiGraphics.drawString(font, Majin, anchoTexto, alturaTexto, 0xFE7FF4, true);

                        break;
                    default:

                        break;
                }

                Entity target = this.minecraft.player;
                Entity dino = new DinoEntity(MainEntity.DINO1.get(), this.minecraft.player.level());

                if(dino instanceof LivingEntity){
                    LivingEntity livingDino = (LivingEntity) dino;

                    renderEntityInInventoryFollowsAngle(pGuiGraphics, Ancho / 2, Altura/2, 20, 30, 0, livingDino);
                }
            });
        } else if(currentPage == 1){

            alturaTexto = (Altura / 2);
            anchoTexto = (Ancho / 2);

            RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
            RenderSystem.setShaderTexture(0, menu1);
            pGuiGraphics.blit(menu1, anchoTexto- 190, alturaTexto - 110, 0, 0, 148, 222);

            alturaTexto = (Altura / 2);
            anchoTexto = (Ancho- this.font.width(this.EYES)) / 2;
            pGuiGraphics.drawString(font,EYES.withStyle(ChatFormatting.BOLD),anchoTexto-115,alturaTexto-90,0xffffff);

        }else if(currentPage == 2){

        }else {

        }



        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

    }
    
    public static void drawStringWithBorder(GuiGraphics guiGraphics,Font font, Component texto, int x, int y, int ColorTexto, int ColorBorde){

        guiGraphics.drawString(font, texto, x + 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x - 1, y, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y + 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y - 1, ColorBorde, false);
        guiGraphics.drawString(font, texto, x, y, ColorTexto, false);
    }
    public static void drawStringWithBorder(GuiGraphics guiGraphics,Font font, Component texto, int x, int y, int ColorTexto){
        drawStringWithBorder(guiGraphics,font, texto, x,y,ColorTexto,0);
    }

    public static void renderEntityInInventoryFollowsAngle(GuiGraphics guiGraphics, int x, int y, int scale, float angleXComponent, float angleYComponent, LivingEntity livingEntity) {
        Quaternionf quaternionf = (new Quaternionf()).rotateZ(3.1415927F);
        Quaternionf quaternionf1 = (new Quaternionf()).rotateX(angleYComponent * 20.0F * 0.017453292F);
        quaternionf.mul(quaternionf1);
        float f2 = livingEntity.yBodyRot;
        float f3 = livingEntity.getYRot();
        float f4 = livingEntity.getXRot();
        float f5 = livingEntity.yHeadRotO;
        float f6 = livingEntity.yHeadRot;
        livingEntity.yBodyRot = 180.0F + angleXComponent * 20.0F;
        livingEntity.setYRot(180.0F + angleXComponent * 40.0F);
        livingEntity.setXRot(-angleYComponent * 20.0F);
        livingEntity.yHeadRot = livingEntity.getYRot();
        livingEntity.yHeadRotO = livingEntity.getYRot();
        renderEntityInInv(guiGraphics, x, y, scale, quaternionf, quaternionf1, livingEntity);
        livingEntity.yBodyRot = f2;
        livingEntity.setYRot(f3);
        livingEntity.setXRot(f4);
        livingEntity.yHeadRotO = f5;
        livingEntity.yHeadRot = f6;
    }


    public static void renderEntityInInv(GuiGraphics pGuiGraphics, int pX, int pY, int pScale, Quaternionf pPose, @Nullable Quaternionf pCameraOrientation, LivingEntity pEntity) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(pX, pY, 50.0);
        pGuiGraphics.pose().mulPoseMatrix((new Matrix4f()).scaling((float) pScale, (float) pScale, (float) (-pScale)));
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
