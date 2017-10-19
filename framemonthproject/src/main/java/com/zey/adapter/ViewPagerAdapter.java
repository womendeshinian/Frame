package com.zey.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


public class ViewPagerAdapter extends PagerAdapter {
    Context c;
    ArrayList<ImageView> alist;
    public ViewPagerAdapter(Context c, ArrayList<ImageView> alist){
            this.c=c;
        this.alist=alist;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view==o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(alist.get(position%alist.size()));
        return alist.get(position%alist.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(alist.get(position%alist.size()));
    }
}
