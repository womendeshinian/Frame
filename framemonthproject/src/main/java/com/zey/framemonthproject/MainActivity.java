package com.zey.framemonthproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
ImageView iv,iv2,iv3,iv4,iv5;
    TranslateAnimation translate,translate2,translate3
            ,translate4;
    AnimationSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    private void init() {
//       iv= (ImageView) findViewById(R.id.imageView);
        iv= (ImageView) findViewById(R.id.imageView);
        iv2= (ImageView) findViewById(R.id.imageView2);
        iv3= (ImageView) findViewById(R.id.imageView3);
        iv4= (ImageView) findViewById(R.id.imageView4);
        iv5= (ImageView) findViewById(R.id.imageView5);


       translate= (TranslateAnimation) AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate_anim);
        iv.setImageResource(R.drawable.one);
        iv.startAnimation(translate);

        translate2= (TranslateAnimation) AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate1);
        iv2.setImageResource(R.drawable.two);
        iv2.startAnimation(translate2);

        translate3= (TranslateAnimation) AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate2);
        iv3.setImageResource(R.drawable.three);
        iv3.startAnimation(translate3);

        translate4= (TranslateAnimation) AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate3);
        iv4.setImageResource(R.drawable.four);
        iv4.startAnimation(translate4);

        set= (AnimationSet) AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate4);
        iv5.setImageResource(R.drawable.eight);
        iv5.startAnimation(set);




    }
}
