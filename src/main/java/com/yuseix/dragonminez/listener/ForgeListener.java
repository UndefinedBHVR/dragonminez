package com.yuseix.dragonminez.listener;

import com.mojang.logging.LogUtils;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.world.DragonBallGenProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class ForgeListener {

    private static final List<BlockPos> dragonBallPositions = new ArrayList<>();

    private static final Logger LOGGER = LogUtils.getLogger();

    private static final List<String> ALLOWED_USERNAMES = Arrays.asList(
            "Dev",
            "MrBrunoh",
            "Yuseix",
            "ezShokkoh");

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        String username = player.getGameProfile().getName();

        if (!ALLOWED_USERNAMES.contains(username)) {
            LOGGER.error("The user {} is not allowed to play the mod. The game session will now be terminated.", username);
            throw new IllegalStateException("DMZ: Username not allowed to start gameplay!");
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
