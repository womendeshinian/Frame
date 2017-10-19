package com.zey.framemonthproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zey.adapter.SearchActivity_Adapter;
import com.zey.bean.FoodBean;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 赵二盈 on 2017/10/13.
 */

public class SearchActivity extends Activity {
    EditText etSearch;
    TextView tvLeibie, tvSearchName;
    Button btnSearch;
    ListView listView5;
    SearchActivity_Adapter adapter;
    FoodBean foodBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchactivity_listview);
        listView5=findViewById(R.id.listView5);

        tvLeibie = findViewById(R.id.tvLeibie);
        tvSearchName = findViewById(R.id.tvSearchName);
        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = etSearch.getText().toString();
                try {
                    final String code = URLEncoder.encode(search, "utf-8");
                    if(code==null){
                        Toast.makeText(SearchActivity.this,"默认推荐养生类菜谱",Toast.LENGTH_SHORT).show();
                    }
                    System.out.println(code + "11111111111111111111111111");
                    new Thread(){
                        @Override
                        public void run() {
                            getData(code);
                        }
                    }.start();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    //用OkHttp的同步机制联网
    public void getData(String code) {
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request.Builder
        Request.Builder builder = new Request.Builder();
        //3.通过内部类Builder对象创建内部类Request对象
        Request request = builder.get().url("http://apicloud.mob.com/v1/cook/menu/search?"
                + "page=1&name=" + code + "&key=520520test&size=20").build();
        //4.创建执行对象Call
        Call call = okHttpClient.newCall(request);
        //5.执行联网 获取数据并处理 同步联网方式
        try {
            Response response  = call.execute();
            String data = response.body().string();
            Log.d("data", data);
            Gson gson = new Gson();
            foodBean = gson.fromJson(data, FoodBean.class);
            runOnUiThread(new Thread(){
                @Override
                public void run() {
                    adapter=new SearchActivity_Adapter(SearchActivity.this,foodBean);
                    listView5.setAdapter(adapter);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}