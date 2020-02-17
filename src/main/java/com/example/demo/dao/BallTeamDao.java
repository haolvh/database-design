package com.example.demo.dao;

import com.example.demo.entity.BallTeam;

import java.util.List;

public interface BallTeamDao {
    List<BallTeam> queryBallTeam();
    List<BallTeam> queryAllBallTeam(String ballTeamName);
    BallTeam queryBallTeamById(Integer ballTeamId);
    BallTeam queryBallTeamByName(String ballTeamName);
    int insertBallTeam(BallTeam ballTeam);
    int updateBallTeam(BallTeam ballTeam);
    int deleteBallTeam(BallTeam ballTeam);
}
