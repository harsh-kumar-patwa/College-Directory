package com.example.collegedirectory.repository;

import com.example.collegedirectory.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    List<StudentProfile> findByDepartmentId(Long departmentId);

    List<StudentProfile> findByUser_NameContainingAndDepartment_IdAndYear(String name, Long departmentId, String year);
}