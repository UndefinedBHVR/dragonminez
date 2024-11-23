package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.storyline.StorylineManager;
import com.yuseix.dragonminez.utils.DebugUtils;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StorylineEvents {

	private static StorylineManager storylineManager;

	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		storylineManager = new StorylineManager();
		storylineManager.initializeSagas();
		DebugUtils.dmzLog("StorylineManager initialized");
	}

	@SubscribeEvent
	public void onServerStopping(ServerStoppingEvent event) {
		storylineManager = null;
		DebugUtils.dmzLog("StorylineManager destroyed");
	}

	public static StorylineManager getStorylineManager() {
		return storylineManager;
	}
}
