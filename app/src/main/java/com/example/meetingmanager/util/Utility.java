package com.example.meetingmanager.util;

import com.example.meetingmanager.gson.Login;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utility {
    //解析处理服务器返回的简单的JSON格式数据

    /**
     * 将返回的json数据解析成为Login实体类
     * @param response
     * @return
     */
    public static Login handleLoginResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("Result");
            String loginContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(loginContent,Login.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
