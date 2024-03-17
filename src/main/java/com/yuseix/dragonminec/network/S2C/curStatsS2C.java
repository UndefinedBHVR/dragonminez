package com.yuseix.dragonminec.network.S2C;

import com.yuseix.dragonminec.client.ClientPlayerStats;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class curStatsS2C {

    private int curEnergy;
    private int curBody;
    private int curStam;
    private int stamina;

    public curStatsS2C(int curEnergy, int curBody, int curStam, int stamina) {
        this.curEnergy = curEnergy;
        this.curBody = curBody;
        this.curStam = curStam;
        this.stamina = stamina;

    }

    public curStatsS2C(FriendlyByteBuf buf) {
        curEnergy = buf.readInt();
        curBody = buf.readInt();
        curStam = buf.readInt();
        stamina = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(curEnergy);
        buf.writeInt(curBody);
        buf.writeInt(curStam);
        buf.writeInt(stamina);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {

            ClientPlayerStats.setCurEnergy(curEnergy);
            ClientPlayerStats.setCurBody(curBody);
            ClientPlayerStats.setCurStam(curStam);
            ClientPlayerStats.setStamina(stamina);


        });
        return true;
    }

}
