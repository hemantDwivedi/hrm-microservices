package com.hrm.recruitmentservice.controller;

import com.hrm.recruitmentservice.dto.request.ApplicantRequest;
import com.hrm.recruitmentservice.dto.response.ApplicantResponse;
import com.hrm.recruitmentservice.service.ApplicantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class ApplicantController {
    private final ApplicantService applicantService;

    // save applicant, returns a applicant ID
    @PostMapping("/{jobId}/applicants")
    public ResponseEntity<Integer> createJob(@Valid @RequestBody ApplicantRequest applicantRequest,
                                             @PathVariable("jobId") Integer jobId){
        return new ResponseEntity<>(applicantService.createApplicant(jobId, applicantRequest), HttpStatus.CREATED);
    }

    // returns an applicant by ID
    @GetMapping("/applicants/{applicantId}")
    public ResponseEntity<ApplicantResponse> findById(@PathVariable("applicantId") Integer id){
        return ResponseEntity.ok(applicantService.findApplicantById(id));
    }

    // returns a list of applicants by job post
    @GetMapping("/{jobId}/applicants")
    public ResponseEntity<List<ApplicantResponse>> findAllApplicantsByJobId(@PathVariable Integer jobId){
        return ResponseEntity.ok(applicantService.findAllApplicantsByJobId(jobId));
    }

    // delete an applicant
    @DeleteMapping("/applicants/{id}")
    public void deleteJobPostById(@PathVariable Integer id){
        applicantService.deleteApplicant(id);
    }
}
