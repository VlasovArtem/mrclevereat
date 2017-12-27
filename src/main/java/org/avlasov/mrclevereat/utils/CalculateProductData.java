package org.avlasov.mrclevereat.utils;

import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.avlasov.mrclevereat.entity.product.MealProduct;
import org.avlasov.mrclevereat.entity.ration.Meal;
import org.avlasov.mrclevereat.entity.recipe.Recipe;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Created By artemvlasov on 23/12/2017
 */
@Component
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

    /**
     * Calculate recipes totals.
     *
     * @param recipe the recipe
     */
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

    /**
     * Calculate Meal Products {@link MealProduct}
     *
     * @param mealProducts Meal Products
     * @return {@link Optional} of {@link NutritionalValueData} is recipes is not empty, other wise {@link Optional#empty()}
     */
    private Optional<NutritionalValueData> calculateMealProducts(List<MealProduct> mealProducts) {
        return Optional.ofNullable(mealProducts)
                .orElse(Collections.emptyList())
                .parallelStream()
                .map(calculateMealProduct())
                .reduce(mergeNutritionalValueData());
    }

    /**
     * Calculate {@link NutritionalValueData} from list of recipes
     *
     * @param recipes List of recipes
     * @return {@link Optional} of {@link NutritionalValueData} is recipes is not empty, other wise {@link Optional#empty()}
     */
    private Optional<NutritionalValueData> calculateRecipes(List<Recipe> recipes) {
        return Optional.ofNullable(recipes)
                .orElse(Collections.emptyList())
                .parallelStream()
                .peek(this::calculateRecipesTotals)
                .map(recipe -> new NutritionalValueData(recipe.getNutritionalValue(), recipe.getVolume()))
                .reduce(mergeNutritionalValueData());
    }

    /**
     * Calculate Meal Product with {@link MealProduct#getVolume()} and {@link NutritionalValueDataBuilder#buildOnBaseNutritionalValue(NutritionalValue)}
     *
     * @return function
     */
    private Function<MealProduct, NutritionalValueData> calculateMealProduct() {
        return mp -> new NutritionalValueDataBuilder(mp.getVolume())
                .buildOnBaseNutritionalValue(mp.getProduct().getNutritionalValue());
    }

    /**
     * Merge Nutritional Value Data into on data. Merge {@link NutritionalValue} with help of {@link CalculateProductData#mergeNutritionalValues(NutritionalValue, NutritionalValue)}
     * and add volume of both {@link NutritionalValueData}
     *
     * @return BinaryOperator
     */
    private BinaryOperator<NutritionalValueData> mergeNutritionalValueData() {
        return (identity, nvd) -> {
            mergeNutritionalValues(identity.nutritionalValue, nvd.nutritionalValue);
            identity.nutritionalVolume = identity.nutritionalVolume + nvd.nutritionalVolume;
            return identity;
        };
    }

    /**
     * Merge {@link NutritionalValue} into one object. Add all required data from source to target component
     *
     * @param target {@link NutritionalValue}
     * @param source {@link NutritionalValue}
     */
    private void mergeNutritionalValues(NutritionalValue target, NutritionalValue source) {
        target.setCalories(target.getCalories() + source.getCalories());
        target.setCarbohydrate(target.getCarbohydrate() + source.getCarbohydrate());
        target.setProtein(target.getProtein() + source.getProtein());
        target.setFat(target.getFat() + source.getFat());
    }

    /**
     * Nutritional Value Data, that stores information about Nutritional Value for current object, like {@link Meal} and {@link Recipe}
     */
    private class NutritionalValueData {
        private NutritionalValue nutritionalValue;
        private double nutritionalVolume;

        /**
         * Instantiates a new Nutritional value data.
         *
         * @param nutritionalValue  the nutritional value
         * @param nutritionalVolume the nutritional volume
         */
        NutritionalValueData(NutritionalValue nutritionalValue, double nutritionalVolume) {
            this.nutritionalValue = nutritionalValue;
            this.nutritionalVolume = nutritionalVolume;
        }

        /**
         * Instantiates a new Nutritional value data.
         */
        NutritionalValueData() {
            nutritionalValue = new NutritionalValue();
        }
    }

    /**
     * Nutritional Value Data builder
     */
    private class NutritionalValueDataBuilder {

        private NutritionalValue nutritionalValue;
        private double volume;
        private double calculatedVolumePercent;

        /**
         * Instantiates a new Nutritional value data builder.
         *
         * @param volume the volume
         */
        NutritionalValueDataBuilder(double volume) {
            this.volume = volume;
            this.nutritionalValue = new NutritionalValue();
            calculatedVolumePercent = volume / 100;
        }

        /**
         * Build on base nutritional value.
         *
         * @param baseNutritionalValue the base nutritional value
         * @return the nutritional value data
         */
        NutritionalValueData buildOnBaseNutritionalValue(NutritionalValue baseNutritionalValue) {
            return this
                    .withProtein(baseNutritionalValue.getProtein())
                    .withFat(baseNutritionalValue.getFat())
                    .withCarbohydrate(baseNutritionalValue.getCarbohydrate())
                    .withCalories(baseNutritionalValue.getCalories())
                    .build();
        }


        /**
         * With protein nutritional value data builder. Set Protein data based on 100 grams of product.
         *
         * @param protein the protein
         * @return the nutritional value data builder
         */
        NutritionalValueDataBuilder withProtein(double protein) {
            nutritionalValue.setProtein(protein * calculatedVolumePercent);
            return this;
        }

        /**
         * With fat nutritional value data builder. Set fat data based on 100 grams of product
         *
         * @param fat the fat
         * @return the nutritional value data builder
         */
        NutritionalValueDataBuilder withFat(double fat) {
            nutritionalValue.setFat(fat * calculatedVolumePercent);
            return this;
        }

        /**
         * With carbohydrate nutritional value data builder. Set carbohydrate data based on 100 grams of product
         *
         * @param carbohydrate the carbohydrate
         * @return the nutritional value data builder
         */
        NutritionalValueDataBuilder withCarbohydrate(double carbohydrate) {
            nutritionalValue.setCarbohydrate(carbohydrate * calculatedVolumePercent);
            return this;
        }

        /**
         * With calories nutritional value data builder. Set calories data based on 100 grams of product
         *
         * @param calories the calories
         * @return the nutritional value data builder
         */
        NutritionalValueDataBuilder withCalories(double calories) {
            nutritionalValue.setCalories(calories * calculatedVolumePercent);
            return this;
        }

        /**
         * Build nutritional value data.
         *
         * @return the nutritional value data
         */
        NutritionalValueData build() {
            return new NutritionalValueData(nutritionalValue, volume);
        }

    }

}
