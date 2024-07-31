package com.hrm.trainingservice.service;

import com.hrm.trainingservice.client.ApiClient;
import com.hrm.trainingservice.dto.request.EnrollmentRequest;
import com.hrm.trainingservice.entity.Course;
import com.hrm.trainingservice.entity.Enrollment;
import com.hrm.trainingservice.exception.ResourceNotFoundException;
import com.hrm.trainingservice.repository.CourseRepository;
import com.hrm.trainingservice.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final ApiClient apiClient;

    public String create(EnrollmentRequest enrollmentRequest) {
        Boolean employee = apiClient.existById(enrollmentRequest.getEmployeeId());
        if (!employee)
            throw new ResourceNotFoundException(format("Employee::%s not found", enrollmentRequest.getEmployeeId()));
        Course course = courseRepository.findById(enrollmentRequest.getCourseId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("Course not found::%s", enrollmentRequest.getCourseId())
                )
        );
        Enrollment enrollment = Enrollment.builder()
                .enrollmentDate(enrollmentRequest.getEnrollmentDate())
                .completionStatus(enrollmentRequest.getCompletionStatus())
                .employeeId(enrollmentRequest.getEmployeeId())
                .course(course)
                .build();
        Enrollment dbEnrollment = enrollmentRepository.save(enrollment);
        return format("Enrollment ID:: %s", dbEnrollment.getId());
    }

    public Enrollment getEnrollmentById(Integer id) {
        return enrollmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("Enrollment not found::%s", id)
                )
        );
    }

    public List<Enrollment> getAllEnrollments(Long empId) {
        List<Enrollment> enrollments = enrollmentRepository.findByEmployeeId(empId);
        if (enrollments.isEmpty()) throw new ResourceNotFoundException(
                format("Enrollments:: %s not found", empId)
        );
        return enrollments;
    }

    public void delete(Integer id) {
        if (enrollmentRepository.existsById(id)) enrollmentRepository.deleteById(id);
        else  throw new ResourceNotFoundException(
                format("Enrollment:: %s not found", id)
        );
    }
}
