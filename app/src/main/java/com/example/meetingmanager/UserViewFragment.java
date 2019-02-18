package com.example.meetingmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meetingmanager.db.UserInfo;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class UserViewFragment extends Fragment {

    public static Fragment newUserInstance(){
        return  new UserViewFragment();
    }
    final List<String> userList = new ArrayList<>();
    final UserInfo user = new UserInfo();  //个人信息

    RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.userinfo_recyclerview, container, false);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

                super.onViewCreated(view, savedInstanceState);
                mRecyclerView=view.findViewById(R.id.userinfo_recyclerView);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
                initUserData();
                mRecyclerView.setAdapter(new UserViewPagerAdapter(userList));
    }

    private void initUserData() {
        //获取用户信息
        user.setAvatar("000");//用户头像
        user.setId(2019);//用户编号
        user.setName("刘畅");//用户姓名
        user.setTitle("项目经理");//用户职称
        user.setUsername("畅畅快报");//用户名
        user.setMale(1);//用户性别

        //将用户信息加入到list列表中
        userList.add(user.getAvatar());
        userList.add(String.valueOf(user.getId()));
        userList.add(user.getName());
        userList.add(user.getTitle());
        userList.add(user.getUsername());
        userList.add(user.getMale()==1?"男":"女");
    }
}
