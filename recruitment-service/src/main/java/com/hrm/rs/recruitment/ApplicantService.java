package com.hrm.rs.recruitment;

import com.hrm.rs.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ApplicantService {
    private final JobPostRepository jobPostRepository;
    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper mapper;

    public Integer createApplicant(Integer jobId, ApplicantRequest applicantRequest) {
        var jobPost = jobPostRepository.findById(jobId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                format("No job post found with ID:: %s", jobId)
                        )
                );
        var applicant = mapper.toApplicant(applicantRequest);
        applicant.setJobPost(jobPost);
        var save = applicantRepository.save(applicant);
        return save.getId();
    }

    public ApplicantResponse findApplicantById(Integer id) {
        var applicant = applicantRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                format("No applicant found with ID:: %s", id)
                        )
                );
        return mapper.toApplicantResponse(applicant);
    }

    public List<ApplicantResponse> findAllApplicants() {
        return applicantRepository
                .findAll()
                .stream()
                .map(mapper::toApplicantResponse)
                .collect(Collectors.toList());
    }

    public void deleteApplicant(Integer id) {
        applicantRepository.deleteById(id);
    }

//    public void updateApplicant(ApplicantRequest applicantRequest) {
//        var applicant = applicantRepository.findById(applicantRequest.id())
//                .orElseThrow(
//                        () -> new ResourceNotFoundException(
//                                format("No applicant found with ID:: %s", applicantRequest.id())
//                        )
//                );
//
//        mergeApplicant(applicant, applicantRequest);
//        applicantRepository.save(applicant);
//    }

    // This method make sures to not override existing data properties with null/empty values
//    private void mergeApplicant(Applicant applicant, ApplicantRequest applicantRequest) {
//        if (isNotBlank(applicantRequest.firstName())) applicant.setFirstName(applicantRequest.firstName());
//        if (isNotBlank(applicantRequest.lastName())) applicant.setLastName(applicantRequest.lastName());
//        if (isNotBlank(applicantRequest.email())) applicant.setEmail(applicantRequest.email());
//        if (isNotBlank(applicantRequest.phone())) applicant.setPhone(applicantRequest.phone());
//        if (isNotBlank(applicantRequest.resumeUrl())) applicant.setResumeUrl(applicantRequest.resumeUrl());
//        if (isNotBlank(applicantRequest.location())) applicant.setLocation(applicantRequest.location());
//        if (isNotBlank(applicantRequest.status())) applicant.setStatus(applicantRequest.status());
//    }
}
