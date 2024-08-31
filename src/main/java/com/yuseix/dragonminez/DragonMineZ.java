package com.yuseix.dragonminez;

import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.events.ForgeBusEvents;
import com.yuseix.dragonminez.events.ModBusEvents;
import com.yuseix.dragonminez.init.*;
import com.yuseix.dragonminez.init.entity.custom.DinoEntity;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import com.yuseix.dragonminez.worldgen.biome.ModBiomes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.ModLoadingStage;
import net.minecraftforge.fml.ModLoadingWarning;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.IModInfo;
import software.bernie.geckolib.GeckoLib;

@Mod(DragonMineZ.MOD_ID)
public class DragonMineZ {

    public static final String MOD_ID = "dragonminez";

    public DragonMineZ() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //Registramos Items
        MainItems.register(modEventBus);
        //Registramos Bloques
        MainBlocks.register(modEventBus);
        //Registramos la nueva TAB del Creativo
        MainTabs.register(modEventBus);
        //Registramos las entidades de los bloques
        MainBlockEntities.register(modEventBus);
        //Registramos los sonidos
        MainSounds.register(modEventBus);
        //Registramos las entidades
        MainEntity.register(modEventBus);
        //Registramos los NPCs (Puntos de Interés y Profesiones)
        MainNPCs.register(modEventBus);
        //Registramos los Fluidos (Tipo de Fluido y Fluido/s)
        MainFluids.register(modEventBus);


        MinecraftForge.EVENT_BUS.register(this);

        //Registramos el Listener del Mod (Normalmente eventos de Forge y FML más como frontend, realmente son los eventos de renderizado y más cosas de cliente)
        modEventBus.register(new ModBusEvents());
        //Registramos el Listener de Forge (Eventos de Forge que van más allá del juego como backend, conocido como ModEvents)
        MinecraftForge.EVENT_BUS.register(new ForgeBusEvents());
        //Se registran los eventos de las Capabilities de las Stats
        MinecraftForge.EVENT_BUS.register(new DMZStatsCapabilities());

        GeckoLib.initialize();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DMCAttrConfig.SPEC, "dragonminez-common.toml");

        // Añade una advertencia al cargar el mod si el usuario no está en la lista de usuarios permitidos para testear el mod.
        /*
        IModInfo modInfo = ModLoadingContext.get().getActiveContainer().getModInfo();
        ModLoadingWarning modLoadingWarning = new ModLoadingWarning(modInfo, ModLoadingStage.CONSTRUCT,
                """
                        DragonMineZ:
                        Only the official DMZ development team is allowed to play the mod.
                        If you are not a member of this team, the mod will cause an error.

                        This mod is in a development phase! Expect bugs, errors and crashes!

                        Proceed with caution!""");
        ModLoader.get().addWarning(modLoadingWarning);

         */
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            ModMessages.register();

        });
    }



}
