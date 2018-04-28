package ru.treejoy.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Класс обертка с данными пользователя.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 28.04.2018
 */
public class CustomUserDetail extends User implements UserDetails {
    /**
     * Конструктор для использования пользователя.
     *
     * @param user .
     */
    public CustomUserDetail(final User user) {
        super(user);
    }

    /**
     * Не используется.
     *
     * @return null.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * Получить пароль.
     *
     * @return пароль.
     */
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    /**
     * Получить имя пользователя.
     *
     * @return имя.
     */
    @Override
    public String getUsername() {
        return super.getLogin();
    }

    /**
     * Проверка действия аккаунта.
     *
     * @return true.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Проверка на блокировку.
     *
     * @return true.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Проверка действия пароля.
     *
     * @return true.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Проверка включен или нет аккаунт пользователя.
     *
     * @return true.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
