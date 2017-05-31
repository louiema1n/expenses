package com.lm.domain;

import java.util.Date;

/**
 * 支出实体
 * Created by Louie on 2017-05-29.
 */
public class Expenses {

    private Integer id; //id
    private String category;    //类别
    private String remark;  //细类
    private double money;   //金额
    private String exdate;    //消费时间
    private String name;    // 支出人

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getExdate() {
        return exdate;
    }

    public void setExdate(String exdate) {
        this.exdate = exdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
