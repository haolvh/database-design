package com.example.demo.dao;

import com.example.demo.entity.People;
import com.example.demo.util.ListUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleDaoTest {

    @Autowired
    PeopleDao peopleDao;

    @Test
    public void queryAllPeople() {
        List<People> list = peopleDao.queryAllPeople();
        System.out.println(list.size());
    }

    @Test
    public void queryPeopleByDId() {
        List<People> list = peopleDao.queryPeopleByDId(1);
        System.out.println(list.size());
    }

    @Test
    public void queryPeopleById() {
        People people = peopleDao.queryPeopleById(2);
        System.out.println(people.getPeopleName());
    }

    @Test
    public void queryPeopleByTeamId() {
        People people = peopleDao.queryPeopleByTeamId(3);
        System.out.println(people.getPeopleName());
    }

    @Test
    public void insertPeople() {
        People people = new People();
        people.setDId(1);
        people.setTeamId(3);
        people.setPeopleName("lvhao");
        int sign = peopleDao.insertPeople(people);
        System.out.println(sign);
    }

    @Test
    public void updatePeople() {
        People people = new People();
        people.setPeopleId(4);
        people.setDId(1);
        people.setTeamId(3);
        people.setPeopleName("adsasdsad");
        int sign = peopleDao.updatePeople(people);
        System.out.println(sign);
    }

    @Test
    public void deletePeople() {
        People people = new People();
        people.setPeopleId(4);
        int sign = peopleDao.deletePeople(people);
        System.out.println(sign);
    }

    @Test
    public void queryDId() {
        List<Integer> list = peopleDao.queryDId();
        list = ListUtil.removeDuplicate(list);
        System.out.println(list);
    }

    @Test
    public void queryTeamId() {
        List<Integer> list = peopleDao.queryTeamId();
        list = ListUtil.removeDuplicate(list);
        System.out.println(list);
    }
}
