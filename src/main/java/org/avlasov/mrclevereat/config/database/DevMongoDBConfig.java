package org.avlasov.mrclevereat.config.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

/**
 * Created By artemvlasov on 27/12/2017
 **/
@Configuration
@EnableMongoRepositories(basePackages = {"org.avlasov.mrclevereat.repository"})
@Profile("dev")
public class DevMongoDBConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "mrclevereat";
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singletonList("org.avlasov.mrclevereat");
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(new MongoClientURI("mongodb://localhost"));
    }
}
