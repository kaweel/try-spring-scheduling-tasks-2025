package com.schedule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobConfigRepository extends JpaRepository<JobConfigEntity, String> {
    List<JobConfigEntity> findByEnabledTrue();
}