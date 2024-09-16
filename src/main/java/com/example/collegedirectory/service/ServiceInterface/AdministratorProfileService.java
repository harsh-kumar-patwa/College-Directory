package com.example.collegedirectory.service.ServiceInterface;

import com.example.collegedirectory.model.AdministratorProfile;
import java.util.List;
import java.util.Map;

public interface AdministratorProfileService {
    AdministratorProfile saveAdministratorProfile(AdministratorProfile administratorProfile);
    void deleteAdministratorProfile(Long id);
    AdministratorProfile findById(Long id);
    List<AdministratorProfile> findAllAdministratorProfiles();
    List<Map<String, Object>> getStudentEnrollmentTrends();
    List<Map<String, Object>> getFacultyCourseLoads();
}
