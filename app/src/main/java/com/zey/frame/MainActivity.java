package com.zey.frame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import org.xutils.x;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.Ext.init(getApplication());

    }
}
