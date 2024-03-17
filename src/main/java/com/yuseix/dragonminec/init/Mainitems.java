package com.yuseix.dragonminec.init;

import com.yuseix.dragonminec.DragonMineC;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Mainitems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DragonMineC.MODID);

    //REGISTRAR ITEMS
    public static final RegistryObject<Item> ESFERA_DRAGON_1 =
            ITEMS.register("esfera1estrella", () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()));

    public static final RegistryObject<Item> ESFERA_DRAGON_2 =
            ITEMS.register("esfera2estrellas", () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()));

    public static final RegistryObject<Item> ESFERA_DRAGON_3 =
            ITEMS.register("esfera3estrellas", () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()));

    public static final RegistryObject<Item> ESFERA_DRAGON_4 =
            ITEMS.register("esfera4estrellas", () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()));

    public static final RegistryObject<Item> ESFERA_DRAGON_5 =
            ITEMS.register("esfera5estrellas", () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()));

    public static final RegistryObject<Item> ESFERA_DRAGON_6 =
            ITEMS.register("esfera6estrellas", () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()));

    public static final RegistryObject<Item> ESFERA_DRAGON_7 =
            ITEMS.register("esfera7estrellas", () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()));

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }

}
