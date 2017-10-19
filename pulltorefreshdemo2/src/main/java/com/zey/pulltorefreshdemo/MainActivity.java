package com.zey.pulltorefreshdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zey.adapter.MyAdapter;
import com.zey.bean.PullBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView mPullToRefreshListView;
    private List<PullBean> data = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pullToRefresh);
        data = getData();
        adapter = new MyAdapter(this, data);
        mPullToRefreshListView.setAdapter(adapter);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        init();
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PullBean bean = new PullBean();
                bean.setTitle("下拉刷新");
                bean.setContent("我的神");
                adapter.addFirst(bean);
                new FinishRefresh().execute();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PullBean bean = new PullBean();
                bean.setTitle("上拉刷新");
                bean.setContent("我的神");
                adapter.addLast(bean);
                new FinishRefresh().execute();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private class FinishRefresh extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//        	adapter.notifyDataSetChanged();
            mPullToRefreshListView.onRefreshComplete();
        }
    }

    private void init() {
        ILoadingLayout startLabels = mPullToRefreshListView
                .getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在载入...");// 刷新时
        startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = mPullToRefreshListView.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在载入...");// 刷新时
        endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
        // 设置下拉刷新文本
        mPullToRefreshListView.getLoadingLayoutProxy(false, true)
                .setPullLabel("上拉刷新...");
        mPullToRefreshListView.getLoadingLayoutProxy(false, true).setReleaseLabel(
                "放开刷新...");
        mPullToRefreshListView.getLoadingLayoutProxy(false, true).setRefreshingLabel(
                "正在加载...");
        // 设置上拉刷新文本
        mPullToRefreshListView.getLoadingLayoutProxy(true, false)
                .setPullLabel("下拉刷新...");
        mPullToRefreshListView.getLoadingLayoutProxy(true, false).setReleaseLabel(
                "放开刷新...");
        mPullToRefreshListView.getLoadingLayoutProxy(true, false).setRefreshingLabel(
                "正在加载...");
    }

    private List<PullBean> getData() {
        List<PullBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PullBean mPullBean = new PullBean();
            mPullBean.setTitle("item " + i + " 搜索业务增速下滑 Google廉颇老矣?");
            mPullBean.setContent("Google于10月17日发布了2014年第三季度财报");
            list.add(mPullBean);
        }
        return list;
    }
}
