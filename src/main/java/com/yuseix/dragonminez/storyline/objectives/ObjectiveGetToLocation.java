package com.yuseix.dragonminez.storyline.objectives;

import com.yuseix.dragonminez.storyline.Objective;

public class ObjectiveGetToLocation extends Objective {
	private final String loc;
	private boolean reached;

	public ObjectiveGetToLocation(String loc) {

		super(false,
				"get_to_location",
				"Get to the " + loc + " location");

		this.loc = loc;
		this.reached = false;
	}

	public void onReachingLoc(String loc) {

		if (loc.equals(this.loc)) {
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
