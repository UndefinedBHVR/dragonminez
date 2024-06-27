package com.yuseix.dragonminez.worldgen.biome.surface;

import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.worldgen.biome.ModBiomes;
import net.minecraft.data.Main;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraftforge.common.Tags;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource NAMEK_DIRT = makeStateRule(MainBlocks.NAMEK_DIRT.get());
    private static final SurfaceRules.RuleSource NAMEK_GRASS_BLOCK = makeStateRule(MainBlocks.NAMEK_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource SACRED_GRASS_BLOCK = makeStateRule(MainBlocks.NAMEK_SACRED_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource NAMEK_STONE = makeStateRule(MainBlocks.NAMEK_STONE.get());
    private static final SurfaceRules.RuleSource NAMEK_DEEPSLATE = makeStateRule(MainBlocks.NAMEK_DEEPSLATE.get());

    public static SurfaceRules.RuleSource compileRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, NAMEK_GRASS_BLOCK), NAMEK_DIRT);
        SurfaceRules.RuleSource sacredSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, SACRED_GRASS_BLOCK), NAMEK_DIRT);

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.AJISSA_PLAINS), grassSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SACRED_PLAINS), sacredSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.NAMEK_OCEAN), SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.waterStartCheck(0, 0), SurfaceRules.sequence(
                                        SurfaceRules.state(Blocks.GRAVEL.defaultBlockState()),
                                        NAMEK_DEEPSLATE)))),
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck
                                (0, true, CaveSurface.FLOOR), NAMEK_STONE),
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck
                                (0, true, 0, CaveSurface.FLOOR), NAMEK_DEEPSLATE),
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck
                                (0, true, 1, CaveSurface.FLOOR), NAMEK_DEEPSLATE)
                );
    }


    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}