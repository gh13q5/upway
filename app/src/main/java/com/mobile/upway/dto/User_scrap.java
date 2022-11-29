package com.mobile.upway.dto;

public class User_scrap {
    User user;
    Combination comb;

    public User_scrap(){ }

    public User_scrap(User user, Combination comb) {
        this.user = user;
        this.comb = comb;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Combination getComb() {
        return comb;
    }

    public void setComb(Combination comb) {
        this.comb = comb;
    }
}
