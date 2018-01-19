package org.avlasov.mrclevereat.entity.product;

import org.junit.jupiter.api.Test;

/**
 * Created By artemvlasov on 19/01/2018
 **/
class RecipeProductTest {

    @Test
    void constructor() {
        new RecipeProduct(Product.builder("test").build(), 10);
    }
}