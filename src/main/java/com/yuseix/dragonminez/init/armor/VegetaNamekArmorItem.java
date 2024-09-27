package com.yuseix.dragonminez.init.armor;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.armor.client.model.ArmorBaseModel;
import com.yuseix.dragonminez.init.armor.client.model.ArmorSaiyanModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
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

public class VegetaNamekArmorItem extends ArmorItem{

    private static final String LAYER1 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/armor/dbz/vegetanamek_armor_layer1.png").toString();
    private static final String LAYER2 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/armor/dbz/vegetanamek_armor_layer2.png").toString();


    public VegetaNamekArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        switch (slot) {
            case HEAD:
                return LAYER1;
            case LEGS:
                return LAYER2;
            case FEET:
                return LAYER1;
            default:
                return LAYER1;
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
