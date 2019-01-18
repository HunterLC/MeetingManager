package com.example.materialviewpagerdemo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UserViewPagerAdapter extends RecyclerView.Adapter<UserViewPagerAdapter.mViewHolder>{

    Context context;
    List<MeetingInfo> newList;
    public UserViewPagerAdapter(List<MeetingInfo> newList) {
        this.newList = newList;
    }

    static class mViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView title,speaker,time,location;
        ImageView img;

        public mViewHolder(View itemView) {
            super(itemView);
            //拿到所有控件
            cardView= itemView.findViewById(R.id.card_view);
            img= itemView.findViewById(R.id.meeting_img);
            title= itemView.findViewById(R.id.meeting_title);
            speaker= itemView.findViewById(R.id.meeting_speaker);
            time = itemView.findViewById(R.id.meeting_time);
            location = itemView.findViewById(R.id.meeting_location);
        }
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.content_cardview,parent,false);//加载item_cardView布局
        mViewHolder holder=new mViewHolder(v);//加入内部类
        Log.i("suc","布局载入成功");
        return holder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        int i=position;
        holder.img.setImageResource(newList.get(i).getImg());
        holder.title.setText(newList.get(i).getTitle());
        holder.speaker.setText(newList.get(i).getSpeaker());
        holder.time.setText(newList.get(i).getTime());
        holder.location.setText(newList.get(i).getLocation());

    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

}
