package com.zey.bean;

/**
 * Created by 赵二盈 on 2017/10/9.
 */

public class UserBean {
    private  int id;
    private String name;
    private String tel;
    private String pwd;
    private String pic;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", pwd='" + pwd + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }

    public UserBean(int id, String name, String tel, String pwd, String pic) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.pwd = pwd;
        this.pic = pic;
    }

    public  UserBean(){

    }
}
