package com.example.meetingmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RoomSelectionActivity extends AppCompatActivity{

    public static final String ROOM_NAME = "room_name";
    public static final String ROOM_IMAGE_ID = "room_image_id";
    Button timePickerButton;
    //选择日期Dialog
    private DatePickerDialog datePickerDialog;
    //选择时间Dialog
    private TimePickerDialog timePickerDialog1, timePickerDialog2;

    //日期属性
    private Calendar calendar;
    private int Year;       //年
    private int month;      //月
    private int day;        //日
    private int hour;       //时
    private int minute;     //分
    private int seconds;    //秒

    TextView beginTime,endTime;
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
        timePickerButton = (Button)findViewById(R.id.roomselection_content_calendar) ;
        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDailog();  //选择时间
            }
        });
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);//获取当前年
        month = calendar.get(Calendar.MONTH)+1;//获取月份，加1是因为月份是从0开始计算的
        day = calendar.get(Calendar.DATE);//获取日
        hour = calendar.get(Calendar.HOUR);//获取小时
        minute = calendar.get(Calendar.MINUTE);//获取分钟
        seconds = calendar.get(Calendar.SECOND);//获取秒钟
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle("预约会议室");
        Glide.with(this).load(R.drawable.ic_launcher_background).into(roomImageView);

        //String roomContent = generateRoomContent(roomName);
        //roomContentText.setText(roomContent);
    }
    private void showDailog() {
        datePickerDialog = new DatePickerDialog(
                this,R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //monthOfYear 得到的月份会减1所以我们要加1
                String time = String.valueOf(year) + "　" + String.valueOf(monthOfYear + 1) + "  " + Integer.toString(dayOfMonth);
                beginTime = (TextView)findViewById(R.id.roomselection_content_showtime) ;
                beginTime.setText("当前时间："+year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日   "+hour+":"+minute+":"+seconds);
                Toast.makeText(RoomSelectionActivity.this, "当前时间："+year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日   "+hour+":"+minute+":"+seconds, Toast.LENGTH_SHORT).show();
                Year = year;
                month = monthOfYear + 1;
                day = dayOfMonth;
                Log.d("测试", time);
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        //设置起始日期和结束日期
        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMinDate(System.currentTimeMillis());
        datePicker.setMaxDate(System.currentTimeMillis()+518400000);
        datePickerDialog.show();
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showTime1();
                    }
                });
        //自动弹出键盘问题解决
        datePickerDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void showTime2() {//弹出结束时间界面
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_end_time, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择时间");
        builder.setView(view);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(RoomSelectionActivity.this,
                        "预约时间已提交", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        datePickerDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void showTime1() {//弹出开始时间界面
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_start_time, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择时间");
        builder.setView(view);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showTime2();
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        datePickerDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
