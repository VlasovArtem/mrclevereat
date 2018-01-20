package org.avlasov.mrclevereat.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created By artemvlasov on 20/01/2018
 **/
class ValidationResultTest {

    private ValidationResult validationResult;

    @BeforeEach
    void setUp() {
        validationResult = new ValidationResult(false, "test", new Object[]{"test"});
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

}