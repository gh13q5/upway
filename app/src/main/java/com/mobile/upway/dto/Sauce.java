package com.mobile.upway.dto;

import java.io.Serializable;

public class Sauce implements Serializable {

    public String name;
    public int kcal;
    public String imgUrl;

    public Sauce(){}

    public Sauce(String name, int kcal, String imgUrl) {
        this.name = name;
        this.kcal = kcal;
        this.imgUrl = imgUrl;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getKcal(){
        return kcal;
    }

    public void setKcal(int kcal){
        this.kcal = kcal;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

