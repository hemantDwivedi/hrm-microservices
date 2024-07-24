package com.hrm.recruitmentservice.helper;

import com.hrm.recruitmentservice.dto.request.ApplicantRequest;
import com.hrm.recruitmentservice.dto.response.ApplicantResponse;
import com.hrm.recruitmentservice.entity.Applicant;
import org.springframework.stereotype.Service;

@Service
public class ApplicantMapper {
    public Applicant toApplicant(ApplicantRequest request){
        return Applicant
                .builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phone(request.phone())
                .location(request.location())
                .resumeUrl(request.resumeUrl())
                .status(request.status())
                .build();
    }

    public ApplicantResponse toApplicantResponse(Applicant applicant) {
        return new ApplicantResponse(
                applicant.getId(),
                applicant.getFirstName(),
                applicant.getLastName(),
                applicant.getEmail(),
                applicant.getPhone(),
                applicant.getResumeUrl(),
                applicant.getLocation(),
                applicant.getStatus()
        );
    }
}
