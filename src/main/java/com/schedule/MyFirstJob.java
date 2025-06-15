package com.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyFirstJob {

    @Scheduled(cron = "* 15-30 19 * * *")
    public void run() {
        System.out.println("HELLO MY FIRST JOB!!");
    }

}
