package com.hrm.employeeservice.service;

import com.hrm.employeeservice.dto.EmployeeDetails;
import com.hrm.employeeservice.entity.Address;
import com.hrm.employeeservice.entity.Employee;
import com.hrm.employeeservice.repository.AddressRepository;
import com.hrm.employeeservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private AddressRepository addressRepository;

    public EmployeeService(EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }


    public String createEmployee(EmployeeDetails employeeDetails) {
        Employee employee = mapToEmployee(employeeDetails);
        Address address = mapToAddress(new Address(), employeeDetails);
        Address savedAddress = addressRepository.save(address);
        employee.setAddress(savedAddress);
        Employee savedEmployee = employeeRepository.save(employee);
        return "Employee created with ID: " + savedEmployee.getEmployeeId();
    }

    public List<Employee> getEmployeeList() {
        return employeeRepository
                .findAll();
    }


    public Employee getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return employee;
    }

    private Address mapToAddress(Address address, EmployeeDetails employeeDetails){
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

    public String updateEmployee(Long id, EmployeeDetails employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        employee.setDateOfBirth(employeeDetails.getDateOfBirth());
        employee.setGender(employeeDetails.getGender());
        Address savedAddress = addressRepository.findById(employee.getAddress().getAddressId()).orElse(null);
        Address address = mapToAddress(savedAddress, employeeDetails);
        addressRepository.save(address);
        employeeRepository.save(employee);
        return "Employee ID: " + employee.getEmployeeId() + " updated";
    }

    public String deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        Address address = addressRepository.findById(employee.getAddress().getAddressId()).orElse(null);
        employeeRepository.delete(employee);
        addressRepository.delete(address);
        return "Employee ID: " + id + " deleted";
    }
}
