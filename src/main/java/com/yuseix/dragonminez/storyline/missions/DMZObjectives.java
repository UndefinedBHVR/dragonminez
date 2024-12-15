package com.yuseix.dragonminez.storyline.missions;

public abstract class DMZObjectives {

	private int currentAmount;
	private boolean completed;
	private Runnable onCompletion;

	public DMZObjectives(boolean completed) {

		this.completed = completed;
	}

	protected boolean isCompleted() {
		return this.completed; // Check completion status
	}


	protected void setCompleted() {
		this.completed = true; // Update completion status

		if (onCompletion != null) {
			onCompletion.run();
		}
	}

	protected void setOnCompletion(Runnable onCompletion) {
		this.onCompletion = onCompletion;
	}

	protected abstract void checkCompletion();
}
