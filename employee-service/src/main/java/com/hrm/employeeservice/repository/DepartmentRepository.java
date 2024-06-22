package com.hrm.employeeservice.repository;

import com.hrm.employeeservice.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}