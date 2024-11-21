package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.network.C2S.MenuC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.utils.Keys;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//Anteriormente llamado ModListener o ClientEvents
//ACTUALMENTE LOS ModBusEvents son eventos que se ejecutan en el bus IModBusEvent
//Si un evento tiene "class x implements IMobBusEvent" TIENE que estar acá.
//Adicionalmente, estos eventos son aquellos que son lanzados al inicio del juego.


@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientEvents {

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
