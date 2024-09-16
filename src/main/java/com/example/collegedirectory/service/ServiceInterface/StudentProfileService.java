package com.example.collegedirectory.service.ServiceInterface;

import com.example.collegedirectory.dto.FacultyAdvisorDTO;
import com.example.collegedirectory.dto.StudentProfileDTO;
import com.example.collegedirectory.model.FacultyProfile;
import com.example.collegedirectory.model.StudentProfile;
import java.util.List;
import java.util.Map;

public interface StudentProfileService {
    StudentProfileDTO getStudentProfile(Long id);
    StudentProfile saveStudentProfile(StudentProfile studentProfile);
    void deleteStudentProfile(Long id);
    StudentProfile findById(Long id);
    List<StudentProfile> findAllStudentProfiles();
    List<StudentProfile> findByDepartment(Long departmentId);
    Map<String, Object> getStudentDashboard(Long id);
    List<StudentProfileDTO> searchStudents(String name, Long departmentId, String year);
    List<FacultyAdvisorDTO> getFacultyAdvisors(Long studentId);
    boolean contactFacultyAdvisor(Long studentId, Long facultyId, String message);

}