package com.zey.pullrefreshpractice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 赵二盈 on 2017/9/22.
 */

public class MyAdapter extends BaseAdapter {
    Context c;
    ArrayList<String> alist;
    public MyAdapter(Context c, ArrayList<String> alist){
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
        view=View.inflate(c,R.layout.adapter,null);
        TextView tv=view.findViewById(R.id.textView);
        tv.setText(alist.get(i));
        return view;
    }
}
