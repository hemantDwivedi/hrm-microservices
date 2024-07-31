package com.hrm.employeeservice.service;

import com.hrm.employeeservice.dto.EmployeeRequest;
import com.hrm.employeeservice.model.Address;
import com.hrm.employeeservice.model.Department;
import com.hrm.employeeservice.model.Employee;
import com.hrm.employeeservice.model.EmploymentHistory;
import com.hrm.employeeservice.exception.ResourceNotFoundException;
import com.hrm.employeeservice.repository.AddressRepository;
import com.hrm.employeeservice.repository.DepartmentRepository;
import com.hrm.employeeservice.repository.EmployeeRepository;
import com.hrm.employeeservice.repository.EmploymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final DepartmentRepository departmentRepository;
    private final EmploymentHistoryRepository employmentHistoryRepository;

    public String createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = mapToEmployee(employeeRequest);
        Department department = departmentRepository.findById(employeeRequest.getDepartmentId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(format("Department ID: %s not found", employeeRequest.getDepartmentId()))
                        );
        employee.setDepartment(department);
        Address address = mapToAddress(new Address(), employeeRequest);
        Address savedAddress = addressRepository.save(address);
        employee.setAddress(savedAddress);
        EmploymentHistory employmentHistory = mapToEmploymentHistory(employeeRequest);
        EmploymentHistory dbEmploymentHistory = employmentHistoryRepository.save(employmentHistory);
        employee.setEmploymentHistory(dbEmploymentHistory);
        Employee savedEmployee = employeeRepository.save(employee);

        return format("Employee ID: %s created!", savedEmployee.getEmployeeId());
    }

    public List<Employee> getEmployeeList() {
        return employeeRepository
                .findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(format("Employee ID: %s not found", id))
                );
    }

    public Boolean existById(Long id) {
        return employeeRepository.existsById(id);
    }

    public String updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(format("Employee ID: %s not found", id))
                );
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhone(employeeRequest.getPhone());
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setGender(employeeRequest.getGender());
        Address savedAddress = addressRepository.findById(employee.getAddress().getAddressId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Address does not exists with ID: " + employee.getAddress().getAddressId()
                        )
                );
        Address address = mapToAddress(savedAddress, employeeRequest);
        addressRepository.save(address);
        employeeRepository.save(employee);
        return "Employee ID: " + employee.getEmployeeId() + " updated";
    }

    public String deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(format("Employee ID: %s not found", id))
                );
        if (employee.getAddress() != null) {
            addressRepository.delete(employee.getAddress());
        }
        employeeRepository.delete(employee);
        return "Employee ID: " + id + " deleted";
    }

    private EmploymentHistory mapToEmploymentHistory(EmployeeRequest employeeRequest) {
        EmploymentHistory employmentHistory = new EmploymentHistory();
        employmentHistory.setStartDate(employeeRequest.getStartDate());
        employmentHistory.setEndDate(employeeRequest.getEndDate());
        employmentHistory.setPosition(employeeRequest.getPosition());
        employmentHistory.setSalary(employeeRequest.getSalary());
        return employmentHistory;
    }

    private Address mapToAddress(Address address, EmployeeRequest employeeRequest) {
        address.setCity(employeeRequest.getCity());
        address.setState(employeeRequest.getState());
        address.setPinCode(employeeRequest.getPinCode());
        return address;
    }

    private Employee mapToEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhone(employeeRequest.getPhone());
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setGender(employeeRequest.getGender());
        return employee;
    }

}
