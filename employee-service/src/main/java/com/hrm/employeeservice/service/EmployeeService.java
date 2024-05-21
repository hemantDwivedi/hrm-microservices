package com.hrm.employeeservice.service;

import com.hrm.employeeservice.dto.EmployeeDetails;
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


    public String createEmployee(EmployeeDetails employeeDetails) {
        Employee employee = mapToEmployee(employeeDetails);
        Department department = departmentRepository.findById(employeeDetails.getDepartmentId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Department does not exists with ID: " + employeeDetails.getDepartmentId())
                );
        employee.setDepartment(department);
        Address address = mapToAddress(new Address(), employeeDetails);
        Address savedAddress = addressRepository.save(address);
        employee.setAddress(savedAddress);
        EmploymentHistory employmentHistory = mapToEmploymentHistory(employeeDetails);
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

    public String updateEmployee(Long id, EmployeeDetails employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee not exists with ID: " + id)
                );
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        employee.setDateOfBirth(employeeDetails.getDateOfBirth());
        employee.setGender(employeeDetails.getGender());
        Address savedAddress = addressRepository.findById(employee.getAddress().getAddressId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Address does not exists with ID: " + employee.getAddress().getAddressId()
                        )
                );
        Address address = mapToAddress(savedAddress, employeeDetails);
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

    private EmploymentHistory mapToEmploymentHistory(EmployeeDetails employeeDetails){
        EmploymentHistory employmentHistory = new EmploymentHistory();
        employmentHistory.setStartDate(employeeDetails.getStartDate());
        employmentHistory.setEndDate(employeeDetails.getEndDate());
        employmentHistory.setPosition(employeeDetails.getPosition());
        employmentHistory.setSalary(employeeDetails.getSalary());
        return employmentHistory;
    }

    private Address mapToAddress(Address address, EmployeeDetails employeeDetails) {
        address.setCity(employeeDetails.getCity());
        address.setState(employeeDetails.getState());
        address.setPinCode(employeeDetails.getPinCode());
        return address;
    }

    private Employee mapToEmployee(EmployeeDetails employeeDetails) {
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
