package com.lm.domain.shiro;

import java.util.List;

/**
 * 用户实体
 * Created by Louie on 2017-06-18.
 */
public class User {
    private Long uid;
    private String name;
    private String password;
    private String salt;
    private byte state;
    private String username;

    //  密码盐
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

   public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
