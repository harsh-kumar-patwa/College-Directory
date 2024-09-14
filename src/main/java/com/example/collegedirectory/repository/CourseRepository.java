package com.example.collegedirectory.repository;

import com.example.collegedirectory.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByDepartmentId(Long departmentId);

    List<Course> findByFacultyId(Long facultyId);
}