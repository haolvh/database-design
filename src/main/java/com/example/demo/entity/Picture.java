package com.example.demo.entity;

public class Picture {
    private int pictureId;
    private int DId;
    private int peopleId;
    private String pictureUrl;
    private String picturePosition;

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int getDId() {
        return DId;
    }

    public void setDId(int DId) {
        this.DId = DId;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPicturePosition() {
        return picturePosition;
    }

    public void setPicturePosition(String picturePosition) {
        this.picturePosition = picturePosition;
    }
}
