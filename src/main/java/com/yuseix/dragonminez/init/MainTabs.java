package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class MainTabs {

    //Creative Mode Tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS_REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DragonMineZ.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BLOQUES_TAB = CREATIVE_TABS_REGISTER.register("dragonminez_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MainBlocks.NAMEK_BLOCK.get()))

                .title(Component.translatable("itemGroup.dragonminez.blocks"))
                .displayItems((parameters, output) -> MainBlocks.BLOCK_REGISTER.getEntries().forEach((block) -> output.accept(block.get().asItem()))).build()
    );

    public static final RegistryObject<CreativeModeTab> ITEMS_TAB = CREATIVE_TABS_REGISTER.register("dragonminez_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MainItems.POTHALA_RIGHT.get()))

                .title(Component.translatable("itemGroup.dragonminez.items"))
                .displayItems((parameters, output) -> MainItems.ITEM_REGISTER.getEntries().forEach((item) -> output.accept(item.get().asItem()))).build()
    );

    public static void register(IEventBus bus) {
        CREATIVE_TABS_REGISTER.register(bus);
    }
}
