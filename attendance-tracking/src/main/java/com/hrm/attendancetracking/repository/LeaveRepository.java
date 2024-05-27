package com.hrm.attendancetracking.repository;

import com.hrm.attendancetracking.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {
}
