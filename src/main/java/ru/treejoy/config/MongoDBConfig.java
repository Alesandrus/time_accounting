package ru.treejoy.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.treejoy.domain.User;
import ru.treejoy.repository.UsersRepository;

@EnableMongoRepositories(basePackages = "ru.treejoy.repository")
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner commandLineRunner(UsersRepository usersRepository) {
        return args -> {
            usersRepository.save(new User(1, "admin", "admin"));
            usersRepository.save(new User(2, "user", "1234"));
        };
    }
}
