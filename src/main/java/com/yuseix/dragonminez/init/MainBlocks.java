package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.blocks.custom.*;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

@SuppressWarnings("unused")
public class MainBlocks {

    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, DragonMineZ.MOD_ID);

    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (IsThisOn) -> (Boolean) IsThisOn.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
    }

    //INICIO DE ITEMS SIN NECESIDADES ESPECIALES **NO** TIENEN SU CLASE EN INIT.BLOCKS.CUSTOM:
    //BLOQUES
    //TODO: SFX Para Bloques Custom
    public static final RegistryObject<Block> NAMEK_BLOCK = registerBlock("namek_block",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.QUARTZ_BLOCK).sound(SoundType.BONE_BLOCK)));
    public static final RegistryObject<Block> NAMEK_GRASS_BLOCK = registerBlock("namek_grass_block",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.GRASS_BLOCK).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> NAMEK_DIRT = registerBlock("namek_dirt",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.DIRT).sound(SoundType.ROOTED_DIRT)));
    public static final RegistryObject<Block> NAMEK_COBBLESTONE = registerBlock("namek_cobblestone",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.COBBLESTONE).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_STONE = registerBlock("namek_stone",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.STONE).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    //Arboles de Namek
    public static final RegistryObject<Block> NAMEK_SACRED_LOG = registerBlock("namek_sacred_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> NAMEK_SACRED_PLANKS = registerBlock("namek_sacred_planks",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> NAMEK_SACRED_LEAVES = registerBlock("namek_sacred_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.of(Blocks.OAK_LEAVES).sound(SoundType.GRASS)));

    public static final RegistryObject<Block> NAMEK_AJISSA_LOG = registerBlock("namek_ajissa_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> NAMEK_AJISSA_PLANKS = registerBlock("namek_ajissa_planks",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> NAMEK_AJISSA_LEAVES = registerBlock("namek_ajissa_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.of(Blocks.OAK_LEAVES).sound(SoundType.GRASS)));
    //GETE
    public static final RegistryObject<Block> GETE_BLOCK = registerBlock("gete_block",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.NETHERITE_BLOCK)));
    //TODO: SAPLING (AUN NO SE COMO HACERLO, XD)
//    public static final RegistryObject<Block> NAMEK_SACRED_SAPLING = registerBlock("namek_sacred_sapling",
//            () -> new SaplingBlock(new SacredTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)
//                    .mapColor(MapColor.PLANT)
//                    .noCollission()
//                    .randomTicks()
//                    .instabreak()
//                    .sound(SoundType.GRASS)
//                    .pushReaction(PushReaction.DESTROY)
//            ));

    //FIN DE ITEMS SIN NECESIDADES ESPECIALES

    //ORES
    public static final RegistryObject<Block> GETE_ORE = registerBlock("gete_debris_ore",
            () -> new GeteOreDebrisBlock(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)
                    .randomTicks().lightLevel(litBlockEmission(9))
            ));
    public static final RegistryObject<Block> NAMEK_DIAMOND_ORE = registerBlock("namek_diamond_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_GOLD_ORE = registerBlock("namek_gold_ore",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.GOLD_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_IRON_ORE = registerBlock("namek_iron_ore",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.IRON_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_LAPIS_ORE = registerBlock("namek_lapis_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Blocks.LAPIS_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_REDSTONE_ORE = registerBlock("namek_redstone_ore",
            () -> new RedStoneOreBlock(BlockBehaviour.Properties.of(Blocks.REDSTONE_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_COAL_ORE = registerBlock("namek_coal_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Blocks.COAL_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_EMERALD_ORE = registerBlock("namek_emerald_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Blocks.EMERALD_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_COPPER_ORE = registerBlock("namek_copper_ore",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.COPPER_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));

    // DEEPSLATE NAMEK
    public static final RegistryObject<Block> NAMEK_DEEPSLATE_DIAMOND = registerBlock("namek_deepslate_diamond",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_DEEPSLATE_GOLD = registerBlock("namek_deepslate_gold",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.GOLD_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_DEEPSLATE_IRON = registerBlock("namek_deepslate_iron",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.IRON_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_DEEPSLATE_LAPIS = registerBlock("namek_deepslate_lapis",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Blocks.LAPIS_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_DEEPSLATE_REDSTONE = registerBlock("namek_deepslate_redstone",
        () -> new RedStoneOreBlock(BlockBehaviour.Properties.of(Blocks.REDSTONE_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_DEEPSLATE_COAL = registerBlock("namek_deepslate_coal",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Blocks.COAL_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_DEEPSLATE_EMERALD = registerBlock("namek_deepslate_emerald",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Blocks.EMERALD_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));
    public static final RegistryObject<Block> NAMEK_DEEPSLATE_COPPER = registerBlock("namek_deepslate_copper",
            () -> new Block(BlockBehaviour.Properties.of(Blocks.COPPER_ORE).requiresCorrectToolForDrops().strength(3.0F, 6.0F)));

    //HORNO CUSTOM
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
