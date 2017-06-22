package com.lm.domain;

import java.util.List;

/**
 * easyui tree数据实体
 * Created by Louie on 2017-06-22.
 */
public class EasyuiTree {
    private long id;
    private String text;
    private Boolean checked = Boolean.FALSE;
    private String state;// 默认是 'open'。当设置为 'closed' 时，该节点有子节点
    private List<EasyuiTree> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<EasyuiTree> getChildren() {
        return children;
    }

    public void setChildren(List<EasyuiTree> children) {
        this.children = children;
    }
}
