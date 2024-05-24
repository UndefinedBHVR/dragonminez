package com.yuseix.dragonminez.client.gui;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.RenderEntityInv;
import com.yuseix.dragonminez.client.gui.buttons.ColorButton;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.TextButton;
import com.yuseix.dragonminez.events.ModEvents;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.DinoEntity;
import com.yuseix.dragonminez.init.entity.custom.FakeBioAndroidEntity;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.PlayerStatsAttrProvider;
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
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.gui.widget.ForgeSlider;
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
    private static final ResourceLocation menu2 = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menumedio.png");
    private static final ResourceLocation texto = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/menutexto.png");
    private static final ResourceLocation colorCuadrado = new ResourceLocation(DragonMineZ.MOD_ID,
            "textures/gui/buttons/characterbuttons.png");

    private final List<AbstractWidget> botonOjos = new ArrayList<>();

    private DMZRightButton botonRazaRight,botonRazaLeft, eyesTypeRight, eyesTypeLeft, bodyTypeRightButton, bodyTypeLeftButton, gendersRigthButton,gendersLeftButton;

    private TextButton nextButton,backButton, setColor;
    private ColorButton eyesButtonColor,eyesButtonColor2,bodyButtonColor1,bodyButtonColor2,bodyButtonColor3, hairButtonColor;
    private ForgeSlider sliderR,sliderG,sliderB;
    private int colorR,colorG,colorB;
    private static int currentPage = 0;
    private static String partePagina = "";



    //NOMBRES DE LAS RAZAS
    private MutableComponent Human = (Component.translatable("dmz.races.name.human"));
    private MutableComponent Saiyan = (Component.translatable("dmz.races.name.saiyan"));
    private MutableComponent Namek = (Component.translatable("dmz.races.name.namek"));
    private MutableComponent BioAndroid = (Component.translatable("dmz.races.name.bioandroid"));
    private MutableComponent ColdDemon = (Component.translatable("dmz.races.name.colddemon"));
    private MutableComponent Majin = (Component.translatable("dmz.races.name.majin"));

    //OTROS
    private MutableComponent CCreation = (Component.translatable("dmz.ccreation.name"));
    //RAZA TITULO
    private MutableComponent Race = (Component.translatable("dmz.ccreation.race"));
    //OJO TITULO
    private MutableComponent EYES = (Component.translatable("dmz.ccreation.eyestype"));
    //GENERO TITULO
    private MutableComponent GENDERS = (Component.translatable("dmz.ccreation.genders"));
    //CUERPOS TITULO
    private MutableComponent BODYTYPES = (Component.translatable("dmz.ccreation.bodytypes"));
    //HAIR TITULO
    private MutableComponent HAIRTYPES = (Component.translatable("dmz.ccreation.hairtypes"));

    //BACK BUTTON
    private MutableComponent BACK = (Component.translatable("dmz.ccreation.back"));
    //NEXT BUTTON
    private MutableComponent NEXT = (Component.translatable("dmz.ccreation.next"));
    //COLOR MENU TITULO
    private MutableComponent COLOR_MENU = (Component.translatable("dmz.ccreation.color_menu"));

    //TIPO DE OJOS 1
    private MutableComponent EYES_TYPE_1 = (Component.translatable("dmz.ccreation.eyestype.type1"));
    private MutableComponent EYES_TYPE_2 = (Component.translatable("dmz.ccreation.eyestype.type2"));

    //TIPO DE CUERPO HUMANO 1
    private MutableComponent H_BODY_TYPE = (Component.translatable("dmz.ccreation.human.bodytype.type1"));
    private MutableComponent H_BODY_TYPE2 = (Component.translatable("dmz.ccreation.human.bodytype.type2"));

    //TIPO DE CUERPO BIOANDROID 1
    private MutableComponent B_BODY_TYPE = (Component.translatable("dmz.ccreation.bioandroid.bodytype.type1"));
    //TIPO DE GENEROS
    private MutableComponent GENDER_MALE = (Component.translatable("dmz.ccreation.genders.male"));
    private MutableComponent GENDER_FEMALE = (Component.translatable("dmz.ccreation.genders.female"));
    //TIPO DE CABELLOS
    private MutableComponent HAIR_0 = (Component.translatable("dmz.ccreation.hairs.0"));

    public CharacterCMenu(Component pTitle) {
        super(pTitle);
    }

    @Override
    protected void init() {

        //MenuInicio
        int posX = (this.width);
        int posY = (this.minecraft.getWindow().getGuiScaledHeight()) / 2;

        if(currentPage == 0){

        } else if (currentPage == 1){
            sliders(posX-127,posY+25);

            botonesRazaColores(72,posY);

        }

        super.init();
    }
    @Override
    public boolean isPauseScreen() {
        return false;
    }
    @Override
    public void tick() {
        super.tick();

        //MenuInicio
        int ancho = (this.width/2);
        int alto = (this.minecraft.getWindow().getGuiScaledHeight()) / 2;


        botonNextBack(ancho, this.height- 25);

        if(currentPage == 0){

                botonesRazasElegir(ancho,alto+87);

        } else if(currentPage == 1){

            botonesBodyType(113, alto - 40);
            botonesGeneros(113, alto - 76);
            botonesOjos(113,alto + 10);

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
            alturaTexto = (Altura / 2);
            anchoTexto = (Ancho / 2);
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
            RenderSystem.depthMask(false);

            pGuiGraphics.blit(texto, anchoTexto - 60, alturaTexto + 85, 0, 16, 130, 18);
            RenderSystem.disableBlend();
            //TITULO
            alturaTexto = (Altura / 2) - 40;
            anchoTexto = ((Ancho - this.font.width(this.CCreation)) / 2);

            drawStringWithBorder(pGuiGraphics, font, this.CCreation,anchoTexto,alturaTexto, 0xFFFFFF);

            //RAZA TITULO
            alturaTexto = (Altura / 2) - 89;
            anchoTexto = ((Ancho - this.font.width(this.Race)) / 2) - 137;

            pGuiGraphics.drawString(font, this.Race.withStyle(ChatFormatting.BOLD), anchoTexto, alturaTexto, 0xFDFDFD, true);


            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

                var raza = cap.getRace();

                switch (raza){
                    case 0: //Humano
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.Human)) / 2);

                        pGuiGraphics.drawString(font, this.Human, anchoTexto, alturaTexto, 0x31EAFF, true);

                        break;
                    case 1: // Saiyan
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.Saiyan)) / 2);

                        pGuiGraphics.drawString(font, this.Saiyan, anchoTexto, alturaTexto, 0xFFBA35, true);

                        break;
                    case 2: // Namek
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.Namek)) / 2);

                        pGuiGraphics.drawString(font, this.Namek, anchoTexto, alturaTexto, 0x378942, true);

                        break;
                    case 3: // BioAndroid
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.BioAndroid)) / 2);

                        pGuiGraphics.drawString(font, this.BioAndroid, anchoTexto, alturaTexto, 0x72DA58, true);

                        break;
                    case 4: // ColdDemon
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.ColdDemon)) / 2);

                        pGuiGraphics.drawString(font, this.ColdDemon, anchoTexto, alturaTexto, 0xAC1BEC, true);

                        break;
                    case 5: // Majin
                        alturaTexto = (Altura / 2) + 90;
                        anchoTexto = ((Ancho - this.font.width(this.Majin)) / 2);

                        pGuiGraphics.drawString(font, this.Majin, anchoTexto, alturaTexto, 0xFE7FF4, true);

                        break;
                    default:

                        break;
                }

                LivingEntity bio = new FakeBioAndroidEntity(MainEntity.FAKEBIOANDROID1.get(),this.minecraft.level);
                LivingEntity dino = new DinoEntity(MainEntity.DINO1.get(), this.minecraft.level);

                alturaTexto = (this.height + 125)/ 2;
                anchoTexto = this.width/2;

                if(cap.getRace() == 0){
                    //renderEntityInInventoryFollowsAngle(pGuiGraphics, anchoTexto, alturaTexto, 15, 30, 0, bio);
                }

            });
        } else if(currentPage == 1){

            //MENU CARACTERISTICAS
            alturaTexto = (Altura / 2);
            anchoTexto = 10;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
            pGuiGraphics.blit(menu1, anchoTexto, alturaTexto - 110, 0, 0, 148, 222);
            RenderSystem.disableBlend();

            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, minecraft.player).ifPresent(cap -> {

                switch(cap.getRace()){
                    case 0:
                        //GENERO TITULO
                        alturaTexto = (Altura / 2);
                        anchoTexto = (20 - this.font.width(this.GENDERS));
                        pGuiGraphics.drawString(font,this.GENDERS.withStyle(ChatFormatting.BOLD),anchoTexto+87,alturaTexto-89,0xFF9B9B);


                        if(cap.getGender().equals("Male")){
                            alturaTexto = (Altura / 2);
                            anchoTexto = (20 - this.font.width(this.GENDER_MALE));
                            drawStringWithBorder(pGuiGraphics,font,this.GENDER_MALE,anchoTexto +77,alturaTexto - 72,0xFFFFFF, 0x2133A6);
                        } else if(cap.getGender().equals("Female")){
                            alturaTexto = (Altura / 2);
                            anchoTexto = (20 - this.font.width(this.GENDER_FEMALE));
                            drawStringWithBorder(pGuiGraphics,font,this.GENDER_FEMALE,anchoTexto +84,alturaTexto - 72,0xFFFFFF, 0xFC63D9);

                        }


                        //TIPO DE CUERPO
                        alturaTexto = (Altura / 2);
                        anchoTexto = 47;
                        RenderSystem.enableBlend();
                        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
                        RenderSystem.setShaderTexture(0, texto);
                        pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 57, 0, 0, 73, 15);
                        RenderSystem.disableBlend();
                        //CUERPO TITULO
                        alturaTexto = (Altura / 2);
                        anchoTexto = (20- this.font.width(this.BODYTYPES)) ;
                        pGuiGraphics.drawString(font,this.BODYTYPES.withStyle(ChatFormatting.BOLD),anchoTexto+95,alturaTexto-54,0xFFCA9B);
                        //TIPO DE CUERPO
                        if(cap.getBodytype() == 0){
                            alturaTexto = (Altura / 2);
                            anchoTexto = (20 - this.font.width(this.H_BODY_TYPE));
                            drawStringWithBorder(pGuiGraphics,font,this.H_BODY_TYPE,anchoTexto + 83,alturaTexto - 36,0xFFFFFF);
                        } else if(cap.getBodytype() == 1){
                            alturaTexto = (Altura / 2);
                            anchoTexto = (20 - this.font.width(this.H_BODY_TYPE2));
                            drawStringWithBorder(pGuiGraphics,font,this.H_BODY_TYPE2,anchoTexto + 83,alturaTexto - 36,0xFFFFFF);

                        }
                        //OJOS
                        alturaTexto = (Altura / 2);
                        anchoTexto = 47;
                        RenderSystem.enableBlend();
                        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
                        RenderSystem.setShaderTexture(0, texto);
                        pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 6, 0, 0, 73, 15);
                        RenderSystem.disableBlend();
                        //OJOS TITULO
                        alturaTexto = (Altura / 2);
                        anchoTexto = (20 - this.font.width(this.EYES));
                        pGuiGraphics.drawString(font,this.EYES.withStyle(ChatFormatting.BOLD),anchoTexto+95,alturaTexto-3,0xFF9B9B);

                            if(cap.getEyesType() == 0){
                                alturaTexto = (Altura / 2);
                                anchoTexto = (20 - this.font.width(this.EYES_TYPE_1));
                                drawStringWithBorder(pGuiGraphics,font,this.EYES_TYPE_1,anchoTexto +83,alturaTexto + 13,0xFFFFFF);
                            } else if(cap.getEyesType() == 1){
                                alturaTexto = (Altura / 2);
                                anchoTexto = (20 - this.font.width(this.EYES_TYPE_2));
                                drawStringWithBorder(pGuiGraphics,font,this.EYES_TYPE_2,anchoTexto +83,alturaTexto + 13,0xFFFFFF);

                            }

                        //HAIR COLOR
                        alturaTexto = (Altura / 2);
                        anchoTexto = 47;
                        RenderSystem.enableBlend();
                        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
                        RenderSystem.setShaderTexture(0, texto);
                        pGuiGraphics.blit(texto, anchoTexto, alturaTexto + 42, 0, 0, 73, 15);
                        RenderSystem.disableBlend();
                        //HAIR TITULO
                        anchoTexto = (20 - this.font.width(this.HAIRTYPES));
                        pGuiGraphics.drawString(font,this.HAIRTYPES.withStyle(ChatFormatting.BOLD),anchoTexto+77,alturaTexto+46,0x65FC63);

                        if (cap.getHairID() == 0){
                            anchoTexto = (20 - this.font.width(this.HAIR_0));
                            drawStringWithBorder(pGuiGraphics,font,this.HAIR_0,anchoTexto +83,alturaTexto + 62,0xFFFFFF);
                        }else if(cap.getHairID() == 1){

                        }

                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        //TIPO DE OJOS
                        alturaTexto = (Altura / 2);
                        anchoTexto = (20 - this.font.width(this.EYES));
                        pGuiGraphics.drawString(font,this.EYES.withStyle(ChatFormatting.BOLD),anchoTexto+94,alturaTexto-89,0xFF9B9B);

                        alturaTexto = (Altura / 2);
                        anchoTexto = (20 - this.font.width(this.EYES_TYPE_1));
                        drawStringWithBorder(pGuiGraphics,font,this.EYES_TYPE_1,anchoTexto +83,alturaTexto - 70,0xFFFFFF);

                        //TIPO DE CUERPO
                        alturaTexto = (Altura / 2);
                        anchoTexto = 47;
                        RenderSystem.enableBlend();
                        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
                        RenderSystem.setShaderTexture(0, texto);
                        pGuiGraphics.blit(texto, anchoTexto, alturaTexto - 39, 0, 0, 73, 15);
                        RenderSystem.disableBlend();

                        alturaTexto = (Altura / 2);
                        anchoTexto = (20- this.font.width(this.BODYTYPES)) ;
                        pGuiGraphics.drawString(font,this.BODYTYPES.withStyle(ChatFormatting.BOLD),anchoTexto+94,alturaTexto-36,0xFFCA9B);

                        alturaTexto = (Altura / 2);
                        anchoTexto = (20 - this.font.width(this.B_BODY_TYPE));
                        drawStringWithBorder(pGuiGraphics,font,this.B_BODY_TYPE,anchoTexto + 106,alturaTexto - 17,0xFFFFFF);

                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
            });

            //MENU COLOR
            alturaTexto = (this.height - 170)/ 2;
            anchoTexto = this.width - 150;
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0f);
            pGuiGraphics.blit(menu2, anchoTexto, alturaTexto, 0, 0, 148, 163);
            RenderSystem.disableBlend();
            //TITULO
            alturaTexto = (Altura / 2);
            anchoTexto = (this.width- this.font.width(this.COLOR_MENU));
            pGuiGraphics.drawString(font,this.COLOR_MENU.withStyle(ChatFormatting.BOLD),anchoTexto-60,alturaTexto-63,0xFF9B9B);

            //cuadrado colores.
            alturaTexto = (Altura / 2) + 10;
            anchoTexto = (this.width - 105);
            float rColor,gColor,bColor;

            switch(partePagina){
                case "ojo1":
                    rColor = sliderR.getValueInt() / 255.0F;
                    gColor = sliderG.getValueInt() / 255.0f;
                    bColor = sliderB.getValueInt() / 255.0f;

                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(rColor,gColor,bColor,1.0f);
                    pGuiGraphics.blit(colorCuadrado, anchoTexto, alturaTexto, 41, 0, 61, 14);
                    RenderSystem.disableBlend();
                    break;
                case "ojo2":
                    rColor = sliderR.getValueInt() / 255.0F;
                    gColor = sliderG.getValueInt() / 255.0f;
                    bColor = sliderB.getValueInt() / 255.0f;

                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(rColor,gColor,bColor,1.0f);
                    pGuiGraphics.blit(colorCuadrado, anchoTexto, alturaTexto, 41, 0, 61, 14);
                    RenderSystem.disableBlend();
                    break;
                case "cuerpo1":
                    rColor = sliderR.getValueInt() / 255.0F;
                    gColor = sliderG.getValueInt() / 255.0f;
                    bColor = sliderB.getValueInt() / 255.0f;

                    RenderSystem.enableBlend();
                    RenderSystem.setShaderColor(rColor,gColor,bColor,1.0f);
                    pGuiGraphics.blit(colorCuadrado, anchoTexto, alturaTexto, 41, 0, 61, 14);
                    RenderSystem.disableBlend();
                    break;
                case "cuerpo2":
                    rColor = sliderR.getValueInt() / 255.0F;
                    gColor = sliderG.getValueInt() / 255.0f;
                    bColor = sliderB.getValueInt() / 255.0f;

                    RenderSystem.enableBlend();
                    RenderSystem.setShaderTexture(0,colorCuadrado);
                    RenderSystem.setShaderColor(rColor,gColor,bColor,1.0f);
                    pGuiGraphics.blit(colorCuadrado, anchoTexto, alturaTexto, 41, 0, 61, 14);
                    RenderSystem.disableBlend();
                    break;
                case "cuerpo3":
                    rColor = sliderR.getValueInt() / 255.0F;
                    gColor = sliderG.getValueInt() / 255.0f;
                    bColor = sliderB.getValueInt() / 255.0f;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderTexture(0,colorCuadrado);
                    RenderSystem.setShaderColor(rColor,gColor,bColor,1.0f);
                    pGuiGraphics.blit(colorCuadrado, anchoTexto, alturaTexto, 41, 0, 61, 14);
                    RenderSystem.disableBlend();

                    break;
                case "cabelloPagina":
                    rColor = sliderR.getValueInt() / 255.0F;
                    gColor = sliderG.getValueInt() / 255.0f;
                    bColor = sliderB.getValueInt() / 255.0f;
                    RenderSystem.enableBlend();
                    RenderSystem.setShaderTexture(0,colorCuadrado);
                    RenderSystem.setShaderColor(rColor,gColor,bColor,1.0f);
                    pGuiGraphics.blit(colorCuadrado, anchoTexto, alturaTexto, 41, 0, 61, 14);
                    RenderSystem.disableBlend();

                    break;
            }


        }else if(currentPage == 2){

        }else {

        }

        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0f);


        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

    }


    public static int calcularColor(int r, int g, int b){
        //Convertir el numero a decimal
        int colorDecimal = (r << 16) + (g << 8) + b;

        return colorDecimal;
    }
    
    public void botonNextBack(int posX, int posY){

        this.removeWidget(backButton);
        this.removeWidget(nextButton);

        if(currentPage == 0){
            this.nextButton = this.addRenderableWidget(new TextButton(this.width-85, posY, this.NEXT.withStyle(ChatFormatting.BOLD), button -> {
                currentPage = 1;
                this.removeWidget(nextButton);
                this.removeWidget(botonRazaLeft);
                this.removeWidget(botonRazaRight);
                sliders(this.width-127,((this.minecraft.getWindow().getGuiScaledHeight()) / 2)+25);
                botonesRazaColores(72,this.height/2);

                PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

                    switch(cap.getRace()){
                        case 0:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
                            break;
                        case 1:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
                            break;
                        case 2:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 2075172));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor2", 12263460));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16746150));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                            break;
                        case 3:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 1603072));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor2", 10478369));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor3", 16741888));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                            break;
                        case 4:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 15590399));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor2", 15255295));
                            ModMessages.sendToServer(new CharacterC2S("BodyColor3", 15240865));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                            break;
                        case 5:
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16753919));
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", 11796480));
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", 11796480));
                            ModMessages.sendToServer(new CharacterC2S("hairColor", 16753919));
                            break;
                        default:

                            break;
                    }


                });
                ModMessages.sendToServer(new CharacterC2S("Gender", 0));

            }));
        }else if(currentPage == 1){
            //BOTON VOLVER
            this.backButton = (TextButton) this.addRenderableWidget(new TextButton(20, posY,this.BACK.withStyle(ChatFormatting.BOLD),button -> {
                currentPage = 0;
                this.removeWidget(sliderR);
                this.removeWidget(sliderG);
                this.removeWidget(sliderB);
                this.removeWidget(eyesButtonColor);
                this.removeWidget(eyesButtonColor2);
                this.removeWidget(bodyButtonColor1);
                this.removeWidget(bodyButtonColor2);
                this.removeWidget(bodyButtonColor3);
                this.removeWidget(hairButtonColor);
                this.removeWidget(setColor);
                this.removeWidget(eyesTypeLeft);
                this.removeWidget(eyesTypeRight);
                this.removeWidget(bodyTypeRightButton);
                this.removeWidget(bodyTypeLeftButton);
                this.removeWidget(gendersRigthButton);
                this.removeWidget(gendersLeftButton);
            }));
            //BOTON SIGUIENTE
            this.nextButton = (TextButton) this.addRenderableWidget(new TextButton(this.width - 85, posY,this.NEXT.withStyle(ChatFormatting.BOLD),button -> {
            }));
        }else{

        }


    }

    public void botonesRazaColores(int posX, int posY){
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);

        this.removeWidget(eyesButtonColor);
        this.removeWidget(eyesButtonColor2);
        this.removeWidget(bodyButtonColor1);
        this.removeWidget(bodyButtonColor2);
        this.removeWidget(bodyButtonColor3);
        this.removeWidget(hairButtonColor);

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            switch (cap.getRace()){
                case 0:
                    //BOTON COLOR OJO 1
                    this.eyesButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor1",posX - 15, posY + 25,Component.empty(),button -> {
                        this.removeWidget(setColor);
                        int ojo1 = cap.getEye1Color();

                        float r = (ojo1 >> 16) / 255.0F;
                        float g = ((ojo1 >> 8) & 0xff) / 255.0f;
                        float b = (ojo1 & 0xff) / 255.0f;

                        colorR = (int) (r*255);
                        colorG = (int) (g*255);
                        colorB = (int) (b*255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY+43,Component.literal("SET"),wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye1Color", calcularColor(colorR,colorG,colorB)));

                        }));
                        this.partePagina = "ojo1";
                    }));
                    //BOTON COLOR OJO 2
                    this.eyesButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("eyeColor2",posX + 15, posY + 25,Component.empty(),button -> {
                        this.removeWidget(setColor);
                        int ojo2 = cap.getEye2Color();

                        float r = (ojo2 >> 16) / 255.0F;
                        float g = ((ojo2 >> 8) & 0xff) / 255.0f;
                        float b = (ojo2 & 0xff) / 255.0f;

                        colorR = (int) (r*255);
                        colorG = (int) (g*255);
                        colorB = (int) (b*255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY+43,Component.literal("SET"),wa -> {
                            ModMessages.sendToServer(new CharacterC2S("eye2Color", calcularColor(colorR,colorG,colorB)));

                        }));
                        this.partePagina = "ojo2";
                    }));
                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1",posX, posY - 25,Component.empty(),button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getBodyColor();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r*255);
                        colorG = (int) (g*255);
                        colorB = (int) (b*255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY+43,Component.literal("SET"),wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", calcularColor(colorR,colorG,colorB)));

                        }));
                        this.partePagina = "cuerpo1";
                    }));

                    this.hairButtonColor = (ColorButton) this.addRenderableWidget(new ColorButton("hairColor",posX, posY + 73,Component.empty(),button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getHairColor();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r*255);
                        colorG = (int) (g*255);
                        colorB = (int) (b*255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY+43,Component.literal("SET"),wa -> {
                            ModMessages.sendToServer(new CharacterC2S("hairColor", calcularColor(colorR,colorG,colorB)));

                        }));
                        this.partePagina = "cabelloPagina";
                    }));
                    break;
                case 1:
                    this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1",posX, posY - 6,Component.empty(),button -> {
                        this.removeWidget(setColor);
                        int cuerpo = cap.getBodyColor();

                        float r = (cuerpo >> 16) / 255.0F;
                        float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                        float b = (cuerpo & 0xff) / 255.0f;

                        colorR = (int) (r*255);
                        colorG = (int) (g*255);
                        colorB = (int) (b*255);

                        sliderR.setValue(colorR);
                        sliderG.setValue(colorG);
                        sliderB.setValue(colorB);

                        this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY+43,Component.literal("SET"),wa -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyColor1", calcularColor(colorR,colorG,colorB)));

                        }));
                        this.partePagina = "cuerpo1";
                    }));
                    break;
                case 2:
                    break;
                case 3:
            this.bodyButtonColor1 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor1",posX - 25, posY - 6,Component.empty(),button -> {
                this.removeWidget(setColor);
                int cuerpo = cap.getBodyColor();

                float r = (cuerpo >> 16) / 255.0F;
                float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                float b = (cuerpo & 0xff) / 255.0f;

                colorR = (int) (r*255);
                colorG = (int) (g*255);
                colorB = (int) (b*255);

                sliderR.setValue(colorR);
                sliderG.setValue(colorG);
                sliderB.setValue(colorB);

                this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY+43,Component.literal("SET"),wa -> {
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", calcularColor(colorR,colorG,colorB)));

                }));
                this.partePagina = "cuerpo1";
            }));

            this.bodyButtonColor2 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor2",posX, posY - 6,Component.empty(),button -> {
                this.removeWidget(setColor);
                int cuerpo = cap.getBodyColor2();

                float r = (cuerpo >> 16) / 255.0F;
                float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                float b = (cuerpo & 0xff) / 255.0f;

                colorR = (int) (r*255);
                colorG = (int) (g*255);
                colorB = (int) (b*255);

                sliderR.setValue(colorR);
                sliderG.setValue(colorG);
                sliderB.setValue(colorB);

                this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY+43,Component.literal("SET"),wa -> {
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", calcularColor(colorR,colorG,colorB)));

                }));
                this.partePagina = "cuerpo2";
            }));

            this.bodyButtonColor3 = (ColorButton) this.addRenderableWidget(new ColorButton("bodyColor3",posX + 25, posY - 6,Component.empty(),button -> {
                this.removeWidget(setColor);
                int cuerpo = cap.getBodyColor3();

                float r = (cuerpo >> 16) / 255.0F;
                float g = ((cuerpo >> 8) & 0xff) / 255.0f;
                float b = (cuerpo & 0xff) / 255.0f;

                colorR = (int) (r*255);
                colorG = (int) (g*255);
                colorB = (int) (b*255);

                sliderR.setValue(colorR);
                sliderG.setValue(colorG);
                sliderB.setValue(colorB);

                this.setColor = (TextButton) this.addRenderableWidget(new TextButton(this.width - 110, posY+43,Component.literal("SET"),wa -> {
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", calcularColor(colorR,colorG,colorB)));

                }));
                this.partePagina = "cuerpo3";
            }));


            break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;

            }

        });
        RenderSystem.disableBlend();
    }

    public void botonesOjos(int posX, int posY){

        this.removeWidget(eyesTypeRight);
        this.removeWidget(eyesTypeLeft);

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,minecraft.player).ifPresent(cap -> {

            switch(cap.getRace()){
                case 0:
                    if(cap.getBodytype() > 0){
                        if(cap.getEyesType() == 0){
                            this.eyesTypeRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 1));
                                this.removeWidget(eyesTypeRight);
                                this.removeWidget(eyesTypeLeft);
                            }));

                        } else if(cap.getEyesType() == 1){
                            this.eyesTypeLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                                ModMessages.sendToServer(new CharacterC2S("EyeType", 0));
                                this.removeWidget(eyesTypeRight);
                                this.removeWidget(eyesTypeLeft);
                            }));
                        }
                    }

                    break;
                case 1:
                    if(cap.getEyesType() == 0){

                        this.eyesTypeRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX - 95, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("EyeType", 1));
                            this.removeWidget(eyesTypeRight);
                            this.removeWidget(eyesTypeLeft);
                        }));

                    } else if(cap.getEyesType() == 1){
                        this.eyesTypeLeft = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 175, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("EyeType", 0));
                            this.removeWidget(eyesTypeRight);
                            this.removeWidget(eyesTypeLeft);
                        }));
                    }

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }

        });
    }
    public void botonesBodyType(int posX, int posY){

        this.removeWidget(bodyTypeRightButton);
        this.removeWidget(bodyTypeLeftButton);

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,minecraft.player).ifPresent(cap -> {

            switch(cap.getRace()){
                case 0:
                    if(cap.getBodytype() == 0){
                        this.bodyTypeRightButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 1));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    } else if(cap.getBodytype() == 1){
                        this.bodyTypeLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("BodyType", 0));
                            this.removeWidget(bodyTypeRightButton);
                            this.removeWidget(bodyTypeLeftButton);
                        }));
                    }
                    break;
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }


        });
    }
    public void botonesGeneros(int posX, int posY){

        this.removeWidget(gendersRigthButton);
        this.removeWidget(gendersLeftButton);

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,minecraft.player).ifPresent(cap -> {

            switch(cap.getRace()){
                case 0:
                    if(cap.getGender().equals("Female")){

                        this.gendersLeftButton = this.addRenderableWidget(new DMZRightButton("left", posX - 65, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("Gender", 0));
                            this.removeWidget(gendersRigthButton);
                            this.removeWidget(gendersLeftButton);
                        }));
                    } else if(cap.getGender().equals("Male")){
                        this.gendersRigthButton = this.addRenderableWidget(new DMZRightButton("right", posX, posY, Component.empty(), button -> {
                            ModMessages.sendToServer(new CharacterC2S("Gender", 1));
                            this.removeWidget(gendersRigthButton);
                            this.removeWidget(gendersLeftButton);
                        }));
                    }
                    break;
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }


        });
    }
    public void botonesRazasElegir(int posX, int posY){

        this.removeWidget(botonRazaRight);
        this.removeWidget(botonRazaLeft);

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

            if(cap.getRace() == 0){
                this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 1));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16765897));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 921617));
                }));
            }else if (cap.getRace() == 1){
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
            }else if (cap.getRace() == 2){
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

            }else if (cap.getRace() == 3){
                this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 4));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 15590399));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 15255295));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 15240865));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
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
            }else if (cap.getRace() == 4){

                this.botonRazaRight = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right", posX + 60, posY, Component.empty(), button -> {
                    ModMessages.sendToServer(new CharacterC2S("setRace", 5));
                    this.removeWidget(botonRazaRight);
                    this.removeWidget(botonRazaLeft);
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 16753919));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 11796480));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 11796480));
                    ModMessages.sendToServer(new CharacterC2S("hairColor", 16753919));
                }));
                this.botonRazaLeft =  (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", posX - 60, posY, Component.empty(), button -> {
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
                    ModMessages.sendToServer(new CharacterC2S("BodyColor1", 15590399));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor2", 15255295));
                    ModMessages.sendToServer(new CharacterC2S("BodyColor3", 15240865));
                    ModMessages.sendToServer(new CharacterC2S("eye1Color", 921617));
                    ModMessages.sendToServer(new CharacterC2S("eye2Color", 921617));
                }));
            }

        });
    }

    public void sliders(int sliderX, int posY){

        this.removeWidget(sliderR);
        this.removeWidget(sliderG);
        this.removeWidget(sliderB);

        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);

        sliderR = this.addRenderableWidget(new ForgeSlider(sliderX,posY-75,100,15,Component.literal("R:").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.RED),Component.empty(),0.0,255.0,colorR,true){
            @Override
            protected void applyValue() {
                super.applyValue();
                colorR = this.getValueInt();
            }
        });
        sliderG = this.addRenderableWidget(new ForgeSlider(sliderX,posY-55,100,15,Component.literal("G:").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GREEN),Component.empty(),0.0,255.0,colorB,true){
            @Override
            protected void applyValue() {
                super.applyValue();
                colorG = this.getValueInt();
            }
        });
        sliderB = this.addRenderableWidget(new ForgeSlider(sliderX,posY-35,100,15,Component.literal("B:").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.BLUE),Component.empty(),0.0,255.0,colorG,true){
            @Override
            protected void applyValue() {
                super.applyValue();
                colorB = this.getValueInt();
            }
        });
        RenderSystem.disableBlend();
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
        renderEntityInInv(guiGraphics, x, y, scale, quaternionf, quaternionf1, livingEntity);
        livingEntity.yBodyRot = f2;
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
}
