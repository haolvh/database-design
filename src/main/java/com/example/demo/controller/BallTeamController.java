package com.example.demo.controller;

import com.example.demo.entity.BallTeam;
import com.example.demo.service.BallTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/team")
public class BallTeamController {

    @Autowired
    BallTeamService ballTeamService;

    Map<String,Object> modelMap = new HashMap<String, Object>();

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> teamList(){
        modelMap.clear();
        List<BallTeam> list = ballTeamService.queryAllTeam();
        modelMap.put("data",list);
        return modelMap;
    }

    @RequestMapping("/manager")
    @ResponseBody
    public Map<String,Object> teamManager(@RequestBody Map<String,Object> data){
        modelMap.clear();
        String teamName = (String) data.get("teamName");
        Map<String,Object> map = ballTeamService.queryTeamManager(teamName);
        modelMap.put("error",0);
        modelMap.put("data",map);
        return modelMap;
    }

    @RequestMapping("/activity")
    @ResponseBody
    public Map<String,Object> teamActivity(@RequestBody Map<String,Object> data){
        modelMap.clear();
        String teamName = (String) data.get("teamName");
        Map<String,Object> map = ballTeamService.queryTeamActivity(teamName);
        modelMap.put("error",0);
        modelMap.put("data",map);
        return modelMap;
    }

    @RequestMapping("/insertBallTeam")
    @ResponseBody
    public Map<String,Object> insertBallTeam(@RequestParam("teamName") String teamName,
                                             @RequestParam("teamIntroduce") String teamIntroduce,
                                             @RequestParam("managerName") String managerName,
                                             @RequestParam("managerBirthday") String managerBirthday,
                                             @RequestParam("managerBirthplace") String managerBirthplace,
                                             @RequestParam("managerIntroduce") String managerIntroduce,
                                             @RequestParam("managerQQ") String managerQQ,
                                             @RequestParam("managerImage") MultipartFile managerImage){
        modelMap.clear();
        System.out.println(teamName+" "+teamIntroduce+" "+
                managerName+" "+managerBirthday+" "+
                managerBirthday+" "+managerBirthplace+" "+
                managerQQ+" "+managerImage.getOriginalFilename());
        List<String> list = ballTeamService.insertBallTeam(teamName,teamIntroduce,managerName,managerBirthday,managerBirthplace,managerIntroduce,managerQQ,managerImage);
        if(list.size()>1)
            modelMap.put("error",0);
        else
            modelMap.put("error",-1);
        modelMap.put("msg",list);
        return modelMap;
    }

    @RequestMapping("/updateBallTeam")
    @ResponseBody
    public Map<String,Object> updateBallTeam(@RequestParam("teamName") String teamName,
                                             @RequestParam("teamIntroduce") String teamIntroduce,
                                             @RequestParam("managerName") String managerName,
                                             @RequestParam("managerBirthday") String managerBirthday,
                                             @RequestParam("managerBirthplace") String managerBirthplace,
                                             @RequestParam("managerIntroduce") String managerIntroduce,
                                             @RequestParam("managerQQ") String managerQQ,
                                             @RequestParam("managerImage") MultipartFile managerImage,
                                             @RequestParam("teamId") Integer teamId){
        modelMap.clear();
        List<String> list = ballTeamService.updateBallTeam(teamName,teamIntroduce,managerName,managerBirthday,managerBirthplace,managerIntroduce,managerQQ,managerImage,teamId);
        if(list.size()>=1)
            modelMap.put("error",0);
        else
            modelMap.put("error",-1);
        modelMap.put("msg",list);
        return modelMap;
    }

    @RequestMapping("/insertActivity")
    @ResponseBody
    public Map<String,Object> insertActivity(@RequestParam("teamName") String teamName,
                                             @RequestParam("teamIntroduce") String teamIntroduce,
                                             @RequestParam("activityTime") String activityTime,
                                             @RequestParam("activityPlace") String activityPlace,
                                             @RequestParam("activityPicture") MultipartFile[] activityPicture){
        modelMap.clear();
        List<String> list = ballTeamService.insertActivity(teamName,teamIntroduce,activityTime,activityPlace,activityPicture);
        if(list.size()>1)
            modelMap.put("error",0);
        else
            modelMap.put("error",-1);
        modelMap.put("msg",list);
        return modelMap;
    }

    @RequestMapping("/deleteActivity")
    @ResponseBody
    public Map<String,Object> deleteActivity(@RequestBody Map<String,Object> data){
        modelMap.clear();
        Integer teamId = (Integer) data.get("teamId");
        List<String> list = ballTeamService.deleteTeam(teamId);
        if(list.size()>1)
            modelMap.put("error",0);
        else
            modelMap.put("error",-1);
        modelMap.put("msg",list);
        return modelMap;
    }
}
