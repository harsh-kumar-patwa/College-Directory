package com.example.collegedirectory.service.ServiceInterface;

import com.example.collegedirectory.model.Enrollment;
import java.util.List;

public interface EnrollmentService {
    Enrollment saveEnrollment(Enrollment enrollment);
    void deleteEnrollment(Long id);
    Enrollment findById(Long id);
    List<Enrollment> findAllEnrollments();
    List<Enrollment> findByStudent(Long studentId);
    List<Enrollment> findByCourse(Long courseId);
}
