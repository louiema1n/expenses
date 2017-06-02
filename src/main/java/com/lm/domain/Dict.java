package com.lm.domain;

/**
 * 字典管理
 * Created by Louie on 2017-06-02.
 */
public class Dict {

    private Integer id;
    private String dname;
    private String context;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
