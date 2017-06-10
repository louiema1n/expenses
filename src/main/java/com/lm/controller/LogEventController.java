package com.lm.controller;

import com.lm.domain.LogEvent;
import com.lm.service.LogEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Louie on 2017-06-10.
 */
@RestController
public class LogEventController {

    @Autowired
    private LogEventService logEventService;

    @RequestMapping("/logevent")
    public List<LogEvent> find() {
        return this.logEventService.findAll();
    }
}
