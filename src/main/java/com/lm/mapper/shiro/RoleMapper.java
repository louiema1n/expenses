package com.lm.mapper.shiro;

import com.lm.domain.shiro.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Louie on 2017-06-20.
 */
public interface RoleMapper {

    @Select("SELECT role,id FROM sys_role r LEFT JOIN sys_user_role ur ON r.id=ur.role_id where ur.uid = #{uid}")
    List<Role> findRoleByUid(@Param("uid") long uid);
}
