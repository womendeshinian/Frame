package com.zey.framemonthproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.zey.bean.UserBean;
import com.zey.dao.UserDao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends Activity {
    EditText etName,etTel,etPwd,etPwd1;
    Button btRegister;
    UserDao dao;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        init();
    }

    private void init() {
        etPwd1=findViewById(R.id.editText5);
        etName=findViewById(R.id.editText);
        etTel=findViewById(R.id.editText1);
        etPwd=findViewById(R.id.editText2);
        btRegister=findViewById(R.id.button1);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao=new UserDao(RegisterActivity.this);
                //手机号
                String tel=etTel.getText().toString();
                UserBean u=dao.findUserByTel(tel);
                boolean c;
                if(u!=null){
                    Toast.makeText(RegisterActivity.this,"该用户已存在",Toast.LENGTH_SHORT).show();

                }else{
                    if(!(tel.equals("")&&tel==null)){
                        //以1开头的11位数
                        String examTel="^1\\d{10}$";
                        Pattern pattern=Pattern.compile(examTel);
                        Matcher matcher=pattern.matcher(tel);
                        boolean d=matcher.matches();
                        System.out.println("d:"+d+"###########");
                        if(d){
                            c=true;
                        }else{
                            c=false;
                        }
                    }else{
                        c=false;
                    }

                    //用户名
                    String name=etName.getText().toString();
                    boolean a;
                    if(!(name.equals("")&&name==null )){
                        //以字母开头，允许字母数字下划线，5-16个字节
                        String examName="^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
                        Pattern pattern=Pattern.compile(examName);
                        Matcher matcher=pattern.matcher(name);
                        boolean b=matcher.matches();
                        System.out.println("b:"+b+"###########");
                        if(b){
                            a=true;
                        }else{
                            a=false;
                        }
                    }else{
                        a=false;
                    }

                    //密码
                    String pwd=etPwd.getText().toString();
                    String pwd1=etPwd1.getText().toString();
                    System.out.println(pwd1+"$$$$$$$$$$$$$$$");
                    System.out.println(pwd+"$$$$$$$$$$$$$$$$$$$$");
                    boolean e;
                    if((!pwd.equals("")&&pwd!=null)&&(!pwd1.equals("")&&pwd!=null)){
                        //以字母开头，长度在6~18之间，只能包含字符、数字和下划线。
//                    String examPwd=" ^\\w{6,12}$";
//                    Pattern pattern=Pattern.compile(examPwd);
//                    Matcher matcher=pattern.matcher(pwd);
//                    boolean f=matcher.matches();
//                    System.out.println("f"+f+"###########");

                        if(pwd.length()>6&&pwd.length()<12){
                                if(pwd.equals(pwd1)){
                                    e=true;
                                }else{
                                    e=false;
                                    Toast.makeText(RegisterActivity.this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                                }
                        }else{
                                e=false;
                                Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                            }
                    }else{
                        e=false;
                    }
                    if(a&&c&&e){
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        dao.registerUser(name,tel,pwd);
                        //传值到登录页面
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        intent.putExtra("tel",tel);
                        intent.putExtra("name",name);
                        intent.putExtra("pwd",pwd);

                        //将信息传给指定的Activity(不带有跳转效果)
                        setResult(101,intent);
                        finish();//彻底关闭当前页面

                    }else{
                        System.out.println("a"+a+"###########"+"c"+c+"###########"+"e"+e+"###########");
                        Toast.makeText(RegisterActivity.this,"对不起，请先完善好您的个人信息",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
