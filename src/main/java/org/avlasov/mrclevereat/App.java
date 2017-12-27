package org.avlasov.mrclevereat;

import org.avlasov.mrclevereat.entity.user.User;
import org.avlasov.mrclevereat.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created By artemvlasov on 26/12/2017
 **/
@SpringBootApplication
@EnableAutoConfiguration
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(App.class);
        UserRepository bean = run.getBean(UserRepository.class);
        bean.save(new User("email@mail.com", "hello".getBytes()));
    }

}
