package com.example.collegedirectory.service.ServiceImplementation;

import com.example.collegedirectory.model.Course;
import com.example.collegedirectory.repository.CourseRepository;
import com.example.collegedirectory.service.ServiceInterface.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findByDepartment(Long departmentId) {
        return courseRepository.findByDepartmentId(departmentId);
    }

    @Override
    public List<Course> findByFaculty(Long facultyId) {
        return courseRepository.findByFacultyId(facultyId);
    }
}
