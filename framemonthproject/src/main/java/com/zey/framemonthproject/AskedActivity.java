package com.zey.framemonthproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.zey.adapter.AskedActivity_Adapter;
import com.zey.dao.FoodDao;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 赵二盈 on 2017/10/13.
 */

public class AskedActivity extends Activity {
    ListView listView4;
    ImageView back;
    ArrayList<HashMap<String,String>> alist=new ArrayList<>();
    FoodDao foodDao;
    AskedActivity_Adapter adapter;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.askedactivity_items);
        init();
    }
    public void init(){
        listView4=findViewById(R.id.listView4);
        back=findViewById(R.id.back);
        foodDao=new FoodDao(AskedActivity.this);
        alist=foodDao.selectAllFromAsked();
        System.out.println(alist+"))))))))))))))))))");
        adapter=new AskedActivity_Adapter(AskedActivity.this,alist);
        listView4.setAdapter(adapter);
        back.setImageResource(R.drawable.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AskedActivity.this,NavSetActivity_SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
