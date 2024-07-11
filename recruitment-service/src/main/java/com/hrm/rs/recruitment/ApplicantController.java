package com.hrm.rs.recruitment;

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

    // save job post in database and returns a string message
    @PostMapping("/{jobId}/applicants")
    public ResponseEntity<Integer> createJob(@Valid @RequestBody ApplicantRequest applicantRequest,
                                             @PathVariable("jobId") Integer jobId){
        return new ResponseEntity<>(applicantService.createApplicant(jobId, applicantRequest), HttpStatus.CREATED);
    }

    // returns a job post by ID
    @GetMapping("/applicants/{applicantId}")
    public ResponseEntity<ApplicantResponse> findById(@PathVariable("applicantId") Integer id){
        return ResponseEntity.ok(applicantService.findApplicantById(id));
    }

    // returns a list of job post
    @GetMapping("/applicants")
    public ResponseEntity<List<ApplicantResponse>> findAllApplicants(){
        return ResponseEntity.ok(applicantService.findAllApplicants());
    }

    // update job post
//    @PutMapping("/applicants")
//    public void updateJobPost(@Valid @RequestBody ApplicantRequest applicantRequest){
//        applicantService.updateApplicant(applicantRequest);
//    }

    // delete a job post and returns a confirmation message
    @DeleteMapping("/applicants/{id}")
    public void deleteJobPostById(@PathVariable Integer id){
        applicantService.deleteApplicant(id);
    }
}
