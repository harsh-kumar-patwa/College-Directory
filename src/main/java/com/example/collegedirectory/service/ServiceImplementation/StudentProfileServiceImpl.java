package com.example.collegedirectory.service.ServiceImplementation;

import com.example.collegedirectory.dto.FacultyAdvisorDTO;
import com.example.collegedirectory.dto.StudentProfileDTO;
import com.example.collegedirectory.model.Enrollment;
import com.example.collegedirectory.model.FacultyProfile;
import com.example.collegedirectory.model.StudentProfile;
import com.example.collegedirectory.repository.EnrollmentRepository;
import com.example.collegedirectory.repository.FacultyProfileRepository;
import com.example.collegedirectory.repository.StudentProfileRepository;
import com.example.collegedirectory.service.ServiceInterface.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

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

        return dashboard;
    }

    @Override
    public List<StudentProfileDTO> searchStudents(String name, Long departmentId, String year) {
        List<StudentProfile> students = studentProfileRepository.findByUser_NameContainingAndDepartment_IdAndYear(name, departmentId, year);
        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<FacultyAdvisorDTO> getFacultyAdvisors(Long studentId) {
        StudentProfile student = studentProfileRepository.findById(studentId).orElse(null);
        if (student == null) {
            return List.of();
        }
        List<FacultyProfile> advisors = facultyProfileRepository.findByDepartmentId(student.getDepartment().getId());
        return advisors.stream().map(this::convertToFacultyAdvisorDTO).collect(Collectors.toList());
    }
    @Override
    public StudentProfileDTO getStudentProfile(Long id) {
        StudentProfile student = studentProfileRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }
        return convertToDTO(student);
    }

    @Override
    public boolean contactFacultyAdvisor(Long studentId, Long facultyId, String message) {
        // Implement the logic to send a message (e.g., email) to the faculty advisor
        // This is a placeholder implementation
        System.out.println("Message from student " + studentId + " to faculty " + facultyId + ": " + message);
        return true;
    }

    private StudentProfileDTO convertToDTO(StudentProfile student) {
        StudentProfileDTO dto = new StudentProfileDTO();
        dto.setId(student.getId());
        dto.setName(student.getUser().getName());
        dto.setPhoto(student.getPhoto());
        dto.setEmail(student.getUser().getEmail());
        dto.setDepartment(student.getDepartment().getName());
        dto.setYear(student.getYear());
        return dto;
    }
    private FacultyAdvisorDTO convertToFacultyAdvisorDTO(FacultyProfile faculty) {
        FacultyAdvisorDTO dto = new FacultyAdvisorDTO();
        dto.setId(faculty.getId());
        dto.setName(faculty.getUser().getName());
        dto.setEmail(faculty.getUser().getEmail());
        dto.setDepartment(faculty.getDepartment().getName());
        dto.setOfficeHours(faculty.getOfficeHours());
        return dto;
    }
}
