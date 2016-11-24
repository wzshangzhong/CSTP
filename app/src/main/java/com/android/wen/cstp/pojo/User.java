package com.android.wen.cstp.pojo;

import java.io.Serializable;

 // 用户信息
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String imgUserPath;

    public User() {
    }

    public User(Integer id, String username, String password, String imgUserPath) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.imgUserPath = imgUserPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgUserPath() {
        return imgUserPath;
    }

    public void setImgUserPath(String imgUserPath) {
        this.imgUserPath = imgUserPath;
    }
}
