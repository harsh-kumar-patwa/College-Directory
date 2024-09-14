package com.example.collegedirectory.service.ServiceImplementation;

import com.example.collegedirectory.model.Enrollment;
import com.example.collegedirectory.model.StudentProfile;
import com.example.collegedirectory.repository.EnrollmentRepository;
import com.example.collegedirectory.repository.StudentProfileRepository;
import com.example.collegedirectory.service.ServiceInterface.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public StudentProfile saveStudentProfile(StudentProfile studentProfile) {
        return studentProfileRepository.save(studentProfile);
    }

    @Override
    public void deleteStudentProfile(Long id) {
        studentProfileRepository.deleteById(id);
    }

    @Override
    public StudentProfile findById(Long id) {
        return studentProfileRepository.findById(id).orElse(null);
    }

    @Override
    public List<StudentProfile> findAllStudentProfiles() {
        return studentProfileRepository.findAll();
    }

    @Override
    public List<StudentProfile> findByDepartment(Long departmentId) {
        return studentProfileRepository.findByDepartmentId(departmentId);
    }

    @Override
    public Map<String, Object> getStudentDashboard(Long studentId) {
        StudentProfile student = studentProfileRepository.findById(studentId).orElse(null);
        if (student == null) {
            return null;
        }

        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("student", student);
        dashboard.put("enrollments", enrollments);
        // Add more dashboard data as needed

        return dashboard;
    }
}
