package com.spring.boot.service.web.impl;

import com.spring.boot.dao.web.master.CompanyDao;
import com.spring.boot.dao.web.master.DepartmentDao;
import com.spring.boot.service.web.CompanyBusinessService;
import com.spring.boot.service.web.DepartmentBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class DepartmentBusinessServiceImpl implements DepartmentBusinessService {
    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public int addDepartment(Map<String, Object> map) {
        return departmentDao.addDepartment(map);
    }

    @Override
    public int updateDepartmentInfo(Map<String, Object> map) {
        return departmentDao.updateDepartmentInfo(map);
    }

    @Override
    public int deleteDepartment(Map<String, Object> map) {
        return departmentDao.deleteDepartment(map);
    }
}
