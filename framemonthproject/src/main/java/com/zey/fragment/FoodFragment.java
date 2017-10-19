package com.zey.fragment;
import android.Manifest;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zey.adapter.FoodFragmentAdapter_listView;
import com.zey.bean.FoodBean;
import com.zey.dao.FoodDao;
import com.zey.framemonthproject.DetailActivity;
import com.zey.framemonthproject.R;
import com.zey.utils.DateUtils;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class FoodFragment extends Fragment {
    TextView tvName,tvIntroduce,tvData,tvStep;

    ListView listView;
   FoodBean foodBean;
    FoodFragmentAdapter_listView adapter;

    FoodDao foodDao;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View view=View.inflate(getActivity(), R.layout.foodfragment,null);
        setPermissions();
        listView=view.findViewById(R.id.listView);
        new Thread(){
            @Override
            public void run() {
                getData("http://apicloud.mob.com/v1/cook/menu/search?"
                        + "page=1&name=%E7%BA%A2%E7%83%A7&cid=0010001007&key=520520test&size=20");
            }
        }.start();


        return view;
    }



    //用OkHttp的同步机制联网
    public void getData(String path) {
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient=new OkHttpClient();
        //2.创建Request.Builder
      Request.Builder builder=new Request.Builder();
        //3.通过内部类Builder对象创建内部类Request对象
        Request request=builder.get().url(path).build();
        //4.创建执行对象Call
        Call call=okHttpClient.newCall(request);
        //5.执行联网 获取数据并处理 同步联网方式
        try {
            Response response=call.execute();
            String data=response.body().string();
            Gson gson=new Gson();
            foodBean=gson.fromJson(data,FoodBean.class);
            getActivity().runOnUiThread(new Thread(){
                @Override
                public void run() {
                    adapter = new FoodFragmentAdapter_listView(getActivity(), foodBean);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String subTitle=foodBean.getResult().getList().get(i).getCtgTitles();
                            String title=foodBean.getResult().getList().get(i).getName();
                            foodDao=new FoodDao(getActivity());
                            if(!foodDao.selectAskedName(title)){
                                System.out.println(title+"^^^^^^^^^^^^^^^^");
                                String time= DateUtils.GetCurrentDate();
                                System.out.println(time+"^^^^^^^^^^^^^^^^^");
                                foodDao.insertAskedName(title,time);
                                Toast.makeText(getActivity(),"添加~",Toast.LENGTH_SHORT).show();

                            }
                            //带点击条目的索引参数 跳转到详细页面
                            Intent intent=new Intent(getActivity(), DetailActivity.class);
                            String step=foodBean.getResult().getList().get(i).getRecipe().getMethod();
                            String cailiao=foodBean.getResult().getList().get(i).getRecipe().getIngredients();
                            String pic=foodBean.getResult().getList().get(i).getThumbnail();
                            intent.putExtra("step",step);
                            intent.putExtra("cialiao",cailiao);
                            intent.putExtra("title",title);
                            intent.putExtra("subTitle",subTitle);
                            intent.putExtra("pic",pic);
                            startActivity(intent);

                            }



                    });
                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                            AlertDialog.Builder builder1= new AlertDialog.Builder(getActivity());
                            final String title1=foodBean.getResult().getList().get(i).getName();
                            System.out.println(title1+"((((((((((((((((");
                            builder1.setTitle(title1);
                            builder1.setMessage("是否收藏");
                            builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //判断数据库中是否已经被收藏
                                    foodDao=new FoodDao(getActivity());
                                    if(foodDao.selectFoodName(title1)){
                                        Toast.makeText(getActivity(),"您早已收藏啦~",Toast.LENGTH_SHORT).show();
                                    }else{

                                        foodDao.insertFoodName(title1);
                                        Toast.makeText(getActivity(),"感谢您的喜欢~",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                            builder1.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getActivity(),"好的",Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder1.show();
                            return true;

                        }

                    });

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //6.0以后要在代码中动态添加权限
    private void setPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//      Log.d("data", "权限申请");
            Toast.makeText(getActivity(), "申请权限", Toast.LENGTH_SHORT).show();
            //Android 6.0申请权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 1) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "写入申请成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "写入申请失败", Toast.LENGTH_SHORT).show();
            }

        }
    }


}
