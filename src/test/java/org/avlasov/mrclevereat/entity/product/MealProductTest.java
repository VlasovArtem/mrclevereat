package org.avlasov.mrclevereat.entity.product;

import org.avlasov.mrclevereat.entity.nutrition.NutritionalValue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created By artemvlasov on 19/01/2018
 **/
class MealProductTest {

    private static MealProduct mealProduct;

    @BeforeAll
    static void init() {
        mealProduct = getMealProduct();
    }

    @Test
    void getProduct() {
        assertNotNull(mealProduct.getProduct());
    }

    @Test
    void getVolume() {
        assertEquals(120, mealProduct.getVolume());
    }

    @Test
    void equals() {
        assertTrue(mealProduct.equals(getMealProduct()));
    }

    @Test
    void hashCodeTest() {
        assertEquals(mealProduct.hashCode(), getMealProduct().hashCode());
    }

    @Test
    void getId() {
        assertEquals(0, mealProduct.getId());
    }

    @Test
    void getCreatedDate() {
        assertNull(mealProduct.getCreatedDate());
    }

    @Test
    void getModifiedDate() {
        assertNull(mealProduct.getModifiedDate());
    }

    @Test
    void getCreatedBy() {
        assertEquals(0, mealProduct.getCreatedBy());
    }

    @Test
    void getModifiedBy() {
        assertEquals(0, mealProduct.getModifiedBy());
    }

    @Test
    void isDeleted() {
        assertFalse(mealProduct.isDeleted());
    }

    private static MealProduct getMealProduct() {
        return new MealProduct(
                Product.builder("test")
                        .description("test")
                        .nutritionalValue(NutritionalValue.builder().calories(10).build())
                        .usdaNumber("test")
                        .build(),
                120);
    }


}