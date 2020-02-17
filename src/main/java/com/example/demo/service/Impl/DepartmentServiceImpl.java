package com.example.demo.service.Impl;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.PeopleDao;
import com.example.demo.dao.PictureDao;
import com.example.demo.dao.RoutineDao;
import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import com.example.demo.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    PeopleDao peopleDao;
    @Autowired
    RoutineDao routineDao;
    @Autowired
    PictureDao pictureDao;
    private boolean canDelete(Integer DId){
        List<Integer> list1 = peopleDao.queryDId();
        List<Integer> list2 = routineDao.queryDId();
        List<Integer> list3 = peopleDao.queryDId();
        List<Integer> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        list = ListUtil.removeDuplicate(list);
        if(list.contains(DId))
            return false;
        else
            return true;
    }

    private boolean dpExit(String departmentName){
        int canInsertSign = 1;
        List<Department> list = departmentDao.queryDepartment();
        for(int i = 0;i < list.size();i++){
            if(list.get(i).getDName()==null||list.get(i).getDName().equals(departmentName)){
                canInsertSign = 0;
                System.out.println("名称重复！无法插入！");
                break;
            }
        }
        if(canInsertSign==0)
            return true;
        else return false;
    }

    @Override
    public List<Department> queryDepartment() {
        return departmentDao.queryDepartment();
    }

    @Override
    public boolean insertDepartment(String departmentName) {
        if(dpExit(departmentName)==false){
            Department department = new Department();
            department.setDName(departmentName);
            if(departmentDao.insertDepartment(department)==1)
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public boolean updateDepartment(Department department) {
        if(dpExit(department.getDName())==false){
            if(departmentDao.updateDepartment(department)==1)
                return true;
            else return false;
        }else
            return false;
    }

    @Override
    public boolean deleteDepartment(Department department) {
        if(canDelete(department.getDId())==true){
            if(departmentDao.deleteDepartment(department)==1)
                return true;
            else
                return false;
        }else
            return false;
    }
}
