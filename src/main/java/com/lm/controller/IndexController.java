package com.lm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Louie on 2017-05-29.
 */
@Controller
public class IndexController {

    @RequestMapping("")
    public String index() {
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

}
