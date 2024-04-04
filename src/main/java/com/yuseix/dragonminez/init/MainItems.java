package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.items.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface MainItems {

    Item.Properties properties = new Item.Properties();
    DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DragonMineZ.MOD_ID);

    //CAPSULAS
    RegistryObject<Item> CAPSULA_ROJA =
            ITEMS.register("capsula_roja", CapsulaRojaItem::new);
    RegistryObject<Item> CAPSULA_VERDE =
            ITEMS.register("capsula_verde", CapsulaVerdeItem::new);
    RegistryObject<Item> CAPSULA_ANARANJADA =
            ITEMS.register("capsula_anaranjada", CapsulaNaranjaItem::new);
    RegistryObject<Item> CAPSULA_AZUL =
            ITEMS.register("capsula_azul", CapsulaAzulItem::new);
    RegistryObject<Item> CAPSULA_MORADA =
            ITEMS.register("capsula_morada", CapsulaMoradaItem::new);

    //POTHALAS
    RegistryObject<Item> POTHALA_RIGHT =
            ITEMS.register("pothala_right", () -> new Item(properties
                    .stacksTo(1)
                    .fireResistant()));
    RegistryObject<Item> POTHALA_LEFT =
            ITEMS.register("pothala_left", () -> new Item(properties
                    .stacksTo(1)
                    .fireResistant()));
    RegistryObject<Item> GREEN_POTHALA_RIGHT =
            ITEMS.register("green_pothala_right", () -> new Item(properties
                    .stacksTo(1)
                    .fireResistant()));
    RegistryObject<Item> GREEN_POTHALA_LEFT =
            ITEMS.register("green_pothala_left", () -> new Item(properties
                    .stacksTo(1)
                    .fireResistant()));

    //DRAGON BALLS
    RegistryObject<Item> DBALL1_BLOCK_ITEM = ITEMS.register("dball1",
            () -> new DBall1BlockItem(MainBlocks.DBALL1_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    RegistryObject<Item> DBALL2_BLOCK_ITEM = ITEMS.register("dball2",
            () -> new DBall2BlockItem(MainBlocks.DBALL2_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    RegistryObject<Item> DBALL3_BLOCK_ITEM = ITEMS.register("dball3",
            () -> new DBall3BlockItem(MainBlocks.DBALL3_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    RegistryObject<Item> DBALL4_BLOCK_ITEM = ITEMS.register("dball4",
            () -> new DBall4BlockItem(MainBlocks.DBALL4_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    RegistryObject<Item> DBALL5_BLOCK_ITEM = ITEMS.register("dball5",
            () -> new DBall5BlockItem(MainBlocks.DBALL5_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    RegistryObject<Item> DBALL6_BLOCK_ITEM = ITEMS.register("dball6",
            () -> new DBall6BlockItem(MainBlocks.DBALL6_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    RegistryObject<Item> DBALL7_BLOCK_ITEM = ITEMS.register("dball7",
            () -> new DBall7BlockItem(MainBlocks.DBALL7_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));


    static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

}
