package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.config.AppConfig;
import org.avlasov.mrclevereat.repository.config.EmbeddedMongoDatabaseConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

/**
 * Created By artemvlasov on 29/12/2017
 **/
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, EmbeddedMongoDatabaseConfig.class})
@DataMongoTest
@Disabled
abstract class RepositoryTestCase<R extends MongoRepository, T> {

    R mongoRepository;

    RepositoryTestCase(R mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    abstract Collection<T> getEntity();

    @BeforeEach
    void setUp() {
        mongoRepository.saveAll(getEntity());
    }

    @AfterEach
    void tearDown() {
        mongoRepository.deleteAll();
    }

}
