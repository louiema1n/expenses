package com.lm.domain.shiro;

/**
 * 用户-角色关系实体
 * Created by Louie on 2017-06-18.
 */
public class UserRole {
    private long uid;
    private long roleId;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
