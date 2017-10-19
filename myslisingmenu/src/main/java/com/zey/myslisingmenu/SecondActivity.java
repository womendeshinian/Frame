package com.zey.myslisingmenu;

import android.app.Activity;
import android.os.Bundle;

import com.zey.lib.SlidingMenu;
import com.zey.lib.app.SlidingActivity;

/**
 * Created by 赵二盈 on 2017/9/20.
 *
 * 通过继承SlidingActivity
 *      1.继承SlidingActivity
 *      2.在onCreate中setBehindContentView(布局)
 *          设置侧滑菜单的布局
 *       3.通过getSlidingMenu()得到SlidingMenu对象，然后设置样式
 *
 */

public class SecondActivity extends SlidingActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.behind);
        SlidingMenu menu=getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);
        //设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        //设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
    }
}
