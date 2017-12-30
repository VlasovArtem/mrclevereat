package org.avlasov.mrclevereat.utils;

import org.avlasov.mrclevereat.entity.nutrition.NutritionalValueBuilder;
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
        new CalculateProductData().calculateMealNutritionalValue(meal);
        assertEquals(meal.getVolume(), 410);
        assertEquals(meal.getNutritionalValue().getCalories(), 382);
    }

    @Test
    void calculateMealTotals_WithProductsOnly_CalculateNutritionalValue() {
        Meal meal = getMeal();
        meal.setRecipes(null);
        new CalculateProductData().calculateMealNutritionalValue(meal);
        assertEquals(meal.getVolume(), 180);
        assertEquals(meal.getNutritionalValue().getFat(), 4.73);
    }

    @Test
    void calculateMealTotals_WithRecipesOnly_CalculateNutritionalValue() {
        Meal meal = getMeal();
        meal.setMealProducts(null);
        new CalculateProductData().calculateMealNutritionalValue(meal);
        assertEquals(meal.getVolume(), 230);
        assertEquals(meal.getNutritionalValue().getCarbohydrate(), 9.129999999999999);
    }

    @Test
    void calculateMealTotals_WithOutData_NoCalculation() {
        Meal meal = getMeal();
        meal.setRecipes(null);
        meal.setMealProducts(null);
        new CalculateProductData().calculateMealNutritionalValue(meal);
        assertEquals(meal.getVolume(), 0);
        assertEquals(meal.getNutritionalValue().getCarbohydrate(), 0);
    }

    @Test
    void calculateMealTotals_WithNull_ThrownException() {
        Assertions.assertThrows(NullPointerException.class, () -> new CalculateProductData().calculateMealNutritionalValue(null));
    }

    @Test
    void calculateRecipesTotals_WithRequiredData_CalculateNutritionalValue() {
        Recipe recipe = getRecipe();
        new CalculateProductData().calculateRecipeNutritionalValue(recipe);
        assertEquals(recipe.getVolume(), 230);
        assertEquals(recipe.getNutritionalValue().getProtein(), 1.88);
        assertEquals(recipe.getName(), "Vegetable Salad");
    }

    @Test
    void calculateRecipesTotals_WithOutData_NoCalculation() {
        Recipe recipe = getRecipe();
        recipe.setMealProducts(null);
        new CalculateProductData().calculateRecipeNutritionalValue(recipe);
        assertEquals(recipe.getVolume(), 0);
        assertEquals(recipe.getNutritionalValue().getProtein(), 0);
    }

    @Test
    void calculateRecipesTotals_WithNull_ThrownException() {
        Assertions.assertThrows(NullPointerException.class, () -> new CalculateProductData().calculateRecipeNutritionalValue(null));
    }

    private Meal getMeal() {
        Meal meal = new Meal.MealBuilder().createMeal();
        meal.setMealProducts(Arrays.asList(getMealProduct(130, getProduct("chicken breast", 31, 3.6, 0, 165)),
                getMealProduct(50, getProduct("potato", 2, 0.1, 17,77))));
        meal.setRecipes(Collections.singletonList(getRecipe()));
        return meal;
    }

    private Recipe getRecipe() {
        Recipe recipe = new Recipe.RecipeBuilder().createRecipe();
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
        Product product = new Product.ProductBuilder().createProduct();
        product.setName(name);
        NutritionalValue nutritionalValue = new NutritionalValueBuilder().createNutritionalValue();
        nutritionalValue.setProtein(protein);
        nutritionalValue.setFat(fat);
        nutritionalValue.setCarbohydrate(carbohydrate);
        nutritionalValue.setCalories(calories);
        product.setNutritionalValue(nutritionalValue);
        return product;
    }
}