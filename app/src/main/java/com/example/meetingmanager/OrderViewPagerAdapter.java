package com.example.meetingmanager;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class OrderViewPagerAdapter extends RecyclerView.Adapter<OrderViewPagerAdapter.mViewHolder>{

    Context context;


    static class mViewHolder extends RecyclerView.ViewHolder {

        Button button;

        public mViewHolder(View itemView) {
            super(itemView);
            //拿到所有控件
            /*cardView= itemView.findViewById(R.id.userinfo_card_view);
            title = itemView.findViewById(R.id.userinfo_title);
            decription = itemView.findViewById(R.id.userinfo_description);
            faceimg = itemView.findViewById(R.id.userinfo_faceimg);
            picture = itemView.findViewById(R.id.userinfo_picture);*/
            button = itemView.findViewById(R.id.meeting_commit);

        }
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null)
            context = parent.getContext();
        View v= LayoutInflater.from(context).inflate(R.layout.content_meeting_information,parent,false);//加载item_cardView布局
        mViewHolder holder=new mViewHolder(v);//加入内部类
        Log.i("suc","布局载入成功");
        return holder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        int i=position;
        //holder.title.setText(label.get(i));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,RoomSelectionActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

}
