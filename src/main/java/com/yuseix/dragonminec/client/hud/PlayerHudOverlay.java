package com.yuseix.dragonminec.client.hud;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.events.ModEvents;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import javax.annotation.Nullable;

public class PlayerHudOverlay {

    private static final ResourceLocation hud = new ResourceLocation(DragonMineC.MODID,
            "textures/gui/hud/hud.png");

    public static final IGuiOverlay HUD_PLAYER = (forgeGui, guiGraphics, v, i, i1) -> {
        assert Minecraft.getInstance().player != null;
        int VidaMaxima = (int) Minecraft.getInstance().player.getMaxHealth();
        int vidarestante = (int) Minecraft.getInstance().player.getHealth();

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,Minecraft.getInstance().player).ifPresent(playerstats -> {

            int vidawa = ((163 * vidarestante) / VidaMaxima);
            int vida = Math.min(vidawa,163);

            int StaminaMax = playerstats.getStamina() + 3;
            int curStamina = playerstats.getCurStam();

            int staminatotal = Math.min( ( (83 * curStamina) / StaminaMax), 83);

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
            RenderSystem.setShaderTexture(0, hud);

            guiGraphics.pose().pushPose();
            guiGraphics.pose().scale(1.2f,1.2f,1.0f);
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
                    119,
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

            guiGraphics.drawString(Minecraft.getInstance().font,String.valueOf(Minecraft.getInstance().player.getHealth()), 150,20,0xBB1C2A);

        });




        double scaleFactor = Minecraft.getInstance().getWindow().getGuiScale();


        RenderSystem.enableScissor((int) ((5)*scaleFactor),
                (int) (Minecraft.getInstance().getWindow().getHeight() - (20* 2)*scaleFactor),
                (int)((25* 2)*scaleFactor),
                (int)((23* 2)*scaleFactor));

        renderEntityInInventoryFollowsAngle(guiGraphics, 30, 125, 65, 35.5f, 0, Minecraft.getInstance().player);

        RenderSystem.disableScissor();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0, hud);

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(1.2f,1.2f,2.0f);
        //Z vacia
        guiGraphics.blit(hud,
                5,
                27,
                0,
                31,
                21,
                17);

        //Z LLENA
        guiGraphics.blit(hud,
                7,
                29,
                21,
                33,
                16,
                14);
        guiGraphics.pose().popPose();

    };



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
