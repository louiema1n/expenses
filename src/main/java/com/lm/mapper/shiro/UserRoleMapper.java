package com.lm.mapper.shiro;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Louie on 2017-06-22.
 */
public interface UserRoleMapper {
    @Insert("insert into sys_user_role(uid, role_id) VALUES(#{uid}, #{role_id})")
    Integer add(@Param("uid") long uid, @Param("role_id") long role_id);

    @Delete("delete from sys_user_role where uid = #{uid}")
    Integer delByUid(@Param("uid") long uid);
}
