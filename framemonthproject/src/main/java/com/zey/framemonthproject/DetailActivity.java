package com.zey.framemonthproject;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.zey.bean.FoodBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 赵二盈 on 2017/10/11.
 */

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tvStep,tvCailiao;
    ImageView ivDownload;
    DisplayImageOptions options;
    String content="如下"+"\n";
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_detailactivity);
        init();
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)     //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.mipmap.ic_launcher)             //加载图片出现问题，会显示该图片
                .cacheInMemory()                                  //缓存用
                .cacheOnDisc()                                   //缓存用
                .displayer(new RoundedBitmapDisplayer(10))       //图片圆角显示，值为整数
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                DetailActivity.this)
                .memoryCacheExtraOptions(480, 800)// 缓存在内存的图片的宽和高度
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(2 * 1024 * 1024) //缓存到内存的最大数据
                .discCacheSize(50 * 1024 * 1024).  //缓存到文件的最大数据
                discCacheFileCount(1000)            //文件数量
                .defaultDisplayImageOptions(options).  //上面的options对象，一些属性配置
                build();
        ImageLoader.getInstance().init(config); //初始化

    }




    public void init(){
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        String subTitle=intent.getStringExtra("subTitle");
        String  step=intent.getStringExtra("step");
        String cailiao=intent.getStringExtra("cailiao");
        String pic=intent.getStringExtra("pic");
        toolbar= (Toolbar) findViewById(R.id.toolbars);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //此处写返回跳转页面
            }
        });
        //加载图片  （设置Logo）
        toolbar.setLogo(R.drawable.eight);
        //设置主标题
        toolbar.setTitle(title);
        //副标题
        toolbar.setSubtitle(subTitle);
        //设置菜单弹出样式的文本及颜色
        toolbar.setPopupTheme(R.style.PopupMenu);
        //设置菜单
        toolbar.inflateMenu(R.menu.menu_main);
        //设置菜单的点击监听
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
            int id=item.getItemId();
                switch(id){
                    case R.id.action_item2:
                        Intent intent1=new Intent(DetailActivity.this,WebviewActivity.class);
                        startActivity(intent1);
                        break;
                }
                return true;
            }
        });

        tvStep= (TextView) findViewById(R.id.tvStep);

        try {
            JSONArray jsonArray=new JSONArray(step);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject= (JSONObject) jsonArray.get(i);
               String stepone=jsonObject.getString("step");
                System.out.println(stepone+"^^^^^^^^^^^^^&");
                content+=stepone+"\n";
                tvStep.setText(content);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        tvCailiao= (TextView) findViewById(R.id.tvCailiao);
//        tvCailiao.setText(cailiao);
        ivDownload= (ImageView) findViewById(R.id.ivDownload);
        ImageLoader.getInstance().displayImage(
               pic,ivDownload,options);
    }
}
