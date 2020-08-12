package com.example.ZhiKe.models;

public class RoomItems {
    private String position;
    private String roomName;
    private int roomPicture;

    public RoomItems(String position,String roomName,int roomPicture){
        this.position = position;
        this.roomName = roomName;
        this.roomPicture = roomPicture;
    }

    public String getPosition(){
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public String getRoomName(){
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomPicture(){
        return roomPicture;
    }
    public void setRoomPicture(int roomPicture) {
        this.roomPicture = roomPicture;
    }
}
