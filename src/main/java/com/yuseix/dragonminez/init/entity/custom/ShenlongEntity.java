package com.yuseix.dragonminez.init.entity.custom;

import com.yuseix.dragonminez.client.gui.entity.KarinMenu;
import com.yuseix.dragonminez.client.gui.entity.ShenlongMenu;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.world.DragonBallGenProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShenlongEntity extends Mob implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private long invokingTime;
    private Player owner;

    public ShenlongEntity(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25000.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.5f)
                .add(Attributes.ATTACK_SPEED, 0.5f)
                .add(Attributes.MOVEMENT_SPEED, 0.18F).build();
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public Player getOwner() {
        return this.owner;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class,35.0f));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (getOwner() == player) {
            if (this.level() instanceof ServerLevel serverWorld) {
                serverWorld.getCapability(DragonBallGenProvider.CAPABILITY).ifPresent(dragonBallsCapability -> {
                    boolean hasDragonBalls = dragonBallsCapability.hasDragonBalls();

                    if (hasDragonBalls) {
                        dragonBallsCapability.setHasDragonBalls(false);
                    }
                });
            }
            if (this.level().isClientSide) {
                Minecraft.getInstance().setScreen(new ShenlongMenu());

                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(player, hand);
    }

    public void setInvokingTime(long time) {
        this.invokingTime = time;
    }

    public long getInvokingTime() {
        return invokingTime;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));

    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {

        tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.shenlong.idle", Animation.LoopType.LOOP));

        return PlayState.CONTINUE;

    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean canCollideWith(Entity pEntity) {
        return false;
    }

    @Override
    public boolean canBeHitByProjectile() {
        return false;
    }

    @Override
    public void remove(RemovalReason reason) {
        if (!this.level().isClientSide && reason == RemovalReason.DISCARDED) {
            onDespawn();
        }
        super.remove(reason);
    }

    private static final List<BlockPos> dragonBallPositions = new ArrayList<>();
    private void onDespawn() {
        if (this.level() instanceof ServerLevel serverWorld) {
            serverWorld.getCapability(DragonBallGenProvider.CAPABILITY).ifPresent(dragonBallsCapability -> {
                boolean hasDragonBalls = dragonBallsCapability.hasDragonBalls();

                if (!hasDragonBalls) {
                    spawnDragonBall(serverWorld, MainBlocks.DBALL1_BLOCK.get().defaultBlockState());
                    spawnDragonBall(serverWorld, MainBlocks.DBALL2_BLOCK.get().defaultBlockState());
                    spawnDragonBall(serverWorld, MainBlocks.DBALL3_BLOCK.get().defaultBlockState());
                    spawnDragonBall(serverWorld, MainBlocks.DBALL4_BLOCK.get().defaultBlockState());
                    spawnDragonBall(serverWorld, MainBlocks.DBALL5_BLOCK.get().defaultBlockState());
                    spawnDragonBall(serverWorld, MainBlocks.DBALL6_BLOCK.get().defaultBlockState());
                    spawnDragonBall(serverWorld, MainBlocks.DBALL7_BLOCK.get().defaultBlockState());

                    dragonBallsCapability.setDragonBallPositions(dragonBallPositions);
                    dragonBallsCapability.setHasDragonBalls(true);
                }
            });
        }
    }
    private void spawnDragonBall(ServerLevel serverWorld, BlockState dragonBall) {
        BlockPos spawnPos = serverWorld.getSharedSpawnPos();
        Random random = new Random();

        int x = spawnPos.getX() + random.nextInt(10000) - 5000;
        int z = spawnPos.getZ() + random.nextInt(10000) - 5000;

        serverWorld.getChunk(x >> 4, z >> 4);


        int y = serverWorld.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);

        BlockPos pos = new BlockPos(x, y, z);

        serverWorld.setBlock(pos, dragonBall, 2);
        System.out.println("Dragon Ball spawned at " + pos);

        dragonBallPositions.add(pos);
    }

}
