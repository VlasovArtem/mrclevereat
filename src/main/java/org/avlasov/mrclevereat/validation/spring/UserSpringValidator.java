package org.avlasov.mrclevereat.validation.spring;

import org.avlasov.mrclevereat.entity.user.User;
import org.avlasov.mrclevereat.validation.UserEntityValidator;
import org.springframework.stereotype.Component;

/**
 * Created By artemvlasov on 20/01/2018
 **/
@Component
public class UserSpringValidator extends SpringValidator<User> {

    public UserSpringValidator(UserEntityValidator entityValidator) {
        super(entityValidator);
    }

    @Override
    Class<User> getClazz() {
        return User.class;
    }

}
