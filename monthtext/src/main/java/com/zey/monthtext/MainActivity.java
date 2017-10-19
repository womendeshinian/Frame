package com.zey.monthtext;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zey.bean.NewsBean;
import com.zey.com.zey.adapter.MyAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
ListView listView;
    MyAdapter adapter;
    NewsBean newsBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        new Thread(){
            @Override
            public void run() {
                getData();
            }
        }.start();
        setPermissions();
    }




    public void getData(){
        //OkHttp
        OkHttpClient okHttpClient=new OkHttpClient();
        Request.Builder builder=new Request.Builder();
        Request request=builder.get().url("http://v.juhe.cn/toutiao/index?type=top&key=562402375cb93590c7eec9ade024dffe").build();
        Call call=okHttpClient.newCall(request);
        try {
            Response response=call.execute();
            String data=response.body().string();
            System.out.println(data+"***************");
            Gson gson=new Gson();
            newsBean=gson.fromJson(data,NewsBean.class);
            System.out.println(newsBean+"***************");
            runOnUiThread(new Thread(){
                @Override
                public void run() {
                    adapter=new MyAdapter(MainActivity.this,newsBean);
                    listView.setAdapter(adapter);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6.0以后要在代码中动态添加权限
    private void setPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//      Log.d("data", "权限申请");
            Toast.makeText(MainActivity.this, "申请权限", Toast.LENGTH_SHORT).show();
            //Android 6.0申请权限
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 1) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "写入申请成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "写入申请失败", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
