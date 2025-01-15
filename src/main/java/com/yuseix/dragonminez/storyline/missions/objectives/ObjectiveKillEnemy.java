package com.yuseix.dragonminez.storyline.missions.objectives;

import com.yuseix.dragonminez.storyline.missions.Objective;
import net.minecraft.world.entity.Entity;

public class ObjectiveKillEnemy extends Objective {
	private final Entity enemyName;
	private final int requiredAmount;
	private int currentAmount;

	public ObjectiveKillEnemy(Entity enemyName, int requiredAmount) {

		super(false);

		this.enemyName = enemyName;
		this.requiredAmount = requiredAmount;
		this.currentAmount = 0;
	}

	public void onEnemyKilled(Entity killedEnemy) {

		if (killedEnemy.equals(enemyName)) {
			currentAmount++;
			checkCompletion();
		}
	}

	@Override
	public void checkCompletion() {
		if (currentAmount >= requiredAmount) {
			setCompleted(); // Mark the objective as complete
		}
	}
}
