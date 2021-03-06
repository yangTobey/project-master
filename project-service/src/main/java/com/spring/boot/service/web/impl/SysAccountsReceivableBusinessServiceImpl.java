package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.entity.SysReceivableAccountsOwnerEntity;
import com.spring.boot.dao.web.master.SysAccountsReceivableDao;
import com.spring.boot.service.web.SysAccountsReceivableBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysAccountsReceivableBusinessServiceImpl implements SysAccountsReceivableBusinessService {
    @Autowired
    private SysAccountsReceivableDao sysAccountsReceivableDao;

    @Override
    public SysAccountsReceivable sysAccountsReceivableAnalysis(Map<String, Object> map) {
        return sysAccountsReceivableDao.sysAccountsReceivableAnalysis(map);
    }

    @Override
    public SysAccountsReceivable findRecordByYearAndMonth(Integer year, Integer month,Long companyId) {
        return sysAccountsReceivableDao.findRecordByYearAndMonth(year,month,companyId);
    }

    @Override
    public List<SysAccountsReceivable> sysAccountsReceivableAnalysisForMonth(Map<String, Object> map) {
        return sysAccountsReceivableDao.sysAccountsReceivableAnalysisForMonth(map);
    }

    @Override
    public SysAccountsReceivable findSysAccountsReceivableById(Map<String, Object> map) {
        return sysAccountsReceivableDao.findSysAccountsReceivableById(map);
    }

    @Override
    public String sysAccountsReceivableMonths(Map<String, Object> map) {
        return sysAccountsReceivableDao.sysAccountsReceivableMonths(map);
    }

    @Override
    public int addSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {
        return sysAccountsReceivableDao.addSysAccountsReceivable(sysAccountsReceivable);
    }

    @Override
    public List<SysAccountsReceivable> sysAccountsReceivableList(Map<String, Object> map) {
        return sysAccountsReceivableDao.sysAccountsReceivableList(map);
    }

    @Override
    public int sysAccountsReceivableListTotal(Map<String, Object> map) {
        return sysAccountsReceivableDao.sysAccountsReceivableListTotal(map);
    }

    @Override
    public int updateSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {
        return sysAccountsReceivableDao.updateSysAccountsReceivable(sysAccountsReceivable);
    }

    @Override
    public int deleteSysAccountsReceivable(Long budgetId) {
        return sysAccountsReceivableDao.deleteSysAccountsReceivable(budgetId);
    }
}
