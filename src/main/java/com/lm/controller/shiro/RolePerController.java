package com.lm.controller.shiro;

import com.lm.domain.shiro.RolePermission;
import com.lm.domain.shiro.UserRole;
import com.lm.service.shiro.RolePerService;
import com.lm.service.shiro.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Louie on 2017-06-22.
 */
@RestController
@RequestMapping("/rolePer")
public class RolePerController {

    @Autowired
    private RolePerService rolePerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(RolePermission rolePermission, @RequestParam Integer flag) {
        int i = 0;
        if (flag == 0) {
            // 第一次先删除uid下所有权限
            i = this.rolePerService.del(rolePermission.getRole_id());
        }
        if (i >= 0) {
            int j = this.rolePerService.add(rolePermission.getRole_id(), rolePermission.getPermission_id() );
            if (j > 0) {
                return "授予权限成功";
            }
            return "授予权限失败";
        }
        return "未知错误";
    }
}
