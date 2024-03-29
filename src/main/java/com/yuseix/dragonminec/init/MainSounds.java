package com.yuseix.dragonminec.init;

import com.yuseix.dragonminec.DragonMineC;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MainSounds {

    public static final DeferredRegister<SoundEvent> SONIDOS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DragonMineC.MODID);

    public static final RegistryObject<SoundEvent> GOLPE1 = registerSoundEvent("punch1");
    public static final RegistryObject<SoundEvent> GOLPE2 = registerSoundEvent("punch2");
    public static final RegistryObject<SoundEvent> GOLPE3 = registerSoundEvent("punch3");
    public static final RegistryObject<SoundEvent> GOLPE4 = registerSoundEvent("punch4");
    public static final RegistryObject<SoundEvent> GOLPE5 = registerSoundEvent("punch5");
    public static final RegistryObject<SoundEvent> GOLPE6 = registerSoundEvent("punch6");
    public static final RegistryObject<SoundEvent> CRITICO1 = registerSoundEvent("critic_punch1");
    public static final RegistryObject<SoundEvent> CRITICO2 = registerSoundEvent("critic_punch2");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(DragonMineC.MODID, name);
        return SONIDOS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus busEvent) {
        SONIDOS.register(busEvent);
    }
}
