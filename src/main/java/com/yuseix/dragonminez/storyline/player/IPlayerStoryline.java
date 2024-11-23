package com.yuseix.dragonminez.storyline.player;

import com.yuseix.dragonminez.storyline.StorylineManager;
import net.minecraft.nbt.CompoundTag;

public interface IPlayerStoryline {
	StorylineManager getStorylineManager();

	void setStorylineManager(StorylineManager manager);

	CompoundTag serializeNBT(); // Save data

	void deserializeNBT(CompoundTag nbt); // Load data
}
