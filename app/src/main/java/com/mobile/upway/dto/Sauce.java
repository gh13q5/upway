package com.mobile.upway.dto;

public class Sauce {

    public String name;
    public int kcal;

    public Sauce(){}

    public Sauce(String sauce, int sauceKcal, String sauceImgUrl){
        this.name = name;
        this.kcal = kcal;
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
}

