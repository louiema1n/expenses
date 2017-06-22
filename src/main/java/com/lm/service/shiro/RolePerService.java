package com.lm.service.shiro;

import com.lm.mapper.shiro.RolePerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Louie on 2017-06-22.
 */
@Service
public class RolePerService {

    @Autowired
    private RolePerMapper rolePerMapper;

    public Integer add(long role_id, long permission_id) {
        return this.rolePerMapper.add(role_id, permission_id);
    }

    public Integer del(long role_id) {
        return this.rolePerMapper.delByRoleId(role_id);
    }
}
