package org.avlasov.mrclevereat.validation.spring;

import org.avlasov.mrclevereat.validation.EntityValidator;
import org.avlasov.mrclevereat.validation.ValidationResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created By artemvlasov on 20/01/2018
 **/
public abstract class SpringValidator<T> implements Validator {

    private EntityValidator<T> entityValidator;

    SpringValidator(EntityValidator<T> entityValidator) {
        this.entityValidator = entityValidator;
    }

    abstract Class<T> getClazz();

    @Override
    public boolean supports(Class<?> clazz) {
        return getClazz().isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationResult validate = entityValidator.validate((T) target);
        if (validate.hasError()) {
            errors.reject(validate.getErrorCode(), validate.getArgs(), "");
        }
    }
}
