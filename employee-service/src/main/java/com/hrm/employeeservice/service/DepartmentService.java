package com.hrm.employeeservice.service;

import com.hrm.employeeservice.exception.ResourceNotFoundException;
import com.hrm.employeeservice.model.Department;
import com.hrm.employeeservice.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public String addDepartment(Department department) {
        departmentRepository.save(department);
        return "Department saved";
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(format("Department ID: %s not found", id))
        );
    }

    public String update(Department department, Long id) {
        Department dbDepartment = departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(format("Department ID: %s not found", id))
        );
        dbDepartment.setName(department.getName());
        dbDepartment.setDescription(department.getDescription());
        departmentRepository.save(dbDepartment);
        return "Department updated!";
    }
}
