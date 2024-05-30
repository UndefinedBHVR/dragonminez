package com.yuseix.dragonminez.stats;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.events.ForgeBusEvents;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerStatsAttrProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final ResourceLocation ID = new ResourceLocation(DragonMineZ.MOD_ID, "mod");
    private final PlayerStatsAttributes backend;
    private final LazyOptional<PlayerStatsAttributes> optional;

    public PlayerStatsAttrProvider(Player player) {
        backend = new PlayerStatsAttributes(player);
        optional = LazyOptional.of(() -> backend);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return ForgeBusEvents.INSTANCE.orEmpty(cap, this.optional);
    }

    public static @NotNull <T> LazyOptional<T> getCap(Capability<T> cap, Entity entity) {
        return entity.getCapability(cap);
    }

    void invalidate() {
        this.optional.invalidate();
    }

    @Override
    public CompoundTag serializeNBT() {
        return backend.saveNBTData();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        backend.loadNBTData(nbt);
    }
}

