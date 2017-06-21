package com.lm.service.shiro;

import com.lm.domain.shiro.User;
import com.lm.mapper.shiro.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<User> all() {
        return this.userMapper.all();
    }

    public Integer add(String name, String password, String salt, byte state, String username) {
        return this.userMapper.add(name, password, salt, state, username);
    }

    public Integer del(long uid) {
        return this.userMapper.delete(uid);
    }

    public Integer upd(long uid, String name, String password, String salt, byte state, String username) {
        return this.userMapper.update(uid, name, password, salt, state, username);
    }

    /**
     * 根据用户名锁定用户
     * @param username
     */
    public void lockUserByUsername(String username) {
        this.userMapper.lockUserByUsername(username);
    }
}
