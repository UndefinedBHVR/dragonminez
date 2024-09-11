package com.yuseix.dragonminez.client.gui.cc;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.TextButton;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.TranslateManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import javax.annotation.Nullable;
import java.util.List;

public class CFirstPage extends Screen {

    private static final ResourceLocation texto = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menutexto.png");

    //Textura de los panoramas
    private static final ResourceLocation PANORAMA_PATH = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/panorama");
    private static final ResourceLocation PANORAMA_BUU = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/buu_panorama");
    private static final ResourceLocation PANORAMA_BIO = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/bio_panorama");
    private static final ResourceLocation PANORAMA_SAIYAN = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/s_panorama");
    private static final ResourceLocation PANORAMA_NAMEK = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/n_panorama");
    private static final ResourceLocation PANORAMA_COLD = new ResourceLocation(DragonMineZ.MOD_ID, "textures/gui/background/c_panorama");

    //Panoramas
    private final PanoramaRenderer customPanorama = new PanoramaRenderer(new CubeMap(PANORAMA_PATH));
    private final PanoramaRenderer panoramaBuu = new PanoramaRenderer(new CubeMap(PANORAMA_BUU));
    private final PanoramaRenderer panoramaBio = new PanoramaRenderer(new CubeMap(PANORAMA_BIO));
    private final PanoramaRenderer panoramaSai = new PanoramaRenderer(new CubeMap(PANORAMA_SAIYAN));
    private final PanoramaRenderer panoramaNam = new PanoramaRenderer(new CubeMap(PANORAMA_NAMEK));
    private final PanoramaRenderer panoramaCold = new PanoramaRenderer(new CubeMap(PANORAMA_COLD));

    private DMZRightButton botonRazaRight, botonRazaLeft;
    private TextButton nextButton;

    private int alturaTexto;
    private int anchoTexto;

    public CFirstPage() {
        super(Component.empty());
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void tick() {
        super.tick();

        botonesRazasElegir(this.width/2,(this.height/2) + 87);

        this.nextButton = this.addRenderableWidget(new TextButton(this.width - 85, this.height - 25, TranslateManager.NEXT.withStyle(ChatFormatting.BOLD), button -> {
            this.minecraft.setScreen(new CCustomizationPage(Component.empty()));
        }));

        }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

        panoramas(pGuiGraphics, pPartialTick);
        pagina0(pGuiGraphics, this.width, this.height);
        paginaInfoRazas(pGuiGraphics);


        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    public void pagina0(GuiGraphics pGuiGraphics, int posX, int posY) {

        RenderSystem.enableBlend();

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.depthMask(false);

        pGuiGraphics.blit(texto, (pGuiGraphics.guiWidth() / 2) - 60, (pGuiGraphics.guiHeight() / 2) + 85, 0, 16, 130, 18);
        //pGuiGraphics.blit(texto, (this.width / 2) - 60, 10, 0, 16, 130, 18);

        RenderSystem.disableBlend();


        //TITULO
        alturaTexto = (posY / 2) - 40;
        anchoTexto = posX/2;

        //drawStringWithBorder(pGuiGraphics, font, TranslateManager.CCreation, anchoTexto, 16, 0xFFFFFF);
        CCustomizationPage.drawStringWithBorder(pGuiGraphics, font, TranslateManager.CCreation, 3, 3, 0xFFFFFF);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            var raza = cap.getRace();

            switch (raza) {
                case 0: //Humano
                    alturaTexto = (posY / 2) + 90;
                    anchoTexto = (this.width/2);

                    pGuiGraphics.drawCenteredString(font, Component.translatable("dmz.races.name.human").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0x31EAFF);

                    break;
                case 1: // Saiyan
                    alturaTexto = (posY / 2) + 90;
                    anchoTexto = (this.width/2);

                    pGuiGraphics.drawCenteredString(font, Component.translatable("dmz.races.name.saiyan").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xFFBA35);

                    break;
                case 2: // Namek
                    alturaTexto = (posY / 2) + 90;
                    anchoTexto = (this.width/2);

                    pGuiGraphics.drawCenteredString(font, Component.translatable("dmz.races.name.namek").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0x378942);

                    break;
                case 3: // BioAndroid
                    alturaTexto = (posY / 2) + 90;
                    anchoTexto = (this.width/2);

                    pGuiGraphics.drawCenteredString(font, Component.translatable("dmz.races.name.bioandroid").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0x72DA58);

                    break;
                case 4: // ColdDemon
                    alturaTexto = (posY / 2) + 90;
                    anchoTexto = (this.width/2);

                    pGuiGraphics.drawCenteredString(font, Component.translatable("dmz.races.name.colddemon").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xAC1BEC);

                    break;
                case 5: // Majin
                    alturaTexto = (posY / 2) + 90;
                    anchoTexto = (this.width/2);

                    pGuiGraphics.drawCenteredString(font, Component.translatable("dmz.races.name.majin").withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xFE7FF4);

                    break;
                default:

                    break;
            }

            //LivingEntity dino = new DinoEntity(MainEntity.DINO1.get(), this.minecraft.level);
            renderEntityInInventoryFollowsAngle(pGuiGraphics, this.width/2, alturaTexto - 10, 70, 0, 0, minecraft.player);


        });
    }

    public void paginaInfoRazas(GuiGraphics graphics){
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            var raza = cap.getRace();

            if(raza == 0){

                CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("dmz.ccreation.human.title").withStyle(ChatFormatting.BOLD),10,(this.height/2)- 30,0x61C3FE);

                List<FormattedCharSequence> lines = font.split(Component.translatable("dmz.ccreation.human.description"), 130);
                for (int i = 0; i < lines.size(); i++) {
                    graphics.drawString(font, lines.get(i), 3, ((this.height/2 ) - 15)  + i * font.lineHeight, 0xFFFFFF);
                }
            } else if(raza == 1){
                CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("dmz.ccreation.saiyan.title").withStyle(ChatFormatting.BOLD),10,(this.height/2)-30,0xFEBA3A);

                List<FormattedCharSequence> lines = font.split(Component.translatable("dmz.ccreation.saiyan.description"), 130);
                for (int i = 0; i < lines.size(); i++) {
                    graphics.drawString(font, lines.get(i), 3, ((this.height/2 ) - 15)  + i * font.lineHeight, 0xFFFFFF);
                }
            } else if(raza == 2){
                CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("dmz.ccreation.namek.title").withStyle(ChatFormatting.BOLD),10,(this.height/2)-30,0x269021);

                List<FormattedCharSequence> lines = font.split(Component.translatable("dmz.ccreation.namek.description"), 130);
                for (int i = 0; i < lines.size(); i++) {
                    graphics.drawString(font, lines.get(i), 3, ((this.height/2 ) - 15)  + i * font.lineHeight, 0xFFFFFF);
                }
            } else if(raza == 3){
                CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("dmz.ccreation.bioandroid.title").withStyle(ChatFormatting.BOLD),10,(this.height/2)-30,0x69FE90);

                List<FormattedCharSequence> lines = font.split(Component.translatable("dmz.ccreation.bioandroid.description"), 130);
                for (int i = 0; i < lines.size(); i++) {
                    graphics.drawString(font, lines.get(i), 3, ((this.height/2 ) - 15) + i * font.lineHeight, 0xFFFFFF);
                }
            } else if(raza == 4){
                CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("dmz.ccreation.colddemon.title").withStyle(ChatFormatting.BOLD),10,(this.height/2)-30,0x9415B9);

                List<FormattedCharSequence> lines = font.split(Component.translatable("dmz.ccreation.colddemon.description"), 130);
                for (int i = 0; i < lines.size(); i++) {
                    graphics.drawString(font, lines.get(i), 3, ((this.height/2 ) - 15) + i * font.lineHeight, 0xFFFFFF);
                }
            } else {
                CCustomizationPage.drawStringWithBorder(graphics, font, Component.translatable("dmz.ccreation.majin.title").withStyle(ChatFormatting.BOLD),10,(this.height/2)-30,0xE691FF);

                List<FormattedCharSequence> lines = font.split(Component.translatable("dmz.ccreation.majin.description"), 130);
                for (int i = 0; i < lines.size(); i++) {
                    graphics.drawString(font, lines.get(i), 3, ((this.height/2 ) - 15) + i * font.lineHeight, 0xFFFFFF);
                }
            }

        });

        }

    public static void renderEntityInInventoryFollowsAngle(GuiGraphics guiGraphics, int x, int y, int scale, float angleXComponent, float angleYComponent, LivingEntity livingEntity) {
        Quaternionf quaternionf = (new Quaternionf()).rotateZ(3.1415927F);
        Quaternionf quaternionf1 = (new Quaternionf()).rotateX(angleYComponent * 20.0F * 0.017453292F);
        quaternionf.mul(quaternionf1);

        // Guardar las rotaciones actuales de la entidad
        float f2 = livingEntity.yBodyRot;
        float f3 = livingEntity.getYRot();
        float f4 = livingEntity.getXRot();
        float f5 = livingEntity.yHeadRotO;
        float f6 = livingEntity.yHeadRot;

        // Ajustar la rotación del cuerpo y de la cabeza
        livingEntity.yBodyRot = 180.0F + angleXComponent * 20.0F;
        livingEntity.yHeadRot = livingEntity.yBodyRot;
        livingEntity.yHeadRotO = livingEntity.yBodyRot;

        // Renderizar la entidad
        renderEntityInInv(guiGraphics, x, y, scale, quaternionf, quaternionf1, livingEntity);

        // Restaurar las rotaciones originales de la entidad
        livingEntity.yBodyRot = f2;
        livingEntity.setYRot(f3);
        livingEntity.setXRot(f4);
        livingEntity.yHeadRotO = f5;
        livingEntity.yHeadRot = f6;
    }

    public static void renderEntityInInv(GuiGraphics pGuiGraphics, int pX, int pY, int pScale, Quaternionf pPose, @Nullable Quaternionf pCameraOrientation, LivingEntity pEntity) {
        RenderSystem.enableBlend();
        RenderSystem.depthMask(true);
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
        RenderSystem.disableBlend();
    }



    public void botonesRazasElegir(int posX, int posY) {

        this.removeWidget(botonRazaRight);
        this.removeWidget(botonRazaLeft);

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            if (cap.getRace() == 0) {
                this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 1));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
                }));
            } else if (cap.getRace() == 1) {
                this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 2));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 2075172));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 12263460));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16746150));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                }));

                this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 0));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
                }));
            } else if (cap.getRace() == 2) {
                this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 3));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 1603072));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 10478369));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16741888));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                }));
                this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 1));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
                }));

            } else if (cap.getRace() == 3) {
                this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 4));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16777215));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 15246079));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16726441));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 16711709));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 16711709));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 7471273));
                }));
                this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 2));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 2075172));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 12263460));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16746150));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                }));
            } else if (cap.getRace() == 4) {

                this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 5));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16753919));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 11796480));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 11796480));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 16753919));
                }));
                this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 3));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 1603072));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 10478369));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16741888));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                }));
            } else {
                this.botonRazaLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 4));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16777215));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 15246079));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16726441));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 16711709));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 16711709));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 7471273));
                }));
            }

        });
    }

    public void panoramas(GuiGraphics graphics, float partialtick){

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {
            var race = cap.getRace();

            if(race == 0){
                this.customPanorama.render(partialtick, 1.0f);

            }else if(race == 1){
                this.panoramaSai.render(partialtick, 1.0f);

            }else if(race == 2){
                this.panoramaNam.render(partialtick, 1.0f);

            }else if(race == 3){
                this.panoramaBio.render(partialtick, 1.0f);

            }else if(race == 4){
                this.panoramaCold.render(partialtick, 1.0f);

            }else {
                this.panoramaBuu.render(partialtick, 1.0f);

            }
        });

    }
}
