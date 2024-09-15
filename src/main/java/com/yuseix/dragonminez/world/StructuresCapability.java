package com.yuseix.dragonminez.world;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class StructuresCapability {

    private boolean hasTorreKamisama = false;
    // Posición donde se generara la torre de kamisama
    private BlockPos torreKamisamaPosition = null;

    public boolean hasTorreKamisama() {
        return hasTorreKamisama;
    }
    public void setHasTorreKamisama(boolean hasTorreKamisama) {
        this.hasTorreKamisama = hasTorreKamisama;
    }

    public BlockPos getTorreKamisamaPosition() {
        return torreKamisamaPosition;
    }

    public void setTorreKamisamaPosition(BlockPos torreKamisamaPosition) {
        this.torreKamisamaPosition = torreKamisamaPosition;
    }

    public void saveNBTData(CompoundTag nbt) {

        //Torre de Kamisama
        nbt.putBoolean("hasTorreKamisama", hasTorreKamisama);

        if (torreKamisamaPosition != null) {
            nbt.put("torreKamisamaPosition", NbtUtils.writeBlockPos(torreKamisamaPosition));
        }

    }

    public void loadNBTData(CompoundTag nbt) {

        hasTorreKamisama = nbt.getBoolean("hasTorreKamisama");

        if (nbt.contains("torreKamisamaPosition")) {
            torreKamisamaPosition = NbtUtils.readBlockPos(nbt.getCompound("torreKamisamaPosition"));
        }
    }

    // Generar la estructura si aún no ha sido generada
    public void generateStructureIfNeeded(ServerLevel level) {
        if (!hasTorreKamisama) {
            // Coordenadas específicas para la estructura (100, 45, 100)
            BlockPos position = new BlockPos(100, 45, 100);
            spawnTorreKamisama(level, position);

            // Marcar como generada y guardar la posición
            setHasTorreKamisama(true);
            setTorreKamisamaPosition(position);
        }
    }

    // Método para generar la estructura en una posición dada
    private void spawnTorreKamisama(ServerLevel level, BlockPos position) {
        StructureTemplate template = level.getStructureManager().getOrCreate(new ResourceLocation(DragonMineZ.MOD_ID, "kamisama"));

        if (template != null) {
            StructurePlaceSettings settings = new StructurePlaceSettings();
            template.placeInWorld(level, position, position, settings, level.getRandom(), 2);
        }
    }
}
