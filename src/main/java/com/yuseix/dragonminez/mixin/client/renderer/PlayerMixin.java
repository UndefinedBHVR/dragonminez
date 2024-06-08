package com.yuseix.dragonminez.mixin.client.renderer;

import com.yuseix.dragonminez.stats.DMZCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.cache.GeckoLibCache;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements GeoAnimatable {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected PlayerMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    Player player = (Player)(Object)this;

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "cola", 0, this::cola),
                new AnimationController<>(this, "idle", 0, this::idle)
                );

        controllerRegistrar.add(shift(this));
        controllerRegistrar.add(swimm(this));

    }

    private <T extends GeoAnimatable> PlayState cola(AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("dmz.tail", Animation.LoopType.LOOP));
        } else {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("dmz.tail", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;

    }

    private <T extends GeoAnimatable> PlayState idle(AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("move.walk", Animation.LoopType.LOOP));
        } else {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("misc.idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;

    }

    public  <T extends GeoAnimatable> AnimationController<T> shift(T animatable) {
        return new AnimationController(animatable, "sneak", 0, (state) -> {
            //return state.setAndContinue(player.isCrouching() ? (state.isMoving() ? DefaultAnimations.SNEAK : RawAnimation.begin().then("idle.shift", Animation.LoopType.LOOP)) : DefaultAnimations.IDLE);
            return (player.isCrouching() ? (state.isMoving() ? state.setAndContinue(DefaultAnimations.SNEAK) : state.setAndContinue(RawAnimation.begin().then("idle.shift", Animation.LoopType.LOOP))) : PlayState.STOP);
        });
    }
    public  <T extends GeoAnimatable> AnimationController<T> swimm(T animatable) {
        return new AnimationController(animatable, "swim", 5, (state) -> {
            //return state.setAndContinue(player.isSwimming() ? (state.isMoving() ? DefaultAnimations.SWIM : DefaultAnimations.IDLE) : DefaultAnimations.IDLE);
            return (player.isSwimming() ? (state.isMoving() ? state.setAndContinue(DefaultAnimations.SWIM) : state.setAndContinue(DefaultAnimations.IDLE)) : PlayState.STOP);
        });
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object o) {
        return this.tickCount;
    }

}