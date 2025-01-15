package com.yuseix.dragonminez.storyline.missions;

import java.util.ArrayList;
import java.util.List;

public class RegisterAdvancementCriterion {

	protected static final List<String> criterionList = new ArrayList<>();

	public RegisterAdvancementCriterion() {
		// Register criteria
		register("location");
		register("biome");
	}


	public static void register(String criterion) {
		if (!criterionList.contains(criterion)) {
			criterionList.add(criterion);
		} else {
			throw new IllegalArgumentException("The advancement criterion [" + criterion + "] was already registered.");
		}
	}

}