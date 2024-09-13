package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.entity.SpawnPlacements.Type.ON_GROUND;
import static net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING;
import static net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation.REPLACE;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MainEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES_REGISTER =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DragonMineZ.MOD_ID);

    public static final RegistryObject<EntityType<DinoEntity>> DINO1 =
            ENTITY_TYPES_REGISTER.register("dino",
                    () -> EntityType.Builder.of(DinoEntity::new, MobCategory.CREATURE)
                            .sized(4.5f, 4.8f)
                            .build(new ResourceLocation(DragonMineZ.MOD_ID, "dino").toString())
            );
    public static final RegistryObject<EntityType<KarinEntity>> MASTER_KARIN =
            ENTITY_TYPES_REGISTER.register("karin",
                    () -> EntityType.Builder.of(KarinEntity::new, MobCategory.CREATURE)
                            .sized(0.7f, 0.7f)
                            .build(new ResourceLocation(DragonMineZ.MOD_ID, "karin").toString())
            );
    public static final RegistryObject<EntityType<DendeEntity>> MASTER_DENDE =
            ENTITY_TYPES_REGISTER.register("dende",
                    () -> EntityType.Builder.of(DendeEntity::new, MobCategory.CREATURE)
                            .sized(1.1f, 2.1f)
                            .build(new ResourceLocation(DragonMineZ.MOD_ID, "dende").toString())
            );
    public static final RegistryObject<EntityType<NubeEntity>> NUBE_VOLADORA =
            ENTITY_TYPES_REGISTER.register("nube",
                    () -> EntityType.Builder.of(NubeEntity::new, MobCategory.AMBIENT)
                            .sized(1.2f, 1.2f)
                            .build(new ResourceLocation(DragonMineZ.MOD_ID, "nube").toString())
            );
    public static final RegistryObject<EntityType<ShenlongEntity>> SHENLONG =
            ENTITY_TYPES_REGISTER.register("shenlong",
                    () -> EntityType.Builder.of(ShenlongEntity::new, MobCategory.AMBIENT)
                            .sized(2.5f, 4.5f)
                            .build(new ResourceLocation(DragonMineZ.MOD_ID, "shenlong").toString())
            );

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent e) {
        e.register(MainEntity.DINO1.get(),
                ON_GROUND,
                MOTION_BLOCKING,
                Animal::checkAnimalSpawnRules,
                REPLACE);
    }

    public static void register(IEventBus bus) {
        ENTITY_TYPES_REGISTER.register(bus);
    }
}
