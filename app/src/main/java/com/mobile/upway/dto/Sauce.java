package com.mobile.upway.dto;

public class Sauce {

    public String sauce;
    public String sauceKcal;
    public String sauceImgUrl;

    public Sauce(){}

    public Sauce(String sauce, String sauceKcal, String sauceImgUrl){
        this.sauce = sauce;
        this.sauceKcal = sauceKcal;
        this.sauceImgUrl = sauceImgUrl;
    }

    public String getSauce(){
        return sauce;
    }

    public void setSauce(String s){
        this.sauce = sauce;
    }

    public String getSauceKcal(){
        return sauceKcal;
    }

    public void setSauceKcal(String s){
        this.sauceKcal = sauceKcal;
    }

    public String getSauceImgUrl(){
        return sauceImgUrl;
    }

    public void setSauceImgUrl() {
        this.sauceImgUrl = sauceImgUrl;
    }
}

