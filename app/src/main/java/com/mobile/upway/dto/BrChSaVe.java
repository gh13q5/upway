package com.mobile.upway.dto;

public class BrChSaVe {

    public String name;
    public int kcal;
    public String imgUrl;

    public BrChSaVe(){}

    public BrChSaVe(String name, int kcal, String imgUrl){
        this.name = name;
        this.kcal = kcal;
        this.imgUrl = imgUrl;
    }

    public String getName(){
        return name;
    }

    public void setName(String s){
        this.name = name;
    }

    public int getKcal(){
        return kcal;
    }

    public void setKcal(String s){
        this.kcal = kcal;
    }

    public String getImgUrl(){
        return imgUrl;
    }

    public void setImgUrl() {
        this.imgUrl = imgUrl;
    }

}
