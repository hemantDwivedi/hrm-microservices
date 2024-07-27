package com.hrm.trainingservice.controller;

import com.hrm.trainingservice.entity.Enrollment;
import com.hrm.trainingservice.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping("/employees/{employee-id}/courses/{course-id}/enrollments")
    public ResponseEntity<String> create(@RequestBody Enrollment enrollment,
                                         @PathVariable("employee-id") Long empId,
                                         @PathVariable("course-id") Long courseId){
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.create(enrollment, empId, courseId));
    }

    @GetMapping("/enrollments/{enrollment-id}")
    public ResponseEntity<Enrollment> getById(@PathVariable("enrollment-id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getEnrollmentById(id));
    }

    @GetMapping("/employees/{employee-id}/enrollments")
    public ResponseEntity<List<Enrollment>> getByEmployeeId(@PathVariable("employee-id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getAllEnrollments(id));
    }

    @DeleteMapping("/enrollments/{enrollment-id}")
    public ResponseEntity<String> delete(@PathVariable("enrollment-id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.delete(id));
    }
}
