package com.example.meetingmanager;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meetingmanager.db.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserViewPagerAdapter extends RecyclerView.Adapter<UserViewPagerAdapter.mViewHolder>{

    Context context;
    UserInfo newUser;
    List<String> userList = new ArrayList<>();
    List<String> label = new ArrayList<>();  //信息标签
    public UserViewPagerAdapter(List<String> userList) {
        this.userList = userList;
        initLabelList();
    }

    public void initLabelList(){
        label.add("我的头像");
        label.add("我的编号");
        label.add("我的姓名");
        label.add("我的职称");
        label.add("我的昵称");
        label.add("我的性别");
    }
    static class mViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView title,decription;
        ImageView faceimg,picture;

        public mViewHolder(View itemView) {
            super(itemView);
            //拿到所有控件
            cardView= itemView.findViewById(R.id.userinfo_card_view);
            title = itemView.findViewById(R.id.userinfo_title);
            decription = itemView.findViewById(R.id.userinfo_description);
            faceimg = itemView.findViewById(R.id.userinfo_faceimg);
            picture = itemView.findViewById(R.id.userinfo_picture);
        }
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.content_infoview,parent,false);//加载item_cardView布局
        mViewHolder holder=new mViewHolder(v);//加入内部类
        Log.i("suc","布局载入成功");
        return holder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        int i=position;
        holder.title.setText(label.get(i));
        if(label.get(i).equals("我的头像"))
           // holder.faceimg.setImageBitmap(userList.get(i).getImg())
            ;
        else
            holder.faceimg.setImageBitmap(null);
        if(!label.get(i).equals("我的头像"))
            holder.decription.setText(userList.get(i));
        else
            holder.decription.setText("");
        if(label.get(i).equals("我的编号")||label.get(i).equals("我的姓名")||label.get(i).equals("我的职称"))
            holder.picture.setImageBitmap(null);
        else
            ;

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

}
