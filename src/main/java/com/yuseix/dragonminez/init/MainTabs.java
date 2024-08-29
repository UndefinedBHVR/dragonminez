package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public final class MainTabs {

    //Creative Mode Tabs
    //TODO: Separar las pestañas en más pestañas y crear checks para que no se muestren dos veces accidentalmente
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS_REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DragonMineZ.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BLOQUES_TAB = CREATIVE_TABS_REGISTER.register("dragonminez_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MainBlocks.DBALL4_BLOCK.get()))

                    .title(Component.translatable("itemGroup.dragonminez.blocks"))
                    .displayItems((parameters, output) -> MainBlocks.BLOCK_REGISTER.getEntries().forEach((block) -> {
                        if (!block.getId().getPath().startsWith("namek_")) {
                            if (!(block.get() instanceof LiquidBlock))
                                output.accept(block.get().asItem());
                        }
                    })).build()
    );

    public static final RegistryObject<CreativeModeTab> NAMEK_TAB = CREATIVE_TABS_REGISTER.register("dragonminez_namek_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MainBlocks.NAMEK_GRASS_BLOCK.get()))

                    .title(Component.translatable("itemGroup.dragonminez_namek_tab"))
                    .displayItems((parameters, output) -> MainItems.ITEM_REGISTER.getEntries().forEach((item) -> {
                        if (item.getId().getPath().startsWith("namek_"))
                            output.accept(item.get().asItem());
                    })).build()
    );

    public static final RegistryObject<CreativeModeTab> ITEMS_TAB = CREATIVE_TABS_REGISTER.register("dragonminez_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MainItems.POTHALA_RIGHT.get()))

                    .title(Component.translatable("itemGroup.dragonminez.items"))
                    .displayItems((parameters, output) -> MainItems.ITEM_REGISTER.getEntries().forEach((item) -> {
                        if (!(item.get() instanceof BlockItem))
                            output.accept(item.get().asItem());
                    })).build()
    );

    public static final RegistryObject<CreativeModeTab> ORES_TAB = CREATIVE_TABS_REGISTER.register("dragonminez_ores_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MainBlocks.GETE_ORE.get()))

                    .title(Component.translatable("itemGroup.dragonminez.ores"))
                    .displayItems((parameters, output) -> {
                        //Añadir más items a la lista
                        output.accept(MainBlocks.GETE_ORE.get().asItem());
                        output.accept(MainItems.GETE_SCRAP.get());
                        output.accept(MainBlocks.GETE_FURNACE.get().asItem());
                        output.accept(MainBlocks.NAMEK_KIKONO_ORE.get());
                        output.accept(MainItems.KIKONO_SHARD.get());
                        output.accept(MainBlocks.KIKONO_BLOCK.get());
                   //     output.accept(MainBlocks.KIKONO_ARMOR_STATION.get());
                    }).build()
    );

    public static void register(IEventBus bus) {
        CREATIVE_TABS_REGISTER.register(bus);
    }
}
