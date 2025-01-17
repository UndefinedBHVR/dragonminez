package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.registry.IDRegistry;
import com.yuseix.dragonminez.storyline.Quest;
import com.yuseix.dragonminez.storyline.Saga;
import com.yuseix.dragonminez.storyline.sagas.SaiyanSaga;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import java.util.Hashtable;

import static com.yuseix.dragonminez.registry.IDRegistry.sagaRegistry;

public class StorylineManager {
	private final Hashtable<String, Saga> sagas = new Hashtable<>();

	/**
	 * Constructor for the StorylineManager. Should never be called elsewhere than in PlayerStorylineProvider.
	 * Will automatically fire the initialization of all sagas for x player when they join.
	 * If you are messy enough you can probably add a "Storyline" to anything and have it work.
	 */
	public StorylineManager() {
		initializeSagas();
	}

	/**
	 * Gets a {@link Saga} by its ID.
	 *
	 * @param id The ID of the saga to retrieve.
	 * @return The saga with the provided ID, or null if it does not exist.
	 */
	public Saga getSaga(String id) {
		return sagas.get(id);
	}

	/**
	 * Resets the progress of all quests in all sagas.
	 * This will set all quests to be incomplete (but not delete them or the saga(s).)
	 */
	public void resetProgress() {
		for (Saga saga : sagas.values()) {
			for (Quest quest : saga.getQuests()) {
				quest.setCompleted(false);
			}
		}
	}

	/**
	 * Checks if a {@link Saga} is completed.
	 *
	 * @param sagaId The ID of the saga to check.
	 * @return {@code True} if the saga is completed, {@code False} otherwise.
	 */
	@SuppressWarnings("unused") // Temporary suppression until I do something w/ it lmao
	public boolean isSagaCompleted(String sagaId) {
		Saga saga = sagas.get(sagaId);
		return saga != null && saga.isCompleted();
	}

	/**
	 * Gets all sagas registered in the {@link StorylineManager}.
	 *
	 * @return A {@link Hashtable} containing all sagas.
	 */
	public Hashtable<String, Saga> getAllSagas() {
		return sagas;
	}

	// Initialize predefined sagas
	private void initializeSagas() {
		//Predefined Sagas by the mod
		Saga saiyanSaga = new SaiyanSaga();
		addSaga(saiyanSaga);

		//Register sagas from registry, no need for a throws exception as the registry should be checked before
		for (Saga saga : sagaRegistry.values()) {
			addSaga(saga);
		}
	}

	// Add a saga to the manager, local use only.
	private void addSaga(Saga saga) {
		//Adds saga to the manager
		sagas.put(saga.getId(), saga);
		//Adds saga to the registry if it's not already there
		if (!sagaRegistry.containsKey(saga.getId()))
			IDRegistry.registerSaga(saga);
	}

	//Must be public for the sake of the capability
	public CompoundTag saveNBTData(CompoundTag nbt) {
		CompoundTag sagasTag = new CompoundTag(); // Main container for all sagas

		for (Saga saga : sagas.values()) {
			CompoundTag sagaTag = new CompoundTag();
			sagaTag.putString("id", saga.getId());
			sagaTag.putBoolean("completed", saga.isCompleted());

			// Save quests in the saga
			ListTag questsTag = new ListTag(); // List for storing quests
			for (Quest quest : saga.getQuests()) {
				CompoundTag questTag = new CompoundTag();
				questTag.putString("id", quest.getId());
				questTag.putBoolean("completed", quest.isCompleted());
				questsTag.add(questTag);
			}
			sagaTag.put("quests", questsTag); // Add quests to saga
			sagasTag.put(saga.getId(), sagaTag); // Add saga to main sagas container
		}

		nbt.put("sagas", sagasTag); // Add all sagas to the provided NBT

		return nbt;
	}

	//Must be public for the sake of the capability
	public void loadNBTData(CompoundTag nbt) {
		if (!nbt.contains("sagas")) return; // Ensure sagas data exists

		CompoundTag sagasTag = nbt.getCompound("sagas");

		for (String sagaKey : sagasTag.getAllKeys()) {
			CompoundTag sagaTag = sagasTag.getCompound(sagaKey);
			String sagaId = sagaTag.getString("id");

			//Start all sagas for data loading
			initializeSagas();

			// Get the existing Saga by ID
			Saga saga = getSaga(sagaId);

			if (saga == null) {
				throw new IllegalArgumentException("Saga with ID '" + sagaId + "' does not exist!");
			}

			// Update quests
			if (sagaTag.contains("quests")) {
				ListTag questsTag = sagaTag.getList("quests", 10); // 10 = CompoundTag type
				for (int i = 0; i < questsTag.size(); i++) {
					CompoundTag questTag = questsTag.getCompound(i);
					String questId = questTag.getString("id");
					boolean questCompleted = questTag.getBoolean("completed");

					// Get the existing Quest by ID
					for (Quest quest : saga.getQuests()) {
						if (quest.getId().equals(questId)) {
							quest.setCompleted(questCompleted); // Update the completed state
							break;
						}
					}
				}
			}
		}
	}
}
