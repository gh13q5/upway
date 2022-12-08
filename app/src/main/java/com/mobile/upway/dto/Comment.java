package com.mobile.upway.dto;

public class Comment {
    public String combTitle;
    public String userId;
    public String content;

    public Comment(){

    }

    public Comment(String combTitle, String userId,String content){
        this.combTitle = combTitle;
        this.userId = userId;
        this.content = content;
    }

    public String getCombTitle() {
        return combTitle;
    }

    public void setCombTitle(String combTitle) {
        this.combTitle = combTitle;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
