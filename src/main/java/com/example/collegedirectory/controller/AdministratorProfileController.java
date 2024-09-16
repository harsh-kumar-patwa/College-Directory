package com.example.collegedirectory.controller;

import com.example.collegedirectory.model.AdministratorProfile;
import com.example.collegedirectory.service.ServiceInterface.AdministratorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/administrators")
public class AdministratorProfileController {

    @Autowired
    private AdministratorProfileService administratorProfileService;

    @GetMapping
    public ResponseEntity<List<AdministratorProfile>> getAllAdministratorProfiles() {
        return ResponseEntity.ok(administratorProfileService.findAllAdministratorProfiles());
    }
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getAdminDashboard() {
        Map<String, Object> dashboardData = new HashMap<>();
        dashboardData.put("studentEnrollmentTrends", administratorProfileService.getStudentEnrollmentTrends());
        dashboardData.put("facultyCourseLoads", administratorProfileService.getFacultyCourseLoads());
        return ResponseEntity.ok(dashboardData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministratorProfile> getAdministratorProfileById(@PathVariable Long id) {
        AdministratorProfile administratorProfile = administratorProfileService.findById(id);
        if (administratorProfile != null) {
            return ResponseEntity.ok(administratorProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AdministratorProfile> createAdministratorProfile(@RequestBody AdministratorProfile administratorProfile) {
        return ResponseEntity.ok(administratorProfileService.saveAdministratorProfile(administratorProfile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministratorProfile> updateAdministratorProfile(@PathVariable Long id, @RequestBody AdministratorProfile administratorProfile) {
        AdministratorProfile existingAdministratorProfile = administratorProfileService.findById(id);
        if (existingAdministratorProfile != null) {
            administratorProfile.setUser(existingAdministratorProfile.getUser());
            return ResponseEntity.ok(administratorProfileService.saveAdministratorProfile(administratorProfile));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministratorProfile(@PathVariable Long id) {
        AdministratorProfile existingAdministratorProfile = administratorProfileService.findById(id);
        if (existingAdministratorProfile != null) {
            administratorProfileService.deleteAdministratorProfile(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
