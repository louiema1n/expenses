package com.lm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Louie on 2017-06-02.
 */
public interface DictMapper {

    @Select("select context from dict where dname = #{dname}")
    String findDict(@Param("dname")String dname);

    @Update("update dict set context = #{context} where dname = #{dname}")
    Integer updDict(@Param("dname")String dname, @Param("context")String context);
}
