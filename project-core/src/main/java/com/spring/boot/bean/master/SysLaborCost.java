package com.spring.boot.bean.master;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/3/12.
 */
public class SysLaborCost {
    private long laborCostId;
    private String year;
    private String month;
    private long companyId;
    private Timestamp createTime;
    private int statusCode;

    public long getLaborCostId() {
        return laborCostId;
    }

    public void setLaborCostId(long laborCostId) {
        this.laborCostId = laborCostId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
