package com.zey.helper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserHelper extends SQLiteOpenHelper {
    //创建一个全局的数据库操作对象
    public SQLiteDatabase db=getWritableDatabase();
    public UserHelper(Context context) {
        super(context, "caipu.db", null, 25);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table user (_id integer primary key autoincrement,name text,tel text,pwd text,pic text)";
        db.execSQL(sql);
        String sql1="create table love (_id integer primary key autoincrement,name,text)";
        db.execSQL(sql1);
        String sql2="create table record(_id integer primary key autoincrement,name,text,date text)";
        db.execSQL(sql2);
        String sql3="create table recordtwo(_id integer primary key autoincrement,name,text,date text)";
        db.execSQL(sql3);
        String sql4="create table recordthree(_id integer primary key autoincrement,name text,date text)";
        db.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
