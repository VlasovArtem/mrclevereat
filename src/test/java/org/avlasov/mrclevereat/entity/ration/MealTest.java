package org.avlasov.mrclevereat.entity.ration;

import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.avlasov.mrclevereat.entity.product.MealProduct;
import org.avlasov.mrclevereat.entity.product.Product;
import org.avlasov.mrclevereat.entity.recipe.Recipe;
import org.avlasov.mrclevereat.entity.user.User;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created By artemvlasov on 19/01/2018
 **/
class MealTest {

    @Test
    void defaultConstructor() {
        new Meal();
    }

    @Test
    void getMealProducts() {
        assertThat(getMeal().getMealProducts(), IsCollectionWithSize.hasSize(1));
    }

    @Test
    void getRecipes() {
        assertThat(getMeal().getRecipes(), IsCollectionWithSize.hasSize(1));
    }

    @Test
    void getNutritionalValue() {
        assertNotNull(getMeal().getNutritionalValue());
    }

    @Test
    void getVolume() {
        assertEquals(10, getMeal().getVolume());
    }

    @Test
    void builder() {
        Meal.builder()
                .recipes(Collections.singletonList(Recipe.builder().build()))
                .mealProducts(Collections.singletonList(new MealProduct(Product.builder("test").build(), 10)))
                .build();
    }

    @Test
    void builder1() {
        assertEquals(10, Meal.builder(getMeal()).build().getVolume());
    }

    @Test
    void equals() {
        assertTrue(getMeal().equals(getMeal()));
    }

    @Test
    void hashCodeTest() {
        assertEquals(getMeal().hashCode(), getMeal().hashCode());
    }

    private Meal getMeal() {
        return Meal.builder()
                .volume(10)
                .nutritionalValue(NutritionalValue.builder().build())
                .addRecipe(Recipe.builder().build())
                .addMealProduct(new MealProduct(Product.builder("test").build(), 10))
                .owner(User.builder("test", "test".getBytes()).build())
                .build();
    }

}