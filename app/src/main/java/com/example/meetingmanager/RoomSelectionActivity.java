package com.example.meetingmanager;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RoomSelectionActivity extends AppCompatActivity {

    public static final String ROOM_NAME = "room_name";
    public static final String ROOM_IMAGE_ID = "room_image_id";
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_selection);
        Intent intent = getIntent();
        //String roomName = intent.getStringExtra(ROOM_NAME);
        //int roomImageId = intent.getIntExtra(ROOM_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.roomselection_toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.roomselection_collapsing_toolbar);
        ImageView roomImageView = (ImageView)findViewById(R.id.roomselection_image_view);

        //寻找内部控件
       // TextView roomContentText = (TextView)findViewById(R.id.meetingdetail_content_text);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle("预约会议室");
        Glide.with(this).load(R.drawable.ic_launcher_background).into(roomImageView);
       /* spinner = (Spinner)findViewById(R.id.roomselection_content_participator) ;
        // 声明一个ArrayAdapter用于存放简单数据
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RoomSelectionActivity.this, android.R.layout.simple_spinner_item, getData());
        // 把定义好的Adapter设定到spinner中
        spinner.setAdapter(adapter);
        // 为第一个Spinner设定选中事件
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RoomSelectionActivity.this,"you clicked "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });*/
        //String roomContent = generateRoomContent(roomName);
        //roomContentText.setText(roomContent);
    }
    private String generateRoomContent(String roomName){
        StringBuilder roomContent = new StringBuilder();
        for(int i = 0; i <500 ; i++){
            roomContent.append(roomName);
        }
        return  roomContent.toString();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private List<String> getData() {
        // 数据源
        List<String> dataList = new ArrayList<String>();
        dataList.add("北京");
        dataList.add("上海");
        dataList.add("南京");
        dataList.add("宜昌");
        return dataList;
         }
}
