package com.zey.framemonthproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.zey.adapter.SecondActivity_Adapter;
import com.zey.dao.FoodDao;

import java.util.ArrayList;


public class LoveActivity extends Activity{
    ListView listView3;
    SecondActivity_Adapter adapter;
    ArrayList<String> alist=new ArrayList<>();
    FoodDao foodDao;
    TextView tvEdit;
    ImageView ivBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.loveactivity_items);
        init();
    }

    private void init() {
        ivBack=findViewById(R.id.ivBack);
        ivBack.setImageResource(R.drawable.back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoveActivity.this,NavSetActivity_SecondActivity.class);
                startActivity(intent);
            }
        });
        tvEdit=findViewById(R.id.tvEdit);
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoveActivity.this,"长按即可删除",Toast.LENGTH_SHORT).show();
            }
        });
        listView3=findViewById(R.id.listView3);
        foodDao=new FoodDao(LoveActivity.this);
        alist=foodDao.selectFromLove();
        System.out.println(alist+"!!!!!!!!!!!!!!!!!!!!!!!!");
        adapter=new SecondActivity_Adapter(LoveActivity.this,alist);
        listView3.setAdapter(adapter);
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoveActivity.this);
                builder.setTitle(alist.get(i));
                builder.setMessage("是否到主网页查看");
                builder.setPositiveButton("好，去吧！", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(LoveActivity.this,"正在为您跳转",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoveActivity.this,NavSetActivity_SecondActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("下次再说，", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(LoveActivity.this,"听你的，就在该页面 ！",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        listView3.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                foodDao.deleteFoodName(alist.get(i));
                alist=foodDao.selectFromLove();
                adapter.updateAdapter(alist);
                Toast.makeText(LoveActivity.this,"该收藏已被删除",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
