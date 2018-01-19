package org.avlasov.mrclevereat.entity.logs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created By artemvlasov on 19/01/2018
 **/
class WeightLogTest {

    @Test
    void getWeight() {
        assertEquals(10, new WeightLog(10).getWeight());
    }
}