package com.example.meetingmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity
{

    private TransitionView mAnimView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mAnimView = findViewById(R.id.ani_view);

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

