package com.yuseix.dragonminez.mixin.client.renderer;

import com.yuseix.dragonminez.events.ModEvents;
import com.yuseix.dragonminez.stats.PlayerStatsAttrProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity implements GeoAnimatable {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    Player player = (Player) (Object) this;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                DefaultAnimations.genericWalkController(this),
                DefaultAnimations.genericIdleController(this)
                );


        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, player).ifPresent(cap -> {

            if(cap.getRace() == 0){
                System.out.println("Funciona la animacion");
                //controllers.add(new AnimationController<>(this, "tailBio", 0, this::cola));
            }else if(cap.getRace() == 1){
                controllers.add(new AnimationController<>(this, "tailS", 0, this::colaS));
            }else if(cap.getRace() == 3){
                controllers.add(new AnimationController<>(this, "tailBio", 0, this::cola));
            }

        });


    }

    private <T extends GeoAnimatable> PlayState cola(AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.bioandroid.tail", Animation.LoopType.LOOP));
        } else {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.bioandroid.tail", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }
    private <T extends GeoAnimatable> PlayState colaS(AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.saiyanrace.tail", Animation.LoopType.LOOP));
        } else {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.saiyanrace.tail", Animation.LoopType.LOOP));
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