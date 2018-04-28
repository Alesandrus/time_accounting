package ru.treejoy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.treejoy.domain.CustomUserDetail;
import ru.treejoy.domain.User;
import ru.treejoy.repository.UsersRepository;

import java.util.Optional;

/**
 * Класс для работы с пользователями, сохраненными в MongoDB.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 28.04.2018
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    /**
     * Экземпляр UserRepository для работы с данными пользователями в БД.
     */
    @Autowired
    private UsersRepository usersRepository;

    /**
     * Загрузка данных пользователя.
     *
     * @param username имя пользователя.
     * @return данные пользователя.
     * @throws UsernameNotFoundException .
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = usersRepository.findByLogin(username);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        return new CustomUserDetail(user);
    }
}
