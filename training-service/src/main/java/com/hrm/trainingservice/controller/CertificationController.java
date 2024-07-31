package com.hrm.trainingservice.controller;

import com.hrm.trainingservice.dto.request.CertificationRequest;
import com.hrm.trainingservice.entity.Certification;
import com.hrm.trainingservice.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/certifications")
@RequiredArgsConstructor
public class CertificationController {
    private final CertificationService certificationService;

    @PostMapping
    public ResponseEntity<String> saveCertification(@RequestBody CertificationRequest certificationRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(certificationService.saveCertification(certificationRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certification> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(certificationService.getCertificationById(id));
    }

    @GetMapping("/eId/{empId}")
    public ResponseEntity<List<Certification>> getByEmployeeId(@PathVariable Long empId){
        return ResponseEntity.status(HttpStatus.OK).body(certificationService.getCertificationByEmployeeId(empId));
    }

    @GetMapping
    public ResponseEntity<List<Certification>> getAllCertifications(){
        return ResponseEntity.status(HttpStatus.OK).body(certificationService.getAllCertifications());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCertification(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(certificationService.deleteCertification(id));
    }
}
