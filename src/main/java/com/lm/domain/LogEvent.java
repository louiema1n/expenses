package com.lm.domain;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志记录实体
 * Created by Louie on 2017-06-10.
 */
public class LogEvent {
    private BigInteger timestmp;
    private String time;
    private String level_string;
    private String caller_class;
    private String formatted_message;
    //  格式化时间戳
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(getTimestmp().longValue()));
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigInteger getTimestmp() {
        return timestmp;
    }

    public void setTimestmp(BigInteger timestmp) {
        this.timestmp = timestmp;
    }

    public String getLevel_string() {
        return level_string;
    }

    public void setLevel_string(String level_string) {
        this.level_string = level_string;
    }

    public String getCaller_class() {
        return caller_class;
    }

    public void setCaller_class(String caller_class) {
        this.caller_class = caller_class;
    }

    public String getFormatted_message() {
        return formatted_message;
    }

    public void setFormatted_message(String formatted_message) {
        this.formatted_message = formatted_message;
    }
}
