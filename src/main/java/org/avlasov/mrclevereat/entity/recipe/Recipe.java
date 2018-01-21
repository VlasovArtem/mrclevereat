package org.avlasov.mrclevereat.entity.recipe;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.avlasov.mrclevereat.entity.product.RecipeProduct;
import org.avlasov.mrclevereat.entity.recipe.enums.RecipeVisibility;
import org.avlasov.mrclevereat.entity.user.User;

import javax.persistence.*;
import java.util.*;

/**
 * Created By artemvlasov on 22/12/2017
 **/
@Entity
@Table(indexes = {
        @Index(name = "RCP_NAME_IDX", columnList = "name")
})
public class Recipe extends Base {

    private double volume;
    @Column(unique = true)
    private String name;
    private String description;
    @Embedded
    private NutritionalValue nutritionalValue;
    @Enumerated(EnumType.STRING)
    private RecipeVisibility recipeVisibility;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private List<RecipeProduct> recipeProducts;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    Recipe() {
    }

    Recipe(double volume, String name, String description, NutritionalValue nutritionalValue, RecipeVisibility recipeVisibility, List<RecipeProduct> recipeProducts) {
        this.volume = volume;
        this.name = name;
        this.description = description;
        this.nutritionalValue = nutritionalValue;
        this.recipeVisibility = recipeVisibility;
        this.recipeProducts = recipeProducts;
    }

    public double getVolume() {
        return volume;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    public RecipeVisibility getRecipeVisibility() {
        return recipeVisibility;
    }

    public List<RecipeProduct> getRecipeProducts() {
        return new ArrayList<>(Optional.ofNullable(recipeProducts).orElseGet(Collections::emptyList));
    }

    public static RecipeBuilder builder() {
        return new RecipeBuilder();
    }

    public static RecipeBuilder builder(Recipe recipe) {
        return new RecipeBuilder(recipe);
    }

    public static class RecipeBuilder {

        private double volume;
        private String name;
        private String description;
        private NutritionalValue nutritionalValue;
        private RecipeVisibility recipeVisibility;
        private List<RecipeProduct> recipeProducts;

        RecipeBuilder(Recipe recipe) {
            volume = recipe.volume;
            name = recipe.name;
            description = recipe.description;
            nutritionalValue = recipe.nutritionalValue;
            recipeVisibility = recipe.recipeVisibility;
            recipeProducts = recipe.recipeProducts;
        }

        RecipeBuilder() {}

        public RecipeBuilder volume(double volume) {
            this.volume = volume;
            return this;
        }

        public RecipeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RecipeBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RecipeBuilder nutritionalValue(NutritionalValue nutritionalValue) {
            this.nutritionalValue = nutritionalValue;
            return this;
        }

        public RecipeBuilder recipeVisibility(RecipeVisibility recipeVisibility) {
            this.recipeVisibility = recipeVisibility;
            return this;
        }

        public RecipeBuilder mealProducts(List<RecipeProduct> mealProducts) {
            this.recipeProducts = mealProducts;
            return this;
        }

        public RecipeBuilder addMealProduct(RecipeProduct recipeProduct) {
            if (Objects.isNull(recipeProducts))
                recipeProducts = new ArrayList<>();
            recipeProducts.add(recipeProduct);
            return this;
        }

        public Recipe build() {
            return new Recipe(volume, name, description, nutritionalValue, recipeVisibility, recipeProducts);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return Double.compare(recipe.volume, volume) == 0 &&
                Objects.equals(name, recipe.name) &&
                Objects.equals(description, recipe.description) &&
                Objects.equals(nutritionalValue, recipe.nutritionalValue) &&
                recipeVisibility == recipe.recipeVisibility &&
                Objects.equals(recipeProducts, recipe.recipeProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, name, description, nutritionalValue, recipeVisibility, recipeProducts);
    }
}
