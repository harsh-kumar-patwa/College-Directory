package com.example.collegedirectory.service.ServiceInterface;

import com.example.collegedirectory.model.AdministratorProfile;
import java.util.List;

public interface AdministratorProfileService {
    AdministratorProfile saveAdministratorProfile(AdministratorProfile administratorProfile);
    void deleteAdministratorProfile(Long id);
    AdministratorProfile findById(Long id);
    List<AdministratorProfile> findAllAdministratorProfiles();
}
