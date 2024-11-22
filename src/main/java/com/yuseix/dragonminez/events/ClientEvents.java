package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainParticles;
import com.yuseix.dragonminez.worldgen.biome.ModBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
	private static final Random RANDOM = new Random();
	private static final String title = "DragonMine Z - Release v1.1.0";

	@SubscribeEvent
	public static void onRenderTick(TickEvent.RenderTickEvent event) {
		if (event.phase == TickEvent.RenderTickEvent.Phase.END) {
			Minecraft minecraft = Minecraft.getInstance();
			if (minecraft.getWindow() != null) {
				// Cambia el título de la ventana
				minecraft.getWindow().setTitle(title);
			}
		}
	}

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		Minecraft mc = Minecraft.getInstance();
		Level level = mc.level;

		if (level == null || mc.player == null) {
			return;
		}

		// Frecuencia de generación (una vez cada 10 ticks)
		if (level.getGameTime() % 10 != 0) {
			return;
		}

		// Obtener posición y bioma
		BlockPos playerPos = mc.player.blockPosition();
		ResourceKey<Biome> currentBiomeKey = level.registryAccess()
				.registryOrThrow(Registries.BIOME)
				.getResourceKey(level.getBiome(playerPos).value())
				.orElse(null);

		if (currentBiomeKey == null) return;

		// Genera partículas dependiendo del bioma
		if (currentBiomeKey.equals(ModBiomes.AJISSA_PLAINS)) {
			spawnParticles(level, MainParticles.AJISSA_LEAVES_PARTICLE.get(), playerPos);
		} else if (currentBiomeKey.equals(ModBiomes.SACRED_LAND)) {
			spawnParticles(level, MainParticles.SACRED_LEAVES_PARTICLE.get(), playerPos);
		}
	}

	private static void spawnParticles(Level level, SimpleParticleType particleType, BlockPos playerPos) {
		// Generar partículas alrededor del jugador
		int cantParticulas = 6;
		for (int i = 0; i < cantParticulas; i++) { // Cantidad
			double x = playerPos.getX() + RANDOM.nextDouble() * 16 - 8; // Rango: -8 a +8 bloques
			double y = playerPos.getY() + RANDOM.nextDouble() * 6;      // Rango: hasta 6 bloques arriba
			double z = playerPos.getZ() + RANDOM.nextDouble() * 16 - 8; // Rango: -8 a +8 bloques

			double xSpeed = (RANDOM.nextDouble() - 0.5) * 0.02; // Velocidad lateral mínima
			double ySpeed = RANDOM.nextDouble() * 0.01;         // Velocidad vertical lenta
			double zSpeed = (RANDOM.nextDouble() - 0.5) * 0.02; // Velocidad lateral mínima

			level.addParticle(particleType, x, y, z, xSpeed, ySpeed, zSpeed);
		}
	}
}
