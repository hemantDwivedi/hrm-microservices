package com.hrm.attendancetracking.helper;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class DateAndTimeHelper {

    public LocalDate getLocalDate() {
        return LocalDate.now();
    }

    public LocalTime getLocalTime() {
        return LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
    }

    public LocalTime productiveTime(LocalTime in, LocalTime out) {
        long tMinute = in.until(out, ChronoUnit.MINUTES);
        int pHour = (int)tMinute / 60;
        int pMinute = (int)tMinute % 60;
        return LocalTime.of(pHour, pMinute);
    }
}