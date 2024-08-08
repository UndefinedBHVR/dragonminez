package com.yuseix.dragonminez.init.entity.custom;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;

public class NubeEntity extends Mob implements GeoEntity {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public NubeEntity(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.05D).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if (!this.level().isClientSide) {
            pPlayer.startRiding(this);
        }
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isVehicle() && this.getControllingPassenger() instanceof Player) {
            Player player = (Player) this.getControllingPassenger();

            float velocidad = 0.42f;
            // Obtener los valores de movimiento del jugador
            double strafe = player.xxa * velocidad;
            double forward = player.zza * velocidad;

            // Ajustar la velocidad de movimiento
            this.setSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED) * velocidad);

            // Aplicar movimiento
            Vec3 movement = new Vec3(strafe, player.yya * velocidad, forward);

            // Rotar la cabeza y el cuerpo del mob hacia la dirección en la que está mirando el jugador
            this.yRotO = player.yRotO;
            this.setYRot(player.getYRot());
            this.xRotO = player.xRotO;
            this.setXRot(player.getXRot());

            if (this.isControlledByLocalInstance()) {
                this.moveRelative(this.getSpeed(), movement);
                this.move(MoverType.SELF, this.getDeltaMovement());
            } else {
                super.travel(pTravelVector);
            }
        } else {
            super.travel(pTravelVector);
        }
    }

    @Override
    @Nullable
    public LivingEntity getControllingPassenger() {
        if (this.getFirstPassenger() instanceof LivingEntity) {
            return (LivingEntity) this.getFirstPassenger();
        } else {
            return null;
        }
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.getEntity() instanceof Player) {
            if (!this.level().isClientSide) {
                //Aca poner el item que va a dropear
                this.spawnAtLocation(Items.DIAMOND);
                this.remove(RemovalReason.KILLED);
            }
            return true;
        }
        return super.hurt(pSource, pAmount);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.COW_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.COW_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    }



    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
