package com.example.collegedirectory.controller;

import com.example.collegedirectory.dto.FacultyProfileDTO;
import com.example.collegedirectory.dto.StudentProfileDTO;
import com.example.collegedirectory.model.Course;
import com.example.collegedirectory.model.FacultyProfile;

import com.example.collegedirectory.service.ServiceInterface.FacultyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/faculty")
public class FacultyProfileController {

    @Autowired
    private FacultyProfileService facultyProfileService;

    @GetMapping
    public ResponseEntity<List<FacultyProfile>> getAllFacultyProfiles() {
        return ResponseEntity.ok(facultyProfileService.findAllFacultyProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyProfile> getFacultyProfileById(@PathVariable Long id) {
        FacultyProfile facultyProfile = facultyProfileService.findById(id);
        if (facultyProfile != null) {
            return ResponseEntity.ok(facultyProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}/profile")
    public ResponseEntity<FacultyProfileDTO> getFacultyProfile(@PathVariable Long id) {
        FacultyProfileDTO profile = facultyProfileService.getFacultyProfile(id);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{facultyId}/class-list/{courseId}")
    public ResponseEntity<List<StudentProfileDTO>> getClassList(@PathVariable Long facultyId, @PathVariable Long courseId) {
        List<StudentProfileDTO> students = facultyProfileService.getClassList(facultyId, courseId);
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<FacultyProfile> createFacultyProfile(@RequestBody FacultyProfile facultyProfile) {
        return ResponseEntity.ok(facultyProfileService.saveFacultyProfile(facultyProfile));
    }

    @PutMapping("/{id}/update-profile")
    public ResponseEntity<FacultyProfileDTO> updateFacultyProfile(@PathVariable Long id, @RequestBody FacultyProfileDTO updatedProfile) {
        FacultyProfileDTO updated = facultyProfileService.updateFacultyProfile(id, updatedProfile);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacultyProfile(@PathVariable Long id) {
        FacultyProfile existingFacultyProfile = facultyProfileService.findById(id);
        if (existingFacultyProfile != null) {
            facultyProfileService.deleteFacultyProfile(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<FacultyProfile>> getFacultyProfilesByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(facultyProfileService.findByDepartment(departmentId));
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<Course>> getFacultyCourses(@PathVariable Long id) {
        List<Course> courses = facultyProfileService.getFacultyCourses(id);
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/{id}/courses")
    public ResponseEntity<Course> addFacultyCourse(@PathVariable Long id, @RequestBody Course course) {
        Course addedCourse = facultyProfileService.addFacultyCourse(id, course);
        if (addedCourse != null) {
            return ResponseEntity.ok(addedCourse);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/contact-info")
    public ResponseEntity<Boolean> updateContactInfo(
            @PathVariable Long id,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String officeHours) {
        boolean updated = facultyProfileService.updateContactInfo(id, email, phone, officeHours);
        if (updated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }
}
