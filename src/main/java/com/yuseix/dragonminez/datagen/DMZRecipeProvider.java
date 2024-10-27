package com.yuseix.dragonminez.datagen;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.init.MainItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class DMZRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public DMZRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreBlasting(pWriter, Gete, RecipeCategory.MISC, MainItems.GETE_SCRAP.get(), 3.5f, 100, "gete");
        oreSmelting(pWriter, Gete, RecipeCategory.MISC, MainItems.GETE_SCRAP.get(), 3.5f, 200, "gete");
        oreBlasting(pWriter, Kikono, RecipeCategory.MISC, MainItems.KIKONO_SHARD.get(), 2.5f, 100, "kikono");
        oreSmelting(pWriter, Kikono, RecipeCategory.MISC, MainItems.KIKONO_SHARD.get(), 2.5f, 200, "kikono");
        oreBlasting(pWriter, Diamantes, RecipeCategory.MISC, Items.DIAMOND, 1.0f, 100, "diamond");
        oreSmelting(pWriter, Diamantes, RecipeCategory.MISC, Items.DIAMOND, 1.0f, 200, "diamond");
        oreBlasting(pWriter, Esmeraldas, RecipeCategory.MISC, Items.EMERALD, 1f, 100, "emerald");
        oreSmelting(pWriter, Esmeraldas, RecipeCategory.MISC, Items.EMERALD, 1f, 200, "emerald");
        oreBlasting(pWriter, Lapis, RecipeCategory.MISC, Items.LAPIS_LAZULI, 0.2f, 100, "lapis_lazuli");
        oreSmelting(pWriter, Lapis, RecipeCategory.MISC, Items.LAPIS_LAZULI, 0.2f, 200, "lapis_lazuli");
        oreBlasting(pWriter, Redstone, RecipeCategory.MISC, Items.REDSTONE, 0.7f, 100, "redstone");
        oreSmelting(pWriter, Redstone, RecipeCategory.MISC, Items.REDSTONE, 0.7f, 200, "redstone");
        oreBlasting(pWriter, Hierro, RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 100, "iron_ingot");
        oreSmelting(pWriter, Hierro, RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 200, "iron_ingot");
        oreBlasting(pWriter, Oro, RecipeCategory.MISC, Items.GOLD_INGOT, 1.0f, 100, "gold_ingot");
        oreSmelting(pWriter, Oro, RecipeCategory.MISC, Items.GOLD_INGOT, 1.0f, 200, "gold_ingot");
        oreBlasting(pWriter, Cobre, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7f, 100, "copper_ingot");
        oreSmelting(pWriter, Cobre, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7f, 200, "copper_ingot");
        oreBlasting(pWriter, Carbon, RecipeCategory.MISC, Items.COAL, 0.1f, 100, "coal");
        oreSmelting(pWriter, Carbon, RecipeCategory.MISC, Items.COAL, 0.1f, 200, "coal");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(MainItems.FROG_LEGS_RAW.get()),
                RecipeCategory.FOOD, MainItems.FROG_LEGS_COOKED.get(), 0.35f, 200)
                .unlockedBy(getHasName(MainItems.FROG_LEGS_RAW.get()), has(MainItems.FROG_LEGS_RAW.get()))
                .group("dragonminez")
                .save(pWriter, new ResourceLocation(DragonMineZ.MOD_ID, "frog_legs_cooked"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(MainItems.FROG_LEGS_RAW.get()),
                RecipeCategory.FOOD, MainItems.FROG_LEGS_COOKED.get(), 0.35f, 100)
                .unlockedBy(getHasName(MainItems.FROG_LEGS_RAW.get()), has(MainItems.FROG_LEGS_RAW.get()))
                .group("dragonminez")
                .save(pWriter, new ResourceLocation(DragonMineZ.MOD_ID, "frog_legs_cooked_smoking"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(MainItems.FROG_LEGS_RAW.get()),
                RecipeCategory.FOOD, MainItems.FROG_LEGS_COOKED.get(), 0.35f, 600)
                .unlockedBy(getHasName(MainItems.FROG_LEGS_RAW.get()), has(MainItems.FROG_LEGS_RAW.get()))
                .group("dragonminez")
                .save(pWriter, new ResourceLocation(DragonMineZ.MOD_ID, "frog_legs_cooked_campfire"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MainItems.KIKONO_SHARD.get(), 9)
                .requires(MainBlocks.KIKONO_BLOCK.get())
                .unlockedBy(getHasName(MainBlocks.KIKONO_BLOCK.get()), has(MainBlocks.KIKONO_BLOCK.get()))
                .group("dragonminez")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MainBlocks.KIKONO_BLOCK.get(), 1)
                .pattern("KKK")
                .pattern("KKK")
                .pattern("KKK")
                .define('K', MainItems.KIKONO_SHARD.get())
                .unlockedBy(getHasName(MainItems.KIKONO_SHARD.get()), has(MainItems.KIKONO_SHARD.get()))
                .group("dragonminez")
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, MainItems.ARMOR_CRAFTING_KIT.get(), 1)
                .requires(Items.RED_WOOL)
                .requires(Items.SHEARS)
                .unlockedBy(getHasName(Items.SHEARS), has(Items.SHEARS))
                .group("dragonminez")
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, MainItems.KIKONO_STRING.get(), 2)
                .requires(MainItems.KIKONO_SHARD.get())
                .requires(MainItems.ARMOR_CRAFTING_KIT.get())
                .unlockedBy(getHasName(MainItems.KIKONO_SHARD.get()), has(MainItems.KIKONO_SHARD.get()))
                .group("dragonminez")
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, MainItems.KIKONO_CLOTH.get(), 1)
                .requires(MainItems.KIKONO_STRING.get(), 4)
                .requires(MainItems.ARMOR_CRAFTING_KIT.get())
                .unlockedBy(getHasName(MainItems.KIKONO_STRING.get()), has(MainItems.KIKONO_STRING.get()))
                .group("dragonminez")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, MainItems.PATTERN_GOKU1.get(), 1)
                .pattern("B#B")
                .pattern("OBO")
                .pattern("OOO")
                .define('B', Items.BLUE_DYE)
                .define('O', Items.ORANGE_DYE)
                .define('#', Items.PAPER)
                .unlockedBy(getHasName(Items.BLUE_DYE), has(Items.BLUE_DYE))
                .group("dragonminez")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, MainItems.PATTERN_GOTEN.get(), 1)
                .pattern("B#B")
                .pattern("OBO")
                .pattern("ONO")
                .define('B', Items.BLUE_DYE)
                .define('O', Items.ORANGE_DYE)
                .define('N', Items.BLACK_DYE)
                .define('#', Items.PAPER)
                .unlockedBy(getHasName(Items.BLUE_DYE), has(Items.BLUE_DYE))
                .group("dragonminez")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, MainItems.PATTERN_GOHAN1.get(), 1)
                .pattern("P#P")
                .pattern("PPP")
                .pattern("BBB")
                .define('P', Items.PURPLE_DYE)
                .define('B', Items.BLUE_DYE)
                .define('#', Items.PAPER)
                .unlockedBy(getHasName(Items.BLUE_DYE), has(Items.BLUE_DYE))
                .group("dragonminez")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, MainItems.PATTERN_VEGETA1.get(), 1)
                .pattern("Y#Y")
                .pattern("BWB")
                .pattern("BYB")
                .define('Y', Items.YELLOW_DYE)
                .define('B', Items.BLUE_DYE)
                .define('W', Items.WHITE_DYE)
                .define('#', Items.PAPER)
                .unlockedBy(getHasName(Items.BLUE_DYE), has(Items.BLUE_DYE))
                .group("dragonminez")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, MainItems.PATTERN_VEGETA2.get(), 1)
                .pattern("B#B")
                .pattern("BWB")
                .pattern("BYB")
                .define('B', Items.BLUE_DYE)
                .define('W', Items.WHITE_DYE)
                .define('Y', Items.YELLOW_DYE)
                .define('#', Items.PAPER)
                .unlockedBy(getHasName(Items.BLUE_DYE), has(Items.BLUE_DYE))
                .group("dragonminez")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, MainItems.PATTERN_BARDOCK1.get(), 1)
                .pattern("G#G")
                .pattern("BBB")
                .pattern("RGR")
                .define('B', Items.BLACK_DYE)
                .define('G', Items.GREEN_DYE)
                .define('R', Items.RED_DYE)
                .define('#', Items.PAPER)
                .unlockedBy(getHasName(Items.BLACK_DYE), has(Items.BLACK_DYE))
                .group("dragonminez")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, MainItems.PATTERN_BARDOCK2.get(), 1)
                .pattern("Y#Y")
                .pattern("BBB")
                .pattern("CYC")
                .define('Y', Items.YELLOW_DYE)
                .define('B', Items.BLACK_DYE)
                .define('C', Items.CYAN_DYE)
                .define('#', Items.PAPER)
                .unlockedBy(getHasName(Items.BLACK_DYE), has(Items.BLACK_DYE))
                .group("dragonminez")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, MainItems.PATTERN_PICCOLO.get(), 1)
                .pattern("P#P")
                .pattern("PPP")
                .pattern("RRR")
                .define('P', Items.PURPLE_DYE)
                .define('R', Items.RED_DYE)
                .define('#', Items.PAPER)
                .unlockedBy(getHasName(Items.GREEN_DYE), has(Items.GREEN_DYE))
                .group("dragonminez")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MainBlocks.KIKONO_ARMOR_STATION.get(), 1)
                .pattern("ACA")
                .pattern("KLK")
                .pattern("KSK")
                .define('A', Items.ANVIL)
                .define('C', MainItems.ARMOR_CRAFTING_KIT.get())
                .define('K', MainBlocks.KIKONO_BLOCK.get())
                .define('L', MainBlocks.NAMEK_SACRED_SLAB.get())
                .define('S', Items.SMITHING_TABLE)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .group("dragonminez")
                .save(pWriter);
    }

    private static final List<ItemLike> Gete = List.of(MainBlocks.GETE_ORE.get());
    private static final List<ItemLike> Kikono = List.of(MainBlocks.NAMEK_KIKONO_ORE.get());
    private static final List<ItemLike> Diamantes = List.of(MainBlocks.NAMEK_DIAMOND_ORE.get(), MainBlocks.NAMEK_DEEPSLATE_DIAMOND.get());
    private static final List<ItemLike> Esmeraldas = List.of(MainBlocks.NAMEK_EMERALD_ORE.get(), MainBlocks.NAMEK_DEEPSLATE_EMERALD.get());
    private static final List<ItemLike> Lapis = List.of(MainBlocks.NAMEK_LAPIS_ORE.get(), MainBlocks.NAMEK_DEEPSLATE_LAPIS.get());
    private static final List<ItemLike> Redstone = List.of(MainBlocks.NAMEK_REDSTONE_ORE.get(), MainBlocks.NAMEK_DEEPSLATE_REDSTONE.get());
    private static final List<ItemLike> Hierro = List.of(MainBlocks.NAMEK_IRON_ORE.get(), MainBlocks.NAMEK_DEEPSLATE_IRON.get());
    private static final List<ItemLike> Oro = List.of(MainBlocks.NAMEK_GOLD_ORE.get(), MainBlocks.NAMEK_DEEPSLATE_GOLD.get());
    private static final List<ItemLike> Cobre = List.of(MainBlocks.NAMEK_COPPER_ORE.get(), MainBlocks.NAMEK_DEEPSLATE_COPPER.get());
    private static final List<ItemLike> Carbon = List.of(MainBlocks.NAMEK_COAL_ORE.get(), MainBlocks.NAMEK_DEEPSLATE_COAL.get());

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  DragonMineZ.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
