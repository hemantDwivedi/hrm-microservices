package com.hrm.trainingservice.controller;

import com.hrm.trainingservice.dto.request.EnrollmentRequest;
import com.hrm.trainingservice.entity.Enrollment;
import com.hrm.trainingservice.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody EnrollmentRequest enrollmentRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.create(enrollmentRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getEnrollmentById(id));
    }

    @GetMapping("/eId/{id}")
    public ResponseEntity<List<Enrollment>> getByEmployeeId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getAllEnrollments(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        enrollmentService.delete(id);
    }
}
