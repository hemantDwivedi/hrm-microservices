package com.hrm.pp.controller;

import com.hrm.pp.entity.Payslip;
import com.hrm.pp.service.PayslipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PayslipController {
    private final PayslipService payslipService;

    @PostMapping("/payroll-records/{payrollId}/payslips")
    public ResponseEntity<Payslip> create(@RequestBody Payslip payslip, @PathVariable Integer payrollId){
        return ResponseEntity.status(HttpStatus.CREATED).body(payslipService.create(payslip, payrollId));
    }

    @GetMapping("/payslips/{id}")
    public ResponseEntity<Payslip> getPayslipById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(payslipService.getById(id));
    }

    @GetMapping("/payslips")
    public ResponseEntity<List<Payslip>> getAllPayslips(){
        return ResponseEntity.status(HttpStatus.OK).body(payslipService.getAllPayslips());
    }

    @PutMapping("/payslips/{id}")
    public ResponseEntity<Payslip> update(@RequestBody Payslip payslip, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(payslipService.update(payslip, id));
    }

    @DeleteMapping("/payslips/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id){
        payslipService.delete(id);
    }
}
