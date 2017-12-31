package org.avlasov.mrclevereat.entity.product;

import javax.persistence.Entity;

/**
 * Created By artemvlasov on 31/12/2017
 **/
@Entity
public class RecipeProduct extends DataProduct {

    public RecipeProduct(Product product, double volume) {
        super(product, volume);
    }

}
