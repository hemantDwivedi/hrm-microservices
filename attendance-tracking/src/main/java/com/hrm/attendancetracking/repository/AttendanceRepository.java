package com.hrm.attendancetracking.repository;

import com.hrm.attendancetracking.model.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<AttendanceRecord, Integer> {
}
