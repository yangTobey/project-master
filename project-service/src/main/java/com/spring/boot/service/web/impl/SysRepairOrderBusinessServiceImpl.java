package com.spring.boot.service.web.impl;

import com.spring.boot.bean.cluster.RepairOrder;
import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.dao.web.cluster.RepairOrderDao;
import com.spring.boot.dao.web.master.SysCompanyDao;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysRepairOrderBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysRepairOrderBusinessServiceImpl implements SysRepairOrderBusinessService {
    @Autowired
    private RepairOrderDao repairOrderDao;

    @Override
    public List<RepairOrder> getRepairOrderForMonth(Map<String, Object> map) {
        return repairOrderDao.getRepairOrderForMonth(map);
    }

    @Override
    public RepairOrder getRepairOrderForLastMonth(Integer year,Integer month) {
        return repairOrderDao.getRepairOrderForLastMonth(year,month);
    }

    @Override
    public RepairOrder getRepairOrderFinish(Map<String, Object> map) {
        return repairOrderDao.getRepairOrderFinish(map);
    }
}
