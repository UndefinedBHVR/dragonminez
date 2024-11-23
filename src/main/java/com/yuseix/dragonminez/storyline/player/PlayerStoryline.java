package com.yuseix.dragonminez.storyline.player;

import com.yuseix.dragonminez.storyline.StorylineManager;
import net.minecraft.nbt.CompoundTag;

public class PlayerStoryline implements IPlayerStoryline {
	private StorylineManager storylineManager;

	public PlayerStoryline() {
		this.storylineManager = new StorylineManager();
	}

	@Override
	public StorylineManager getStorylineManager() {
		return storylineManager;
	}

	@Override
	public void setStorylineManager(StorylineManager manager) {
		this.storylineManager = manager;
	}

	@Override
	public CompoundTag serializeNBT() {
		// Serialize storyline data (customize as needed)
		CompoundTag tag = new CompoundTag();
		// Example serialization logic here
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		// Deserialize storyline data
		// Example deserialization logic here
	}
}
