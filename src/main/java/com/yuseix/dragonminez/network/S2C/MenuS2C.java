package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.client.gui.AttributesMenu;
import com.yuseix.dragonminez.client.gui.cc.CFirstPage;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MenuS2C {

    private final boolean openCharacterMenu;

    public MenuS2C(boolean isConfirmCharacter) {
        this.openCharacterMenu = isConfirmCharacter;
    }
    public MenuS2C(FriendlyByteBuf buf) {
        this.openCharacterMenu = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(openCharacterMenu);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                if (openCharacterMenu) {
                    //Si el jugador ya creo su personaje
                    Minecraft.getInstance().setScreen(new AttributesMenu(Component.translatable("menu.title.dragonminez.menuzmzmzm")));
                } else {
                    //Si el jugador aun no creo su personaje
                    Minecraft.getInstance().setScreen(new CFirstPage());

                }
            });
        });
        return true;
    }

}
