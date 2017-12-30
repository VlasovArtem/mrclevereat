package org.avlasov.mrclevereat.entity.product;

import org.avlasov.mrclevereat.entity.Base;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;

/**
 * Created By artemvlasov on 23/12/2017. Contains information about current meal with product and volume of product.
 **/
@Entity
public class MealProduct extends Base {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    private double volume;

    public MealProduct(Product product, double volume) {
        this.product = product;
        this.volume = volume;
    }

    public Product getProduct() {
        return product;
    }

    public double getVolume() {
        return volume;
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
