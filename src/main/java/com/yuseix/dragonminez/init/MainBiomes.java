package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class MainBiomes {

    public static final ResourceKey<Biome> NAMEK_BIOME = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID,"namek_biome"));
}
