package com.yuseix.dragonminec.init.blocks.entity;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.init.Mainblocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DragonMineC.MODID);


    public static final RegistryObject<BlockEntityType<Dball1BlockEntity>> DBALL1_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("dball1_block_entity" , () ->
                    BlockEntityType.Builder.of(Dball1BlockEntity::new, Mainblocks.DBALL1_BLOCK.get())
                            .build(null));
    public static final RegistryObject<BlockEntityType<Dball2BlockEntity>> DBALL2_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("dball2_block_entity" , () ->
                    BlockEntityType.Builder.of(Dball2BlockEntity::new, Mainblocks.DBALL2_BLOCK.get())
                            .build(null));
    public static final RegistryObject<BlockEntityType<Dball3BlockEntity>> DBALL3_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("dball3_block_entity" , () ->
                    BlockEntityType.Builder.of(Dball3BlockEntity::new, Mainblocks.DBALL3_BLOCK.get())
                            .build(null));
    public static final RegistryObject<BlockEntityType<Dball4BlockEntity>> DBALL4_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("dball4_block_entity" , () ->
                    BlockEntityType.Builder.of(Dball4BlockEntity::new, Mainblocks.DBALL4_BLOCK.get())
                            .build(null));
    public static final RegistryObject<BlockEntityType<Dball5BlockEntity>> DBALL5_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("dball5_block_entity" , () ->
                    BlockEntityType.Builder.of(Dball5BlockEntity::new, Mainblocks.DBALL5_BLOCK.get())
                            .build(null));
    public static final RegistryObject<BlockEntityType<Dball6BlockEntity>> DBALL6_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("dball6_block_entity" , () ->
                    BlockEntityType.Builder.of(Dball6BlockEntity::new, Mainblocks.DBALL6_BLOCK.get())
                            .build(null));
    public static final RegistryObject<BlockEntityType<Dball7BlockEntity>> DBALL7_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("dball7_block_entity" , () ->
                    BlockEntityType.Builder.of(Dball7BlockEntity::new, Mainblocks.DBALL7_BLOCK.get())
                            .build(null));

    public static void register(IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }
}
