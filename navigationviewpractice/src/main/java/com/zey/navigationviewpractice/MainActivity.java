package com.zey.navigationviewpractice;

import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Button btn,btn2;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"我是第一个按钮",Toast.LENGTH_SHORT).show();
                navigationView= (NavigationView) findViewById(R.id.nav_view);
                //获取头部的控件
                View headerView = navigationView.getHeaderView(0);
                //通过headView中的findViewById方法来查找头部控件
                btn=headerView.findViewById(R.id.button);
                btn2=headerView.findViewById(R.id.button2);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"我是第一个按钮",Toast.LENGTH_SHORT).show();
                    }
                });
                drawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
                //item 点击关注
                // 通过setNavigationItemSelectedListenster方法
                //来设置菜单条目的点击，里面重写 onNavigationSelectedListenster
                //方法提供了被选中的MenuItem
                if(navigationView!=null){
                    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            int id=item.getItemId();
                            switch(id){
                                case R.id.nav_home:
                                    Toast.makeText(MainActivity.this,"nav_home",Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.nav_friends:
                                    break;
                                case R.id.nav_discussion:
                                    break;
                                case R.id.nav_view:
                                    break;
                                case R.id.nav_messages:
                                    break;
                            }
                            item.setChecked(true);
                            drawerLayout.closeDrawers();
                            return false;
                        }
                    });
                }

            }
        });




    }
}
