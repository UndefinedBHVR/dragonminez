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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class MainBlocks {

    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, DragonMineZ.MOD_ID);

    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> (Boolean)p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
    }

    //BLOQUE
    //TODO: SFX Para Bloques Custom
    public static final RegistryObject<Block> NAMEK_BLOCK = registerBlock("namek_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> NAMEK_GRASS_BLOCK = registerBlock("namek_grass_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> NAMEK_DIRT = registerBlock("namek_dirt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.ROOTED_DIRT)));

    //ORES
    public static final RegistryObject<Block> GETE_ORE = registerBlock("gete_ore",
            () -> new GeteOreBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()
                    .randomTicks().lightLevel(litBlockEmission(9)).strength(3.0F, 3.0F)
            ));

    public static final RegistryObject<Block> GETE_FURNACE = registerBlock("gete_furnace",
            () -> new GeteFurnaceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(3.5F)
                    .lightLevel(litBlockEmission(13))
            ));

    //PLANTAS
    public static final RegistryObject<Block> NAMEK_GRASS = registerBlock("namek_grass",
            () -> new NamekPlantsBlock(() -> MobEffects.LUCK, 5, BlockBehaviour.Properties.copy(Blocks.ALLIUM)
                    .noOcclusion()
                    .noCollission()
            ));


    //DRAGON BALLS - TIERRA
    public static final RegistryObject<Block> DBALL1_BLOCK = BLOCK_REGISTER.register("dball1",
            () -> new Dball1Block(BlockBehaviour.Properties.copy(Blocks.BAMBOO)
                    .noOcclusion()
                    .lightLevel(value -> 15)
            ));
    public static final RegistryObject<Block> DBALL2_BLOCK = BLOCK_REGISTER.register("dball2",
            () -> new Dball2Block(BlockBehaviour.Properties.copy(Blocks.BAMBOO)
                    .noOcclusion()
                    .lightLevel(value -> 15)
            ));
    public static final RegistryObject<Block> DBALL3_BLOCK = BLOCK_REGISTER.register("dball3",
            () -> new Dball3Block(BlockBehaviour.Properties.copy(Blocks.BAMBOO)
                    .noOcclusion()
                    .lightLevel(value -> 15)
            ));
    public static final RegistryObject<Block> DBALL4_BLOCK = BLOCK_REGISTER.register("dball4",
            () -> new Dball4Block(BlockBehaviour.Properties.copy(Blocks.BAMBOO)
                    .noOcclusion()
                    .lightLevel(value -> 15)
            ));
    public static final RegistryObject<Block> DBALL5_BLOCK = BLOCK_REGISTER.register("dball5",
            () -> new Dball5Block(BlockBehaviour.Properties.copy(Blocks.BAMBOO)
                    .noOcclusion()
                    .lightLevel(value -> 15)
            ));
    public static final RegistryObject<Block> DBALL6_BLOCK = BLOCK_REGISTER.register("dball6",
            () -> new Dball6Block(BlockBehaviour.Properties.copy(Blocks.BAMBOO)
                    .noOcclusion()
                    .lightLevel(value -> 15)
            ));
    public static final RegistryObject<Block> DBALL7_BLOCK = BLOCK_REGISTER.register("dball7",
            () -> new Dball7Block(BlockBehaviour.Properties.copy(Blocks.BAMBOO)
                    .noOcclusion()
                    .lightLevel(value -> 15)
            ));

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> supplier) {
        RegistryObject<Block> registeredObject = BLOCK_REGISTER.register(name, supplier);
        //Registramos bloques como items también
        //Nota: No se usa el método MainItems.register() porque los bloques no son un evento BUS; se registra el bloque como item aquí.
        MainItems.ITEM_REGISTER.register(name, () -> new BlockItem(registeredObject.get(), new Item.Properties()));
        return registeredObject;
    }

    public static void register(IEventBus bus) {
        BLOCK_REGISTER.register(bus);
    }
}
