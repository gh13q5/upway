package com.mobile.upway.dto;

public class Bread {

    public String bread;
    public String breadKcal;
    public String breadImgUrl;

    public Bread(){}

    public Bread(String bread, String breadKcal, String breadImgUrl){
        this.bread = bread;
        this.breadKcal = breadKcal;
        this.breadImgUrl = breadImgUrl;
    }

    public String getBread(){
        return bread;
    }

    public void setBread(String s){
        this.bread = bread;
    }

    public String getBreadKcal(){
        return breadKcal;
    }

    public void setBreadKcal(String s){
        this.breadKcal = breadKcal;
    }

    public String getBreadImgUrl(){
        return breadImgUrl;
    }

    public void setBreadImgUrl() {
        this.breadImgUrl = breadImgUrl;
    }

}
