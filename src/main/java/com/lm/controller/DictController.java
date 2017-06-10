package com.lm.controller;

import com.lm.service.DictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Louie on 2017-06-02.
 */
@RestController
public class DictController {

    private static final Logger LOGGER = LoggerFactory.getLogger("log");

    @Autowired
    private DictService dictService;

    /**
     * 获取支出/缴纳人
     * @return
     */
    @GetMapping("/nameDict")
    public String findName() {
        LOGGER.info("【查询】所有支出/缴纳人数据");
        return this.dictService.findDict("支出/缴纳人");
    }

    /**
     * 修改支出/缴纳人
     */
    @PostMapping("/nameDict")
    public void updName(@RequestParam("context") String context) {
        LOGGER.info("【修改】支出/缴纳人数据字典：" + context);
        this.dictService.updDict("支出/缴纳人", context);
    }

    /**
     * 获取支出/缴纳类别
     * @return
     */
    @GetMapping("/cateDict")
    public String findCate() {
        LOGGER.info("【查询】所有支出/缴纳类别数据");
        return this.dictService.findDict("支出/缴纳类别");
    }

    /**
     * 修改支出/缴纳类别
     */
    @PostMapping("/cateDict")
    public void updCate(@RequestParam("context") String context) {
        LOGGER.info("【修改】支出/缴纳类别数据字典：" + context);
        this.dictService.updDict("支出/缴纳类别", context);
    }
}
