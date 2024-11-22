package com.yuseix.dragonminez.world;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class StructuresCapability {

    private boolean hasTorreKamisama = false;
    private boolean hasHabTiempo = false;

    // Posición donde se generara la torre de kamisama
    private BlockPos torreKamisamaPosition = null;
    // Posición hab tiempo
    private BlockPos habTiempoPos = null;

    public boolean hasTorreKamisama() {
        return hasTorreKamisama;
    }

    public boolean isHasHabTiempo() {
        return hasHabTiempo;
    }

    public void setHasTorreKamisama(boolean hasTorreKamisama) {
        this.hasTorreKamisama = hasTorreKamisama;
    }

    public void setHasHabTiempo(boolean hasHabTiempo) {
        this.hasHabTiempo = hasHabTiempo;
    }

    public BlockPos getTorreKamisamaPosition() {
        return torreKamisamaPosition != null ? torreKamisamaPosition : new BlockPos(100, 45, 100);
    }

    public BlockPos getHabTiempoPos() {
        return habTiempoPos != null ? habTiempoPos : new BlockPos(-9, 31, -70);
    }

    public void setTorreKamisamaPosition(BlockPos torreKamisamaPosition) {
        this.torreKamisamaPosition = torreKamisamaPosition;
    }

    public void setHabTiempoPos(BlockPos habTiempoPos) {
        this.habTiempoPos = habTiempoPos;
    }

    public void saveNBTData(CompoundTag nbt) {

        //Torre de Kamisama
        nbt.putBoolean("hasTorreKamisama", hasTorreKamisama);
        //Hab Tiempo
        nbt.putBoolean("hasHabTiempo", hasHabTiempo);

        if (torreKamisamaPosition != null) {
            nbt.put("torreKamisamaPosition", NbtUtils.writeBlockPos(torreKamisamaPosition));
        }
        if (habTiempoPos != null) {
            nbt.put("habTiempoPosition", NbtUtils.writeBlockPos(habTiempoPos));
        }
    }

    public void loadNBTData(CompoundTag nbt) {

        hasTorreKamisama = nbt.getBoolean("hasTorreKamisama");
        hasHabTiempo = nbt.getBoolean("hasHabTiempo");

        if (nbt.contains("torreKamisamaPosition")) {
            torreKamisamaPosition = NbtUtils.readBlockPos(nbt.getCompound("torreKamisamaPosition"));
        }
        if (nbt.contains("habTiempoPosition")) {
            habTiempoPos = NbtUtils.readBlockPos(nbt.getCompound("habTiempoPosition"));
        }
    }

    // Generar la estructura si aún no ha sido generada
    public void generateKamisamaStructure(ServerLevel level) {
        if (!hasTorreKamisama) {
            // Coordenadas específicas para la estructura (100, 45, 100)
            BlockPos position = new BlockPos(100, 45, 100);
            spawnTorreKamisama(level, position);

            // Marcar como generada y guardar la posición
            setHasTorreKamisama(true);
            setTorreKamisamaPosition(position);
        }
    }
    public void generateHabTiempoStructure(ServerLevel level) {
        if (!hasHabTiempo) {
            BlockPos position = new BlockPos(-9, 31, -70);
            spawnHabTiempo(level, position);

            // Marcar como generada y guardar la posición
            setHasHabTiempo(true);
            setHabTiempoPos(position);
        }
    }

    // Método para generar la estructura
    private void spawnTorreKamisama(ServerLevel level, BlockPos position) {
        StructureTemplate template = level.getStructureManager().getOrCreate(new ResourceLocation(DragonMineZ.MOD_ID, "kamisama"));

        if (template != null) {
            StructurePlaceSettings settings = new StructurePlaceSettings();
            template.placeInWorld(level, position, position, settings, level.getRandom(), 2);
        }
    }
    private void spawnHabTiempo(ServerLevel level, BlockPos position) {
        StructureTemplate template = level.getStructureManager().getOrCreate(new ResourceLocation(DragonMineZ.MOD_ID, "habtiempo"));

        if (template != null) {
            StructurePlaceSettings settings = new StructurePlaceSettings();
            template.placeInWorld(level, position, position, settings, level.getRandom(), 2);
        }
    }
}
