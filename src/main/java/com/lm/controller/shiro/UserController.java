package com.lm.controller.shiro;

import com.lm.domain.shiro.User;
import com.lm.service.shiro.UserService;
import com.lm.utils.MD5Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Louie on 2017-06-19.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有user数据
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> users() {
        return this.userService.all();
    }

    /**
     * 新增user
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(User user) throws NoSuchAlgorithmException {
        // 将user进行密码加密
        MD5Password.md5PasswordUser(user);
        int i = this.userService.add(
                user.getName(),
                user.getPassword(),
                user.getSalt(),
                user.getState(),
                user.getUsername());
        if (i > 0) {
            return "新增成功。";
        }
        return "新增失败!";
    }

    /**
     * 删除user
     * @return
     */
    @RequestMapping(value = "/del/{uid}",method = RequestMethod.DELETE)
    public String del(@PathVariable("uid") long uid) {
        int i = this.userService.del(uid);
        if (i > 0) {
            return "删除成功。";
        }
        return "删除失败!";
    }

    /**
     * 修改user
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String upd(User user) {
        // 获取user加密
        MD5Password.md5PasswordUser(user);
        int i = this.userService.upd(
                user.getUid(),
                user.getName(),
                user.getPassword(),
                user.getSalt(),
                user.getState(),
                user.getUsername());
        if (i > 0) {
            return "修改成功。";
        }
        return "修改失败!";
    }
}
