package com.zey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.zey.bean.PullBean;
import com.zey.pulltorefreshdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbo on 2017/1/9.
 */

public class MyAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<PullBean> data=new ArrayList<PullBean>();
    public MyAdapter(Context context,List<PullBean> list) {
        mInflater=LayoutInflater.from(context);
        data=list;
    }
    public void addFirst(PullBean bean){
        data.add(0,bean);
    }
    public void addLast(PullBean bean){
        data.add(bean);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder{
        private TextView title;
        private TextView content;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null){
            viewHolder=new ViewHolder();
            view=mInflater.inflate(R.layout.item,null);
            viewHolder.title= (TextView) view.findViewById(R.id.title);
            viewHolder.content= (TextView) view.findViewById(R.id.content);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(data.get(i).getTitle());
        viewHolder.content.setText(data.get(i).getContent());
        return view;
    }

}
