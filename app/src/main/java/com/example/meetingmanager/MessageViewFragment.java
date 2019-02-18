package com.example.meetingmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meetingmanager.db.MessageInfo;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageViewFragment extends Fragment {
    public static Fragment newMessageInstance(){
        return  new RecyclerViewFragment();
    }

    final List<MessageInfo> items1 = new ArrayList<>();
    final List<MessageInfo> items = new ArrayList<>();
    static final int ITEMS = 8;


    RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_recyclerview, container, false);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mRecyclerView=view.findViewById(R.id.message_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        initMessageData();
        mRecyclerView.setAdapter(new MessageViewPagerAdapter(items));
    }

    private void initMessageData() {
        items1.add(new MessageInfo("会议提醒","亲，你将于14：00参加位于X1225的会议，请按时出席！",new Date(System.currentTimeMillis())));

    }

}
