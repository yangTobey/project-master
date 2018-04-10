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
    public SysBudgetDetails sysBudgetAnalysisByCompanyId(Map<String, Object> map) {
        return sysBudgetDetailsDao.sysBudgetAnalysisByCompanyId(map);
    }

    @Override
    public SysBudgetDetails findRecordByYearAndMonth(Integer year, Integer month,Long companyId) {
        return sysBudgetDetailsDao.findRecordByYearAndMonth(year,month,companyId);
    }

    @Override
    public List<SysBudgetDetails> sysBudgetDetailsIncomeForMonth(Map<String, Object> map) {
        return sysBudgetDetailsDao.sysBudgetDetailsIncomeForMonth(map);
    }

    @Override
    public List<SysBudgetDetails> sysBudgetDetailsList(Map<String, Object> map) {
        return sysBudgetDetailsDao.sysBudgetDetailsList(map);
    }

    @Override
    public int sysBudgetDetailsListTotal(Map<String, Object> map) {
        return sysBudgetDetailsDao.sysBudgetDetailsListTotal(map);
    }

    @Override
    public SysBudgetDetails sysBudgetRealProfitsByMonth(Integer year, Integer month,List<Long> sysUserCompanyIds) {
        return sysBudgetDetailsDao.sysBudgetRealProfitsByMonth(year,month,sysUserCompanyIds);
    }

    @Override
    public SysBudgetDetails findSysBudgetDetailsById(Map<String, Object> map) {
        return sysBudgetDetailsDao.findSysBudgetDetailsById(map);
    }

    @Override
    public String sysAccountsReceivableMonths(Map<String, Object> map) {
        return null;
    }

    @Override
    public int addSysBudgetDetails(SysBudgetDetails sysBudgetDetails) {
        return sysBudgetDetailsDao.addSysBudgetDetails(sysBudgetDetails);
    }

    @Override
    public int updateSysBudgetDetails(SysBudgetDetails sysBudgetDetails) {
        return sysBudgetDetailsDao.updateSysBudgetDetails(sysBudgetDetails);
    }

    @Override
    public int deleteSysBudgetDetails(Long budgetId) {
        return sysBudgetDetailsDao.deleteSysBudgetDetails(budgetId);
    }
}


