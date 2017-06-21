package com.lm.controller.shiro;

import com.lm.domain.shiro.Role;
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
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有role
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Role> all() {
        return this.roleService.all();
    }

    /**
     * 新增role
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid Role role, BindingResult bindingResult) {
        int i = this.roleService.add(
                role.getDescription(),
                role.getRole(),
                role.getAvailable()
        );
        if (i > 0) {
            return "新增成功";
        }
        return "新增失败";
    }

    /**
     * 修改role
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String upd(Role role) {
        int i = this.roleService.upd(
                role.getId(),
                role.getDescription(),
                role.getRole(),
                role.getAvailable()
        );
        if (i > 0) {
            return "修改成功";
        }
        return "修改失败";
    }

    /**
     * 停用role
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public String del(@PathVariable("id") long id) {
        int i = this.roleService.del(id);
        if (i > 0) {
            return "停用成功";
        }
        return "停用失败";
    }
}
