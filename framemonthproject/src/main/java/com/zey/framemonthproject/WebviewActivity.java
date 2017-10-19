package com.zey.framemonthproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

/**
 * Created by 赵二盈 on 2017/10/14.
 */

public class WebviewActivity extends Activity {
    WebView webViewone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webViewone=findViewById(R.id.webViewone);
        webViewone.loadUrl("https://www.xinshipu.com/");

    }
}
