package com.schedule.api;

import org.springframework.web.bind.annotation.RestController;

import com.schedule.dynamic.DynamicJobScheduler;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ScheduleController {

    private final DynamicJobScheduler dynamicJobScheduler;
    
    public ScheduleController(DynamicJobScheduler dynamicJobScheduler) {
        this.dynamicJobScheduler = dynamicJobScheduler;
    }

    @PostMapping("/re-schedule")
    public String postMethodName(@RequestBody ScheduleConfig scheduleConfig) {
        return  dynamicJobScheduler.reSchedule(scheduleConfig);
    }
    
}
