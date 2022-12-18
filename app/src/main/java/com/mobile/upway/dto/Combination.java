package com.mobile.upway.dto;

import java.io.Serializable;
import java.util.List;

public class Combination implements Serializable {
    public String id;
    public String imgUrl;
    public String title;
    public String description;
    public int scraps;
    public int kcal;
    public int price;
    public User user;
    public Menu menu;
    public Bread bread;
    public Cheese cheese;
    public List<Vegetable> vegetableList;
    public List<Sauce> sauceList;
    public List<Options> optionsList;

    public Combination() {
    }

    public Combination(String uid,String title, String description, int scraps, int kcal, int price, User user, Menu menu, Bread bread, Cheese cheese, List<Vegetable> vegetableList, List<Sauce> sauceList, List<Options> optionsList) {
        this.id = uid;
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

    public Combination(String uid, String imgUrl, String title, String description, int scraps, int kcal, int price, User user, Menu menu, Bread bread, Cheese cheese, List<Vegetable> vegetableList, List<Sauce> sauceList, List<Options> optionsList) {
        this.id = uid;
        this.imgUrl = imgUrl;
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

    public String getId() {
        return id;
    }

    public void setId(String uid) {
        this.id = uid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Bread getBread() {
        return bread;
    }

    public void setBread(Bread bread) {
        this.bread = bread;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    public List<Vegetable> getVegetableList() {
        return vegetableList;
    }

    public void setVegetableList(List<Vegetable> vegetableList) {
        this.vegetableList = vegetableList;
    }

    public List<Sauce> getSauceList() {
        return sauceList;
    }

    public void setSauceList(List<Sauce> sauceList) {
        this.sauceList = sauceList;
    }

    public List<Options> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<Options> optionsList) {
        this.optionsList = optionsList;
    }
}
