package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    Map<String,Object> modelMap = new HashMap<String, Object>();

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> departmentList(){
        modelMap.clear();
        List<Department> list = departmentService.queryDepartment();
        modelMap.put("data",list);
        return modelMap;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String,Object> departmentInsert(@RequestBody Map<String,Object> data){
        modelMap.clear();
        String departmentName = (String) data.get("DName");
        boolean insertSign = departmentService.insertDepartment(departmentName);
        if(insertSign==true){
            modelMap.put("error",0);
            modelMap.put("msg","插入成功！");
        }else{
            modelMap.put("error",-1);
            modelMap.put("msg","插入失败！");
        }
        return modelMap;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> departmentUpdate(@RequestBody Map<String,Object> data){
        modelMap.clear();
        Integer departmentId = (Integer) data.get("DId");
        String departmentName = (String) data.get("DName");
        Department department = new Department();
        department.setDId(departmentId);
        department.setDName(departmentName);
        boolean updateSign = departmentService.updateDepartment(department);
        if(updateSign==true){
            modelMap.put("error",0);
            modelMap.put("msg","更新成功！");
        }else{
            modelMap.put("error",-1);
            modelMap.put("msg","更新失败！");
        }
        return modelMap;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> departmentDelete(@RequestBody Map<String,Object> data){
        modelMap.clear();
        Integer departmentId = (Integer) data.get("DId");
        String departmentName = (String) data.get("DName");
        Department department = new Department();
        department.setDId(departmentId);
        department.setDName(departmentName);
        boolean deleteSign = departmentService.deleteDepartment(department);
        if(deleteSign==true){
            modelMap.put("error",0);
            modelMap.put("msg","删除成功！");
        }else{
            modelMap.put("error",-1);
            modelMap.put("msg","删除失败！");
        }
        return modelMap;
    }
}
