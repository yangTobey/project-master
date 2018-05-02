package com.spring.boot.bean.master.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.boot.bean.master.SysBudgetDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/2.
 */
public class SysBudgetDetailsEntity implements Serializable {
    private SysBudgetDetails sysBudgetDetailsForMonth;
    private SysBudgetDetails sysBudgetDetailsForYear;

    public SysBudgetDetails getSysBudgetDetailsForMonth() {
        return sysBudgetDetailsForMonth;
    }

    public void setSysBudgetDetailsForMonth(SysBudgetDetails sysBudgetDetailsForMonth) {
        this.sysBudgetDetailsForMonth = sysBudgetDetailsForMonth;
    }

    public SysBudgetDetails getSysBudgetDetailsForYear() {
        return sysBudgetDetailsForYear;
    }

    public void setSysBudgetDetailsForYear(SysBudgetDetails sysBudgetDetailsForYear) {
        this.sysBudgetDetailsForYear = sysBudgetDetailsForYear;
    }
}
