package org.avlasov.mrclevereat.entity.product;

import org.avlasov.mrclevereat.entity.Base;

/**
 * Created By artemvlasov on 22/12/2017
 **/
public class Product extends Base {

    private String name;
    private String description;
    private double protein;
    private double fat;
    private double carbohydrate;
    private double calories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
