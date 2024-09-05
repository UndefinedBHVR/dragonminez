package com.yuseix.dragonminez.worldgen.biome;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.worldgen.ModPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {

    public static final ResourceKey<Biome> AJISSA_PLAINS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "ajissa_plains"));

    public static final ResourceKey<Biome> SACRED_LAND = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "sacred_land"));

    public static final ResourceKey<Biome> NAMEKIAN_RIVERS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "namekian_rivers"));

    public static final ResourceKey<Biome> TIME_CHAMBER = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "hyperbolic_time_chamber"));

    public static void boostrap(BootstapContext<Biome> context){
        context.register(AJISSA_PLAINS, ajisa_plainsBiome(context));
        context.register(SACRED_LAND, sacredBiome(context));
        context.register(NAMEKIAN_RIVERS, namekianRiverBiome(context));
        context.register(TIME_CHAMBER, Time_Chamber_Biome(context));

    }
    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome ajisa_plainsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.BEE, 5, 4, 4));
        //BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        //BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);

        //globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        //BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        //BiomeDefaultFeatures.addFerns(biomeBuilder);
        //BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NAMEK_AJISSA_TREE_PLACED);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NAMEK_PATCH_GRASS_PLAIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NAMEK_PLAINS_FLOWERS);



        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(1479227)
                        .waterFogColor(676390)
                        .skyColor(6530427)
                        .grassColorOverride(0x2AA5EC)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(14217783)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome sacredBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.BEE, 5, 4, 4));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NAMEK_SACRED_TREE_PLACED);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NAMEK_PATCH_SACRED_GRASS_PLAIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NAMEK_SACRED_FLOWERS);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(1479227)
                        .waterFogColor(676390)
                        .skyColor(6530427)
                        .grassColorOverride(0x2AA5EC)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(14217783)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
    public static Biome namekianRiverBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(1479227)
                        .waterFogColor(676390)
                        .skyColor(6530427)
                        .grassColorOverride(0x2AA5EC)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(14217783)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
    public static Biome Time_Chamber_Biome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));


        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xDCF2FF)
                        .waterFogColor(0xDCF2FF)
                        .skyColor(0xF7FCFF)
                        .grassColorOverride(0xDCF2FF)
                        .foliageColorOverride(0xDCF2FF)
                        .fogColor(0xDCF2FF)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }


}
