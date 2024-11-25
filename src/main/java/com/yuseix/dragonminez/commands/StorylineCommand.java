package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.yuseix.dragonminez.storyline.missions.Quest;
import com.yuseix.dragonminez.storyline.player.PlayerStorylineProvider;
import com.yuseix.dragonminez.storyline.sagas.Saga;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.util.concurrent.atomic.AtomicInteger;

public class StorylineCommand {

	public StorylineCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("storyline")
				// /storyline set quest <id> <true|false>
				.then(Commands.literal("set")
						.then(Commands.literal("quest")
								.then(Commands.argument("id", StringArgumentType.string())
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
		// Use an AtomicInteger to store the result inside the lambda
		AtomicInteger result = new AtomicInteger(0);

		source.getPlayer().getCapability(PlayerStorylineProvider.CAPABILITY).ifPresent(playerStoryline -> {
			//Automáticamente busca todas las sagas para encontrar la quest (no eficiente?)
			for (Saga saga : playerStoryline.getAllSagas().values()) {
				Quest quest = saga.getQuestbyId(questId);

				if (quest != null) {
					if (completed) {
						quest.completeQuest();
						source.sendSuccess(() -> Component.literal("Quest '" + questId + "' marked as completed."), true);
					} else {
						quest.setCompleted(false);
						source.sendSuccess(() -> Component.literal("Quest '" + questId + "' marked as incomplete."), true);
					}
					result.set(1); // Mark success
					return;
				}
			}
			source.sendFailure(Component.literal("Quest with ID '" + questId + "' not found."));
			result.set(0); // Mark failure
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


}
