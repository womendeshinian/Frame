package com.zey.swiperefreshlayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 赵二盈 on 2017/9/21.
 */

public class MyAdapter extends BaseAdapter {
    ArrayList<String> alist;
    Context c;
    public MyAdapter(ArrayList<String> alist,Context c){
        this.alist=alist;
        this.c=c;
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
            view1=View.inflate(c,R.layout.adapter,null);
            holder=new MyHolder();
            holder.tv=view1.findViewById(R.id.textView);
            view1.setTag(holder);
        }
        holder.tv.setText(alist.get(i));
        return view1;
    }
    class MyHolder{
        TextView tv;
    }
    public void updateAdapter(ArrayList<String> alist){
        this.alist=alist;
        notifyDataSetChanged();
    }
}
