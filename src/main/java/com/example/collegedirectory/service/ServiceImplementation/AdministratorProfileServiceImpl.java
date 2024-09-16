package com.example.collegedirectory.service.ServiceImplementation;

import com.example.collegedirectory.model.AdministratorProfile;
import com.example.collegedirectory.repository.AdministratorProfileRepository;
import com.example.collegedirectory.repository.CourseRepository;
import com.example.collegedirectory.repository.EnrollmentRepository;
import com.example.collegedirectory.service.ServiceInterface.AdministratorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdministratorProfileServiceImpl implements AdministratorProfileService {

    @Autowired
    private AdministratorProfileRepository administratorProfileRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public AdministratorProfile saveAdministratorProfile(AdministratorProfile administratorProfile) {
        return administratorProfileRepository.save(administratorProfile);
    }

    @Override
    public void deleteAdministratorProfile(Long id) {
        administratorProfileRepository.deleteById(id);
    }

    @Override
    public AdministratorProfile findById(Long id) {
        return administratorProfileRepository.findById(id).orElse(null);
    }

    @Override
    public List<AdministratorProfile> findAllAdministratorProfiles() {
        return administratorProfileRepository.findAll();
    }

    @Override
    public List<Map<String, Object>> getStudentEnrollmentTrends() {
        return enrollmentRepository.findEnrollmentCountByYear();
    }

    @Override
    public List<Map<String, Object>> getFacultyCourseLoads() {
        return courseRepository.findCourseCountByFaculty();
    }
}