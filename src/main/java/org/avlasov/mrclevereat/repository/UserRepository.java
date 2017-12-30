package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created By artemvlasov on 25/12/2017
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}
