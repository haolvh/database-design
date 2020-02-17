package com.example.demo.dao;

import com.example.demo.entity.People;

import java.util.List;

public interface PeopleDao {
    List<People> queryAllPeople();
    List<People> queryPeopleByDId(Integer DId);
    List<Integer> queryDId();
    List<Integer> queryTeamId();
    List<Integer> queryPeopleIdByTeamId(Integer teamId);
    List<People> queryPeopleByTask(Integer teamId);
    People queryPeopleById(Integer peopleId);
    People queryPeopleByTeamId(Integer teamId);
    People queryPeopleByName(String peopleName);

    int insertPeople(People people);
    int updatePeople(People people);
    int deletePeople(People people);
}
