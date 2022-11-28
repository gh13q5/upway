package com.mobile.upway.dto;

public class Vegetable {

    public String vegetable;
    public String vegetableKcal;
    public String vegetableImgUrl;

    public Vegetable(){}

    public Vegetable(String vegetable, String vegetableKcal, String vegetableImgUrl){
        this.vegetable = vegetable;
        this.vegetableKcal = vegetableKcal;
        this.vegetableImgUrl = vegetableImgUrl;
    }

    public String getVegetable(){
        return vegetable;
    }

    public void setVegetable(){
        this.vegetable = vegetable;
    }

    public String getVegetableKcal(){
        return vegetableKcal;
    }

    public void setVegetableKcal(){
        this.vegetableKcal = vegetableKcal;
    }

    public String getVegetableImgUrl(){
        return vegetableImgUrl;
    }

    public void setVegetableImgUrl() {
        this.vegetableImgUrl = vegetableImgUrl;
    }
}
