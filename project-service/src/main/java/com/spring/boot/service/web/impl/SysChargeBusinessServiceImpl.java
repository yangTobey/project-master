package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.bean.master.SysQualityManage;
import com.spring.boot.bean.master.SysQualityManageFile;
import com.spring.boot.bean.master.entity.SysQualityManageEntity;
import com.spring.boot.dao.web.master.SysChargeDao;
import com.spring.boot.dao.web.master.SysQualityManageDao;
import com.spring.boot.service.web.SysChargeBusinessService;
import com.spring.boot.service.web.SysQualityManageBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysChargeBusinessServiceImpl implements SysChargeBusinessService {
    @Autowired
    private SysChargeDao sysChargeDao;

    @Override
    public SysChargeDetails sysChargeDetails(Map<String, Object> map) {
        return sysChargeDao.sysChargeDetails(map);
    }

    @Override
    public SysChargeDetails sysChargeDetailsForYear(Map<String, Object> map) {
        return sysChargeDao.sysChargeDetailsForYear(map);
    }

    @Override
    public SysChargeDetails findSysChargeDetailsById(Long chargeId) {
        return sysChargeDao.findSysChargeDetailsById(chargeId);
    }

    @Override
    public SysChargeDetails findSysChargeDetailsByWeekOfYear(Integer year, Integer weekOfYear,Long companyId) {
        return sysChargeDao.findSysChargeDetailsByWeekOfYear(year,weekOfYear,companyId);
    }

    @Override
    public int addSysCharge(SysChargeDetails sysChargeDetails) {
        return sysChargeDao.addSysCharge(sysChargeDetails);
    }

    @Override
    public int updateSysCharge(Map<String, Object> map) {
        return sysChargeDao.update(map);
    }
}
