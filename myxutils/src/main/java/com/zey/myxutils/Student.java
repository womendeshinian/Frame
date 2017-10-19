package com.zey.myxutils;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by 赵二盈 on 2017/9/27.
 */


@Table(name = "stu")//创建该实体类对象时 会自动检查该表是否存在
public class Student {
        public  Student(String name,String age){
            this.name = name;
            this.age = age;
        }
        public Student(){

        }

        /**
         * name = "id"：数据库表中的一个字段
         * isId = true：是否是主键
         * autoGen = true：是否自动增长
         * property = "NOT NULL"：添加约束
         */
        @Column(name="_id",isId = true)
        private  int id;
        @Column(name="stuName")
        private  String name;
        @Column(name="stuAge")
        private  String age;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        @Override//打印对象是 默认打印的是地址   如果需要显示内部信息  则重写toString方法
        public String toString() {
            return "学生信息：姓名："+name+"，年龄："+age;
        }
    }


