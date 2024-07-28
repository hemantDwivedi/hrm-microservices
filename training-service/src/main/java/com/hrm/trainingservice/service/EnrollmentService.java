package com.hrm.trainingservice.service;

import com.hrm.trainingservice.client.ApiClient;
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

    public String create(Enrollment enrollment, Long empId, Long courseId){
        Boolean employee = apiClient.existById(empId);
        if (!employee) throw new ResourceNotFoundException("Employee not found");
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("Course not found::%s", courseId)
                )
        );
        enrollment.setCourse(course);
        enrollment.setEmployeeId(empId);
        Enrollment dbEnrollment = enrollmentRepository.save(enrollment);
        return format("Enrollment ID:: %s", dbEnrollment.getId());
    }

    public Enrollment getEnrollmentById(Integer id){
        return enrollmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        format("Enrollment not found::%s", id)
                )
        );
    }

    public List<Enrollment> getAllEnrollments(Long empId){
        List<Enrollment> enrollments = enrollmentRepository.findByEmployeeId(empId);
        if (enrollments.isEmpty()) throw new ResourceNotFoundException(
                "Enrollment not found"
        );
        return enrollments;
    }

    public String delete(Integer id){
        enrollmentRepository.deleteById(id);
        return format("Enrollment:: %s deleted!", id);
    }
}
