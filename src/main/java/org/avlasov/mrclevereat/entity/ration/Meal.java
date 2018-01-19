package org.avlasov.mrclevereat.entity.ration;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.avlasov.mrclevereat.entity.product.MealProduct;
import org.avlasov.mrclevereat.entity.recipe.Recipe;
import org.avlasov.mrclevereat.entity.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created By artemvlasov on 22/12/2017
 **/
@Entity
public class Meal extends Base {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private List<MealProduct> mealProducts;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private List<Recipe> recipes;
    @Embedded
    private NutritionalValue nutritionalValue;
    private double volume;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    Meal() {}

    Meal(List<MealProduct> mealProducts, List<Recipe> recipes, NutritionalValue nutritionalValue, double volume, User owner) {
        this.mealProducts = mealProducts;
        this.recipes = recipes;
        this.nutritionalValue = nutritionalValue;
        this.volume = volume;
        this.owner = owner;
    }

    public List<MealProduct> getMealProducts() {
        return new ArrayList<>(mealProducts);
    }

    public List<Recipe> getRecipes() {
        return new ArrayList<>(recipes);
    }

    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    public double getVolume() {
        return volume;
    }

    public static MealBuilder builder() {
        return new MealBuilder();
    }

    public static MealBuilder builder(Meal meal) {
        return new MealBuilder(meal);
    }

    public static class MealBuilder {

        private List<MealProduct> mealProducts;
        private List<Recipe> recipes;
        private NutritionalValue nutritionalValue;
        private double volume;
        private User owner;

        MealBuilder() {
        }

        MealBuilder(Meal meal) {
            mealProducts = meal.mealProducts;
            recipes = meal.recipes;
            nutritionalValue = meal.nutritionalValue;
            volume = meal.volume;
            owner = meal.owner;
        }

        public MealBuilder mealProducts(List<MealProduct> mealProducts) {
            this.mealProducts = mealProducts;
            return this;
        }

        public MealBuilder addMealProduct(MealProduct mealProduct) {
            if (Objects.isNull(mealProducts))
                mealProducts = new ArrayList<>();
            mealProducts.add(mealProduct);
            return this;
        }

        public MealBuilder recipes(List<Recipe> recipes) {
            this.recipes = recipes;
            return this;
        }

        public MealBuilder addRecipe(Recipe recipe) {
            if (Objects.isNull(recipes))
                recipes = new ArrayList<>();
            recipes.add(recipe);
            return this;
        }

        public MealBuilder nutritionalValue(NutritionalValue nutritionalValue) {
            this.nutritionalValue = nutritionalValue;
            return this;
        }

        public MealBuilder volume(double volume) {
            this.volume = volume;
            return this;
        }

        public MealBuilder owner(User owner) {
            this.owner = owner;
            return this;
        }

        public Meal build() {
            return new Meal(mealProducts, recipes, nutritionalValue, volume, owner);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;
        if (!super.equals(o)) return false;
        Meal meal = (Meal) o;
        return Double.compare(meal.volume, volume) == 0 &&
                Objects.equals(mealProducts, meal.mealProducts) &&
                Objects.equals(recipes, meal.recipes) &&
                Objects.equals(nutritionalValue, meal.nutritionalValue) &&
                Objects.equals(owner, meal.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mealProducts, recipes, nutritionalValue, volume, owner);
    }
}
