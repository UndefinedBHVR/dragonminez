package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.network.C2S.MenuC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.utils.Keys;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents {
	@SubscribeEvent
	public static void onKeyInput(InputEvent.Key event) {
		if (Keys.STATS_MENU.consumeClick()) {
			ModMessages.sendToServer(new MenuC2S());
		}
	}

	@SubscribeEvent
	public static void RenderHealthBar(RenderGuiOverlayEvent.Pre event) {
		if (VanillaGuiOverlay.PLAYER_HEALTH.type() == event.getOverlay()) {
			event.setCanceled(true);
		}
	}
}
