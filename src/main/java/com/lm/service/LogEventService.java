package com.lm.service;

import com.lm.domain.LogEvent;
import com.lm.mapper.LogEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Louie on 2017-06-10.
 */
@Service
public class LogEventService {

    @Autowired
    private LogEventMapper logEventMapper;

    public List<LogEvent> findAll() {
        return this.logEventMapper.all();
    }
}
