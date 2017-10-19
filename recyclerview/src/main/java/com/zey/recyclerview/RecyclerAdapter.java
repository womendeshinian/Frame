package com.zey.recyclerview;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 赵二盈 on 2017/9/19.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder>{
    Context c;
    List<String> datas;
    IOnListItemClickListenster listItemClickListenster;
    public RecyclerAdapter(Context c, List<String> datas){
        this.c=c;
        this.datas=datas;
    }

    //初始化监听对象


    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public RecyclerAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(c,R.layout.adapter,null);
        MyHolder holder=new MyHolder(view);

        return holder;
    }
    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(RecyclerAdapter.MyHolder holder, final int position) {
            holder.tv.setText(datas.get(position));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItemClickListenster.onClick(position);
            }
        });
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listItemClickListenster.onLongClick(position);
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


    //控件管理
    class MyHolder extends ViewHolder{
        TextView tv;
        View view;


        public MyHolder(View itemView) {
            super(itemView);
            view=itemView;
            tv=itemView.findViewById(R.id.textView);
        }
    }

    //初始化监听条目

    public void setListItemClickListenster(IOnListItemClickListenster listItemClickListenster) {
        this.listItemClickListenster = listItemClickListenster;
    }

    //删除数据
    public void deleteData(int position){
        datas.remove(position);
        notifyItemRemoved(position);
    }

    //添加数据
    public void addData(int position,String data){
        datas.add(position,data);
        notifyItemInserted(position);
    }
}
