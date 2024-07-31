package com.hrm.pp.controller;

import com.hrm.pp.dto.request.PayslipRequest;
import com.hrm.pp.entity.Payslip;
import com.hrm.pp.service.PayslipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/payslips")
@RequiredArgsConstructor
public class PayslipController {
    private final PayslipService payslipService;

    @PostMapping
    public ResponseEntity<Payslip> create(@RequestBody PayslipRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(payslipService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payslip> getPayslipById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(payslipService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Payslip>> getAllPayslips(){
        return ResponseEntity.status(HttpStatus.OK).body(payslipService.getAllPayslips());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payslip> update(@RequestBody PayslipRequest payslipRequest, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(payslipService.update(payslipRequest, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        payslipService.delete(id);
    }
}
