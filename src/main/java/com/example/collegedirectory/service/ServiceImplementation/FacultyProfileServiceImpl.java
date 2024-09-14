package com.example.collegedirectory.service.ServiceImplementation;

import com.example.collegedirectory.model.Course;
import com.example.collegedirectory.model.FacultyProfile;
import com.example.collegedirectory.repository.CourseRepository;
import com.example.collegedirectory.repository.FacultyProfileRepository;
import com.example.collegedirectory.service.ServiceInterface.FacultyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyProfileServiceImpl implements FacultyProfileService {

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public FacultyProfile saveFacultyProfile(FacultyProfile facultyProfile) {
        return facultyProfileRepository.save(facultyProfile);
    }

    @Override
    public void deleteFacultyProfile(Long id) {
        facultyProfileRepository.deleteById(id);
    }

    @Override
    public FacultyProfile findById(Long id) {
        return facultyProfileRepository.findById(id).orElse(null);
    }

    @Override
    public List<FacultyProfile> findAllFacultyProfiles() {
        return facultyProfileRepository.findAll();
    }

    @Override
    public List<FacultyProfile> findByDepartment(Long departmentId) {
        // Assuming you've added this method to your repository
        return facultyProfileRepository.findByDepartmentId(departmentId);
    }

    @Override
    public List<Course> getFacultyCourses(Long facultyId) {
        return courseRepository.findByFacultyId(facultyId);
    }

    @Override
    public Course addFacultyCourse(Long facultyId, Course course) {
        FacultyProfile faculty = facultyProfileRepository.findById(facultyId).orElse(null);
        if (faculty != null) {
            course.setFaculty(faculty);
            return courseRepository.save(course);
        }
        return null;
    }
}