package com.example.demo.service.Impl;

import com.example.demo.dao.BallTeamDao;
import com.example.demo.dao.PeopleDao;
import com.example.demo.dao.PictureDao;
import com.example.demo.entity.BallTeam;
import com.example.demo.entity.People;
import com.example.demo.entity.Picture;
import com.example.demo.service.BallTeamService;
import com.example.demo.util.PictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BallTeamServiceImpl implements BallTeamService {

    @Autowired
    BallTeamDao ballTeamDao;
    @Autowired
    PeopleDao peopleDao;
    @Autowired
    PictureDao pictureDao;
    @Autowired
    PictureUtil pictureUtil;

    private boolean canInsert(String teamName){
        int canInsertSign = 1;
        List<BallTeam> list = ballTeamDao.queryBallTeam();
        for(int i = 0; i < list.size();i++){
            if(list.get(i).getTeamName().equals(teamName)){
                canInsertSign=0;
                break;
            }
        }
        if(canInsertSign==1)
            return true;
        else
            return false;
    }

    @Override
    public List<BallTeam> queryAllTeam() {
        return ballTeamDao.queryAllBallTeam("队");
    }

    @Override
    public Map<String, Object> queryTeamManager(String teamName) {
        Integer teamId = ballTeamDao.queryBallTeamByName(teamName).getTeamId();
        People people = peopleDao.queryPeopleByTeamId(teamId);
        Picture peopleImage = pictureDao.queryPictureByPeopleId(people.getPeopleId());
        Map<String,Object> map = new HashMap<>();
        map.put("manager",people);
        map.put("managerImage",peopleImage);
        return map;
    }

    @Override
    public Map<String, Object> queryTeamActivity(String teamName) {
        BallTeam team = ballTeamDao.queryBallTeamByName(teamName);
        Integer peopleId = peopleDao.queryPeopleByTeamId(team.getTeamId()).getPeopleId();
        List<String> activityUrls = pictureDao.queryPictureByTeamId(peopleId);
        Map<String,Object> map = new HashMap<>();
        map.put("team",team);
        map.put("activity",activityUrls);
        return map;
    }

    @Override
    public List<String> insertBallTeam(String teamName, String teamIntroduce, String managerName, String managerBirthday, String managerBirthplace, String managerIntroduce, String managerQQ, MultipartFile managerImage) {
        List<String> msgList = new ArrayList<>();
        if(canInsert(teamName)){
            BallTeam ballTeam = new BallTeam();
            ballTeam.setTeamName(teamName);
            ballTeam.setTeamIntroduce(teamIntroduce);
            int tInsert = ballTeamDao.insertBallTeam(ballTeam);
            if(tInsert==1) {
                msgList.add("team插入成功！");
                People people = new People();
                people.setDId(1);
                people.setTeamId(ballTeamDao.queryBallTeamByName(teamName).getTeamId());
                people.setPeopleTask(teamName+"队长");
                people.setPeopleName(managerName);
                people.setPeopleBirthday(managerBirthday);
                people.setPeopleBirthplace(managerBirthplace);
                people.setPeopleIntroduce(managerIntroduce);
                people.setPeopleQQ(managerQQ);
                int pInsert = peopleDao.insertPeople(people);
                if(pInsert==1){
                    msgList.add("people插入成功！");
                    Integer peopleId = peopleDao.queryPeopleByTeamId(ballTeamDao.queryBallTeamByName(teamName).getTeamId()).getPeopleId();
                    Picture picture = pictureUtil.downloadPicture(managerImage,"manager",1,peopleId);
                    int imageInsert = pictureDao.insertPicture(picture);
                    if(imageInsert==1)
                        msgList.add("头像插入成功！");
                    else
                        msgList.add("头像插入失败！");
                }
                else
                    msgList.add("people插入失败!");
            }
            else
                msgList.add("team插入失败！");
        }
        else
            msgList.add("插入失败!有重复！");
        return msgList;
    }

    @Override
    public List<String> updateBallTeam(String teamName, String teamIntroduce, String managerName, String managerBirthday, String managerBirthplace, String managerIntroduce, String managerQQ, MultipartFile managerImage, Integer teamId) {
        BallTeam ballTeam = ballTeamDao.queryBallTeamById(teamId);
        if(!teamName.equals(""))
            ballTeam.setTeamName(teamName);
        if(!teamIntroduce.equals(""))
            ballTeam.setTeamIntroduce(teamIntroduce);
        ballTeamDao.updateBallTeam(ballTeam);
        People people = peopleDao.queryPeopleByTeamId(teamId);
        people.setPeopleTask(teamName+"队长");
        if(!managerName.equals(""))
            people.setPeopleName(managerName);
        if(!managerBirthday.equals(""))
            people.setPeopleBirthday(managerBirthday);
        if(!managerBirthplace.equals(""))
            people.setPeopleBirthplace(managerBirthplace);
        if(!managerIntroduce.equals(""))
            people.setPeopleIntroduce(managerIntroduce);
        if(!managerQQ.equals(""))
            people.setPeopleQQ(managerQQ);
        peopleDao.updatePeople(people);
        Picture picture = pictureDao.queryPictureByPeopleId(people.getPeopleId());
        if(!managerImage.isEmpty()){
            pictureUtil.deletePicture(picture);
            pictureDao.deletePicture(picture);
            Picture picture1 = pictureUtil.downloadPicture(managerImage,"manager",1,people.getPeopleId());
            pictureDao.insertPicture(picture1);
        }
        List<String> msgList = new ArrayList<>();
        msgList.add("更新成功!");
        return msgList;
    }

    @Override
    public List<String> insertActivity(String teamName, String teamIntroduce, String activityTime, String activityPlace, MultipartFile[] activityPicture) {
        List<String> msgList = new ArrayList<>();
        if(canInsert(teamName)){
            BallTeam ballTeam = new BallTeam();
            ballTeam.setTeamName(teamName);
            ballTeam.setTeamIntroduce(teamIntroduce);
            int tInsert = ballTeamDao.insertBallTeam(ballTeam);
            if(tInsert==1) {
                msgList.add("team插入成功！");
                People people = new People();
                people.setDId(1);
                people.setTeamId(ballTeamDao.queryBallTeamByName(teamName).getTeamId());
                people.setPeopleTask(null);
                people.setPeopleName(teamName);
                people.setPeopleBirthday(activityTime);
                people.setPeopleBirthplace(activityPlace);
                people.setPeopleIntroduce(null);
                people.setPeopleQQ(null);
                int pInsert = peopleDao.insertPeople(people);
                if(pInsert==1){
                    msgList.add("people插入成功！");
                    Integer peopleId = peopleDao.queryPeopleByTeamId(ballTeamDao.queryBallTeamByName(teamName).getTeamId()).getPeopleId();
                    int activityPictureInsert = 0;
                    for(int i = 0;i < activityPicture.length;i++){
                        Picture picture = pictureUtil.downloadPicture(activityPicture[i],"activity",1,peopleId);
                        pictureDao.insertPicture(picture);
                        activityPictureInsert++;
                    }
                    if(activityPictureInsert==activityPicture.length)
                        msgList.add("图片插入成功！");
                    else
                        msgList.add("图片插入失败！");
                }else
                    msgList.add("people插入失败!");
            }
            else
                msgList.add("team插入失败！");
        }
        else
            msgList.add("插入失败!有重复！");
        return msgList;
    }

    @Override
    public List<String> deleteTeam(Integer teamId) {
        List<String> msgList = new ArrayList<>();
        Integer peopleId = peopleDao.queryPeopleByTeamId(teamId).getPeopleId();
        int pictureDeletionSign = 0;
        List<Picture> list = pictureDao.queryPicturesByPeopleId(peopleId);
        for(int i = 0;i < list.size();i++){
            pictureUtil.deletePicture(list.get(i));
            pictureDao.deletePicture(list.get(i));
            pictureDeletionSign++;
        }
        if(pictureDeletionSign==list.size()){
            msgList.add("picture deletion successfully!");
            if(peopleDao.deletePeople(peopleDao.queryPeopleByTeamId(teamId))==1){
                msgList.add("people deletion successfully!");
                if(ballTeamDao.deleteBallTeam(ballTeamDao.queryBallTeamById(teamId))==1){
                    msgList.add("team deletion successfully！");
                }else
                    msgList.add("team deletion Error！");
            }else
                msgList.add("people deletion Error!");
        }else
            msgList.add("picture deletion Error!");
        return msgList;
    }
}
