package com.spring.boot.service.web.impl;

import com.spring.boot.dao.web.master.SysCompanyDao;
import com.spring.boot.dao.web.master.SysDepartmentDao;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysDepartmentBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysDepartmentBusinessServiceImpl implements SysDepartmentBusinessService {
    @Autowired
    private SysDepartmentDao sysDepartmentDao;

    @Override
    public int addDepartment(Map<String, Object> map) {
        return sysDepartmentDao.addDepartment(map);
    }

    @Override
    public int updateDepartmentInfo(Map<String, Object> map) {
        return sysDepartmentDao.updateDepartmentInfo(map);
    }

    @Override
    public int deleteDepartment(Map<String, Object> map) {
        return sysDepartmentDao.deleteDepartment(map);
    }
}
