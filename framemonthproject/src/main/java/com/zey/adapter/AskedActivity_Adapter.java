package com.zey.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zey.framemonthproject.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 赵二盈 on 2017/10/13.
 */

public class AskedActivity_Adapter extends BaseAdapter {
    Context c;
    ArrayList<HashMap<String,String>> alist;
    public AskedActivity_Adapter(Context c, ArrayList<HashMap<String,String>>alist){
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
            view1=View.inflate(c, R.layout.asked_adapter,null);
            holder=new MyHolder();
            holder.tvAsked=view1.findViewById(R.id.tvAsked);
            holder.tvTime=view1.findViewById(R.id.tvTime);
            view1.setTag(holder);
        }
        holder.tvAsked.setText(alist.get(i).get("name"));
        holder.tvTime.setText(alist.get(i).get("date"));
        return view1;
    }
    class MyHolder{
        TextView tvAsked,tvTime;
    }
//    public void updateAdapter( ArrayList<String> alist){
//        this.alist=alist;
//        notifyDataSetChanged();
//    }
}
