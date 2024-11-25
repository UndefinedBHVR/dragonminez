package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.utils.DebugUtils;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StorylineEvents {

	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		DebugUtils.dmzLog("StorylineManager initialized");
	}
	
}
