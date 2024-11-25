package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.yuseix.dragonminez.storyline.StorylineManager;
import com.yuseix.dragonminez.storyline.missions.Quest;
import com.yuseix.dragonminez.storyline.player.PlayerStorylineProvider;
import com.yuseix.dragonminez.storyline.sagas.Saga;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class StorylineCommand {

	private final StorylineManager storylineManager = new StorylineManager();

	public StorylineCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("storyline")
				// /storyline set quest <id> <true|false>
				.then(Commands.literal("set")
						.then(Commands.literal("quest")
								.then(Commands.argument("id", StringArgumentType.string())
										.suggests(this::suggestQuestIds)
										.then(Commands.argument("completed", BoolArgumentType.bool())
												.executes(context -> {
													String questId = StringArgumentType.getString(context, "id");
													boolean completed = BoolArgumentType.getBool(context, "completed");

													return setQuestCompletion(context.getSource(), questId, completed);
												})
										)
								)
						)
				)

				// /storyline get saga <id>
				.then(Commands.literal("get")
						.then(Commands.literal("saga")
								.then(Commands.argument("id", StringArgumentType.string())
										.executes(context -> {
											String sagaId = StringArgumentType.getString(context, "id");
											return getSagaInfo(context.getSource(), sagaId);
										})
								)
						)
				)
		);
	}

	private int setQuestCompletion(CommandSourceStack source, String questId, boolean completed) {
		AtomicInteger result = new AtomicInteger(0);

		// Access the capability of the player
		source.getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			// Iterate through all sagas to find the quest
			for (Saga saga : playerStoryline.getAllSagas().values()) {
				Quest quest = saga.getQuestbyId(questId);


				if (!storylineManager.isQuestActive(questId)) {
					source.sendFailure(Component.literal("Quest '" + questId + "' is not active for player '" + source.getPlayer().getDisplayName().getString() + "'."));
					result.set(0);
					return;
				}

				if (quest != null) {

					// Set completion status
					if (completed) {
						quest.completeQuest();
						source.sendSuccess(() -> Component.literal("Quest '" + questId + "' marked as completed."), true);
					} else {
						quest.setCompleted(false);
						source.sendSuccess(() -> Component.literal("Quest '" + questId + "' marked as incomplete."), true);
					}

					result.set(1); // Indicate success
					return;
				}
			}

			// If no quest was found
			source.sendFailure(Component.literal("Quest with ID '" + questId + "' not found."));
			result.set(0); // Indicate failure
		});

		return result.get(); // Return the result
	}


	private int getSagaInfo(CommandSourceStack source, String sagaId) {

		AtomicInteger result = new AtomicInteger(0);

		source.getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			Saga saga = playerStoryline.getSaga(sagaId);

			if (saga == null) {
				source.sendFailure(Component.literal("Saga with ID '" + sagaId + "' not found."));
				result.set(0); // Set the result to 0 if the saga is not found
				return;
			}

			source.sendSuccess(() -> Component.literal("Saga: " + saga.getName() + " [" + sagaId + "]"), false);

			for (Quest quest : saga.getQuests()) {
				String status = quest.isCompleted() ? "COMPLETED" : "INCOMPLETE";
				source.sendSuccess(() -> Component.literal("- " + quest.getId() + ": " + quest.getDescription() + " [" + status + "]"), false);
			}

			result.set(1); // Set the result to 1 if everything is successful
		});

		return result.get(); // Return the stored result
	}

	// Provide suggestions for quest IDs
	private CompletableFuture<Suggestions> suggestQuestIds(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
		//El nuevo StorylineManager no se usa como tal, pero se puede usar para obtener todas las sagas y quests porque al inicializarlo se inicializan todas las sagas
		//Estas sagas, a su vez, tienen todas las quests que se pueden completar
		for (Saga saga : storylineManager.getAllSagas().values()) {
			for (Quest quest : saga.getQuests()) {
				builder.suggest(quest.getId());
			}
		}
		return builder.buildFuture();
	}
}
