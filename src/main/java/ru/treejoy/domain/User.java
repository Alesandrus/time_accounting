package ru.treejoy.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Документ класса пользователя, для сохранения в MongoDB.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 28.04.2018
 */
@Document
public class User {
    /**
     * ID пользователя.
     */
    @Id
    private Integer id;

    /**
     * Логин пользователя.
     */
    private String login;

    /**
     * Пароль пользователя.
     */
    private String password;

    /**
     * Статус нахождения на работе. True - если на работе.
     */
    private boolean onWork;

    /**
     * Статус нахождения на перерыве. True - если на перерыве.
     */
    private boolean onBreak;

    /**
     * Время начала работы.
     */
    private Long workDayBegin = 0L;

    /**
     * Время простоя за рабочий день.
     */
    private Long downtimeByDay = 0L;

    /**
     * Время начала перерыва.
     */
    private Long beginTimeBreak = 0L;

    /**
     * Время конца рабочего дня.
     */
    private Long workDayEnd = 0L;

    /**
     * Конструктор по умолчанию.
     */
    public User() {
    }

    /**
     * Конструктор для копирования.
     *
     * @param user .
     */
    public User(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.onWork = user.isOnWork();
        this.workDayBegin = user.workDayBegin;
        this.downtimeByDay = user.downtimeByDay;
        this.workDayEnd = user.workDayEnd;
        this.beginTimeBreak = user.beginTimeBreak;
        this.onBreak = user.onBreak;
    }

    /**
     * Конструктор для создания в БД.
     *
     * @param id       пользователя.
     * @param login    логин.
     * @param password пароль.
     */
    public User(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    /**
     * Геттер для ID.
     * @return ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Cеттер для ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Геттер для логина.
     * @return логин.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Сеттер для логина.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Геттер для пароля.
     * @return пароля.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Сеттер для пароля.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Геттер для статуса нахождения на работе.
     * @return статус нахождения на работе.
     */
    public boolean isOnWork() {
        return onWork;
    }

    /**
     * Сеттер для статуса нахождения на работе.
     */
    public void setOnWork(boolean onWork) {
        this.onWork = onWork;
    }

    /**
     * Геттер для времени начала рабочего дня.
     * @return время начала рабочего дня.
     */
    public Long getWorkDayBegin() {
        return workDayBegin;
    }

    /**
     * Сеттер для времени начала рабочего дня.
     */
    public void setWorkDayBegin(Long workDayBegin) {
        this.workDayBegin = workDayBegin;
    }

    /**
     * Геттер для времени простоя за день.
     * @return время простоя.
     */
    public Long getDowntimeByDay() {
        return downtimeByDay;
    }

    /**
     * Сеттер для времени простоя за день.
     */
    public void setDowntimeByDay(Long downtimeByDay) {
        this.downtimeByDay = downtimeByDay;
    }

    /**
     * Геттер для времени конца рабочего дня.
     * @return время конца рабочего дня.
     */
    public Long getWorkDayEnd() {
        return workDayEnd;
    }

    /**
     * Сеттер для времени конца рабочего дня.
     */
    public void setWorkDayEnd(Long workDayEnd) {
        this.workDayEnd = workDayEnd;
    }

    /**
     * Геттер для времени начала перерыва.
     * @return время начала перерыва.
     */
    public Long getBeginTimeBreak() {
        return beginTimeBreak;
    }

    /**
     * Сеттер для времени начала перерыва.
     */
    public void setBeginTimeBreak(Long beginTimeBreak) {
        this.beginTimeBreak = beginTimeBreak;
    }

    /**
     * Геттер для статуса нахождения на перерыве.
     * @return статус нахождения на перерыве.
     */
    public boolean isOnBreak() {
        return onBreak;
    }

    /**
     * Сеттер для статуса нахождения на перерыве.
     */
    public void setOnBreak(boolean onBreak) {
        this.onBreak = onBreak;
    }
}
