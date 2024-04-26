package com.yuseix.dragonminez.character.models.bioandroid;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoReplacedEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class GeoBioAndroidPlayer extends LivingEntity implements GeoReplacedEntity {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private Player player;

    public GeoBioAndroidPlayer(){
        super(EntityType.PLAYER, null);
    }


    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return null;
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot equipmentSlot) {
        return null;
    }

    @Override
    public void setItemSlot(EquipmentSlot equipmentSlot, ItemStack itemStack) {

    }

    @Override
    public HumanoidArm getMainArm() {
        return null;
    }

    @Override
    public EntityType<?> getReplacingEntityType() {
        return EntityType.PLAYER;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,"controller",0,this::predicate));
        controllerRegistrar.add(new AnimationController<>(this,"shift",0,this::shifting));
        controllerRegistrar.add(new AnimationController<>(this,"swimm",0,this::swimming));
        controllerRegistrar.add(new AnimationController<>(this,"idle",0,this::idle));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.bioandroid.tail", Animation.LoopType.LOOP));
        } else {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.bioandroid.tail", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }

    private <T extends GeoAnimatable> PlayState idle(AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.bioandroid.walk", Animation.LoopType.LOOP));
        } else {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.bioandroid.idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }
    private <T extends GeoAnimatable> PlayState shifting(AnimationState<T> tAnimationState) {

        if(this.isCrouching()){
            System.out.println("esta webada cargo");
            if(tAnimationState.isMoving()){
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.bioandroid.shiftingmove", Animation.LoopType.LOOP));
            } else {
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.bioandroid.shifting", Animation.LoopType.LOOP));
            }
        }

        return PlayState.CONTINUE;
    }

    private <T extends GeoAnimatable> PlayState swimming(AnimationState<T> tAnimationState) {

        if(this.isSwimming()){
            if(tAnimationState.isMoving()){
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.bioandroid.swimmingmove", Animation.LoopType.LOOP));
            } else {
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.bioandroid.swimming", Animation.LoopType.LOOP));
            }
        }

        return PlayState.CONTINUE;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
