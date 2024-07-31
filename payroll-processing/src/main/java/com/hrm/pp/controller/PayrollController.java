package com.hrm.pp.controller;

import com.hrm.pp.entity.PayrollRecord;
import com.hrm.pp.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/payrolls")
@RequiredArgsConstructor
public class PayrollController {
    private final PayrollService payrollService;

    @GetMapping("/eId/{empId}")
    public ResponseEntity<List<PayrollRecord>> getAllPayrollsByEmployeeId(@PathVariable Long empId){
        return ResponseEntity.status(HttpStatus.OK).body(payrollService.getAllPayrollsByEmployeeId(empId));
    }

    @GetMapping
    public ResponseEntity<List<PayrollRecord>> getAllPayrolls(){
        return ResponseEntity.status(HttpStatus.OK).body(payrollService.getAllPayrolls());
    }

    @GetMapping("{id}")
    public ResponseEntity<PayrollRecord> getPayrollById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(payrollService.getPayrollById(id));
    }

    @PostMapping
    public ResponseEntity<PayrollRecord> create(@RequestBody PayrollRecord payrollRecord){
        return ResponseEntity.status(HttpStatus.CREATED).body(payrollService.createPayroll(payrollRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        payrollService.deletePayroll(id);
        return ResponseEntity.status(HttpStatus.OK).body("Payroll record:: " + id + " Deleted!");
    }
}
