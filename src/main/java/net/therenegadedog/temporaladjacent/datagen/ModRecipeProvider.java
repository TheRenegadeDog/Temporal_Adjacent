package net.therenegadedog.temporaladjacent.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.therenegadedog.temporaladjacent.TemporalAdjacent;
import net.therenegadedog.temporaladjacent.core.ModBlocks;
import net.therenegadedog.temporaladjacent.core.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        List<ItemLike> ZEITON_SMELTABLE = List.of(ModItems.UNREFINED_ZEITON,
            ModBlocks.ZEITON_CRYSTAL_ORE, ModBlocks.ZEITON_CRYSTAL_DEEPSLATE_ORE);

        oreBlasting(ZEITON_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.ZEITON.get(),  0.3f, 100, "zeiton");
        oreSmelting(ZEITON_SMELTABLE, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.ZEITON.get(),  0.1f, 200, "zeiton");

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ZEITON_CRYSTAL_BLOCK.get())
                .pattern("ZZZ")
                .pattern("ZRZ")
                .pattern("ZZZ")
                .define('Z', ModItems.ZEITON)
                .define('R', Blocks.REDSTONE_BLOCK)
                .unlockedBy(getHasName(ModItems.ZEITON.get()), has(ModItems.ZEITON.get()))
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.ZEITON.get(), 8)
                .requires(ModBlocks.ZEITON_CRYSTAL_BLOCK)
                .unlockedBy(getHasName(ModBlocks.ZEITON_CRYSTAL_BLOCK.get()), has(ModItems.ZEITON.get()))
                .save(output);
    }

    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(AbstractCookingRecipe.Factory<T> factory, List<ItemLike> smeltables,
                                                                RecipeCategory craftingCategory, CookingBookCategory cookingCategory,
                                                                ItemLike result, float experience, int cookingTime, String group, String fromDesc) {
        for (ItemLike item : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(item), craftingCategory, cookingCategory, result, experience, cookingTime, factory)
                    .group(group)
                    .unlockedBy(getHasName(item), this.has(item))
                    .save(this.output, TemporalAdjacent.MOD_ID + ":" + getItemName(result) + fromDesc + "_" + getItemName(item));

        }
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "TemporalAdjacent Recipes";
        }
    }

}
