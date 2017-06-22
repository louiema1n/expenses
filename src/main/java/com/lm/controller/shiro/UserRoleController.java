package com.lm.controller.shiro;

import com.lm.domain.shiro.UserRole;
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
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(UserRole userRole, @RequestParam Integer flag) {
        int i = 0;
        if (flag == 0) {
            // 第一次先删除uid下所有角色
            i = this.userRoleService.del(userRole.getUid());
        }
        if (i >= 0) {
            int j = this.userRoleService.add(userRole.getUid(), userRole.getRole_id());
            if (j > 0) {
                return "授予角色成功";
            }
            return "授予角色失败";
        }
        return "未知错误";
    }
}
