package com.lm.mapper.shiro;

import com.lm.domain.shiro.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Louie on 2017-06-18.
 */
public interface UserMapper {
    @Select("select * from sys_user where username = #{username}")
    User findByUserName(@Param("username") String username);
}
