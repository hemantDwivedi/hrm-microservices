package com.hrm.pp.service;

import com.hrm.pp.dto.request.PayslipRequest;
import com.hrm.pp.entity.PayrollRecord;
import com.hrm.pp.entity.Payslip;
import com.hrm.pp.exception.ResourceNotFoundException;
import com.hrm.pp.repository.PayrollRepository;
import com.hrm.pp.repository.PayslipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class PayslipService {
    private final PayslipRepository payslipRepository;
    private final PayrollRepository payrollRepository;

    public Payslip create(PayslipRequest payslipRequest){
        PayrollRecord payrollRecord = payrollRepository.findById(payslipRequest.getPayrollId()).orElseThrow(
                () -> new ResourceNotFoundException(format("Payroll ID: %s not found", payslipRequest.getPayrollId()))
        );
        Payslip payslip = Payslip.builder().payslipDate(payslipRequest.getPayslipDate()).payrollRecord(payrollRecord).build();
        return payslipRepository.save(payslip);
    }

    public Payslip getById(Integer id){
        return payslipRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(format("Payslip ID: %s not found", id))
        );
    }

    public List<Payslip> getAllPayslips(){
        return payslipRepository.findAll();
    }

    public Payslip update(PayslipRequest payslipRequest, Integer id){
        Payslip dbPayslip = payslipRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(format("Payslip ID: %s not found", id))
        );

        dbPayslip.setPayslipDate(payslipRequest.getPayslipDate());
        return payslipRepository.save(dbPayslip);
    }

    public void delete(Integer id){
        if (payslipRepository.existsById(id)){
            payslipRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(format("Payslip ID: %s not found", id));
        }
    }
}
