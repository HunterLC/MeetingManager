package com.example.meetingmanager.util;

import com.example.meetingmanager.gson.Login;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Utility {            //解析处理服务器返回的简单的JSON格式数据


    /**
     * 检查json中是否有BOM头，如果有则剔除utf-8的BOM头
     * @param in
     * @return
     */
    public static String JSONTokener(String in) {
        // consume an optional byte order mark (BOM) if it exists
        if (in != null && in.startsWith("\ufeff")) {    //剔除utf-8的BOM头
            in = in.substring(1);
        }
        return in;
    }

    /**
     * 将返回的json数据解析成为Login实体类
     * @param response
     * @return
     */
    public static Login handleLoginResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(JSONTokener(response));
            //JSONArray jsonArray = jsonObject.getJSONArray("Login");
            //String loginContent = jsonArray.getJSONObject(0).toString();
            String loginContent = jsonObject.toString();
            return new Gson().fromJson(loginContent,Login.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
