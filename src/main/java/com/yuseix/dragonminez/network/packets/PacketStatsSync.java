package com.yuseix.dragonminez.network.packets;

import com.yuseix.dragonminez.network.Packet;
import com.yuseix.dragonminez.utils.ClientPlayerStats;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class PacketStatsSync implements Packet {

    private int races;
    private int hairID, bodytype, eyesType;
    private int strength;
    private int defense;
    private int constitution, curBody, curStam, stamina;
    private int KiPower;
    private int energy, currentEnergy;
    private int bodyColor;

    public PacketStatsSync() {
    }

    public PacketStatsSync(int races, int hairID, int bodytype, int eyesType, int strength, int defense, int constitution, int curBody, int curStam, int stamina, int KiPower, int energy, int currentEnergy, int bodyColor) {
        this.races = races;
        this.hairID = hairID;
        this.bodytype = bodytype;
        this.eyesType = eyesType;
        this.strength = strength;
        this.defense = defense;
        this.constitution = constitution;
        this.curBody = curBody;
        this.curStam = curStam;
        this.stamina = stamina;
        this.KiPower = KiPower;
        this.energy = energy;
        this.currentEnergy = currentEnergy;
        this.bodyColor = bodyColor;
    }

    @Override
    public void read(FriendlyByteBuf buf) {
        races = buf.readInt();
        hairID = buf.readInt();
        bodytype = buf.readInt();
        eyesType = buf.readInt();
        strength = buf.readInt();
        defense = buf.readInt();
        constitution = buf.readInt();
        curBody = buf.readInt();
        curStam = buf.readInt();
        stamina = buf.readInt();
        KiPower = buf.readInt();
        energy = buf.readInt();
        currentEnergy = buf.readInt();
        bodyColor = buf.readInt();
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeInt(races);
        buf.writeInt(hairID);
        buf.writeInt(bodytype);
        buf.writeInt(eyesType);
        buf.writeInt(strength);
        buf.writeInt(defense);
        buf.writeInt(constitution);
        buf.writeInt(curBody);
        buf.writeInt(curStam);
        buf.writeInt(stamina);
        buf.writeInt(KiPower);
        buf.writeInt(energy);
        buf.writeInt(currentEnergy);
        buf.writeInt(bodyColor);
    }

    //En este método, player SIEMPRE es null, ya que en Packets Handler, automáticamente ya se tiene el jugador
    //O sea que se procesa el jugador en el método handle de Packets Handler, y no acá.
    @Override
    public void handle(ServerPlayer player) {
        ClientPlayerStats.races = this.races;
        ClientPlayerStats.hairID = this.hairID;
        ClientPlayerStats.bodytype = this.bodytype;
        ClientPlayerStats.eyesType = this.eyesType;
        ClientPlayerStats.strength = this.strength;
        ClientPlayerStats.defense = this.defense;
        ClientPlayerStats.constitution = this.constitution;
        ClientPlayerStats.curBody = this.curBody;
        ClientPlayerStats.curStam = this.curStam;
        ClientPlayerStats.stamina = this.stamina;
        ClientPlayerStats.KiPower = this.KiPower;
        ClientPlayerStats.energy = this.energy;
        ClientPlayerStats.currentEnergy = this.currentEnergy;
        ClientPlayerStats.bodyColor = this.bodyColor;
    }
}