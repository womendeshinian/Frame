package com.zey.com.zey.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.zey.bean.NewsBean;
import com.zey.monthtext.R;

/**
 * Created by 赵二盈 on 2017/10/17.
 */

public class MyAdapter extends BaseAdapter {
    Context c;
    NewsBean newsBean;
    ImageView iv;
    TextView tv;
    DisplayImageOptions options;
    public MyAdapter(Context c, NewsBean newsBean){
        this.c=c;
        this.newsBean=newsBean;
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)     //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.mipmap.ic_launcher)             //加载图片出现问题，会显示该图片
                .cacheInMemory()                                  //缓存用
                .cacheOnDisc()                                   //缓存用
                .displayer(new RoundedBitmapDisplayer(10))       //图片圆角显示，值为整数
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                c)
                .memoryCacheExtraOptions(480, 800)// 缓存在内存的图片的宽和高度
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(2 * 1024 * 1024) //缓存到内存的最大数据
                .discCacheSize(50 * 1024 * 1024).  //缓存到文件的最大数据
                discCacheFileCount(1000)            //文件数量
                .defaultDisplayImageOptions(options).  //上面的options对象，一些属性配置
                build();
        ImageLoader.getInstance().init(config); //初始化
    }
    @Override
    public int getCount() {
        return newsBean.getResult().getData().size();
    }

    @Override
    public Object getItem(int i) {
        return newsBean.getResult().getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=View.inflate(c, R.layout.adapter,null);
        iv=view.findViewById(R.id.imageView);
        tv=view.findViewById(R.id.textView);
        tv.setText(newsBean.getResult().getData().get(i).getTitle());
        ImageLoader.getInstance().displayImage(
                newsBean.getResult().getData().get(i).getThumbnail_pic_s(),
                iv,options);
        return view;
    }
}
