package com.spring.boot.bean.master.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 小业主应收款详细信息
 * Created by Administrator on 2018/3/16.
 */
public class SysReceivableAccountsOwnerEntity {
    //对应月份
    //private Integer month;
    //对应数据所在年份所在月份列表
    private String monthsInfo;

    private Map<Integer,Object> receivableAccounts;
    private Map<Integer,Object> completeAccounts;

    //一月份应收款
    //private Double receivableAccountsJan;
    //一月份已收款
    //private Double completeAccountsJan;

    //二月份应收款
    //private Double receivableAccountsFeb;
    //二月份已收款
    //private Double completeAccountsFeb;

    //三月份应收款
    //private Double receivableAccountsMar;
    //三月份已收款
    //private Double completeAccountsMar;

    //四月份应收款
    //private Double receivableAccountsApr;
    //四月份已收款
    //private Double completeAccountsApr;

    //五月份应收款
    //private Double receivableAccountsMay;
    //五月份已收款
    //private Double completeAccountsMay;

    //六月份应收款
   // private Double receivableAccountsJune;
    //六月份已收款
    //private Double completeAccountsJune;

    //七月份应收款
    //private Double receivableAccountsJuly;
    //七月份已收款
    //private Double completeAccountsJuly;

    //八月份应收款
    //private Double receivableAccountsAug;
    //八月份已收款
    //private Double completeAccountsAug;

    //九月份应收款
   // private Double receivableAccountsSept;
    //九月份已收款
    //private Double completeAccountsSept;

    //十月份应收款
    //private Double receivableAccountsOct;
    //十月份已收款
    //private Double completeAccountsOct;

    //十一月份应收款
    //private Double receivableAccountsNov;
    //十一月份已收款
    //private Double completeAccountsNov;

    //十二月份应收款
    //private Double receivableAccountsDec;
    //十二月份已收款
    //private Double completeAccountsDec;


    public String getMonthsInfo() {
        return monthsInfo;
    }

    public void setMonthsInfo(String monthsInfo) {
        this.monthsInfo = monthsInfo;
    }

    public Map<Integer, Object> getReceivableAccounts() {
        return receivableAccounts;
    }

    public void setReceivableAccounts(Map<Integer, Object> receivableAccounts) {
        this.receivableAccounts = receivableAccounts;
    }

    public Map<Integer, Object> getCompleteAccounts() {
        return completeAccounts;
    }

    public void setCompleteAccounts(Map<Integer, Object> completeAccounts) {
        this.completeAccounts = completeAccounts;
    }
}


