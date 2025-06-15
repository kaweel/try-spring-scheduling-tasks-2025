package com.schedule.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_config")
@Data
@NoArgsConstructor
public class JobConfigEntity {
    
    @Id
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String expression;

    @Column(nullable = false)
    private Boolean enabled;
}
