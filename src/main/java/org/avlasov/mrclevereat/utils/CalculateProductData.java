package org.avlasov.mrclevereat.utils;

import org.avlasov.mrclevereat.entity.product.MealProduct;
import org.avlasov.mrclevereat.entity.ration.Meal;
import org.avlasov.mrclevereat.entity.recipe.Recipe;
import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Created By artemvlasov on 23/12/2017
 **/
public class CalculateProductData {

    /**
     * Calculate All required data from Meal, like {@link Meal#getRecipes()} and {@link Meal#getMealProducts()}
     *
     * @param meal Meal
     */
    public void calculateMealTotals(Meal meal) {
        Objects.requireNonNull(meal);
        NutritionalValueData nutritionalValueData = new NutritionalValueData();
        calculateMealProducts(meal.getMealProducts())
                .ifPresent(nvd -> mergeNutritionalValueData().apply(nutritionalValueData, nvd));
        calculateRecipes(meal.getRecipes())
                .ifPresent(nvd -> mergeNutritionalValueData().apply(nutritionalValueData, nvd));
        meal.setVolume(nutritionalValueData.nutritionalVolume);
        meal.setNutritionalValue(nutritionalValueData.nutritionalValue);
    }

    public void calculateRecipesTotals(Recipe recipe) {
        Objects.requireNonNull(recipe);
        Optional<NutritionalValueData> nutritionalValueData = calculateMealProducts(recipe.getMealProducts());
        if (nutritionalValueData.isPresent()) {
            recipe.setVolume(nutritionalValueData.get().nutritionalVolume);
            recipe.setNutritionalValue(nutritionalValueData.get().nutritionalValue);
        } else {
            recipe.setNutritionalValue(new NutritionalValue());
        }
    }

    private Optional<NutritionalValueData> calculateMealProducts(List<MealProduct> mealProducts) {
        return Optional.ofNullable(mealProducts)
                .orElse(Collections.emptyList())
                .parallelStream()
                .map(calculateMealProduct())
                .reduce(mergeNutritionalValueData());
    }

    private Optional<NutritionalValueData> calculateRecipes(List<Recipe> recipes) {
        return Optional.ofNullable(recipes)
                .orElse(Collections.emptyList())
                .parallelStream()
                .peek(this::calculateRecipesTotals)
                .map(recipe -> new NutritionalValueData(recipe.getNutritionalValue(), recipe.getVolume()))
                .reduce(mergeNutritionalValueData());
    }

    private Function<MealProduct, NutritionalValueData> calculateMealProduct() {
        return mp -> new NutritionalValueDataBuilder(mp.getVolume())
                    .buildOnBaseNutritionalValue(mp.getProduct().getNutritionalValue());
    }

    private BinaryOperator<NutritionalValueData> mergeNutritionalValueData() {
        return (identity, nvd) -> {
            mergeNutritionalValues(identity.nutritionalValue, nvd.nutritionalValue);
            identity.nutritionalVolume = identity.nutritionalVolume + nvd.nutritionalVolume;
            return identity;
        };
    }

    private void mergeNutritionalValues(NutritionalValue target, NutritionalValue source) {
        target.setCalories(target.getCalories() + source.getCalories());
        target.setCarbohydrate(target.getCarbohydrate() + source.getCarbohydrate());
        target.setProtein(target.getProtein() + source.getProtein());
        target.setFat(target.getFat() + source.getFat());
    }

    private class NutritionalValueData {
        private NutritionalValue nutritionalValue;
        private double nutritionalVolume;

        NutritionalValueData(NutritionalValue nutritionalValue, double nutritionalVolume) {
            this.nutritionalValue = nutritionalValue;
            this.nutritionalVolume = nutritionalVolume;
        }

        NutritionalValueData() {
            nutritionalValue = new NutritionalValue();
        }
    }

    private class NutritionalValueDataBuilder {

        private NutritionalValue nutritionalValue;
        private double volume;
        private double calculatedVolumePercent;

        NutritionalValueDataBuilder(double volume) {
            this.volume = volume;
            this.nutritionalValue = new NutritionalValue();
            calculatedVolumePercent = volume / 100;
        }

        NutritionalValueData buildOnBaseNutritionalValue(NutritionalValue baseNutritionalValue) {
            return this
                    .withProtein(baseNutritionalValue.getProtein())
                    .withFat(baseNutritionalValue.getFat())
                    .withCarbohydrate(baseNutritionalValue.getCarbohydrate())
                    .withCalories(baseNutritionalValue.getCalories())
                    .build();
        }

        NutritionalValueDataBuilder withProtein(double protein) {
            nutritionalValue.setProtein(protein * calculatedVolumePercent);
            return this;
        }

        NutritionalValueDataBuilder withFat(double fat) {
            nutritionalValue.setFat(fat * calculatedVolumePercent);
            return this;
        }

        NutritionalValueDataBuilder withCarbohydrate(double carbohydrate) {
            nutritionalValue.setCarbohydrate(carbohydrate * calculatedVolumePercent);
            return this;
        }

        NutritionalValueDataBuilder withCalories(double calories) {
            nutritionalValue.setCalories(calories * calculatedVolumePercent);
            return this;
        }

        NutritionalValueData build() {
            return new NutritionalValueData(nutritionalValue, volume);
        }

    }

}
