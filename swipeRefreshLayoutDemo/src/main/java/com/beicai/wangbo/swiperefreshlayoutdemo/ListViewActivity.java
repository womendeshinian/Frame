package com.beicai.wangbo.swiperefreshlayoutdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView listView;
    private MyAdapter adapter;
    List<ImageInfor> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initData();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new MyAdapter(list);
        listView.setAdapter(adapter);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.addList(list);
                mHandler.sendEmptyMessageDelayed(0,5000);
            }
        });
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getApplication(),"数据更新完毕",Toast.LENGTH_SHORT).show();
            //刷新完成后停止刷新
            mSwipeRefreshLayout.setRefreshing(false);
        }
    };

    /**
     * 初始化数据
     */
    private void initData(){
        list.add(new ImageInfor(R.drawable.caiyilin, "蔡依林"));
        list.add(new ImageInfor(R.drawable.ulinxinru, "林心如"));
        list.add(new ImageInfor(R.drawable.caiyilin,"蔡依林"));
        list.add(new ImageInfor(R.drawable.ulinxinru, "林心如"));
        list.add(new ImageInfor(R.drawable.caiyilin,"蔡依林"));
        list.add(new ImageInfor(R.drawable.ulinxinru, "林心如"));
    }

    /**
     * 自定义adapter
     */
    class MyAdapter extends BaseAdapter {
        private List<ImageInfor> list;

        MyAdapter(List<ImageInfor> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getApplication()).inflate(R.layout.carditem,null);
            ImageView iv_backgroud = (ImageView) convertView.findViewById(R.id.picture);
            TextView tv_title = (TextView) convertView.findViewById(R.id.name);
            iv_backgroud.setBackgroundResource(list.get(position).getImageId());
            tv_title.setText(list.get(position).getName());
            return convertView;
        }

        public void addList(List<ImageInfor> listAdd){
            this.list.addAll(listAdd);
            notifyDataSetChanged();
        }

    }
}
