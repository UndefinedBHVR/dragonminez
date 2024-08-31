package com.yuseix.dragonminez.worldgen.dimension;

import com.yuseix.dragonminez.DragonMineZ;
import com.mojang.datafixers.util.Pair;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions extends NoiseRouterData{

    /*

    LEVEL STEM = Complemento del level, ambos sirven xd
    LEVEL = Esto si lo necesitamos porque basicamente es el mundo
    DIMENSIONTYPE = Esto es para establecer reglas en nuestro mundo

     */
    public static final ResourceKey<Level> NAMEK_DIM_LEVEL__KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DragonMineZ.MOD_ID, "namek"));
    public static final ResourceKey<LevelStem> NAMEK_DIM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DragonMineZ.MOD_ID, "namek"));
    public static final ResourceKey<DimensionType> NAMEK_DIM_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE,
                    new ResourceLocation(DragonMineZ.MOD_ID, "namek_type"));

    //NOISE SETTINGS CUSTOM
    public static final ResourceKey<NoiseGeneratorSettings> NAMEK_NOISE_SETTINGS = ResourceKey.create(Registries.NOISE_SETTINGS, new ResourceLocation(DragonMineZ.MOD_ID, "nameknoisegen"));


    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(NAMEK_DIM_TYPE, new DimensionType(
                OptionalLong.of(7500), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                true, // bedWorks
                true, // respawnAnchorWorks
                -64, // minY
                384, // height
                384, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                7.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }
    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        //Noise custom (Ya esta hecho en json pq en codigo es una japa
        //Aca es para poner un solo bioma en toda la dimension
        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.AJISSA_PLAINS)),
                noiseGenSettings.getOrThrow(NAMEK_NOISE_SETTINGS));


        //Varios Biomas
        NoiseBasedChunkGenerator noiseNamekMultiBiomes = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(Pair.of(
                                        Climate.parameters(0.0F, 0.0F, 0.1F, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.AJISSA_PLAINS)),
                                Pair.of(
                                        Climate.parameters(0.0F, 0.0F, 0.6F, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.SACRED_LAND)),
                                Pair.of(
                                        Climate.parameters(0.0F, 0.0F, -0.45f, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(ModBiomes.NAMEKIAN_RIVERS))
                        ))),
                noiseGenSettings.getOrThrow(NAMEK_NOISE_SETTINGS)); //Aca es poner nuestro namek noise
        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.NAMEK_DIM_TYPE), noiseNamekMultiBiomes);

        context.register(NAMEK_DIM_KEY, stem);
    }

    public static void bootstrapNoise(BootstapContext<NoiseGeneratorSettings> context) {
        //HolderGetter<NoiseGeneratorSettings> noiseGeneratorSettingsHolder = context.lookup(Registries.NOISE_SETTINGS);

        HolderGetter<DensityFunction> densityFunctions = context.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<NormalNoise.NoiseParameters> noiseParameters = context.lookup(Registries.NOISE);

        NoiseSettings noiseSettings = NoiseSettings.create(
                -64,
                384,
                1,
                2);

        NoiseGeneratorSettings noisegen = new NoiseGeneratorSettings(
                noiseSettings,
                MainBlocks.NAMEK_STONE.get().defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                NoiseRouterData.overworld(context.lookup(Registries.DENSITY_FUNCTION), context.lookup(Registries.NOISE), false, false),
                makeRules(),
                List.of(),
                63,
                false,
                true,
                true,
                false);

        context.register(NAMEK_NOISE_SETTINGS, noisegen);
    }


    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.RuleSource bedrockRule = SurfaceRules.ifTrue(
                SurfaceRules.verticalGradient(
                        "bedrock_floor",
                        VerticalAnchor.aboveBottom(0), // true_at_and_below
                        VerticalAnchor.aboveBottom(5)  // false_at_and_above
                ),
                SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())
        );

        SurfaceRules.RuleSource namekSurfaceRule = SurfaceRules.sequence(
                // Primera condición: Verifica si el bioma es uno de los especificados.
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.AJISSA_PLAINS, ModBiomes.AJISSA_PLAINS, ModBiomes.NAMEKIAN_RIVERS),
                        SurfaceRules.sequence(
                                // Segunda condición: Verifica si estamos por encima de la superficie preliminar.
                                SurfaceRules.ifTrue(
                                        SurfaceRules.abovePreliminarySurface(),
                                        SurfaceRules.ifTrue(
                                                // Tercera condición: Verifica la profundidad de la piedra.
                                                SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                                                SurfaceRules.sequence(
                                                        // Cuarta condición: Verifica si hay agua en la capa inferior.
                                                        SurfaceRules.ifTrue(
                                                                SurfaceRules.waterBlockCheck(-1, 0),
                                                                // Si es verdad, coloca el bloque de pasto de Namek.
                                                                SurfaceRules.state(MainBlocks.NAMEK_GRASS_BLOCK.get().defaultBlockState())
                                                        ),
                                                        // Si la capa de agua no está presente, coloca el bloque de tierra de Namek.
                                                        SurfaceRules.state(MainBlocks.NAMEK_DIRT.get().defaultBlockState())
                                                )
                                        )
                                ),
                                // Quinta condición: Verifica la profundidad de la piedra en la capa de suelo con `add_surface_depth: true`.
                                SurfaceRules.ifTrue(
                                        SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
                                        // Si es verdad, coloca el bloque de tierra de Namek.
                                        SurfaceRules.state(MainBlocks.NAMEK_DIRT.get().defaultBlockState())
                                )
                        )
                )
        );

        SurfaceRules.RuleSource sacredLandSurfaceRule = SurfaceRules.sequence(
                // Verifica si el bioma es "sacred_land"
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.SACRED_LAND),
                        SurfaceRules.sequence(
                                // Verifica si estamos por encima de la superficie preliminar
                                SurfaceRules.ifTrue(
                                        SurfaceRules.abovePreliminarySurface(),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                                                SurfaceRules.sequence(
                                                        // Si hay agua en el nivel 0, coloca el bloque "namek_sacred_grass_block"
                                                        SurfaceRules.ifTrue(
                                                                SurfaceRules.waterBlockCheck(0, 0),
                                                                SurfaceRules.state(MainBlocks.NAMEK_SACRED_GRASS_BLOCK.get().defaultBlockState())
                                                        ),
                                                        // Luego coloca el bloque "namek_dirt"
                                                        SurfaceRules.state(MainBlocks.NAMEK_DIRT.get().defaultBlockState())
                                                )
                                        )
                                ),
                                // Verifica la profundidad de la piedra y coloca el bloque "namek_dirt" si la condición es verdadera
                                SurfaceRules.ifTrue(
                                        SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
                                        SurfaceRules.state(MainBlocks.NAMEK_DIRT.get().defaultBlockState())
                                )
                        )
                )
        );

        SurfaceRules.RuleSource deepslateRule = SurfaceRules.ifTrue(
                SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)),
                SurfaceRules.state(MainBlocks.NAMEK_DEEPSLATE.get().defaultBlockState())
        );

        return SurfaceRules.sequence(
                bedrockRule,
                namekSurfaceRule,
                sacredLandSurfaceRule,
                deepslateRule
        );
    }

}
