package com.yuseix.dragonminez.init.armor;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.armor.client.model.ArmorBaseModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class DbzArmorItem extends ArmorItem {

    private final String itemId;

    public DbzArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, String itemId) {
        super(pMaterial, pType, pProperties);
        this.itemId = itemId; // ID del item

    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {

        String texturePath = DragonMineZ.MOD_ID + ":textures/armor/" + itemId;

        switch (slot) {
            case HEAD:
                return texturePath + "_layer1.png";
            case LEGS:
                return texturePath + "_layer2.png";
            case FEET:
                return texturePath + "_layer1.png";
            default:
                return texturePath + "_layer1.png";
        }
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            private ArmorBaseModel model;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {

                if(model == null){
                    model = new ArmorBaseModel(Minecraft.getInstance().getEntityModels().bakeLayer(ArmorBaseModel.LAYER_LOCATION));
                }
                return model;

            }
        });
    }
}
