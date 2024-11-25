package com.yuseix.dragonminez.storyline.missions;

import java.util.ArrayList;
import java.util.List;

public class Quest {
	private final String id;
	private final String name;
	private final String description;
	private final List<String> objectives;
	private boolean completed;

	public Quest(String id, String name, String description, List<String> objectives) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.objectives = new ArrayList<>(objectives);
		this.completed = false;
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

	public List<String> getObjectives() {
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
	}

}


