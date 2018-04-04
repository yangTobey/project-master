package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.dao.web.master.SysAccountsReceivableDao;
import com.spring.boot.dao.web.master.SysCompanyDao;
import com.spring.boot.service.web.SysAccountsReceivableBusinessService;
import com.spring.boot.service.web.SysCompanyBusinessService;
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
    public int addSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {
        return sysAccountsReceivableDao.addSysAccountsReceivable(sysAccountsReceivable);
    }

    @Override
    public int updateSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable) {
        return sysAccountsReceivableDao.updateSysAccountsReceivable(sysAccountsReceivable);
    }
}
