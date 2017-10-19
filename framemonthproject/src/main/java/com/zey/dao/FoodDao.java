package com.zey.dao;

import android.content.Context;
import android.database.Cursor;


import com.zey.helper.UserHelper;

import java.util.ArrayList;
import java.util.HashMap;


public class FoodDao {
    UserHelper helper;
   public FoodDao(Context c){
       helper=new UserHelper(c);
   }
   //添加菜名
    public void insertFoodName(String name){
        String sql="insert into love (name) values (?)";
        helper.db.execSQL(sql,new String[]{name});
    }
    //检测是否有重复
    public boolean selectFoodName(String name){
        boolean a=false;
        String sql="select * from love where name=?";
       Cursor cr= helper.db.rawQuery(sql,new String[]{name});
        if(cr.getCount()>0){
            a=true;
        }
        return  a;
    }
    //删除收藏
    public void deleteFoodName(String name){
        String sql="delete from love where name=?";
        helper.db.execSQL(sql,new String[]{name});
    }
    //查询收藏
    public ArrayList<String> selectFromLove(){
        ArrayList<String> alist=new ArrayList<>();
        String sql="select * from love";
        Cursor cr=helper.db.rawQuery(sql,null);
        cr.moveToFirst();
        while(cr.moveToNext()){
            String name=cr.getString(cr.getColumnIndex("name"));
            alist.add(name);
        }
        return alist;
    }
    //根据关键字查找
    public ArrayList<String> selectLoveByKey(String key){
        ArrayList<String> alist=new ArrayList<>();
        String sql="select * from love where name like ?";
        Cursor cr=helper.db.rawQuery(sql,new String[]{"%"+key+"%"});
        cr.moveToFirst();
        while(cr.moveToNext()){
            String name=cr.getString(cr.getColumnIndex("name"));
            alist.add(name);
        }
        return alist;
    }

    //添加进访问历史
    public void insertAskedName(String name,String time){
        String sql="insert into recordthree (name,date) values (?,?)";
        helper.db.execSQL(sql,new String[]{name,time});
    }

    //查看访问历史
    public ArrayList<HashMap<String,String>> selectAllFromAsked(){
        ArrayList<HashMap<String,String>> alist=new ArrayList<>();
        String sql="select * from recordthree";
        Cursor cr=helper.db.rawQuery(sql,null);
        cr.moveToFirst();
        while(cr.moveToNext()){
            String name=cr.getString(cr.getColumnIndex("name"));
            String time=cr.getString(cr.getColumnIndex("date"));
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("name",name);
            hashMap.put("date",time);
            alist.add(hashMap);
        }
        return alist;
    }

    //检测访问历史是否有重复
    public boolean selectAskedName(String name){
        boolean b=false;
        String sql="select * from recordthree where name=?";
        Cursor cr= helper.db.rawQuery(sql,new String[]{name});
        if(cr.getCount()>0){
            b=true;
        }
        return  b;
    }
}
