package com.example.collegedirectory.repository;

import com.example.collegedirectory.model.Enrollment;
import com.example.collegedirectory.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Map;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);

    List<Enrollment> findByCourseId(Long courseId);

    Enrollment findByStudentIdAndCourseId(Long studentId, Long courseId);

    List<StudentProfile> findStudentsByCourseId(Long courseId);

    @Query("SELECT new map(FUNCTION('YEAR', e.enrollmentDate) as year, COUNT(e) as count) FROM Enrollment e GROUP BY FUNCTION('YEAR', e.enrollmentDate)")
    List<Map<String, Object>> findEnrollmentCountByYear();
}