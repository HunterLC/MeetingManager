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
import java.util.Date;
import java.util.List;

public class RecyclerViewFragment extends Fragment {
    public static Fragment newHistoryInstance(){
        return  new RecyclerViewFragment();
    }

    final List<MeetingInfo> items1 = new ArrayList<>();
    final List<MeetingInfo> items = new ArrayList<>();
    static final int ITEMS = 8;


    RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

                super.onViewCreated(view, savedInstanceState);
                mRecyclerView=view.findViewById(R.id.recyclerView);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
                initHistoryData();
                mRecyclerView.setAdapter(new HistoryViewPagerAdapter(items));
        }

    private void initHistoryData() {
        items.add(new MeetingInfo(R.drawable.ic_launcher_background,"销售部年终促销","康书铭",new Date(),"......"));
        items.add(new MeetingInfo(R.drawable.ic_launcher_foreground,"放假注意事项","刘畅",new Date(),"X9201"));
        items.add(new MeetingInfo(R.drawable.logo_white,"消防安全讲座","张舜宇",new Date(),"X4359"));
        items.add(new MeetingInfo(R.drawable.material_drawer_badge,"策划部总结大会","李铖",new Date(),"X1415"));
        items.add(new MeetingInfo(R.drawable.material_drawer_circle_mask,"随便开着玩大会","石润昊",new Date(),"X7508"));
        items.add(new MeetingInfo(R.drawable.ic_launcher_background,"销售部年终促销","康书铭",new Date(),"X2019"));
        items.add(new MeetingInfo(R.drawable.ic_launcher_background,"销售部年终促销","康书铭",new Date(),"X2019"));
        items.add(new MeetingInfo(R.drawable.ic_launcher_background,"销售部年终促销","康书铭",new Date(),"X2019"));


    }

}
