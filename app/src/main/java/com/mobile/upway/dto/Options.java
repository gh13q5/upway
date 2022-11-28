package com.mobile.upway.dto;

public class Options {

    public String options;
    public String optionsPrice;
    public String optionsKcal;
    public String optionsImgUrl;

    public Options(){}

    public Options(String options, String optionsPrice, String optionsKcal, String optionsImgUrl){
        this.options = options;
        this.optionsPrice = optionsPrice;
        this.optionsKcal = optionsKcal;
        this.optionsImgUrl = optionsImgUrl;
    }

    public String getOptions(){
        return options;
    }

    public void setOptions(){
        this.options = options;
    }

    public String getOptionsPrice(){
        return optionsPrice;
    }

    public void setOptionsPrice(){
        this.optionsPrice = optionsPrice;
    }

    public String getOptionsKcal(){
        return optionsKcal;
    }

    public void setOptionsKcal(){
        this.optionsKcal = optionsKcal;
    }

    public String getOptionsImgUrl(){
        return optionsImgUrl;
    }

    public void setOptionsImgUrl() {
        this.optionsImgUrl = optionsImgUrl;
    }

}
