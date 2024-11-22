package com.yuseix.dragonminez.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class GenAttRegistry {

    public static final String MOD_ID = "dragonminez";
    public static final String MOD_NAME = "DragonMineZ";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public Attribute get(ResourceLocation id) {
        return ForgeRegistries.ATTRIBUTES.getValue(id);
    }

    public ResourceLocation getId(Attribute value) {
        return ForgeRegistries.ATTRIBUTES.getKey(value);
    }

    public boolean isRegistered(Attribute value) {
        return ForgeRegistries.ATTRIBUTES.containsValue(value);
    }

    public boolean exists(ResourceLocation id) {
        return ForgeRegistries.ATTRIBUTES.containsKey(id);
    }

    public Collection<Attribute> getValues() {
        return ForgeRegistries.ATTRIBUTES.getValues();
    }
}
