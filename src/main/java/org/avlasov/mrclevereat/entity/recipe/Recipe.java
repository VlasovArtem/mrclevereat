package org.avlasov.mrclevereat.entity.recipe;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.nutrition.NutritionalFacts;
import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.avlasov.mrclevereat.entity.product.MealProduct;
import org.avlasov.mrclevereat.entity.recipe.enums.RecipeComplexity;
import org.avlasov.mrclevereat.entity.recipe.enums.RecipeVisibility;
import org.avlasov.mrclevereat.entity.social.Comment;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

/**
 * Created By artemvlasov on 22/12/2017
 **/
@Document
public class Recipe extends Base implements NutritionalFacts {

    private boolean isShared;
    private int likes;
    private double volume;
    private String name;
    private String description;
    private NutritionalValue nutritionalValue;
    private RecipeVisibility recipeVisibility;
    private RecipeComplexity complexity;
    private List<Byte[]> images;
    private List<MealProduct> mealProducts;
    private List<Comment> comments;

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

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

    @Override
    public void setNutritionalValue(NutritionalValue nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public RecipeVisibility getRecipeVisibility() {
        return recipeVisibility;
    }

    public void setRecipeVisibility(RecipeVisibility recipeVisibility) {
        this.recipeVisibility = recipeVisibility;
    }

    public RecipeComplexity getComplexity() {
        return complexity;
    }

    public void setComplexity(RecipeComplexity complexity) {
        this.complexity = complexity;
    }

    public List<Byte[]> getImages() {
        return images;
    }

    public void setImages(List<Byte[]> images) {
        this.images = images;
    }

    public List<MealProduct> getMealProducts() {
        return mealProducts;
    }

    public void setMealProducts(List<MealProduct> mealProducts) {
        this.mealProducts = mealProducts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        if (!super.equals(o)) return false;
        Recipe recipe = (Recipe) o;
        return isShared == recipe.isShared &&
                likes == recipe.likes &&
                Double.compare(recipe.volume, volume) == 0 &&
                Objects.equals(name, recipe.name) &&
                Objects.equals(description, recipe.description) &&
                Objects.equals(nutritionalValue, recipe.nutritionalValue) &&
                recipeVisibility == recipe.recipeVisibility &&
                complexity == recipe.complexity &&
                Objects.equals(images, recipe.images) &&
                Objects.equals(mealProducts, recipe.mealProducts) &&
                Objects.equals(comments, recipe.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), isShared, likes, volume, name, description, nutritionalValue, recipeVisibility, complexity, images, mealProducts, comments);
    }
}
