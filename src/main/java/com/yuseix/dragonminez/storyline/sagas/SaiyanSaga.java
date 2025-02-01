package com.yuseix.dragonminez.storyline.sagas;

import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.storyline.Quest;
import com.yuseix.dragonminez.storyline.Saga;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveCollectItem;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveGetToLocation;

import java.util.List;

public class SaiyanSaga extends Saga {

	public SaiyanSaga() {
		super("saiyan_saga", "Saiyan Saga");
		addQuests();
		addPrerequisites();
	}

	@Override
	public void addQuests() {
		// Add quests to the saga
		Quest quest1 = new Quest(
				"arrival_of_raditz",
				"Arrival of Raditz",
				"Raditz has landed on Earth! Investigate his arrival and landing site.",
				List.of(new ObjectiveGetToLocation("raditz_landing_site")),
				List.of() // No prerequisites
		);

		Quest quest2 = new Quest(
				"the_defeat_of_raditz",
				"The Defeat of Raditz",
				"Defeat Raditz and save Gohan!",
				List.of(new ObjectiveCollectItem(MainItems.CAPSULA_MORADA.get(), 5)),
				List.of(quest1) // Prerequisite is the first quest
		);

		Quest quest3 = new Quest(
				"training_with_piccolo",
				"Training with Piccolo",
				"Prepare for the arrival of the Saiyans by training with Piccolo.",
				List.of(new ObjectiveCollectItem(MainItems.CAPSULA_MORADA.get(), 5)),
				List.of(quest2) // Prerequisite is the second quest
		);

		Quest quest4 = new Quest(
				"the_saiyan_invasion",
				"The Saiyan Invasion",
				"The Saiyans have arrived! Defend Earth from the Saiyan invasion.",
				List.of(new ObjectiveCollectItem(MainItems.CAPSULA_MORADA.get(), 5)),
				List.of(quest3) // Prerequisite is the third quest
		);

		Quest quest5 = new Quest(
				"battle_with_nappa",
				"Battle with Nappa",
				"Defeat Nappa and get one step closer to victory.",
				List.of(new ObjectiveCollectItem(MainItems.CAPSULA_MORADA.get(), 5)),
				List.of(quest4) // Prerequisite is the fourth quest
		);

		Quest quest6 = new Quest(
				"the_final_battle",
				"The Final Battle",
				"Defeat Vegeta and save Earth from the Saiyan threat.",
				List.of(new ObjectiveCollectItem(MainItems.CAPSULA_MORADA.get(), 5)),
				List.of(quest5) // Prerequisite is the fifth quest
		);

		this.addQuest(quest1);
		this.addQuest(quest2);
		this.addQuest(quest3);
		this.addQuest(quest4);
		this.addQuest(quest5);
		this.addQuest(quest6);
	}

	@Override
	public void addPrerequisites() {
		// No prerequisites for the Saiyan Saga, as it is the first saga
	}
}
