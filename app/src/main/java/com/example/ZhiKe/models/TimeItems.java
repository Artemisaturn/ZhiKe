package com.example.ZhiKe.models;

public class TimeItems {
    private String number;
    private String timeShow;
    private int timePicture;

    public TimeItems(String number,String timeShow,int timePicture){
        this.number = number;
        this.timeShow = timeShow;
        this.timePicture = timePicture;
    }

    public String getNumber(){
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getTimeShow(){
        return timeShow;
    }
    public void setTimeShow(String timeShow) {
        this.timeShow = timeShow;
    }

    public int getTimePicture(){
        return timePicture;
    }
    public void setTimePicture(int timePicture) {
        this.timePicture = timePicture;
    }
}
