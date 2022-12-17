package com.mobile.upway.dto;

public class Options {

    public String name;
    public int price;
    public int kcal;
    public String imgUrl;

    public Options(){}

    public Options(String name, int price, int kcal, String imgUrl) {
        this.name = name;
        this.price = price;
        this.kcal = kcal;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
