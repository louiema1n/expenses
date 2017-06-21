package com.lm.service.shiro;

import com.lm.domain.shiro.Role;
import com.lm.mapper.shiro.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Louie on 2017-06-20.
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> findRoleByUid(long uid) {
        return this.roleMapper.findRoleByUid(uid);
    }

    public List<Role> all() {
        return this.roleMapper.all();
    }

    public Integer add(String description, String role, Boolean available) {
        return this.roleMapper.add(description, role, available);
    }

    public Integer upd(long id, String description, String role, Boolean available) {
        return this.roleMapper.update(id, description, role, available);
    }

    public Integer del(long id) {
        return this.roleMapper.delete(id);
    }
}
