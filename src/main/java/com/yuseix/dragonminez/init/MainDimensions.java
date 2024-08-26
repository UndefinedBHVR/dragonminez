package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class MainDimensions {
    public static final ResourceKey<Level> NAMEK_DIM = ResourceKey.create(
            Registries.DIMENSION,
            new ResourceLocation(DragonMineZ.MOD_ID, "namekdim")
    );
}
