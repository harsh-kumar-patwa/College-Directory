package com.example.collegedirectory.controller;

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

    @PostMapping
    public ResponseEntity<FacultyProfile> createFacultyProfile(@RequestBody FacultyProfile facultyProfile) {
        return ResponseEntity.ok(facultyProfileService.saveFacultyProfile(facultyProfile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyProfile> updateFacultyProfile(@PathVariable Long id, @RequestBody FacultyProfile facultyProfile) {
        FacultyProfile existingFacultyProfile = facultyProfileService.findById(id);
        if (existingFacultyProfile != null) {
            facultyProfile.setUser(existingFacultyProfile.getUser());
            return ResponseEntity.ok(facultyProfileService.saveFacultyProfile(facultyProfile));
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
        // This assumes you've added a getFacultyCourses method to your FacultyProfileService
        List<Course> courses = facultyProfileService.getFacultyCourses(id);
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/{id}/courses")
    public ResponseEntity<Course> addFacultyCourse(@PathVariable Long id, @RequestBody Course course) {
        // This assumes you've added an addFacultyCourse method to your FacultyProfileService
        Course addedCourse = facultyProfileService.addFacultyCourse(id, course);
        if (addedCourse != null) {
            return ResponseEntity.ok(addedCourse);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
