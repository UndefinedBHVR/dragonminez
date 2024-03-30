package com.yuseix.dragonminec.events;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.client.gui.AttributesMenu;
import com.yuseix.dragonminec.client.hud.PlayerHudOverlay;
import com.yuseix.dragonminec.utils.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
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


    }
}
