package com.lm.mapper.shiro;

import com.lm.domain.shiro.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Louie on 2017-06-20.
 */
public interface RoleMapper {

    @Select("SELECT role,id FROM sys_role r LEFT JOIN sys_user_role ur ON r.id=ur.role_id where ur.uid = #{uid}")
    List<Role> findRoleByUid(@Param("uid") long uid);

    @Select("select * from sys_role")
    List<Role> all();

    @Insert("insert into sys_role(description, role, available) values(#{description}, #{role}, #{available})")
    Integer add(
            @Param("description") String description,
            @Param("role") String role,
            @Param("available") Boolean available);

    @Update("update sys_role set available = false where id = #{id}")
    Integer delete(@Param("id") long id);

    @Update("update sys_role set description = #{description}, role = #{role}, available = #{available} where id = #{id}")
    Integer update(
            @Param("id") long id,
            @Param("description") String description,
            @Param("role") String role,
            @Param("available") Boolean available);
}
