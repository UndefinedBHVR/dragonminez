package com.yuseix.dragonminez.init.armor.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.init.armor.VegetaSaiyanArmorItem;
import com.yuseix.dragonminez.init.armor.client.model.VegetaSaiyanArmorModel;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.EquipmentSlot;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class VegetaSaiyanArmorRenderer extends GeoArmorRenderer<VegetaSaiyanArmorItem> {
    public VegetaSaiyanArmorRenderer() {
        super(new VegetaSaiyanArmorModel());
    }

    @Override
    public void preRender(PoseStack poseStack, VegetaSaiyanArmorItem animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        resetBones();
        EquipmentSlot slot = animatable.getEquipmentSlot();
        applyBoneVisibilityBySlot(slot);
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    protected void applyBoneVisibilityBySlot(EquipmentSlot currentSlot) {
        resetBones();

        switch (currentSlot){
            case CHEST:
                this.model.getBone("bipedBody").ifPresent(bone -> bone.setHidden(false));

                if(this.currentEntity instanceof AbstractClientPlayer player){

                    DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {

                        String modelname = player.getModelName();

                        var raza = cap.getRace();

                        if(raza == 0){
                            if (cap.getBodytype() == 0) {
                                if ("default".equals(modelname)) {
                                    this.model.getBone("steveRightArm").ifPresent(bone -> bone.setHidden(false));
                                    this.model.getBone("steveLeftArm").ifPresent(bone -> bone.setHidden(false));
                                } else if ("slim".equals(modelname)) {
                                    this.model.getBone("alexRightArm").ifPresent(bone -> bone.setHidden(false));
                                    this.model.getBone("alexLeftArm").ifPresent(bone -> bone.setHidden(false));
                                }


                            } else if (cap.getBodytype() == 1) {

                                if (cap.getGender().equals("Male")) {
                                    this.model.getBone("steveRightArm").ifPresent(bone -> bone.setHidden(false));
                                    this.model.getBone("steveLeftArm").ifPresent(bone -> bone.setHidden(false));
                                } else {
                                    this.model.getBone("alexRightArm").ifPresent(bone -> bone.setHidden(false));
                                    this.model.getBone("alexLeftArm").ifPresent(bone -> bone.setHidden(false));
                                }


                            }
                        } else if(raza == 1){
                            if (cap.getBodytype() == 0) {
                                if ("default".equals(modelname)) {
                                    this.model.getBone("steveRightArm").ifPresent(bone -> bone.setHidden(false));
                                    this.model.getBone("steveLeftArm").ifPresent(bone -> bone.setHidden(false));
                                } else if ("slim".equals(modelname)) {
                                    this.model.getBone("alexRightArm").ifPresent(bone -> bone.setHidden(false));
                                    this.model.getBone("alexLeftArm").ifPresent(bone -> bone.setHidden(false));
                                }
                            } else if (cap.getBodytype() == 1) {
                                if (cap.getGender().equals("Male")) {
                                    this.model.getBone("steveRightArm").ifPresent(bone -> bone.setHidden(false));
                                    this.model.getBone("steveLeftArm").ifPresent(bone -> bone.setHidden(false));
                                } else {
                                    this.model.getBone("alexRightArm").ifPresent(bone -> bone.setHidden(false));
                                    this.model.getBone("alexLeftArm").ifPresent(bone -> bone.setHidden(false));
                                }
                            }
                        } else if(raza == 2){
                            this.model.getBone("steveRightArm").ifPresent(bone -> bone.setHidden(false));
                            this.model.getBone("steveLeftArm").ifPresent(bone -> bone.setHidden(false));

                        } else if(raza == 3){
                            this.model.getBone("steveRightArm").ifPresent(bone -> bone.setHidden(false));
                            this.model.getBone("steveLeftArm").ifPresent(bone -> bone.setHidden(false));
                        } else if(raza == 4){
                            this.model.getBone("steveRightArm").ifPresent(bone -> bone.setHidden(false));
                            this.model.getBone("steveLeftArm").ifPresent(bone -> bone.setHidden(false));
                        } else{
                            if (cap.getGender().equals("Male")) {
                                this.model.getBone("steveRightArm").ifPresent(bone -> bone.setHidden(false));
                                this.model.getBone("steveLeftArm").ifPresent(bone -> bone.setHidden(false));
                            } else {
                                this.model.getBone("alexRightArm").ifPresent(bone -> bone.setHidden(false));
                                this.model.getBone("alexLeftArm").ifPresent(bone -> bone.setHidden(false));
                            }
                        }

                    });
                }

                break;
            case LEGS:
                this.model.getBone("armorLeftLeg").ifPresent(bone -> {
                    bone.setHidden(false);
                });
                this.model.getBone("armorRightLeg").ifPresent(bone -> {
                    bone.setHidden(false);
                });
                break;
            case FEET:
                this.model.getBone("armorLeftBoot").ifPresent(bone -> {
                    bone.setHidden(false);
                });
                this.model.getBone("armorRightBoot").ifPresent(bone -> {
                    bone.setHidden(false);
                });
                break;
            case HEAD:
                break;
        }
    }

    private void resetBones() {
        // Ocultamos todos los bones al inicio
        this.model.getBone("bipedBody").ifPresent(bone -> bone.setHidden(true));

        this.model.getBone("steveRightArm").ifPresent(bone -> bone.setHidden(true));
        this.model.getBone("alexRightArm").ifPresent(bone -> bone.setHidden(true));

        this.model.getBone("steveLeftArm").ifPresent(bone -> bone.setHidden(true));
        this.model.getBone("alexLeftArm").ifPresent(bone -> bone.setHidden(true));

        this.model.getBone("armorRightLeg").ifPresent(bone -> bone.setHidden(true));
        this.model.getBone("armorRightBoot").ifPresent(bone -> bone.setHidden(true));

        this.model.getBone("armorLeftLeg").ifPresent(bone -> bone.setHidden(true));
        this.model.getBone("armorLeftBoot").ifPresent(bone -> bone.setHidden(true));

    }
}
