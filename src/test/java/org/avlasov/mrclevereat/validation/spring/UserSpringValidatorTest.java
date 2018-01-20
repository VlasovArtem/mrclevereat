package org.avlasov.mrclevereat.validation.spring;

import org.avlasov.mrclevereat.entity.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created By artemvlasov on 20/01/2018
 **/
class UserSpringValidatorTest {

    @Test
    void getClazz() {
        assertEquals(User.class, new UserSpringValidator(null).getClazz());
    }
}