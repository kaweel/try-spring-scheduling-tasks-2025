package com.schedule.dynamic;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.schedule.api.ScheduleConfig;
import com.schedule.job.JobTask;
import com.schedule.repository.JobConfigEntity;
import com.schedule.repository.JobConfigRepository;

@Component
public class DynamicJobScheduler {

    private final JobConfigRepository jobConfigRepository;
    private final List<JobTask> jobTasks;
    private final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    public DynamicJobScheduler(JobConfigRepository jobConfigRepository, List<JobTask> jobTasks) {
        this.jobConfigRepository = jobConfigRepository;
        this.jobTasks = jobTasks;
        scheduler.setPoolSize(1);
        scheduler.initialize();
        scheduleAllJobs();
    }

    public void scheduleAllJobs() {
        jobConfigRepository.findAll().forEach(this::schedule);
    }

    public void schedule(JobConfigEntity jobConfigEntity) {
        ScheduledFuture<?> existing = scheduledTasks.get(jobConfigEntity.getName());
        if (existing != null && !existing.isCancelled()) {
            existing.cancel(false);
        }

        CronTrigger trigger = new CronTrigger(jobConfigEntity.getExpression());
        ScheduledFuture<?> future = scheduler.schedule(() -> run(jobConfigEntity), trigger);
        scheduledTasks.put(jobConfigEntity.getName(), future);
    }

    private void run(JobConfigEntity jobConfigEntity) {
        jobTasks.stream()
                .filter(job -> job.getName().equalsIgnoreCase(jobConfigEntity.getName()))
                .findFirst().ifPresentOrElse(job -> job.run(jobConfigEntity.getEnabled()),
                        () -> System.out.println("[⏸] Skipping " + jobConfigEntity.getName()));
    }

    public String reSchedule(ScheduleConfig scheduleConfig) {
        System.out.println("=== reSchedule ===");
        JobConfigEntity jobConfigEntity = new JobConfigEntity();
        BeanUtils.copyProperties(scheduleConfig, jobConfigEntity);
        jobConfigRepository.save(jobConfigEntity);
        this.schedule(jobConfigEntity);
        return "success";
    }

}
