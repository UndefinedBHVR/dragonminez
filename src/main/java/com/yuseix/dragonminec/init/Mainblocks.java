package com.yuseix.dragonminec.init;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.init.blocks.custom.*;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Mainblocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DragonMineC.MODID);

    //BLOQUE
    public static final RegistryObject<Block> NAMEK_BLOCK = registerBlock("namek_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).sound(SoundType.BONE_BLOCK)));

    public static final RegistryObject<Block> NAMEK_GRASS_BLOCK = registerBlock("namek_grass_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> NAMEK_DIRT = registerBlock("namek_dirt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.ROOTED_DIRT)));



    //plantas
    public static final RegistryObject<Block> NAMEK_GRASS = registerBlock("namek_grass",
            () -> new NamekPlantsBlock(() -> MobEffects.LUCK, 5,BlockBehaviour.Properties.copy(Blocks.ALLIUM)
                    .noOcclusion()
                    .noCollission()
            ));





    public static final RegistryObject<Block> DBALL1_BLOCK = BLOCKS.register("dball1",
            () -> new Dball1Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> {
                return 15;
            })
                    .noParticlesOnBreak()
            ));
    public static final RegistryObject<Block> DBALL2_BLOCK = BLOCKS.register("dball2",
            () -> new Dball2Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> {
                        return 15;
                    })
                    .noParticlesOnBreak()
            ));
    public static final RegistryObject<Block> DBALL3_BLOCK = BLOCKS.register("dball3",
            () -> new Dball3Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> {
                        return 15;
                    })
                    .noParticlesOnBreak()
            ));
    public static final RegistryObject<Block> DBALL4_BLOCK = BLOCKS.register("dball4",
            () -> new Dball4Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> {
                        return 15;
                    })
                    .noParticlesOnBreak()
            ));
    public static final RegistryObject<Block> DBALL5_BLOCK = BLOCKS.register("dball5",
            () -> new Dball5Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> {
                        return 15;
                    })
                    .noParticlesOnBreak()
            ));
    public static final RegistryObject<Block> DBALL6_BLOCK = BLOCKS.register("dball6",
            () -> new Dball6Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> {
                        return 15;
                    })
                    .noParticlesOnBreak()
            ));
    public static final RegistryObject<Block> DBALL7_BLOCK = BLOCKS.register("dball7",
            () -> new Dball7Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
                    .lightLevel(value -> {
                        return 15;
                    })
                    .noParticlesOnBreak()
            ));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return Mainitems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus bus){
        BLOCKS.register(bus);
    }
}
