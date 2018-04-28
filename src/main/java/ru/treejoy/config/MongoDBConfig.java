package ru.treejoy.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.treejoy.domain.User;
import ru.treejoy.repository.UsersRepository;

/**
 * Конфигурация базы данных MongoDB.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 28.04.2018
 */
@EnableMongoRepositories(basePackages = "ru.treejoy.repository")
@Configuration
public class MongoDBConfig {
    /**
     * Добавление в базу данных пользователей.
     *
     * @param usersRepository репозиторий для работы с пользователями.
     * @return CommandLineRunner.
     */
    @Bean
    CommandLineRunner commandLineRunner(UsersRepository usersRepository) {
        return args -> {
            usersRepository.save(new User(1, "admin", "admin"));
            usersRepository.save(new User(2, "user", "1234"));
        };
    }
}
