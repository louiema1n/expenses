package com.lm.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lm.domain.Expenses;
import com.lm.domain.Footer;
import com.lm.domain.Income;
import com.lm.domain.PageResult;
import com.lm.service.IncomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louie on 2017-05-29.
 */
@RestController
@RequestMapping("/inc")
public class IncomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger("log");

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

        LOGGER.info("【查询】所有缴纳数据。");

        // 获取记录
        List<Income> incomeList = new ArrayList<>();
        incomeList = this.incomeService.findAll();

        // 获取总记录数
        Long total = pageHelper.getTotal();

        // 设置footer属性
        Footer footer = new Footer();
        footer.setCategory("缴纳合计：");
        // 遍历list，计算money总和
        double totalMoney = 0;
        for (Income income : incomeList) {
            totalMoney += income.getMoney();
        }
        footer.setMoney(totalMoney);

        List<Footer> footerList = new ArrayList<>();
        footerList.add(footer);

        pageResult.setRows(incomeList);
        pageResult.setTotal(Integer.parseInt(total.toString()));
        pageResult.setFooter(footerList);
        return pageResult;
    }

    /**
     * 新增income
     * @param income
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String add(Income income) {
        LOGGER.info("【新增】缴纳记录：" +
                income.getName() + "在" +
                income.getExdate() + "共缴纳" +
                income.getMoney() + "用于" +
                income.getCategory() + "的" +
                income.getRemark());
        Integer i = this.incomeService.addIncome(
                income.getCategory(),
                income.getRemark(),
                income.getMoney(),
                income.getExdate(),
                income.getName());
        if (i > 0) {
            LOGGER.info("新增成功。");
            return "新增成功。";
        }
        LOGGER.info("新增失败!");
        return "新增失败！";
    }

    /**
     * 删除income
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        LOGGER.info("【删除】缴纳记录:" + this.incomeService.findById(id));
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
        LOGGER.info("【更新前】缴纳记录：" + this.incomeService.findById(income.getId()));
        Integer i = incomeService.upIncome(
                income.getId(),
                income.getCategory(),
                income.getRemark(),
                income.getMoney(),
                income.getExdate(),
                income.getName());
        if (i > 0) {
            LOGGER.info("【更新后】支出记录：" + income);
            return "修改成功。";
        }
        LOGGER.info("更新失败");
        return "修改失败！";
    }

    @RequestMapping(value = "/{sdate}/{edate}", method = RequestMethod.GET)
    public PageResult findByDate(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "1") Integer rows,
                                 @PathVariable("sdate") String sdate, @PathVariable("edate") String edate) {
        PageResult pageResult = new PageResult();
        // arg1 第几页,arg2 pageSize,conut 计算总数
        Page<Object> pageHelper = PageHelper.startPage(page, rows, true);

        List<Income> incomeList = new ArrayList<>();
        incomeList = this.incomeService.findByDate(sdate, edate);

        // 获取总记录数
        Long total = pageHelper.getTotal();

        // 设置footer
        Footer footer = new Footer();
        footer.setCategory("缴纳合计：");
        double totalMoney = 0;
        for (Income income : incomeList) {
            totalMoney += income.getMoney();
        }
        footer.setMoney(totalMoney);

        List<Footer> footerList = new ArrayList<>();
        footerList.add(footer);

        pageResult.setFooter(footerList);
        pageResult.setRows(incomeList);
        pageResult.setTotal(Integer.parseInt(total.toString()));
        return pageResult;
    }
}
