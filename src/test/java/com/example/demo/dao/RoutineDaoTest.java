package com.example.demo.dao;

import com.example.demo.entity.Routine;
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
public class RoutineDaoTest {

    @Autowired
    RoutineDao routineDao;

    @Test
    public void queryRoutine() {
        List<Routine> list = routineDao.queryRoutine();
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i).getDId()+" "+
                    list.get(i).getRoutineId()+" "+
                    list.get(i).getRoutineTitle()+" "+
                    list.get(i).getRoutineContent()+" "+
                    list.get(i).getRoutineTime());
        }
    }

    @Test
    public void insertRoutine() {
        Routine routine = new Routine();
        routine.setDId(2);
        routine.setRoutineTitle("我们");
        routine.setRoutineContent("asdasdasdas");
        routine.setRoutineTime("2019-12-9");
        int sign = routineDao.insertRoutine(routine);
        System.out.println(sign);
    }

    @Test
    public void queryRoutineByTitle() {
        List<Routine> list = routineDao.queryRoutineByTitle("我");
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i).getRoutineTitle()+" "+
                    list.get(i).getRoutineId());
        }
    }

    @Test
    public void queryRoutineByContent() {
        List<Routine> list = routineDao.queryRoutineByContent("as");
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i).getRoutineTitle()+" "+
                    list.get(i).getRoutineId());
        }
    }

    @Test
    public void updateRoutine() {
        Routine routine = new Routine();
        routine.setRoutineId(4);
        routine.setDId(2);
        routine.setRoutineTitle("阿斯顿撒多撒");
        routine.setRoutineContent("绿化空间和空间");
        routine.setRoutineTime("2019-12-20");
        int sign = routineDao.updateRoutine(routine);
        System.out.println(sign);
    }

    @Test
    public void deleteRoutine() {
        Routine routine = new Routine();
        routine.setRoutineId(4);
        int sign = routineDao.deleteRoutine(routine);
        System.out.println(sign);
    }

    @Test
    public void queryDId() {
        List<Integer> list = routineDao.queryDId();
        list = ListUtil.removeDuplicate(list);
        System.out.println(list);
    }
}
