package com.yuseix.dragonminec.init;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.init.entity.custom.DinoEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.text.html.parser.Entity;

public class MainEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DragonMineC.MODID);


    public static final RegistryObject<EntityType<DinoEntity>> DINO1 =
            ENTITY_TYPES.register("dino",
                    () -> EntityType.Builder.of(DinoEntity::new, MobCategory.AMBIENT)
                            .sized(4.5f,4.8f)
                            .build(new ResourceLocation(DragonMineC.MODID,"dino").toString())
            );


    public static void register(IEventBus bus){
        ENTITY_TYPES.register(bus);
    }
}
