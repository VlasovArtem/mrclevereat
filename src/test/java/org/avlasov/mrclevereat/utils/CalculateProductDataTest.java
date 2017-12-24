package org.avlasov.mrclevereat.utils;

import org.avlasov.mrclevereat.entity.product.MealProduct;
import org.avlasov.mrclevereat.entity.product.Product;
import org.avlasov.mrclevereat.entity.ration.Meal;
import org.avlasov.mrclevereat.entity.recipe.Recipe;
import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created By artemvlasov on 23/12/2017
 **/
class CalculateProductDataTest {

    @Test
    void calculateMealTotals_WithRequiredData_CalculateNutritionalValue() {
        Meal meal = getMeal();
        new CalculateProductData().calculateMealTotals(meal);
        assertEquals(meal.getVolume(), 410);
        assertEquals(meal.getNutritionalValue().getCalories(), 382);
    }

    @Test
    void calculateMealTotals_WithProductsOnly_CalculateNutritionalValue() {
        Meal meal = getMeal();
        meal.setRecipes(null);
        new CalculateProductData().calculateMealTotals(meal);
        assertEquals(meal.getVolume(), 180);
        assertEquals(meal.getNutritionalValue().getFat(), 4.73);
    }

    @Test
    void calculateMealTotals_WithRecipesOnly_CalculateNutritionalValue() {
        Meal meal = getMeal();
        meal.setMealProducts(null);
        new CalculateProductData().calculateMealTotals(meal);
        assertEquals(meal.getVolume(), 230);
        assertEquals(meal.getNutritionalValue().getCarbohydrate(), 9.129999999999999);
    }

    @Test
    void calculateMealTotals_WithOutData_NoCalculation() {
        Meal meal = getMeal();
        meal.setRecipes(null);
        meal.setMealProducts(null);
        new CalculateProductData().calculateMealTotals(meal);
        assertEquals(meal.getVolume(), 0);
        assertEquals(meal.getNutritionalValue().getCarbohydrate(), 0);
    }

    @Test
    void calculateMealTotals_WithNull_ThrownException() {
        Assertions.assertThrows(NullPointerException.class, () -> new CalculateProductData().calculateMealTotals(null));
    }

    @Test
    void calculateRecipesTotals_WithRequiredData_CalculateNutritionalValue() {
        Recipe recipe = getRecipe();
        new CalculateProductData().calculateRecipesTotals(recipe);
        assertEquals(recipe.getVolume(), 230);
        assertEquals(recipe.getNutritionalValue().getProtein(), 1.88);
        assertEquals(recipe.getName(), "Vegetable Salad");
    }

    @Test
    void calculateRecipesTotals_WithOutData_NoCalculation() {
        Recipe recipe = getRecipe();
        recipe.setMealProducts(null);
        new CalculateProductData().calculateRecipesTotals(recipe);
        assertEquals(recipe.getVolume(), 0);
        assertEquals(recipe.getNutritionalValue().getProtein(), 0);
    }

    @Test
    void calculateRecipesTotals_WithNull_ThrownException() {
        Assertions.assertThrows(NullPointerException.class, () -> new CalculateProductData().calculateRecipesTotals(null));
    }

    private Meal getMeal() {
        Meal meal = new Meal();
        meal.setMealProducts(Arrays.asList(getMealProduct(130, getProduct("chicken breast", 31, 3.6, 0, 165)),
                getMealProduct(50, getProduct("potato", 2, 0.1, 17,77))));
        meal.setRecipes(Collections.singletonList(getRecipe()));
        return meal;
    }

    private Recipe getRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Vegetable Salad");
        recipe.setMealProducts(Arrays.asList(
                getMealProduct(50, getProduct("cucumber", 0.7, 0, 3.6, 16)),
                getMealProduct(70, getProduct("tomato", 0.9, 0.2, 3.9, 18)),
                getMealProduct(100, getProduct("bell pepper", 0.9, 0.2, 4.6, 20)),
                getMealProduct(10, getProduct("olive oil", 0, 100, 0, 884))
                ));
        return recipe;
    }

    private MealProduct getMealProduct(double volume, Product product) {
        MealProduct mealProduct = new MealProduct();
        mealProduct.setVolume(volume);
        mealProduct.setProduct(product);
        return mealProduct;
    }


    private Product getProduct(String name, double protein, double fat, double carbohydrate, double calories) {
        Product product = new Product();
        product.setName(name);
        NutritionalValue nutritionalValue = new NutritionalValue();
        nutritionalValue.setProtein(protein);
        nutritionalValue.setFat(fat);
        nutritionalValue.setCarbohydrate(carbohydrate);
        nutritionalValue.setCalories(calories);
        product.setNutritionalValue(nutritionalValue);
        return product;
    }
}