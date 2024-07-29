package com.hrm.attendancetracking.repository;

import com.hrm.attendancetracking.model.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<AttendanceRecord, Integer> {
    List<AttendanceRecord> findByEmployeeId(Long id);
}