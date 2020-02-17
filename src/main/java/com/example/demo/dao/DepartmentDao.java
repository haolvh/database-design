package com.example.demo.dao;

import com.example.demo.entity.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> queryDepartment();
    Department queryDepartmentByName(String departmentName);
    int insertDepartment(Department department);
    int updateDepartment(Department department);
    int deleteDepartment(Department department);
}
