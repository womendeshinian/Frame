package com.zey.toolbar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * 布局设置步骤：
 * 1.配置Gradle
 2.在AndroidManifest.xml设置Activity的主题
 3.重定义主题
 4.创建Toolbar的布局文件widget_layout.xml
 5.在Activity中引用

    ToolBar元素
        1.导航按钮
        2.Logo
        3.主标题和副标题
        4.动作菜单
        5.自定义View
 */




public class MainActivity extends AppCompatActivity {
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        //1.导航按钮
        //设置导航按钮
        toolbar.setNavigationIcon(R.drawable.ic_menu_send);
        //设置导航按钮监听事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"导航按钮",Toast.LENGTH_SHORT).show();
            }
        });
        //2.Logo
        //设置Logo
        toolbar.setLogo(R.mipmap.ic_launcher_round);
        //也可设置监听
        toolbar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });
        //3.主标题和副标题
            //主标题
                toolbar.setTitle("主标题");
                //主标题颜色
                toolbar.setTitleTextColor(Color.RED);
                //修改主标题的颜色外观
                toolbar.setTitleTextAppearance(this,R.style.Theme_ToolBar_Base_Subtitle);
                //副标题
                toolbar.setSubtitle("副标题");
                toolbar.setSubtitleTextColor(Color.RED);
                toolbar.setSubtitleTextAppearance(this,R.style.Theme_ToolBar_Base_Subtitle);
        //4.动作菜单
                //设置菜单弹出样式的文本颜色和背景
            toolbar.setPopupTheme(R.style.PopupMenu);
                //设置菜单
                    toolbar.inflateMenu(R.menu.menu_main);
                //设置菜单及其点击监听
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id=item.getItemId();
                switch(id){
                    case R.id.search:
                        break;
                }

                return true;
            }
        });
        //5.若干个自定义View

    }
}
