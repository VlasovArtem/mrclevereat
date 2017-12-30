package org.avlasov.mrclevereat.config;

import org.avlasov.mrclevereat.config.database.DevDataSourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created By artemvlasov on 25/12/2017
 **/
@Configuration
@ComponentScan(basePackages = {"org.avlasov.mrclevereat.utils"})
@Import(DevDataSourceConfig.class)
public class AppConfig {
}
