package com.example.demo.dao;

import com.example.demo.entity.Picture;
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
public class PictureDaoTest {

    @Autowired
    PictureDao pictureDao;

    @Test
    public void queryPictures() {
        List<Picture> list = pictureDao.queryPictures();
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i).getPictureId()+" "+
                    list.get(i).getDId()+" "+
                    list.get(i).getPeopleId()+" "+
                    list.get(i).getPictureUrl()+" "+
                    list.get(i).getPicturePosition());
        }
    }

    @Test
    public void queryPictureById() {
        List<Picture> list = pictureDao.queryPictureById(1);
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i).getPictureId()+" "+
                    list.get(i).getDId()+" "+
                    list.get(i).getPeopleId()+" "+
                    list.get(i).getPictureUrl()+" "+
                    list.get(i).getPicturePosition());
        }
    }

    @Test
    public void queryPictureByDId() {
        List<Picture> list = pictureDao.queryPictureByDId(1);
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i).getPictureId()+" "+
                    list.get(i).getDId()+" "+
                    list.get(i).getPeopleId()+" "+
                    list.get(i).getPictureUrl()+" "+
                    list.get(i).getPicturePosition());
        }
    }

    @Test
    public void queryPictureByPeopleId() {
        Picture picture = pictureDao.queryPictureByPeopleId(2);
        System.out.println(picture.getPeopleId());
    }

    @Test
    public void insertPicture() {
        Picture picture = new Picture();
        picture.setDId(1);
        picture.setPeopleId(2);
        picture.setPictureUrl("Asdsadasd");
        picture.setPicturePosition("aasdasdasdasdsa");
        int sign = pictureDao.insertPicture(picture);
        System.out.println(sign);
    }

    @Test
    public void updatePicture() {
        Picture picture = new Picture();
        picture.setPictureId(2);
        picture.setDId(1);
        picture.setPeopleId(2);
        picture.setPictureUrl("A");
        picture.setPicturePosition("aasdasdasdasdsa");
        int sign = pictureDao.updatePicture(picture);
        System.out.println(sign);
    }

    @Test
    public void deletePicture() {
        Picture picture = new Picture();
        picture.setPictureId(2);
        int sign = pictureDao.deletePicture(picture);
        System.out.println(sign);
    }

    @Test
    public void queryDId() {
        List<Integer> list = pictureDao.queryDId();
        list = ListUtil.removeDuplicate(list);
        System.out.println(list);
    }

    @Test
    public void queryPeopleId() {
        List<Integer> list = pictureDao.queryPeopleId();
        list = ListUtil.removeDuplicate(list);
        System.out.println(list);
    }
}
