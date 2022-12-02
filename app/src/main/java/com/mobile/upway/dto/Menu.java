package com.mobile.upway.dto;

public class Menu {
    String name;
    int kcal;
    int price;

    public Menu(){}

    public Menu(String name, int kcal, int price) {
        this.name = name;
        this.kcal = kcal;
        this.price = price;
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
}
