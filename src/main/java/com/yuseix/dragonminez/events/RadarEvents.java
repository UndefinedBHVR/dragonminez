package com.yuseix.dragonminez.events;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.items.custom.DragonBallRadarItem;
import com.yuseix.dragonminez.init.items.custom.NamekDragonBallRadarItem;
import com.yuseix.dragonminez.world.DragonBallGenProvider;
import com.yuseix.dragonminez.world.NamekDragonBallGenProvider;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class RadarEvents {
	private static final ResourceLocation fondo = new ResourceLocation(DragonMineZ.MOD_ID,
			"textures/gui/radar.png");
	private static final ResourceLocation boton = new ResourceLocation(DragonMineZ.MOD_ID,
			"textures/gui/buttons/characterbuttons.png");


	private static List<BlockPos> closestDballPositions = new ArrayList<>();
	private static long lastUpdateTime = 0;
	private static final int UPDATE_INTERVAL_TICKS = 20 * 5; // (20 Ticks * Cant Segundos) = Segundos en Minecraft, default 5.

	@SubscribeEvent
	public static void onRenderGameOverlay(RenderGuiOverlayEvent.Pre event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.isPaused()) return;

		Player player = mc.player;
		if (player == null) return;
		GuiGraphics gui = event.getGuiGraphics();
		int radarSize = 140; // Radar tamañito (121x146 px)

		// Determine the radar type and dimension
		boolean isOverworld = player.level().dimension().equals(Level.OVERWORLD);
		boolean isNamek = player.level().dimension().equals(ModDimensions.NAMEK_DIM_LEVEL_KEY);

		if (isOverworld || isNamek) {
			// Check if the player holds the correct radar
			ItemStack radarItem = player.getMainHandItem();
			if ((isOverworld && radarItem.getItem() instanceof DragonBallRadarItem) ||
					(isNamek && radarItem.getItem() instanceof NamekDragonBallRadarItem)) {

				// Get radar range from NBT or use default
				int radarRange = radarItem.getOrCreateTag().getInt(DragonBallRadarItem.NBT_RANGE);
				if (radarRange == 0) radarRange = 75; // Default range

				// Draw radar background
				int centerX = mc.getWindow().getGuiScaledWidth() - radarSize - 10;
				int centerY = mc.getWindow().getGuiScaledHeight() - radarSize - 10;

				RenderSystem.setShader(GameRenderer::getPositionTexShader);
				RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
				RenderSystem.setShaderTexture(0, fondo);
				gui.blit(fondo, centerX, centerY, 0, 0, 121, 146);

				// Update dragon ball positions every 2 seconds (40 ticks)
				long currentTime = player.level().getGameTime();


				/*TODO: Capabilities nao funcionan
				 *  Estas necesitan que serverLevel sea un ServerLevel, pero no se puede hacer un cast directo y hay que usar packets. Y a mi me da paja */

				if (currentTime - lastUpdateTime > UPDATE_INTERVAL_TICKS) {

					System.out.println("Update...");

					if (isOverworld) {

						Level serverLevel = mc.level;
						serverLevel.getCapability(DragonBallGenProvider.CAPABILITY).ifPresent(capability ->
								closestDballPositions = capability.DragonBallPositions());
					} else if (isNamek) {
						Level serverLevel = mc.level;
						serverLevel.getCapability(NamekDragonBallGenProvider.CAPABILITY).ifPresent(capability ->
								closestDballPositions = capability.namekDragonBallPositions());
					}
					lastUpdateTime = currentTime;

					// Render detected dragon balls
					renderDballRadar(gui, player, radarRange, closestDballPositions, centerX, centerY);
				}
			}
		}
	}

	// Radar rendering logic
	private static void renderDballRadar(GuiGraphics gui, Player player, int radarRange, List<BlockPos> dballPositions, int centerX, int centerY) {
		for (BlockPos pos : dballPositions) {
			// Calculate 2D distance (ignoring Y)
			double dx = pos.getX() - player.getX();
			double dz = pos.getZ() - player.getZ();
			double distance = Math.sqrt(dx * dx + dz * dz);

			if (distance > radarRange) continue;

			// Calculate angle to block
			double angleToBlock = Math.atan2(dz, dx);
			double playerYaw = Math.toRadians(player.getYRot());
			double adjustedAngle = angleToBlock - playerYaw;

			// Map distance to radar radius (0-50 px)
			double radarRadius = Math.min(distance / radarRange * 50, 50);

			// Calculate radar dot position
			int radarX = (int) (centerX + 61 - radarRadius * Math.cos(adjustedAngle));
			int radarY = (int) (centerY + 87 - radarRadius * Math.sin(adjustedAngle));

			// Draw yellow dot
			gui.blit(fondo, radarX - 2, radarY - 2, 121, 0, 6, 6);
		}
	}
}