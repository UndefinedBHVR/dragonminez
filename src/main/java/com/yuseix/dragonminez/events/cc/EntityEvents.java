package com.yuseix.dragonminez.events.cc;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.init.MainFluids;
import com.yuseix.dragonminez.init.armor.DbzArmorItem;
import com.yuseix.dragonminez.init.armor.PiccoloArmorItem;
import com.yuseix.dragonminez.init.armor.SaiyanArmorItem;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekianEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.SoldierEntity;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class EntityEvents {

	@SubscribeEvent
	public static void mobDeath(LivingDeathEvent event) {
		// Verificar si la entidad muerta es un aldeano, namekianentity,etc
		if (event.getEntity() instanceof NamekianEntity ||
				event.getEntity() instanceof Villager ||
				event.getEntity() instanceof Player) {

			// Verificar si el atacante es un jugador
			if (event.getSource().getEntity() instanceof Player) {
				Player player = (Player) event.getSource().getEntity();


				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
					cap.removeDmzAlignment(5); //Remover puntos te hace maligno
				});

				player.displayClientMessage(Component.translatable("lines.alignment.evil"), true);

			}
		}
		//Aca es para ganar puntos de bondad xxx claro pe tilin
		if (event.getEntity() instanceof SoldierEntity || event.getEntity() instanceof Monster) {

			if (event.getSource().getEntity() instanceof Player) {
				Player player = (Player) event.getSource().getEntity();

				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
					cap.addDmzAlignment(2); //Agregar puntos te hace bueno
				});

			}
		}

		//Pa ganar tps cuando mates algo claro pes papeto
		if (event.getEntity() instanceof Monster || event.getEntity() instanceof Animal || event.getEntity() instanceof Player
				|| event.getEntity() instanceof NamekianEntity) {
			if (event.getSource().getEntity() instanceof Player) {
				Player player = (Player) event.getSource().getEntity();
				var vidaTps = (int) (event.getEntity().getMaxHealth() * 0.5); // 50% hp enemigo
				var calculoTps = (int) Math.round((10 + vidaTps) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_GAIN.get()); // (10 + 50% hp) * config

				// multiplicar si está en la hab del tiempo pes
				if (player.level().dimension().equals(ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY)) {
					calculoTps *= DMZGeneralConfig.MULTIPLIER_ZPOINTS_HTC.get();
				}

				int finalTps = (int) Math.round(calculoTps);

				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
					cap.addZpoints(finalTps);
					// Testing
              /* if (player.level().dimension().equals(ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY)) {
                    player.sendSystemMessage(Component.literal("TPS: " + finalTps + " (HTC)")); }
                    else {player.sendSystemMessage(Component.literal("TPS: " + finalTps)); } */
				});
			}
		}

		// Eliminar la marca majin
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
				cap.removePermanentEffect("majin");
			});
		}
	}

	@SubscribeEvent
	public static void onEntityHit(LivingHurtEvent event) {

		if (event.getSource().getEntity() instanceof Player) {
			Player player = (Player) event.getSource().getEntity();

			double baseTps = DMZGeneralConfig.PERHIT_ZPOINTS_GAIN.get() * DMZGeneralConfig.MULTIPLIER_ZPOINTS_GAIN.get();

			// multiplicar si está en la hab del tiempo pes
			if (player.level().dimension().equals(ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY)) {
				baseTps *= DMZGeneralConfig.MULTIPLIER_ZPOINTS_HTC.get();
			}

			int finalTps = (int) Math.round(baseTps);

			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
				cap.addZpoints(finalTps);

				// Testing
              /* if (player.level().dimension().equals(ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY)) {
                    player.sendSystemMessage(Component.literal("TPS: " + finalTps + " (HTC)")); }
                    else {player.sendSystemMessage(Component.literal("TPS: " + finalTps)); } */
			});
		}

		// Reducir durabilidad armadura de 1 en 1 xd
		LivingEntity entity = event.getEntity();

		// No verifico que sea un jugador para que funcione en zombies, npcs, etc que utilice armaduras y no se les haga instabreak

		for (EquipmentSlot slot : EquipmentSlot.values()) {
			if (slot.getType() == EquipmentSlot.Type.ARMOR) continue;

			ItemStack armorStack = entity.getItemBySlot(slot);

			if (armorStack.getItem() instanceof ArmorItem) {
				int damageToArmor = 1;
				armorStack.hurtAndBreak(damageToArmor, entity, (e) -> e.broadcastBreakEvent(slot));
			}
		}
	}


	private static final double HEAL_PERCENTAGE = 0.05; // 5% por segundo
	private static final int HEAL_TICKS = 3 * 20; // 3 segundos

	private static final java.util.Map<Player, Long> lastHealingTime = new java.util.WeakHashMap<>();

	@SubscribeEvent
	public static void onLivingTick(TickEvent.PlayerTickEvent event) {
		Player player = event.player;

		if (player.level().isClientSide || event.phase != TickEvent.Phase.END) {
			return;
		}

		FluidState fluidState = player.level().getFluidState(player.blockPosition());

		if (fluidState.isEmpty()) {
			return;
		}

		if (fluidState.is(MainFluids.SOURCE_HEALING.get())) {
			long currentTime = player.level().getGameTime(); // Tiempo actual en ticks
			long lastHealTime = lastHealingTime.getOrDefault(player, 0L);

			// Solo aplicar curación ha pasado el tiempo
			if (currentTime - lastHealTime >= HEAL_TICKS) {
				funcLiqCurativo(player);
				lastHealingTime.put(player, currentTime); // Actualizar el último tiempo de curación
			}
		} else if (fluidState.is(MainFluids.SOURCE_NAMEK.get())) {
			funcAguaNamek(player);
		}
	}

	private static void funcLiqCurativo(Player player) {
		if (player instanceof ServerPlayer serverPlayer) {
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, serverPlayer).ifPresent(stats -> {
				int maxHp = stats.getMaxHealth();
				int healHp = (int) (maxHp * HEAL_PERCENTAGE);
				int maxKi = stats.getMaxEnergy();
				int healKi = (int) (maxKi * HEAL_PERCENTAGE);

				serverPlayer.heal(healHp);
				stats.addCurEnergy(healKi);
			});
		}

		// Apagar el fuego
		if (player.isOnFire()) {
			player.clearFire();
		}
	}

	private static void funcAguaNamek(Player player) {
		if (player.isOnFire()) {
			player.clearFire();
		}
	}
}




