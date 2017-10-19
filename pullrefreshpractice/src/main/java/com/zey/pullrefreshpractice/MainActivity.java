package com.zey.pullrefreshpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
PullToRefreshListView pullToRefreshListView;
    MyAdapter adapter;
    ArrayList<String> alist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        pullToRefreshListView= (PullToRefreshListView) findViewById(R.id.pullToRefresh);
        //封装数据
        for(int i=0;i<20;i++ ){
            alist.add("大家好，我是嗷大喵一枚~"+i);
        }
        adapter=new MyAdapter(MainActivity.this,alist);
        pullToRefreshListView.setAdapter(adapter);
        //给刷新的ListView设置属性 下拉或者上滑的文字
        //向下拉设置
        ILoadingLayout loadingLayout=
                pullToRefreshListView.getLoadingLayoutProxy(true,false);
        loadingLayout.setPullLabel("下拉刷新");
        loadingLayout.setRefreshingLabel("正在刷新。。。");
        loadingLayout.setReleaseLabel("松开刷新");


    }
}
