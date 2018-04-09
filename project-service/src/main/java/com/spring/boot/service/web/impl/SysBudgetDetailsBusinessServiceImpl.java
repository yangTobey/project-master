package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysBudgetDetails;
import com.spring.boot.dao.web.master.SysAccountsReceivableDao;
import com.spring.boot.dao.web.master.SysBudgetDetailsDao;
import com.spring.boot.service.web.SysAccountsReceivableBusinessService;
import com.spring.boot.service.web.SysBudgetDetailsBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysBudgetDetailsBusinessServiceImpl implements SysBudgetDetailsBusinessService {
    @Autowired
    private SysBudgetDetailsDao sysBudgetDetailsDao;

    @Override
    public SysBudgetDetails sysBudgetDetailsAnalysis(Map<String, Object> map) {
        return sysBudgetDetailsDao.sysBudgetDetailsAnalysis(map);
    }

    @Override
    public SysAccountsReceivable findRecordByYearAndMonth(Integer year, Integer month) {
        return sysBudgetDetailsDao.findRecordByYearAndMonth(year,month);
    }

    @Override
    public List<SysBudgetDetails> sysBudgetDetailsIncomeForMonth(Map<String, Object> map) {
        return sysBudgetDetailsDao.sysBudgetDetailsIncomeForMonth(map);
    }

    @Override
    public SysBudgetDetails sysBudgetRealProfitsByMonth(Integer year, Integer month,List<Long> sysUserCompanyIds) {
        return sysBudgetDetailsDao.sysBudgetRealProfitsByMonth(year,month,sysUserCompanyIds);
    }

    @Override
    public String sysAccountsReceivableMonths(Map<String, Object> map) {
        return null;
    }

    @Override
    public int addSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {
        return 0;
    }

    @Override
    public int updateSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {
        return 0;
    }
}


