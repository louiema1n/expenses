package com.lm.domain.shiro;

/**
 * 用户-角色关系实体
 * Created by Louie on 2017-06-18.
 */
public class UserRole {
    private long uid;
    private long role_id;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }
}
