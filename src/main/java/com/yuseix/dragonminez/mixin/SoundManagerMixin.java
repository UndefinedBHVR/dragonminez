package com.yuseix.dragonminez.mixin;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundManager.class)
public class SoundManagerMixin {

    @Unique
    private static final ResourceLocation MENU_MUSIC_RESOURCE = new ResourceLocation("minecraft", "music.menu");

    @Inject(method = "play", at = @At("HEAD"), cancellable = true)
    private void onPlay(SoundInstance pSound, CallbackInfo ci) {
        // Compare the sound instance's location with the default menu music location
        if (pSound.getLocation().equals(MENU_MUSIC_RESOURCE)) {
            ci.cancel();
            System.out.println("Musica default MC cancelada");
        }
    }
}