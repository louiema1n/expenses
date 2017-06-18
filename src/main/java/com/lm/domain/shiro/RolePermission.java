package com.lm.domain.shiro;

/**
 * 角色-权限实体
 * Created by Louie on 2017-06-18.
 */
public class RolePermission {
    private long permissionId;
    private long roleId;

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
