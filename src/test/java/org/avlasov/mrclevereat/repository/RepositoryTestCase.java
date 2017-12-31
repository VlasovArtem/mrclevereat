package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.repository.config.TestDataBase;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created By artemvlasov on 31/12/2017
 **/
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDataBase.class})
@DataJpaTest
@SqlGroup(value = {
        @Sql("/scripts/drop-data.sql"),
        @Sql("/scripts/data-h2.sql")
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryTestCase {}
