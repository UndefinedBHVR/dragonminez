package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MainBiomes {

    private static final Music NORMAL_MUSIC = null;

    public static final ResourceKey<Biome> NAMEK_BIOME = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID,"namek_biome"));

    public static void bootstrap(BootstapContext<Biome> context){
        HolderGetter<PlacedFeature> holdergetter = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holdergetter1 = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(NAMEK_BIOME, namekBiome(holdergetter, holdergetter1));

    }

    public static Biome namekBiome(HolderGetter<PlacedFeature> pPlacedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> pConfiguredWorldCarverGetter)
    {
        //Esto es para poner mobs que spawneen
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        //Ejemplo de mob spawneando DINO
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(MainEntity.DINO1.get(), 8, 4,4));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(pPlacedFeatureGetter, pConfiguredWorldCarverGetter);

        //Surface Custom, aqui reemplazamos cesped, tierra y piedra
        SurfaceRules.RuleSource customSurfaceRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(MainBiomes.NAMEK_BIOME),  // Reemplaza con tu bioma registrado
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(MainBlocks.NAMEK_GRASS_BLOCK.get().defaultBlockState())),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.state(MainBlocks.NAMEK_DIRT.get().defaultBlockState())),
                                SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), SurfaceRules.state(MainBlocks.NAMEK_STONE.get().defaultBlockState()))
                        )
                )
        );

        return biome(Biome.Precipitation.NONE, 2.0F, 0.0F, 0x38A932, 0x38A932, 0x90FF8A, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }



    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, int colorcielo ,int foliageColor,int grassColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, Music music)
    {
        return (new Biome.BiomeBuilder()).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(colorcielo).foliageColorOverride(foliageColor).grassColorOverride(grassColor).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, int colorcielo , MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, Music music)
    {
        return (new Biome.BiomeBuilder()).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(colorcielo).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

}
