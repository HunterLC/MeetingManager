package com.example.meetingmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LoginActivity extends AppCompatActivity
{

    private TransitionView mAnimView;
    Button faceLogin ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mAnimView = findViewById(R.id.ani_view);
        faceLogin = (Button)findViewById(R.id.btn_registered_facelogin);
        faceLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,TestActivity.class);
                startActivity(intent);
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

    public void singUp(View view)
    {
        mAnimView.startAnimation();
    }
}

