package com.zey.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.zey.bean.UserBean;
import com.zey.helper.UserHelper;


public class UserDao {
    UserHelper helper;
   public UserDao(Context c){
       helper=new UserHelper(c);
   }

   //注册 （添加）
    public void registerUser(String name,String tel,String pwd){
        String sql="insert into user (name,tel,pwd) values (?,?,?) ";
        helper.db.execSQL(sql,new String[]{name,tel,pwd});
    }
    //通过手机号判断用户是否已存在
    public UserBean findUserByTel(String tel){
        String sql="select * from user where tel =?";
        Cursor cursor=helper.db.rawQuery(sql,new String[]{tel});
        UserBean u=null;
        if(cursor.getCount()>0){
          cursor.moveToFirst();
            u=new UserBean();
            u.setName(cursor.getString(cursor.getColumnIndex("name")
            ));
            u.setTel(cursor.getString(cursor.getColumnIndex("tel")));
            u.setPwd(cursor.getString(cursor.getColumnIndex("pwd")));
        }
        return u;
    }
    //根据手机号 添加图片
    public void insertPicByTel(String path,String pic){
        String  sql="update user set pic=? where tel =?";
        Log.d("tel",".........刷新");
        helper.db.execSQL(sql,new String[]{path,pic});
    }


}
