package com.example.meetingmanager;
import java.io.Serializable;
import java.util.Date;

public class MeetingInfo implements Serializable{
    String title; // 会议主题
    String description; //会议内容介绍
    String sponsor; // 会议发起人用户名
    String[] participator;  //参会人用户名数组
    Date startTime;  //会议开始时间
    int id;  //使用会议室id
    int duration;  //会议持续时间duration * 30min
    int cancelled;  //是否取消
    int end; //是否提前结束
    Date createTime;//会议预定时间
    Date endTime;//会议提前结束时间

    public MeetingInfo( int id,String title, String sponsor,Date startTime,String description ) {
        this.title = title;
        this.description = description;
        this.sponsor = sponsor;
        this.startTime = startTime;
        this.id = id;
    }

    public MeetingInfo(String title, String description, String sponsor, String[] participator,
                       Date startTime, int id, int duration, int cancelled, int end, Date createTime, Date endTime) {
        this.title = title;
        this.description = description;
        this.sponsor = sponsor;
        this.participator = participator;
        this.startTime = startTime;
        this.id = id;
        this.duration = duration;
        this.cancelled = cancelled;
        this.end = end;
        this.createTime = createTime;
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String[] getParticipator() {
        return participator;
    }

    public void setParticipator(String[] participator) {
        this.participator = participator;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCancelled() {
        return cancelled;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
