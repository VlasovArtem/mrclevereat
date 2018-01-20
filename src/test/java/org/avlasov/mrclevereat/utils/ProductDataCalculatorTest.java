package org.avlasov.mrclevereat.utils;

import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.avlasov.mrclevereat.entity.product.MealProduct;
import org.avlasov.mrclevereat.entity.product.Product;
import org.avlasov.mrclevereat.entity.product.RecipeProduct;
import org.avlasov.mrclevereat.entity.ration.Meal;
import org.avlasov.mrclevereat.entity.recipe.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created By artemvlasov on 23/12/2017
 **/
class ProductDataCalculatorTest {

    @Test
    void calculateMealTotals_WithRequiredData_CalculateNutritionalValue() {
        Meal meal = getMeal().build();
        Meal newMeal = new ProductDataCalculator().calculateMealNutritionalValue(meal);
        assertEquals(newMeal.getVolume(), 410);
        assertEquals(newMeal.getNutritionalValue().getCalories(), 382);
    }

    @Test
    void calculateMealTotals_WithProductsOnly_CalculateNutritionalValue() {
        Meal meal = getMeal()
                .recipes(null)
                .build();
        Meal newMeal = new ProductDataCalculator().calculateMealNutritionalValue(meal);
        assertEquals(newMeal.getVolume(), 180);
        assertEquals(newMeal.getNutritionalValue().getFat(), 4.73);
    }

    @Test
    void calculateMealTotals_WithRecipesOnly_CalculateNutritionalValue() {
        Meal meal = getMeal()
                .mealProducts(null)
                .build();
        Meal newMeal = new ProductDataCalculator().calculateMealNutritionalValue(meal);
        assertEquals(newMeal.getVolume(), 230);
        assertEquals(newMeal.getNutritionalValue().getCarbohydrate(), 9.129999999999999);
    }

    @Test
    void calculateMealTotals_WithOutData_NoCalculation() {
        Meal meal = getMeal()
                .recipes(null)
                .mealProducts(null)
                .build();
        Meal newMeal = new ProductDataCalculator().calculateMealNutritionalValue(meal);
        assertEquals(newMeal.getVolume(), 0);
        assertEquals(newMeal.getNutritionalValue().getCarbohydrate(), 0);
    }

    @Test
    void calculateMealTotals_WithNull_ThrownException() {
        Assertions.assertThrows(NullPointerException.class, () -> new ProductDataCalculator().calculateMealNutritionalValue(null));
    }

    @Test
    void calculateRecipesTotals_WithRequiredData_CalculateNutritionalValue() {
        Recipe recipe = getRecipe().build();
        Recipe newRecipe = new ProductDataCalculator().calculateRecipeNutritionalValue(recipe);
        assertEquals(newRecipe.getVolume(), 230);
        assertEquals(newRecipe.getNutritionalValue().getProtein(), 1.88);
        assertEquals(newRecipe.getName(), "Vegetable Salad");
    }

    @Test
    void calculateRecipesTotals_WithOutData_NoCalculation() {
        Recipe recipe = getRecipe()
                .mealProducts(null)
                .build();
        Recipe newRecipe = new ProductDataCalculator().calculateRecipeNutritionalValue(recipe);
        assertEquals(newRecipe.getVolume(), 0);
        assertEquals(newRecipe.getNutritionalValue().getProtein(), 0);
    }

    @Test
    void calculateRecipesTotals_WithNull_ThrownException() {
        Assertions.assertThrows(NullPointerException.class, () -> new ProductDataCalculator().calculateRecipeNutritionalValue(null));
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
                .addMealProduct(getRecipeProduct(50, getProduct("cucumber", 0.7, 0, 3.6, 16).build()))
                .addMealProduct(getRecipeProduct(70, getProduct("tomato", 0.9, 0.2, 3.9, 18).build()))
                .addMealProduct(getRecipeProduct(100, getProduct("bell pepper", 0.9, 0.2, 4.6, 20).build()))
                .addMealProduct(getRecipeProduct(10, getProduct("olive oil", 0, 100, 0, 884).build()));
    }

    private MealProduct getMealProduct(double volume, Product product) {
        return new MealProduct(product, volume);
    }

    private RecipeProduct getRecipeProduct(double volume, Product product) {
        return new RecipeProduct(product, volume);
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