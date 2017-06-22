package com.lm.mapper.shiro;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Louie on 2017-06-22.
 */
public interface RolePerMapper {
    @Insert("insert into sys_role_permission(role_id, permission_id) VALUES(#{role_id}, #{permission_id})")
    Integer add(@Param("role_id") long role_id, @Param("permission_id") long permission_id);

    @Delete("delete from sys_role_permission where role_id = #{role_id}")
    Integer delByRoleId(@Param("role_id") long role_id);
}
