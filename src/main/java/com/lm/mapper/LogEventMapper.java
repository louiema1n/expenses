package com.lm.mapper;

import com.lm.domain.LogEvent;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Louie on 2017-06-10.
 */
public interface LogEventMapper {
    @Select("select * from logging_event where timestmp >= #{sdate}*1000 and timestmp <= #{edate}*1000")
    List<LogEvent> all(@Param("sdate") BigInteger sdate, @Param("edate") BigInteger edate);
}
