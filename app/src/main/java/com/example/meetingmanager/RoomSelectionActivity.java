package com.example.meetingmanager;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
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
                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                // 通知渠道的id
                String id = "my_channel_01";
                // 用户可以看到的通知渠道的名字.
                CharSequence name = getString(R.string.app_name);
                // 用户可以看到的通知渠道的描述
                String description = getString(R.string.app_name);
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(id, name, importance);
                // 配置通知渠道的属性
                mChannel.setDescription(description);
                // 设置通知出现时的闪灯（如果 android 设备支持的话）
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                // 设置通知出现时的震动（如果 android 设备支持的话）
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
               //最后在notificationmanager中创建该通知渠道
                manager.createNotificationChannel(mChannel);
                String CHANNEL_ID = "my_channel_01";
                Notification notification = new Notification.Builder(RoomSelectionActivity.this)
                        .setContentText("This is a test")
                        .setContentTitle("This is a title")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_icon)
                        .setTicker("you have a new information,check it now?")
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_icon))
                        .setChannelId(CHANNEL_ID)
                        .build();
                manager.notify(1,notification);
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
