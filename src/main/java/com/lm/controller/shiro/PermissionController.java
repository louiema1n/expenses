package com.lm.controller.shiro;

import com.lm.domain.shiro.Permission;
import com.lm.domain.shiro.Role;
import com.lm.service.shiro.PermissionService;
import com.lm.service.shiro.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Louie on 2017-06-20.
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有permission
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Permission> all() {
        List<Permission> permissions = this.permissionService.all();
        // 初始化父级权限
        for (Permission permission : permissions) {
            if (permission.getPid() == 0) {
                permission.setParentPer(permission.getName());
            } else {
                permission.setParentPer(this.permissionService.findPerByPid(permission.getPid()).getName());
            }
        }
        return permissions;
    }

    /**
     * 新增permission
     * @param permission
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid Permission permission, BindingResult bindingResult) {
        int i = this.permissionService.add(
                permission.getName(),
                permission.getPermission(),
                permission.getResource_type(),
                permission.getUrl(),
                permission.getAvailable(),
                permission.getPid()
        );
        if (i > 0) {
            return "新增成功";
        }
        return "新增失败";
    }

    /**
     * 修改permission
     * @param permission
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String upd(Permission permission) {
        int i = this.permissionService.upd(
                permission.getId(),
                permission.getName(),
                permission.getPermission(),
                permission.getResource_type(),
                permission.getUrl(),
                permission.getAvailable(),
                permission.getPid()
        );
        if (i > 0) {
            return "修改成功";
        }
        return "修改失败";
    }

    /**
     * 停用permission
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public String del(@PathVariable("id") long id) {
        int i = this.permissionService.del(id);
        if (i > 0) {
            return "停用成功";
        }
        return "停用失败";
    }
}
