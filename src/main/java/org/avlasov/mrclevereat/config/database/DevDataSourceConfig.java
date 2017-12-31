package org.avlasov.mrclevereat.config.database;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;

/**
 * Created By artemvlasov on 27/12/2017
 **/
@Configuration
@EnableJpaRepositories(basePackages = {"org.avlasov.mrclevereat.repository"})
@Profile("dev")
public class DevDataSourceConfig {

    @Bean(destroyMethod = "shutdown")
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("scripts/schema-h2.sql")
                .addScripts("scripts/data-h2.sql")
                .build();
    }

    /**
     * Link example jdbc:h2:tcp://localhost:9092/mem:testdb
     * @return
     * @throws SQLException
     */
    @Bean
    public Server server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
    }

}
