package com.yuseix.dragonminec;

import com.mojang.logging.LogUtils;
import com.yuseix.dragonminec.client.gui.AttributesMenu;
import com.yuseix.dragonminec.config.DMCAttrConfig;
import com.yuseix.dragonminec.init.MainSounds;
import com.yuseix.dragonminec.init.Mainblocks;
import com.yuseix.dragonminec.init.Mainitems;
import com.yuseix.dragonminec.init.Maintabs;
import com.yuseix.dragonminec.network.ModMessages;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(DragonMineC.MODID)
public class DragonMineC {

    public static final String MODID = "dragonminec";

    private static final Logger LOGGER = LogUtils.getLogger();

    public DragonMineC()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        Mainitems.register(modEventBus);

        Maintabs.register(modEventBus);

        Mainblocks.register(modEventBus);

        MainSounds.register(modEventBus);

        GeckoLib.shadowInit();

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DMCAttrConfig.SPEC,"dragonminec-common.toml");

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            ModMessages.register();
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
