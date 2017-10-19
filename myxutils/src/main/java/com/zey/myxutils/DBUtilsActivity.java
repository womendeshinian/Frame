package com.zey.myxutils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import org.xutils.DbManager;
import org.xutils.DbManager.DaoConfig;
import org.xutils.DbManager.DbUpgradeListener;
import org.xutils.DbManager.TableCreateListener;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by wangye on 2017/9/26.
 */
@ContentView(R.layout.dbutils)
public class DBUtilsActivity extends AppCompatActivity {

  @ViewInject(R.id.btDB)
  Button button;
  @ViewInject(R.id.listview)
  ListView listView;
  ArrayList<Student> arrayList = new ArrayList<>();
  MyAdapter adapter;
  DbManager db ;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    x.view().inject(this);

    adapter = new MyAdapter();
    listView.setAdapter(adapter);
  }

  @Event(value = R.id.btDB)
  private void myClick(View v) {
    arrayList.add(new Student("张三","18"));
    arrayList.add(new Student("张三","18"));
    arrayList.add(new Student("李四","19"));
    arrayList.add(new Student("李四","19"));
    arrayList.add(new Student("王五","20"));
    arrayList.add(new Student("王五","20"));
    Student s1 = new Student("王五","20");
    Student s = new Student();
    s.setName("sad");
    s.setAge("12");
    try {
      initDB();
      db.save(s);
      db.save(s1);
      db.save(arrayList);
      //此时适配器跟Activity用的是一个全局的集合
      adapter.notifyDataSetChanged();
    } catch (DbException e) {
      e.printStackTrace();
    }

  }

  @Event(type = ListView.OnItemClickListener.class, value = R.id.listview)
  private void myOnItemClickListener(AdapterView<?> var1, View var2, int var3, long var4) {
    Toast.makeText(this, "int:"+var3+",long:"+var4, Toast.LENGTH_SHORT).show();
  }

  @Event(type = ListView.OnItemLongClickListener.class, value = R.id.listview)
  private boolean myOnItemLongClickListener(AdapterView<?> var1, View var2, int var3, long var4) {
    Toast.makeText(this, "int:"+var3+",long:"+var4, Toast.LENGTH_SHORT).show();
    return true;
  }

  void initDB(){

    DaoConfig daoConfig = new DaoConfig()
        //设置数据库名，默认xutils.db
        .setDbName("myapp.db")
        //设置表创建的监听
        .setTableCreateListener(new TableCreateListener(){
          @Override
          public void onTableCreated(DbManager db, TableEntity<?> table) {
          }
        })
        //设置是否允许事务，默认true
        .setAllowTransaction(true)
        //设置数据库的版本号
        .setDbVersion(3)
        //设置数据库更新的监听
        .setDbUpgradeListener(new DbUpgradeListener() {
          @Override
          public void onUpgrade(DbManager db, int oldVersion,
              int newVersion) {
            Student s = new Student();
            Toast.makeText(DBUtilsActivity.this, "sadasd", Toast.LENGTH_SHORT).show();
          }
        })
        //设置数据库打开的监听
        .setDbOpenListener(new DbManager.DbOpenListener() {
          @Override
          public void onDbOpened(DbManager db) {
            //开启数据库支持多线程操作，提升性能
            db.getDatabase().enableWriteAheadLogging();
          }
        });
    db = x.getDb(daoConfig);
  }


  class MyAdapter extends BaseAdapter{

    @Override
    public int getCount() {
      return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
      return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
      return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      TextView tx = new TextView(DBUtilsActivity.this);
      tx.setText(arrayList.get(i).getName()+"           "+arrayList.get(i).getAge());
      return tx;
    }
  }
}
