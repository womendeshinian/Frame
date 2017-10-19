package com.zey.okhttp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnGet,btnPost,btnDown;
    TextView tv;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setPermissions();
    }

    public void init() {
        btnGet= (Button) findViewById(R.id.button);
        btnGet.setOnClickListener(this);
        btnDown= (Button) findViewById(R.id.button2);
        btnDown.setOnClickListener(this);
        btnPost= (Button) findViewById(R.id.button3);
        btnPost.setOnClickListener(this);
        tv= (TextView) findViewById(R.id.textView);
        iv= (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button:
                new Thread(){
                    @Override
                    public void run() {
                        doGet();
                    }
                }.start();
                break;
            case R.id.button2:
                new Thread(){
                    @Override
                    public void run() {
//                        doPost();
                        doPostString();
                    }
                }.start();
                break;
            case R.id.button3:
                        doDown();
                break;
        }
    }
    /**
     * 下载
     */
    public void doDown(){
        //1.创建OKHttpClient对象
        OkHttpClient okHttpClient=new OkHttpClient();
        //2.创建builder对象
        Request.Builder builder=new Request.Builder();
        //3.通过builder对象创建request对象
        Request  request=builder.get().url("http://08.imgmini.eastday.com//mobile//20170925//20170925150134_77ae353e7f15f1fecc294dec794238b3_3_mwpm_03200403.jpg").build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //下载文件
                //创建输入流对象 获取File内容
//                InputStream inputStream=response.body().byteStream();
//                File file=new File(Environment.getExternalStorageDirectory()+"/myimg.jpg");
//                FileOutputStream os=new FileOutputStream(file);
//                int length=0;
//                byte buffer[]=new byte[600];
//                while((length=inputStream.read(buffer))!=-1){
//                    os.write(buffer,0,length);
//                    os.flush();
//                }
//                inputStream.close();
//                os.close();
                //显示从网络上读取的图片
                InputStream inputStream=response.body().byteStream();
                final Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                runOnUiThread(new Thread(){
                    @Override
                    public void run() {
                        iv.setImageBitmap(bitmap);
                    }
                });
            }
        });

    }



    /**
     * 用Post传递String给服务器
     */
    public void doPostString(){
        //相比较post,构造RequestBody的方法有变化
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody requestBody=RequestBody.create(MediaType.parse("text/xml;charset=utf-8"),"");
        //3.构造Request
        Request.Builder builder=new Request.Builder();
        Request request=builder.post(requestBody).url("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx/getDatabaseInfo?").build();
        //4.创建Call执行对象
        Call call=okHttpClient.newCall(request);
        try {
            Response response=call.execute();
            final String data=response.body().string();
            runOnUiThread(new Thread(){
                @Override
                public void run() {
                    tv.setText(data);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 用Post传递键值对
     */
    public void doPost(){
        //1.创建OKHttpClient对象
        OkHttpClient okHttpClient=new OkHttpClient();
        //2.创建封装请求参数的RequestBody对象 （此处和Get方式对比）
        FormBody.Builder builder=new FormBody.Builder();
        RequestBody requestBody=builder.add("type","")
                .add("key","2ca3a5b1cb6edf55250bff550ac34325").build();
        //3.创建Request对象
        Request.Builder builder1=new Request.Builder();
        Request request=builder1.post(requestBody).url("http://v.juhe.cn/toutiao/index").build();
        //4. 创建Call执行对象
        Call call=okHttpClient.newCall(request);
        //5.  执行联网
                //同步
        try {
            Response response=call.execute();
            final String data=response.body().string();
            runOnUiThread(new Thread(){
                @Override
                public void run() {
                   tv.setText(data);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public void doGet(){
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClick=new OkHttpClient();
        //2.创建Request.Builder
        Request.Builder builder=new Request.Builder();
        //3.通过内部类Builder对象创建外部类Requst对象
        final Request request=builder.get().url("http://apicloud.mob.com/v1/cook/menu/search?"+
                "page=1&name=%E4%B8%9C%E5%8C%97%E8%8F%9C&cid=0010001007&key=520520test&size=20").build();
        //4.创建执行对象Call
        Call call=okHttpClick.newCall(request);
        //5.执行联网  获取数据并处理
            //同步联网方式
        try {
            Response response=call.execute();
            final String data=response.body().string();
            runOnUiThread(new  Thread(){
                @Override
                public void run() {
                    tv.setText(data);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
            //异步联网方式
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String data=response.body().string();
//                runOnUiThread(new Thread(){
//                    @Override
//                    public void run() {
//                        tv.setText("异步：\n"+data);
//                    }
//                });
//            }
//        });

    }
    //6.0以后要在代码中动态添加权限
    private void setPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//      Log.d("data", "权限申请");
            Toast.makeText(this, "申请权限", Toast.LENGTH_SHORT).show();
            //Android 6.0申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 1) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "写入申请成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "写入申请失败", Toast.LENGTH_SHORT).show();
            }

        }
    }

}

