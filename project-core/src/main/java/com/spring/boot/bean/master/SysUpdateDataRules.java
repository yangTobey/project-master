package com.spring.boot.bean.master;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/2.
 */
public class SysUpdateDataRules implements Serializable {
    private Integer id;
    private Integer day;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
