package com.yuseix.dragonminez.worldgen.biome;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainEntity;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModBiomes {
    public static final ResourceKey<Biome> AJISSA_PLAINS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "ajissa_plains"));
    public static final ResourceKey<Biome> SACRED_PLAINS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "sacred_plains"));
    public static final ResourceKey<Biome> NAMEK_OCEAN = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "namek_ocean"));

    public static void generateData(BootstapContext<Biome> context) {
        addAjissaPlains(context);
        addSacredPlains(context);
        addNamekOcean(context);
    }

    public static void addAjissaPlains(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carver = context.lookup(Registries.CONFIGURED_CARVER);

        MobSpawnSettings.Builder mobs = new MobSpawnSettings.Builder();
        mobs.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(MainEntity.DINO1.get(), 8, 1, 4));

        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(context
                .lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.plainsSpawns(mobs);
        BiomeDefaultFeatures.addPlainGrass(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addPlainVegetation(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder);

        context.register(AJISSA_PLAINS, new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.8F)
                .downfall(0.4F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x61b651)
                        .waterFogColor(0x326726)
                        .fogColor(0x57aa48)
                        .skyColor(0x559134)
                        .build())
                .mobSpawnSettings(mobs.build())
                .generationSettings(builder.build())
                .build());
    }

    public static void addSacredPlains(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carver = context.lookup(Registries.CONFIGURED_CARVER);

        MobSpawnSettings.Builder mobs = new MobSpawnSettings.Builder();
        mobs.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(MainEntity.DINO1.get(), 8, 1, 4));

        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(context
                .lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.plainsSpawns(mobs);
        BiomeDefaultFeatures.addPlainGrass(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addPlainVegetation(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder);

        context.register(SACRED_PLAINS, new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.8F)
                .downfall(0.4F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x61b651)
                        .waterFogColor(0x326726)
                        .fogColor(0x57aa48)
                        .skyColor(0x559134)
                        .build())
                .mobSpawnSettings(mobs.build())
                .generationSettings(builder.build())
                .build());
    }

    public static void addNamekOcean(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carver = context.lookup(Registries.CONFIGURED_CARVER);

        MobSpawnSettings.Builder mobs = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(context
                .lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.warmOceanSpawns(mobs, 10, 4);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder);

        context.register(NAMEK_OCEAN, new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.8F)
                .downfall(0.4F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x61b651)
                        .waterFogColor(0x326726)
                        .fogColor(0x57aa48)
                        .skyColor(0x559134)
                        .build())
                .mobSpawnSettings(mobs.build())
                .generationSettings(builder.build())
                .build());
    }
}