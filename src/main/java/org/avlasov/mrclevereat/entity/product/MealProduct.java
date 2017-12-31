package org.avlasov.mrclevereat.entity.product;

import javax.persistence.Entity;

/**
 * Created By artemvlasov on 23/12/2017. Contains information about current meal with product and volume of product.
 **/
@Entity
public class MealProduct extends DataProduct {

    public MealProduct(Product product, double volume) {
        super(product, volume);
    }

}
