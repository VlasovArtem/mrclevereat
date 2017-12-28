package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.entity.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

/**
 * Created By artemvlasov on 25/12/2017
 **/
public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query(value = "{ email : ?0 }", fields = " { dietData : 1 } ")
    Optional<User> findDietDataByEmail(String email);
    @Query(value = "{ email : ?0 }", fields = " { 'dietData.weightLogs' : 1 } ")
    Optional<User> findWeightLogsByEmail(String email);
    @Query(value = "{ _id : ?0 }", fields = " { dietData : 1 } ")
    Optional<User> findDietDataById(ObjectId id);
    @Query(value = "{ _id : ?0 }", fields = " { 'dietData.weightLogs' : 1 } ")
    Optional<User> findWeightLogsById(ObjectId id);

}
