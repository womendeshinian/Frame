package com.zey.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
RecyclerView recyclerView;
    RecyclerAdapter adapter;
    List<String> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        initData();
        //创建并设置Adapter
        adapter=new RecyclerAdapter(MainActivity.this,datas);
        //设置recyclerView布局的排列方式
        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        //设置为垂直布局，为默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //如果能确定每个item的高度是固定的，设置这个选项可以提高性能
//        recyclerView.setHasFixedSize(true);


        //设置Adapter
        recyclerView.setAdapter(adapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //添加动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView通过适配器添加监听     是一个自定义的监听接口
        //想ListView条目的监听，listView.setOnItemClickListenster(.....);
        IOnListItemClickListenster iOnListItemClickListenster=new IOnListItemClickListenster() {
            @Override
            public void onClick(int position) {
                Snackbar.make(getWindow().getDecorView(),"listItem"+position,
                        Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                Snackbar.make(getWindow().getDecorView(),"listItemLongClick"+position,Snackbar.LENGTH_SHORT).show();
            }

        };
        adapter.setListItemClickListenster(iOnListItemClickListenster);
    }

    private void init() {
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        //把引入的选项菜单以溢出菜单的形式显示到Toolbar上
        setSupportActionBar(toolbar);
    }
    //创建选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    //监听菜单
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.list_add:
                adapter.addData(4,"我是赵二盈");
                break;
            case R.id.list_delete:
                adapter.deleteData(4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initData(){
        datas=new ArrayList<String>();
        for(int i=0;i<40;i++){
            datas.add("item:"+i);
        }
    }

//    public class DividerItemDecoration extends RecyclerView.ItemDecoration {
//
//        private  final int[] ATTRS = new int[]{
//                android.R.attr. listDivider
//        };
//
//        public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
//        public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
//
//        private Drawable mDivider;
//
//        private int mOrientation;
//
//        public DividerItemDecoration(Context context, int orientation) {
//            final TypedArray a = context.obtainStyledAttributes(ATTRS );
//            mDivider = a.getDrawable(0);
//            a.recycle();
//            setOrientation(orientation);
//        }
//
//        public void setOrientation( int orientation) {
//            if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
//                throw new IllegalArgumentException( "invalid orientation");
//            }
//            mOrientation = orientation;
//        }
//        @Override
//        public void onDraw(Canvas c, RecyclerView parent) {
//            if (mOrientation == VERTICAL_LIST) {
//                drawVertical(c, parent);
//            } else {
//                drawHorizontal(c, parent);
//            }
//        }
//        public void drawVertical(Canvas c, RecyclerView parent) {
//            final int left = parent.getPaddingLeft();
//            final int right = parent.getWidth() - parent.getPaddingRight();
//            final int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                final View child = parent.getChildAt(i);
//                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
//                        .getLayoutParams();
//                final int top = child.getBottom() + params.bottomMargin;
//
//                final int bottom = top + mDivider.getIntrinsicHeight();
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
//        }
//        public void drawHorizontal(Canvas c, RecyclerView parent) {
//            final int top = parent.getPaddingTop();
//            final int bottom = parent.getHeight() - parent.getPaddingBottom();
//            final int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                final View child = parent.getChildAt(i);
//                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
//                        .getLayoutParams();
//                final int left = child.getRight() + params.rightMargin;
//                final int right = left + mDivider.getIntrinsicHeight();
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
//        }
//        @Override
//        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
//            if (mOrientation == VERTICAL_LIST) {
//                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
//            }else{
//                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
//            }
//        }
//    }



    }
