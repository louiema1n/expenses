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

    /**
     * 获取用户类别
     * @return
     */
    @GetMapping("/nameCDict")
    public String findNameC() {
        LOGGER.info("【查询】所有用户类别数据");
        return this.dictService.findDict("用户类别");
    }

    /**
     * 修改用户类别
     */
    @PostMapping("/nameCDict")
    public void updNameC(@RequestParam("context") String context) {
        LOGGER.info("【修改】用户类别数据字典：" + context);
        this.dictService.updDict("用户类别", context);
    }

    /**
     * 获取用户类别
     * @return
     */
    @GetMapping("/stateDict")
    public String findState() {
        LOGGER.info("【查询】所有用户状态数据");
        return this.dictService.findDict("用户状态");
    }

    /**
     * 修改用户类别
     */
    @PostMapping("/stateDict")
    public void updState(@RequestParam("context") String context) {
        LOGGER.info("【修改】用户状态数据字典：" + context);
        this.dictService.updDict("用户状态", context);
    }

    /**
     * 获取角色状态
     * @return
     */
    @GetMapping("/availableDict")
    public String findAvailable() {
        LOGGER.info("【查询】所有角色状态数据");
        return this.dictService.findDict("角色状态");
    }

    /**
     * 修改角色状态
     */
    @PostMapping("/availableDict")
    public void updAvailable(@RequestParam("context") String context) {
        LOGGER.info("【修改】角色状态数据字典：" + context);
        this.dictService.updDict("角色状态", context);
    }

    /**
     * 获取资源类型
     * @return
     */
    @GetMapping("/resourceTypeDict")
    public String findResourceType() {
        LOGGER.info("【查询】所有资源类型数据");
        return this.dictService.findDict("资源类型");
    }

    /**
     * 修改资源类型
     */
    @PostMapping("/resourceTypeDict")
    public void updResourceType(@RequestParam("context") String context) {
        LOGGER.info("【修改】资源类型数据字典：" + context);
        this.dictService.updDict("资源类型", context);
    }
}
