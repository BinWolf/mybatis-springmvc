package com.wolf.entity;

import java.io.Serializable;

/**
 * Created by wolf on 16/3/5.
 *
 * 用户信息
 *
 * 这只是定义了用户的基本信息,当具体业务需要添加用户某些信息时,可以继承本类进行扩展
 */
public class User implements Serializable{

    //用户id
    private String userId;
    //用户名字
    private String userName;
    //登录密码
    private String passWord;
    //登录名
    private String loginName;
    //身份证
    private String idCardNo;
    //手机
    private String mobileNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}
