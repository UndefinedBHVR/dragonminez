package com.yuseix.dragonminez.worldgen;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.utils.DMZTags;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    //aca es para definir alturas de los minerales y como funcionan en la generacion
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_COAL_ORE_BURIED_KEY = registerKey("namek_coal_ore_buried_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_COAL_ORE_NORMAL_KEY = registerKey("namek_coal_ore_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_COPPER_ORE_SMALL_KEY = registerKey("namek_copper_ore_small_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_COPPER_ORE_LARGE_KEY = registerKey("namek_copper_ore_large_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_IRON_ORE_SMALL_KEY = registerKey("namek_iron_ore_small_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_IRON_ORE_LARGE_KEY = registerKey("namek_iron_ore_large_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_GOLD_ORE_BURIED_KEY = registerKey("namek_gold_ore_buried_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_LAPIS_ORE_KEY = registerKey("namek_lapis_ore_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_LAPIS_ORE_LARGE_KEY = registerKey("namek_lapis_ore_large_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_REDSTONE_ORE_KEY = registerKey("namek_redstone_ore_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_EMERALD_ORE_KEY = registerKey("namek_emerald_ore_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_DIAMOND_ORE_KEY = registerKey("namek_diamond_ore_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_DIAMOND_ORE_MIDDLE_KEY = registerKey("namek_diamond_ore_middle_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_DIAMOND_ORE_LARGE_KEY = registerKey("namek_diamond_ore_large_configured");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_PATCH_GRASS_KEY = registerKey("namek_patch_grass_configured");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NAMEK_PATCH_SACRED_GRASS_KEY = registerKey("namek_patch_sacred_grass_configured");




    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_NAMEK_AJISSA_KEY = registerKey("namek_ajissa_key");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        //MC NORMAL
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        //DMZ
        RuleTest namek_stoneReplaceable = new TagMatchTest(DMZTags.Blocks.NAMEK_STONE_ORE_REPLACEABLES);
        RuleTest namek_deepslateReplaceables = new TagMatchTest(DMZTags.Blocks.NAMEK_DEEPSLATE_ORE_REPLACEABLES);

        //CARBON
        //Aca por ejemplo definimos que aparecen en las piedras normales como tambien en la deepslate
        //Aca definimos que puede aparecer en los tags de namek_Stone y namek_deepslate
        List<OreConfiguration.TargetBlockState> namek_coal_ores = List.of(

                OreConfiguration.target(namek_stoneReplaceable, MainBlocks.NAMEK_COAL_ORE.get().defaultBlockState()),

                OreConfiguration.target(namek_deepslateReplaceables, MainBlocks.NAMEK_DEEPSLATE_COAL.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> namek_copper_ores = List.of(

                OreConfiguration.target(namek_stoneReplaceable, MainBlocks.NAMEK_COPPER_ORE.get().defaultBlockState()),

                OreConfiguration.target(namek_deepslateReplaceables, MainBlocks.NAMEK_DEEPSLATE_COPPER.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> namek_iron_ores = List.of(

                OreConfiguration.target(namek_stoneReplaceable, MainBlocks.NAMEK_IRON_ORE.get().defaultBlockState()),

                OreConfiguration.target(namek_deepslateReplaceables, MainBlocks.NAMEK_DEEPSLATE_IRON.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> namek_gold_ores = List.of(

                OreConfiguration.target(namek_stoneReplaceable, MainBlocks.NAMEK_GOLD_ORE.get().defaultBlockState()),

                OreConfiguration.target(namek_deepslateReplaceables, MainBlocks.NAMEK_DEEPSLATE_GOLD.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> namek_lapis_ores = List.of(

                OreConfiguration.target(namek_stoneReplaceable, MainBlocks.NAMEK_LAPIS_ORE.get().defaultBlockState()),

                OreConfiguration.target(namek_deepslateReplaceables, MainBlocks.NAMEK_DEEPSLATE_LAPIS.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> namek_redstone_ores = List.of(

                OreConfiguration.target(namek_stoneReplaceable, MainBlocks.NAMEK_REDSTONE_ORE.get().defaultBlockState()),

                OreConfiguration.target(namek_deepslateReplaceables, MainBlocks.NAMEK_DEEPSLATE_REDSTONE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> namek_emerald_ores = List.of(

                OreConfiguration.target(namek_stoneReplaceable, MainBlocks.NAMEK_EMERALD_ORE.get().defaultBlockState()),

                OreConfiguration.target(namek_deepslateReplaceables, MainBlocks.NAMEK_DEEPSLATE_EMERALD.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> namek_diamond_ores = List.of(

                OreConfiguration.target(namek_stoneReplaceable, MainBlocks.NAMEK_DIAMOND_ORE.get().defaultBlockState()),

                OreConfiguration.target(namek_deepslateReplaceables, MainBlocks.NAMEK_DEEPSLATE_DIAMOND.get().defaultBlockState()));

        //Ore
        register(context, NAMEK_COAL_ORE_BURIED_KEY, Feature.ORE, new OreConfiguration(namek_coal_ores, 17, 0.5f));
        register(context, NAMEK_COAL_ORE_NORMAL_KEY, Feature.ORE, new OreConfiguration(namek_coal_ores, 17));

        register(context, NAMEK_COPPER_ORE_SMALL_KEY, Feature.ORE, new OreConfiguration(namek_copper_ores, 10));
        register(context, NAMEK_COPPER_ORE_LARGE_KEY, Feature.ORE, new OreConfiguration(namek_copper_ores, 20));

        register(context, NAMEK_IRON_ORE_SMALL_KEY, Feature.ORE, new OreConfiguration(namek_iron_ores, 4));
        register(context, NAMEK_IRON_ORE_LARGE_KEY, Feature.ORE, new OreConfiguration(namek_iron_ores, 9));

        register(context, NAMEK_GOLD_ORE_BURIED_KEY, Feature.ORE, new OreConfiguration(namek_gold_ores, 9, 0.5f));

        register(context, NAMEK_LAPIS_ORE_KEY, Feature.ORE, new OreConfiguration(namek_lapis_ores, 7));
        register(context, NAMEK_LAPIS_ORE_LARGE_KEY, Feature.ORE, new OreConfiguration(namek_lapis_ores, 7, 1.0f));

        register(context, NAMEK_REDSTONE_ORE_KEY, Feature.ORE, new OreConfiguration(namek_redstone_ores, 8));

        register(context, NAMEK_EMERALD_ORE_KEY, Feature.ORE, new OreConfiguration(namek_emerald_ores, 3));

        register(context, NAMEK_DIAMOND_ORE_KEY, Feature.ORE, new OreConfiguration(namek_diamond_ores, 4,0.5f));
        register(context, NAMEK_DIAMOND_ORE_MIDDLE_KEY, Feature.ORE, new OreConfiguration(namek_diamond_ores, 8,1.0f));
        register(context, NAMEK_DIAMOND_ORE_LARGE_KEY, Feature.ORE, new OreConfiguration(namek_diamond_ores, 12,0.7f));

        //GRASS
        register(context, NAMEK_PATCH_GRASS_KEY, Feature.RANDOM_PATCH, new RandomPatchConfiguration(32, 7, 3,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(MainBlocks.NAMEK_GRASS.get()
                        .defaultBlockState()
                )))));
        register(context, NAMEK_PATCH_SACRED_GRASS_KEY, Feature.RANDOM_PATCH, new RandomPatchConfiguration(32, 7, 3,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(MainBlocks.NAMEK_SACRED_GRASS.get()
                        .defaultBlockState()
                )))));

        //Ejemplo arbol (aca especificas el tamaño de la madera, hojas y eso)
        register(context, TREE_NAMEK_AJISSA_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MainBlocks.NAMEK_AJISSA_LOG.get()),
                new StraightTrunkPlacer(5,4,3),

                BlockStateProvider.simple(MainBlocks.NAMEK_AJISSA_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),

                new TwoLayersFeatureSize(1,0,2)).build()
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(DragonMineZ.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
