package org.avlasov.mrclevereat.validation;

/**
 * Created By artemvlasov on 20/01/2018
 **/
public interface EntityValidator<T> {

    ValidationResult validate(T t);

}
