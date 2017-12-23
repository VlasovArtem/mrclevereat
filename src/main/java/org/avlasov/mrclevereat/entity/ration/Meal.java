package org.avlasov.mrclevereat.entity.ration;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.product.MealProduct;
import org.avlasov.mrclevereat.entity.recipe.Recipe;
import org.avlasov.mrclevereat.nutrition.NutritionalFacts;
import org.avlasov.mrclevereat.nutrition.NutritionalValue;

import java.util.List;

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
}
