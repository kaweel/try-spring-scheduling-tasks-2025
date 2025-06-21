package com.schedule.job;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JobAService implements JobTask {

    @Value("${schedule.job.a.name}")
    String name;

    private static final AtomicInteger jobACount = new AtomicInteger();

    @Override
    public void run() {
        System.out.println("HELLO JOB " + name + "!! [thread=" + Thread.currentThread().getName() + "][count="
                + jobACount.incrementAndGet() + "]");
    }

    @Override
    public String getName() {
        return name;
    }

}
