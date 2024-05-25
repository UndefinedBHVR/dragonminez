package com.yuseix.dragonminez.init;


import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.fluids.SimpleFluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class MainFluids {

    public static final DeferredRegister<FluidType> FLUID_TYPE_REGISTER =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, DragonMineZ.MOD_ID);

    public static final DeferredRegister<Fluid> FLUIDS_REGISTER =
            DeferredRegister.create(ForgeRegistries.FLUIDS, DragonMineZ.MOD_ID);

    public static final RegistryObject<FluidType> HEALING_LIQUID_FLUID_TYPE = FLUID_TYPE_REGISTER.register("healing_liquid_fluid_type",
            () -> new SimpleFluid(
                    0xe07f38,
                    FluidType.Properties.create().lightLevel(5)));

    public static final RegistryObject<FlowingFluid> SOURCE_HEALING_LIQUID = FLUIDS_REGISTER.register("healing_liquid_fluid",
            () -> new ForgeFlowingFluid.Source(MainFluids.HEALING_LIQUID_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_HEALING_LIQUID = FLUIDS_REGISTER.register("flowing_healing_liquid_fluid",
            () -> new ForgeFlowingFluid.Flowing(MainFluids.HEALING_LIQUID_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties HEALING_LIQUID_FLUID_PROPERTIES
            = new ForgeFlowingFluid.Properties(MainFluids.HEALING_LIQUID_FLUID_TYPE, SOURCE_HEALING_LIQUID, FLOWING_HEALING_LIQUID)
            .block(MainBlocks.HEALING_LIQUID_BLOCK)
            .bucket(MainItems.HEALING_LIQUID_BUCKET);
}