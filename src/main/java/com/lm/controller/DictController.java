package com.lm.controller;

import com.lm.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Louie on 2017-06-02.
 */
@RestController
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * 获取支出/缴纳人
     * @return
     */
    @GetMapping("/nameDict")
    public String findName() {
        return this.dictService.findDict("支出/缴纳人");
    }

    /**
     * 修改支出/缴纳人
     */
    @PostMapping("/nameDict")
    public void updName(@RequestParam("context") String context) {
        this.dictService.updDict("支出/缴纳人", context);
    }

    /**
     * 获取支出/缴纳类别
     * @return
     */
    @GetMapping("/cateDict")
    public String findCate() {
        return this.dictService.findDict("支出/缴纳类别");
    }

    /**
     * 修改支出/缴纳类别
     */
    @PostMapping("/cateDict")
    public void updCate(@RequestParam("context") String context) {
        this.dictService.updDict("支出/缴纳类别", context);
    }
}
