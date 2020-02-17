package com.example.demo.service;

import com.example.demo.entity.People;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PeopleService {
    List<People> queryDepartmentPeople();
    List<String> insertPeople(String peopleName,
                        String peopleTask,
                        String peopleBirthday,
                        String peopleBirthplace,
                        String peopleIntroduce,
                        String peopleQQ,
                        MultipartFile peopleImage);
    List<String> updatePeople(String peopleName,
                              String peopleTask,
                              String peopleBirthday,
                              String peopleBirthplace,
                              String peopleIntroduce,
                              String peopleQQ,
                              MultipartFile peopleImage,
                              Integer peopleId);
    List<String> deletePeople(Integer peopleId);
}
