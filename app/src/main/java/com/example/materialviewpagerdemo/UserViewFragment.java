package com.example.materialviewpagerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

public class UserViewFragment extends Fragment {

    public static Fragment newUserInstance(){
        return  new RecyclerViewFragment();
    }
    final List<MeetingInfo> items1 = new ArrayList<>();

    RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view, container, false);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

                super.onViewCreated(view, savedInstanceState);
                mRecyclerView=view.findViewById(R.id.recyclerView22);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
                initUserData();
                mRecyclerView.setAdapter(new UserViewPagerAdapter(items1));
    }

    private void initUserData() {

        items1.add(new MeetingInfo(R.drawable.ic_launcher_background,"销售部年终促销","康书铭","2019-1-15","X2019"));
        items1.add(new MeetingInfo(R.drawable.ic_launcher_foreground,"放假注意事项","刘畅","2019-1-16","X9201"));


    }
}
