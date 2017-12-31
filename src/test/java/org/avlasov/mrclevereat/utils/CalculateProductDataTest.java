package org.avlasov.mrclevereat.utils;

import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.avlasov.mrclevereat.entity.product.MealProduct;
import org.avlasov.mrclevereat.entity.product.Product;
import org.avlasov.mrclevereat.entity.ration.Meal;
import org.avlasov.mrclevereat.entity.recipe.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created By artemvlasov on 23/12/2017
 **/
class CalculateProductDataTest {

    @Test
    void calculateMealTotals_WithRequiredData_CalculateNutritionalValue() {
        Meal meal = getMeal().build();
        new CalculateProductData().calculateMealNutritionalValue(meal);
        assertEquals(meal.getVolume(), 410);
        assertEquals(meal.getNutritionalValue().getCalories(), 382);
    }

    @Test
    void calculateMealTotals_WithProductsOnly_CalculateNutritionalValue() {
        Meal meal = getMeal()
                .recipes(null)
                .build();
        new CalculateProductData().calculateMealNutritionalValue(meal);
        assertEquals(meal.getVolume(), 180);
        assertEquals(meal.getNutritionalValue().getFat(), 4.73);
    }

    @Test
    void calculateMealTotals_WithRecipesOnly_CalculateNutritionalValue() {
        Meal meal = getMeal()
                .mealProducts(null)
                .build();
        new CalculateProductData().calculateMealNutritionalValue(meal);
        assertEquals(meal.getVolume(), 230);
        assertEquals(meal.getNutritionalValue().getCarbohydrate(), 9.129999999999999);
    }

    @Test
    void calculateMealTotals_WithOutData_NoCalculation() {
        Meal meal = getMeal()
                .recipes(null)
                .mealProducts(null)
                .build();
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
        Recipe recipe = getRecipe().build();
        new CalculateProductData().calculateRecipeNutritionalValue(recipe);
        assertEquals(recipe.getVolume(), 230);
        assertEquals(recipe.getNutritionalValue().getProtein(), 1.88);
        assertEquals(recipe.getName(), "Vegetable Salad");
    }

    @Test
    void calculateRecipesTotals_WithOutData_NoCalculation() {
        Recipe recipe = getRecipe()
                .mealProducts(null)
                .build();
        new CalculateProductData().calculateRecipeNutritionalValue(recipe);
        assertEquals(recipe.getVolume(), 0);
        assertEquals(recipe.getNutritionalValue().getProtein(), 0);
    }

    @Test
    void calculateRecipesTotals_WithNull_ThrownException() {
        Assertions.assertThrows(NullPointerException.class, () -> new CalculateProductData().calculateRecipeNutritionalValue(null));
    }

    private Meal.MealBuilder getMeal() {
        return Meal.builder()
                .addMealProduct(getMealProduct(130, getProduct("chicken breast", 31, 3.6, 0, 165).build()))
                .addMealProduct(getMealProduct(50, getProduct("potato", 2, 0.1, 17,77).build()))
                .addRecipe(getRecipe().build());
    }

    private Recipe.RecipeBuilder getRecipe() {
        return Recipe.builder()
                .name("Vegetable Salad")
                .addMealProduct(getMealProduct(50, getProduct("cucumber", 0.7, 0, 3.6, 16).build()))
                .addMealProduct(getMealProduct(70, getProduct("tomato", 0.9, 0.2, 3.9, 18).build()))
                .addMealProduct(getMealProduct(100, getProduct("bell pepper", 0.9, 0.2, 4.6, 20).build()))
                .addMealProduct(getMealProduct(10, getProduct("olive oil", 0, 100, 0, 884).build()));
    }

    private MealProduct getMealProduct(double volume, Product product) {
        return new MealProduct(product, volume);
    }


    private Product.ProductBuilder getProduct(String name, double protein, double fat, double carbohydrate, double calories) {
        Product.ProductBuilder builder = Product.builder(name);
        NutritionalValue nutritionalValue = NutritionalValue.builder()
                .protein(protein)
                .fat(fat)
                .carbohydrate(carbohydrate)
                .calories(calories)
                .build();
        return builder.nutritionalValue(nutritionalValue);
    }
}