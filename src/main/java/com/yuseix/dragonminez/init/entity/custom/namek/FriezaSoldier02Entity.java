package com.yuseix.dragonminez.init.entity.custom.namek;

import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.projectil.KiSmallBallProjectil;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class FriezaSoldier02Entity extends SoldierEntity{

    public FriezaSoldier02Entity(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0D)
                .add(Attributes.ATTACK_DAMAGE, 350.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23F).build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));

        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, NamekianEntity.class, true));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));

    }



    @Override
    public void tick() {
        super.tick();

        LivingEntity target = this.getTarget();
        if (target != null) {
            double heightDifference = target.getY() - this.getY();

            // Si el jugador está muy alto, activa el modo de vuelo
            if (heightDifference > 1.5) {
                this.setNoGravity(true);

                // Mantén la misma posición horizontal que el jugador
                double targetX = target.getX();
                double targetY = target.getY()-1.5; // Ajusta esto según la altura deseada
                double targetZ = target.getZ();

                // Configura la posición deseada en el aire con control de altura
                this.getMoveControl().setWantedPosition(targetX, targetY, targetZ, 1.0);

                // Aplica movimiento vertical para flotar
                double verticalSpeed = 0.01; // Ajusta este valor para que el movimiento sea más suave
                this.setDeltaMovement(this.getDeltaMovement().add(0, verticalSpeed, 0));

            } else {
                // Si el jugador no está tan alto, mantén a la entidad en el suelo
                this.setNoGravity(false);

            }
        } else {
            this.setNoGravity(false);
        }


    }




}