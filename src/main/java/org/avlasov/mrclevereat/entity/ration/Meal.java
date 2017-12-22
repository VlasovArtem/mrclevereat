package org.avlasov.mrclevereat.entity.ration;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.product.Product;
import org.avlasov.mrclevereat.entity.recipe.Recipe;

import java.util.List;

/**
 * Created By artemvlasov on 22/12/2017
 **/
public class Meal extends Base {

    private List<Product> products;
    private List<Recipe> recipes;
    private double totalProteins;
    private double totalFats;
    private double totalCarbohydrates;
    private double totalCalories;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
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

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

}
