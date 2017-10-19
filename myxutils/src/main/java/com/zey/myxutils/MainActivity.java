package com.zey.myxutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * 注解
 * 添加监听  @Event  添加点击事件，方法声明必须为private
 */
//加载主步局
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    //控件的查找
    @ViewInject(R.id.button)
    Button btn1;
    @ViewInject(R.id.button2)
    Button btn2;
    Callback.Cancelable cancelable;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不需用了
//        setContentView(R.layout.activity_main);
        //让Activity识别注解
        x.view().inject(this);
    }
    //绑定短按监听  type可以省略
    @Event(type= View.OnClickListener.class,value=R.id.button)
    private void onClickListenster(View v){
        Toast.makeText(this,"我是button的短按监听",Toast.LENGTH_SHORT).show();
        String url="http://v.juhe.cn/toutiao/index";
        RequestParams params=new RequestParams(url);
        params.addQueryStringParameter("type","");
        params.addQueryStringParameter("key","2ca3a5b1cb6edf55250bff550ac34325");
        cancelable=x.http().get(params,new Callback.CommonCallback<String>(){

            @Override
            public void onSuccess(String result) {
                Log.d("data",result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                btn2.setText("获取结束");

            }
        });

    }
    @Event(type=View.OnLongClickListener.class,value=R.id.button2)
    private boolean OnLongClickListenster(View v){
        Toast.makeText(this,"我是button2的长按监听",Toast.LENGTH_SHORT).show();
        return true;
    }
}
