package com.yuseix.dragonminez.worldgen.dimension;

import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.datafixers.util.Pair;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.worldgen.biome.ModBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions {

    public static final ResourceKey<Level> NAMEK = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DragonMineZ.MOD_ID, "planet_namek"));
    public static final ResourceKey<LevelStem> NAMEK_STEM = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DragonMineZ.MOD_ID, "planet_namek"));
    public static final ResourceKey<DimensionType> NAMEK_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(DragonMineZ.MOD_ID, "planet_namek"));

    //Más fácil para agregar futuras dimensiones en lista :P
//    public static void generateDimension(BootstapContext<DimensionType> context) {
//        namekDimension(context);
//    }

    public static void generateDimension(BootstapContext<DimensionType> context) {
        context.register(NAMEK_DIM_TYPE, new DimensionType(
                OptionalLong.of(12000), //fixedTime
                true, //hasSkyLight
                false, //hasCeiling
                false, //ultrawarm
                true, //natural
                1.0F, //coordinateScale
                true, //bedWorks
                true, //respawnAnchorWorks
                -64, //minY
                256, //height
                256, //logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, //infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, //effectsLocation
                0.0F, //ambientLight
                new DimensionType.MonsterSettings(false, true,
                        UniformInt.of(0, 7), 0))); //monsterSettings
    }

    public static void generateStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimType = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        MultiNoiseBiomeSource biome = MultiNoiseBiomeSource.createFromList(
                new Climate.ParameterList<>(List.of(
                        Pair.of(Climate.parameters(0.2F, 0.6F, 0.1F, 0.6F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.AJISSA_PLAINS)),
                        Pair.of(Climate.parameters(0.4F, 0.4F, -0.4F, 0.7F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.SACRED_PLAINS)),
                        Pair.of(Climate.parameters(0.5F, 0.6F, 0.3F, 0.5F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.NAMEK_OCEAN))
                )));

        Holder<NoiseGeneratorSettings> noise = noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD);
        NoiseBasedChunkGenerator generator = new NoiseBasedChunkGenerator(biome, noise);

        context.register(NAMEK_STEM, new LevelStem(dimType.getOrThrow(NAMEK_DIM_TYPE), generator));
    }
}
