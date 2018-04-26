package ru.treejoy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.treejoy.domain.User;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<User, Integer> {
    Optional<User> findByLogin(String username);
}
