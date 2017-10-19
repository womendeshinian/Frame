package com.zey.swiperefreshlayout;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
SwipeRefreshLayout swipeRefreshLayout;
    MyAdapter adapter;
    ListView listView;
    ArrayList<String> alist=new ArrayList<>();
    ArrayList<String> data=new ArrayList<>();
    int index;
    Handler hand=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x111){

               adapter.updateAdapter(data);
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        //设置刷新控件的颜色的变化
        swipeRefreshLayout.setColorSchemeColors(getResources()
        .getColor(android.R.color.holo_blue_bright,null),
         getResources().getColor(android.R.color.holo_green_light),
          getResources().getColor(android.R.color.holo_red_dark) ,
                getResources().getColor(android.R.color.holo_orange_dark));
        //设置刷新控件的背景颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(
                getResources().getColor(R.color.colorAccent,null)
        );
        //给刷新控件设置监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this,"正在刷新",Toast.LENGTH_SHORT).show();
                hand.sendEmptyMessageDelayed(0x111,3000);
                //将新数据插入到集合的前面，显示到listView
                int i;
                int k=0;
                for(i=index;i<index+20;i++){
                    data.add(k,alist.get(i));
                    k++;
                }
                index+=20;
            }
        });
        //封装全部要显示的数据
        for(int i=0;i<100;i++){
            alist.add("我是赵二盈"+i);
        }
        //获取第一次 要显示的前20条数据
        for(index=0;index<20;index++){
            data.add(alist.get(index));
        }

        listView= (ListView) findViewById(R.id.listView);
        adapter=new MyAdapter(data,MainActivity.this);
        listView.setAdapter(adapter);

    }


}
