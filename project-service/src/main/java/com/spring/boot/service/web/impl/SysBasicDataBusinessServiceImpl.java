package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysBasicData;
import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.dao.web.master.SysBasicDataDao;
import com.spring.boot.dao.web.master.SysCompanyDao;
import com.spring.boot.service.web.SysBasicDataBusinessService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysBasicDataBusinessServiceImpl implements SysBasicDataBusinessService {
    @Autowired
    private SysBasicDataDao sysBasicDataDao;

    @Override
    public List<SysBasicData> sysBasicDataAnalysisData(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<SysBasicData> sysBasicDataAnalysisList(Map<String, Object> map) {
        return sysBasicDataDao.queryList(map);
    }

    @Override
    public int sysBasicDataAnalysisListTotal(Map<String, Object> map) {
        return sysBasicDataDao.getSysCompanyListTotal(map);
    }

    @Override
    public int addSysBasicData(Map<String, Object> map) {
        return sysBasicDataDao.addSysBasicData(map);
    }

    @Override
    public int updateSysBasicData(Map<String, Object> map) {
        return sysBasicDataDao.updateSysBasicData(map);
    }

    @Override
    public int deleteSysBasicData(Map<String, Object> map) {
        return sysBasicDataDao.deleteSysBasicData(map);
    }
}
