package com.yuseix.dragonminez.world;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainBiomes;
import com.yuseix.dragonminez.init.MainDimensions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, MainDimensions::bootstrapType)
            .add(Registries.LEVEL_STEM, MainDimensions::bootstrapStem)
            .add(Registries.BIOME, MainBiomes::bootstrap);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Collections.singleton(DragonMineZ.MOD_ID));
    }
}
