package com.spring.boot.bean.master.entity;

import com.spring.boot.bean.master.SysBudgetDetails;
import com.spring.boot.bean.master.SysChargeDetails;

import java.io.Serializable;
import java.util.Map;

/**
 * 财务大屏数据展示实体类
 * Created by xiaoyang on 2018/4/24.
 */
public class SysFinancialDataAnalysisEntity implements Serializable {

    /********************************收费情况统计信息************************/
    private SysChargeDetails sysChargeDetails;
    /********************************应收账款统计信息************************/
    private Map<String, Object> sysAccountsReceivableMap;
    /********************************执行预算统计信息************************/
    private SysBudgetDetails sysBudgetDetails;

    public SysChargeDetails getSysChargeDetails() {
        return sysChargeDetails;
    }

    public void setSysChargeDetails(SysChargeDetails sysChargeDetails) {
        this.sysChargeDetails = sysChargeDetails;
    }

    public Map<String, Object> getSysAccountsReceivableMap() {
        return sysAccountsReceivableMap;
    }

    public void setSysAccountsReceivableMap(Map<String, Object> sysAccountsReceivableMap) {
        this.sysAccountsReceivableMap = sysAccountsReceivableMap;
    }

    public SysBudgetDetails getSysBudgetDetails() {
        return sysBudgetDetails;
    }

    public void setSysBudgetDetails(SysBudgetDetails sysBudgetDetails) {
        this.sysBudgetDetails = sysBudgetDetails;
    }
}


