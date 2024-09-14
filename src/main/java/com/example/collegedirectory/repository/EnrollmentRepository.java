package com.example.collegedirectory.repository;

import com.example.collegedirectory.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);

    List<Enrollment> findByCourseId(Long courseId);

    Enrollment findByStudentIdAndCourseId(Long studentId, Long courseId);
}