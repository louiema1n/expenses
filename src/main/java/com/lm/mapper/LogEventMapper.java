package com.lm.mapper;

import com.lm.domain.LogEvent;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Louie on 2017-06-10.
 */
public interface LogEventMapper {
    @Select("select * from logging_event")
    List<LogEvent> all();
}
