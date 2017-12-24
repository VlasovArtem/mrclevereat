package org.avlasov.mrclevereat.entity.product;

import java.util.Objects;

/**
 * Created By artemvlasov on 23/12/2017. Contains information about current meal with product and volume of product.
 **/
public class MealProduct {

    private Product product;
    private double volume;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealProduct)) return false;
        MealProduct that = (MealProduct) o;
        return Double.compare(that.volume, volume) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {

        return Objects.hash(product, volume);
    }
}
