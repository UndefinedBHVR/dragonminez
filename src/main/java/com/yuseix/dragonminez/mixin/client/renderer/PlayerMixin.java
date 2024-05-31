package com.yuseix.dragonminez.mixin.client.renderer;

import com.yuseix.dragonminez.stats.DMZCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements GeoAnimatable {

    //private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    protected PlayerMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    Player player = (Player)(Object)this;

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(DefaultAnimations.genericIdleController(this));

        DMZStatsProvider.getCap(DMZCapabilities.INSTANCE, player).ifPresent(cap -> {

            var raza = cap.getRace();

            if(raza == 0){

            }else if(raza == 1){

            }else if(raza == 2){

            } else if(raza == 3){
                controllerRegistrar.add(
                        new AnimationController<>(this, "colaimperfect", 0, this::colaBio)

                );
            }
        });

    }

    private <T extends GeoAnimatable> PlayState colaBio(AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.bioandroid.tail", Animation.LoopType.LOOP));
        } else {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.bioandroid.tail", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;

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