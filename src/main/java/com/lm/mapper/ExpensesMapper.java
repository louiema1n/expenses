package com.lm.mapper;

import com.lm.domain.Expenses;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Louie on 2017-05-29.
 */
public interface ExpensesMapper {

    @Select("select * from expenses")
    List<Expenses> findAll();

    @Insert("insert into expenses(category,remark,money,exdate,name,imgurl) values(#{category},#{remark},#{money},#{exdate},#{name},#{imgurl})")
    Integer add(@Param("category") String category,
                @Param("remark") String remark,
                @Param("money") Double money,
                @Param("exdate") String exdate,
                @Param("name")String name,
                @Param("imgurl") String imgurl);

    @Delete("delete from expenses where id = #{id}")
    Integer delete(@Param("id") Integer id);

    @Update("update expenses set category = #{category}, remark = #{remark}, money = #{money}, exdate = #{exdate}, name = #{name} where id = #{id}")
    Integer update(@Param("id") Integer id, @Param("category") String category, @Param("remark") String remark, @Param("money") Double money, @Param("exdate") String exdate, @Param("name") String name);

    @Select("select * from expenses where exdate >= #{sdate} and exdate <= #{edate}")
    List<Expenses> findByDate(@Param("sdate") String sdate, @Param("edate") String edate);

    @Select("select * from expenses where id = #{id}")
    Expenses findById(@Param("id") Integer id);
}
