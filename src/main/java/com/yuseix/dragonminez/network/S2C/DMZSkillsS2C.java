package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DMZSkillsS2C {
    private final Map<String, Integer> skillsdmz;

    public DMZSkillsS2C(Map<String, Integer> skillsdmz) {
        this.skillsdmz = skillsdmz;
    }

    // Constructor para recibir los datos del buffer
    public DMZSkillsS2C(FriendlyByteBuf buf) {
        int size = buf.readInt();
        this.skillsdmz = new HashMap<>();
        for (int i = 0; i < size; i++) {
            String effect = buf.readUtf();
            int nivel = buf.readInt();
            skillsdmz.put(effect, nivel);
        }
    }

    // Método para escribir los datos al buffer
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(skillsdmz.size());
        for (Map.Entry<String, Integer> entry : skillsdmz.entrySet()) {
            buf.writeUtf(entry.getKey());
            buf.writeInt(entry.getValue());
        }
    }

    // Método para procesar el paquete en el lado del cliente
    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            // Obtener la capacidad del jugador en el cliente y actualizar los efectos permanentes
            var player = net.minecraft.client.Minecraft.getInstance().player;
            if (player != null) {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                    cap.getDMZSkills().clear(); // Limpiar efectos actuales
                    cap.getDMZSkills().putAll(skillsdmz); // Actualizar efectos
                });
            }
        });
        context.get().setPacketHandled(true);
    }
}