package com.lm.service;

import com.lm.mapper.DictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Louie on 2017-06-02.
 */
@Service
public class DictService {

    @Autowired
    private DictMapper dictMapper;

    public String findDict(String dname) {
        return this.dictMapper.findDict(dname);
    }

    public Integer updDict(String dname, String context) {
        return this.dictMapper.updDict(dname, context);
    }
}
