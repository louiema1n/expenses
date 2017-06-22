package com.lm.domain.shiro;

/**
 * 角色-权限实体
 * Created by Louie on 2017-06-18.
 */
public class RolePermission {
    private long permission_id;
    private long role_id;

    public long getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(long permission_id) {
        this.permission_id = permission_id;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }
}
