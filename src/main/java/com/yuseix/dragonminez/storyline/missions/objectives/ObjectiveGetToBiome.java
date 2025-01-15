package com.yuseix.dragonminez.storyline.missions.objectives;

import com.yuseix.dragonminez.storyline.missions.Objective;

public class ObjectiveGetToBiome extends Objective {
	private final String biome;
	private boolean reached;

	public ObjectiveGetToBiome(String biomeName) {

		super(false);

		this.biome = biomeName;
		this.reached = false;
	}

	public void onPlayerEnterBiome(String enteredBiome) {

		if (enteredBiome.equals(biome)) {
			reached = true;
			checkCompletion();
		}
	}

	@Override
	public void checkCompletion() {
		if (reached) {
			setCompleted(); // Mark the objective as complete
		}
	}
}
