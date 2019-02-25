package com.example.meetingmanager.gson;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("status")
    public int apiStatus;

    @SerializedName("message")
    public String tips;

    public Result result;
    public class Result{
        @SerializedName("role")
        public String userRole;

        @SerializedName("token")
        public String Token;
    }
}
