package com.yuseix.dragonminez.storyline;

import com.yuseix.dragonminez.storyline.missions.Quest;
import com.yuseix.dragonminez.storyline.sagas.Saga;
import com.yuseix.dragonminez.storyline.sagas.SaiyanSaga;

import java.util.Hashtable;

public class StorylineManager {
	private final Hashtable<String, Saga> sagas;

	public StorylineManager() {
		this.sagas = new Hashtable<>();
	}

	// Add a saga to the manager
	public void addSaga(Saga saga) {
		sagas.put(saga.getId(), saga);
	}

	// Retrieve a saga by ID
	public Saga getSaga(String id) {
		return sagas.get(id);
	}

	// Initialize predefined sagas
	public void initializeSagas() {
		addSaga(SaiyanSaga.createSaiyanSaga());
		// Add more sagas as needed
	}

	// Complete a quest in a saga
	public void completeQuest(String sagaId, String questId) {
		Saga saga = sagas.get(sagaId);
		if (saga != null) {
			for (Quest quest : saga.getQuests()) {
				if (quest.getId().equals(questId)) {
					quest.completeQuest();
					break;
				}
			}
		}
	}

	// Check if a saga is completed
	public boolean isSagaCompleted(String sagaId) {
		Saga saga = sagas.get(sagaId);
		return saga != null && saga.isCompleted();
	}

	// List all sagas
	public Hashtable<String, Saga> getAllSagas() {
		return sagas;
	}

}
