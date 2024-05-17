package com.hrm.employeeservice.service;

import com.hrm.employeeservice.dto.EmployeeDto;
import com.hrm.employeeservice.entity.Employee;
import com.hrm.employeeservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public String createEmployee(EmployeeDto employeeDetails) {
        Employee employee = mapToEmployee(employeeDetails);
        Employee savedEmployee = employeeRepository.save(employee);
        return "Employee saved successfully with ID: " + savedEmployee.getEmployeeId();
    }

    private Employee mapToEmployee(EmployeeDto employeeDetails) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        employee.setDateOfBirth(employeeDetails.getDateOfBirth());
        employee.setGender(employeeDetails.getGender());
        return employee;
    }
}
