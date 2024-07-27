package com.hrm.pp.service;

import com.hrm.pp.entity.PayrollRecord;
import com.hrm.pp.entity.Payslip;
import com.hrm.pp.exception.ResourceNotFoundException;
import com.hrm.pp.repository.PayrollRepository;
import com.hrm.pp.repository.PayslipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayslipService {
    private final PayslipRepository payslipRepository;
    private final PayrollRepository payrollRepository;

    public Payslip create(Payslip payslip, Integer payrollId){
        PayrollRecord payrollRecord = payrollRepository.findById(payrollId).orElseThrow(
                () -> new ResourceNotFoundException("Payroll record not found")
        );

        payslip.setPayrollRecord(payrollRecord);
        return payslipRepository.save(payslip);
    }

    public Payslip getById(Integer id){
        return payslipRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Payslip not found")
        );
    }

    public List<Payslip> getAllPayslips(){
        return payslipRepository.findAll();
    }

    public Payslip update(Payslip payslip, Integer id){
        Payslip dbPayslip = payslipRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Payslip not found")
        );

        dbPayslip.setPayslipDate(payslip.getPayslipDate());
        return payslipRepository.save(dbPayslip);
    }

    public void delete(Integer id){
        if (payslipRepository.existsById(id)){
            payslipRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Payslip not found");
        }
    }
}
