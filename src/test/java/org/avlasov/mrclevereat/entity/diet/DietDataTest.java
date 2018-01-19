package org.avlasov.mrclevereat.entity.diet;

import org.avlasov.mrclevereat.entity.logs.WeightLog;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created By artemvlasov on 19/01/2018
 **/
class DietDataTest {

    @Test
    void defaultConstructor() {
        new DietData();
    }

    @Test
    void getActivityScore() {
        assertEquals(10, getBuilder().build().getActivityScore());
    }

    @Test
    void getTargetWeight() {
        assertEquals(80, getBuilder().build().getTargetWeight());
    }

    @Test
    void getGramsPerWeek() {
        assertEquals(1000, getBuilder().build().getGramsPerWeek());
    }

    @Test
    void getWeightLogs() {
        assertThat(getBuilder().build().getWeightLogs(), IsCollectionWithSize.hasSize(1));
    }

    @Test
    void builder() {
        assertNotNull(getBuilder().build());
    }

    @Test
    void equals() {
        assertTrue(getBuilder().build().equals(getBuilder().build()));
    }

    @Test
    void hashCodeTest() {
        assertEquals(getBuilder().build().hashCode(), getBuilder().build().hashCode());
    }

    private DietData.DietDataBuilder getBuilder() {
        return DietData.builder()
                .activityScore((byte) 10)
                .gramsPerWeek(1000)
                .targetWeight(80)
                .weightLogs(Collections.singletonList(new WeightLog(110)));
    }

}