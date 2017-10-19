package com.zey.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.zey.framemonthproject.R;

import java.util.ArrayList;


public class SecondActivity_Adapter extends BaseAdapter {
    Context c;
    ArrayList<String> alist;
    public SecondActivity_Adapter(Context c, ArrayList<String> alist){
        this.c=c;
        this.alist=alist;
    }
    @Override
    public int getCount() {
        return alist.size();
    }

    @Override
    public Object getItem(int i) {
        return alist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;
        MyHolder holder;
        if(view!=null){
            view1=view;
            holder= (MyHolder) view1.getTag();
        }else{
            view1=View.inflate(c, R.layout.love_adapter,null);
            holder=new MyHolder();
            holder.tvShoucang=view1.findViewById(R.id.tvShoucang);
            view1.setTag(holder);
        }
        holder.tvShoucang.setText(alist.get(i));
        return view1;
    }
    class MyHolder{
        TextView tvShoucang;
    }
    public void updateAdapter( ArrayList<String> alist){
        this.alist=alist;
        notifyDataSetChanged();
    }
}
