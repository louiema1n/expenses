package com.lm.mapper.shiro;

import com.lm.domain.shiro.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Louie on 2017-06-20.
 */
public interface PermissionMapper {

    @Select("SELECT permission,id FROM sys_permission p LEFT JOIN sys_role_permission rp ON p.id=rp.permission_id where rp.role_id = #{roleId}")
    List<Permission> findPerByRoleid(@Param("roleId") long roleId);
}
