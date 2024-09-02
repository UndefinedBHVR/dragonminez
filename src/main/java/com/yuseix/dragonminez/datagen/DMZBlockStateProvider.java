package com.yuseix.dragonminez.datagen;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMZBlockStateProvider extends BlockStateProvider {
    public DMZBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DragonMineZ.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //Bloques
        blockWithItem(MainBlocks.NAMEK_BLOCK);
        blockWithItem(MainBlocks.NAMEK_GRASS_BLOCK);
        blockWithItem(MainBlocks.NAMEK_SACRED_GRASS_BLOCK);
        blockWithItem(MainBlocks.NAMEK_DIRT);
        blockWithItem(MainBlocks.NAMEK_STONE);
        blockWithItem(MainBlocks.NAMEK_COBBLESTONE);
        axisBlock(((RotatedPillarBlock) MainBlocks.NAMEK_DEEPSLATE.get()), blockTexture(MainBlocks.NAMEK_DEEPSLATE.get()), blockTexture(MainBlocks.NAMEK_DEEPSLATE.get()));

        //Madera de Ajissa de Namek
        axisBlock(((RotatedPillarBlock) MainBlocks.NAMEK_AJISSA_WOOD.get()), blockTexture(MainBlocks.NAMEK_AJISSA_WOOD.get()), blockTexture(MainBlocks.NAMEK_AJISSA_WOOD.get()));
        axisBlock(((RotatedPillarBlock) MainBlocks.NAMEK_STRIPPED_AJISSA_WOOD.get()), blockTexture(MainBlocks.NAMEK_STRIPPED_AJISSA_WOOD.get()), blockTexture(MainBlocks.NAMEK_STRIPPED_AJISSA_WOOD.get()));
        blockWithItem(MainBlocks.NAMEK_AJISSA_PLANKS);
        blockItem(MainBlocks.NAMEK_AJISSA_WOOD);
        blockItem(MainBlocks.NAMEK_STRIPPED_AJISSA_WOOD);
        leavesBlock(MainBlocks.NAMEK_AJISSA_LEAVES);
        stairsBlock(((StairBlock) MainBlocks.NAMEK_AJISSA_STAIRS.get()), blockTexture(MainBlocks.NAMEK_AJISSA_PLANKS.get()));
        slabBlock(((SlabBlock) MainBlocks.NAMEK_AJISSA_SLAB.get()), blockTexture(MainBlocks.NAMEK_AJISSA_PLANKS.get()), blockTexture(MainBlocks.NAMEK_AJISSA_PLANKS.get()));
        buttonBlock(((ButtonBlock) MainBlocks.NAMEK_AJISSA_BUTTON.get()), blockTexture(MainBlocks.NAMEK_AJISSA_PLANKS.get()));
        fenceBlock(((FenceBlock) MainBlocks.NAMEK_AJISSA_FENCE.get()), blockTexture(MainBlocks.NAMEK_AJISSA_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) MainBlocks.NAMEK_AJISSA_FENCE_GATE.get()), blockTexture(MainBlocks.NAMEK_AJISSA_PLANKS.get()));
        doorBlockWithRenderType(((DoorBlock) MainBlocks.NAMEK_AJISSA_DOOR.get()), modLoc("block/ajissa_door_bottom"), modLoc("block/ajissa_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) MainBlocks.NAMEK_AJISSA_TRAPDOOR.get()), modLoc("block/ajissa_trapdoor"), true, "cutout");

        //Madera Sagrada de Namek
        axisBlock(((RotatedPillarBlock) MainBlocks.NAMEK_SACRED_WOOD.get()), blockTexture(MainBlocks.NAMEK_SACRED_WOOD.get()), blockTexture(MainBlocks.NAMEK_SACRED_WOOD.get()));
        axisBlock(((RotatedPillarBlock) MainBlocks.NAMEK_STRIPPED_SACRED_WOOD.get()), blockTexture(MainBlocks.NAMEK_STRIPPED_SACRED_WOOD.get()), blockTexture(MainBlocks.NAMEK_STRIPPED_SACRED_WOOD.get()));
        blockWithItem(MainBlocks.NAMEK_SACRED_PLANKS);
        blockItem(MainBlocks.NAMEK_SACRED_WOOD);
        blockItem(MainBlocks.NAMEK_STRIPPED_SACRED_WOOD);
        leavesBlock(MainBlocks.NAMEK_SACRED_LEAVES);
        stairsBlock(((StairBlock) MainBlocks.NAMEK_SACRED_STAIRS.get()), blockTexture(MainBlocks.NAMEK_SACRED_PLANKS.get()));
        slabBlock(((SlabBlock) MainBlocks.NAMEK_SACRED_SLAB.get()), blockTexture(MainBlocks.NAMEK_SACRED_PLANKS.get()), blockTexture(MainBlocks.NAMEK_SACRED_PLANKS.get()));
        buttonBlock(((ButtonBlock) MainBlocks.NAMEK_SACRED_BUTTON.get()), blockTexture(MainBlocks.NAMEK_SACRED_PLANKS.get()));
        fenceBlock(((FenceBlock) MainBlocks.NAMEK_SACRED_FENCE.get()), blockTexture(MainBlocks.NAMEK_SACRED_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) MainBlocks.NAMEK_SACRED_FENCE_GATE.get()), blockTexture(MainBlocks.NAMEK_SACRED_PLANKS.get()));
        doorBlockWithRenderType(((DoorBlock) MainBlocks.NAMEK_SACRED_DOOR.get()), modLoc("block/sacred_door_bottom"), modLoc("block/sacred_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) MainBlocks.NAMEK_SACRED_TRAPDOOR.get()), modLoc("block/sacred_trapdoor"), true, "cutout");

        //Ores Nuevos
        blockWithItem(MainBlocks.GETE_BLOCK);
        blockWithItem(MainBlocks.GETE_ORE);
        blockWithItem(MainBlocks.NAMEK_KIKONO_ORE);
        blockWithItem(MainBlocks.KIKONO_BLOCK);

        //Ores (Default) de Namek
        blockWithItem(MainBlocks.NAMEK_DIAMOND_ORE);
        blockWithItem(MainBlocks.NAMEK_GOLD_ORE);
        blockWithItem(MainBlocks.NAMEK_IRON_ORE);
        blockWithItem(MainBlocks.NAMEK_LAPIS_ORE);
        blockWithItem(MainBlocks.NAMEK_REDSTONE_ORE);
        blockWithItem(MainBlocks.NAMEK_COAL_ORE);
        blockWithItem(MainBlocks.NAMEK_EMERALD_ORE);
        blockWithItem(MainBlocks.NAMEK_COPPER_ORE);

        //Ores (Default) de Deepslate de Namek
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_DIAMOND);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_GOLD);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_IRON);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_LAPIS);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_REDSTONE);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_COAL);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_EMERALD);
        blockWithItem(MainBlocks.NAMEK_DEEPSLATE_COPPER);

        //BlockEntities
        blockWithItem(MainBlocks.GETE_FURNACE);
        //blockWithItem(MainBlocks.KIKONO_ARMOR_STATION);

        //Plantas
        simpleBlockWithItem(MainBlocks.NAMEK_GRASS.get(), models().cross(blockTexture(MainBlocks.NAMEK_GRASS.get()).getPath(), blockTexture(MainBlocks.NAMEK_GRASS.get())).renderType("cutout"));
        simpleBlockWithItem(MainBlocks.NAMEK_SACRED_GRASS.get(), models().cross(blockTexture(MainBlocks.NAMEK_SACRED_GRASS.get()).getPath(), blockTexture(MainBlocks.NAMEK_SACRED_GRASS.get())).renderType("cutout"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(DragonMineZ.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                new ResourceLocation("minecraft:block/leaves"), "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
}
