package com.yuseix.dragonminez.events;

import com.mojang.logging.LogUtils;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.commands.ResetCharacterCommand;
import com.yuseix.dragonminez.commands.StatsCommand;
import com.yuseix.dragonminez.commands.ZPointsCommand;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.utils.Keys;
import com.yuseix.dragonminez.network.C2S.MenuC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.world.DragonBallGenProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.server.command.ConfigCommand;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//Anteriormente llamado ForgeListener ya que los eventos forman parte del bus de MinecraftForge
//ACTUALMENTE LOS ModEvents son eventos que se ejecutan en el bus de Forge **(DIFERENTE al IModBusEvent)**
//Si una clase extiende "Event" se considera un evento del bus de Forge y TIENE que estar acá.
//O también si es parte del paquete "net.minecraftforge.eventbus.api"
public final class ForgeBusEvents {

    public static final Capability<DMZStatsCapabilities> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {
    });

    private static final List<BlockPos> dragonBallPositions = new ArrayList<>();

    private static final Logger LOGGER = LogUtils.getLogger();

    private static final List<String> ALLOWED_USERNAMES = Arrays.asList(
            "Dev",
            "MrBrunoh",
            "Yuseix",
            "ezShokkoh");


    /*
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        String username = player.getGameProfile().getName();

        if (!ALLOWED_USERNAMES.contains(username)) {
            LOGGER.error("The user {} is not allowed to play the mod. The game session will now be terminated.", username);
            throw new IllegalStateException("DMZ: Username not allowed to start gameplay!");
        }
    }
    */

    @SubscribeEvent
    //Cancela el renderizado de la barra de vida
    public void RenderHealthBar(RenderGuiOverlayEvent.Pre event) {
        if (VanillaGuiOverlay.PLAYER_HEALTH.type() == event.getOverlay()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

        ServerLevel serverWorld = event.getServer().getLevel(Level.OVERWORLD);
        if (serverWorld == null) {
            return;
        }
        // Hacer algo cuando el servidor empiece???
        LOGGER.info("HOLA SOY DRAGON BLOCK TEST UWU");

        if (!serverWorld.isClientSide()) {
            serverWorld.getCapability(DragonBallGenProvider.CAPABILITY).ifPresent(dragonBallsCapability -> {
                boolean bhasDragonBalls = dragonBallsCapability.hasDragonBalls();

                if (!bhasDragonBalls) {
                    spawnDragonBall(serverWorld, MainBlocks.DBALL1_BLOCK.get().defaultBlockState());
                    spawnDragonBall(serverWorld, MainBlocks.DBALL2_BLOCK.get().defaultBlockState());
                    spawnDragonBall(serverWorld, MainBlocks.DBALL3_BLOCK.get().defaultBlockState());
                    spawnDragonBall(serverWorld, MainBlocks.DBALL4_BLOCK.get().defaultBlockState());
                    spawnDragonBall(serverWorld, MainBlocks.DBALL5_BLOCK.get().defaultBlockState());
                    spawnDragonBall(serverWorld, MainBlocks.DBALL6_BLOCK.get().defaultBlockState());
                    spawnDragonBall(serverWorld, MainBlocks.DBALL7_BLOCK.get().defaultBlockState());

                    dragonBallsCapability.setDragonBallPositions(dragonBallPositions);
                    dragonBallsCapability.setHasDragonBalls(true);
                }
            });

        }

    }

    @SubscribeEvent
    public void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            if (event.getObject().getCapability(INSTANCE).isPresent()) {
                return;
            }
            //System.out.println("Añadiendo capability");
            final DMZStatsProvider provider = new DMZStatsProvider(player);

            event.addCapability(DMZStatsProvider.ID, provider);

        }
    }

    @SubscribeEvent
    public void onAttachCapabilitiesWorld(AttachCapabilitiesEvent<Level> event) {
        if (event.getObject() instanceof ServerLevel) {
            if (!event.getObject().getCapability(DragonBallGenProvider.CAPABILITY).isPresent())
                event.addCapability(new ResourceLocation(DragonMineZ.MOD_ID, "dragon_balls"), new DragonBallGenProvider());
        }
    }

    @SubscribeEvent
    public void onCommandsRegister(RegisterCommandsEvent event) {
        new ZPointsCommand(event.getDispatcher());
        new StatsCommand(event.getDispatcher());
        new ResetCharacterCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {

        if (Keys.STATS_MENU.consumeClick()) {
            /*
            Minecraft.getInstance().setScreen(new CharacterCMenu(
                    Component.translatable("menu.title.dragonminez.statsmenu")));

             */
            ModMessages.sendToServer(new MenuC2S());
        }

    }

    private void spawnDragonBall(ServerLevel serverWorld, BlockState dragonBall) {

        //Spawn the dragon balls
        BlockPos spawnPos = serverWorld.getSharedSpawnPos();
        Random random = new Random();

        // Generate a random position within a 5k block radius from the spawn
        int x = spawnPos.getX() + random.nextInt(10000) - 5000;
        int z = spawnPos.getZ() + random.nextInt(10000) - 5000;

        serverWorld.getChunk(x >> 4, z >> 4); // Load the chunk (if not already loaded)


        int y = serverWorld.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);

        BlockPos pos = new BlockPos(x, y, z);

        // Place a Dragon Ball block at the generated position
        serverWorld.setBlock(pos, dragonBall, 2);
        System.out.println("Dragon Ball spawned at " + pos);

        dragonBallPositions.add(pos);
    }
}
