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
                            if (!block.getId().getPath().startsWith("sacred_")) {
                                if (!block.getId().getPath().endsWith("_flower")) {
                                    if (!block.getId().getPath().startsWith("potted_")) {
                                        if (!(block.get() instanceof LiquidBlock)) {
                                            output.accept(block.get().asItem());
                                        }
                                    }
                                }
                            }
                        }
                    })).build()
    );

    public static final RegistryObject<CreativeModeTab> NAMEK_TAB = CREATIVE_TABS_REGISTER.register("dragonminez_namek_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MainBlocks.NAMEK_GRASS_BLOCK.get()))

                    .title(Component.translatable("itemGroup.dragonminez.namek"))
                    .displayItems((parameters, output) -> MainItems.ITEM_REGISTER.getEntries().forEach((item) -> {
                        if (item.getId().getPath().startsWith("namek_"))
                            output.accept(item.get().asItem());
                        if (item.getId().getPath().startsWith("sacred_"))
                            output.accept(item.get().asItem());
                        output.accept(MainBlocks.CHRYSANTHEMUM_FLOWER.get().asItem());
                        output.accept(MainBlocks.AMARYLLIS_FLOWER.get().asItem());
                        output.accept(MainBlocks.MARIGOLD_FLOWER.get().asItem());
                        output.accept(MainBlocks.CATHARANTHUS_ROSEUS_FLOWER.get().asItem());
                        output.accept(MainBlocks.TRILLIUM_FLOWER.get().asItem());
                        output.accept(MainBlocks.LOTUS_FLOWER.get().asItem());
                    })).build()
    );

    public static final RegistryObject<CreativeModeTab> ITEMS_TAB = CREATIVE_TABS_REGISTER.register("dragonminez_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MainItems.POTHALA_RIGHT.get()))

                    .title(Component.translatable("itemGroup.dragonminez.items"))
                    .displayItems((parameters, output) -> MainItems.ITEM_REGISTER.getEntries().forEach((item) -> {
                        if (!(item.get() instanceof BlockItem)) {
                            if (!item.getId().getPath().contains("_armor_")) {
                                output.accept(item.get().asItem());
                            }
                        }
                    })).build()
    );

    public static final RegistryObject<CreativeModeTab> ARMORS_TAB =  CREATIVE_TABS_REGISTER.register("dragonminez_armors_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MainItems.BARDOCK_DBZ_ARMOR_CHESTPLATE.get()))

                    .title(Component.translatable("itemGroup.dragonminez.armors"))
                    .displayItems((parameters, output) -> MainItems.ITEM_REGISTER.getEntries().forEach((item) -> {
                        if (item.getId().getPath().contains("_armor_")) {
                            if (!item.getId().getPath().equals("kikono_armor_station")) {
                                output.accept(item.get().asItem());
                            }
                        }
                    })).build()
    );

    public static final RegistryObject<CreativeModeTab> ORES_TAB = CREATIVE_TABS_REGISTER.register("dragonminez_ores_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MainBlocks.GETE_ORE.get()))

                    .title(Component.translatable("itemGroup.dragonminez.ores"))
                    .displayItems((parameters, output) -> MainBlocks.BLOCK_REGISTER.getEntries().forEach((block) -> {
                        if (block.getId().getPath().contains("_ore")) {
                            output.accept(block.get().asItem());
                            output.accept(MainBlocks.GETE_FURNACE.get().asItem());
                            output.accept(MainBlocks.KIKONO_BLOCK.get().asItem());
                            output.accept(MainBlocks.KIKONO_ARMOR_STATION.get().asItem());
                            output.accept(MainItems.KIKONO_SHARD.get().asItem());
                            output.accept(MainItems.KIKONO_CLOTH.get().asItem());
                            output.accept(MainItems.KIKONO_STRING.get().asItem());
                            output.accept(MainItems.GETE_INGOT.get().asItem());
                            output.accept(MainItems.GETE_SCRAP.get().asItem());
                        }
                    })).build()
    );

    public static void register(IEventBus bus) {
        CREATIVE_TABS_REGISTER.register(bus);
    }
}
