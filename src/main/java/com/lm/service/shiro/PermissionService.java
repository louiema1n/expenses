package com.lm.service.shiro;

import com.lm.domain.shiro.Permission;
import com.lm.mapper.shiro.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Louie on 2017-06-20.
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> findPerByRoleid(long roleId) {
        return this.permissionMapper.findPerByRoleid(roleId);
    }

    public List<Permission> all() {
        return this.permissionMapper.all();
    }

    public Integer add(String name, String permission, String resource_type, String url, Boolean available) {
        return this.permissionMapper.add(name, permission, resource_type, url, available);
    }

    public Integer upd(long id, String name, String permission, String resource_type, String url, Boolean available) {
        return this.permissionMapper.update(id, name, permission, resource_type, url, available);
    }

    public Integer del(long id) {
        return this.permissionMapper.delete(id);
    }
}

