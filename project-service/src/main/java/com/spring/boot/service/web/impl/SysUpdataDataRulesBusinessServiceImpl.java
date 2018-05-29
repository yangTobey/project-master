package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysUpdateDataRules;
import com.spring.boot.dao.web.master.SysUpdataDataRulesDao;
import com.spring.boot.service.web.SysUpdateDataRulesBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaoyang on 2018/1/25.
 */
@Service
public class SysUpdataDataRulesBusinessServiceImpl implements SysUpdateDataRulesBusinessService {
    @Autowired
    private SysUpdataDataRulesDao sysUpdataDataRulesDao;
    @Override
    public SysUpdateDataRules findSysUpdateDataRules() {
        return sysUpdataDataRulesDao.findSysUpdateDataRules();
    }
}
