package com.yuseix.dragonminez.worldgen.biome.surface;

import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.worldgen.biome.ModBiomes;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {
    public static SurfaceRules.RuleSource compileRules() {
        return SurfaceRules.sequence(
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.AJISSA_PLAINS),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(MainBlocks.NAMEK_GRASS_BLOCK.get().defaultBlockState()))),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1,0),
                                                SurfaceRules.state(MainBlocks.NAMEK_DIRT.get().defaultBlockState())),
                                        SurfaceRules.state(MainBlocks.NAMEK_DIRT.get().defaultBlockState()))
                        )
                ),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SACRED_PLAINS),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(MainBlocks.NAMEK_SACRED_GRASS_BLOCK.get().defaultBlockState()))),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1,0),
                                                SurfaceRules.state(MainBlocks.NAMEK_DIRT.get().defaultBlockState())),
                                        SurfaceRules.state(MainBlocks.NAMEK_DIRT.get().defaultBlockState()))
                        )
                )
        );
    }
}