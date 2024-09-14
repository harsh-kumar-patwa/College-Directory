package com.example.collegedirectory.service.ServiceImplementation;

import com.example.collegedirectory.model.Enrollment;
import com.example.collegedirectory.repository.EnrollmentRepository;
import com.example.collegedirectory.service.ServiceInterface.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public Enrollment findById(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Enrollment> findAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public List<Enrollment> findByStudent(Long studentId) {
        // Assuming you've added this method to your repository
        return enrollmentRepository.findByStudentId(studentId);
    }

    @Override
    public List<Enrollment> findByCourse(Long courseId) {
        // Assuming you've added this method to your repository
        return enrollmentRepository.findByCourseId(courseId);
    }
}
