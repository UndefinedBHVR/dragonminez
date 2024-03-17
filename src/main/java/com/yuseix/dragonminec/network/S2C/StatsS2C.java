package com.yuseix.dragonminec.network.S2C;

import com.yuseix.dragonminec.client.ClientPlayerStats;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StatsS2C {

    private int strength;
    private int defense;
    private int constitution;
    private int kiPower;
    private int energy;

    public StatsS2C(int str, int def, int con, int ki, int energy) {
        this.strength = str;
        this.defense = def;
        this.constitution = con;
        this.kiPower = ki;
        this.energy = energy;
    }

    public StatsS2C(FriendlyByteBuf buf) {
        strength = buf.readInt();
        defense = buf.readInt();
        constitution = buf.readInt();
        kiPower = buf.readInt();
        energy = buf.readInt();

    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(strength);
        buf.writeInt(defense);
        buf.writeInt(constitution);
        buf.writeInt(kiPower);
        buf.writeInt(energy);

    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {

            ClientPlayerStats.setStrenght(strength);
            ClientPlayerStats.setDefense(defense);
            ClientPlayerStats.setConstitution(constitution);
            ClientPlayerStats.setKipower(kiPower);
            ClientPlayerStats.setEnergy(energy);

        });
        return true;
    }
}
