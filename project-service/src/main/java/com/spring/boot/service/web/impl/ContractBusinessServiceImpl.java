package com.spring.boot.service.web.impl;

import com.spring.boot.dao.web.master.ContractDao;
import com.spring.boot.service.web.ContractBusinessService;
import com.spring.boot.service.web.DepartmentBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class ContractBusinessServiceImpl implements ContractBusinessService {
    @Autowired
    private ContractDao contractDao;

    @Override
    public int addContractType(Map<String, Object> map) {
        return contractDao.addContractType(map);
    }

    @Override
    public int updateContractType(Map<String, Object> map) {
        return contractDao.updateContractType(map);
    }

    @Override
    public int deleteContractType(Map<String, Object> map) {
        return contractDao.deleteContractType(map);
    }
}
