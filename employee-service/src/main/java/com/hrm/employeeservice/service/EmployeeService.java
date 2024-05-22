package com.hrm.employeeservice.service;

import com.hrm.employeeservice.dto.EmployeeRequest;
import com.hrm.employeeservice.entity.Address;
import com.hrm.employeeservice.entity.Department;
import com.hrm.employeeservice.entity.Employee;
import com.hrm.employeeservice.entity.EmploymentHistory;
import com.hrm.employeeservice.exception.ResourceNotFoundException;
import com.hrm.employeeservice.repository.AddressRepository;
import com.hrm.employeeservice.repository.DepartmentRepository;
import com.hrm.employeeservice.repository.EmployeeRepository;
import com.hrm.employeeservice.repository.EmploymentHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private AddressRepository addressRepository;
    private DepartmentRepository departmentRepository;
    private EmploymentHistoryRepository employmentHistoryRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           AddressRepository addressRepository,
                           DepartmentRepository departmentRepository,
                           EmploymentHistoryRepository employmentHistoryRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.departmentRepository = departmentRepository;
        this.employmentHistoryRepository = employmentHistoryRepository;
    }


    public String createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = mapToEmployee(employeeRequest);
        Department department = departmentRepository.findById(employeeRequest.getDepartmentId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Department does not exists with ID: " + employeeRequest.getDepartmentId())
                );
        employee.setDepartment(department);
        Address address = mapToAddress(new Address(), employeeRequest);
        Address savedAddress = addressRepository.save(address);
        employee.setAddress(savedAddress);
        EmploymentHistory employmentHistory = mapToEmploymentHistory(employeeRequest);
        employmentHistoryRepository.save(employmentHistory);
        employee.setEmploymentHistory(employmentHistory);
        Employee savedEmployee = employeeRepository.save(employee);

        return "Employee created with ID: " + savedEmployee.getEmployeeId();
    }

    public List<Employee> getEmployeeList() {
        return employeeRepository
                .findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee not exists with ID: " + id)
                );
    }

    public String updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee not exists with ID: " + id)
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
                        () -> new ResourceNotFoundException("Employee does not exists with ID: " + id)
                );
        if (employee.getAddress() != null) {
            addressRepository.delete(employee.getAddress());
        }
        employeeRepository.delete(employee);
        return "Employee ID: " + id + " deleted";
    }

    private EmploymentHistory mapToEmploymentHistory(EmployeeRequest employeeRequest){
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
