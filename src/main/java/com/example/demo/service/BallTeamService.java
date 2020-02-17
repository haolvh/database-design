package com.example.demo.service;

import com.example.demo.entity.BallTeam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface BallTeamService {
    List<BallTeam> queryAllTeam();
    Map<String,Object> queryTeamManager(String teamName);
    Map<String,Object> queryTeamActivity(String teamName);

    List<String> insertBallTeam(String teamName,
                                      String teamIntroduce,
                                      String managerName,
                                      String managerBirthday,
                                      String managerBirthplace,
                                      String managerIntroduce,
                                      String managerQQ,
                                      MultipartFile managerImage);
    List<String> updateBallTeam(String teamName,
                                String teamIntroduce,
                                String managerName,
                                String managerBirthday,
                                String managerBirthplace,
                                String managerIntroduce,
                                String managerQQ,
                                MultipartFile managerImage,
                                Integer teamId);

    List<String> insertActivity(String teamName,
                                String teamIntroduce,
                                String activityTime,
                                String activityPlace,
                                MultipartFile[] activityPicture);

    List<String> deleteTeam(Integer teamId);
}
