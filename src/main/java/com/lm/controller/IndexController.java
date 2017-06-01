package com.lm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Louie on 2017-05-29.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
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

//    @RequestMapping("/indext")
//    public String indext() {
//        return "indext";
//    }

}
