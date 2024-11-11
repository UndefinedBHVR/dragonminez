package com.yuseix.dragonminez.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dragonminez", value = Dist.CLIENT)
public class TitleUtils {
    private static final String title = "DragonMine Z - Release v1.0.0";

    @SubscribeEvent
    public static void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.RenderTickEvent.Phase.END) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.getWindow() != null) {
                minecraft.getWindow().setTitle(title);
            }
        }
    }
}
