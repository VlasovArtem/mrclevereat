package org.avlasov.mrclevereat.validation;

import org.avlasov.mrclevereat.entity.diet.DietData;
import org.avlasov.mrclevereat.entity.user.User;
import org.avlasov.mrclevereat.entity.user.enums.Gender;
import org.avlasov.mrclevereat.repository.UserRepository;
import org.avlasov.mrclevereat.testcase.MockitoJUnit5TestCase;
import org.avlasov.mrclevereat.validation.enums.UserErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created By artemvlasov on 20/01/2018
 **/
class UserEntityValidatorTest extends MockitoJUnit5TestCase {

    @Mock
    private UserRepository userRepository;
    @Mock
    private DietDataValidator dietDataValidator;
    @InjectMocks
    private UserEntityValidator userEntityValidator;

    @BeforeEach
    void setUp() {
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(dietDataValidator.validate(any(User.class))).thenReturn(ValidationResult.NO_ERRORS);
    }

    @Test
    void validate_WithValidaData_ReturnValidationResult() {
        ValidationResult validate = userEntityValidator.validate(getUserBuilder().build());
        assertFalse(validate.hasError());
    }

    @Test
    void validate_WithNullEmail_ReturnValidationResult() {
        ValidationResult validate = userEntityValidator.validate(User.builder(null, "test".getBytes()).build());
        assertTrue(validate.hasError());
        assertEquals(UserErrorCode.EMAIL.getCode(), validate.getErrorCode());
    }

    @Test
    void validate_WithShortEmail_ReturnValidationResult() {
        ValidationResult validate = userEntityValidator.validate(User.builder("t", "test".getBytes()).build());
        assertTrue(validate.hasError());
        assertEquals(UserErrorCode.EMAIL.getCode(), validate.getErrorCode());
    }

    @Test
    void validate_WithInvalidPatternEmail_ReturnValidationResult() {
        ValidationResult validate = userEntityValidator.validate(User.builder("test", "test".getBytes()).build());
        assertTrue(validate.hasError());
        assertEquals(UserErrorCode.EMAIL.getCode(), validate.getErrorCode());
    }

    @Test
    void validate_WithEmailExists_ReturnValidationResult() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        ValidationResult validate = userEntityValidator.validate(getUserBuilder().build());
        assertTrue(validate.hasError());
        assertEquals(UserErrorCode.ALREADY_EXISTS.getCode(), validate.getErrorCode());
    }

    @Test
    void validate_WithInvalidBirthDay_ReturnValidationResult() {
        ValidationResult validate = userEntityValidator.validate(getUserBuilder().birthday(LocalDate.now()).build());
        assertTrue(validate.hasError());
        assertEquals(UserErrorCode.BIRTHDAY_AGE.getCode(), validate.getErrorCode());
    }

    @Test
    void validate_WithNullBirthDay_ReturnValidationResult() {
        ValidationResult validate = userEntityValidator.validate(getUserBuilder().birthday(null).build());
        assertTrue(validate.hasError());
        assertEquals(UserErrorCode.BIRTHDAY_AGE.getCode(), validate.getErrorCode());
    }

    @Test
    void validate_WithInvalidAge_ReturnValidationResult() {
        ValidationResult validate = userEntityValidator.validate(getUserBuilder().age(200).build());
        assertTrue(validate.hasError());
        assertEquals(UserErrorCode.BIRTHDAY_AGE.getCode(), validate.getErrorCode());
    }

    @Test
    void validate_WithInvalidWeight_ReturnValidationResult() {
        ValidationResult validate = userEntityValidator.validate(getUserBuilder().weight(10).build());
        assertTrue(validate.hasError());
        assertEquals(UserErrorCode.WEIGHT.getCode(), validate.getErrorCode());
    }

    @Test
    void validate_WithInvalidHeight_ReturnValidationResult() {
        ValidationResult validate = userEntityValidator.validate(getUserBuilder().height((short) 10).build());
        assertTrue(validate.hasError());
        assertEquals(UserErrorCode.HEIGHT.getCode(), validate.getErrorCode());
    }

    private User.UserBuilder getUserBuilder() {
        return User.builder("test@mail.com", "test".getBytes())
                .height((short) 170)
                .gender(Gender.MALE)
                .age(30)
                .birthday(LocalDate.of(1982, 9, 5))
                .firstName("test")
                .lastName("test")
                .weight(90)
                .dietData(DietData.builder().build());
    }

}