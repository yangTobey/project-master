package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.bean.master.SysEnergy;
import com.spring.boot.bean.master.SysProject;
import com.spring.boot.dao.web.master.SysChargeDao;
import com.spring.boot.dao.web.master.SysProjectDao;
import com.spring.boot.service.web.SysChargeBusinessService;
import com.spring.boot.service.web.SysProjectBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysProjectBusinessServiceImpl implements SysProjectBusinessService {
    @Autowired
    private SysProjectDao sysProjectDao;

    @Override
    public int updateSysProject(Map<String, Object> map) {
        return sysProjectDao.updateSysProject(map);
    }

    @Override
    public int addSysProject(SysProject sysProject) {
        return sysProjectDao.addSysProject(sysProject);
    }
}
