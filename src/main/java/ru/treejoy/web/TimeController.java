package ru.treejoy.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.treejoy.service.WorkTimeService;

@RestController
@RequestMapping("/timer")
public class TimeController {
    private WorkTimeService workTimeService;

    public TimeController(WorkTimeService workTimeService) {
        this.workTimeService = workTimeService;
    }

    @GetMapping("/beginwork")
    public boolean beginWork() {
        return workTimeService.beginingWork();
    }

    @GetMapping("/timebeginwork")
    public long timeOfBeginWork() {
        return workTimeService.getBeginingWorkTime();
    }

    @GetMapping("/beginbreak")
    public boolean beginBreak() {
        return workTimeService.beginTimeBreak();
    }

    @GetMapping("/endbreak")
    public boolean endBreak() {
        return workTimeService.endTimeBreak();
    }

    @GetMapping("/downtime")
    public long getDowntime() {
        return workTimeService.getCurrentDownTime();
    }

    @GetMapping("/endwork")
    public boolean endWork() {
        return workTimeService.endWork();
    }

    @GetMapping("/worktime")
    public long getWorkTime() {
        return workTimeService.getWorkTimeByDay();
    }

    @GetMapping("/getname")
    public String getName() {
        return workTimeService.getCurrentUsername();
    }

    @GetMapping("/isonwork")
    public boolean isOnWork() {
        return workTimeService.isOnWork();
    }

    @GetMapping("/isonbreak")
    public boolean isOnBreak() {
        return workTimeService.isOnBreak();
    }
}
