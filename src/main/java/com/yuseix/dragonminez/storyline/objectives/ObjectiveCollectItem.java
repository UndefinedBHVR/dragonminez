package com.yuseix.dragonminez.storyline.objectives;

import com.yuseix.dragonminez.storyline.Objective;
import net.minecraft.world.item.Item;


public class ObjectiveCollectItem extends Objective {
	private final Item itemId;
	private final int requiredAmount;
	private int currentAmount;

	public ObjectiveCollectItem(Item itemId, int requiredAmount) {

		super(false,
				"collect_item",
				"Collect " + requiredAmount + " " + itemId.getDefaultInstance().getDescriptionId());

		this.itemId = itemId;
		this.requiredAmount = requiredAmount;
		this.currentAmount = 0;
	}

	public void onItemCollected(Item collectedItem) {

		if (collectedItem.equals(itemId)) {
			currentAmount++;
			checkCompletion();
		}
	}


	public int getCurrentAmount() {
		return currentAmount;
	}

	public int getRequiredAmount() {
		return requiredAmount;
	}

	@Override
	public void checkCompletion() {
		if (currentAmount >= requiredAmount) {
			setCompleted(); // Mark the objective as complete
		}
	}
}


