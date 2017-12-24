package org.avlasov.mrclevereat.entity.ration;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.product.MealProduct;
import org.avlasov.mrclevereat.entity.recipe.Recipe;
import org.avlasov.mrclevereat.entity.nutrition.NutritionalFacts;
import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;

import java.util.List;
import java.util.Objects;

/**
 * Created By artemvlasov on 22/12/2017
 **/
public class Meal extends Base implements NutritionalFacts {

    private List<MealProduct> mealProducts;
    private List<Recipe> recipes;
    private NutritionalValue nutritionalValue;
    private double volume;

    public List<MealProduct> getMealProducts() {
        return mealProducts;
    }

    public void setMealProducts(List<MealProduct> mealProducts) {
        this.mealProducts = mealProducts;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(NutritionalValue nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;
        Meal meal = (Meal) o;
        return Double.compare(meal.volume, volume) == 0 &&
                Objects.equals(mealProducts, meal.mealProducts) &&
                Objects.equals(recipes, meal.recipes) &&
                Objects.equals(nutritionalValue, meal.nutritionalValue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mealProducts, recipes, nutritionalValue, volume);
    }
}
