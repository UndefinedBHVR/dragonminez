package com.yuseix.dragonminec.stats;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerStatsAttrProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerStatsAttributes> PLAYER_STATS = CapabilityManager.get(
            new CapabilityToken<PlayerStatsAttributes>() {
            });

    private PlayerStatsAttributes playerStats = null;
    private final LazyOptional<PlayerStatsAttributes> optional = LazyOptional.of(this::createPlayerStats);

    private PlayerStatsAttributes createPlayerStats()
    {
        if(this.playerStats == null)
        {
            this.playerStats = new PlayerStatsAttributes();
        }
        return this.playerStats;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if(cap == PLAYER_STATS){
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerStats().saveNBTData(nbt);

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerStats().loadNBTData(nbt);
    }
}
