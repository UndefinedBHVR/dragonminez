package com.yuseix.dragonminec.events;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.character.LayerDMZBase;
import com.yuseix.dragonminec.client.gui.AttributesMenu;
import com.yuseix.dragonminec.client.hud.PlayerHudOverlay;
import com.yuseix.dragonminec.utils.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = DragonMineC.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {

            if (KeyBinding.STATS_MENU_KEY.consumeClick()) {
                Minecraft.getInstance().setScreen(new AttributesMenu(
                        Component.translatable("menu.title.dragonminec.statsmenu")));
            }

        }

        @SubscribeEvent
        public static void RenderHealthBar(RenderGuiOverlayEvent.Pre event) {
            if (VanillaGuiOverlay.PLAYER_HEALTH.type() == event.getOverlay()) {
                event.setCanceled(true);
            }
        }

    }


    @Mod.EventBusSubscriber(modid = DragonMineC.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent e) {
            //Tecla para abrir menu
            e.register(KeyBinding.STATS_MENU_KEY);

        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent e) {
            e.registerAboveAll("playerhud", PlayerHudOverlay.HUD_PLAYER);
        }

        @SubscribeEvent
        public static void registerModelLayers(EntityRenderersEvent.AddLayers e) {

        }

    }
}
