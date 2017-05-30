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

    public Integer addExpenses(String category, String remark, Double money, String exdate) {
        return this.expensesMapper.add(category, remark, money, exdate);
    }

    public Integer delExpenses(Integer id) {
        return this.expensesMapper.delete(id);
    }

    public Integer upExpenses(Integer id, String category, String remark, Double money, String exdate) {
        return this.expensesMapper.update(id, category, remark, money, exdate);
    }

    public List<Expenses> findByDate(String sdate, String edate) {
        return this.expensesMapper.findByDate(sdate, edate);
    }
}
