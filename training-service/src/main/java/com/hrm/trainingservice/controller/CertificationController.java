package com.hrm.trainingservice.controller;

import com.hrm.trainingservice.entity.Certification;
import com.hrm.trainingservice.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CertificationController {
    private final CertificationService certificationService;

    @PostMapping("/employees/{empId}/courses/{courseId}/certifications")
    public ResponseEntity<String> saveCertification(@RequestBody Certification certification,
                                                    @PathVariable Long empId,
                                                    @PathVariable Long courseId){
        return ResponseEntity.status(HttpStatus.CREATED).body(certificationService.saveCertification(certification, empId, courseId));
    }

    @GetMapping("/certifications/{id}")
    public ResponseEntity<Certification> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(certificationService.getCertificationById(id));
    }

    @GetMapping("/employees/{empId}/certifications")
    public ResponseEntity<List<Certification>> getByEmployeeId(@PathVariable Long empId){
        return ResponseEntity.status(HttpStatus.OK).body(certificationService.getCertificationByEmployeeId(empId));
    }

    @GetMapping("/certifications")
    public ResponseEntity<List<Certification>> getAllCertifications(){
        return ResponseEntity.status(HttpStatus.OK).body(certificationService.getAllCertifications());
    }

    @DeleteMapping("/certifications/{id}")
    public ResponseEntity<String> deleteCertification(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(certificationService.deleteCertification(id));
    }
}
