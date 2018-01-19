package org.avlasov.mrclevereat.entity.product;

import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created By artemvlasov on 19/01/2018
 **/
class ProductTest {

    @Test
    void getName() {
        assertEquals("Test", getProduct().getName());
    }

    @Test
    void getDescription() {
        assertEquals("Test", getProduct().getDescription());
    }

    @Test
    void getNutritionalValue() {
        assertNotNull(getProduct().getNutritionalValue());
    }

    @Test
    void getUsdaNumber() {
        assertEquals("Test", getProduct().getUsdaNumber());
    }

    @Test
    void builder() {
        new Product.ProductBuilder("Test");
    }

    @Test
    void equals() {
        assertTrue(getProduct().equals(getProduct()));
    }

    @Test
    void hashCodeTest() {
        assertEquals(getProduct().hashCode(), getProduct().hashCode());
    }

    private Product getProduct() {
        return Product.builder("Test")
                .nutritionalValue(NutritionalValue.builder().build())
                .description("Test")
                .usdaNumber("Test")
                .build();
    }

}