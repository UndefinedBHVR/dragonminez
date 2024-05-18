package com.yuseix.dragonminez.stats;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StatsAttrProviderV2 implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<StatsAttributesV2> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    private final LazyOptional<StatsAttributesV2> optional = LazyOptional.of(this::getStatsBackend);

    private StatsAttributesV2 Stats = null;

    private StatsAttributesV2 getStatsBackend() {
        if (this.Stats == null) {
            this.Stats = new StatsAttributesV2();
        }
        return this.Stats;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CAPABILITY) {
            return this.optional.cast();
        } else {
            return LazyOptional.empty();
        }
    }

    //Esto se usa en partes de manejo in-game
    public static @NotNull <T> LazyOptional<T> getCap(Capability<T> cap, Entity entity) {
        return entity.getCapability(cap);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        getStatsBackend().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        getStatsBackend().loadNBTData(nbt);
    }
}

