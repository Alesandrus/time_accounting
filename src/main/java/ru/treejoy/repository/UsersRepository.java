package ru.treejoy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.treejoy.domain.User;

import java.util.Optional;

/**
 * Интерфейс для работы с пользователями, сохраненными в MongoDB.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 28.04.2018
 */
public interface UsersRepository extends MongoRepository<User, Integer> {
    /**
     * Находит пользователя по логину.
     *
     * @param username логин пользователя.
     * @return пользователя.
     */
    Optional<User> findByLogin(String username);
}
