// Registry class to manage IDs
package com.yuseix.dragonminez.registry;

import com.yuseix.dragonminez.storyline.Quest;
import com.yuseix.dragonminez.storyline.Saga;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;


/**
 * {@code Storyline API} Registry class to manage IDs.
 * <p>
 * This class is responsible for managing the registration of IDs for Sagas and Quests within the Storyline API.
 * Note: This API is still under development and not yet complete. If any addon developers encounter errors related to this class,
 * please be aware that these issues will be addressed in a future version.
 *
 * @apiNote Both {@link Saga} and {@link Quest} classes have methods to register their respective IDs automatically.
 * Thus, it is not necessary to manually register IDs for Sagas/Quests.
 */
public class IDRegistry {
	private static final Set<String> sagaIds = new HashSet<>();
	private static final Set<String> questIds = new HashSet<>();
	public static final Hashtable<String, Saga> sagaRegistry = new Hashtable<>();

	public static void registerSagaId(String id) {
		if (!sagaIds.add(id)) {
			throw new IllegalArgumentException("Saga ID '" + id + "' is already registered.");
		}
	}

	public static void registerQuestId(String id) {
		if (!questIds.add(id)) {
			throw new IllegalArgumentException("Quest ID '" + id + "' is already registered.");
		}
	}

	public static void clearAllIds() {
		sagaIds.clear();
		questIds.clear();
	}

	/**
	 * Registers a custom {@link Saga} to the {@link IDRegistry#sagaRegistry}.
	 * This method should be called during mod initialization.
	 * <p>
	 * Usage only for addons and mods that want to add their own sagas.
	 *
	 * @param saga The Saga to register
	 */
	public static void registerSaga(Saga saga) {
		sagaRegistry.put(saga.getId(), saga);
	}

}