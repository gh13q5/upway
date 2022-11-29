package com.mobile.upway.dto;

public class Menu {
    int id;
    String name;
    int kcal;
    int price;
    int size;

    public Menu(){}

    public Menu(int id, String name, int kcal, int price, int size) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.price = price;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
