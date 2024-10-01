package com.yuseix.dragonminez.init.items.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.world.DragonBallGenProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
public class DragonBallRadarItem extends Item {

    public DragonBallRadarItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack item = player.getItemInHand(hand);

        //Toggle radar soun
        if (level.isClientSide())
            player.playSound(MainSounds.RADAR_SCAN.get(), 0.5F, 1.0F);
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {

            /*TODO: Renderizado Radar
             * Abajo se puede ver cómo se envian al chat las posiciones de las DBalls al hacer click derecho con el radar.
             * Pero aún no existe ni la textura ni el renderizado del radar en sí.
             * Parece una tarea complicada, hay que revisar items como el MapItem
             * labels: Estado: Disponible, Prioridad: Media, Tipo: Modelos
             */

            level.getCapability(DragonBallGenProvider.CAPABILITY).ifPresent(dragonBallsCapability -> {
                if (dragonBallsCapability.hasDragonBalls()) {
                    List<BlockPos> dragonBallPositions = dragonBallsCapability.DragonBallPositions();
                    for (BlockPos pos : dragonBallPositions) {
                        player.sendSystemMessage(Component.literal("Dragon Ball found! At " + pos));
                    }
                }
            });
        }

        return InteractionResultHolder.pass(item);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            private static final HumanoidModel.ArmPose EXAMPLE_POSE = HumanoidModel.ArmPose.create("EXAMPLE", false, (model, entity, arm) -> {
                if (arm == HumanoidArm.RIGHT) {
                    model.rightArm.xRot = (float) (Math.random() * Math.PI * 2);
                } else {
                    model.leftArm.xRot = (float) (Math.random() * Math.PI * 2);
                }
            });

            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                if (!itemStack.isEmpty()) {
                    if (entityLiving.getUsedItemHand() == hand && entityLiving.getUseItemRemainingTicks() > 0) {
                        return EXAMPLE_POSE;
                    }
                }
                return HumanoidModel.ArmPose.EMPTY;
            }

            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                int i = arm == HumanoidArm.RIGHT ? 1 : -1;
                poseStack.translate(i * 0.56F, -0.52F, -0.72F);
                if (player.getUseItem() == itemInHand && player.isUsingItem()) {
                    poseStack.translate(2.0, -0.05, 0.0);
                }
                return true;
            }
        });
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.dragonminez.dball_radar.tooltip").withStyle(ChatFormatting.GRAY));
    }

}