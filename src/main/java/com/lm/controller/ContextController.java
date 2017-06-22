package com.lm.controller;

import com.lm.domain.Context;
import com.lm.domain.EasyuiTree;
import com.lm.domain.shiro.Permission;
import com.lm.domain.shiro.Role;
import com.lm.service.shiro.PermissionService;
import com.lm.service.shiro.RoleService;
import com.lm.utils.PerToTree;
import com.lm.utils.RoleToTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louie on 2017-06-22.
 */
@RestController
@RequestMapping("/ctx")
public class ContextController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 父级角色初始化
     * @return
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public List<Context> role() {
        List<Role> roles = this.roleService.all();
        List<Context> contexts = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            Context context = new Context();
            context.setId(roles.get(i).getId());
            context.setText(roles.get(i).getDescription());
            contexts.add(context);
        }
        return contexts;
    }

    /**
     * 父级权限初始化
     * @return
     */
    @RequestMapping(value = "/per", method = RequestMethod.GET)
    public List<Context> per() {
        List<Permission> permissions = this.permissionService.all();
        List<Context> contexts = new ArrayList<>();
        for (int i = 0; i < permissions.size(); i++) {
            Context context = new Context();
            context.setId(permissions.get(i).getId());
            context.setText(permissions.get(i).getName());
            contexts.add(context);
        }
        return contexts;
    }

    /**
     * role转换成tree
     * @return
     */
    @RequestMapping(value = "/role/{uid}", method = RequestMethod.GET)
    public List<EasyuiTree> roleTree(@PathVariable("uid") long uid){
        // 获取所有role
        List<Role> roles = this.roleService.all();
        // 根据uid获取role
        List<Role> userRole = this.roleService.findRoleByUid(uid);
        List<EasyuiTree> trees = new RoleToTree().roleToTree(roles, 0, userRole);
        return trees;
    }

    /**
     * role转换成tree
     * @return
     */
    @RequestMapping(value = "/per/{id}", method = RequestMethod.GET)
    public List<EasyuiTree> perTree(@PathVariable("id") long id){
        // 获取所有permission
        List<Permission> permissions = this.permissionService.all();
        // 根据uid获取role
        List<Permission> rolePer = this.permissionService.findPerByRoleid(id);
        List<EasyuiTree> trees = new PerToTree().perToTree(permissions, 0, rolePer);
        return trees;
    }


}
