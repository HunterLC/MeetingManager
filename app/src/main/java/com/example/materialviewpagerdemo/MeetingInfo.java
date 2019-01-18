package com.example.materialviewpagerdemo;
import java.io.Serializable;
import java.util.Date;

public class MeetingInfo implements Serializable{
    int img; //picture
    String title; // meeting's name
    String speaker; // speaker's name
    String time;
    String location;

    public MeetingInfo(int img, String title, String speaker, String time, String location){
        this.img = img;
        this.title = title;
        this.speaker = speaker;
        this.time = time;
        this.location = location;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
