package com.example.collegedirectory.repository;

import com.example.collegedirectory.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}