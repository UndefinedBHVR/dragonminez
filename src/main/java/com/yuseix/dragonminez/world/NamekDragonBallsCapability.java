package com.yuseix.dragonminez.world;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;

import java.util.ArrayList;
import java.util.List;

public class NamekDragonBallsCapability {

    public boolean hasNamekDragonBalls = false;
    public List<BlockPos> namekDragonBallPositions = new ArrayList<>();


    public boolean hasNamekDragonBalls() {
        return hasNamekDragonBalls;
    }


    public void setHasNamekDragonBalls(boolean hasNamekDragonBalls) {
        this.hasNamekDragonBalls = hasNamekDragonBalls;
    }

    public List<BlockPos> namekDragonBallPositions() {
        //Returna null nc pq
        return namekDragonBallPositions;
    }

    public void setNamekDragonBallPositions(List<BlockPos> dragonBallPositions) {
        this.namekDragonBallPositions = dragonBallPositions;
    }

    public void saveNBTData(CompoundTag nbt) {

        nbt.putBoolean("hasNamekDragonBalls", hasNamekDragonBalls);

        ListTag listTag = new ListTag();
        for (BlockPos pos : namekDragonBallPositions) {
            CompoundTag subCompound = NbtUtils.writeBlockPos(pos);
            listTag.add(subCompound);
        }
        nbt.put("namekDragonBallPositions", listTag);


    }

    public void loadNBTData(CompoundTag nbt) {
        hasNamekDragonBalls = nbt.getBoolean("hasNamekDragonBalls");

        ListTag listTag = nbt.getList("namekDragonBallPositions", 10); // 10 is the NBT type for CompoundTag
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag subCompound = listTag.getCompound(i);
            BlockPos pos = NbtUtils.readBlockPos(subCompound);
            namekDragonBallPositions.add(pos);
        }

    }



}
