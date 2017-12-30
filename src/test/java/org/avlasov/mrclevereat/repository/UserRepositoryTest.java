package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.entity.user.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Disabled;

import java.util.Collection;
import java.util.Collections;

/**
 * Created By artemvlasov on 25/12/2017
 **/
@Disabled
class UserRepositoryTest {
    
    private static ObjectId id = ObjectId.get();

//    @Autowired
//    UserRepositoryTest(UserRepository userRepository) {
//        super(userRepository);
//    }

//    @Override
    Collection<User> getEntity() {
//        User user = new User("test@mail.com", "password".getBytes());
//        user.setId(id);
//        DietData dietData = new DietDataBuilder().createDietData();
//        dietData.setActivityScore((byte) 10);
//        dietData.setGramsPerWeek(1000);
//        dietData.setTargetWeight(90);
//        WeightLog weightLog = new WeightLog();
//        weightLog.setWeight(130);
//        weightLog.setCreatedDate(LocalDateTime.now());
//        dietData.setWeightLogs(Collections.singletonList(weightLog));
//        user.setDietData(dietData);
        return Collections.emptyList();
    }

//    @Test
//    void findByEmail_WithExistingEmail_ReturnOptionalUser() {
//        Optional<User> user = mongoRepository.findByEmail("test@mail.com");
//        assertTrue(user.isPresent());
//    }
//
//    @Test
//    void findByEmail_WithNotExistingEmail_ReturnEmptyOptional() {
//        Optional<User> user = mongoRepository.findByEmail("test2@mail.com");
//        assertFalse(user.isPresent());
//    }
//
//    @Test
//    void existsByEmail_WithExistingEmail_ReturnTrue() {
//        assertTrue(mongoRepository.existsByEmail("test@mail.com"));
//    }
//
//    @Test
//    void existsByEmail_WithNotExistingEmail_ReturnFalse() {
//        assertFalse(mongoRepository.existsByEmail("test2@mail.com"));
//    }

}