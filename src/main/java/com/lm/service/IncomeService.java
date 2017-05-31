package com.lm.service;

import com.lm.domain.Income;
import com.lm.mapper.IncomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Louie on 2017-05-29.
 */
@Service
public class IncomeService {

    @Autowired
    private IncomeMapper incomeMapper;

    public List<Income> findAll() {
        return this.incomeMapper.findAll();
    }

    public Integer addIncome(String category, String remark, Double money, String exdate, String name) {
        return this.incomeMapper.add(category, remark, money, exdate, name);
    }

    public Integer delIncome(Integer id) {
        return this.incomeMapper.delete(id);
    }

    public Integer upIncome(Integer id, String category, String remark, Double money, String exdate, String name) {
        return this.incomeMapper.update(id, category, remark, money, exdate, name);
    }

    public List<Income> findByDate(String sdate, String edate) {
        return this.incomeMapper.findByDate(sdate, edate);
    }
}
