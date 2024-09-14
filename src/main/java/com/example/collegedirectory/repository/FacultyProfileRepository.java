package com.example.collegedirectory.repository;

import com.example.collegedirectory.model.FacultyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Long> {
    List<FacultyProfile> findByDepartmentId(Long departmentId);
}