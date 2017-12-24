package org.avlasov.mrclevereat.entity.recipe;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.product.MealProduct;
import org.avlasov.mrclevereat.entity.recipe.enums.RecipeVisibility;
import org.avlasov.mrclevereat.nutrition.NutritionalFacts;
import org.avlasov.mrclevereat.nutrition.NutritionalValue;

import java.util.List;
import java.util.Objects;

/**
 * Created By artemvlasov on 22/12/2017
 **/
public class Recipe extends Base implements NutritionalFacts {

    private String name;
    private String description;
    private boolean isShared;
    private List<MealProduct> mealProducts;
    private RecipeVisibility recipeVisibility;
    private List<Byte[]> images;
    private NutritionalValue nutritionalValue;
    private double volume;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public List<MealProduct> getMealProducts() {
        return mealProducts;
    }

    public void setMealProducts(List<MealProduct> mealProducts) {
        this.mealProducts = mealProducts;
    }

    public RecipeVisibility getRecipeVisibility() {
        return recipeVisibility;
    }

    public void setRecipeVisibility(RecipeVisibility recipeVisibility) {
        this.recipeVisibility = recipeVisibility;
    }

    public List<Byte[]> getImages() {
        return images;
    }

    public void setImages(List<Byte[]> images) {
        this.images = images;
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
    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return isShared == recipe.isShared &&
                Double.compare(recipe.volume, volume) == 0 &&
                Objects.equals(name, recipe.name) &&
                Objects.equals(description, recipe.description) &&
                Objects.equals(mealProducts, recipe.mealProducts) &&
                recipeVisibility == recipe.recipeVisibility &&
                Objects.equals(images, recipe.images) &&
                Objects.equals(nutritionalValue, recipe.nutritionalValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, isShared, mealProducts, recipeVisibility, images, nutritionalValue, volume);
    }
}
