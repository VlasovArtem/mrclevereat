package org.avlasov.mrclevereat.validation;

/**
 * Created By artemvlasov on 20/01/2018
 * For Error code please reference to resources/validation/errormsg
 **/
public class ValidationResult {

    public static final ValidationResult NO_ERRORS = new ValidationResult();
    private boolean hasError;
    private String errorCode;
    private Object[] args;

    ValidationResult() {}

    ValidationResult(boolean hasError, String errorCode, Object[] args) {
        this.hasError = hasError;
        this.errorCode = errorCode;
        this.args = args;
    }

    public static ValidationResult createErrorValidationResult(String errorCode) {
        return new ValidationResult(true, errorCode, null);
    }

    public static ValidationResult createErrorValidationResult(String errorCode, Object[] args) {
        return new ValidationResult(true, errorCode, args);
    }

    public boolean hasError() {
        return hasError;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }

}
