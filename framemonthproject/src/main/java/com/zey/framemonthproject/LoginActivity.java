package com.zey.framemonthproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zey.bean.UserBean;
import com.zey.dao.UserDao;


public class LoginActivity extends Activity {
    EditText loginTel,loginPwd;
    Button btLogin;
    TextView tv;
    String tel;
    String pwd;
    UserDao dao;
    UserBean userBean;
    SharedPreferences sh;
    SharedPreferences.Editor ed;
    CheckBox cbLogin,cbPwd;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        tv=findViewById(R.id.textView9);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
               startActivityForResult(intent,100);
            }
        });
        init();
    }

    private void init() {
        cbLogin=findViewById(R.id.checkbox2);
        cbPwd=findViewById(R.id.checkbox1);
        loginTel= findViewById(R.id.editText3);
        loginPwd=findViewById(R.id.editText4);
        btLogin=findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tel=loginTel.getText().toString();
                pwd=loginPwd.getText().toString();
                dao=new UserDao(LoginActivity.this);
                userBean=dao.findUserByTel(tel);
                    if(userBean==null){
                        Toast.makeText(LoginActivity.this,"该用户不存在",Toast.LENGTH_SHORT).show();
                    }else{
                        if(tel!=null&&!(tel.equals(""))){
                            //判断密码、
                            if(pwd!=null&&!(pwd.equals(""))){
                                if(userBean.getPwd().equals(pwd)){
                                    //跳转
                                    Intent intent=new Intent(LoginActivity.this,NavSetActivity_SecondActivity.class);
                                    //传值（昵称）
                                    intent.putExtra("name",userBean.getName());
                                    intent.putExtra("tel",userBean.getTel());
//                                    intent.putExtra("pic",userBean.getPic());
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this,"正在为您跳转ing...",Toast.LENGTH_SHORT).show();

                                }
                            }else {
                                Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });
        //用SharedPreferences 来进行记住密码 自动登录 功能
                //1.SharedPreferences 的初始化
            sh=getSharedPreferences("userBean",MODE_PRIVATE);
            ed=sh.edit();
            if(sh.getBoolean("cbLogin",false)){
            loginTel.setText(sh.getString("tel",""));
            loginPwd.setText(sh.getString("pwd",""));
                cbLogin.setChecked(true);
                cbPwd.setChecked(true);
                Toast.makeText(LoginActivity.this, "正在登陆ing...", Toast.LENGTH_SHORT).show();
             }else if(sh.getBoolean("cbPwd",false)){
                loginTel.setText(sh.getString("tel",""));
                loginPwd.setText(sh.getString("pwd",""));
                cbPwd.setChecked(true);
            }

           //记住密码
            cbPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(cbPwd.isChecked()){
                        //把账号，密码存入
                        String tel=loginTel.getText().toString();
                        String pwd=loginPwd.getText().toString();
                        if(!tel.equals("")&&tel!=null&&!pwd.equals("")&&pwd!=null){
                            ed.putString("tel",tel);
                            ed.putString("pwd",pwd);
                            //保存复选框的状态
                            ed.putBoolean("cbpwd",true);
                            ed.commit();
                        }
                        }else{//没选 记住密码
                        ed.putBoolean("cbPwd",false);
                        ed.commit();
                    }
                }
            });
        //自动登录
        cbLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cbLogin.isChecked()){
                    cbPwd.setChecked(true);
                        //保存复选框的状态
                        ed.putBoolean("cbLogin",true);
                        ed.commit();

                }else{
                    ed.putBoolean("cbLogin",false);
                    ed.commit();
                }
            }
        });

    }
    //接收RegisterActivity页面传过来的值

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==100&& resultCode==101){
            loginTel.setText(data.getStringExtra("tel"));
            loginPwd.setText(data.getStringExtra("pwd"));

        }
    }
}
