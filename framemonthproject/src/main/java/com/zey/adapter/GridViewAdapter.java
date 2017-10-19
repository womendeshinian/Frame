package com.zey.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zey.framemonthproject.R;


public class GridViewAdapter extends BaseAdapter {
    Context c;
    int[] img;
    public GridViewAdapter(Context c, int[] img){
        this.c=c;
        this.img=img;
    }
    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       view=View.inflate(c, R.layout.gridview_adapter,null);
        ImageView iv=view.findViewById(R.id.imageView8);
        iv.setImageResource(img[i]);
        return view;
    }
}
