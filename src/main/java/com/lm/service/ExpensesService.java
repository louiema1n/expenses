package com.lm.service;

import com.lm.domain.Expenses;
import com.lm.mapper.ExpensesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Louie on 2017-05-29.
 */
@Service
public class ExpensesService {

    @Autowired
    private ExpensesMapper expensesMapper;

    public List<Expenses> findAll() {
        return this.expensesMapper.findAll();
    }

    public Integer addExpenses(String category, String remark, Double money, String exdate, String name, String imgurl) {
        return this.expensesMapper.add(category, remark, money, exdate, name, imgurl);
    }

    public Integer delExpenses(Integer id) {
        return this.expensesMapper.delete(id);
    }

    public Integer upExpenses(Integer id, String category, String remark, Double money, String exdate, String name) {
        return this.expensesMapper.update(id, category, remark, money, exdate, name);
    }

    public List<Expenses> findByDate(String sdate, String edate) {
        return this.expensesMapper.findByDate(sdate, edate);
    }

    public Expenses findById(Integer id) {
        return this.expensesMapper.findById(id);
    }
}
