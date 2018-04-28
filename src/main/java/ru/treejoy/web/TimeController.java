package ru.treejoy.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.treejoy.service.WorkTimeService;

/**
 * Контроллер для работы с сервисом контроля рабочего времени пользователя.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 28.04.2018
 */
@RestController
@RequestMapping("/timer")
public class TimeController {
    /**
     * Экземпляр WorkTimeService для учета времени работы пользователя.
     */
    private WorkTimeService workTimeService;

    /**
     * Констркутор для связывания с WorkTimeService.
     *
     * @param workTimeService .
     */
    public TimeController(WorkTimeService workTimeService) {
        this.workTimeService = workTimeService;
    }

    /**
     * Устанавливает время начала работы.
     *
     * @return true если установилось. False - если уже на работе.
     */
    @GetMapping("/beginwork")
    public boolean beginWork() {
        return workTimeService.beginingWork();
    }

    /**
     * Получает время начала работы.
     *
     * @return время начала работы.
     */
    @GetMapping("/timebeginwork")
    public long timeOfBeginWork() {
        return workTimeService.getBeginingWorkTime();
    }

    /**
     * Устанавливает время начла перерыва и выставляет флаг статуса нахождения на перерыве.
     *
     * @return true если пользователь ушел на перерыв. False - если уже на перерыве или не пришел еще на работу.
     */
    @GetMapping("/beginbreak")
    public boolean beginBreak() {
        return workTimeService.beginTimeBreak();
    }

    /**
     * Устанавливает флаг нахождения на перерыве в false и увеличвает время простоя.
     *
     * @return true если пользователь пришел с перерыва. False - если он не на работе или уже пришел с перерыва.
     */
    @GetMapping("/endbreak")
    public boolean endBreak() {
        return workTimeService.endTimeBreak();
    }

    /**
     * Получает время простоя пользователя.
     *
     * @return время простоя.
     */
    @GetMapping("/downtime")
    public long getDowntime() {
        return workTimeService.getCurrentDownTime();
    }

    /**
     * Устанавливает время окончания работы.
     *
     * @return true если пользователь нажал кнопку "ушел с работы"
     * и при этом он в этот момент был со статусом на работе.
     */
    @GetMapping("/endwork")
    public boolean endWork() {
        return workTimeService.endWork();
    }

    /**
     * Получает время отработанное за день.
     *
     * @return отработанное время.
     */
    @GetMapping("/worktime")
    public long getWorkTime() {
        return workTimeService.getWorkTimeByDay();
    }

    /**
     * Получает логин пользователя, находящегося в текущей сессии.
     *
     * @return логин пользователя.
     */
    @GetMapping("/getname")
    public String getName() {
        return workTimeService.getCurrentUsername();
    }

    /**
     * Получает статус нахождения пользователя на работе.
     *
     * @return true если на работе.
     */
    @GetMapping("/isonwork")
    public boolean isOnWork() {
        return workTimeService.isOnWork();
    }

    /**
     * Получает статус нахождения пользователя на перерыве.
     *
     * @return true если пользователь находится на перерыве.
     */
    @GetMapping("/isonbreak")
    public boolean isOnBreak() {
        return workTimeService.isOnBreak();
    }
}
