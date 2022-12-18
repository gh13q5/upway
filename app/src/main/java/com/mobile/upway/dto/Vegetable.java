package com.mobile.upway.dto;

import java.io.Serializable;

public class Vegetable implements Serializable {

    public String name;
    public double kcal;
    public String imgUrl;

    public Vegetable() {
    }

    public Vegetable(String name, double kcal, String imgUrl) {
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

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
