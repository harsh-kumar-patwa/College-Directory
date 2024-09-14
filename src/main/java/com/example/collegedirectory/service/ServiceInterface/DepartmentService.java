package com.example.collegedirectory.service.ServiceInterface;

import com.example.collegedirectory.model.Department;
import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);
    void deleteDepartment(Long id);
    Department findById(Long id);
    List<Department> findAllDepartments();
}
