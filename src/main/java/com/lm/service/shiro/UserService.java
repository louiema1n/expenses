package com.lm.service.shiro;

import com.lm.domain.shiro.User;
import com.lm.mapper.shiro.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Louie on 2017-06-18.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByUsername(String username) {
        return this.userMapper.findByUserName(username);
    }
}
