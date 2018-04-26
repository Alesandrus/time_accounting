package ru.treejoy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.treejoy.domain.User;
import ru.treejoy.repository.UsersRepository;

@Service
public class WorkTimeService {
    @Autowired
    private UsersRepository usersRepository;

    public boolean beginingWork() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        if (!user.isOnWork()) {
            user.setWorkDayBegin(System.currentTimeMillis());
            user.setOnWork(true);
            usersRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public long getBeginingWorkTime() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        return user.getWorkDayBegin();
    }

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

    public long getCurrentDownTime() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        return user.getDowntimeByDay();
    }

    public boolean endWork() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        boolean isEnd = false;
        if(user.isOnWork()) {
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

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public boolean isOnWork() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        return user.isOnWork();
    }

    public boolean isOnBreak() {
        User user = usersRepository.findByLogin(getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        return user.isOnBreak();
    }
}
