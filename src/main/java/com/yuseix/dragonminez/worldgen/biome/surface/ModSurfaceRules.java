package com.yuseix.dragonminez.worldgen.biome.surface;

import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.worldgen.biome.ModBiomes;
import net.minecraft.data.Main;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource NAMEK_DIRT = makeStateRule(MainBlocks.NAMEK_DIRT.get());
    private static final SurfaceRules.RuleSource NAMEK_GRASS_BLOCK = makeStateRule(MainBlocks.NAMEK_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource SACRED_GRASS_BLOCK = makeStateRule(MainBlocks.NAMEK_SACRED_GRASS_BLOCK.get());

    public static SurfaceRules.RuleSource compileRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, NAMEK_GRASS_BLOCK), NAMEK_DIRT);

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.AJISSA_PLAINS),NAMEK_GRASS_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SACRED_PLAINS), SACRED_GRASS_BLOCK),

                    SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}