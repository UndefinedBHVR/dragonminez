package com.yuseix.dragonminez;

import com.mojang.logging.LogUtils;
import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.init.*;
import com.yuseix.dragonminez.init.blocks.entity.MainBlockEntities;
import com.yuseix.dragonminez.init.blocks.entity.client.*;
import com.yuseix.dragonminez.init.entity.client.renderer.DinoRenderer;
import com.yuseix.dragonminez.init.entity.client.renderer.FakeBioAndroidRenderer;
import com.yuseix.dragonminez.network.ModMessages;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.listener.ForgeListener;
import com.yuseix.dragonminez.listener.ModListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.ModLoadingStage;
import net.minecraftforge.fml.ModLoadingWarning;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.IModInfo;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.core.molang.LazyVariable;
import software.bernie.geckolib.core.molang.MolangParser;
import software.bernie.geckolib.core.molang.MolangQueries;

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

        modEventBus.register(new ModListener());
        MinecraftForge.EVENT_BUS.register(new ForgeListener());

        GeckoLib.initialize();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DMCAttrConfig.SPEC, "dragonminez-common.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            SpawnPlacements.register(MainEntity.DINO1.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules);

            ModMessages.register();
        });

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
      
      // Añade una advertencia al cargar el mod si el usuario no está en la lista de usuarios permitidos para testear el mod.
        IModInfo modInfo = ModLoadingContext.get().getActiveContainer().getModInfo();
        ModLoadingWarning modLoadingWarning = new ModLoadingWarning(modInfo, ModLoadingStage.CONSTRUCT,
                """
                        DragonMineZ:
                        Only the official DMZ development team is allowed to play the mod.
                        If you are not a member of this team, the mod will cause an error.

                        This mod is in a development phase! Expect bugs, errors and crashes!

                        Proceed with caution!""");
        ModLoader.get().addWarning(modLoadingWarning);
                                                                    
    }
}
