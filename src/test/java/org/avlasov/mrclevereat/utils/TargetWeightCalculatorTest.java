package org.avlasov.mrclevereat.utils;

import org.avlasov.mrclevereat.entity.user.User;
import org.avlasov.mrclevereat.entity.user.enums.Gender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created By artemvlasov on 20/01/2018
 **/
class TargetWeightCalculatorTest {

    private TargetWeightCalculator targetWeightCalculator = new TargetWeightCalculator();

    @Test
    void calculateTargetWeightByBrokkFormula_WithMaleUser_ReturnDouble() {
        User user = getUserBuilder()
                .gender(Gender.MALE)
                .height((short) 170)
                .build();
        assertEquals(80.5, targetWeightCalculator.calculateTargetWeightByBrokkFormula(user));
    }

    @Test
    void calculateTargetWeightByBrokkFormula_WithFemaleUser_ReturnDouble() {
        User user = getUserBuilder()
                .gender(Gender.FEMALE)
                .height((short) 170)
                .build();
        assertEquals(69, targetWeightCalculator.calculateTargetWeightByBrokkFormula(user));
    }

    @Test
    void calculateTargetWeightByBrokkFormula_WithNullUser_ThrownException() {
        assertThrows(NullPointerException.class, () -> targetWeightCalculator.calculateTargetWeightByBrokkFormula(null));
    }

    private User.UserBuilder getUserBuilder() {
        return User.builder("test", "test".getBytes());
    }

}