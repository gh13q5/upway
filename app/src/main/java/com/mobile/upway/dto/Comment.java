package com.mobile.upway.dto;

public class Comment {
    public String user;
    public String combination;
    public String content;

    public Comment(){

    }

    public Comment(String user, String combination, String content) {
        this.user = user;
        this.combination = combination;
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
