package com.example.meetingmanager.util;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 与服务器进行交互，发起一条HTTP请求只调用该方法，传入请求地址并注册一个回调来处理服务器响应
     * @param address
     * @param callback
     */
    public static void sendOkHttpRequest(String address,String json,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON,json);
        Request request = new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }

}
