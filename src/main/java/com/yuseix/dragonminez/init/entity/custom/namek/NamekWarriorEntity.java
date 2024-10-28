package com.yuseix.dragonminez.init.entity.custom.namek;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.level.Level;

public class NamekWarriorEntity extends PathfinderMob {
    public NamekWarriorEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2000.0D)
                .add(Attributes.ATTACK_DAMAGE, 500.5f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.22F).build();
    }

    @Override
    protected void registerGoals() {
        // Permite que la entidad flote en el agua
        this.goalSelector.addGoal(1, new FloatGoal(this));

        // Ataque cuerpo a cuerpo: solo ataca si es provocado
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));

        // Objetivo de ataque: atacará solo al ser dañado
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

        // Camina de manera aleatoria evitando el agua
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        // Mirada aleatoria
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }
}
