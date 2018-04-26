package ru.treejoy.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private Integer id;
    private String login;
    private String password;
    private boolean onWork;
    private boolean onBreak;
    private Long workDayBegin = 0L;
    private Long downtimeByDay = 0L;
    private Long beginTimeBreak = 0L;
    private Long workDayEnd = 0L;

    public User() {
    }

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

    public User(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnWork() {
        return onWork;
    }

    public void setOnWork(boolean onWork) {
        this.onWork = onWork;
    }

    public Long getWorkDayBegin() {
        return workDayBegin;
    }

    public void setWorkDayBegin(Long workDayBegin) {
        this.workDayBegin = workDayBegin;
    }

    public Long getDowntimeByDay() {
        return downtimeByDay;
    }

    public void setDowntimeByDay(Long downtimeByDay) {
        this.downtimeByDay = downtimeByDay;
    }

    public Long getWorkDayEnd() {
        return workDayEnd;
    }

    public void setWorkDayEnd(Long workDayEnd) {
        this.workDayEnd = workDayEnd;
    }

    public Long getBeginTimeBreak() {
        return beginTimeBreak;
    }

    public void setBeginTimeBreak(Long beginTimeBreak) {
        this.beginTimeBreak = beginTimeBreak;
    }

    public boolean isOnBreak() {
        return onBreak;
    }

    public void setOnBreak(boolean onBreak) {
        this.onBreak = onBreak;
    }
}
