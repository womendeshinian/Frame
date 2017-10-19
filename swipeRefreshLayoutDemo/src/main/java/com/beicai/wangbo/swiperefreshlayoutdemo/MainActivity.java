package com.beicai.wangbo.swiperefreshlayoutdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_TextView;
    private Button btn_listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        btn_TextView = (Button) findViewById(R.id.btn_textView);
        btn_listView = (Button) findViewById(R.id.btn_listView);
        btn_TextView.setOnClickListener(this);
        btn_listView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_textView:
                intent = new Intent(getApplication(),TextViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_listView:
                intent = new Intent(getApplication(),ListViewActivity.class);
                startActivity(intent);
                break;
        }
    }
}
