package org.avlasov.mrclevereat.validation.spring;

import org.avlasov.mrclevereat.testcase.MockitoJUnit5TestCase;
import org.avlasov.mrclevereat.validation.EntityValidator;
import org.avlasov.mrclevereat.validation.ValidationResult;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created By artemvlasov on 20/01/2018
 **/
class SpringValidatorTest extends MockitoJUnit5TestCase {

    @Mock
    private EntityValidator<Object> validator;
    @Mock
    private Errors errors;
    @InjectMocks
    private TestSpringValidator springValidator;

    @Test
    void getClazz() {
        assertEquals(Object.class, springValidator.getClazz());
    }

    @Test
    void supports() {
        assertTrue(springValidator.supports(Object.class));
    }

    @Test
    void validate_WithValidData() {
        when(validator.validate(any())).thenReturn(ValidationResult.NO_ERRORS);
        springValidator.validate(new Object(), errors);
        verify(errors, times(0)).reject("test", null, "");
    }

    @Test
    void validate_WithError() {
        when(validator.validate(any())).thenReturn(ValidationResult.createErrorValidationResult("test"));
        springValidator.validate(new Object(), errors);
        verify(errors).reject("test", null, "");
    }

    private static class TestSpringValidator extends SpringValidator<Object> {

        TestSpringValidator(EntityValidator<Object> entityValidator) {
            super(entityValidator);
        }

        @Override
        Class<Object> getClazz() {
            return Object.class;
        }


    }

}