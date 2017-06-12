package com.lm.controller;

import com.lm.domain.LogEvent;
import com.lm.service.LogEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Louie on 2017-06-10.
 */
@RestController
public class LogEventController {

    @Autowired
    private LogEventService logEventService;

    /**
     * 查询日志
     * @return
     */
    @RequestMapping("/logevent/{time}")
    public List<LogEvent> find(@PathVariable("time") String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BigInteger bi = new BigInteger("-28800");
        try {
            bi = BigInteger.valueOf((sdf.parse(time).getTime())/1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.logEventService.findAll(bi, new BigInteger("" + (bi.intValue() + 86400)));
    }
}
