package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.entity.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created By artemvlasov on 25/12/2017
 **/
class UserRepositoryTest extends RepositoryTestCase {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail_WithExistingEmail_ReturnOptionalUser() {
        Optional<User> user = userRepository.findByEmail("test@mail.com");
        assertTrue(user.isPresent());
    }

    @Test
    void findByEmail_WithNotExistingEmail_ReturnEmptyOptional() {
        Optional<User> user = userRepository.findByEmail("test2@mail.com");
        assertFalse(user.isPresent());
    }

    @Test
    void existsByEmail_WithExistingEmail_ReturnTrue() {
        assertTrue(userRepository.existsByEmail("test@mail.com"));
    }

    @Test
    void existsByEmail_WithNotExistingEmail_ReturnFalse() {
        assertFalse(userRepository.existsByEmail("test2@mail.com"));
    }

}