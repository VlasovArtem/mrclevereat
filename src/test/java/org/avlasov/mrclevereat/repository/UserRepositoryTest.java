package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.config.AppConfig;
import org.avlasov.mrclevereat.entity.diet.DietData;
import org.avlasov.mrclevereat.entity.user.User;
import org.avlasov.mrclevereat.repository.config.EmbeddedMongoDatabaseConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created By artemvlasov on 25/12/2017
 **/
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, EmbeddedMongoDatabaseConfig.class})
@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = new User("test@mail.com", "password".getBytes());
        DietData dietData = new DietData();
        dietData.setActivityScore((byte) 10);
        dietData.setGramsPerWeek(1000);
        dietData.setTargetWeight(90);
        user.setDietData(dietData);
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

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

    @Test
    void findDietDataByEmail_WithExistingEmail_ReturnUserObjectWithDietDataOnly() {
        Optional<User> dietDataByEmail = userRepository.findDietDataByEmail("test@mail.com");
        assertTrue(dietDataByEmail.isPresent());
        assertNotNull(dietDataByEmail.get().getDietData());
        assertNull(dietDataByEmail.get().getEmail());
    }

    @Test
    void findDietDataByEmail_WithNotExistingEmail_ReturnEmptyOptional() {
        Optional<User> dietDataByEmail = userRepository.findDietDataByEmail("test2@mail.com");
        assertFalse(dietDataByEmail.isPresent());
    }

}