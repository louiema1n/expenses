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
}
