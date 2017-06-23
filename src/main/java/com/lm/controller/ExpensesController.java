package com.lm.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lm.domain.Expenses;
import com.lm.domain.Footer;
import com.lm.domain.PageResult;
import com.lm.domain.UpdImg;
import com.lm.service.ExpensesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louie on 2017-05-29.
 */
@RestController
@RequestMapping("/exp")
public class ExpensesController {

    private static final Logger LOGGER = LoggerFactory.getLogger("log");

    // 图片存储地址
    @Value("${web.updImg-path}")
    private String path;

    @Autowired
    private ExpensesService expensesService;

    /**
     * 显示所有expenses
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public PageResult all(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "rows", defaultValue = "1") Integer rows) {
        PageResult pageResult = new PageResult();
        // arg1 第几页,arg2 pageSize,conut 计算总数
        Page<Object> pageHelper = PageHelper.startPage(page, rows, true);

        LOGGER.info("【查询】所有支出数据。");

        // 获取记录
        List<Expenses> expensesList = new ArrayList<>();
        expensesList = this.expensesService.findAll();

        // 获取总记录数
        Long total = pageHelper.getTotal();

        // 设置footer属性
        Footer footer = new Footer();
        footer.setCategory("支出合计：");
        // 遍历list，计算money总和
        double totalMoney = 0;
        for (Expenses exp : expensesList) {
            totalMoney += exp.getMoney();
        }
        footer.setMoney(totalMoney);

        List<Footer> footerList = new ArrayList<>();
        footerList.add(footer);

        pageResult.setRows(expensesList);
        pageResult.setTotal(Integer.parseInt(total.toString()));
        pageResult.setFooter(footerList);

        return pageResult;
    }

    /**
     * 新增Expenses
     * @param expenses
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String add(Expenses expenses, MultipartFile file) {
        if (!file.isEmpty()) {
            // 上传图片
            String imgurl = new UpdImg().upload(file, path);
            // 格式化图片地址
            expenses.setImgurl("<a href=\"javascript:void(0)\" onclick=\"view('" + imgurl + "')\">查看图片</a>");
        }

        LOGGER.info("【新增】支出记录:" +
                expenses.getName() + "在" +
                expenses.getExdate() + "共支出" +
                expenses.getMoney() + "用于" +
                expenses.getCategory() + "的" +
                expenses.getRemark());
        Integer i = this.expensesService.addExpenses(
                expenses.getCategory(),
                expenses.getRemark(),
                expenses.getMoney(),
                expenses.getExdate(),
                expenses.getName(),
                expenses.getImgurl());
        if (i > 0) {
            LOGGER.info("新增成功。");
            return "新增成功。";
        }
        LOGGER.info("新增失败!");
        return "新增失败！";
    }

    /**
     * 删除Expenses
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        LOGGER.info("【删除】支出记录:" + this.expensesService.findById(id));
        Integer i = this.expensesService.delExpenses(id);
        if (i > 0) {
            return "删除成功。";
        }
        return "删除失败！";
    }

    /**
     * 更新Expenses
     * @param expenses
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(Expenses expenses) {
        LOGGER.info("【更新前】支出记录：" + this.expensesService.findById(expenses.getId()));
        Integer i = expensesService.upExpenses(
                expenses.getId(),
                expenses.getCategory(),
                expenses.getRemark(),
                expenses.getMoney(),
                expenses.getExdate(),
                expenses.getName());
        if (i > 0) {
            LOGGER.info("【更新后】支出记录：" + expenses);
            return "修改成功。";
        }
        LOGGER.info("更新失败");
        return "修改失败！";
    }

    /**
     * 根据日期段查询
     * @param page
     * @param rows
     * @param sdate
     * @param edate
     * @return
     */
    @RequestMapping(value = "/{sdate}/{edate}", method = RequestMethod.GET)
    public PageResult findByDate(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "1") Integer rows,
                                 @PathVariable("sdate") String sdate, @PathVariable("edate") String edate) {
        PageResult pageResult = new PageResult();
        // arg1 第几页,arg2 pageSize,conut 计算总数
        Page<Object> pageHelper = PageHelper.startPage(page, rows, true);

        List<Expenses> expensesList = new ArrayList<>();
        expensesList = this.expensesService.findByDate(sdate, edate);

        // 获取总记录数
        Long total = pageHelper.getTotal();

        // 设置footer
        Footer footer = new Footer();
        footer.setCategory("支出合计：");
        double totalMoney = 0;
        for (Expenses expenses : expensesList) {
            totalMoney += expenses.getMoney();
        }
        footer.setMoney(totalMoney);

        List<Footer> footerList = new ArrayList<>();
        footerList.add(footer);

        pageResult.setFooter(footerList);
        pageResult.setRows(expensesList);
        pageResult.setTotal(Integer.parseInt(total.toString()));
        return pageResult;
    }

    /**
     * 单日期查询
     * @param page
     * @param rows
     * @param date
     * @return
     */
    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public PageResult findByDate(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "1") Integer rows,
                                 @PathVariable("date") String date) {
        String s = date.substring(0, 9);
        String e = date.substring(9);
        int temp = Integer.valueOf(e).intValue() + 1;
        String edate = s + temp + "";
        String sdate = date;

        PageResult pageResult = new PageResult();
        // arg1 第几页,arg2 pageSize,conut 计算总数
        Page<Object> pageHelper = PageHelper.startPage(page, rows, true);

        List<Expenses> expensesList = new ArrayList<>();
        expensesList = this.expensesService.findByDate(sdate, edate);

        // 获取总记录数
        Long total = pageHelper.getTotal();

        // 设置footer
        Footer footer = new Footer();
        footer.setCategory("支出合计：");
        double totalMoney = 0;
        for (Expenses expenses : expensesList) {
            totalMoney += expenses.getMoney();
        }
        footer.setMoney(totalMoney);

        List<Footer> footerList = new ArrayList<>();
        footerList.add(footer);

        pageResult.setFooter(footerList);
        pageResult.setRows(expensesList);
        pageResult.setTotal(Integer.parseInt(total.toString()));
        return pageResult;
    }
}
