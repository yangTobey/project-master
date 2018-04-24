package com.spring.boot.bean.master.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * 财务大屏数据展示实体类
 * Created by xiaoyang on 2018/4/24.
 */
public class SysFinancialDataAnalysisEntity implements Serializable {

    /********************************收费情况统计信息************************/
    private  Map<String, Object> sysChargeDetailsMap;
    /********************************应收账款统计信息************************/
    private  Map<String, Object> sysAccountsReceivableMap;
    /********************************执行预算统计信息************************/
    private  Map<String, Object> sysBudgetDetailsMap;

    public Map<String, Object> getSysChargeDetailsMap() {
        return sysChargeDetailsMap;
    }

    public void setSysChargeDetailsMap(Map<String, Object> sysChargeDetailsMap) {
        this.sysChargeDetailsMap = sysChargeDetailsMap;
    }

    public Map<String, Object> getSysAccountsReceivableMap() {
        return sysAccountsReceivableMap;
    }

    public void setSysAccountsReceivableMap(Map<String, Object> sysAccountsReceivableMap) {
        this.sysAccountsReceivableMap = sysAccountsReceivableMap;
    }

    public Map<String, Object> getSysBudgetDetailsMap() {
        return sysBudgetDetailsMap;
    }

    public void setSysBudgetDetailsMap(Map<String, Object> sysBudgetDetailsMap) {
        this.sysBudgetDetailsMap = sysBudgetDetailsMap;
    }
}


