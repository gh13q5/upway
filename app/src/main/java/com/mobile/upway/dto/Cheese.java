package com.mobile.upway.dto;

public class Cheese {

    public String cheese;
    public String cheeseKcal;
    public String cheeseImgUrl;

    public Cheese(){}

    public Cheese(String cheese, String cheeseKcal, String cheeseImgUrl){
        this.cheese = cheese;
        this.cheeseKcal = cheeseKcal;
        this.cheeseImgUrl = cheeseImgUrl;
    }

    public String getCheese(){
        return cheese;
    }

    public void setCheese(String s){
        this.cheese = cheese;
    }

    public String getCheeseKcal(){
        return cheeseKcal;
    }

    public void setCheeseKcal(String s){
        this.cheeseKcal = cheeseKcal;
    }

    public String getCheeseImgUrl(){
        return cheeseImgUrl;
    }

    public void setCheeseImgUrl() {
        this.cheeseImgUrl = cheeseImgUrl;
    }
}
