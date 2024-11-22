package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DMZTempEffectsS2C {
	private final Map<String, Integer> tempEffects;

	public DMZTempEffectsS2C(Map<String, Integer> tempEffects) {
		this.tempEffects = tempEffects;
	}

	// Constructor para recibir los datos del buffer
	public DMZTempEffectsS2C(FriendlyByteBuf buf) {
		int size = buf.readInt();
		this.tempEffects = new HashMap<>();
		for (int i = 0; i < size; i++) {
			String effect = buf.readUtf();
			int tiempo = buf.readInt();
			tempEffects.put(effect, tiempo);
		}
	}

	// Método para escribir los datos al buffer
	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(tempEffects.size());
		for (Map.Entry<String, Integer> entry : tempEffects.entrySet()) {
			buf.writeUtf(entry.getKey());
			buf.writeInt(entry.getValue());
		}
	}

	// Método para procesar el paquete en el lado del cliente
	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(
					Dist.CLIENT, () -> () -> ClientPacketHandler.handleTempEffectsPacket(tempEffects, ctxSupplier)
			);
		});
		ctxSupplier.get().setPacketHandled(true);
	}
}
