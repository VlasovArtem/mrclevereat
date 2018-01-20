package org.avlasov.mrclevereat.config;

import org.avlasov.mrclevereat.config.database.DevDataSourceConfig;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Created By artemvlasov on 25/12/2017
 **/
@Configuration
@ComponentScan(basePackages = {"org.avlasov.mrclevereat.utils"})
@Import(DevDataSourceConfig.class)
public class AppConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setBasenames("validation/errormsg/diet_data");
        bundleMessageSource.setDefaultEncoding("UTF-8");
        return bundleMessageSource;
    }

}
