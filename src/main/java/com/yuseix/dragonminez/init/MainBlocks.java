package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.blocks.custom.*;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public interface MainBlocks {

    DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DragonMineZ.MOD_ID);

    //BLOQUE
    RegistryObject<Block> NAMEK_BLOCK = registerBlock("namek_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).sound(SoundType.BONE_BLOCK)));

    RegistryObject<Block> NAMEK_GRASS_BLOCK = registerBlock("namek_grass_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).sound(SoundType.GRASS)));
    RegistryObject<Block> NAMEK_DIRT = registerBlock("namek_dirt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.ROOTED_DIRT)));


    //plantas
    RegistryObject<Block> NAMEK_GRASS = registerBlock("namek_grass",
            () -> new NamekPlantsBlock(() -> MobEffects.LUCK, 5, BlockBehaviour.Properties.copy(Blocks.ALLIUM)
                    .noOcclusion()
                    .noCollission()
            ));


    RegistryObject<Block> DBALL1_BLOCK = BLOCKS.register("dball1",
            () -> new Dball1Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> 15)
                    .noParticlesOnBreak()
            ));
    RegistryObject<Block> DBALL2_BLOCK = BLOCKS.register("dball2",
            () -> new Dball2Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> 15)
                    .noParticlesOnBreak()
            ));
    RegistryObject<Block> DBALL3_BLOCK = BLOCKS.register("dball3",
            () -> new Dball3Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> 15)
                    .noParticlesOnBreak()
            ));
    RegistryObject<Block> DBALL4_BLOCK = BLOCKS.register("dball4",
            () -> new Dball4Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> 15)
                    .noParticlesOnBreak()
            ));
    RegistryObject<Block> DBALL5_BLOCK = BLOCKS.register("dball5",
            () -> new Dball5Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> 15)
                    .noParticlesOnBreak()
            ));
    RegistryObject<Block> DBALL6_BLOCK = BLOCKS.register("dball6",
            () -> new Dball6Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> 15)
                    .noParticlesOnBreak()
            ));
    RegistryObject<Block> DBALL7_BLOCK = BLOCKS.register("dball7",
            () -> new Dball7Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> 15)
                    .noParticlesOnBreak()
            ));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        MainItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
