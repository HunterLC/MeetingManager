package com.example.meetingmanager;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.ExpandableBadgeDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondarySwitchDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryToggleDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class MainActivity extends AppCompatActivity {
    private MaterialViewPager mViewPager;
    private Drawer mainDrawer = null;
    static final int TAPS = 3;
    private IProfile profile = null;//登录用户信息
    private IProfile profile1 = null;//登录用户信息
    private IProfile profile2 = null;//登录用户信息
    private AccountHeader headerResult = null;//head头布局
    private SectionDrawerItem sectionItem_user = null;//个人中心分组标签，无点击效果
    private PrimaryDrawerItem primaryItem_userInfo = null;//我的信息 菜单
    private PrimaryDrawerItem primaryItem_history = null; //参会记录 菜单
    private ExpandableBadgeDrawerItem expandableItem_setting = null;//设置 菜单
    private SecondaryDrawerItem secondItem = null;//子item
    private SwitchDrawerItem switchItem1 = null;//带有switch开关的item1
    private SwitchDrawerItem switchItem2 = null;//带有switch开关的item2
    private Drawer result = null;//嵌套抽屉

    /**
     * toolbar联动菜单
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    /**
     * onCreate
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.materialViewPager);
        //添加监听
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });
        //设置Toolbar
        Toolbar toolbar = mViewPager.getToolbar();
        toolbar.setHasTransientState(true);
        toolbar.inflateMenu(R.menu.toolbar_menu);//设置右上角填充menu
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }

        //设置每一个menu菜单的点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_information:
                       Toast.makeText(MainActivity.this,"你点击了消息通知",Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(MainActivity.this,MessageActivity.class);
                       startActivity(intent);

                        break;
                }
                return false;
            }
        });



        //设置Adapter
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % TAPS) {
                    case 0:
                        return new OrderViewFragment();
                    case 1:
                        return RecyclerViewFragment.newHistoryInstance();
                    case 2:
                        return UserViewFragment.newUserInstance();
                    default:
                        return new Fragment();
                }
            }

            @Override
            public int getCount() {
                return TAPS;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % TAPS) {
                    case 0:
                        return "会议预约";
                    case 1:
                        return "参会记录";
                    case 2:
                        return "个人中心";

                    default:
                        return "TAPN";
                }
            }
        });
        //设置setViewPager
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        setProfile();
        //创建和NavigationView相似的Headerlayout
        setHeadLayout(savedInstanceState);//方法中不能直接设置此属性，所以oncreate中传参
       /* result = new DrawerBuilder()
                .withActivity(this)
                .addDrawerItems(setPrimaryItem())
                .build();*/
        //最后创建抽屉，将配置好的布局属性加进去
        mainDrawer = new DrawerBuilder().withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)//和toolbar关联
                .withActionBarDrawerToggle(true) //启用toolbar的ActionBarDrawerToggle动画
                .addDrawerItems(
                        setSectionItem_user(), //个人中心分隔条
                        setPrimaryItem_userInfo(),//我的信息 菜单条
                        setPrimaryItem_history(),//历史会议记录 菜单条
                        setExpandableItem_setting(),
                        setSwitchItem1(),
                        setSwitchItem2()
                )//给抽屉添加item布局
                .withShowDrawerOnFirstLaunch(false) //默认开启抽屉
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //监听方法实现
                        int choose = (int)drawerItem.getIdentifier();
                        switch(choose){
                            case 2:mViewPager.getViewPager().setCurrentItem(2);
                                break;//个人信息
                            case 3:mViewPager.getViewPager().setCurrentItem(1);//参会记录
                                break;
                            case 5:
                                Intent intent = new Intent(MainActivity.this,RoomSelectionActivity.class);
                                startActivity(intent);
                            default:
                                Toast.makeText(MainActivity.this, drawerItem.getIdentifier() + " is clicked", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                }) //抽屉中item的监听事件
                .withDrawerGravity(Gravity.LEFT) //设置抽屉打开方向默认从左，end从右侧打开
                //.append(result)
                .build();




    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = mainDrawer.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    /**
     * ①IProfile创建登录用户对象
     */
    private void setProfile() {
        profile = new ProfileDrawerItem()
                .withName("ksm")
                .withEmail("626289512@qq.com")
                .withIcon("http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg")
                .withIdentifier(100);//标识符，当设置监听事件时可以根据这个来区别对象
        profile1 = new ProfileDrawerItem()
                .withName("lc")
                .withEmail("411887055@qq.com")
                .withIcon("http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg")
                .withIdentifier(101);//标识符，当设置监听事件时可以根据这个来区别对象
        profile2 = new ProfileDrawerItem()
                .withName("ctc")
                .withEmail("411887055@qq.com")
                .withIcon("http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg")
                .withIdentifier(102);//标识符，当设置监听事件时可以根据这个来区别对象
    }

    /**
     * ②创建head布局，显示概要，将创建的profile加入布局中
     * withOnAccountHeaderListener
     * 获得标识符进行判断点击事件是来自哪个用户对象而执行相应的响应方法。
     * 这里获取标识符的方法getIdentifier方法的返回值是long型的，
     * 而switch是不接受long变量的，所以这里需要转一下变量类型。
     * 监听事件的方法是返回值的，一般是按照原来给出的返回false就可以了，
     * 表示当执行完点击事件后，关闭抽屉菜单；
     * withTranslucentStatusBar(true) 设置是否启用沉浸式状态栏
     * addProfiles(profile) 于添加用户对象，可以添加多个，使用逗号进行隔开；
     * withSaveIntstance方法就是将意外被杀掉的Activity的状态设置回来。
     * 之后我们使用build就可以获得一个类似Headerlayout的对象。
     * 把新创建的AccountHeader对象添加进去的方法：
     * new DrawerBuilder()
     * .withActivity(this)
     * .withAccountHeader(headerResult)
     * .build();
     */
    private void setHeadLayout(Bundle savedInstanceState) {
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.ic_launcher_background)
                .withTranslucentStatusBar(true) //半透明效果
                .addProfiles(profile,profile1,profile2)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        switch ((int) profile.getIdentifier()) {
                            case 100://根据不同标识符监听不同对象
                                Toast.makeText(MainActivity.this, "头像被点击", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();//至此头布局head构建完成
    }

    /**
     * 个人中心分组标签
     *
     * @return
     */
    private SectionDrawerItem setSectionItem_user() {
        sectionItem_user = new SectionDrawerItem()
                .withName("个人中心")
                .withDivider(true)
                .withTextColor(R.color.md_black_1000)
                .withIdentifier(1);
        return  sectionItem_user;
    }

    private PrimaryDrawerItem setPrimaryItem_userInfo() {
        primaryItem_userInfo = new PrimaryDrawerItem()
                .withName("我的信息")
                .withDescription("")
                .withIcon(GoogleMaterial.Icon.gmd_accounts_list_alt)
                .withIdentifier(2)
                .withSelectable(false);
        return primaryItem_userInfo;
    }

    private PrimaryDrawerItem setPrimaryItem_history() {
        primaryItem_history = new PrimaryDrawerItem()
                .withName("参会记录")
                .withDescription("")
                .withIcon(GoogleMaterial.Icon.gmd_accounts_list_alt)
                .withBadge("1") //气泡
                .withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_light_blue_A700))
                .withIdentifier(3)
                .withSelectable(false);
        return primaryItem_history;
    }



    /**
     * 伸缩式item包含内部子item SecondaryDrawerItem
     * @return
     */
    private ExpandableBadgeDrawerItem setExpandableItem_setting() {
        expandableItem_setting = new ExpandableBadgeDrawerItem()
                .withName("设置")
                .withIcon(FontAwesome.Icon.faw_apple)
                .withIdentifier(4)
                .withBadge("10") //设置圆角气泡中的数字
                .withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_light_blue_A700))
                .withSubItems(
                        new SecondaryDrawerItem().withName("关于我们").withIdentifier(5),
                        new SecondarySwitchDrawerItem().withName("软件自启").withIdentifier(6)
                ); //内部item
        return expandableItem_setting;
    }
    private SwitchDrawerItem setSwitchItem1(){
        switchItem1=new SwitchDrawerItem()
               // .withIcon(R.drawable.profile3)
                .withIdentifier(7)
                .withCheckable(false)
                .withOnCheckedChangeListener(checkedChangeListener)
                .withName("开关1");
        return switchItem1;
    }
    private SwitchDrawerItem setSwitchItem2(){
        switchItem2=new SwitchDrawerItem()
                //.withIcon(R.drawable.profile4)
                .withIdentifier(8)
                .withCheckable(true)
                .withOnCheckedChangeListener(checkedChangeListener)
                .withName("开关2");
        return switchItem2;
    }
    //开关item的状态监听
    private OnCheckedChangeListener checkedChangeListener=new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if(drawerItem instanceof Nameable) {
                Toast.makeText(MainActivity.this,((Nameable)drawerItem).getName() + "'s check is" + isChecked,Toast.LENGTH_SHORT).show();
            }

        }
    };

}
