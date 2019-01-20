package com.example.meetingmanager;

import android.annotation.SuppressLint;
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

public class MessageViewPagerAdapter extends RecyclerView.Adapter<MessageViewPagerAdapter.mViewHolder>{

    Context context;
    List<MessageInfo> newList;
    public MessageViewPagerAdapter(List<MessageInfo> newList) {
        this.newList = newList;
    }

    static class mViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView title,curtime,content;

        @SuppressLint("CutPasteId")
        public mViewHolder(View itemView) {
            super(itemView);
            //拿到所有控件

            cardView= itemView.findViewById(R.id.message_view);
            title= itemView.findViewById(R.id.message_title);
            content= itemView.findViewById(R.id.message_content);
            curtime = itemView.findViewById(R.id.message_curtime);

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
        holder.title.setText(newList.get(i).getTitle());
        holder.curtime.setText(newList.get(i).getCurrentTime());
        holder.content.setText(newList.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

}