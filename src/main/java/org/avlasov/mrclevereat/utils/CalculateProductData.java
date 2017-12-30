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
    public void calculateMealNutritionalValue(Meal meal) {
        Objects.requireNonNull(meal);
        NutritionalValueData nutritionalValueData = new NutritionalValueData();
        calculateMealProducts(meal.getMealProducts())
                .ifPresent(nvd -> mergeNutritionalValueData().apply(nutritionalValueData, nvd));
        calculateRecipes(meal.getRecipes())
                .ifPresent(nvd -> mergeNutritionalValueData().apply(nutritionalValueData, nvd));
        meal.setNutritionalValue(nutritionalValueData.nutritionalValue);
        meal.setVolume(nutritionalValueData.nutritionalVolume);
    }

    /**
     * Calculate recipes totals.
     *
     * @param recipe the recipe
     */
    public void calculateRecipeNutritionalValue(Recipe recipe) {
        Objects.requireNonNull(recipe);
        Optional<NutritionalValueData> nutritionalValueData = calculateMealProducts(recipe.getMealProducts());
        if (nutritionalValueData.isPresent()) {
            recipe.setVolume(nutritionalValueData.get().nutritionalVolume);
            recipe.setNutritionalValue(nutritionalValueData.get().nutritionalValue);
        } else {
            recipe.setNutritionalValue(NutritionalValue.builder().build());
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
                .peek(this::calculateRecipeNutritionalValue)
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
            identity.nutritionalValue = mergeNutritionalValues(identity.nutritionalValue, nvd.nutritionalValue);
            identity.nutritionalVolume = identity.nutritionalVolume + nvd.nutritionalVolume;
            return identity;
        };
    }

    /**
     * Merge {@link NutritionalValue} into one object. Add all required data from source to target component
     *
     * @param nv1 {@link NutritionalValue}
     * @param nv2 {@link NutritionalValue}
     */
    private NutritionalValue mergeNutritionalValues(NutritionalValue nv1, NutritionalValue nv2) {
        return NutritionalValue.builder()
                .calories(nv1.getCalories() + nv2.getCalories())
                .carbohydrate(nv1.getCarbohydrate() + nv2.getCarbohydrate())
                .fat(nv1.getFat() + nv2.getFat())
                .protein(nv1.getProtein() + nv2.getProtein())
                .build();
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
            nutritionalValue = NutritionalValue.builder().build();
        }
    }

    /**
     * Nutritional Value Data builder
     */
    private class NutritionalValueDataBuilder {

        private double volume;
        private double protein;
        private double fat;
        private double carbohydrate;
        private double calories;

        /**
         * Instantiates a new Nutritional value data builder.
         *
         * @param volume the volume
         */
        NutritionalValueDataBuilder(double volume) {
            this.volume = volume;
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
            this.protein = protein;
            return this;
        }

        /**
         * With fat nutritional value data builder. Set fat data based on 100 grams of product
         *
         * @param fat the fat
         * @return the nutritional value data builder
         */
        NutritionalValueDataBuilder withFat(double fat) {
            this.fat = fat;
            return this;
        }

        /**
         * With carbohydrate nutritional value data builder. Set carbohydrate data based on 100 grams of product
         *
         * @param carbohydrate the carbohydrate
         * @return the nutritional value data builder
         */
        NutritionalValueDataBuilder withCarbohydrate(double carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        /**
         * With calories nutritional value data builder. Set calories data based on 100 grams of product
         *
         * @param calories the calories
         * @return the nutritional value data builder
         */
        NutritionalValueDataBuilder withCalories(double calories) {
            this.calories = calories;
            return this;
        }

        /**
         * Build nutritional value data.
         *
         * @return the nutritional value data
         */
        NutritionalValueData build() {
            double volumeFactor = volume / 100;
            NutritionalValue nutritionalValue = NutritionalValue.builder()
                    .protein(protein * volumeFactor)
                    .carbohydrate(carbohydrate * volumeFactor)
                    .calories(calories * volumeFactor)
                    .fat(fat * volumeFactor)
                    .build();
            return new NutritionalValueData(nutritionalValue, volume);
        }

    }

}
