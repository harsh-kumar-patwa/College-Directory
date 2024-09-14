package com.example.collegedirectory.controller;

import com.example.collegedirectory.model.Enrollment;
import com.example.collegedirectory.service.ServiceInterface.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.findAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        Enrollment enrollment = enrollmentService.findById(id);
        if (enrollment != null) {
            return ResponseEntity.ok(enrollment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentService.saveEnrollment(enrollment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        Enrollment existingEnrollment = enrollmentService.findById(id);
        if (existingEnrollment != null) {
            enrollment.setId(id);
            return ResponseEntity.ok(enrollmentService.saveEnrollment(enrollment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        Enrollment existingEnrollment = enrollmentService.findById(id);
        if (existingEnrollment != null) {
            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.findByStudent(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.findByCourse(courseId));
    }

    @PostMapping("/enroll")
    public ResponseEntity<Enrollment> enrollStudentInCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        // This method assumes you've added an enrollStudentInCourse method to your EnrollmentService
        Enrollment newEnrollment = enrollmentService.enrollStudentInCourse(studentId, courseId);
        if (newEnrollment != null) {
            return ResponseEntity.ok(newEnrollment);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/unenroll")
    public ResponseEntity<Void> unenrollStudentFromCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        // This method assumes you've added an unenrollStudentFromCourse method to your EnrollmentService
        boolean success = enrollmentService.unenrollStudentFromCourse(studentId, courseId);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
