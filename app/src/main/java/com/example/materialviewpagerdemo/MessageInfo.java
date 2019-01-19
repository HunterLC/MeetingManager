package com.example.materialviewpagerdemo;

import   java.text.SimpleDateFormat;
import java.util.Date;

public class MessageInfo {//消息信息
    private  String  username;//用户名

    public MessageInfo(String title, String content, Date curdate) {
        this.title = title;
        this.content = content;
        this.curdate = curdate;
    }

    private  int     message_id;//消息id
    private  String  title;//消息标题
    private boolean  readed;//是否已读
    private String  content;//消息内容
    private SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
    private Date curdate;//当前时间

    public MessageInfo(String username, int message_id, String title, boolean readed, String content, Date curdate) {
        this.username = username;
        this.message_id = message_id;
        this.title = title;
        this.readed = readed;
        this.content = content;
        this.curdate = curdate;
    }

    String getCurrentTime(){
        curdate =  new Date(System.currentTimeMillis());
        return String.valueOf(curdate);

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}


