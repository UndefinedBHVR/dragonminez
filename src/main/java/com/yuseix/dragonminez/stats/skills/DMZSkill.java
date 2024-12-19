package com.yuseix.dragonminez.stats.skills;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;

public class DMZSkill {

    private Component name;
    private Component desc;
    private int level;
    private boolean isActive;

    public DMZSkill(Component name, Component desc, int level) {
        this.name = name;
        this.desc = desc;
        this.level = level;
    }

    public DMZSkill(Component name, Component desc, int level, boolean isActive) {
        this.name = name;
        this.desc = desc;
        this.level = level;
        this.isActive = isActive;
    }
    // Constructor para leer desde el buffer
    public DMZSkill(FriendlyByteBuf buf) {
        this.name = buf.readComponent();
        this.desc = buf.readComponent();
        this.level = buf.readInt();
        this.isActive = buf.readBoolean();
    }

    // Método para escribir en el buffer
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeComponent(name);
        buf.writeComponent(desc);
        buf.writeInt(level);
        buf.writeBoolean(isActive);
    }

    public Component getName() {
        return name;
    }

    public void setName(Component name) {
        this.name = name;
    }

    public Component getDesc() {
        return desc;
    }

    public void setDesc(Component desc) {
        this.desc = desc;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
