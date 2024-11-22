package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DMZPermanentEffectsSyncS2C {
    private final Map<String, Boolean> permanentEffects;

    public DMZPermanentEffectsSyncS2C(Map<String, Boolean> permanentEffects) {
        this.permanentEffects = permanentEffects;
    }

    // Constructor para recibir los datos del buffer
    public DMZPermanentEffectsSyncS2C(FriendlyByteBuf buf) {
        int size = buf.readInt();
        this.permanentEffects = new HashMap<>();
        for (int i = 0; i < size; i++) {
            String effect = buf.readUtf();
            boolean isActive = buf.readBoolean();
            permanentEffects.put(effect, isActive);
        }
    }

    // Método para escribir los datos al buffer
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(permanentEffects.size());
        for (Map.Entry<String, Boolean> entry : permanentEffects.entrySet()) {
            buf.writeUtf(entry.getKey());
            buf.writeBoolean(entry.getValue());
        }
    }

    // Método para procesar el paquete en el lado del cliente
    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            // Obtener la capacidad del jugador en el cliente y actualizar los efectos permanentes
            var player = Minecraft.getInstance().player;
            if (player != null) {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                    cap.getDMZPermanentEffects().clear(); // Limpiar efectos actuales
                    cap.getDMZPermanentEffects().putAll(permanentEffects); // Actualizar efectos
                });
            }
        });
        context.get().setPacketHandled(true);
    }
}