package com.hrm.attendancetracking.repository;

import com.hrm.attendancetracking.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {
    List<Leave> findByEmployeeId(Long id);
}