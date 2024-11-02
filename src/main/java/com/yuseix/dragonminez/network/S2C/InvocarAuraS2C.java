package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.init.entity.custom.fpcharacters.AuraEntity;
import com.yuseix.dragonminez.network.C2S.InvocarAuraC2S;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class InvocarAuraS2C {
    private final UUID playerId;
    private final float transparency;

    public InvocarAuraS2C(UUID playerId, float transparency) {
        this.playerId = playerId;
        this.transparency = transparency;
    }

    public InvocarAuraS2C(FriendlyByteBuf buf) {
        this.playerId = buf.readUUID();
        this.transparency = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUUID(playerId);
        buf.writeFloat(transparency);
    }

    public static void handle(InvocarAuraS2C packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            // Aquí puedes manejar la lógica para aplicar la transparencia
            // Por ejemplo, actualiza el aura correspondiente con el nuevo valor de transparencia
            AuraEntity aura = InvocarAuraC2S.playerAuraMap.get(packet.playerId);
            if (aura != null) {
                aura.setTransparencia(packet.transparency);
            }
        });
        context.setPacketHandled(true);
    }
}