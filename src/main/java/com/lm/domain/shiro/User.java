package com.lm.domain.shiro;

/**
 * 用户实体
 * Created by Louie on 2017-06-18.
 */
public class User {
    private long uid;
    private String name;
    private String password;
    private String salt;
    private byte state;
    private String username;

    // 已有角色
    private String roles;

    //  密码盐
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

   public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
