package org.avlasov.mrclevereat.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Created By artemvlasov on 20/01/2018
 **/
class ValidationResultTest {

    private ValidationResult validationResult;

    @BeforeEach
    void setUp() {
        validationResult = new ValidationResult(false, "test", new Object[]{"test"}, Locale.ENGLISH);
    }

    @Test
    void hasError() {
        assertFalse(validationResult.hasError());
    }

    @Test
    void getErrorCode() {
        assertEquals("test", validationResult.getErrorCode());
    }

    @Test
    void getArg() {
        assertArrayEquals(new Object[]{"test"}, validationResult.getArg());
    }

    @Test
    void getLocale() {
        assertEquals(Locale.ENGLISH, validationResult.getLocale());
    }
}