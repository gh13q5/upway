package com.mobile.upway;

public class Combination {
    public int combId;
    public String title;
    public String description;
    public int scraps;
    public int kcal;
    public int price;

    public Combination() {
    }

    public Combination(int combId, String title, String description, int scraps, int kcal, int price) {
        this.combId = combId;
        this.title = title;
        this.description = description;
        this.scraps = scraps;
        this.kcal = kcal;
        this.price = price;
    }

    public int getCombId() {
        return combId;
    }

    public void setCombId(int combId) {
        this.combId = combId;
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
}
