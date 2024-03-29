package com.yuseix.dragonminec.init;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.init.items.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Mainitems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DragonMineC.MODID);

    //REGISTRAR ITEMS
    public static final RegistryObject<Item> CAPSULA_ROJA =
            ITEMS.register("capsula_roja", () -> new CapsulaRojaItem(new Item.Properties()));
    public static final RegistryObject<Item> CAPSULA_VERDE =
            ITEMS.register("capsula_verde", () -> new CapsulaVerdeItem(new Item.Properties()));
    public static final RegistryObject<Item> CAPSULA_ANARANJADA =
            ITEMS.register("capsula_anaranjada", () -> new CapsulaNaranjaItem(new Item.Properties()));
    public static final RegistryObject<Item> CAPSULA_AZUL =
            ITEMS.register("capsula_azul", () -> new CapsulaAzulItem(new Item.Properties()));
    public static final RegistryObject<Item> CAPSULA_MORADA =
            ITEMS.register("capsula_morada", () -> new CapsulaMoradaItem(new Item.Properties()));
    public static final RegistryObject<Item> POTHALA_RIGHT =
            ITEMS.register("pothala_right", () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()));
    public static final RegistryObject<Item> POTHALA_LEFT =
            ITEMS.register("pothala_left", () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()));
    public static final RegistryObject<Item> GREEN_POTHALA_RIGHT =
            ITEMS.register("green_pothala_right", () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()));
    public static final RegistryObject<Item> GREEN_POTHALA_LEFT =
            ITEMS.register("green_pothala_left", () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()));
    //BLOCK ITEM
    public static final RegistryObject<Item> DBALL1_BLOCK_ITEM = ITEMS.register("dball1",
            () -> new DBall1BlockItem(Mainblocks.DBALL1_BLOCK.get(), new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL2_BLOCK_ITEM = ITEMS.register("dball2",
            () -> new DBall2BlockItem(Mainblocks.DBALL2_BLOCK.get(), new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL3_BLOCK_ITEM = ITEMS.register("dball3",
            () -> new DBall3BlockItem(Mainblocks.DBALL3_BLOCK.get(), new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL4_BLOCK_ITEM = ITEMS.register("dball4",
            () -> new DBall4BlockItem(Mainblocks.DBALL4_BLOCK.get(), new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL5_BLOCK_ITEM = ITEMS.register("dball5",
            () -> new DBall5BlockItem(Mainblocks.DBALL5_BLOCK.get(), new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL6_BLOCK_ITEM = ITEMS.register("dball6",
            () -> new DBall6BlockItem(Mainblocks.DBALL6_BLOCK.get(), new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL7_BLOCK_ITEM = ITEMS.register("dball7",
            () -> new DBall7BlockItem(Mainblocks.DBALL7_BLOCK.get(), new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));


    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

}
