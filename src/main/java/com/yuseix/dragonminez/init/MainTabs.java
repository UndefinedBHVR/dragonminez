package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public interface MainTabs {

    DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DragonMineZ.MOD_ID);

    RegistryObject<CreativeModeTab> PRUEBA_TAB = CREATIVE_MODE_TABS.register("dragonminez_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MainItems.POTHALA_RIGHT.get()))

                    .title(Component.translatable("creativetab.dragonmineztab"))

                    .displayItems((wa, anadir) -> {
                        //BLOQUES
                        anadir.accept(MainBlocks.NAMEK_BLOCK.get());
                        anadir.accept(MainBlocks.NAMEK_GRASS_BLOCK.get());
                        anadir.accept(MainBlocks.NAMEK_DIRT.get());
                        anadir.accept(MainBlocks.NAMEK_GRASS.get());

                        //ITEMS
                        anadir.accept(MainItems.POTHALA_RIGHT.get());
                        anadir.accept(MainItems.POTHALA_LEFT.get());
                        anadir.accept(MainItems.GREEN_POTHALA_RIGHT.get());
                        anadir.accept(MainItems.GREEN_POTHALA_LEFT.get());
                        anadir.accept(MainItems.CAPSULA_ROJA.get());
                        anadir.accept(MainItems.CAPSULA_VERDE.get());
                        anadir.accept(MainItems.CAPSULA_ANARANJADA.get());
                        anadir.accept(MainItems.CAPSULA_AZUL.get());
                        anadir.accept(MainItems.CAPSULA_MORADA.get());


                    })

                    .build());
    RegistryObject<CreativeModeTab> ESFERAS_TAB = CREATIVE_MODE_TABS.register("esferas_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MainItems.DBALL1_BLOCK_ITEM.get()))

                    .title(Component.translatable("creativetab.esferastab"))

                    .displayItems((wa, anadir) -> {
                        //BLOQUES
                        anadir.accept(MainItems.DBALL1_BLOCK_ITEM.get());
                        anadir.accept(MainItems.DBALL2_BLOCK_ITEM.get());
                        anadir.accept(MainItems.DBALL3_BLOCK_ITEM.get());
                        anadir.accept(MainItems.DBALL4_BLOCK_ITEM.get());
                        anadir.accept(MainItems.DBALL5_BLOCK_ITEM.get());
                        anadir.accept(MainItems.DBALL6_BLOCK_ITEM.get());
                        anadir.accept(MainItems.DBALL7_BLOCK_ITEM.get());


                    })

                    .build());

    static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }

}
