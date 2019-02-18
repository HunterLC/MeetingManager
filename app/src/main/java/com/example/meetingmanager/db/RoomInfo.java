package com.example.meetingmanager.db;

import org.litepal.crud.DataSupport;

public class RoomInfo extends DataSupport {
    private int meetingRoom_id;   //请求会议室id
    private int id;              //回应会议室id
    private int capacity;        //会议室容量
    private boolean enable;      //是否可用
    private String name;        //会议室名称

    public int getMeetingRoom_id() {
        return meetingRoom_id;
    }

    public void setMeetingRoom_id(int meetingRoom_id) {
        this.meetingRoom_id = meetingRoom_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
