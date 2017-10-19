package com.zey.framemonthproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;


import com.zey.adapter.GridViewAdapter;
import com.zey.adapter.ViewPagerAdapter;
import com.zey.utils.GridViewData;
import com.zey.utils.ViewPagerData;

import java.util.ArrayList;


public class SecondActivity extends Activity {

    ViewPager vp;

    ViewPagerAdapter adapter;

    Button btnEnter;

    GridView gridView;
    GridViewAdapter gvAdapter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        init();

        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent intent=new Intent(SecondActivity.this,LoginActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        btnEnter=findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        gridView=findViewById(R.id.gridView);
        int[] data = GridViewData.getData();
        System.out.println(data.length+"@@@@@@@@@@@@@@@@@@@@@@@@");
        gvAdapter=new GridViewAdapter(SecondActivity.this,data);
        gridView.setAdapter(gvAdapter);

    }
    private void init() {

        vp=findViewById(R.id.viewPager);
//        vp.setId(R.id.test);
        ArrayList<ImageView> alist= ViewPagerData.data(SecondActivity.this);
        System.out.println(alist.size()+"##############3");
        adapter=new ViewPagerAdapter(SecondActivity.this,alist);
        vp.setAdapter(adapter);
        //设置当前页
//        int  m=Integer.MAX_VALUE/2 % (alist.size());
//        vp.setCurrentItem(Integer.MAX_VALUE/2-m);
        vp.setCurrentItem(0);
//        //用线程设置自动轮播
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){

                    SystemClock.sleep(2000);
                    //2秒后继续展示下一张  加载页面要在主线程中执行
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //设置当前条目
                            vp.setCurrentItem(vp.getCurrentItem()+1);
                        }
                    });
                }
            }
        }).start();

    }

}
