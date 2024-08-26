package com.yuseix.dragonminez.worldgen.biome;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(Registries.BIOME, DragonMineZ.MOD_ID);

    public static final ResourceKey<Biome> AJISSA_PLAINS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "ajissa_plains"));

    public static final ResourceKey<Biome> SACRED_LAND = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "sacred_land"));

    public static final ResourceKey<Biome> NAMEKIAN_RIVERS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DragonMineZ.MOD_ID, "namekian_rivers"));

    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);
    }
}
