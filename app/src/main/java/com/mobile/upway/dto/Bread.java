package com.mobile.upway.dto;

public class Bread {

    public String name;
    public int kcal;
    public String imgUrl;


    public Bread() {
    }

    public Bread(String name, int kcal) {
        this.name = name;
        this.kcal = kcal;
    }

    public Bread(String name, int kcal, String imgUrl) {
        this.name = name;
        this.kcal = kcal;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
