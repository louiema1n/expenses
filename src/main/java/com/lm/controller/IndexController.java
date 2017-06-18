package com.lm.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Louie on 2017-05-29.
 */
@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger("log");

    @RequestMapping({"/", "/index"})
    public String index() {
        LOGGER.info("请求主页成功。");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 登录
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Map<String, Object> map) {
        // 获取异常，shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        // 回显errMsg
        String errMsg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                errMsg = "用户名不存在";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                errMsg = "密码错误";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                errMsg = "验证码错误";
            } else {
                errMsg = exception;
            }
        }
        map.put("errMsg", errMsg);
        // 此方法不处理登陆成功，由shiro处理
        return "login";
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
