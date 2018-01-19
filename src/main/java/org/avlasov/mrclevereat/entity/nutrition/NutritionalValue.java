package org.avlasov.mrclevereat.entity.nutrition;

import java.util.Objects;

/**
 * Created By artemvlasov on 23/12/2017
 * data for 100 grams of product
 **/
public class NutritionalValue {

    private double protein;
    private double fat;
    private double carbohydrate;
    private double calories;

    NutritionalValue() {}

    NutritionalValue(double protein, double fat, double carbohydrate, double calories) {
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public double getCalories() {
        return calories;
    }

    public static NutritionalValueBuilder builder() {
        return new NutritionalValueBuilder();
    }

    public static class NutritionalValueBuilder {
        private double protein;
        private double fat;
        private double carbohydrate;
        private double calories;

        public NutritionalValueBuilder protein(double protein) {
            this.protein = protein;
            return this;
        }

        public NutritionalValueBuilder fat(double fat) {
            this.fat = fat;
            return this;
        }

        public NutritionalValueBuilder carbohydrate(double carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutritionalValueBuilder calories(double calories) {
            this.calories = calories;
            return this;
        }

        public NutritionalValue build() {
            return new NutritionalValue(protein, fat, carbohydrate, calories);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NutritionalValue)) return false;
        NutritionalValue that = (NutritionalValue) o;
        return Double.compare(that.protein, protein) == 0 &&
                Double.compare(that.fat, fat) == 0 &&
                Double.compare(that.carbohydrate, carbohydrate) == 0 &&
                Double.compare(that.calories, calories) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(protein, fat, carbohydrate, calories);
    }
}
