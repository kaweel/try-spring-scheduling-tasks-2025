package com.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ScheduletaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduletaskApplication.class, args);
	}

}
