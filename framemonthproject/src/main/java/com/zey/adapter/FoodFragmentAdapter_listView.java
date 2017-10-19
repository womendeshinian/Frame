package com.zey.adapter;

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
import com.zey.bean.FoodBean;
import com.zey.framemonthproject.R;


public class FoodFragmentAdapter_listView extends BaseAdapter {
    Context c;
    FoodBean foodBean;
    DisplayImageOptions options;
    public FoodFragmentAdapter_listView(Context c, FoodBean foodBean){
            this.c=c;
            this.foodBean=foodBean;
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
        return foodBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int i) {
        return foodBean.getResult().getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view1;
        final MyHolder holder;
        if(view!=null){
            view1=view;
            holder= (MyHolder) view1.getTag();
        }else{
            holder=new MyHolder();
            view1=View.inflate(c, R.layout.zilayout_listview,null);
            holder.iv=view1.findViewById(R.id.imageView6);
            holder.tvRange=view1.findViewById(R.id.textView10);
            holder.tvName=view1.findViewById(R.id.textView11);
            holder.tvProblem=view1.findViewById(R.id.textView12);
            view1.setTag(holder);
        }
        //将控件重新赋值
        holder.tvRange.setText( foodBean.getResult().getList().get(i).getCtgTitles());
        holder.tvName.setText(foodBean.getResult().getList().get(i).getName());
        holder.tvProblem.setText(foodBean.getResult().getList().get(i).getRecipe().getTitle());
        ImageLoader.getInstance().displayImage(
                foodBean.getResult().getList().get(i).getThumbnail(),
                holder.iv,options);

        return view1;
    }
    class MyHolder{
        ImageView iv;
        TextView tvRange,tvName,tvProblem;
    }
    public void updateAdapter(FoodBean foodBean){
        this.foodBean=foodBean;
        notifyDataSetChanged();
    }
}
