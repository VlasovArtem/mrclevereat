package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.entity.diet.DietData;
import org.avlasov.mrclevereat.entity.logs.WeightLog;
import org.avlasov.mrclevereat.entity.user.User;
import org.bson.types.ObjectId;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created By artemvlasov on 25/12/2017
 **/
class UserRepositoryTest extends RepositoryTestCase<UserRepository, User> {
    
    private static ObjectId id = ObjectId.get();

    @Autowired
    UserRepositoryTest(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    Collection<User> getEntity() {
        User user = new User("test@mail.com", "password".getBytes());
        user.setId(id);
        DietData dietData = new DietData();
        dietData.setActivityScore((byte) 10);
        dietData.setGramsPerWeek(1000);
        dietData.setTargetWeight(90);
        WeightLog weightLog = new WeightLog();
        weightLog.setWeight(130);
        weightLog.setCreatedDate(LocalDateTime.now());
        dietData.setWeightLogs(Collections.singletonList(weightLog));
        user.setDietData(dietData);
        return Collections.singleton(user);
    }

    @Test
    void findByEmail_WithExistingEmail_ReturnOptionalUser() {
        Optional<User> user = mongoRepository.findByEmail("test@mail.com");
        assertTrue(user.isPresent());
    }

    @Test
    void findByEmail_WithNotExistingEmail_ReturnEmptyOptional() {
        Optional<User> user = mongoRepository.findByEmail("test2@mail.com");
        assertFalse(user.isPresent());
    }

    @Test
    void existsByEmail_WithExistingEmail_ReturnTrue() {
        assertTrue(mongoRepository.existsByEmail("test@mail.com"));
    }

    @Test
    void existsByEmail_WithNotExistingEmail_ReturnFalse() {
        assertFalse(mongoRepository.existsByEmail("test2@mail.com"));
    }

    @Test
    void findDietDataByEmail_WithExistingEmail_ReturnUserObjectWithDietDataOnly() {
        Optional<User> dietDataByEmail = mongoRepository.findDietDataByEmail("test@mail.com");
        assertTrue(dietDataByEmail.isPresent());
        assertNotNull(dietDataByEmail.get().getDietData());
        assertNull(dietDataByEmail.get().getEmail());
    }

    @Test
    void findDietDataByEmail_WithNotExistingEmail_ReturnEmptyOptional() {
        Optional<User> dietDataByEmail = mongoRepository.findDietDataByEmail("test2@mail.com");
        assertFalse(dietDataByEmail.isPresent());
    }

    @Test
    void findWeightLogsByEmail_WithExistingEmail_ReturnOptionalUserWithWeightLog() {
        Optional<User> weightLogsByEmail = mongoRepository.findWeightLogsByEmail("test@mail.com");
        assertTrue(weightLogsByEmail.isPresent());
        assertNull(weightLogsByEmail.get().getEmail());
        assertThat(weightLogsByEmail.get().getDietData().getWeightLogs(), IsCollectionWithSize.hasSize(1));
    }

    @Test
    void findWeightLogsByEmail_WithNotExistingEmail_ReturnEmptyOptional() {
        Optional<User> weightLogsByEmail = mongoRepository.findWeightLogsByEmail("test2@mail.com");
        assertFalse(weightLogsByEmail.isPresent());
    }

    @Test
    void findDietDataById_WithExistingId_ReturnUserDietData() {
        Optional<User> dietDataById = mongoRepository.findDietDataById(id);
        assertTrue(dietDataById.isPresent());
        assertNotNull(dietDataById.get().getDietData());
        assertNull(dietDataById.get().getEmail());
    }

    @Test
    void findDietDataById_WithNotExistingId_ReturnEmptyOptional() {
        Optional<User> dietDataById = mongoRepository.findDietDataById(ObjectId.get());
        assertFalse(dietDataById.isPresent());
    }

    @Test
    void findWeightLogsById_WithExistingId_ReturnUserWithWeightLog() {
        Optional<User> weightLogsByEmail = mongoRepository.findWeightLogsById(id);
        assertTrue(weightLogsByEmail.isPresent());
        assertNull(weightLogsByEmail.get().getEmail());
        assertThat(weightLogsByEmail.get().getDietData().getWeightLogs(), IsCollectionWithSize.hasSize(1));
    }

    @Test
    void findWeightLogsById_WithNotExistingId_ReturnEmptyOptional() {
        Optional<User> weightLogsByEmail = mongoRepository.findWeightLogsById(ObjectId.get());
        assertFalse(weightLogsByEmail.isPresent());
    }

}