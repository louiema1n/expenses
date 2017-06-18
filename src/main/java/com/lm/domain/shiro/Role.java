package com.lm.domain.shiro;

import java.util.List;

/**
 * 角色实体
 * Created by Louie on 2017-06-18.
 */
public class Role {
    private long id;
    private Boolean available = Boolean.FALSE;  // 是否可用
    private String description;
    private String role;                        // 角色名称

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
