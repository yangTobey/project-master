package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysDepartment;
import com.spring.boot.bean.master.SysLaborCost;
import com.spring.boot.bean.master.SysLaborCostDetails;
import com.spring.boot.dao.web.master.SysCompanyDao;
import com.spring.boot.dao.web.master.SysLaborCostDao;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysLaborCostBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysLaborCostBusinessServiceImpl implements SysLaborCostBusinessService {
    @Autowired
    private SysLaborCostDao sysLaborCostDao;

    @Override
    public List<SysLaborCost> getSysLaborCostList() {
        return sysLaborCostDao.queryList();
    }

    @Override
    public int addSysLaborCost(SysLaborCost sysLaborCost) {
        return sysLaborCostDao.addSysLaborCost(sysLaborCost);
    }

    @Override
    public int addSysLaborCostDetails(SysLaborCostDetails sysLaborCostDetails) {
        return sysLaborCostDao.addSysLaborCostDetails(sysLaborCostDetails);
    }

    @Override
    public int updateSysLaborCostInfo(Map<String, Object> map) {
        return sysLaborCostDao.update(map);
    }

    @Override
    public int deleteSysLaborCostInfo(Map<String, Object> map) {
        return sysLaborCostDao.delete(map);
    }
}
