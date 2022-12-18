package com.mobile.upway.dto;

import java.io.Serializable;

public class Menu implements Serializable {
    public String name;
    public int kcal;
    public int price;
    public String imgUrl;

    public Menu(){}

    public Menu(String name, int kcal, int price, String imgUrl) {
        this.name = name;
        this.kcal = kcal;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgUrl(){
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
