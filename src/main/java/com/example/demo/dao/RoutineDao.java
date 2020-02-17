package com.example.demo.dao;

import com.example.demo.entity.Routine;

import java.util.List;

public interface RoutineDao {
    List<Routine> queryRoutine();
    List<Integer> queryDId();

    /*全部为模糊查找*/
    List<Routine> queryRoutineByTitle(String routineTitle);
    List<Routine> queryRoutineByContent(String routineContent);

    int insertRoutine(Routine routine);
    int updateRoutine(Routine routine);
    int deleteRoutine(Routine routine);
}
