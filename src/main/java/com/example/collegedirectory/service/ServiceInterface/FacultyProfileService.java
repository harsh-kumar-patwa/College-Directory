package com.example.collegedirectory.service.ServiceInterface;

import com.example.collegedirectory.dto.FacultyProfileDTO;
import com.example.collegedirectory.dto.StudentProfileDTO;
import com.example.collegedirectory.model.Course;
import com.example.collegedirectory.model.FacultyProfile;
import com.example.collegedirectory.model.StudentProfile;

import java.util.List;

public interface FacultyProfileService {
    FacultyProfile saveFacultyProfile(FacultyProfile facultyProfile);
    void deleteFacultyProfile(Long id);
    FacultyProfile findById(Long id);
    List<FacultyProfile> findAllFacultyProfiles();
    List<FacultyProfile> findByDepartment(Long departmentId);
    FacultyProfileDTO getFacultyProfile(Long id);
    List<Course> getFacultyCourses(Long id);
    FacultyProfileDTO updateFacultyProfile(Long id, FacultyProfileDTO updatedProfile);
    Course addFacultyCourse(Long id, Course course);
    List<StudentProfileDTO> getClassList(Long facultyId, Long courseId);
    boolean updateContactInfo(Long facultyId, String email, String phone, String officeHours);
}

