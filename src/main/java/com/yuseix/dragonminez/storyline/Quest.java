package com.yuseix.dragonminez.storyline;

import com.yuseix.dragonminez.registry.IDRegistry;
import com.yuseix.dragonminez.utils.DebugUtils;

import java.util.ArrayList;
import java.util.List;

public class Quest {
	private final String id;
	private final String name;
	private final String description;
	private final List<Objective> objectives;
	private boolean completed;

	public Quest(String id, String name, String description, List<Objective> objectives) {
		IDRegistry.registerQuestId(id);
		this.id = id;
		this.name = name;
		this.description = description;
		this.objectives = new ArrayList<>(objectives);
		this.completed = false;

		for (Objective objective : objectives) {
			objective.setOnCompletion(this::checkQuestCompletion);
		}

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Objective> getObjectives() {
		return objectives;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void completeQuest() {
		this.completed = true;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;

		if (completed) {
			DebugUtils.dmzLog("Quest '" + name + "' is now completed!");
			// Trigger any additional logic for quest completion here
		}
	}

	private void checkQuestCompletion() {
		boolean allCompleted = objectives.stream().allMatch(Objective::isCompleted);
		if (allCompleted) {
			setCompleted(true);
		}
	}

}


