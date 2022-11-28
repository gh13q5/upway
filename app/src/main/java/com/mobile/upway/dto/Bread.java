package com.mobile.upway.dto;

public class Bread {

    public String bread;
    public String breadPrice;
    public String breadKcal;
    public String breadImgUrl;
    public int breadLength;

    public Bread(){}

    public Bread(String bread, String breadPrice, String breadKcal, String breadImgUrl, int breadLength){
        this.bread = bread;
        this.breadPrice = breadPrice;
        this.breadKcal = breadKcal;
        this.breadImgUrl = breadImgUrl;
        this.breadLength = breadLength;
    }

    public String getBread(){
        return bread;
    }

    public void setBread(){
        this.bread = bread;
    }

    public String getBreadPrice(){
        return breadPrice;
    }

    public void setBreadPrice(){
        this.breadPrice = breadPrice;
    }

    public String getBreadKcal(){
        return breadKcal;
    }

    public void setBreadKcal(){
        this.breadKcal = breadKcal;
    }

    public String getBreadImgUrl(){
        return breadImgUrl;
    }

    public void setBreadImgUrl() {
        this.breadImgUrl = breadImgUrl;
    }

    public int getBreadLength() { return breadLength; }

    public void setBreadLength() { this.breadLength = breadLength; }

}
