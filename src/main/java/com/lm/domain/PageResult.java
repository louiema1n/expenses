package com.lm.domain;

import java.util.List;

/**
 * 分页结果格式化
 * Created by Louie on 2017-05-30.
 */
public class PageResult {
    private Integer total;
    private List<?> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
