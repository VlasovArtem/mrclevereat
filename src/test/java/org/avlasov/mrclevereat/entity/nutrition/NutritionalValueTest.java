package org.avlasov.mrclevereat.entity.nutrition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created By artemvlasov on 19/01/2018
 **/
class NutritionalValueTest {

    @Test
    void defaultConstructor() {
        new NutritionalValue();
    }

    @Test
    void getProtein() {
        assertEquals(32, getBuilder().build().getProtein());
    }

    @Test
    void getFat() {
        assertEquals(3, getBuilder().build().getFat());
    }

    @Test
    void getCarbohydrate() {
        assertEquals(100, getBuilder().build().getCarbohydrate());
    }

    @Test
    void getCalories() {
        assertEquals(160, getBuilder().build().getCalories());
    }

    @Test
    void builder() {
        assertNotNull(getBuilder());
    }

    @Test
    void equals() {
        assertTrue(getBuilder().build().equals(getBuilder().build()));
    }

    @Test
    void hashCodeTest() {
        assertEquals(getBuilder().build().hashCode(), getBuilder().build().hashCode());
    }

    private NutritionalValue.NutritionalValueBuilder getBuilder() {
        return NutritionalValue.builder()
                .calories(160)
                .carbohydrate(100)
                .fat(3)
                .protein(32);
    }

}