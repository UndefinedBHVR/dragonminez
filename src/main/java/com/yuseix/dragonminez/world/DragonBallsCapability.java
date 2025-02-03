package com.yuseix.dragonminez.world;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.ArrayList;
import java.util.List;

public class DragonBallsCapability {

    public boolean hasDragonBalls = false;
    public List<BlockPos> dragonBallPositions = new ArrayList<>();


    public boolean hasDragonBalls() {
        return hasDragonBalls;
    }


    public void setHasDragonBalls(boolean hasDragonBalls) {
        this.hasDragonBalls = hasDragonBalls;
    }

    public List<BlockPos> DragonBallPositions() {
        //Returna null nc pq
        return dragonBallPositions;
    }

    public void setDragonBallPositions(List<BlockPos> dragonBallPositions) {
        this.dragonBallPositions = dragonBallPositions;
    }

    public void saveNBTData(CompoundTag nbt) {

        nbt.putBoolean("hasDragonBalls", hasDragonBalls);

        ListTag listTag = new ListTag();
        for (BlockPos pos : dragonBallPositions) {
            CompoundTag subCompound = NbtUtils.writeBlockPos(pos);
            listTag.add(subCompound);
        }
        nbt.put("dragonBallPositions", listTag);


    }

    public void loadNBTData(CompoundTag nbt) {
        hasDragonBalls = nbt.getBoolean("hasDragonBalls");

        ListTag listTag = nbt.getList("dragonBallPositions", 10); // 10 is the NBT type for CompoundTag
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag subCompound = listTag.getCompound(i);
            BlockPos pos = NbtUtils.readBlockPos(subCompound);
            dragonBallPositions.add(pos);
        }

    }

    public void updateDragonBallPositions(Level level) {
        dragonBallPositions.removeIf(pos -> !level.isLoaded(pos) || level.getBlockEntity(pos) == null);
    }
}
