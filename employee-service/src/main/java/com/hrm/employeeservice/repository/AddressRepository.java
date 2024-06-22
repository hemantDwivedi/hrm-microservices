package com.hrm.employeeservice.repository;

import com.hrm.employeeservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
