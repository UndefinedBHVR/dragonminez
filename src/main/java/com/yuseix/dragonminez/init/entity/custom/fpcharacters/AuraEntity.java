package com.yuseix.dragonminez.init.entity.custom.fpcharacters;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.UUID;

public class AuraEntity extends Mob{

    private static final EntityDataAccessor<Integer> COLOR_AURA = SynchedEntityData.defineId(AuraEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TRANSFORMATION = SynchedEntityData.defineId(AuraEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> RAZA = SynchedEntityData.defineId(AuraEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TIPO_AURA = SynchedEntityData.defineId(AuraEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Float> TRANSPARENCIA = SynchedEntityData.defineId(AuraEntity.class, EntityDataSerializers.FLOAT);

    private UUID ownerUUID;

    public AuraEntity(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
        this.noPhysics = true;
        this.entityData.define(COLOR_AURA, 0); // Color predeterminado (negro)
        this.entityData.define(TRANSFORMATION, 0); // Forma por defecto
        this.entityData.define(RAZA, 0); // RAZA por defecto
        this.entityData.define(TIPO_AURA, 0); // Tipo de Ki
        this.entityData.define(TRANSPARENCIA, 0.15f); // Transparencia por defecto

    }

    public int getColorAura() {
        return this.entityData.get(COLOR_AURA);
    }

    public void setColorAura(int colorAura) {
        this.entityData.set(COLOR_AURA, colorAura);
    }
    public int getTransformation() {
        return this.entityData.get(TRANSFORMATION);
    }

    public void setTransformation(int transformation) {
        this.entityData.set(TRANSFORMATION, transformation);
    }
    public int getRaza() {
        return this.entityData.get(RAZA);
    }

    public void setRaza(int raza) {
        this.entityData.set(RAZA, raza);
    }
    public int getTipoAura() {
        return this.entityData.get(TIPO_AURA);
    }

    public void setTipoAura(int tipo_aura) {
        this.entityData.set(TIPO_AURA, tipo_aura);
    }
    public float getTransparencia() {
        return this.entityData.get(TRANSPARENCIA);
    }

    public void setTransparencia(float transparencia) {
        this.entityData.set(TRANSPARENCIA, transparencia);
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.18F).build();
    }

    public void setOwnerUUID(UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
    }

    @Override
    public void tick() {
        super.tick();

    }

    @Override
    public boolean isPushable() {
        return false; // Desactiva la capacidad de empujar y ser empujada
    }

    @Override
    protected void registerGoals() {

    }

    @Override
    public boolean canCollideWith(Entity pEntity) {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false; // No permite que esta entidad sea colisionada por bloques
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    public AABB getBoundingBoxForCulling() {
        return super.getBoundingBoxForCulling();
    }
}
