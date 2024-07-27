package com.hrm.pp.repository;

import com.hrm.pp.entity.PayrollRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<PayrollRecord, Integer> {
    List<PayrollRecord> findByEmployeeId(Long id);
}
