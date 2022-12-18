package com.mobile.upway.dto;

import java.io.Serializable;

public class Cheese implements Serializable {

    public String name;
    public int kcal;
    public String imgUrl;

    public Cheese() {
    }

    public Cheese(String name, int kcal) {
        this.name = name;
        this.kcal = kcal;
    }

    public Cheese(String name, int kcal, String imgUrl) {
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
