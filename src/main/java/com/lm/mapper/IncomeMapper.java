package com.lm.mapper;

import com.lm.domain.Income;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Louie on 2017-05-29.
 */
public interface IncomeMapper {

    @Select("select * from income")
    List<Income> findAll();

    @Insert("insert into income(category,remark,money,exdate,name) values(#{category},#{remark},#{money},#{exdate},#{name})")
    Integer add(@Param("category") String category, @Param("remark") String remark, @Param("money") Double money, @Param("exdate") String exdate, @Param("name") String name);

    @Delete("delete from income where id = #{id}")
    Integer delete(@Param("id") Integer id);

    @Update("update income set category = #{category}, remark = #{remark}, money = #{money}, exdate = #{exdate}, name = #{name} where id = #{id}")
    Integer update(@Param("id") Integer id, @Param("category") String category, @Param("remark") String remark, @Param("money") Double money, @Param("exdate") String exdate, @Param("name") String name);

    @Select("select * from income where exdate >= #{sdate} and exdate <= #{edate}")
    List<Income> findByDate(@Param("sdate") String sdate, @Param("edate") String edate);
}
