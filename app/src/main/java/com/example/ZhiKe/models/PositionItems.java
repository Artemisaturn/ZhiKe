package com.example.ZhiKe.models;

public class PositionItems {
    private String buildingName;
    private int buildingPicture;

    public PositionItems(String buildingName,int buildingPicture){
        this.buildingName = buildingName;
        this.buildingPicture = buildingPicture;
    }

    public String getBuildingName(){
        return buildingName;
    }
    public void setBuildingName(String buildingName){
        this.buildingName = buildingName;
    }

    public int getBuildingPicture(){
        return buildingPicture;
    }
    public void setBuildingPicture(int buildingPicture){
        this.buildingPicture = buildingPicture;
    }

}
