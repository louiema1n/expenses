package com.lm.mapper.shiro;

import com.lm.domain.shiro.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Louie on 2017-06-18.
 */
public interface UserMapper {
    @Select("select * from sys_user where username = #{username}")
    User findByUserName(@Param("username") String username);

    @Select("select * from sys_user")
    List<User> all();

    @Insert("insert into sys_user(name, password, salt, state, username) values(#{name}, #{password}, #{salt}, #{state}, #{username})")
    Integer add(
            @Param("name") String name,
            @Param("password") String password,
            @Param("salt") String salt,
            @Param("state") byte state,
            @Param("username") String username);

    @Update("update sys_user set state = 3 where uid = #{uid}")
    Integer delete(@Param("uid") long uid);

    @Update("update sys_user set name = #{name}, password = #{password}, salt = #{salt}, state = #{state}, username = #{username} where uid = #{uid}")
    Integer update(
            @Param("uid") long uid,
            @Param("name") String name,
            @Param("password") String password,
            @Param("salt") String salt,
            @Param("state") byte state,
            @Param("username") String username);

    @Update("update sys_user set state = 2 where username = #{username}")
    Integer lockUserByUsername(@Param("username") String username);

}
