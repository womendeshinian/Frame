package com.zey.utils;

import android.content.Context;
import android.widget.ImageView;


import com.zey.framemonthproject.R;

import java.util.ArrayList;


public class ViewPagerData {

    public static ArrayList<ImageView>  data(Context c){
        int[] imgs=new int[]{
                R.drawable.five,
                R.drawable.six,
                R.drawable.seven,
                R.drawable.eleven,
                R.drawable.twlven,
                R.drawable.thiren,

                R.drawable.nine,
                R.drawable.fourteen,
        };

         ArrayList<ImageView> alist=new ArrayList<>();
        for(int i=0;i<imgs.length;i++){
            //适应图片原本的宽高
//            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageView iv=new ImageView(c);
            iv.setImageResource(imgs[i]);
            alist.add(iv);
        }
        return  alist;
    }

}
