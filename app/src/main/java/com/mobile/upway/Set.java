package com.mobile.upway;

public class Set {
    public int id;
    public int userId;
    public String content;

    public Set(){

    }
    public Set(int id, int userId, String content){
        this.id = id;
        this.userId = userId;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
