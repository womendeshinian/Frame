package com.zey.framemonthproject;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.zey.dao.UserDao;
import com.zey.fragment.FoodFragment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class NavSetActivity_SecondActivity extends SlidingActivity {
    ImageView mImageView,ivTouxiang,ivSearch,ivShoucang,ivAsked;
    private float mCurrentCheckedRadioLeft;//当前被选中的RadioButton距离左侧的距离
    private HorizontalScrollView mHorizontalScrollView;//上面的水平滚动控件
    private RadioGroup myRadioGroup;
    private LinearLayout layout,titleLayout;
    private ArrayList<View> mViews;
    UserDao dao;
    FragmentManager fm;
    FragmentTransaction ft;
    FoodFragment foodFragment;
    Intent intent;

    TextView tvName,tvExit;
    TextView tvSearch,tvLove,tvAsked;
    ImageView ivPic;
    //侧滑的属性对象
    private SlidingMenu menu;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navset);

        //加载隐藏的侧滑页面
        setBehindContentView(R.layout.behind);
        menu = getSlidingMenu();
        //设置侧滑的属性
        //设置侧滑页的宽度
        //在代码中动画的获取当前屏幕的分辨率
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        //获取屏幕宽度
        int width = displayMetrics.widthPixels;
        //获取屏幕高度
        int height = displayMetrics.heightPixels;
        menu.setBehindWidth(width/3*2);
        //设置侧滑的范围   全屏范围内滑动有效
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置侧滑页出现方向  出现方向与滑动方向相反
        menu.setMode(SlidingMenu.LEFT);
        menu.showContent();
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        foodFragment = new FoodFragment();
        ft.add(R.id.content,foodFragment);
        ft.show(foodFragment);
        ft.commit();
        getTitleInfo();
        initGroup();
        behind();

    }

    //侧滑页
    public void behind(){
        ivTouxiang= (ImageView) findViewById(R.id.ivPic);
        ivTouxiang.setImageResource(R.drawable.user);
        tvName= (TextView) findViewById(R.id.tvName);
        //接收登录用户的信息
       intent=getIntent();
        tvName.setText(intent.getStringExtra("name"));
        ivPic= (ImageView) findViewById(R.id.ivPic);
        tvExit= (TextView) findViewById(R.id.tvExit);
        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NavSetActivity_SecondActivity.this,LoginActivity.class);
                startActivity(intent);
                SharedPreferences sharedPreferences
                        =getSharedPreferences("userBean",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("cbLogin",false);
                editor.commit();
                finish();
            }
        });
        tvSearch= (TextView) findViewById(R.id.tvSearch);
        ivSearch= (ImageView) findViewById(R.id.ivSearch);
        ivSearch.setImageResource(R.drawable.love5);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NavSetActivity_SecondActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        tvLove= (TextView) findViewById(R.id.tvLove);
        ivShoucang= (ImageView) findViewById(R.id.ivShoucang);
        ivShoucang.setImageResource(R.drawable.love4);

        tvLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NavSetActivity_SecondActivity.this,LoveActivity.class);
                startActivity(intent);
            }
        });
        tvAsked= (TextView) findViewById(R.id.tvAsked);
        ivAsked= (ImageView) findViewById(R.id.ivAsked);
        ivAsked.setImageResource(R.drawable.asked);
        tvAsked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NavSetActivity_SecondActivity.this,AskedActivity.class);
                startActivity(intent);
            }
        });



    }


    private List<Map<String, Object>> titleList = new ArrayList<Map<String,Object>>();
    private void getTitleInfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", 0);
        map.put("title", "红烧");
        titleList.add(map);
        map = new HashMap<>();
        map.put("id", 1);
        map.put("title", "焖");
        titleList.add(map);

        map = new HashMap<>();
        map.put("id", 2);
        map.put("title", "炖");
        titleList.add(map);

        map = new HashMap<>();
        map.put("id", 3);
        map.put("title", "卤");
        titleList.add(map);

        map = new HashMap<>();
        map.put("id", 4);
        map.put("title", "川菜");
        titleList.add(map);

        map = new HashMap<>();
        map.put("id", 5);
        map.put("title", "湘菜");
        titleList.add(map);

        map = new HashMap<>();
        map.put("id", 6);
        map.put("title", "鲁菜");
        titleList.add(map);

        map = new HashMap<>();
        map.put("id", 7);
        map.put("title", "粤菜");
        titleList.add(map);

        map = new HashMap<>();
        map.put("id", 8);
        map.put("title", "养生");
        titleList.add(map);
        map = new HashMap<>();
        map.put("id", 9);
        map.put("title", "东北菜");
        titleList.add(map);
    }
    int _id = 1000;
    private void initGroup(){

        titleLayout = (LinearLayout) findViewById(R.id.title_lay);
        layout = (LinearLayout) findViewById(R.id.lay);
        //mImageView = new ImageView(this);

        mImageView = (ImageView) findViewById(R.id.img1);
        mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);


        myRadioGroup = new RadioGroup(this);
        myRadioGroup.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        myRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(myRadioGroup);
        for (int i = 0; i <titleList.size(); i++) {
            Map<String, Object> map = titleList.get(i);
            RadioButton radio = new RadioButton(this);

            radio.setBackgroundResource(R.drawable.radiobtn_selector);
            radio.setButtonDrawable(android.R.color.transparent);
            LayoutParams l = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, Gravity.CENTER);
            radio.setLayoutParams(l);
            radio.setGravity(Gravity.CENTER);
            radio.setPadding(20, 15, 20, 15);
            radio.setId(_id+i);
            //radio.setPadding(left, top, right, bottom)
            radio.setText(map.get("title")+"");
            radio.setTextColor(Color.WHITE);
            radio.setTextSize(25);
            radio.setTag(map);
            if (i == 0) {
                radio.setChecked(true);
                int itemWidth = (int) radio.getPaint().measureText(map.get("title")+"");
                mImageView.setLayoutParams(new  LayoutParams(itemWidth+radio.getPaddingLeft()+radio.getPaddingRight(),4));
            }
            myRadioGroup.addView(radio);
        }
        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case 1000:
                        new Thread(){
                            @Override
                            public void run() {
                                foodFragment.getData("http://apicloud.mob.com/v1/cook/menu/search?"+
                                "page=1&name=%E7%BA%A2%E7%83%A7&cid=0010001007&key=520520test&size=20");
                            }
                        }.start();
                        break;
                    case 1001:
                        new Thread(){
                            @Override
                            public void run() {
                                foodFragment.getData("http://apicloud.mob.com/v1/cook/menu/search?"+
                                        "page=1&name=%E7%84%96&cid=0010001007&key=520520test&size=20");
                            }
                        }.start();
                        break;
                    case 1002:
                        new Thread(){
                            @Override
                            public void run() {
                                foodFragment.getData("http://apicloud.mob.com/v1/cook/menu/search?"+
                                        "page=1&name=%E7%82%96&cid=0010001007&key=520520test&size=20");
                            }
                        }.start();
                        break;
                    case 1003:
                        new Thread(){
                            @Override
                            public void run() {
                                foodFragment.getData("http://apicloud.mob.com/v1/cook/menu/search?"+
                                        "page=1&name=%E5%8D%A4&cid=0010001007&key=520520test&size=20");
                            }
                        }.start();
                        break;
                    case 1004:  new Thread(){
                        @Override
                        public void run() {
                            foodFragment.getData("http://apicloud.mob.com/v1/cook/menu/search?"+
                                    "page=1&name=%E5%B7%9D%E8%8F%9C&cid=0010001007&key=520520test&size=20");
                        }
                    }.start();

                        break;
                    case 1005:
                        new Thread(){
                            @Override
                            public void run() {
                                foodFragment.getData("http://apicloud.mob.com/v1/cook/menu/search?"+
                                        "page=1&name=%E6%B9%98%E8%8F%9C&cid=0010001007&key=520520test&size=20");
                            }
                        }.start();
                        break;
                    case 1006:
                        new Thread(){
                            @Override
                            public void run() {
                                foodFragment.getData("http://apicloud.mob.com/v1/cook/menu/search?"+
                                        "page=1&name=%E9%B2%81%E8%8F%9C&cid=0010001007&key=520520test&size=20");
                            }
                        }.start();
                        break;
                    case 1007:
                        new Thread(){
                            @Override
                            public void run() {
                                foodFragment.getData("http://apicloud.mob.com/v1/cook/menu/search?"+
                                        "page=1&name=%E7%B2%A4%E8%8F%9C&cid=0010001007&key=520520test&size=20");
                            }
                        }.start();
                        break;
                    case 1008:
                        new Thread(){
                            @Override
                            public void run() {
                                foodFragment.getData("http://apicloud.mob.com/v1/cook/menu/search?"+
                                        "page=1&name=%E5%85%BB%E7%94%9F&cid=0010001007&key=520520test&size=20");
                            }
                        }.start();
                        break;
                    case 1009:
                        new Thread(){
                            @Override
                            public void run() {
                                foodFragment.getData("http://apicloud.mob.com/v1/cook/menu/search?"+
                                        "page=1&name=%E4%B8%9C%E5%8C%97%E8%8F%9C&cid=0010001007&key=520520test&size=20");
                            }
                        }.start();
                        break;
                }
                //Map<String, Object> map = (Map<String, Object>) group.getChildAt(checkedId).getTag();
                int radioButtonId = group.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton) findViewById(radioButtonId);

                AnimationSet animationSet = new AnimationSet(true);
                TranslateAnimation translateAnimation;
                translateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, rb.getLeft(), 0f, 0f);
                animationSet.addAnimation(translateAnimation);
                animationSet.setFillBefore(true);
                animationSet.setFillAfter(true);
                animationSet.setDuration(300);

                mImageView.startAnimation(animationSet);//开始上面蓝色横条图片的动画切换
                mCurrentCheckedRadioLeft = rb.getLeft();//更新当前蓝色横条距离左边的距离
                mHorizontalScrollView.smoothScrollTo((int)mCurrentCheckedRadioLeft-(int)getResources().getDimension(R.dimen.rdo2), 0);

                mImageView.setLayoutParams(new  LayoutParams(rb.getRight()-rb.getLeft(),4));

            }
        });

    }

    String picturePath=null;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==110&&resultCode==Activity.RESULT_OK&&null!=data){
            Uri selectedImage=data.getData();
            String[] filePathColumns={MediaStore.Images.Media.DATA};
            Cursor c=this.getContentResolver().query(selectedImage,filePathColumns,null,null,null);
            c.moveToFirst();
            int columnIndex=c.getColumnIndex(filePathColumns[0]);
            picturePath=c.getString(columnIndex);
            c.close();
            try {
                InputStream is=new FileInputStream(picturePath);
                Log.d("data3","........."+picturePath);
                ivPic.setImageBitmap(BitmapFactory.decodeStream(is));
                dao=new UserDao(NavSetActivity_SecondActivity.this);
                dao.insertPicByTel(picturePath,intent.getStringExtra("tel"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
