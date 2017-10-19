package com.zey.myxutils;

import android.app.Application;

import org.xutils.x;


/**
 * Created by 赵二盈 on 2017/9/25.
 */

//继承Application的该类代表了当前项目里的所有初始化的东西
public class MyApp extends Application {
    public  int a=1;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        x.Ext.init(this);
        // 是否输出debug日志, 开启debug会影响性能.
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

}
