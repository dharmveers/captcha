package com.captcha.model;

import jakarta.persistence.*;

@Entity(name = "Usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private  String userid;
    private String userPass;

    @Transient
    private String captcha;
    @Transient
    private String hiddenCaptcha;
    @Transient
    private String userCaptcha;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getUserCaptcha() {
        return userCaptcha;
    }

    public void setUserCaptcha(String userCaptcha) {
        this.userCaptcha = userCaptcha;
    }

    public String getHiddenCaptcha() {
        return hiddenCaptcha;
    }

    public void setHiddenCaptcha(String hiddenCaptcha) {
        this.hiddenCaptcha = hiddenCaptcha;
    }
}
