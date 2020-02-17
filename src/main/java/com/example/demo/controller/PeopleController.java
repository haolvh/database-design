package com.example.demo.controller;

import com.example.demo.entity.People;
import com.example.demo.service.PeopleService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/people")
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    Map<String,Object> modelMap = new HashMap<String, Object>();

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> peopleList(){
       modelMap.clear();
       List<People> list = peopleService.queryDepartmentPeople();
       modelMap.put("data",list);
       return modelMap;
    }
    /*public String peopleList(){
        modelMap.clear();
        List<People> list = peopleService.queryDepartmentPeople();
        modelMap.put("data",list);
        System.out.println(list.size());
        class member{
            String key;
            List<People> value;
            public member(String key, List<People> val){
                this.key = key;
                value = new ArrayList<>();
                value.
            }
        }
        String str = new Gson().toJson(new member("data", list));
//        String jsonObject = (new Gson()).toJson(new member("data",list));
        System.out.println(str);
        return str;
    }*/
    @RequestMapping("/insert")
    @ResponseBody
    public Map<String,Object> peopleInsert(@RequestParam("peopleName") String peopleName,
                                           @RequestParam("peopleTask") String peopleTask,
                                           @RequestParam("peopleBirthday") String peopleBirthday,
                                           @RequestParam("peopleBirthplace") String peopleBirthplace,
                                           @RequestParam("peopleIntroduce") String peopleIntroduce,
                                           @RequestParam("peopleQQ") String peopleQQ,
                                           @RequestParam(value = "peopleImage",required = false)MultipartFile peopleImage){
        modelMap.clear();
        List<String> list = peopleService.insertPeople(peopleName,peopleTask,peopleBirthday,peopleBirthplace,peopleIntroduce,peopleQQ,peopleImage);
        modelMap.put("data",list);
        modelMap.put("error",0);
        return modelMap;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> peopleUpdate(@RequestParam("peopleName") String peopleName,
                                           @RequestParam("peopleTask") String peopleTask,
                                           @RequestParam("peopleBirthday") String peopleBirthday,
                                           @RequestParam("peopleBirthplace") String peopleBirthplace,
                                           @RequestParam("peopleIntroduce") String peopleIntroduce,
                                           @RequestParam("peopleQQ") String peopleQQ,
                                           @RequestParam("peopleImage")MultipartFile peopleImage,
                                           @RequestParam("peopleId") Integer peopleId){
        modelMap.clear();
        List<String> list = peopleService.updatePeople(peopleName,peopleTask,peopleBirthday,peopleBirthplace,peopleIntroduce,peopleQQ,peopleImage,peopleId);
        modelMap.put("data",list);
        modelMap.put("error",0);
        return modelMap;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> peopleDelete(@RequestBody Map<String,Object> data){
        modelMap.clear();
        System.out.println(data);
        Integer peopleId = (Integer) data.get("peopleId");
        List<String> list = peopleService.deletePeople(peopleId);
        modelMap.put("data",list);
        modelMap.put("error",0);
        return modelMap;
    }
}
