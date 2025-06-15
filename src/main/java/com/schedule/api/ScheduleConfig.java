package com.schedule.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleConfig {
    private String name;

    private String expression;

    private Boolean enabled;
}
