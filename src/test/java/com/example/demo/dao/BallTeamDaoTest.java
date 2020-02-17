package com.example.demo.dao;

import com.example.demo.entity.BallTeam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BallTeamDaoTest {

    @Autowired
    BallTeamDao ballTeamDao;

    @Test
    public void queryBallTeam() {
        List<BallTeam> list = ballTeamDao.queryBallTeam();
        System.out.println(list.get(1).getTeamIntroduce());
    }


    @Test
    public void queryBallTeamByName() {
        BallTeam ballTeam = ballTeamDao.queryBallTeamByName("总产值");
        System.out.println(ballTeam.getTeamId()+"  "+ballTeam.getTeamName()+"  "+ballTeam.getTeamIntroduce());
    }

    @Test
    public void queryBallTeamById() {
        BallTeam ballTeam = ballTeamDao.queryBallTeamById(1);
        System.out.println(ballTeam.getTeamId()+"  "+ballTeam.getTeamName()+"  "+ballTeam.getTeamIntroduce());
    }

    @Test
    public void insertBallTeam() {
        BallTeam ballTeam = new BallTeam();
        ballTeam.setTeamName("5555");
        ballTeam.setTeamIntroduce("5555555");
        int sign = ballTeamDao.insertBallTeam(ballTeam);
        System.out.println(sign);
    }

    @Test
    public void deleteBallTeam() {
        BallTeam ballTeam = new BallTeam();
        ballTeam.setTeamId(1);
        int sign = ballTeamDao.deleteBallTeam(ballTeam);
        System.out.println(sign);
    }

    @Test
    public void updateBallTeam() {
        BallTeam ballTeam = new BallTeam();
        ballTeam.setTeamId(2);
        ballTeam.setTeamName("66");
        ballTeam.setTeamIntroduce("666");
        int sign = ballTeamDao.updateBallTeam(ballTeam);
        System.out.println(sign);
    }
}
