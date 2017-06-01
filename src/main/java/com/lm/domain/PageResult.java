package com.lm.domain;

import java.util.List;

/**
 * 分页结果格式化
 * Created by Louie on 2017-05-30.
 */
public class PageResult {
    private Integer total;  // 总数
    private List<?> rows;   // 所有记录
    private List<Footer> footer;

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

    public List<Footer> getFooter() {
        return footer;
    }

    public void setFooter(List<Footer> footer) {
        this.footer = footer;
    }
}
