package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.CharacterCMenu;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.blocks.entity.MainBlockEntities;
import com.yuseix.dragonminez.init.blocks.entity.client.*;
import com.yuseix.dragonminez.init.entity.client.renderer.DinoRenderer;
import com.yuseix.dragonminez.init.entity.client.renderer.FakeBioAndroidRenderer;
import com.yuseix.dragonminez.utils.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

        if (KeyBinding.STATS_MENU_KEY.consumeClick()) {
            Minecraft.getInstance().setScreen(new CharacterCMenu(
                    Component.translatable("menu.title.dragonminez.statsmenu")));
        }
    }
    @SubscribeEvent
    public static void RenderHealthBar(RenderGuiOverlayEvent.Pre event) {
        if (VanillaGuiOverlay.PLAYER_HEALTH.type() == event.getOverlay()) {
            event.setCanceled(true);
        }
    }


    @Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent e) {

            //Tecla para abrir menu de stats
            e.register(KeyBinding.STATS_MENU_KEY);
        }
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent e) {
            //e.registerAboveAll("playerhud", PlayerHudOverlay.HUD_PLAYER);
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            //ENTIDADES
            EntityRenderers.register(MainEntity.DINO1.get(), DinoRenderer::new);
            EntityRenderers.register(MainEntity.FAKEBIOANDROID1.get(), FakeBioAndroidRenderer::new);

            //BLOQUES
            BlockEntityRenderers.register(MainBlockEntities.DBALL1_BLOCK_ENTITY.get(), Dball1BlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL2_BLOCK_ENTITY.get(), Dball2BlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL3_BLOCK_ENTITY.get(), Dball3BlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL4_BLOCK_ENTITY.get(), Dball4BlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL5_BLOCK_ENTITY.get(), Dball5BlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL6_BLOCK_ENTITY.get(), Dball6BlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL7_BLOCK_ENTITY.get(), Dball7BlockRenderer::new);

        }

        @SubscribeEvent
        public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {

        }
    }
}
