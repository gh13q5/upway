package com.mobile.upway.dto;

public class Vegetable {

    public String vegetable;
    public String vegetablePrice;
    public String vegetableKcal;
    public String vegetableImgUrl;

    public Vegetable(){}

    public Vegetable(String cheese, String cheesePrice, String cheeseKcal, String cheeseImgUrl){
        this.vegetable = vegetable;
        this.vegetablePrice = vegetablePrice;
        this.vegetableKcal = vegetableKcal;
        this.vegetableImgUrl = vegetableImgUrl;
    }

    public String getVegetable(){
        return vegetable;
    }

    public void setVegetable(){
        this.vegetable = vegetable;
    }

    public String getVegetablePrice(){
        return vegetablePrice;
    }

    public void setVegetablePrice(){
        this.vegetablePrice = vegetablePrice;
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
