package ru.treejoy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.treejoy.domain.User;
import ru.treejoy.repository.UsersRepository;

/**
 * Класс-сервис для установки различных данных о пользователе.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 28.04.2018
 */
@Service
public class WorkTimeService {
    /**
     * Экземпляр UserRepository для работы с данными пользователями в БД.
     */
    @Autowired
    private UsersRepository usersRepository;

    /**
     * Установка начала работы.
     *
     * @return true если если пользователь еще не на работе.
     */
    public boolean beginingWork() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        if (!user.isOnWork()) {
            user.setWorkDayBegin(System.currentTimeMillis());
            user.setOnWork(true);
            user.setDowntimeByDay(0L);
            usersRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Получить время начала работы.
     *
     * @return время начала работы.
     */
    public long getBeginingWorkTime() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        return user.getWorkDayBegin();
    }

    /**
     * Установить время начала перерыва.
     *
     * @return true если пользователь на работе и не на перерыве.
     */
    public boolean beginTimeBreak() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        boolean isWork = user.isOnWork() && !user.isOnBreak();
        if (isWork) {
            user.setBeginTimeBreak(System.currentTimeMillis());
            user.setOnBreak(true);
            usersRepository.save(user);
        }
        return isWork;
    }

    /**
     * Установливает статус окончания перерыва и увеличивает значение времени простоя.
     *
     * @return true если пользователь на работе и на перерыве.
     */
    public boolean endTimeBreak() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        boolean isBreak = user.isOnWork() && user.isOnBreak();
        if (isBreak) {
            long timeBreak = System.currentTimeMillis() - user.getBeginTimeBreak();
            long currentDowntime = user.getDowntimeByDay();
            user.setDowntimeByDay(currentDowntime + timeBreak);
            user.setOnBreak(false);
            usersRepository.save(user);
        }
        return isBreak;
    }

    /**
     * Получает текущее время простоя.
     *
     * @return время простоя.
     */
    public long getCurrentDownTime() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        return user.getDowntimeByDay();
    }

    /**
     * Устанавливает статус окончания работы.
     *
     * @return true если работа окончена.
     */
    public boolean endWork() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        boolean isEnd = false;
        if (user.isOnWork()) {
            if (user.isOnBreak()) {
                long timeBreak = System.currentTimeMillis() - user.getBeginTimeBreak();
                long currentDowntime = user.getDowntimeByDay();
                user.setDowntimeByDay(currentDowntime + timeBreak);
                user.setOnBreak(false);
            }
            user.setWorkDayEnd(System.currentTimeMillis());
            isEnd = true;
            user.setOnWork(false);
            usersRepository.save(user);
        }
        return isEnd;
    }

    /**
     * Получает время работы за день.
     *
     * @return время работы за день.
     */
    public long getWorkTimeByDay() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        long workTime;
        if (!user.isOnWork()) {
            workTime = user.getWorkDayEnd() - user.getWorkDayBegin() - user.getDowntimeByDay();
        } else {
            workTime = System.currentTimeMillis() - user.getWorkDayBegin() - user.getDowntimeByDay();
        }
        return workTime;
    }

    /**
     * Получить логин текщего пользователя, находящегося в сессии.
     *
     * @return логин пользователя.
     */
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    /**
     * Нахождение пользователя на работе.
     *
     * @return true если пользователь на работе.
     */
    public boolean isOnWork() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        return user.isOnWork();
    }

    /**
     * Нахождение пользователя на перерыве.
     *
     * @return true если пользователь на перерыве.
     */
    public boolean isOnBreak() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        return user.isOnBreak();
    }
}
