package com.hrm.pp.controller;

import com.hrm.pp.entity.PayrollRecord;
import com.hrm.pp.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PayrollController {
    private final PayrollService payrollService;

    @GetMapping("/employees/{employee-id}/payroll-records")
    public ResponseEntity<List<PayrollRecord>> getAllPayrollsByEmployeeId(@PathVariable("employee-id") Long empId){
        return ResponseEntity.status(HttpStatus.OK).body(payrollService.getAllPayrollsByEmployeeId(empId));
    }

    @GetMapping("/payroll-records")
    public ResponseEntity<List<PayrollRecord>> getAllPayrolls(){
        return ResponseEntity.status(HttpStatus.OK).body(payrollService.getAllPayrolls());
    }

    @GetMapping("/payroll-records/{payroll-id}")
    public ResponseEntity<PayrollRecord> getPayrollById(@PathVariable("payroll-id") Integer payrollId){
        return ResponseEntity.status(HttpStatus.OK).body(payrollService.getPayrollById(payrollId));
    }

    @PostMapping("/employees/{employee-id}/payroll-records")
    public ResponseEntity<PayrollRecord> create(@RequestBody PayrollRecord payrollRecord, @PathVariable("employee-id") Long empId){
        return ResponseEntity.status(HttpStatus.CREATED).body(payrollService.createPayroll(payrollRecord, empId));
    }

    @DeleteMapping("/payroll-records/{payroll-id}")
    public ResponseEntity<String> delete(@PathVariable("payroll-id") Integer payrollId){
        payrollService.deletePayroll(payrollId);
        return ResponseEntity.status(HttpStatus.OK).body("Payroll record:: " + payrollId + " Deleted!");
    }
}
