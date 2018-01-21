package org.avlasov.mrclevereat.entity.recipe;

import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.avlasov.mrclevereat.entity.product.Product;
import org.avlasov.mrclevereat.entity.product.RecipeProduct;
import org.avlasov.mrclevereat.entity.recipe.enums.RecipeVisibility;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created By artemvlasov on 19/01/2018
 **/
class RecipeTest {

    @Test
    void defaultConstructor() {
        new Recipe();
    }

    @Test
    void getVolume() {
        assertEquals(100, getRecipe().getVolume());
    }

    @Test
    void getName() {
        assertEquals("test", getRecipe().getName());
    }

    @Test
    void getDescription() {
        assertEquals("test", getRecipe().getDescription());
    }

    @Test
    void getNutritionalValue() {
        assertNotNull(getRecipe().getNutritionalValue());
    }

    @Test
    void getRecipeVisibility() {
        assertEquals(RecipeVisibility.DAY, getRecipe().getRecipeVisibility());
    }

    @Test
    void getRecipeProducts() {
        assertThat(getRecipe().getRecipeProducts(), IsCollectionWithSize.hasSize(1));
    }

    @Test
    void builder() {
        assertNotNull(Recipe.builder());
    }

    @Test
    void builder_WithSource() {
        assertEquals(100, Recipe.builder(getRecipe())
                .mealProducts(Collections.singletonList(new RecipeProduct(Product.builder("Test").build(), 10)))
                .build().getVolume());
    }

    @Test
    void equals() {
        assertTrue(getRecipe().equals(getRecipe()));
    }

    @Test
    void hashCodeTest() {
        assertEquals(getRecipe().hashCode(), getRecipe().hashCode());
    }

    private Recipe getRecipe() {
        return Recipe.builder()
                .recipeVisibility(RecipeVisibility.DAY)
                .addMealProduct(new RecipeProduct(Product.builder("Test").build(), 10))
                .description("test")
                .name("test")
                .nutritionalValue(NutritionalValue.builder().build())
                .volume(100)
                .build();
    }

}