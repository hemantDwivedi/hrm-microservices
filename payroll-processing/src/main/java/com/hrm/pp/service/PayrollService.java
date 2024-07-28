package com.hrm.pp.service;

import com.hrm.pp.client.ApiClient;
import com.hrm.pp.entity.PayrollRecord;
import com.hrm.pp.exception.ResourceNotFoundException;
import com.hrm.pp.repository.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PayrollService {
    private final PayrollRepository payrollRepository;
    private final ApiClient apiClient;

    public List<PayrollRecord> getAllPayrollsByEmployeeId(Long id) {
        List<PayrollRecord> payrollRecords = payrollRepository.findByEmployeeId(id);
        if (payrollRecords.isEmpty()) throw new ResourceNotFoundException("Payroll record not found");
        return payrollRecords;
    }

    public List<PayrollRecord> getAllPayrolls() {
        List<PayrollRecord> payrollRecords = payrollRepository.findAll();
        if (payrollRecords.isEmpty()) throw new ResourceNotFoundException("Payroll record not found");
        return payrollRecords;
    }

    public PayrollRecord getPayrollById(Integer id) {
        return payrollRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                format("Payroll record not found::%s", id)
        ));
    }

    public PayrollRecord createPayroll(PayrollRecord payrollRecord, Long id) {
        Boolean employee = apiClient.existById(id);
        if (!employee) throw new ResourceNotFoundException("Employee not found");
        payrollRecord.setEmployeeId(id);
        return payrollRepository.save(payrollRecord);
    }

    public void deletePayroll(Integer id) {
        if (payrollRepository.existsById(id)) {
            payrollRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Payroll record not found with id " + id);
        }
    }
}
