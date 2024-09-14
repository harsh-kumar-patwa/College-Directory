package com.example.collegedirectory.service.ServiceInterface;

import com.example.collegedirectory.model.Course;
import com.example.collegedirectory.model.FacultyProfile;
import java.util.List;

public interface FacultyProfileService {
    FacultyProfile saveFacultyProfile(FacultyProfile facultyProfile);
    void deleteFacultyProfile(Long id);
    FacultyProfile findById(Long id);
    List<FacultyProfile> findAllFacultyProfiles();
    List<FacultyProfile> findByDepartment(Long departmentId);

    List<Course> getFacultyCourses(Long id);

    Course addFacultyCourse(Long id, Course course);
}

