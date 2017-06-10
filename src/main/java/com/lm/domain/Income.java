package com.lm.domain;

/**
 * 缴纳实体
 * Created by Louie on 2017-05-29.
 */
public class Income {

    private Integer id; //id
    private String category;    //类别
    private String remark;  //备注
    private double money;   //金额
    private String exdate;    //缴纳时间
    private String name;    // 缴纳人

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

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", remark='" + remark + '\'' +
                ", money=" + money +
                ", exdate='" + exdate + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
