package com.lm.mapper.shiro;

import com.lm.domain.shiro.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Louie on 2017-06-20.
 */
public interface PermissionMapper {

    @Select("SELECT permission,id,name FROM sys_permission p LEFT JOIN sys_role_permission rp ON p.id=rp.permission_id where rp.role_id = #{roleId}")
    List<Permission> findPerByRoleid(@Param("roleId") long roleId);

    @Select("select * from sys_permission")
    List<Permission> all();

    @Insert("insert into sys_permission(name, permission, resource_type, url, available, pid) values(#{name}, #{permission}, #{resource_type}, #{url}, #{available}, #{pid})")
    Integer add(
            @Param("name") String name,
            @Param("permission") String permission,
            @Param("resource_type") String resource_type,
            @Param("url") String url,
            @Param("available") Boolean available,
            @Param("pid") long pid);

    @Update("update sys_permission set available = false where id = #{id}")
    Integer delete(@Param("id") long id);

    @Update("update sys_permission set name = #{name}, permission = #{permission}, resource_type = #{resource_type}, url = #{url}, available = #{available}, pid = #{pid} where id = #{id}")
    Integer update(
            @Param("id") long id,
            @Param("name") String name,
            @Param("permission") String permission,
            @Param("resource_type") String resource_type,
            @Param("url") String url,
            @Param("available") Boolean available,
            @Param("pid") long pid);

    @Select("select * from sys_permission where id = #{pid}")
    Permission findPerByPid(@Param("pid") long pid);
}
