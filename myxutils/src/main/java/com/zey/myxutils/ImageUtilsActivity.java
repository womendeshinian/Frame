package com.zey.myxutils;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import org.xutils.common.Callback.CacheCallback;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by wangye on 2017/9/26.
 */
@ContentView(R.layout.imageutils)
public class ImageUtilsActivity extends AppCompatActivity {

  @ViewInject(R.id.btImage)
  Button button;
  @ViewInject(R.id.imageUtils)
  ImageView imageView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    x.view().inject(this);
  }

  @Event(value = R.id.btImage)
  private void myClick(View v) {
    ImageOptions imageOptions = new ImageOptions.Builder()
        .setFadeIn(true)
        .build();
    String url = "http:\\/\\/05.imgmini.eastday.com\\/mobile\\/20170926\\/20170926093728_73bda069e855e0a05472a99c450faabb_1_mwpm_03200403.jpg";
//    x.image().bind(imageView, url, imageOptions);
//    x.image().loadDrawable(url, imageOptions, new CommonCallback<Drawable>() {
//      @Override
//      public void onSuccess(Drawable result) {
//        imageView.setImageDrawable(result);
//      }
//      @Override
//      public void onError(Throwable ex, boolean isOnCallback) {
//      }
//      @Override
//      public void onCancelled(CancelledException cex) {
//      }
//      @Override
//      public void onFinished() {
//      }
//    });
    x.image().loadFile(url, imageOptions, new CacheCallback<File>() {
      @Override
      public boolean onCache(File result) {
        return false;
      }

      @Override
      public void onSuccess(File result) {
        Toast.makeText(ImageUtilsActivity.this, "成功:"+result.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        //加载SDCard或者指定路径下的文件
        imageView.setImageBitmap(BitmapFactory.decodeFile(result.getAbsolutePath()));
      }

      @Override
      public void onError(Throwable ex, boolean isOnCallback) {
        Toast.makeText(ImageUtilsActivity.this, "失败:"+ex.toString(), Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onCancelled(CancelledException cex) {

      }

      @Override
      public void onFinished() {

      }
    });
  }
}
