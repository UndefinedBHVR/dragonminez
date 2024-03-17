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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Mainitems.ESFERA_DRAGON_1.get()))

                    .title(Component.translatable("creativetab.dragonminectab"))

                    .displayItems((wa, anadir) -> {
                        //BLOQUES
                        anadir.accept(Mainblocks.NAMEK_BLOCK.get());
                        anadir.accept(Mainblocks.NAMEK_GRASS_BLOCK.get());
                        anadir.accept(Mainblocks.NAMEK_DIRT.get());
                        //ITEMS
                        anadir.accept(Mainitems.ESFERA_DRAGON_1.get());



                    })

                    .build());

    public static void register(IEventBus bus){
        CREATIVE_MODE_TABS.register(bus);
    }

}
