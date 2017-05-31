package com.lm.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lm.domain.Income;
import com.lm.domain.PageResult;
import com.lm.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Louie on 2017-05-29.
 */
@RestController
@RequestMapping("/inc")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    /**
     * 显示所有income
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public PageResult all(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "rows", defaultValue = "1") Integer rows) {
        PageResult pageResult = new PageResult();
        // arg1 第几页,arg2 pageSize,conut 计算总数
        Page<Object> pageHelper = PageHelper.startPage(page, rows, true);
        pageResult.setRows(this.incomeService.findAll());
        // 获取总记录数
        Long total = pageHelper.getTotal();
        pageResult.setTotal(Integer.parseInt(total.toString()));
        return pageResult;
    }

    /**
     * 新增income
     * @param income
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String add(Income income) {
        Integer i = this.incomeService.addIncome(
                income.getCategory(),
                income.getRemark(),
                income.getMoney(),
                income.getExdate(),
                income.getName());
        if (i > 0) {
            return "新增成功。";
        }
        return "新增失败！";
    }

    /**
     * 删除income
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        Integer i = this.incomeService.delIncome(id);
        if (i > 0) {
            return "删除成功。";
        }
        return "删除失败！";
    }

    /**
     * 更新income
     * @param income
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(Income income) {
        Integer i = incomeService.upIncome(
                income.getId(),
                income.getCategory(),
                income.getRemark(),
                income.getMoney(),
                income.getExdate(),
                income.getName());
        if (i > 0) {
            return "修改成功。";
        }
        return "修改失败！";
    }

    @RequestMapping(value = "/{sdate}/{edate}", method = RequestMethod.GET)
    public PageResult findByDate(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "1") Integer rows,
                                 @PathVariable("sdate") String sdate, @PathVariable("edate") String edate) {
        PageResult dPageResult = new PageResult();
        // arg1 第几页,arg2 pageSize,conut 计算总数
        Page<Object> pageHelper = PageHelper.startPage(page, rows, true);
        dPageResult.setRows(this.incomeService.findByDate(sdate, edate));
        // 获取总记录数
        Long total = pageHelper.getTotal();
        dPageResult.setTotal(Integer.parseInt(total.toString()));
        return dPageResult;
    }
}
