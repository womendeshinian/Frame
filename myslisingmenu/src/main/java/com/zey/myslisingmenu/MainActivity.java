package com.zey.myslisingmenu;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;

import com.zey.lib.SlidingMenu;

/**
 * SlidingMenu实现侧滑
 * 第一种：在Activity中通过SlidingMenu构造方法，直接设置侧滑菜单
 *              1.获取SlidingMenu对象
 *              2.设置触摸屏幕的模式
 *              3.然后设置侧滑菜单的布局
 *  第二种：
 *  见SecondActivity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SlidingMenu menu=new SlidingMenu(this);
        //设置菜单滑动方向
        menu.setMode(SlidingMenu.LEFT);
        //侧滑第一页面
        //设置阴影图片的宽度和资源
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);

        /**动态获取手机屏幕的宽度
         *
         */
        WindowManager windowManager= (WindowManager) getSystemService(WINDOW_SERVICE);
        //获取显示对象
        Display display=windowManager.getDefaultDisplay();
        //创建属性对象
        DisplayMetrics displayMetrics=new DisplayMetrics();
        //属性对象传给显示对象  获取显示对象的属性
        display.getMetrics(displayMetrics);
        //设置菜单的宽
        menu.setBehindWidth(displayMetrics.widthPixels/3*2);

        //设置菜单占屏幕的比例
//        menu.setBehindOffset(R.dimen.slidingmenu_offset);
//        设置阴影的图片资源
        menu.setShadowDrawable(R.color.colorPrimary);
//        设置阴影图片的宽度
        menu.setShadowWidthRes(R.dimen.shadow_width);
        //设置滑动菜单时是否淡入淡出
        menu.setFadeEnabled(true);
//设置淡入淡出的比例
        menu.setFadeDegree(1.0f);
        //设置滑动时拖拽效果  ?????????????
        menu.setBehindScrollScale(1);

        //使SlidingMenu俯角在Activity上
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.sliding);

        //添加第二个侧滑页
        menu.setSecondaryMenu(R.layout.right_behind);
        menu.showContent();
        //设置侧滑栏显示动画
        menu.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer(){

            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
                //上升动画
//                canvas.translate(0,canvas.getHeight()*(1-interp.getInterpolation(percentOpen)));
            }
        });




        //显示内容
        menu.showContent();
        menu.showMenu();
        Button btn= (Button) findViewById(R.id.button6);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //动态判断自动关闭或开启侧滑页
                menu.toggle();
            }
        });
        TextView tv= (TextView) findViewById(R.id.tvClick);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.toggle();
            }
        });

       /* //设置SlidingMenu划出时主页面显示的剩余宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //设置滑动有效范围
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
     //为滑动菜单设置布局
        menu.setMenu(R.layout.behind);
        //支持右侧划出菜单
        menu.setSecondaryMenu(R.layout.right_behind);
        //设置右侧菜单阴影的图片资源
        menu.setSecondaryShadowDrawable(R.drawable.btn_style_zero_focused);
        //右侧SlidingMenu的Fragment
//        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame2, new SampleListFragment()).commit();
        //设置滑动方式 ，右滑，或左右滑
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        //设置阴影宽度
        menu.setShadowWidth(getWindowManager().getDefaultDisplay().getWidth()/40);
        //设置菜单占屏幕的比例
        menu.setBehindOffset(getWindowManager().getDefaultDisplay().getWidth()/5);
        //设置滑动时菜单是否淡入淡出
        menu.setFadeEnabled(true);
        //设置淡入淡出的比例
        menu.setFadeDegree(0.4f);
        //设置滑动时拖拽效果
        menu.setBehindScrollScale(0);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
*/


    }




}
