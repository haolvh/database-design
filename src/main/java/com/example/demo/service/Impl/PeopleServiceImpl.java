package com.example.demo.service.Impl;

import com.example.demo.dao.BallTeamDao;
import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.PeopleDao;
import com.example.demo.dao.PictureDao;
import com.example.demo.entity.BallTeam;
import com.example.demo.entity.Department;
import com.example.demo.entity.People;
import com.example.demo.entity.Picture;
import com.example.demo.service.PeopleService;
import com.example.demo.util.PictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    PeopleDao peopleDao;
    @Autowired
    PictureDao pictureDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    BallTeamDao ballTeamDao;
    @Autowired
    PictureUtil pictureUtil;

    private boolean canInsert(String peopleName){
        int insertSign = 1;
        List<People> list = peopleDao.queryAllPeople();
        for(int i = 0;i < list.size();i++){
            if(list.get(i).getPeopleName().equals(peopleName)){
                insertSign = 0;
                break;
            }
        }
        if(insertSign==1){
            return true;
        }else
            return false;
    }

    @Override
    public List<People> queryDepartmentPeople() {
        List<People> list = peopleDao.queryPeopleByTask(25);
        System.out.println(list.size());
        for(int i = 0;i < list.size();i++){
            list.get(i).setPeopleImage(pictureDao.queryPictureByPeopleId(list.get(i).getPeopleId()).getPictureUrl());
        }
        return list;
    }

    @Override
    public List<String> insertPeople(String peopleName, String peopleTask, String peopleBirthday, String peopleBirthplace, String peopleIntroduce, String peopleQQ, MultipartFile peopleImage) {
        List<String> msgList = new ArrayList<>();
        if(canInsert(peopleName)){
            Integer DId = departmentDao.queryDepartmentByName("信息体育部").getDId();
            if(DId==null){
                Department department = new Department();
                department.setDName("信息体育部");
                departmentDao.insertDepartment(department);
                DId = departmentDao.queryDepartmentByName("信息体育部").getDId();
            }
            Integer teamId = ballTeamDao.queryBallTeamByName("体育部成员").getTeamId();
            if(teamId==null){
                BallTeam ballTeam = new BallTeam();
                ballTeam.setTeamName("体育部成员");
                ballTeam.setTeamIntroduce("13人");
                ballTeamDao.insertBallTeam(ballTeam);
                teamId = ballTeamDao.queryBallTeamByName("体育部成员").getTeamId();
            }
            People people = new People();
            people.setDId(DId);
            people.setTeamId(teamId);
            people.setPeopleName(peopleName);
            people.setPeopleBirthday(peopleBirthday);
            people.setPeopleBirthplace(peopleBirthplace);
            people.setPeopleTask(peopleTask);
            people.setPeopleIntroduce(peopleIntroduce);
            people.setPeopleQQ(peopleQQ);
            peopleDao.insertPeople(people);
            Integer peopleId = peopleDao.queryPeopleByName(peopleName).getPeopleId();
            //System.out.println(peopleId);
            Picture picture = pictureUtil.downloadPicture(peopleImage,"department",DId,peopleId);
            pictureDao.insertPicture(picture);

            People people1 = peopleDao.queryPeopleById(peopleId);
            people1.setPeopleImage(pictureDao.queryPictureByPeopleId(peopleId).getPictureUrl());
            msgList.add(peopleName+"successfully insert!");
        }else
            msgList.add(peopleName+"insert Error!");
        return msgList;
    }

    @Override
    public List<String> updatePeople(String peopleName, String peopleTask, String peopleBirthday, String peopleBirthplace, String peopleIntroduce, String peopleQQ, MultipartFile peopleImage, Integer peopleId) {
        List<String> msgList = new ArrayList<>();
        if(!(peopleName == null)&&!peopleName.equals("")){
            People people = peopleDao.queryPeopleById(peopleId);
            if(!peopleName.equals(""))
                people.setPeopleName(peopleName);
            if(!peopleTask.equals(""))
                people.setPeopleTask(peopleTask);
            if(!peopleBirthday.equals(""))
                people.setPeopleBirthday(peopleBirthday);
            if(!peopleBirthplace.equals(""))
                people.setPeopleBirthplace(peopleBirthplace);
            if(!peopleIntroduce.equals(""))
                people.setPeopleIntroduce(peopleIntroduce);
            if(!peopleQQ.equals(""))
                people.setPeopleQQ(peopleQQ);
            peopleDao.updatePeople(people);
            Picture picture = pictureDao.queryPictureByPeopleId(peopleId);
            System.out.println(peopleImage);
            if(!peopleImage.isEmpty()){
                pictureUtil.deletePicture(picture);
                pictureDao.deletePicture(picture);
                Picture picture1 = pictureUtil.downloadPicture(peopleImage,"department",departmentDao.queryDepartmentByName("信息体育部").getDId(),peopleId);
                pictureDao.insertPicture(picture1);
            }else
                System.out.println("dfasdasd");
            msgList.add("update successfully!");
        }else
            msgList.add("update Error1");
        return msgList;
    }

    @Override
    public List<String> deletePeople(Integer peopleId) {
        Picture picture = pictureDao.queryPictureByPeopleId(peopleId);
        pictureUtil.deletePicture(picture);
        pictureDao.deletePicture(picture);
        peopleDao.deletePeople(peopleDao.queryPeopleById(peopleId));
        List<String> msgList = new ArrayList<>();
        msgList.add("deletion successfully!");
        return msgList;
    }
}
