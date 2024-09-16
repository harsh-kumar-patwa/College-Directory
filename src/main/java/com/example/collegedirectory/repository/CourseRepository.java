package com.example.collegedirectory.repository;

import com.example.collegedirectory.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByDepartmentId(Long departmentId);

    List<Course> findByFacultyId(Long facultyId);

    @Query("SELECT new map(c.faculty.user.name as facultyName, COUNT(c) as courseCount) FROM Course c GROUP BY c.faculty")
    List<Map<String, Object>> findCourseCountByFaculty();

    Course findByIdAndFacultyId(Long courseId, Long facultyId);




}