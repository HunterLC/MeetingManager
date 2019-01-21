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

    //Button控件
    Button timePickerButton;
    Button chooseNumberButton;
    Button chooseRoomButton;
    Button chooseParticipatorButton;
    Button checkInformationButton;
    Button commitButton;

    //选择日期Dialog
    private DatePickerDialog datePickerDialog;
    //选择时间Dialog

    //日期属性
    private Calendar calendar;
    private int Year;       //年
    private int month;      //月
    private int day;        //日
    private int hour;       //时
    private int minute;     //分
    private int seconds;    //秒

    //TextView控件
    TextView beginTime,endTime;
    TextView showNumberView;
    TextView showRoomView;

    private int choice;
    private AlertDialog.Builder builder;
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
        timePickerButton = (Button)findViewById(R.id.roomselection_content_calendar);
        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDailog();  //选择时间
            }
        });

        checkInformationButton = (Button)findViewById(R.id.roomselection_checkinformation);
        checkInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomSelectionActivity.this,RoomDetailActivity.class);
                intent.putExtra(RoomDetailActivity.ROOM_NAME,"会议室详情");
                intent.putExtra(RoomDetailActivity.ROOM_IMAGE_ID,R.drawable.meeting_room_sample);
                startActivity(intent);
            }
        });

        commitButton = (Button)findViewById(R.id.roomselection_commit);
        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RoomSelectionActivity.this,"你已经成功预约了本次会议",Toast.LENGTH_SHORT).show();
            }
        });

        chooseNumberButton =(Button)findViewById(R.id.roomselection_content_choose_number);
        showNumberView = (TextView)findViewById(R.id.roomselection_content_shownumber);
        chooseNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPeopleNumberSelect();
            }
        });
        chooseRoomButton =(Button)findViewById(R.id.roomselection_content_choose_room);
        showRoomView = (TextView) findViewById(R.id.roomselection_content_showroom);
        chooseRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRoomSelect();
            }
        });
        chooseParticipatorButton =(Button)findViewById(R.id.roomselection_content_choose_participator);

        //日历设置
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);//获取当前年
        month = calendar.get(Calendar.MONTH)+1;//获取月份，加1是因为月份是从0开始计算的
        day = calendar.get(Calendar.DATE);//获取日
        hour = calendar.get(Calendar.HOUR);//获取小时
        minute = calendar.get(Calendar.MINUTE);//获取分钟
        seconds = calendar.get(Calendar.SECOND);//获取秒钟

        //toolbar设置
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle("会议详细设定");
        Glide.with(this).load(R.drawable.ic_launcher_background).into(roomImageView);

        //String roomContent = generateRoomContent(roomName);
        //roomContentText.setText(roomContent);
    }

    /**
     * 单选 dialog
     */
    private void showPeopleNumberSelect() {

        //默认选中第一个
        final String[] items = {"10人", "30人", "45人", "60人", "90人", "120人"};
        choice = -1;
        builder = new AlertDialog.Builder(this,R.style.dialog)
                .setIcon(R.mipmap.ic_launcher).setTitle("选择会议人数")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choice = i;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (choice != -1) {
                            Toast.makeText(RoomSelectionActivity.this, "你选择了" + items[choice], Toast.LENGTH_LONG).show();
                            showNumberView.setText(items[choice]);
                        }
                    }
                });
        builder.create().show();
    }

    /**
     * 单选 dialog
     */
    private void showRoomSelect() {

        //默认选中第一个
        final String[] items = {"7508", "9118", "9126", "9208", "9334", "9501"};
        choice = -1;
        builder = new AlertDialog.Builder(this,R.style.dialog).setIcon(R.mipmap.ic_launcher).setTitle("选择会议室编号")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choice = i;
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (choice != -1) {
                            Toast.makeText(RoomSelectionActivity.this, "你选择了" + items[choice], Toast.LENGTH_LONG).show();
                            showRoomView.setText(items[choice]);
                        }
                    }
                });
        builder.create().show();
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
                showTips();  //提示用户未预约会议成功
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showTips(){
        builder = new AlertDialog.Builder(this,R.style.dialog)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("您未完成预约流程，确认放弃操作么？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ;
                    }
                });
        builder.create().show();
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
