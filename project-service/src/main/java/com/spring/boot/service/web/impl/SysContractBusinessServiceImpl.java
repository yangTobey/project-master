package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysContract;
import com.spring.boot.dao.web.master.SysContractDao;
import com.spring.boot.dao.web.master.SysContractTypeDao;
import com.spring.boot.service.web.SysContractBusinessService;
import com.spring.boot.service.web.SysDepartmentBusinessService;
import com.spring.boot.service.web.SysContractBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysContractBusinessServiceImpl implements SysContractBusinessService {
    @Autowired
    private SysContractDao sysContractDao;

    @Autowired
    private SysContractTypeDao sysContractTypeDao;

    @Override
    public int addContractType(Map<String, Object> map) {
        return sysContractTypeDao.save(map);
    }

    @Override
    public int updateContractType(Map<String, Object> map) {
        return sysContractTypeDao.update(map);
    }

    @Override
    public int deleteContractType(Map<String, Object> map) {
        return sysContractTypeDao.delete(map);
    }

    @Override
    public int addSysContract(Map<String, Object> map) {
        return sysContractDao.save(map);
    }

    @Override
    public int updateSysContract(Map<String, Object> map) {
        return sysContractDao.updateSysContract(map);
    }

    @Override
    public int deleteSysContract(Map<String, Object> map) {
        return sysContractDao.deleteSysContract(map);
    }

    @Override
    public SysContract findSysContractByContractCode(String contractCode) {
        return sysContractDao.findSysContractByContractCode(contractCode);
    }

    @Override
    public List<SysContract> sysContractDataList(Map<String, Object> map) {
        return sysContractDao.sysContractDataList(map);
    }

    @Override
    public int sysContractDataTotal(Map<String, Object> map) {
        return sysContractDao.sysContractDataTotal(map);
    }
}