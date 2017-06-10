package com.lm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Louie on 2017-05-29.
 */
@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger("log");

    @RequestMapping("")
    public String index() {
        LOGGER.info("请求主页成功。");
        return "index";
    }

    @RequestMapping("/expenses")
    public String exp() {
        return "expenses";
    }

    @RequestMapping("/income")
    public String inc() {
        return "income";
    }

    @RequestMapping("/dict")
    public String indext() {
        return "dict";
    }

    @RequestMapping("/menu")
    public String menu() {
        return "temp/menu";
    }

    @RequestMapping("/log")
    public String log() {
        return "log";
    }

}
