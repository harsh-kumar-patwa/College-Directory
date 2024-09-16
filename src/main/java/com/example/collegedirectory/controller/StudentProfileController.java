package com.example.collegedirectory.controller;

import com.example.collegedirectory.dto.FacultyAdvisorDTO;
import com.example.collegedirectory.dto.StudentProfileDTO;
import com.example.collegedirectory.model.StudentProfile;
import com.example.collegedirectory.service.ServiceInterface.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAllStudentProfiles() {
        return ResponseEntity.ok(studentProfileService.findAllStudentProfiles());
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<StudentProfileDTO> getStudentProfile(@PathVariable Long id) {
        StudentProfileDTO profile = studentProfileService.getStudentProfile(id);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getStudentProfileById(@PathVariable Long id) {
        StudentProfile studentProfile = studentProfileService.findById(id);
        if (studentProfile != null) {
            return ResponseEntity.ok(studentProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<StudentProfile> createStudentProfile(@RequestBody StudentProfile studentProfile) {
        return ResponseEntity.ok(studentProfileService.saveStudentProfile(studentProfile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfile> updateStudentProfile(@PathVariable Long id, @RequestBody StudentProfile studentProfile) {
        StudentProfile existingStudentProfile = studentProfileService.findById(id);
        if (existingStudentProfile != null) {
            studentProfile.setUser(existingStudentProfile.getUser());
            return ResponseEntity.ok(studentProfileService.saveStudentProfile(studentProfile));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentProfile(@PathVariable Long id) {
        StudentProfile existingStudentProfile = studentProfileService.findById(id);
        if (existingStudentProfile != null) {
            studentProfileService.deleteStudentProfile(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<StudentProfile>> getStudentProfilesByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(studentProfileService.findByDepartment(departmentId));
    }

    @GetMapping("/{id}/dashboard")
    public ResponseEntity<Map<String, Object>> getStudentDashboard(@PathVariable Long id) {
        Map<String, Object> dashboard = studentProfileService.getStudentDashboard(id);
        if (dashboard != null) {
            return ResponseEntity.ok(dashboard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<StudentProfileDTO>> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) String year) {
        List<StudentProfileDTO> students = studentProfileService.searchStudents(name, departmentId, year);
        return ResponseEntity.ok(students);
    }
    @GetMapping("/{id}/advisors")
    public ResponseEntity<List<FacultyAdvisorDTO>> getFacultyAdvisors(@PathVariable Long id) {
        List<FacultyAdvisorDTO> advisors = studentProfileService.getFacultyAdvisors(id);
        return ResponseEntity.ok(advisors);
    }

    @PostMapping("/{studentId}/contact-advisor/{facultyId}")
    public ResponseEntity<String> contactFacultyAdvisor(
            @PathVariable Long studentId,
            @PathVariable Long facultyId,
            @RequestBody String message) {
        boolean success = studentProfileService.contactFacultyAdvisor(studentId, facultyId, message);
        if (success) {
            return ResponseEntity.ok("Message sent successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to send message");
        }
    }

}
