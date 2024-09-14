package com.example.collegedirectory.service.ServiceInterface;

import com.example.collegedirectory.model.StudentProfile;
import java.util.List;

public interface StudentProfileService {
    StudentProfile saveStudentProfile(StudentProfile studentProfile);
    void deleteStudentProfile(Long id);
    StudentProfile findById(Long id);
    List<StudentProfile> findAllStudentProfiles();
    List<StudentProfile> findByDepartment(Long departmentId);
}