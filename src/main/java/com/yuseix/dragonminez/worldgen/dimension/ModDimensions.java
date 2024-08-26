package com.yuseix.dragonminez.worldgen.dimension;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.awt.*;

public class ModDimensions {

    public static final ResourceKey<Level> NAMEK_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DragonMineZ.MOD_ID, "namek"));

    public static final ResourceKey<DimensionType> NAMEK_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, NAMEK_KEY.location());

    public static void register() {
        System.out.println("Registrando Dimensiones.");
    }
}
