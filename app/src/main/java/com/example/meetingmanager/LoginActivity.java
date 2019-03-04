package com.example.meetingmanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.meetingmanager.gson.Login;
import com.example.meetingmanager.util.HttpUtil;
import com.example.meetingmanager.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity
{

    private TransitionView mAnimView;
    Button faceLogin ;
    public static String LOGIN_SUCCESS_TOKEN = null;  //全局使用的token

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }


        mAnimView = findViewById(R.id.ani_view);
        faceLogin = (Button)findViewById(R.id.btn_registered_facelogin);
        faceLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {    //相应人脸识别按键
                requestLogin();
                Intent intent = new Intent(LoginActivity.this,TestActivity.class); //人脸模块
                //startActivity(intent);
            }
        });

        mAnimView.setOnAnimationEndListener(new TransitionView.OnAnimationEndListener()
        {
            @Override
            public void onEnd()
            {
                //跳转到主页面
                gotoMainActivity();
            }
        });
    }

    private void gotoMainActivity()
    {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void singUp(View view)  //相应登录按键
    {
        mAnimView.startAnimation();
    }
    public void requestLogin(){
        String loginUrl = "http://10.0.2.2/test.json";
        HttpUtil.sendOkHttpRequest(loginUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this,"获取登录信息失败1",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                Log.d("222",responseText);
                //Toast.makeText(LoginActivity.this,responseText,Toast.LENGTH_LONG).show();
                final Login login = Utility.handleLoginResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(login != null && 200 == login.apiStatus){
                            LOGIN_SUCCESS_TOKEN = login.result.token;   //全局使用的token
                            Toast.makeText(LoginActivity.this,login.result.token,Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(LoginActivity.this,"获取登录信息失败2",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}

