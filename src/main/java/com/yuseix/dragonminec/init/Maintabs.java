package com.yuseix.dragonminec.init;

import com.yuseix.dragonminec.DragonMineC;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Maintabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DragonMineC.MODID);

    public static final RegistryObject<CreativeModeTab> PRUEBA_TAB = CREATIVE_MODE_TABS.register("dragonminec_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Mainitems.POTHALA_RIGHT.get()))

                    .title(Component.translatable("creativetab.dragonminectab"))

                    .displayItems((wa, anadir) -> {
                        //BLOQUES
                        anadir.accept(Mainblocks.NAMEK_BLOCK.get());
                        anadir.accept(Mainblocks.NAMEK_GRASS_BLOCK.get());
                        anadir.accept(Mainblocks.NAMEK_DIRT.get());
                        anadir.accept(Mainblocks.NAMEK_GRASS.get());

                        //ITEMS
                        anadir.accept(Mainitems.POTHALA_RIGHT.get());
                        anadir.accept(Mainitems.POTHALA_LEFT.get());
                        anadir.accept(Mainitems.GREEN_POTHALA_RIGHT.get());
                        anadir.accept(Mainitems.GREEN_POTHALA_LEFT.get());
                        anadir.accept(Mainitems.CAPSULA_ROJA.get());
                        anadir.accept(Mainitems.CAPSULA_VERDE.get());
                        anadir.accept(Mainitems.CAPSULA_ANARANJADA.get());
                        anadir.accept(Mainitems.CAPSULA_AZUL.get());
                        anadir.accept(Mainitems.CAPSULA_MORADA.get());


                    })

                    .build());
    public static final RegistryObject<CreativeModeTab> ESFERAS_TAB = CREATIVE_MODE_TABS.register("esferas_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Mainitems.DBALL1_BLOCK_ITEM.get()))

                    .title(Component.translatable("creativetab.esferastab"))

                    .displayItems((wa, anadir) -> {
                        //BLOQUES
                        anadir.accept(Mainitems.DBALL1_BLOCK_ITEM.get());
                        anadir.accept(Mainitems.DBALL2_BLOCK_ITEM.get());
                        anadir.accept(Mainitems.DBALL3_BLOCK_ITEM.get());
                        anadir.accept(Mainitems.DBALL4_BLOCK_ITEM.get());
                        anadir.accept(Mainitems.DBALL5_BLOCK_ITEM.get());
                        anadir.accept(Mainitems.DBALL6_BLOCK_ITEM.get());
                        anadir.accept(Mainitems.DBALL7_BLOCK_ITEM.get());


                    })

                    .build());
    public static void register(IEventBus bus){
        CREATIVE_MODE_TABS.register(bus);
    }

}
