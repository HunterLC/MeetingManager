package com.example.meetingmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.meetingmanager.gson.Login;
import com.example.meetingmanager.util.HttpUtil;
import com.example.meetingmanager.util.Utility;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity
{

    private TransitionView mAnimView;
    SpinKitView spinKitView;
    Button faceLoginButton,loginButton;
    EditText userAccount,userPassword;
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
        SharedPreferences prefs = getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        if(prefs.getString("account",null)!=null && prefs.getString("password",null)!=null)
            gotoMainActivity();

        mAnimView = findViewById(R.id.ani_view);
        loginButton = (Button)findViewById(R.id.btn_registered_login);
        faceLoginButton = (Button)findViewById(R.id.btn_registered_facelogin);
        faceLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {    //相应人脸识别按键
               // Intent intent = new Intent(LoginActivity.this,TestActivity.class); //人脸模块
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
        requestLogin();
    }


    public String toJson(String username,String password,String face) {
        return "{\"username\":\"" + username + "\"," + "\"password\":\"" + password + "\","+"\"face\":\""+face+"\"}";
    }

    public void requestLogin(){
        userAccount = (EditText)findViewById(R.id.Ed_uerPhoneNumber);
        userPassword = (EditText)findViewById(R.id.Ed_uerPassword);
        final String account = userAccount.getText().toString();
        final String password = userPassword.getText().toString();
        spinKitView = (SpinKitView)findViewById(R.id.spin_kit);
        Sprite circle = new Circle();
        spinKitView.setIndeterminateDrawable(circle);
        spinKitView.setVisibility(View.VISIBLE);
        Toast.makeText(LoginActivity.this,"信息加载中...",Toast.LENGTH_SHORT).show();
        //网络请求
        String loginUrl = "http://47.107.251.255/user/logIn";
        String json = toJson(account,password,"asdasd");
        Log.d("LoginActivity  json's value ",json);
        HttpUtil.sendOkHttpRequest(loginUrl,json,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        spinKitView.setVisibility(View.INVISIBLE);
                        Toast.makeText(LoginActivity.this,"获取登录信息失败",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                Log.d("222",responseText);
                final Login login = Utility.handleLoginResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(login != null && 200 == login.apiStatus){   //登录成功
                            //SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit();
                            SharedPreferences loginSP = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);  //保存登录信息，只能被本应用所访问
                            loginSP.edit()              //记住密码自动登录
                                    .putString("account",account)
                                    .putString("password",password)
                                    .apply();
                            LOGIN_SUCCESS_TOKEN = login.result.token;   //全局使用的token
                            spinKitView.setVisibility(View.INVISIBLE);//关闭加载动画
                            faceLoginButton.setEnabled(false);//人脸识别键不可触碰
                            loginButton.setEnabled(false);//登陆键不可触碰
                            userAccount.setFocusable(false);//账号编辑框不可点击
                            userPassword.setFocusable(false);//密码框不可点击
                            mAnimView.startAnimation();   //登陆成功动画
                            Toast.makeText(LoginActivity.this,login.result.token,Toast.LENGTH_LONG).show();
                        } else{
                            spinKitView.setVisibility(View.INVISIBLE);//关闭加载动画
                            Toast.makeText(LoginActivity.this,"获取登录信息失败",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}

