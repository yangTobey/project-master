package com.spring.boot.service.web.impl;

import com.spring.boot.dao.web.master.SysContractDao;
import com.spring.boot.service.web.SysContractBusinessService;
import com.spring.boot.service.web.SysDepartmentBusinessService;
import com.spring.boot.service.web.SysContractBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysContractBusinessServiceImpl implements SysContractBusinessService {
    @Autowired
    private SysContractDao sysContractDao;

    @Override
    public int addContractType(Map<String, Object> map) {
        return sysContractDao.addContractType(map);
    }

    @Override
    public int updateContractType(Map<String, Object> map) {
        return sysContractDao.updateContractType(map);
    }

    @Override
    public int deleteContractType(Map<String, Object> map) {
        return sysContractDao.deleteContractType(map);
    }
}
