package com.mobile.upway.dto;

import java.util.List;

public class Combination {
    public String title;
    public String description;
    public int scraps;
    public int kcal;
    public int price;
    public String user;
    public String menu;
    public String bread;
    public String cheese;
    public List<String> vegetableList;
    public List<String> sauceList;
    public List<String> optionsList;

    public Combination() {
    }

    public Combination(String title, String description, int scraps, int kcal, int price, String user, String menu, String bread, String cheese, List<String> vegetableList, List<String> sauceList, List<String> optionsList) {
        this.title = title;
        this.description = description;
        this.scraps = scraps;
        this.kcal = kcal;
        this.price = price;
        this.user = user;
        this.menu = menu;
        this.bread = bread;
        this.cheese = cheese;
        this.vegetableList = vegetableList;
        this.sauceList = sauceList;
        this.optionsList = optionsList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScraps() {
        return scraps;
    }

    public void setScraps(int scraps) {
        this.scraps = scraps;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public List<String> getVegetableList() {
        return vegetableList;
    }

    public void setVegetableList(List<String> vegetableList) {
        this.vegetableList = vegetableList;
    }

    public List<String> getSauceList() {
        return sauceList;
    }

    public void setSauceList(List<String> sauceList) {
        this.sauceList = sauceList;
    }

    public List<String> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<String> optionsList) {
        this.optionsList = optionsList;
    }
}
