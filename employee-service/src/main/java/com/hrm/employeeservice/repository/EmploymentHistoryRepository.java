package com.hrm.employeeservice.repository;

import com.hrm.employeeservice.entity.EmploymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory, Integer> {
}
