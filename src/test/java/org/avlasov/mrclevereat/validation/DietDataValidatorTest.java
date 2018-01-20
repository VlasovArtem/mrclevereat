package org.avlasov.mrclevereat.validation;

import org.avlasov.mrclevereat.entity.diet.DietData;
import org.avlasov.mrclevereat.entity.user.User;
import org.avlasov.mrclevereat.entity.user.enums.Gender;
import org.avlasov.mrclevereat.testcase.MockitoJUnit5TestCase;
import org.avlasov.mrclevereat.utils.TargetWeightCalculator;
import org.avlasov.mrclevereat.validation.enums.DietDataErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created By artemvlasov on 20/01/2018
 **/
class DietDataValidatorTest extends MockitoJUnit5TestCase {

    @Mock
    private TargetWeightCalculator targetWeightCalculator;
    @InjectMocks
    private DietDataValidator dietDataValidator;

    @BeforeEach
    void setUp() {
        when(targetWeightCalculator.calculateTargetWeightByBrokkFormula(any(User.class))).thenReturn((double) 80);
    }

    @Test
    void validate_WithNoErrors_ReturnValidationResult() {
        ValidationResult validate = dietDataValidator.validate(getUserBuilder().build());
        assertFalse(validate.hasError());
    }

    @Test
    void validate_WithInvalidActivityScore_ReturnValidationResult() {
        ValidationResult validate = dietDataValidator.validate(getUserBuilder().dietData(getDietDataBuilder().activityScore((byte) 0).build()).build());
        assertTrue(validate.hasError());
        assertEquals(DietDataErrorCode.ACTIVITY_SCORE.getCode(), validate.getErrorCode());
    }

    @Test
    void validate_WithInvalidGramsPerWeek_ReturnValidationResult() {
        ValidationResult validate = dietDataValidator.validate(getUserBuilder().dietData(getDietDataBuilder().gramsPerWeek(3000).build()).build());
        assertTrue(validate.hasError());
        assertEquals(DietDataErrorCode.GRAMS_PER_WEEK.getCode(), validate.getErrorCode());
    }

    @Test
    void validate_WithInvalidTargetWeight_ReturnValidationResult() {
        ValidationResult validate = dietDataValidator.validate(getUserBuilder().dietData(getDietDataBuilder().targetWeight(69).build()).build());
        assertTrue(validate.hasError());
        assertEquals(DietDataErrorCode.TARGET_WEIGHT.getCode(), validate.getErrorCode());
    }

    private User.UserBuilder getUserBuilder() {
        return User.builder("test", "test".getBytes())
                .height((short) 170)
                .gender(Gender.MALE)
                .weight(120)
                .dietData(getDietDataBuilder().build());
    }

    private DietData.DietDataBuilder getDietDataBuilder() {
        return DietData.builder().targetWeight(80).gramsPerWeek(1000).activityScore((byte) 6);
    }

}