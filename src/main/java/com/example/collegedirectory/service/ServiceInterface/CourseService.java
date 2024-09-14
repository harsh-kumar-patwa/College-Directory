package com.example.collegedirectory.service.ServiceInterface;

import com.example.collegedirectory.model.Course;
import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    Course findById(Long id);
    List<Course> findAllCourses();
    List<Course> findByDepartment(Long departmentId);
    List<Course> findByFaculty(Long facultyId);
}