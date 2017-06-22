package com.lm.service.shiro;

import com.lm.mapper.shiro.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Louie on 2017-06-22.
 */
@Service
public class UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public Integer add(long uid, long role_id) {
        return this.userRoleMapper.add(uid, role_id);
    }

    public Integer del(long uid) {
        return this.userRoleMapper.delByUid(uid);
    }
}
