package com.example.collegedirectory.service.ServiceImplementation;

import com.example.collegedirectory.model.StudentProfile;
import com.example.collegedirectory.repository.StudentProfileRepository;
import com.example.collegedirectory.service.ServiceInterface.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

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
}