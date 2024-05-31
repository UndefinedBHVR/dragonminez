package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.commands.StatsCommand;
import com.yuseix.dragonminez.commands.ZPointsCommand;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.DinoEntity;
import com.yuseix.dragonminez.init.entity.custom.FakeBioAndroidEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new ZPointsCommand(event.getDispatcher());
        new StatsCommand(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }

    @Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {

        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(MainEntity.DINO1.get(), DinoEntity.setAttributes());
            event.put(MainEntity.FAKEBIOANDROID1.get(), FakeBioAndroidEntity.setAttributes());
        }

    }
}
