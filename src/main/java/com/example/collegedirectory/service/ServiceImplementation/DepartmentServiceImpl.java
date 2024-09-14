package com.example.collegedirectory.service.ServiceImplementation;

import com.example.collegedirectory.model.Department;
import com.example.collegedirectory.repository.DepartmentRepository;
import com.example.collegedirectory.service.ServiceInterface.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }
}
