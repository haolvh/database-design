package com.example.demo.dao;

import com.example.demo.entity.Picture;

import java.util.List;

public interface PictureDao {
    List<Picture> queryPictures();
    List<Picture> queryPictureById(Integer pictureId);
    List<String> queryPictureByTeamId(Integer peopleId);
    List<Picture> queryPicturesByPeopleId(Integer peopleId);
    List<Picture> queryPictureByDId(Integer DId);
    List<Integer> queryDId();
    List<Integer> queryPeopleId();
    Picture queryPictureByPeopleId(Integer peopleId);

    int insertPicture(Picture picture);
    int updatePicture(Picture picture);
    int deletePicture(Picture picture);
}
