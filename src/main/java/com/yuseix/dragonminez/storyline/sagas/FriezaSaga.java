package com.yuseix.dragonminez.storyline.sagas;

import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.registry.IDRegistry;
import com.yuseix.dragonminez.storyline.Quest;
import com.yuseix.dragonminez.storyline.Saga;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveCollectItem;
import com.yuseix.dragonminez.storyline.objectives.ObjectiveGetToLocation;

import java.util.List;

public class FriezaSaga extends Saga {

	public FriezaSaga() {
		super("frieza_saga", "Frieza Saga");
		addQuests();
		addPrerequisites();
	}

	@Override
	public void addQuests() {
		// Add quests to the saga
		Quest quest1 = new Quest(
				"frieza1",
				"Arrival of Raditz",
				"Raditz has landed on Earth! Investigate his arrival and landing site.",
				List.of(new ObjectiveGetToLocation("raditz_landing_site")),
				List.of() // No prerequisites
		);

		Quest quest2 = new Quest(
				"frieza2",
				"The Defeat of Raditz",
				"Defeat Raditz and save Gohan!",
				List.of(new ObjectiveCollectItem(MainItems.CAPSULA_MORADA.get(), 5)),
				List.of(quest1) // Prerequisite is the first quest
		);

		Quest quest3 = new Quest(
				"frieza3",
				"Training with Piccolo",
				"Prepare for the arrival of the Saiyans by training with Piccolo.",
				List.of(new ObjectiveCollectItem(MainItems.CAPSULA_MORADA.get(), 5)),
				List.of(quest2) // Prerequisite is the second quest
		);

		Quest quest4 = new Quest(
				"frieza4",
				"The Saiyan Invasion",
				"The Saiyans have arrived! Defend Earth from the Saiyan invasion.",
				List.of(new ObjectiveCollectItem(MainItems.CAPSULA_MORADA.get(), 5)),
				List.of(quest3) // Prerequisite is the third quest
		);

		Quest quest5 = new Quest(
				"frieza5",
				"Battle with Nappa",
				"Defeat Nappa and get one step closer to victory.",
				List.of(new ObjectiveCollectItem(MainItems.CAPSULA_MORADA.get(), 5)),
				List.of(quest4) // Prerequisite is the fourth quest
		);

		Quest quest6 = new Quest(
				"frieza6",
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
		addPrequisite(IDRegistry.sagaRegistry.get("saiyan_saga"));
	}
}
