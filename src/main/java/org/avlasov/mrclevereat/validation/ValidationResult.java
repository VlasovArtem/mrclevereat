package org.avlasov.mrclevereat.validation;

/**
 * Created By artemvlasov on 20/01/2018
 * For Error code please reference to resources/validation/errormsg
 **/
public class ValidationResult {

    public static final ValidationResult NO_ERRORS = new ValidationResult();
    private boolean hasError;
    private String errorCode;
    private Object[] arg;

    ValidationResult() {}

    ValidationResult(boolean hasError, String errorCode, Object[] arg) {
        this.hasError = hasError;
        this.errorCode = errorCode;
        this.arg = arg;
    }

    public boolean hasError() {
        return hasError;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getArg() {
        return arg;
    }

}
