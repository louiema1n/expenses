package com.lm.controller.shiro;

import com.lm.domain.shiro.Role;
import com.lm.domain.shiro.User;
import com.lm.service.shiro.RoleService;
import com.lm.service.shiro.UserService;
import com.lm.utils.MD5Password;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有user数据
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @RequiresPermissions("user:view")
    public List<User> users() {
        List<User> users = this.userService.all();
        // 初始化已有角色
        for (User user : users) {
            // 根据uid获取已有role
            List<Role> roles = this.roleService.findRoleByUid(user.getUid());
            String str = "[";
            for (int i = 0; i < roles.size(); ) {
                str += roles.get(i).getDescription();
                i++;
                if (i < roles.size()) {
                    str += ",";
                }
            }
            str += "]";
            user.setRoles(str);
        }
        return users;
    }

    /**
     * 新增user
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("user:add")
    public String add(@Valid User user, BindingResult bindingResult) throws NoSuchAlgorithmException {
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
    @RequiresPermissions("user:del")
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
    @RequiresPermissions("user:edit")
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

    /**
     * 通过username获取user
     * @param username
     * @return
     */
    @RequestMapping(value = "/findUserByUsername/{username}", method = RequestMethod.GET)
    public User findUserByUsername(@PathVariable("username") String username) {
        return this.userService.findByUsername(username);
    }

    /**
     * 根据用户id修改password
     * @param user，newpassword
     * @return
     */
    @RequestMapping(value = "/changePwd", method = RequestMethod.PUT)
    public String changePwd(User user, @RequestParam("newpassword") String newpassword) {
        user.setPassword(newpassword);
        MD5Password.md5PasswordUser(user);
        int i = this.userService.upd(
                user.getUid(),
                user.getName(),
                user.getPassword(),
                user.getSalt(),
                user.getState(),
                user.getUsername());
        if (i > 0) {
            return "密码修改成功。";
        }
        return "密码修改失败!";
    }
}
