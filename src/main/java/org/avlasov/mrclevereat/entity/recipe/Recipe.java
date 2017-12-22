package org.avlasov.mrclevereat.entity.recipe;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.product.Product;
import org.avlasov.mrclevereat.entity.recipe.enums.RecipeVisibility;

import java.util.List;

/**
 * Created By artemvlasov on 22/12/2017
 **/
public class Recipe extends Base {

    private String name;
    private String description;
    private boolean isShared;
    private List<Product> products;
    private double totalProteins;
    private double totalFats;
    private double totalCarbohydrates;
    private RecipeVisibility recipeVisibility;
    private List<Byte[]> images;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalProteins() {
        return totalProteins;
    }

    public void setTotalProteins(double totalProteins) {
        this.totalProteins = totalProteins;
    }

    public double getTotalFats() {
        return totalFats;
    }

    public void setTotalFats(double totalFats) {
        this.totalFats = totalFats;
    }

    public double getTotalCarbohydrates() {
        return totalCarbohydrates;
    }

    public void setTotalCarbohydrates(double totalCarbohydrates) {
        this.totalCarbohydrates = totalCarbohydrates;
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
}
