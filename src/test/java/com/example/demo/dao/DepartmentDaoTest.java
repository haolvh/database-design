package com.example.demo.dao;


import com.example.demo.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentDaoTest {

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void queryDepartment() {
        List<Department> list = departmentDao.queryDepartment();
        System.out.println(list.get(0).getDId());
    }

    @Test
    public void insertDepartment() {
        Department department = new Department();
        department.setDName("33333");
        int sign = departmentDao.insertDepartment(department);
        System.out.println(sign);
    }

    @Test
    public void deleteDepartment() {
        Department department = new Department();
        department.setDId(1);
        int sign = departmentDao.deleteDepartment(department);
        System.out.println(sign);
    }

    @Test
    public void updateDepartment() {
        Department department = new Department();
        department.setDId(2);
        department.setDName("1111111");
        int sign = departmentDao.updateDepartment(department);
        System.out.println(sign);
    }
}
