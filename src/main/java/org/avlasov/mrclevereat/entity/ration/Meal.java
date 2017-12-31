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

    private Meal() {}

    public List<MealProduct> getMealProducts() {
        return mealProducts;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    public double getVolume() {
        return volume;
    }

    public User getOwner() {
        return owner;
    }

    public void setNutritionalValue(NutritionalValue nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public static MealBuilder builder() {
        return new MealBuilder();
    }

    public static class MealBuilder {

        private List<MealProduct> mealProducts;
        private List<Recipe> recipes;
        private NutritionalValue nutritionalValue;
        private double volume;
        private User owner;

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
            Meal meal = new Meal();
            meal.mealProducts = mealProducts;
            meal.recipes = recipes;
            meal.nutritionalValue = nutritionalValue;
            meal.volume = volume;
            meal.owner = owner;
            return meal;
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
