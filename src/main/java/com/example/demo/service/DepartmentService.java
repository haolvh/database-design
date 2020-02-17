package com.example.demo.service;

import com.example.demo.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> queryDepartment();
    boolean insertDepartment(String departmentName);
    boolean updateDepartment(Department department);
    boolean deleteDepartment(Department department);
}
