package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DMZPermanentEffectsSyncS2C {
	private final Map<String, Boolean> effects;

	// Constructor para enviar datos
	public DMZPermanentEffectsSyncS2C(Map<String, Boolean> effects) {
		this.effects = new HashMap<>(effects); // Copia los datos
	}

	// Constructor para recibir datos
	public DMZPermanentEffectsSyncS2C(FriendlyByteBuf buf) {
		int size = buf.readInt();
		effects = new HashMap<>();
		for (int i = 0; i < size; i++) {
			String key = buf.readUtf(); // Lee el nombre del efecto
			boolean value = buf.readBoolean(); // Lee el estado (true/false)
			effects.put(key, value);
		}
	}

	// Escribe los datos en el buffer
	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(effects.size());
		for (Map.Entry<String, Boolean> entry : effects.entrySet()) {
			buf.writeUtf(entry.getKey());
			buf.writeBoolean(entry.getValue());
		}
	}

	// Manejo del packet
	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
					ClientPacketHandler.handlePermanentEffectsPacket(effects, ctxSupplier)
			);
		});
		ctxSupplier.get().setPacketHandled(true);
	}
}