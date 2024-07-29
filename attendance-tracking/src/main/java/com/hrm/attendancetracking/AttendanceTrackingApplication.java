package com.hrm.attendancetracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AttendanceTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceTrackingApplication.class, args);
    }

}