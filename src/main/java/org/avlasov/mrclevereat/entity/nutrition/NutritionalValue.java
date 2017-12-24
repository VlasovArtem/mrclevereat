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

    public NutritionalValue() {
    }

    public NutritionalValue(double protein, double fat, double carbohydrate, double calories) {
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
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
