package org.avlasov.mrclevereat.entity.product;

import org.avlasov.mrclevereat.entity.Base;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;

/**
 * Created By artemvlasov on 31/12/2017
 **/
public abstract class DataProduct extends Base {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    private double volume;

    DataProduct(Product product, double volume) {
        this.product = product;
        this.volume = volume;
    }

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
        if (!(o instanceof DataProduct)) return false;
        if (!super.equals(o)) return false;
        DataProduct that = (DataProduct) o;
        return Double.compare(that.volume, volume) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), product, volume);
    }
}
