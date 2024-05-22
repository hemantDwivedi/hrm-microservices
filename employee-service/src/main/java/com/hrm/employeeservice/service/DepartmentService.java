package com.hrm.employeeservice.service;

import com.hrm.employeeservice.entity.Department;
import com.hrm.employeeservice.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public String addDepartment(Department department) {
        departmentRepository.save(department);
        return "Department saved";
    }
}
